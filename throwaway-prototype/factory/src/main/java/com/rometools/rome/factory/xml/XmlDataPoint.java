package com.rometools.rome.factory.xml;

import com.rometools.rome.common.parser.Parser;
import java.util.Comparator;

public class XmlDataPoint implements Comparable<XmlDataPoint> {

  private static final Comparator<XmlDataPoint> COMPARATOR =
      Comparator.comparing(XmlDataPoint::getXmlModelBinding);

  private XmlModelBinding xmlModelBinding;
  private Parser<?> parser;

  public XmlDataPoint(XmlModelBinding xmlModelBinding, Parser<?> parser) {
    this.xmlModelBinding = xmlModelBinding;
    this.parser = parser;
  }

  public XmlModelBinding getXmlModelBinding() {
    return xmlModelBinding;
  }

  public Parser<?> getParser() {
    return parser;
  }

  @Override
  public int compareTo(XmlDataPoint other) {
    return COMPARATOR.compare(this, other);
  }
}
