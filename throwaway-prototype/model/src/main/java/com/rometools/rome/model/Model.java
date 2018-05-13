package com.rometools.rome.model;

public class Model {

  private final Node root;

  public Model(Node root) {
    this.root = root;
  }

  public Node root() {
    return root;
  }
}
