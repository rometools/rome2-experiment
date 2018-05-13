package com.rometools.rome.model.generator;

import static com.google.common.base.CaseFormat.LOWER_CAMEL;
import static com.google.common.base.CaseFormat.UPPER_CAMEL;

import com.rometools.rome.model.Node;
import com.rometools.rome.model.xml.jaxb.Multiplicity;

public class Names {

  private final String singularName;
  private final Multiplicity multiplicity;

  public Names(String singularName, Multiplicity multiplicity) {
    this.singularName = singularName;
    this.multiplicity = multiplicity;
  }

  public static Names forNode(Node node) {
    String name = (node.entityName() != null)
                  ? node.entityName()
                  : node.name();
    return new Names(name, node.multiplicity());
  }

  public String type() {
    return LOWER_CAMEL.to(UPPER_CAMEL, singularName);
  }

  public String singular() {
    return singularName;
  }

  public String singularClassMethod(String prefix) {
    return prefix + LOWER_CAMEL.to(UPPER_CAMEL, singularName);
  }

  public String classMethod(String prefix) {
    return prefix + LOWER_CAMEL.to(UPPER_CAMEL, classField());
  }

  public String classField() {
    return multiplicity == Multiplicity.MANY
           ? singularName + "List"
           : singularName;
  }
}
