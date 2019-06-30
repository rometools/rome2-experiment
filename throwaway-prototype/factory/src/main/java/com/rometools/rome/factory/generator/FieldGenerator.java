package com.rometools.rome.factory.generator;

import static com.google.common.base.CaseFormat.LOWER_CAMEL;
import static com.google.common.base.CaseFormat.UPPER_CAMEL;
import static com.rometools.rome.factory.model.OneOrMany.MANY;
import static com.rometools.rome.factory.model.OneOrMany.ONE;

import com.rometools.rome.common.value.Values;
import com.rometools.rome.factory.model.Model;
import com.rometools.rome.factory.model.Models;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.lang.model.element.Modifier;

public class FieldGenerator {

  private final ModelGenerator modelGenerator;
  private final Model model;
  private final Model.Field field;
  private final ClassName mainType;
  private final TypeSpec.Builder mainClass;
  private final MethodSpec.Builder mainClassConstructor;
  private final TypeSpec.Builder builderClass;
  private final CodeBlock.Builder equalsMethodBody;
  private final CodeBlock.Builder toStringMethodBody;
  private final CodeBlock.Builder buildMethodBody;
  private boolean isFirst;

  public FieldGenerator(
      ModelGenerator modelGenerator,
      Model model,
      Model.Field field,
      ClassName mainType,
      TypeSpec.Builder mainClass,
      MethodSpec.Builder mainClassConstructor,
      TypeSpec.Builder builderClass,
      CodeBlock.Builder equalsMethodBody,
      CodeBlock.Builder toStringMethodBody,
      CodeBlock.Builder buildMethodBody,
      boolean isFirst) {
    this.modelGenerator = modelGenerator;
    this.model = model;
    this.field = field;
    this.mainType = mainType;
    this.mainClass = mainClass;
    this.mainClassConstructor = mainClassConstructor;
    this.builderClass = builderClass;
    this.equalsMethodBody = equalsMethodBody;
    this.toStringMethodBody = toStringMethodBody;
    this.buildMethodBody = buildMethodBody;
    this.isFirst = isFirst;
  }

  public void generate() {
    addToMainConstructor();
    generateMainField();
    generateGetter();
    generateHasGetter();
    generateGetFirst();

    extendBuildMethod();
    extendEqualsMethod();
    extendToStringMethod();
    generateBuilderField();
    generateSetter();
    generateAdder();
    generateClearer();
  }

  private void addToMainConstructor() {
    mainClassConstructor.addParameter(
        ParameterSpec.builder(getFieldType(), getFieldName(), Modifier.FINAL).build());
    mainClassConstructor.addStatement("this.$L = $L", getFieldName(), getFieldName());
  }

  private void generateMainField() {
    mainClass.addField(
        FieldSpec.builder(getFieldType(), getFieldName())
            .addModifiers(Modifier.PRIVATE, Modifier.FINAL)
            .build());
  }

  public void generateGetter() {
    TypeName returnType;

    if (field.getOneOrMany() == MANY) {
      returnType = getFieldType();
    } else if (field.isValue()) {
      returnType = getValueType();
    } else {
      returnType = getEntityType();
    }

    MethodSpec.Builder builder =
        MethodSpec.methodBuilder(joinNames("get", getFieldName()))
            .addModifiers(Modifier.PUBLIC)
            .returns(returnType);

    if (field.getOneOrMany() == MANY) {
      builder
          .beginControlFlow("if ($L == null)", getFieldName())
          .addStatement("return new $T<>()", ArrayList.class)
          .endControlFlow();

    } else if (field.isEntity()) {
      builder
          .beginControlFlow("if ($L == null)", getFieldName())
          .addStatement("return $T.builder().build()", getEntityType())
          .endControlFlow();
    }

    if (field.isValue() && field.getOneOrMany() == ONE) {
      builder.addStatement("return $T.ofNullable($L)", getValueType(), getFieldName());

    } else {
      builder.addStatement("return $L", getFieldName());
    }

    mainClass.addMethod(builder.build());
  }

  public void generateHasGetter() {
    MethodSpec.Builder builder =
        MethodSpec.methodBuilder(joinNames("has", getFieldName()))
            .addModifiers(Modifier.PUBLIC)
            .returns(TypeName.BOOLEAN);

    builder.addStatement("return $L != null", getFieldName());

    mainClass.addMethod(builder.build());
  }

  private void generateGetFirst() {
    if (field.getOneOrMany() != MANY) {
      return;
    }

    TypeName returnType;
    if (field.isValue()) {
      returnType = getValueType();
    } else {
      returnType = getEntityType();
    }

    MethodSpec.Builder builder =
        MethodSpec.methodBuilder(joinNames("getFirst", field.getName()))
            .addModifiers(Modifier.PUBLIC)
            .returns(returnType);

    if (field.isValue()) {
      builder
          .beginControlFlow("if ($L == null)", getFieldName())
          .addStatement("return $T.none()", getValueType())
          .endControlFlow();

    } else {
      builder
          .beginControlFlow("if ($L == null)", getFieldName())
          .addStatement("return $T.builder().build()", getEntityType())
          .endControlFlow();
    }

    if (field.isValue()) {
      builder.addStatement("return $T.of($L.get(0))", getValueType(), getFieldName());
    } else {
      builder.addStatement("return $L.get(0)", getFieldName());
    }

    mainClass.addMethod(builder.build());
  }

  private void generateBuilderField() {
    builderClass.addField(
        FieldSpec.builder(getFieldType(), getFieldName()).addModifiers(Modifier.PRIVATE).build());
  }

  private void generateSetter() {
    builderClass.addMethod(
        MethodSpec.methodBuilder(joinNames("set", getFieldName()))
            .addModifiers(Modifier.PUBLIC)
            .addParameter(
                ParameterSpec.builder(getFieldType(), getFieldName(), Modifier.FINAL).build())
            .addStatement("this.$L = $L", getFieldName(), getFieldName())
            .addStatement("return this")
            .returns(getBuilderClassName())
            .build());
  }

  private void generateAdder() {
    if (field.getOneOrMany() != MANY) {
      return;
    }

    TypeName parameterType;
    if (field.isValue()) {
      parameterType = getValueRawType();
    } else {
      parameterType = getEntityType();
    }

    builderClass.addMethod(
        MethodSpec.methodBuilder(joinNames("add", field.getName()))
            .addModifiers(Modifier.PUBLIC)
            .addParameter(
                ParameterSpec.builder(parameterType, field.getName(), Modifier.FINAL).build())
            .addCode(
                CodeBlock.builder()
                    .beginControlFlow("if ($L == null)", field.getName())
                    .addStatement("return this")
                    .endControlFlow()
                    .add("\n")
                    .build())
            .addCode(
                CodeBlock.builder()
                    .beginControlFlow("if (this.$L == null)", getFieldName())
                    .addStatement("this.$L = new $T<>()", getFieldName(), ArrayList.class)
                    .endControlFlow()
                    .add("\n")
                    .build())
            .addStatement("this.$L.add($L)", getFieldName(), field.getName())
            .addStatement("return this")
            .returns(getBuilderClassName())
            .build());
  }

  private void generateClearer() {
    builderClass.addMethod(
        MethodSpec.methodBuilder(joinNames("clear", getFieldName()))
            .addModifiers(Modifier.PUBLIC)
            .addStatement("this.$L = null", getFieldName())
            .addStatement("return this")
            .returns(getBuilderClassName())
            .build());
  }

  private void extendBuildMethod() {
    if (!isFirst) {
      buildMethodBody.add(",\n");
    }

    buildMethodBody.add("$L", getFieldName());
  }

  private void extendEqualsMethod() {
    if (!isFirst) {
      equalsMethodBody.add("\n    && ");
    }

    equalsMethodBody.add("$T.equals($L, that.$L)", Objects.class, getFieldName(), getFieldName());
  }

  private void extendToStringMethod() {
    String part = "\"" + getFieldName() + "\":";

    if (!isFirst) {
      part = "," + part;
    }

    toStringMethodBody.addStatement("result.append($S)", part);

    boolean isString = getFieldType().equals(TypeName.get(String.class));
    if (isString) {
      toStringMethodBody.addStatement("result.append($S + $L + $S)", "\"", getFieldName(), "\"");
    } else {
      toStringMethodBody.addStatement(
          "result.append($T.valueOf($L))", String.class, getFieldName());
    }
  }

  private ClassName getBuilderClassName() {
    return mainType.nestedClass("Builder");
  }

  private String joinNames(String name1, String name2) {
    return name1 + LOWER_CAMEL.to(UPPER_CAMEL, name2);
  }

  public TypeName getFieldType() {
    TypeName type;
    if (field.isValue()) {
      type = getValueRawType();
    } else {
      type = getEntityType();
    }

    if (field.getOneOrMany() == MANY) {
      return getListType(type);
    } else {
      return type;
    }
  }

  private String getFieldName() {
    if (field.getOneOrMany() == ONE) {
      return field.getName();
    } else {
      return getListName(field.getName());
    }
  }

  private String getListName(String name) {
    return name + "List";
  }

  private TypeName getListType(TypeName type) {
    return ParameterizedTypeName.get(ClassName.get(List.class), type);
  }

  private ClassName getEntityType() {
    return Models.getEntityClassName(model.getName(), field.getName());
  }

  private TypeName getValueRawType() {
    return ClassName.get(field.getType());
  }

  private ClassName getValueType() {
    return ClassName.get(Values.getValueClass(field.getType()));
  }
}
