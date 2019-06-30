package com.rometools.rome.factory.xml;

import com.rometools.rome.common.parser.Parser;
import com.rometools.rome.common.xml.XmlPath;
import com.rometools.rome.common.model.ModelPath;
import com.rometools.rome.factory.model.OneOrMany;
import java.util.HashSet;
import java.util.Set;

public class XmlSchema {

  private final String targetModel;
  private final Set<XmlDataPoint> dataPoints;
  private final Set<XmlModelBinding> entityBindings;

  public XmlSchema(
      String targetModel, Set<XmlDataPoint> dataPoints, Set<XmlModelBinding> entityBindings) {
    this.targetModel = targetModel;
    this.dataPoints = dataPoints;
    this.entityBindings = entityBindings;
  }

  public static Builder forModel(String name) {
    return new Builder(name);
  }

  public String getTargetModel() {
    return targetModel;
  }

  public Set<XmlDataPoint> getDataPoints() {
    return dataPoints;
  }

  public Set<XmlModelBinding> getEntityBindings() {
    return entityBindings;
  }

  public static class Builder {
    private final String targetModel;

    private final Set<XmlDataPoint> dataPoints = new HashSet<>();
    private final Set<XmlModelBinding> entityBindings = new HashSet<>();

    public Builder(String targetModel) {
      this.targetModel = targetModel;
    }

    public Builder dataPoint(
        String xmlPath, String modelLocation, OneOrMany oneOrMany, Parser<?> parser) {
      dataPoints.add(
          new XmlDataPoint(
              new XmlModelBinding(
                  XmlPath.create(xmlPath), ModelPath.create(modelLocation), oneOrMany),
              parser));
      return this;
    }

    public Builder entityBinding(String xmlPath, String modelLocation, OneOrMany oneOrMany) {
      entityBindings.add(
          new XmlModelBinding(XmlPath.create(xmlPath), ModelPath.create(modelLocation), oneOrMany));
      return this;
    }

    public XmlSchema build() {
      return new XmlSchema(targetModel, dataPoints, entityBindings);
    }
  }
}
