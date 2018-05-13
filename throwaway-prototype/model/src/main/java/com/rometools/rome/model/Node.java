package com.rometools.rome.model;

import com.rometools.rome.model.xml.jaxb.Multiplicity;
import com.rometools.rome.model.xml.jaxb.Type;
import java.util.List;

public class Node {

  private final String name;
  private final Multiplicity multiplicity;
  private final Type type;
  private final String entityName;
  private final List<Node> children;

  public Node(
      String name,
      Multiplicity multiplicity,
      Type type,
      String entityName, List<Node> children) {
    this.name = name;
    this.multiplicity = multiplicity;
    this.type = type;
    this.entityName = entityName;
    this.children = children;
  }

  public String name() {
    return name;
  }

  public Multiplicity multiplicity() {
    return multiplicity;
  }

  public Type type() {
    return type;
  }

  public String entityName() {
    return entityName;
  }

  public List<Node> children() {
    return children;
  }
}
