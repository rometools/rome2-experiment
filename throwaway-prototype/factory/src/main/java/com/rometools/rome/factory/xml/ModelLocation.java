package com.rometools.rome.factory.xml;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ModelLocation {

  public List<String> hierarchy;

  private ModelLocation(List<String> hierarchy) {
    this.hierarchy = hierarchy;
  }

  public static ModelLocation create(String hierarchy) {
    return new ModelLocation(Arrays.asList(hierarchy.substring(1).split("/")));
  }

  public ModelLocation parent() {
    if (hierarchy.size() == 1) {
      return null;
    }

    return new ModelLocation(hierarchy.subList(0, hierarchy.size() - 1));
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
    ModelLocation modelLocation = (ModelLocation) o;
    return Objects.equals(hierarchy, modelLocation.hierarchy);
  }

  @Override
  public int hashCode() {
    return Objects.hash(hierarchy);
  }

  @Override
  public String toString() {
    return "/" + String.join("/", hierarchy);
  }
}
