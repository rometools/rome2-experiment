package com.rometools.rome.common.model;

import java.util.Objects;

public class ModelId<F> {

  private final String name;
  private final Class<F> feedClass;

  public ModelId(String name, Class<F> feedClass) {
    this.name = name;
    this.feedClass = feedClass;
  }

  @SuppressWarnings("unchecked")
  public F cast(Object object) {
    return (F) object;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ModelId<?> modelId = (ModelId<?>) o;
    return Objects.equals(name, modelId.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name);
  }
}
