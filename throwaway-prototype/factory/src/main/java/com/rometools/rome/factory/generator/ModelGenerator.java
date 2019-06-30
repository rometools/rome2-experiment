package com.rometools.rome.factory.generator;

import static com.google.common.base.CaseFormat.LOWER_CAMEL;
import static com.google.common.base.CaseFormat.UPPER_CAMEL;
import static com.rometools.rome.factory.model.Models.DEFAULT_MODEL_PACKAGE;

import com.rometools.rome.factory.model.Model;
import com.rometools.rome.factory.model.Models;
import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import javax.annotation.processing.Generated;
import javax.lang.model.element.Modifier;

public class ModelGenerator {

  private final Model model;
  private final Path targetDirectory;
  private TypeSpec.Builder containerBuilder;

  public ModelGenerator(Model model, Path targetDirectory) {
    System.out.println(model.getName());
    for (Model.Entity entity : model.getEntities()) {
      System.out.println(entity.getName());
    }
    this.model = model;
    this.targetDirectory = targetDirectory;
  }

  public void generate() {
    if (!"main".equals(model.getName())) {
      ClassName containerClass =
          ClassName.get(DEFAULT_MODEL_PACKAGE, LOWER_CAMEL.to(UPPER_CAMEL, model.getName()));
      containerBuilder = TypeSpec.classBuilder(containerClass).addModifiers(Modifier.PUBLIC);
      containerBuilder.addAnnotation(
          AnnotationSpec.builder(Generated.class)
              .addMember("value", "$S", getClass().getName())
              .addMember("date", "$S", Instant.now().toString())
              .build());
    }

    for (Model.Entity entity : model.getEntities()) {
      generateEntity(entity);
    }

    if (containerBuilder != null) {
      writeFile(containerBuilder.build(), DEFAULT_MODEL_PACKAGE);
    }
  }

  private void generateEntity(Model.Entity entity) {
    ClassName entityClassName = Models.getEntityClassName(model.getName(), entity.getName());

    TypeSpec.Builder generatedClass =
        new EntityGenerator(
                this, model, entity, entityClassName, !"main".equals(model.getName()))
            .generate();

    if (!"main".equals(model.getName())) {
      containerBuilder.addType(generatedClass.addModifiers(Modifier.STATIC).build());
    } else {
      generatedClass.addAnnotation(
          AnnotationSpec.builder(Generated.class)
              .addMember("value", "$S", getClass().getName())
              .addMember("date", "$S", Instant.now().toString())
              .build());

      writeFile(generatedClass.build(), DEFAULT_MODEL_PACKAGE);
    }
  }

  private void writeFile(TypeSpec generatedClass, String packageName) {
    JavaFile javaFile = JavaFile.builder(packageName, generatedClass).build();

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
