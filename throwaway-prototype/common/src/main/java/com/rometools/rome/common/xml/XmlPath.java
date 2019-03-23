package com.rometools.rome.common.xml;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class XmlPath implements Comparable<XmlPath> {

  private static final Comparator<XmlPath> COMPARATOR = Comparator.comparing(XmlPath::toString);

  public static final XmlPath ROOT = new XmlPath(Collections.emptyList(), false);

  private List<String> hierarchy;
  private boolean isAttribute;

  public XmlPath(List<String> hierarchy, boolean isAttribute) {
    this.hierarchy = hierarchy;
    this.isAttribute = isAttribute;
  }

  public static XmlPath create(String value) {
    List<String> hierarchy = Arrays.asList(value.substring(1).split("/"));

    String name = hierarchy.get(hierarchy.size() - 1);

    boolean isAttribute = false;

    if (name.startsWith("@")) {
      hierarchy.set(hierarchy.size() - 1, name.replaceFirst("@", ""));
      isAttribute = true;
    }

    return new XmlPath(hierarchy, isAttribute);
  }

  public boolean isAttribute() {
    return isAttribute;
  }

  public String getName() {
    return hierarchy.get(hierarchy.size() - 1);
  }

  private XmlPath child(String name, boolean childIsAttribute) {
    if (isAttribute) {
      throw new IllegalStateException();
    }

    List<String> newHierarchy = new ArrayList<>(hierarchy);
    newHierarchy.add(name);
    return new XmlPath(newHierarchy, childIsAttribute);
  }

  public XmlPath child(String name) {
    return child(name, false);
  }

  public XmlPath attribute(String name) {
    return child(name, true);
  }

  public XmlPath parent() {
    return new XmlPath(hierarchy.subList(0, hierarchy.size() - 1), false);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    XmlPath that = (XmlPath) o;
    return isAttribute == that.isAttribute && Objects.equals(hierarchy, that.hierarchy);
  }

  @Override
  public int hashCode() {
    return Objects.hash(hierarchy, isAttribute);
  }

  @Override
  public String toString() {
    if (isAttribute) {
      return "/" + String.join("/", hierarchy.subList(0, hierarchy.size() - 1)) + "/@" + getName();
    } else {
      return "/" + String.join("/", hierarchy);
    }
  }

  @Override
  public int compareTo(XmlPath other) {
    return COMPARATOR.compare(this, other);
  }
}
