package com.rometools.rome.factory.generator;

import static com.rometools.rome.factory.model.OneOrMany.MANY;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.rometools.rome.common.model.ModelPath;
import com.rometools.rome.common.model.ModelTarget;
import com.rometools.rome.common.xml.EntitySetup;
import com.rometools.rome.common.xml.FieldSetup;
import com.rometools.rome.common.xml.XmlPath;
import com.rometools.rome.factory.model.Models;
import com.rometools.rome.factory.xml.XmlDataPoint;
import com.rometools.rome.factory.xml.XmlModelBinding;
import com.rometools.rome.factory.xml.XmlSchema;
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
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import javax.annotation.processing.Generated;
import javax.lang.model.element.Modifier;

public class ParserGenerator {

  private static final String TARGET_PACKAGE = "com.rometools.rome.xml";

  private final Set<XmlSchema> schemas;
  private final Path targetDirectory;

  public ParserGenerator(Set<XmlSchema> schemas, Path targetDirectory) {
    this.schemas = schemas;
    this.targetDirectory = targetDirectory;
  }

  public void generate() {
    TypeSpec.Builder type =
        TypeSpec.classBuilder(ClassName.get(TARGET_PACKAGE, "GeneratedXmlBindings"))
            .addModifiers(Modifier.PUBLIC);

    type.addField(
        FieldSpec.builder(
                ParameterizedTypeName.get(
                    ClassName.get(Multimap.class),
                    ClassName.get(XmlPath.class),
                    ParameterizedTypeName.get(
                        ClassName.get(Supplier.class), ClassName.get(EntitySetup.class))),
                "ENTITY_SETUP_MAPPING",
                Modifier.PUBLIC,
                Modifier.FINAL,
                Modifier.STATIC)
            .initializer("$T.create()", HashMultimap.class)
            .build());

    type.addField(
        FieldSpec.builder(
                ParameterizedTypeName.get(
                    ClassName.get(Multimap.class),
                    ClassName.get(XmlPath.class),
                    ParameterizedTypeName.get(
                        ClassName.get(Supplier.class), ClassName.get(FieldSetup.class))),
                "FIELD_SETUP_MAPPING",
                Modifier.PUBLIC,
                Modifier.FINAL,
                Modifier.STATIC)
            .initializer("$T.create()", HashMultimap.class)
            .build());

    CodeBlock.Builder staticBlock = CodeBlock.builder();

    TreeSet<XmlModelBinding> xmlModelBindings =
        schemas.stream()
            .map(XmlSchema::getEntityBindings)
            .flatMap(Collection::stream)
            .collect(Collectors.toCollection(TreeSet::new));

    for (XmlSchema schema : schemas) {

      for (XmlModelBinding entityBinding : schema.getEntityBindings()) {

        CodeBlock.Builder entitySetup = CodeBlock.builder();

        entitySetup
            .add("ENTITY_SETUP_MAPPING.put(\n")
            .indent()
            .indent()
            .add("$T.create($S),\n", XmlPath.class, entityBinding.getXmlPath().toString())
            .add("() ->\n")
            .indent()
            .indent();

        if (entityBinding.getModelPath().isTopLevel()) {
          entitySetup.add("$T.startTopLevel(\n", EntitySetup.class);
        } else {
          entitySetup.add("$T.start(\n", EntitySetup.class);
        }

        entitySetup.indent().indent();

        entitySetup.add(
            "new $T($S, $T.create($S)),\n",
            ModelTarget.class,
            schema.getTargetModel(),
            ModelPath.class,
            entityBinding.getModelPath().toString());

        if (!entityBinding.getModelPath().isTopLevel()) {
          entitySetup.add(
              "new $T($S, $T.create($S)),\n",
              ModelTarget.class,
              schema.getTargetModel(),
              ModelPath.class,
              entityBinding.getModelPath().getParent().toString());
        }

        entitySetup.add(
            "$T::builder,\n",
            Models.getEntityClassName(
                schema.getTargetModel(), entityBinding.getModelPath().name()));

        entitySetup.add(
            "builder -> (($T.Builder) builder).build()",
            Models.getEntityClassName(
                schema.getTargetModel(), entityBinding.getModelPath().name()));

        if (!entityBinding.getModelPath().isTopLevel()) {
          entitySetup.add(
              ",\n(b, v) -> (($T.Builder) b).$L$L(($T) v)",
              Models.getEntityClassName(
                  schema.getTargetModel(), entityBinding.getModelPath().getParent().name()),
              entityBinding.getOneOrMany() == MANY ? "add" : "set",
              Models.getEntityClassName(
                      schema.getTargetModel(), entityBinding.getModelPath().name())
                  .simpleName(),
              Models.getEntityClassName(
                  schema.getTargetModel(), entityBinding.getModelPath().name()));
        }

        entitySetup.add("));\n\n");
        entitySetup.unindent().unindent().unindent().unindent().unindent().unindent();

        staticBlock.add(entitySetup.build());
      }

      for (XmlDataPoint dataPoint : schema.getDataPoints()) {

        CodeBlock.Builder fieldSetup = CodeBlock.builder();

        fieldSetup
            .add("FIELD_SETUP_MAPPING.put(\n")
            .indent()
            .indent()
            .add(
                "$T.create($S),\n",
                XmlPath.class,
                dataPoint.getXmlModelBinding().getXmlPath().toString())
            .add("() ->\n")
            .indent()
            .indent();

        fieldSetup.add("new $T(\n", FieldSetup.class);

        fieldSetup.indent().indent();

        fieldSetup.add(
            "new $T($S, $T.create($S)),\n",
            ModelTarget.class,
            schema.getTargetModel(),
            ModelPath.class,
            dataPoint.getXmlModelBinding().getModelPath().toString());

        fieldSetup.add(
            "new $T($S, $T.create($S)),\n",
            ModelTarget.class,
            schema.getTargetModel(),
            ModelPath.class,
            dataPoint.getXmlModelBinding().getModelPath().getParent().toString());

        fieldSetup.add(
            "(b, v) -> (($T.Builder) b).$L$L(new $T().parse(($T) v).value())));\n\n",
            Models.getEntityClassName(
                schema.getTargetModel(),
                dataPoint.getXmlModelBinding().getModelPath().getParent().name()),
            dataPoint.getXmlModelBinding().getOneOrMany() == MANY ? "add" : "set",
            Models.getEntityClassName(
                    schema.getTargetModel(), dataPoint.getXmlModelBinding().getModelPath().name())
                .simpleName(),
            dataPoint.getParser().getClass(),
            String.class);

        fieldSetup.unindent().unindent().unindent().unindent().unindent().unindent();

        staticBlock.add(fieldSetup.build());
      }
    }
    type.addStaticBlock(staticBlock.build());

    type.addAnnotation(
        AnnotationSpec.builder(Generated.class)
            .addMember("value", "$S", getClass().getName())
            .addMember("date", "$S", Instant.now().toString())
            .build());

    TypeSpec typeSpec = type.build();
    JavaFile javaFile = JavaFile.builder(TARGET_PACKAGE, typeSpec).build();

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
}
