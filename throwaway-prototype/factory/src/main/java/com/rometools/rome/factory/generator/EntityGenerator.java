package com.rometools.rome.factory.generator;

import com.rometools.rome.common.model.ModelId;
import com.rometools.rome.factory.model.Model;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeVariableName;
import java.util.Map;
import java.util.TreeSet;
import javax.lang.model.element.Modifier;

public class EntityGenerator {

  private final ModelGenerator modelGenerator;
  private final Model model;
  private final Model.Entity entity;
  private final ClassName mainClassName;
  private final boolean inner;

  public EntityGenerator(
      ModelGenerator modelGenerator,
      Model model,
      Model.Entity entity,
      ClassName mainClassName,
      boolean inner) {
    this.modelGenerator = modelGenerator;
    this.model = model;
    this.entity = entity;
    this.mainClassName = mainClassName;
    this.inner = inner;
  }

  public TypeSpec.Builder generate() {
    TypeSpec.Builder mainClass = TypeSpec.classBuilder(mainClassName).addModifiers(Modifier.PUBLIC);

    MethodSpec.Builder mainClassConstructor = MethodSpec.constructorBuilder();

    TypeSpec.Builder builderClass =
        TypeSpec.classBuilder(mainClassName.nestedClass("Builder"))
            .addModifiers(Modifier.PUBLIC, Modifier.STATIC);

    CodeBlock.Builder buildMethodBody = CodeBlock.builder();
    buildMethodBody.add("return new $T(\n", mainClassName);
    buildMethodBody.indent();

    CodeBlock.Builder equalsMethodBody =
        CodeBlock.builder()
            .beginControlFlow("if (this == other)")
            .addStatement("return true")
            .endControlFlow()
            .add("\n")
            .beginControlFlow("if (other == null || getClass() != other.getClass())")
            .addStatement("return false")
            .endControlFlow()
            .add("\n")
            .addStatement("$T that = ($T) other", mainClassName, mainClassName)
            .add("return ");

    CodeBlock.Builder toStringMethodBody =
        CodeBlock.builder()
            .addStatement("$T result = new $T()", StringBuilder.class, StringBuilder.class)
            .addStatement("result.append($S)", "{");

    TreeSet<Model.Field> fields = new TreeSet<>(entity.getFields());

    boolean isFirst = true;
    for (Model.Field field : fields) {
      new FieldGenerator(
              modelGenerator,
              model,
              field,
              mainClassName,
              mainClass,
              mainClassConstructor,
              builderClass,
              equalsMethodBody,
              toStringMethodBody,
              buildMethodBody,
              isFirst)
          .generate();

      isFirst = false;
    }

    if (entity.getName().equals("feed")) {
      mainClassConstructor.addStatement("this._models = _models");

      builderClass.addField(
          ParameterizedTypeName.get(Map.class, ModelId.class, Object.class), "_models");

      builderClass.addMethod(
          MethodSpec.methodBuilder("_setModels")
              .addModifiers(Modifier.PUBLIC)
              .addParameter(
                  ParameterizedTypeName.get(Map.class, ModelId.class, Object.class), "_models")
              .addStatement("this._models = _models")
              .build());

      buildMethodBody.add(",\n_models");
    }

    builderClass.addMethod(
        MethodSpec.methodBuilder("build")
            .returns(mainClassName)
            .addModifiers(Modifier.PUBLIC)
            .addCode(buildMethodBody.add("\n").unindent().addStatement(")").build())
            .build());

    mainClass.addType(builderClass.build());

    mainClass.addMethod(
        MethodSpec.methodBuilder("builder")
            .returns(mainClassName.nestedClass("Builder"))
            .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
            .addStatement("return new $T()", mainClassName.nestedClass("Builder"))
            .build());

    if (entity.getName().equals("feed")) {
      mainClassConstructor.addParameter(
          ParameterSpec.builder(
                  ParameterizedTypeName.get(Map.class, ModelId.class, Object.class),
                  "_models",
                  Modifier.FINAL)
              .build());
    }
    mainClass.addMethod(mainClassConstructor.build());

    mainClass.addMethod(
        MethodSpec.methodBuilder("equals")
            .addAnnotation(Override.class)
            .addModifiers(Modifier.PUBLIC)
            .returns(TypeName.BOOLEAN)
            .addParameter(Object.class, "other", Modifier.FINAL)
            .addCode(equalsMethodBody.add(";\n").build())
            .build());

    mainClass.addMethod(
        MethodSpec.methodBuilder("toString")
            .addAnnotation(Override.class)
            .addModifiers(Modifier.PUBLIC)
            .returns(ClassName.get(String.class))
            .addCode(toStringMethodBody.build())
            .addStatement("result.append($S)", "}")
            .addStatement("return result.toString()")
            .build());

    if (entity.getName().equals("feed")) {
      mainClass.addField(
          FieldSpec.builder(
                  ParameterizedTypeName.get(Map.class, ModelId.class, Object.class),
                  "_models",
                  Modifier.PRIVATE,
                  Modifier.FINAL)
              .build());

      TypeVariableName typeVariable = TypeVariableName.get("T");

      mainClass.addMethod(
          MethodSpec.methodBuilder("as")
              .addModifiers(Modifier.PUBLIC)
              .addTypeVariable(typeVariable)
              .addParameter(
                  ParameterizedTypeName.get(ClassName.get(ModelId.class), typeVariable), "modelId")
              .addStatement("return modelId.cast(_models.get(modelId))")
              .returns(typeVariable)
              .build());
    }

    return mainClass;
  }
}
