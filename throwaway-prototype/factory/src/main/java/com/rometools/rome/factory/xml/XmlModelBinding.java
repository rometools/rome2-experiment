package com.rometools.rome.factory.xml;

import com.rometools.rome.common.xml.XmlPath;
import com.rometools.rome.common.model.ModelPath;
import com.rometools.rome.factory.model.OneOrMany;
import java.util.Comparator;

public class XmlModelBinding implements Comparable<XmlModelBinding> {

  private static final Comparator<XmlModelBinding> COMPARATOR =
      Comparator.comparing(XmlModelBinding::getXmlPath).thenComparing(XmlModelBinding::getModelPath);

  private final OneOrMany oneOrMany;
  private XmlPath xmlPath;
  private ModelPath modelPath;

  public XmlModelBinding(XmlPath xmlPath, ModelPath modelPath, OneOrMany oneOrMany) {
    this.xmlPath = xmlPath;
    this.modelPath = modelPath;
    this.oneOrMany = oneOrMany;
  }

  public XmlPath getXmlPath() {
    return xmlPath;
  }

  public ModelPath getModelPath() {
    return modelPath;
  }

  public OneOrMany getOneOrMany() {
    return oneOrMany;
  }

  @Override
  public int compareTo(XmlModelBinding other) {
    return COMPARATOR.compare(this, other);
  }
}
