package com.rometools.rome;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Reader {

  private final Schema schema;

  public Reader(Schema schema) {
    this.schema = schema;
  }

  public Feed read(byte[] source) {
    try {
      SAXParserFactory factory = SAXParserFactory.newInstance();
      factory.setNamespaceAware(true);
      SAXParser parser = factory.newSAXParser();

      Handler handler = new Handler(compile(schema));
      parser.parse(new ByteArrayInputStream(source), handler);

      return handler.result();

    } catch (Exception e) {
      throw new RomeException("Failed to parse xml", e);
    }
  }

  // TODO Automatically generate parsing code
  static class Handler extends DefaultHandler {

    private final Map<List<String>, Xml.Element> schema;

    private final Map<Xml.Element, String> result = new HashMap<>();

    private final LinkedList<EntityType> level = new LinkedList<>();
    private final LinkedList<String> location = new LinkedList<>();
    private final StringBuilder characters = new StringBuilder();

    private Feed.Builder feedBuilder;
    private Item.Builder itemBuilder;

    public Handler(Map<List<String>, Xml.Element> schema) {
      this.schema = schema;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)
        throws SAXException {
      location.addLast(localName);

      Xml.Element element = schema.get(location);
      if (element != null && element.entityType() == EntityType.FEED) {
        feedBuilder = Feed.builder();
        level.addLast(EntityType.FEED);
      }
      if (element != null && element.entityType() == EntityType.ITEM) {
        itemBuilder = Item.builder();
        level.addLast(EntityType.ITEM);
      }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
      Xml.Element element = schema.get(location);
      if (element != null) {
        if (element.entityType() == EntityType.FEED) {
          level.removeLast();
        } else if (element.entityType() == EntityType.ITEM) {
          feedBuilder.item(itemBuilder.build());
          level.removeLast();
        } else if (element.name().equals("title")) {
          if (level.getLast() == EntityType.FEED) {
            feedBuilder.title(characters.toString().trim());
          } else if (level.getLast() == EntityType.ITEM) {
            itemBuilder.title(characters.toString().trim());
          }
        }
      }

      characters.setLength(0);
      location.removeLast();
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
      characters.append(ch, start, length);
    }

    public Feed result() {
      return feedBuilder.build();
    }
  }

  static Map<List<String>, Xml.Element> compile(Schema schema) {
    Map<List<String>, Xml.Element> result = new HashMap<>();
    compile(Collections.emptyList(), schema.tree(), result);
    return result;
  }

  static void compile(
      List<String> location,
      Xml.Element element,
      Map<List<String>, Xml.Element> result) {
    List<String> newLocation = new ArrayList<>(location);
    newLocation.add(element.name());
    result.put(newLocation, element);
    for (Xml.Element child : element.children()) {
      compile(newLocation, child, result);
    }
  }
}
