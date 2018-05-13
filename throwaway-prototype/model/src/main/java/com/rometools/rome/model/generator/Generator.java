package com.rometools.rome.model.generator;

import com.google.common.base.CaseFormat;
import com.rometools.rome.model.Model;
import com.rometools.rome.model.Node;
import com.rometools.rome.model.value.Values;
import com.rometools.rome.model.xml.jaxb.Multiplicity;
import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.util.ArrayList;
import javax.annotation.Generated;
import javax.lang.model.element.Modifier;

public class Generator {

  private static final String BASE_PACKAGE = "com.rometools.rome";

  private final Model model;
  private final Path targetDirectory;

  public Generator(Model model, Path targetDirectory) {
    this.model = model;
    this.targetDirectory = targetDirectory;
  }

  public void generate() {
    generate(model.root());
  }

  private void generate(Node node) {
    if (node.entityName() != null) {
      generateEntity(node);
    }

    node.children().forEach(this::generate);
  }

  private void generateEntity(Node node) {
    Names names = Names.forNode(node);

    String packageName = BASE_PACKAGE + ".model.rss";

    ClassName className = ClassName.get(packageName, names.type());

    TypeSpec.Builder type = TypeSpec.classBuilder(className)
        .addModifiers(Modifier.PUBLIC);

    MethodSpec.Builder constructor = MethodSpec.constructorBuilder();

    for (Node child : node.children()) {
      addField(
          packageName,
          type,
          constructor,
          child);
    }

    type.addMethod(constructor.build());

    addBuilder(node, className, type);

    type.addAnnotation(AnnotationSpec.builder(Generated.class)
        .addMember("value", "$S", getClass().getName())
        .addMember("date", "$S", Instant.now().toString())
        .build());

    TypeSpec typeSpec = type.build();
    JavaFile javaFile = JavaFile.builder(packageName, typeSpec).build();

    Path actualTargetDir = targetDirectory.resolve(javaFile.packageName.replace(".", "/"));
    try {
      Files.createDirectories(actualTargetDir);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    try {
      javaFile.writeTo(targetDirectory);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  void addBuilder(Node node, ClassName className, TypeSpec.Builder type) {
    ClassName builderClassName = className.nestedClass("Builder");
    TypeSpec builder = composeBuilder(node, builderClassName);
    type.addType(builder);

    type.addMethod(MethodSpec.methodBuilder("builder")
        .returns(builderClassName)
        .addModifiers(Modifier.PUBLIC, Modifier.STATIC)
        .addStatement("return new $T()", builderClassName)
        .build());
  }

  private void addField(
      String packageName,
      TypeSpec.Builder typeBuilder,
      MethodSpec.Builder constructor,
      Node node) {
    Names names = Names.forNode(node);

    final ClassName wrapperClass;
    if (node.type() != null) {
      wrapperClass = ClassName.get(Values.wrapperClass(node.type()));
    } else {
      wrapperClass = ClassName.get(packageName, names.type());
    }

    final TypeName propertyTypeName;
    if (node.multiplicity() == Multiplicity.MANY) {
      ClassName arrayList = ClassName.get(ArrayList.class);
      propertyTypeName = ParameterizedTypeName.get(arrayList, wrapperClass);
    } else {
      propertyTypeName = wrapperClass;
    }

    constructor.addParameter(
        ParameterSpec.builder(propertyTypeName, names.classField(), Modifier.FINAL)
            .build());
    constructor.addStatement("this.$L = $L", names.classField(), names.classField());

    typeBuilder.addField(
        FieldSpec.builder(propertyTypeName, names.classField())
            .addModifiers(Modifier.PRIVATE, Modifier.FINAL)
            .build());

    typeBuilder.addMethod(
        MethodSpec.methodBuilder(names.classMethod("get"))
            .addModifiers(Modifier.PUBLIC)
            .returns(propertyTypeName)
            .addStatement("return $L", names.classField())
            .build());

    if (node.multiplicity() == Multiplicity.MANY) {
      typeBuilder.addMethod(
          MethodSpec.methodBuilder(names.singularClassMethod("getFirst"))
              .addModifiers(Modifier.PUBLIC)
              .returns(wrapperClass)
              .addComment("TODO return empty value if list is empty")
              .addStatement("return $L.get(0)", names.classField())
              .build());
    }
  }

  public TypeSpec composeBuilder(Node entity, ClassName className) {
    TypeSpec.Builder builder = TypeSpec.classBuilder("Builder")
        .addModifiers(Modifier.PUBLIC, Modifier.STATIC);

    for (Node field : entity.children()) {
      addBuilderField(className, builder, field);
    }

    MethodSpec.Builder build = MethodSpec.methodBuilder("build")
        .returns(className.enclosingClassName())
        .addModifiers(Modifier.PUBLIC);

    addBuildMethod(entity, className, build);

    builder.addMethod(build.build());

    return builder.build();
  }

  private void addBuilderField(ClassName className, TypeSpec.Builder builder, Node node) {
    Names names = Names.forNode(node);

    final ClassName rawClass;

    if (node.type() != null) {
      rawClass = ClassName.get(Values.rawClass(node.type()));
    } else {
      rawClass = ClassName.get(
          className.packageName(),
          CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, node.name()));
    }

    final FieldSpec.Builder property;
    final TypeName propertyTypeName;
    if (node.multiplicity() == Multiplicity.MANY) {
      ClassName arrayList = ClassName.get(ArrayList.class);
      propertyTypeName = ParameterizedTypeName.get(arrayList, rawClass);

      property = FieldSpec.builder(propertyTypeName, names.classField())
          .addModifiers(Modifier.PRIVATE)
          .initializer(CodeBlock.builder()
              .add("new $T()", ArrayList.class)
              .build());
    } else {
      property = FieldSpec.builder(rawClass, names.classField())
          .addModifiers(Modifier.PRIVATE);
      propertyTypeName = rawClass;
    }

    builder.addField(property.build());

    if (node.multiplicity() == Multiplicity.MANY) {
      builder.addMethod(
          MethodSpec.methodBuilder(names.singularClassMethod("add"))
              .addModifiers(Modifier.PUBLIC)
              .addParameter(
                  ParameterSpec.builder(
                      rawClass,
                      names.singular(),
                      Modifier.FINAL).build())
              .addStatement("this.$L.add($L)", names.classField(), names.singular())
              .addStatement("return this")
              .returns(className)
              .build());
    }

    builder.addMethod(
        MethodSpec.methodBuilder(names.classMethod("set"))
            .addModifiers(Modifier.PUBLIC)
            .addParameter(
                ParameterSpec.builder(
                    propertyTypeName,
                    names.classField(),
                    Modifier.FINAL).build())
            .addStatement("this.$L = $L", names.classField(), names.classField())
            .addStatement("return this")
            .returns(className)
            .build());

    if (node.multiplicity() == Multiplicity.MANY) {
      builder.addMethod(
          MethodSpec.methodBuilder(names.classMethod("clear"))
              .addModifiers(Modifier.PUBLIC)
              .addStatement("this.$L.clear()", names.classField())
              .addStatement("return this")
              .returns(className)
              .build());

    } else {
      builder.addMethod(
          MethodSpec.methodBuilder(names.singularClassMethod("clear"))
              .addModifiers(Modifier.PUBLIC)
              .addStatement("this.$L = null", names.classField())
              .addStatement("return this")
              .returns(className)
              .build());
    }
  }

  private void addBuildMethod(Node entity, ClassName className, MethodSpec.Builder builder) {
    CodeBlock.Builder body = CodeBlock.builder();

    body.add("return new $T(\n", className.enclosingClassName());
    body.indent();

    int i = 0;
    int last = entity.children().size();

    for (Node child : entity.children()) {
      Names names = Names.forNode(child);

      i++;
      String comma = (i == last) ? "" : ",";

      if (child.type() != null) {
        Class<?> wrapperClass = Values.wrapperClass(child.type());

        if (child.multiplicity() == Multiplicity.MANY) {
          body.add("$T.mapListOfNullables($L)$L\n", wrapperClass, names.classField(), comma);
        } else {
          body.add("$T.ofNullable($L)$L\n", wrapperClass, names.classField(), comma);
        }
      } else {
        body.add("$L$L\n", names.classField(), comma);
      }
    }

    body.unindent();
    body.addStatement(")");
    builder.addCode(body.build());
  }
}
