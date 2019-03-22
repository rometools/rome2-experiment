package com.rometools.rome.factory.xml;

import com.rometools.rome.common.xml.XmlPath;
import com.rometools.rome.factory.generator.OneOrMany;

public class EntityBinding {

  private final OneOrMany oneOrMany;
  private XmlPath xmlPath;
  private ModelLocation modelLocation;

  public EntityBinding(XmlPath xmlPath, ModelLocation modelLocation, OneOrMany oneOrMany) {
    this.xmlPath = xmlPath;
    this.modelLocation = modelLocation;
    this.oneOrMany = oneOrMany;
  }

  public XmlPath getXmlPath() {
    return xmlPath;
  }

  public ModelLocation getModelLocation() {
    return modelLocation;
  }

  public OneOrMany getOneOrMany() {
    return oneOrMany;
  }
}
