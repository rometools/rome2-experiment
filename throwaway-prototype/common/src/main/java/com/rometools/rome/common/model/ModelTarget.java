package com.rometools.rome.common.model;

import java.util.Objects;

public class ModelTarget {

  private final String modelName;
  private final ModelPath modelPath;

  public ModelTarget(String modelName, ModelPath modelPath) {
    this.modelName = modelName;
    this.modelPath = modelPath;
  }

  public String getModelName() {
    return modelName;
  }

  public ModelPath getModelPath() {
    return modelPath;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ModelTarget that = (ModelTarget) o;
    return Objects.equals(modelName, that.modelName) && Objects.equals(modelPath, that.modelPath);
  }

  @Override
  public int hashCode() {
    return Objects.hash(modelName, modelPath);
  }

  @Override
  public String toString() {
    return modelName + "/" + modelPath;
  }
}
