package com.rometools.rome.factory.xml.schema;

import static com.rometools.rome.common.parser.Parser.DATETIME;
import static com.rometools.rome.common.parser.Parser.STRING;
import static com.rometools.rome.factory.model.OneOrMany.MANY;
import static com.rometools.rome.factory.model.OneOrMany.ONE;

import com.google.common.collect.ImmutableSet;
import com.rometools.rome.factory.xml.XmlSchema;

public class Atom {

  public static ImmutableSet<XmlSchema> schemas() {
    return ImmutableSet.of(
        XmlSchema.forModel("main")
            .dataPoint("/feed/title", "/feed/title", ONE, STRING)
            .dataPoint("/feed/entry/title", "/feed/item/title", ONE, STRING)
            .entityBinding("/feed", "/feed", ONE)
            .entityBinding("/feed/entry", "/feed/item", MANY)
            .build(),
        XmlSchema.forModel("atom")
            .dataPoint("/feed/title", "/feed/title", ONE, STRING)
            .dataPoint("/feed/updated", "/feed/updated", ONE, DATETIME)
            // TODO See below.
            //            .dataPoint("/feed/author/name", "/feed/author/name", ONE, STRING)
            //            .dataPoint("/feed/author/uri", "/feed/author/uri", ONE, STRING)
            //            .dataPoint("/feed/author/email", "/feed/author/email", ONE, STRING)
            .dataPoint("/feed/entry/title", "/feed/item/title", ONE, STRING)
            .dataPoint("/feed/entry/summary", "/feed/item/summary", ONE, STRING)
            .dataPoint("/feed/entry/author/name", "/feed/item/author/name", ONE, STRING)
            .dataPoint("/feed/entry/author/uri", "/feed/item/author/uri", ONE, STRING)
            .dataPoint("/feed/entry/author/email", "/feed/item/author/email", ONE, STRING)
            .entityBinding("/feed", "/feed", ONE)
            // TODO Somehow specify that feed author and item author are the same entity.
            //            .entityBinding("/feed/author", "/feed/author", MANY)
            .entityBinding("/feed/entry", "/feed/item", MANY)
            .entityBinding("/feed/entry/author", "/feed/item/author", MANY)
            .build());
  }
}
