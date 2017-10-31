package com.rometools.rome;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Xml {

  public static Element element(String name) {
    return new Element(name);
  }

  public static class Element {

    private final String name;

    private List<Element> children = Collections.emptyList();
    private ValueType valueType;
    private boolean many = false;
    private EntityType entityType;

    public Element(String name) {
      this.name = name;
    }

    public Element children(Element... children) {
      this.children = Arrays.asList(children);
      return this;
    }

    public Element type(ValueType valueType) {
      this.valueType = valueType;
      return this;
    }

    public Element many() {
      this.many = true;
      return this;
    }

    public Element entity(EntityType entityType) {
      this.entityType = entityType;
      return this;
    }

    public String name() {
      return name;
    }

    public List<Element> children() {
      return children;
    }

    public ValueType valueType() {
      return valueType;
    }

    public boolean isMany() {
      return many;
    }

    public EntityType entityType() {
      return entityType;
    }
  }

}
