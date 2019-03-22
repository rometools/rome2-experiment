package com.rometools.rome.factory.generator;

import static com.google.common.base.CaseFormat.LOWER_CAMEL;
import static com.google.common.base.CaseFormat.UPPER_CAMEL;

import com.rometools.rome.common.xml.XmlPath;
import com.rometools.rome.factory.xml.DataPoint;
import com.rometools.rome.factory.xml.EntityBinding;
import com.rometools.rome.factory.xml.ModelLocation;
import com.rometools.rome.factory.xml.XmlModelDefinition;
import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import javax.annotation.Generated;
import javax.lang.model.element.Modifier;

public class ParserGenerator {

  private static final String PACKAGE = "com.rometools.rome.xml";
  private static final String MODEL_PACKAGE = "com.rometools.rome.model";

  private final XmlModelDefinition model;
  private final Path targetDirectory;

  public ParserGenerator(XmlModelDefinition model, Path targetDirectory) {
    this.model = model;
    this.targetDirectory = targetDirectory;
  }

  public void generate() {
    Map<ModelLocation, XmlPath> modelPathToXmlPath =
        model.getEntityBindings().stream()
            .collect(Collectors.toMap(EntityBinding::getModelLocation, EntityBinding::getXmlPath));

    TypeSpec.Builder type =
        TypeSpec.classBuilder(ClassName.get(PACKAGE, "GeneratedXmlBindings"))
            .addModifiers(Modifier.PUBLIC);

    type.addField(
        FieldSpec.builder(
                ParameterizedTypeName.get(
                    ClassName.get(Map.class),
                    ClassName.get(XmlPath.class),
                    ParameterizedTypeName.get(
                        ClassName.get(Supplier.class), ClassName.get(Object.class))),
                "BUILDER_MAPPING",
                Modifier.PUBLIC,
                Modifier.FINAL,
                Modifier.STATIC)
            .initializer("new $T<>()", HashMap.class)
            .build());

    type.addField(
        FieldSpec.builder(
                ParameterizedTypeName.get(
                    ClassName.get(Map.class),
                    ClassName.get(XmlPath.class),
                    ParameterizedTypeName.get(
                        ClassName.get(Function.class),
                        ClassName.get(Object.class),
                        ClassName.get(Object.class))),
                "BUILD_MAPPING",
                Modifier.PUBLIC,
                Modifier.FINAL,
                Modifier.STATIC)
            .initializer("new $T<>()", HashMap.class)
            .build());

    type.addField(
        FieldSpec.builder(
                ParameterizedTypeName.get(
                    ClassName.get(Map.class),
                    ClassName.get(XmlPath.class),
                    ClassName.get(XmlPath.class)),
                "PARENT_MAPPING",
                Modifier.PUBLIC,
                Modifier.FINAL,
                Modifier.STATIC)
            .initializer("new $T<>()", HashMap.class)
            .build());

    type.addField(
        FieldSpec.builder(
                ParameterizedTypeName.get(
                    ClassName.get(Map.class),
                    ClassName.get(XmlPath.class),
                    ParameterizedTypeName.get(
                        ClassName.get(BiFunction.class),
                        ClassName.get(Object.class),
                        ClassName.get(Object.class),
                        ClassName.get(Object.class))),
                "SETTER_MAPPING",
                Modifier.PUBLIC,
                Modifier.FINAL,
                Modifier.STATIC)
            .initializer("new $T<>()", HashMap.class)
            .build());

    CodeBlock.Builder staticBlock = CodeBlock.builder();

    for (EntityBinding entityBinding : model.getEntityBindings()) {
      staticBlock.addStatement(
          "BUILDER_MAPPING.put($T.create($S), $T::builder)",
          XmlPath.class,
          entityBinding.getXmlPath().toString(),
          ClassName.get(MODEL_PACKAGE, simpleClassName(entityBinding.getModelLocation().name())));

      staticBlock.addStatement(
          "BUILD_MAPPING.put($T.create($S), b -> (($T.Builder) b).build())",
          XmlPath.class,
          entityBinding.getXmlPath().toString(),
          ClassName.get(MODEL_PACKAGE, simpleClassName(entityBinding.getModelLocation().name())));

      if (!entityBinding.getModelLocation().isTopLevel()) {
        staticBlock.addStatement(
            "PARENT_MAPPING.put($T.create($S), $T.create($S))",
            XmlPath.class,
            entityBinding.getXmlPath().toString(),
            XmlPath.class,
            modelPathToXmlPath.get(entityBinding.getModelLocation().parent()).toString());

        staticBlock.addStatement(
            "SETTER_MAPPING.put($T.create($S), (b, v) -> (($T.Builder) b).$L(($T) v))",
            XmlPath.class,
            entityBinding.getXmlPath().toString(),
            ClassName.get(
                MODEL_PACKAGE, simpleClassName(entityBinding.getModelLocation().parent().name())),
            joinNames(
                entityBinding.getOneOrMany() == OneOrMany.ONE ? "set" : "add",
                entityBinding.getModelLocation().name()),
            ClassName.get(MODEL_PACKAGE, simpleClassName(entityBinding.getModelLocation().name())));
      }
    }

    for (DataPoint dataPoint : model.getDataPoints()) {

      staticBlock.addStatement(
          "PARENT_MAPPING.put($T.create($S), $T.create($S))",
          XmlPath.class,
          dataPoint.getXmlPath().toString(),
          XmlPath.class,
          modelPathToXmlPath.get(dataPoint.getModelLocation().parent()).toString());

      staticBlock.addStatement(
          "SETTER_MAPPING.put($T.create($S), (b, v) -> (($T.Builder) b).$L(new $T().parse(($T) v).asNullable()))",
          XmlPath.class,
          dataPoint.getXmlPath().toString(),
          ClassName.get(
              MODEL_PACKAGE, simpleClassName(dataPoint.getModelLocation().parent().name())),
          joinNames(
              dataPoint.getOneOrMany() == OneOrMany.ONE ? "set" : "add",
              dataPoint.getModelLocation().name()),
          ClassName.get(dataPoint.getParser().getClass()),
          ClassName.get(String.class));
    }

    type.addStaticBlock(staticBlock.build());

    type.addAnnotation(
        AnnotationSpec.builder(Generated.class)
            .addMember("value", "$S", getClass().getName())
            .addMember("date", "$S", Instant.now().toString())
            .build());

    TypeSpec typeSpec = type.build();
    JavaFile javaFile = JavaFile.builder(PACKAGE, typeSpec).build();

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

  private Object joinNames(String name1, String name2) {
    return name1 + LOWER_CAMEL.to(UPPER_CAMEL, name2);
  }

  private String simpleClassName(String entityName) {
    return LOWER_CAMEL.to(UPPER_CAMEL, entityName);
  }
}
