package com.rometools.rome.factory.xml;

import com.rometools.rome.common.xml.XmlPath;
import com.rometools.rome.factory.generator.ModelPath;
import com.rometools.rome.factory.generator.OneOrMany;
import java.util.Comparator;

public class EntityBinding implements Comparable<EntityBinding> {

  private static final Comparator<EntityBinding> COMPARATOR =
      Comparator.comparing(EntityBinding::getXmlPath).thenComparing(EntityBinding::getModelPath);

  private final OneOrMany oneOrMany;
  private XmlPath xmlPath;
  private ModelPath modelPath;

  public EntityBinding(XmlPath xmlPath, ModelPath modelPath, OneOrMany oneOrMany) {
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
  public int compareTo(EntityBinding other) {
    return COMPARATOR.compare(this, other);
  }
}
