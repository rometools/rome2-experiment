package com.rometools.rome.factory.generator;

import static com.google.common.base.CaseFormat.LOWER_CAMEL;
import static com.google.common.base.CaseFormat.UPPER_CAMEL;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ModelGenerator {

  public static final String PACKAGE = "com.rometools.rome.model";

  private final Model model;
  private final Path targetDirectory;

  public ModelGenerator(Model model, Path targetDirectory) {
    this.model = model;
    this.targetDirectory = targetDirectory;
  }

  public void generate() {
    for (Model.Entity entity : model.getEntities()) {
      generateEntity(entity);
    }
  }

  private void generateEntity(Model.Entity entity) {
    ClassName entityClassName = getEntityClassName(entity.getName());

    TypeSpec generatedClass = new EntityGenerator(this, entity, entityClassName).generate();

    JavaFile javaFile = JavaFile.builder(PACKAGE, generatedClass).build();

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

  public ClassName getEntityClassName(String entityName) {
    return ClassName.get(PACKAGE, LOWER_CAMEL.to(UPPER_CAMEL, entityName));
  }
}
