package com.rometools.rome.factory.schema;

import com.rometools.rome.common.parser.IntParser;
import com.rometools.rome.common.parser.StringParser;
import com.rometools.rome.factory.generator.OneOrMany;
import com.rometools.rome.factory.xml.XmlModelDefinition;

public class Rss {

  public static XmlModelDefinition model() {
    return XmlModelDefinition.builder("rss")
        .addDataPoint("/rss/channel/title", "/feed/title", new StringParser(), OneOrMany.ONE)
        .addDataPoint(
            "/rss/channel/description", "/feed/description", new StringParser(), OneOrMany.ONE)
        .addDataPoint(
            "/rss/channel/image/url", "/feed/image/url", new StringParser(), OneOrMany.ONE)
        .addDataPoint(
            "/rss/channel/item/title", "/feed/item/title", new StringParser(), OneOrMany.ONE)
        .addDataPoint(
            "/rss/channel/item/description",
            "/feed/item/description",
            new StringParser(),
            OneOrMany.ONE)
        .addDataPoint(
            "/rss/channel/item/link", "/feed/item/link", new StringParser(), OneOrMany.ONE)
        .addDataPoint(
            "/rss/channel/item/enclosure/@url",
            "/feed/item/enclosure/url",
            new StringParser(),
            OneOrMany.ONE)
        .addDataPoint(
            "/rss/channel/item/enclosure/@length",
            "/feed/item/enclosure/length",
            new IntParser(),
            OneOrMany.ONE)
        .addDataPoint(
            "/rss/channel/item/enclosure/@type",
            "/feed/item/enclosure/type",
            new StringParser(),
            OneOrMany.ONE)
        .addEntityBinding("/rss", "/feed", OneOrMany.ONE)
        .addEntityBinding("/rss/channel/image", "/feed/image", OneOrMany.ONE)
        .addEntityBinding("/rss/channel/item", "/feed/item", OneOrMany.MANY)
        .addEntityBinding("/rss/channel/item/enclosure", "/feed/item/enclosure", OneOrMany.MANY)
        .build();
  }
}
