package com.rometools.rome.factory.model;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.rometools.rome.common.model.ModelPath;
import com.rometools.rome.factory.xml.XmlDataPoint;
import com.rometools.rome.factory.xml.XmlModelBinding;
import com.rometools.rome.factory.xml.XmlSchema;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ModelCompiler {

  public static Set<Model> compile(Set<XmlSchema> schemas) {
    Multimap<ModelPath, XmlModelBinding> parentToChildren = HashMultimap.create();

    schemas.stream()
        .map(XmlSchema::getEntityBindings)
        .flatMap(Collection::stream)
        .forEach(
            modelBinding -> {
              if (!modelBinding.getModelPath().isTopLevel()) {
                parentToChildren.put(modelBinding.getModelPath().getParent(), modelBinding);
              }
            });

    Multimap<ModelPath, XmlDataPoint> parentToDatapoints = HashMultimap.create();

    schemas.stream()
        .map(XmlSchema::getDataPoints)
        .flatMap(Collection::stream)
        .forEach(
            xmlDataPoint -> {
              parentToDatapoints.put(
                  xmlDataPoint.getXmlModelBinding().getModelPath().getParent(), xmlDataPoint);
            });

    // For each path, which models does it belong to.
    Multimap<ModelPath, String> pathToModelNames = HashMultimap.create();

    for (XmlSchema schema : schemas) {
      for (XmlDataPoint dataPoint : schema.getDataPoints()) {
        pathToModelNames.put(
            dataPoint.getXmlModelBinding().getModelPath(), schema.getTargetModel());
      }
      for (XmlModelBinding dataPoint : schema.getEntityBindings()) {
        pathToModelNames.put(dataPoint.getModelPath(), schema.getTargetModel());
      }
    }

    // All paths that have children.
    Set<ModelPath> parents = new HashSet<>();
    parents.addAll(parentToChildren.keySet());
    parents.addAll(parentToDatapoints.keySet());

    // Per model, mapping between paths and their children.
    Map<String, HashMultimap<ModelPath, Model.Field>> modelNameToParentsToFields = new HashMap<>();

    for (ModelPath parentModelPath : parents) {
      if (parentToDatapoints.containsKey(parentModelPath)) {
        for (XmlDataPoint xmlDataPoint : parentToDatapoints.get(parentModelPath)) {
          Model.Field field =
              Model.Field.valueField(
                  xmlDataPoint.getXmlModelBinding().getModelPath().name(),
                  xmlDataPoint.getParser().getResultClass(),
                  xmlDataPoint.getXmlModelBinding().getOneOrMany());

          Collection<String> modelNames =
              pathToModelNames.get(xmlDataPoint.getXmlModelBinding().getModelPath());

          for (String modelName : modelNames) {
            if (!modelNameToParentsToFields.containsKey(modelName)) {
              modelNameToParentsToFields.put(modelName, HashMultimap.create());
            }
            modelNameToParentsToFields.get(modelName).put(parentModelPath, field);
          }
        }
      }

      if (parentToChildren.containsKey(parentModelPath)) {
        for (XmlModelBinding modelBinding : parentToChildren.get(parentModelPath)) {
          Model.Field field =
              Model.Field.objectField(modelBinding.getModelPath().name(), modelBinding.getOneOrMany());

          Collection<String> modelNames = pathToModelNames.get(modelBinding.getModelPath());

          for (String modelName : modelNames) {
            if (!modelNameToParentsToFields.containsKey(modelName)) {
              modelNameToParentsToFields.put(modelName, HashMultimap.create());
            }
            modelNameToParentsToFields.get(modelName).put(parentModelPath, field);
          }
        }
      }
    }

    // Compiled models.
    Set<Model> models = new HashSet<>();

    for (String modelName : modelNameToParentsToFields.keySet()) {
      HashMultimap<ModelPath, Model.Field> parentToFields = modelNameToParentsToFields.get(modelName);
      Set<Model.Entity> entities = new HashSet<>();
      for (ModelPath parentModelPath : parentToFields.keySet()) {
        entities.add(new Model.Entity(parentModelPath.name(), parentToFields.get(parentModelPath)));
      }

      models.add(new Model(modelName, entities));
    }

    return models;
  }
}
