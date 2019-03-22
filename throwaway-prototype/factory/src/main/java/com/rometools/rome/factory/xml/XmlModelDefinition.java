package com.rometools.rome.factory.xml;

import com.rometools.rome.common.parser.Parser;
import com.rometools.rome.common.xml.XmlPath;
import com.rometools.rome.factory.generator.OneOrMany;
import java.util.HashSet;
import java.util.Set;

public class XmlModelDefinition {

  private final String name;
  private final Set<DataPoint> dataPoints;
  private final Set<EntityBinding> entityBindings;

  public XmlModelDefinition(
      String name, Set<DataPoint> dataPoints, Set<EntityBinding> entityBindings) {
    this.name = name;
    this.dataPoints = dataPoints;
    this.entityBindings = entityBindings;
  }

  public static Builder builder(String name) {
    return new Builder(name);
  }

  public String getName() {
    return name;
  }

  public Set<DataPoint> getDataPoints() {
    return dataPoints;
  }

  public Set<EntityBinding> getEntityBindings() {
    return entityBindings;
  }

  public static class Builder {
    private final String name;

    private final Set<DataPoint> dataPoints = new HashSet<>();
    private final Set<EntityBinding> entityBindings = new HashSet<>();

    public Builder(String name) {
      this.name = name;
    }

    public Builder addDataPoint(
        String xmlPath, String modelLocation, Parser<?> parser, OneOrMany oneOrMany) {
      dataPoints.add(
          new DataPoint(
              XmlPath.create(xmlPath), ModelLocation.create(modelLocation), parser, oneOrMany));
      return this;
    }

    public Builder addEntityBinding(String xmlPath, String modelLocation, OneOrMany oneOrMany) {
      entityBindings.add(
          new EntityBinding(
              XmlPath.create(xmlPath), ModelLocation.create(modelLocation), oneOrMany));
      return this;
    }

    public XmlModelDefinition build() {
      return new XmlModelDefinition(name, dataPoints, entityBindings);
    }
  }
}
