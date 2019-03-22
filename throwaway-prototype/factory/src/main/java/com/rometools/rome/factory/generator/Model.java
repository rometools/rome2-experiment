package com.rometools.rome.factory.generator;

import java.util.Set;

public class Model {

  private String name;
  private Set<Entity> entities;

  public Model(String name, Set<Entity> entities) {
    this.name = name;
    this.entities = entities;
  }

  public String getName() {
    return name;
  }

  public Set<Entity> getEntities() {
    return entities;
  }

  public static class Entity {

    private String name;
    private Set<Field> fields;

    public Entity(String name, Set<Field> fields) {
      this.name = name;
      this.fields = fields;
    }

    public String getName() {
      return name;
    }

    public Set<Field> getFields() {
      return fields;
    }
  }
}
