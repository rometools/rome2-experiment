package com.rometools.rome.factory.xml;

import com.rometools.rome.common.parser.Parser;
import com.rometools.rome.common.xml.XmlPath;
import com.rometools.rome.factory.generator.OneOrMany;

public class DataPoint {

  private XmlPath xmlPath;
  private ModelLocation modelLocation;
  private Parser<?> parser;
  private OneOrMany oneOrMany;

  public DataPoint(
      XmlPath xmlPath, ModelLocation modelLocation, Parser<?> parser, OneOrMany oneOrMany) {
    this.xmlPath = xmlPath;
    this.modelLocation = modelLocation;
    this.parser = parser;
    this.oneOrMany = oneOrMany;
  }

  public XmlPath getXmlPath() {
    return xmlPath;
  }

  public ModelLocation getModelLocation() {
    return modelLocation;
  }

  public Parser<?> getParser() {
    return parser;
  }

  public OneOrMany getOneOrMany() {
    return oneOrMany;
  }
}
