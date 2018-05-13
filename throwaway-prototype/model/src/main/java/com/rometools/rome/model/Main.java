package com.rometools.rome.model;

import com.google.common.io.Resources;
import com.rometools.rome.model.generator.Generator;
import com.rometools.rome.model.xml.jaxb.Attribute;
import com.rometools.rome.model.xml.jaxb.Element;
import com.rometools.rome.model.xml.jaxb.Model;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

public class Main {

  public static void main(String[] args) throws Exception {
    Path baseDirectory = Paths.get(args[0]);
    Path targetDirectory = baseDirectory.resolve("model/src/main/java");

    Model xmlModel = loadXmlModel("rss.xml");

    com.rometools.rome.model.Model model =
        new com.rometools.rome.model.Model(toNode(xmlModel.getElement()));

    new Generator(model, targetDirectory).generate();
  }

  private static Node toNode(Element element) {
    ArrayList<Node> children = new ArrayList<>();

    element.getElements()
        .stream()
        .map(Main::toNode)
        .forEach(children::add);

    element.getAttributes()
        .stream()
        .map(Main::toNode)
        .forEach(children::add);

    return new Node(
        element.getName(),
        element.getMultiplicity(),
        element.getType(),
        element.getEntity(),
        children);
  }

  private static Node toNode(Attribute attribute) {
    return new Node(
        attribute.getName(),
        attribute.getMultiplicity(),
        attribute.getType(),
        null,
        Collections.emptyList());
  }

  private static Model loadXmlModel(String resourceName) throws Exception {
    JAXBContext context = JAXBContext.newInstance(Model.class);
    Unmarshaller unmarshaller = context.createUnmarshaller();
    StreamSource source = new StreamSource(Resources.getResource(resourceName).openStream());
    return unmarshaller.unmarshal(source, Model.class).getValue();
  }
}
