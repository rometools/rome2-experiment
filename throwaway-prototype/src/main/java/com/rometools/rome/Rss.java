package com.rometools.rome;

import static com.rometools.rome.Xml.element;

public class Rss implements Schema {

  public Xml.Element tree() {
    return element("rss").children(
        element("channel")
            .entity(EntityType.FEED)
            .children(
                element("title").type(ValueType.STRING),
                element("item")
                    .entity(EntityType.ITEM)
                    .children(
                        element("title").type(ValueType.STRING),
                        element("pubDate").type(ValueType.DATETIME)
                    )));
  }

}
