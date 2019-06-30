package com.rometools.rome.xml;

import com.google.common.base.Functions;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Multimap;
import com.rometools.rome.common.model.ModelId;
import com.rometools.rome.common.model.ModelTarget;
import com.rometools.rome.common.xml.EntitySetup;
import com.rometools.rome.common.xml.XmlPath;
import com.rometools.rome.model.Atom;
import com.rometools.rome.model.Feed;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XmlModelBuilder {

  private static final Logger LOG = LoggerFactory.getLogger(XmlModelBuilder.class);

  private final Map<ModelTarget, EntitySetup> targetToSetup = new HashMap<>();
  private final Multimap<XmlPath, EntitySetup> pathToSetup = HashMultimap.create();
  private final Map<String, EntitySetup> topLevelEntities = new HashMap<>();

  public void startEntity(XmlPath xmlPath) {
    if (isEntity(xmlPath)) {
      Set<EntitySetup> setups =
          GeneratedXmlBindings.ENTITY_SETUP_MAPPING.get(xmlPath).stream()
              .map(Supplier::get)
              .collect(Collectors.toSet());

      targetToSetup.putAll(
          setups.stream().collect(Collectors.toMap(EntitySetup::getTarget, Functions.identity())));
      setups.forEach(setup -> pathToSetup.put(xmlPath, setup));
    }
  }

  public void endEntity(XmlPath xmlPath) {
    if (pathToSetup.containsKey(xmlPath)) {
      buildAndSetEntity(xmlPath);
      pathToSetup.removeAll(xmlPath);
    }
  }

  private boolean isEntity(XmlPath xmlPath) {
    return GeneratedXmlBindings.ENTITY_SETUP_MAPPING.containsKey(xmlPath);
  }

  private void buildAndSetEntity(XmlPath xmlPath) {
    Collection<EntitySetup> setups = pathToSetup.get(xmlPath);
    for (EntitySetup setup : setups) {
      if (setup.getTarget().getModelPath().isTopLevel()) {
        topLevelEntities.put(setup.getTarget().getModelName(), setup);
      } else {
        Object entity = setup.build();
        EntitySetup parent = targetToSetup.get(setup.getParentTarget());
        setup.set(parent, entity);
      }
    }
  }

  public void setValue(XmlPath xmlPath, Object value) {
    GeneratedXmlBindings.FIELD_SETUP_MAPPING.get(xmlPath).stream()
        .map(Supplier::get)
        .forEach(
            setup -> {
              EntitySetup parent = targetToSetup.get(setup.getParentTarget());
              setup.set(parent, value);
            });
  }

  public boolean isDataPoint(XmlPath xmlPath) {
    return GeneratedXmlBindings.FIELD_SETUP_MAPPING.containsKey(xmlPath);
  }

  public Feed build() {
    EntitySetup mainEntity = topLevelEntities.get("main");

    // TODO Make it work for any number of different models.
    if (topLevelEntities.containsKey("atom")) {
      ((Feed.Builder) mainEntity.getBuilder())
          ._setModels(
              ImmutableMap.of(
                  new ModelId<>("atom", Atom.Feed.class), topLevelEntities.get("atom").build()));
    }

    return (Feed) mainEntity.build();
  }
}
