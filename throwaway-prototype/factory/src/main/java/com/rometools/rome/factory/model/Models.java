package com.rometools.rome.factory.model;

import static com.google.common.base.CaseFormat.LOWER_CAMEL;
import static com.google.common.base.CaseFormat.UPPER_CAMEL;

import com.squareup.javapoet.ClassName;

public class Models {

  public static final String DEFAULT_MODEL_PACKAGE = "com.rometools.rome.model";

  public static ClassName getEntityClassName(String modelName, String entityName) {
    if (!"main".equals(modelName)) {
      return ClassName.get(
          DEFAULT_MODEL_PACKAGE,
          LOWER_CAMEL.to(UPPER_CAMEL, modelName),
          LOWER_CAMEL.to(UPPER_CAMEL, entityName));
    }
    return ClassName.get(DEFAULT_MODEL_PACKAGE, LOWER_CAMEL.to(UPPER_CAMEL, entityName));
  }
}
