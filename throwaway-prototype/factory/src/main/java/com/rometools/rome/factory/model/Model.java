package com.rometools.rome.factory.model;

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

  public static class Field implements Comparable<Field> {

    private String name;
    private Class<?> type;
    private OneOrMany oneOrMany;

    private Field(String name, Class<?> type, OneOrMany oneOrMany) {
      this.name = name;
      this.type = type;
      this.oneOrMany = oneOrMany;
    }

    public static Field valueField(String name, Class<?> type, OneOrMany oneOrMany) {
      return new Field(name, type, oneOrMany);
    }

    public static Field objectField(String name, OneOrMany oneOrMany) {
      return new Field(name, null, oneOrMany);
    }

    public String getName() {
      return name;
    }

    public Class<?> getType() {
      return type;
    }

    public OneOrMany getOneOrMany() {
      return oneOrMany;
    }

    public boolean isValue() {
      return type != null;
    }

    public boolean isEntity() {
      return type == null;
    }

    @Override
    public int compareTo(Field other) {
      return this.name.compareTo(other.name);
    }
  }
}
