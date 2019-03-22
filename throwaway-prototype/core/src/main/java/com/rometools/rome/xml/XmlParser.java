package com.rometools.rome.xml;

import com.rometools.rome.common.xml.XmlPath;
import com.rometools.rome.model.Feed;
import java.io.ByteArrayInputStream;
import java.util.Iterator;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.XMLEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XmlParser {

  private static final Logger LOG = LoggerFactory.getLogger(XmlParser.class);

  private final byte[] bytes;
  private final PositionTracker positionTracker = new PositionTracker();
  private final XmlModelBuilder xmlModelBuilder = new XmlModelBuilder();

  public XmlParser(byte[] bytes) {
    this.bytes = bytes;
  }

  public Feed parse() {
    try {
      XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
      XMLEventReader xmlEventReader =
          xmlInputFactory.createXMLEventReader(new ByteArrayInputStream(bytes));

      while (xmlEventReader.hasNext()) {
        XMLEvent xmlEvent = positionTracker.before(xmlEventReader.nextEvent());

        if (xmlEvent.isAttribute()) {
          // ATTRIBUTE events come as a part of START_ELEMENT.
          throw new IllegalStateException("Unexpected attribute event");
        }

        XmlPath currentPath = positionTracker.getPosition();

        if (xmlEvent.isStartElement()) {
          xmlModelBuilder.startEntity(currentPath);
        }

        if (xmlEvent.isEndElement()) {
          xmlModelBuilder.endEntity(currentPath);
        }

        if (xmlEvent.isStartElement() && xmlModelBuilder.isDataPoint(currentPath)) {
          xmlModelBuilder.setValue(currentPath, xmlEventReader.getElementText());

          // Emulate END_ELEMENT event because XMLEventReader::getElementText consumed it.
          positionTracker.moveUp();
        }

        if (xmlEvent.isStartElement()) {
          for (Iterator it = xmlEvent.asStartElement().getAttributes(); it.hasNext(); ) {
            Attribute attribute = (Attribute) it.next();

            XmlPath attributePath = currentPath.attribute(attribute.getName().getLocalPart());

            if (xmlModelBuilder.isDataPoint(attributePath)) {
              xmlModelBuilder.setValue(attributePath, attribute.getValue());
            }
          }
        }

        positionTracker.after(xmlEvent);
      }

      return xmlModelBuilder.build();

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public static class PositionTracker {

    private XmlPath position = XmlPath.ROOT;

    public XMLEvent before(XMLEvent event) {
      if (event.isStartElement()) {
        position = position.child(event.asStartElement().getName().getLocalPart());
      }

      return event;
    }

    public void after(XMLEvent event) {
      if (event.isEndElement()) {
        moveUp();
      }
    }

    public XmlPath getPosition() {
      return position;
    }

    public void moveUp() {
      position = position.parent();
    }
  }
}
