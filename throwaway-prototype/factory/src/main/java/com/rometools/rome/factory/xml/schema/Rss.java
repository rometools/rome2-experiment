package com.rometools.rome.factory.xml.schema;

import static com.rometools.rome.common.parser.Parser.DATETIME;
import static com.rometools.rome.common.parser.Parser.INT;
import static com.rometools.rome.common.parser.Parser.STRING;
import static com.rometools.rome.factory.model.OneOrMany.MANY;
import static com.rometools.rome.factory.model.OneOrMany.ONE;

import com.google.common.collect.ImmutableSet;
import com.rometools.rome.factory.xml.XmlSchema;

public class Rss {

  public static ImmutableSet<XmlSchema> schemas() {
    return ImmutableSet.of(
        XmlSchema.forModel("main")
            .dataPoint("/rss/channel/title", "/feed/title", ONE, STRING)
            .dataPoint("/rss/channel/description", "/feed/description", ONE, STRING)
            .dataPoint("/rss/channel/pubDate", "/feed/published", ONE, DATETIME)
            .dataPoint("/rss/channel/lastBuildDate", "/feed/updated", ONE, DATETIME)
            .dataPoint("/rss/channel/image/url", "/feed/image/url", ONE, STRING)
            .dataPoint("/rss/channel/item/title", "/feed/item/title", ONE, STRING)
            .dataPoint("/rss/channel/item/description", "/feed/item/description", ONE, STRING)
            .dataPoint("/rss/channel/item/pubDate", "/feed/item/published", ONE, DATETIME)
            .dataPoint("/rss/channel/item/link", "/feed/item/link", ONE, STRING)
            .dataPoint("/rss/channel/item/enclosure/@url", "/feed/item/enclosure/url", ONE, STRING)
            .dataPoint(
                "/rss/channel/item/enclosure/@length", "/feed/item/enclosure/length", ONE, INT)
            .dataPoint(
                "/rss/channel/item/enclosure/@type", "/feed/item/enclosure/type", ONE, STRING)
            .entityBinding("/rss", "/feed", ONE)
            .entityBinding("/rss/channel/image", "/feed/image", ONE)
            .entityBinding("/rss/channel/item", "/feed/item", MANY)
            .entityBinding("/rss/channel/item/enclosure", "/feed/item/enclosure", MANY)
            .build());
  }
}
