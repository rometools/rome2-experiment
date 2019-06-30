package com.rometools.rome.common.model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class ModelPath implements Comparable<ModelPath> {

  private static final Comparator<ModelPath> COMPARATOR = Comparator.comparing(ModelPath::toString);

  private final List<String> hierarchy;

  private ModelPath(List<String> hierarchy) {
    this.hierarchy = hierarchy;
  }

  public static ModelPath create(String hierarchy) {
    return new ModelPath(Arrays.asList(hierarchy.substring(1).split("/")));
  }

  public ModelPath getParent() {
    if (hierarchy.size() == 1) {
      return null;
    }

    return new ModelPath(hierarchy.subList(0, hierarchy.size() - 1));
  }

  public String name() {
    return hierarchy.get(hierarchy.size() - 1);
  }

  public boolean isTopLevel() {
    return hierarchy.size() == 1;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ModelPath modelPath = (ModelPath) o;
    return Objects.equals(hierarchy, modelPath.hierarchy);
  }

  @Override
  public int hashCode() {
    return Objects.hash(hierarchy);
  }

  @Override
  public String toString() {
    return "/" + String.join("/", hierarchy);
  }

  @Override
  public int compareTo(ModelPath other) {
    return COMPARATOR.compare(this, other);
  }
}
