package com.rometools.rome.factory.xml;

import com.rometools.rome.common.parser.Parser;
import com.rometools.rome.common.xml.XmlPath;
import com.rometools.rome.factory.generator.ModelPath;
import com.rometools.rome.factory.generator.OneOrMany;
import java.util.Comparator;

public class DataPoint implements Comparable<DataPoint> {

  private static final Comparator<DataPoint> COMPARATOR =
      Comparator.comparing(DataPoint::getXmlPath).thenComparing(DataPoint::getModelPath);

  private XmlPath xmlPath;
  private ModelPath modelPath;
  private Parser<?> parser;
  private OneOrMany oneOrMany;

  public DataPoint(XmlPath xmlPath, ModelPath modelPath, Parser<?> parser, OneOrMany oneOrMany) {
    this.xmlPath = xmlPath;
    this.modelPath = modelPath;
    this.parser = parser;
    this.oneOrMany = oneOrMany;
  }

  public XmlPath getXmlPath() {
    return xmlPath;
  }

  public ModelPath getModelPath() {
    return modelPath;
  }

  public Parser<?> getParser() {
    return parser;
  }

  public OneOrMany getOneOrMany() {
    return oneOrMany;
  }

  @Override
  public int compareTo(DataPoint other) {
    return COMPARATOR.compare(this, other);
  }
}
