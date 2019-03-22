package com.rometools.rome.xml;

import com.rometools.rome.common.xml.XmlPath;
import com.rometools.rome.model.Feed;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class XmlModelBuilder {

  private final Map<XmlPath, Object> ongoingBuilders = new LinkedHashMap<>();
  private Object topLevelObject;

  public void startEntity(XmlPath xmlPath) {
    if (isEntity(xmlPath)) {
      Supplier<Object> buildCreator = GeneratedXmlBindings.BUILDER_MAPPING.get(xmlPath);
      Object builder = buildCreator.get();
      ongoingBuilders.put(xmlPath, builder);
    }
  }

  public void endEntity(XmlPath xmlPath) {
    if (isEntity(xmlPath) && ongoingBuilders.containsKey(xmlPath)) {
      buildAndSetEntity(xmlPath);
    }
  }

  private boolean isEntity(XmlPath xmlPath) {
    return GeneratedXmlBindings.BUILDER_MAPPING.keySet().contains(xmlPath);
  }

  private void buildAndSetEntity(XmlPath xmlPath) {
    Object entity = buildEntity(xmlPath);
    setValue(xmlPath, entity);
  }

  private Object buildEntity(XmlPath xmlPath) {
    Object builder = ongoingBuilders.get(xmlPath);
    ongoingBuilders.remove(xmlPath);
    Function<Object, Object> buildFunction = GeneratedXmlBindings.BUILD_MAPPING.get(xmlPath);
    return buildFunction.apply(builder);
  }

  public void setValue(XmlPath xmlPath, Object value) {
    XmlPath parent = GeneratedXmlBindings.PARENT_MAPPING.get(xmlPath);
    Object parentBuilder = ongoingBuilders.get(parent);
    BiFunction<Object, Object, Object> setter = GeneratedXmlBindings.SETTER_MAPPING.get(xmlPath);
    if (setter != null) {
      setter.apply(parentBuilder, value);
    } else {
      topLevelObject = value;
    }
  }

  public boolean isDataPoint(XmlPath xmlPath) {
    return GeneratedXmlBindings.SETTER_MAPPING.keySet().contains(xmlPath)
        && !GeneratedXmlBindings.BUILDER_MAPPING.keySet().contains(xmlPath);
  }

  public Feed build() {
    return (Feed) topLevelObject;
  }
}
