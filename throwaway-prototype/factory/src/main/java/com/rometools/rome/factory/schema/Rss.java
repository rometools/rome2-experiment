package com.rometools.rome.factory.schema;

import com.rometools.rome.common.parser.DateTimeParser;
import com.rometools.rome.common.parser.IntParser;
import com.rometools.rome.common.parser.StringParser;
import com.rometools.rome.factory.generator.OneOrMany;
import com.rometools.rome.factory.xml.XmlModelDefinition;

public class Rss {

  public static XmlModelDefinition model() {
    return XmlModelDefinition.builder("rss")
        .addDataPoint("/rss/channel/title", "/feed/title", StringParser.INSTANCE, OneOrMany.ONE)
        .addDataPoint(
            "/rss/channel/description", "/feed/description", StringParser.INSTANCE, OneOrMany.ONE)
        .addDataPoint(
            "/rss/channel/pubDate", "/feed/published", DateTimeParser.INSTANCE, OneOrMany.ONE)
        .addDataPoint(
            "/rss/channel/image/url", "/feed/image/url", StringParser.INSTANCE, OneOrMany.ONE)
        .addDataPoint(
            "/rss/channel/item/title", "/feed/item/title", StringParser.INSTANCE, OneOrMany.ONE)
        .addDataPoint(
            "/rss/channel/item/description",
            "/feed/item/description",
            StringParser.INSTANCE,
            OneOrMany.ONE)
        .addDataPoint(
            "/rss/channel/item/pubDate",
            "/feed/item/published",
            DateTimeParser.INSTANCE,
            OneOrMany.ONE)
        .addDataPoint(
            "/rss/channel/item/link", "/feed/item/link", StringParser.INSTANCE, OneOrMany.ONE)
        .addDataPoint(
            "/rss/channel/item/enclosure/@url",
            "/feed/item/enclosure/url",
            StringParser.INSTANCE,
            OneOrMany.ONE)
        .addDataPoint(
            "/rss/channel/item/enclosure/@length",
            "/feed/item/enclosure/length",
            IntParser.INSTANCE,
            OneOrMany.ONE)
        .addDataPoint(
            "/rss/channel/item/enclosure/@type",
            "/feed/item/enclosure/type",
            StringParser.INSTANCE,
            OneOrMany.ONE)
        .addEntityBinding("/rss", "/feed", OneOrMany.ONE)
        .addEntityBinding("/rss/channel/image", "/feed/image", OneOrMany.ONE)
        .addEntityBinding("/rss/channel/item", "/feed/item", OneOrMany.MANY)
        .addEntityBinding("/rss/channel/item/enclosure", "/feed/item/enclosure", OneOrMany.MANY)
        .build();
  }
}
