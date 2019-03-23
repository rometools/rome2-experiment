package com.rometools.rome.factory.generator;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import java.time.Instant;
import java.util.TreeSet;
import javax.annotation.Generated;
import javax.lang.model.element.Modifier;

public class EntityGenerator {

  private final ModelGenerator modelGenerator;
  private final Model.Entity entity;
  private final ClassName mainClassName;

  public EntityGenerator(
      ModelGenerator modelGenerator, Model.Entity entity, ClassName mainClassName) {
    this.modelGenerator = modelGenerator;
    this.entity = entity;
    this.mainClassName = mainClassName;
  }

  public TypeSpec generate() {
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

    TreeSet<Field> fields = new TreeSet<>(entity.getFields());

    boolean isFirst = true;
    for (Field field : fields) {
      new FieldGenerator(
              modelGenerator,
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

    mainClass.addAnnotation(
        AnnotationSpec.builder(Generated.class)
            .addMember("value", "$S", getClass().getName())
            .addMember("date", "$S", Instant.now().toString())
            .build());

    return mainClass.build();
  }
}
