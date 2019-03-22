package com.rometools.rome.factory.generator;

import com.rometools.rome.factory.xml.DataPoint;
import com.rometools.rome.factory.xml.EntityBinding;
import com.rometools.rome.factory.xml.ModelLocation;
import com.rometools.rome.factory.xml.XmlModelDefinition;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ModelMerger {

  public static Model merge(XmlModelDefinition... models) {
    XmlModelDefinition model = models[0]; // Only one for now.

    Map<ModelLocation, Set<EntityBinding>> parentToChildren = new HashMap<>();

    model
        .getEntityBindings()
        .forEach(
            entityBinding -> {
              ModelLocation parent = entityBinding.getModelLocation().parent();
              if (parent != null) {
                if (!parentToChildren.containsKey(parent)) {
                  parentToChildren.put(parent, new HashSet<>());
                }
                parentToChildren.get(parent).add(entityBinding);
              }
            });

    Map<ModelLocation, Set<DataPoint>> parentToDatapoints = new HashMap<>();

    model
        .getDataPoints()
        .forEach(
            dataPoint -> {
              ModelLocation parent = dataPoint.getModelLocation().parent();
              if (!parentToDatapoints.containsKey(parent)) {
                parentToDatapoints.put(parent, new HashSet<>());
              }
              parentToDatapoints.get(parent).add(dataPoint);
            });

    Set<Model.Entity> result = new HashSet<>();

    Set<ModelLocation> entityLocations = new HashSet<>();
    entityLocations.addAll(parentToChildren.keySet());
    entityLocations.addAll(parentToDatapoints.keySet());

    for (ModelLocation entityLocation : entityLocations) {
      Set<Field> fields = new HashSet<>();

      if (parentToDatapoints.containsKey(entityLocation)) {
        for (DataPoint dataPoint : parentToDatapoints.get(entityLocation)) {
          fields.add(
              Field.valueField(
                  dataPoint.getModelLocation().name(),
                  dataPoint.getParser().getResultClass(),
                  dataPoint.getOneOrMany()));
        }
      }

      if (parentToChildren.containsKey(entityLocation)) {
        for (EntityBinding entityBinding : parentToChildren.get(entityLocation)) {
          fields.add(
              Field.objectField(
                  entityBinding.getModelLocation().name(), entityBinding.getOneOrMany()));
        }
      }

      result.add(new Model.Entity(entityLocation.name(), fields));
    }

    return new Model(model.getName(), result);
  }
}
