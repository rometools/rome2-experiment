package com.rometools.rome.xml;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.rometools.rome.common.model.ModelPath;
import com.rometools.rome.common.model.ModelTarget;
import com.rometools.rome.common.parser.DateTimeParser;
import com.rometools.rome.common.parser.IntParser;
import com.rometools.rome.common.parser.StringParser;
import com.rometools.rome.common.xml.EntitySetup;
import com.rometools.rome.common.xml.FieldSetup;
import com.rometools.rome.common.xml.XmlPath;
import com.rometools.rome.model.Atom;
import com.rometools.rome.model.Enclosure;
import com.rometools.rome.model.Feed;
import com.rometools.rome.model.Image;
import com.rometools.rome.model.Item;
import java.lang.String;
import java.util.function.Supplier;
import javax.annotation.processing.Generated;

@Generated(
    value = "com.rometools.rome.factory.generator.ParserGenerator",
    date = "2019-06-30T10:02:08.268841Z"
)
public class GeneratedXmlBindings {
  public static final Multimap<XmlPath, Supplier<EntitySetup>> ENTITY_SETUP_MAPPING = HashMultimap.create();

  public static final Multimap<XmlPath, Supplier<FieldSetup>> FIELD_SETUP_MAPPING = HashMultimap.create();

  static {
    ENTITY_SETUP_MAPPING.put(
        XmlPath.create("/rss/channel/image"),
        () ->
            EntitySetup.start(
                new ModelTarget("main", ModelPath.create("/feed/image")),
                new ModelTarget("main", ModelPath.create("/feed")),
                Image::builder,
                builder -> ((Image.Builder) builder).build(),
                (b, v) -> ((Feed.Builder) b).setImage((Image) v)));

    ENTITY_SETUP_MAPPING.put(
        XmlPath.create("/rss/channel/item"),
        () ->
            EntitySetup.start(
                new ModelTarget("main", ModelPath.create("/feed/item")),
                new ModelTarget("main", ModelPath.create("/feed")),
                Item::builder,
                builder -> ((Item.Builder) builder).build(),
                (b, v) -> ((Feed.Builder) b).addItem((Item) v)));

    ENTITY_SETUP_MAPPING.put(
        XmlPath.create("/rss"),
        () ->
            EntitySetup.startTopLevel(
                new ModelTarget("main", ModelPath.create("/feed")),
                Feed::builder,
                builder -> ((Feed.Builder) builder).build()));

    ENTITY_SETUP_MAPPING.put(
        XmlPath.create("/rss/channel/item/enclosure"),
        () ->
            EntitySetup.start(
                new ModelTarget("main", ModelPath.create("/feed/item/enclosure")),
                new ModelTarget("main", ModelPath.create("/feed/item")),
                Enclosure::builder,
                builder -> ((Enclosure.Builder) builder).build(),
                (b, v) -> ((Item.Builder) b).addEnclosure((Enclosure) v)));

    FIELD_SETUP_MAPPING.put(
        XmlPath.create("/rss/channel/item/link"),
        () ->
            new FieldSetup(
                new ModelTarget("main", ModelPath.create("/feed/item/link")),
                new ModelTarget("main", ModelPath.create("/feed/item")),
                (b, v) -> ((Item.Builder) b).setLink(new StringParser().parse((String) v).value())));

    FIELD_SETUP_MAPPING.put(
        XmlPath.create("/rss/channel/item/description"),
        () ->
            new FieldSetup(
                new ModelTarget("main", ModelPath.create("/feed/item/description")),
                new ModelTarget("main", ModelPath.create("/feed/item")),
                (b, v) -> ((Item.Builder) b).setDescription(new StringParser().parse((String) v).value())));

    FIELD_SETUP_MAPPING.put(
        XmlPath.create("/rss/channel/image/url"),
        () ->
            new FieldSetup(
                new ModelTarget("main", ModelPath.create("/feed/image/url")),
                new ModelTarget("main", ModelPath.create("/feed/image")),
                (b, v) -> ((Image.Builder) b).setUrl(new StringParser().parse((String) v).value())));

    FIELD_SETUP_MAPPING.put(
        XmlPath.create("/rss/channel/item/pubDate"),
        () ->
            new FieldSetup(
                new ModelTarget("main", ModelPath.create("/feed/item/published")),
                new ModelTarget("main", ModelPath.create("/feed/item")),
                (b, v) -> ((Item.Builder) b).setPublished(new DateTimeParser().parse((String) v).value())));

    FIELD_SETUP_MAPPING.put(
        XmlPath.create("/rss/channel/title"),
        () ->
            new FieldSetup(
                new ModelTarget("main", ModelPath.create("/feed/title")),
                new ModelTarget("main", ModelPath.create("/feed")),
                (b, v) -> ((Feed.Builder) b).setTitle(new StringParser().parse((String) v).value())));

    FIELD_SETUP_MAPPING.put(
        XmlPath.create("/rss/channel/item/enclosure/@type"),
        () ->
            new FieldSetup(
                new ModelTarget("main", ModelPath.create("/feed/item/enclosure/type")),
                new ModelTarget("main", ModelPath.create("/feed/item/enclosure")),
                (b, v) -> ((Enclosure.Builder) b).setType(new StringParser().parse((String) v).value())));

    FIELD_SETUP_MAPPING.put(
        XmlPath.create("/rss/channel/item/enclosure/@url"),
        () ->
            new FieldSetup(
                new ModelTarget("main", ModelPath.create("/feed/item/enclosure/url")),
                new ModelTarget("main", ModelPath.create("/feed/item/enclosure")),
                (b, v) -> ((Enclosure.Builder) b).setUrl(new StringParser().parse((String) v).value())));

    FIELD_SETUP_MAPPING.put(
        XmlPath.create("/rss/channel/pubDate"),
        () ->
            new FieldSetup(
                new ModelTarget("main", ModelPath.create("/feed/published")),
                new ModelTarget("main", ModelPath.create("/feed")),
                (b, v) -> ((Feed.Builder) b).setPublished(new DateTimeParser().parse((String) v).value())));

    FIELD_SETUP_MAPPING.put(
        XmlPath.create("/rss/channel/description"),
        () ->
            new FieldSetup(
                new ModelTarget("main", ModelPath.create("/feed/description")),
                new ModelTarget("main", ModelPath.create("/feed")),
                (b, v) -> ((Feed.Builder) b).setDescription(new StringParser().parse((String) v).value())));

    FIELD_SETUP_MAPPING.put(
        XmlPath.create("/rss/channel/item/enclosure/@length"),
        () ->
            new FieldSetup(
                new ModelTarget("main", ModelPath.create("/feed/item/enclosure/length")),
                new ModelTarget("main", ModelPath.create("/feed/item/enclosure")),
                (b, v) -> ((Enclosure.Builder) b).setLength(new IntParser().parse((String) v).value())));

    FIELD_SETUP_MAPPING.put(
        XmlPath.create("/rss/channel/item/title"),
        () ->
            new FieldSetup(
                new ModelTarget("main", ModelPath.create("/feed/item/title")),
                new ModelTarget("main", ModelPath.create("/feed/item")),
                (b, v) -> ((Item.Builder) b).setTitle(new StringParser().parse((String) v).value())));

    FIELD_SETUP_MAPPING.put(
        XmlPath.create("/rss/channel/lastBuildDate"),
        () ->
            new FieldSetup(
                new ModelTarget("main", ModelPath.create("/feed/updated")),
                new ModelTarget("main", ModelPath.create("/feed")),
                (b, v) -> ((Feed.Builder) b).setUpdated(new DateTimeParser().parse((String) v).value())));

    ENTITY_SETUP_MAPPING.put(
        XmlPath.create("/feed/entry"),
        () ->
            EntitySetup.start(
                new ModelTarget("main", ModelPath.create("/feed/item")),
                new ModelTarget("main", ModelPath.create("/feed")),
                Item::builder,
                builder -> ((Item.Builder) builder).build(),
                (b, v) -> ((Feed.Builder) b).addItem((Item) v)));

    ENTITY_SETUP_MAPPING.put(
        XmlPath.create("/feed"),
        () ->
            EntitySetup.startTopLevel(
                new ModelTarget("main", ModelPath.create("/feed")),
                Feed::builder,
                builder -> ((Feed.Builder) builder).build()));

    FIELD_SETUP_MAPPING.put(
        XmlPath.create("/feed/entry/title"),
        () ->
            new FieldSetup(
                new ModelTarget("main", ModelPath.create("/feed/item/title")),
                new ModelTarget("main", ModelPath.create("/feed/item")),
                (b, v) -> ((Item.Builder) b).setTitle(new StringParser().parse((String) v).value())));

    FIELD_SETUP_MAPPING.put(
        XmlPath.create("/feed/title"),
        () ->
            new FieldSetup(
                new ModelTarget("main", ModelPath.create("/feed/title")),
                new ModelTarget("main", ModelPath.create("/feed")),
                (b, v) -> ((Feed.Builder) b).setTitle(new StringParser().parse((String) v).value())));

    ENTITY_SETUP_MAPPING.put(
        XmlPath.create("/feed/entry/author"),
        () ->
            EntitySetup.start(
                new ModelTarget("atom", ModelPath.create("/feed/item/author")),
                new ModelTarget("atom", ModelPath.create("/feed/item")),
                Atom.Author::builder,
                builder -> ((Atom.Author.Builder) builder).build(),
                (b, v) -> ((Atom.Item.Builder) b).addAuthor((Atom.Author) v)));

    ENTITY_SETUP_MAPPING.put(
        XmlPath.create("/feed/entry"),
        () ->
            EntitySetup.start(
                new ModelTarget("atom", ModelPath.create("/feed/item")),
                new ModelTarget("atom", ModelPath.create("/feed")),
                Atom.Item::builder,
                builder -> ((Atom.Item.Builder) builder).build(),
                (b, v) -> ((Atom.Feed.Builder) b).addItem((Atom.Item) v)));

    ENTITY_SETUP_MAPPING.put(
        XmlPath.create("/feed"),
        () ->
            EntitySetup.startTopLevel(
                new ModelTarget("atom", ModelPath.create("/feed")),
                Atom.Feed::builder,
                builder -> ((Atom.Feed.Builder) builder).build()));

    FIELD_SETUP_MAPPING.put(
        XmlPath.create("/feed/entry/title"),
        () ->
            new FieldSetup(
                new ModelTarget("atom", ModelPath.create("/feed/item/title")),
                new ModelTarget("atom", ModelPath.create("/feed/item")),
                (b, v) -> ((Atom.Item.Builder) b).setTitle(new StringParser().parse((String) v).value())));

    FIELD_SETUP_MAPPING.put(
        XmlPath.create("/feed/entry/author/uri"),
        () ->
            new FieldSetup(
                new ModelTarget("atom", ModelPath.create("/feed/item/author/uri")),
                new ModelTarget("atom", ModelPath.create("/feed/item/author")),
                (b, v) -> ((Atom.Author.Builder) b).setUri(new StringParser().parse((String) v).value())));

    FIELD_SETUP_MAPPING.put(
        XmlPath.create("/feed/entry/author/email"),
        () ->
            new FieldSetup(
                new ModelTarget("atom", ModelPath.create("/feed/item/author/email")),
                new ModelTarget("atom", ModelPath.create("/feed/item/author")),
                (b, v) -> ((Atom.Author.Builder) b).setEmail(new StringParser().parse((String) v).value())));

    FIELD_SETUP_MAPPING.put(
        XmlPath.create("/feed/updated"),
        () ->
            new FieldSetup(
                new ModelTarget("atom", ModelPath.create("/feed/updated")),
                new ModelTarget("atom", ModelPath.create("/feed")),
                (b, v) -> ((Atom.Feed.Builder) b).setUpdated(new DateTimeParser().parse((String) v).value())));

    FIELD_SETUP_MAPPING.put(
        XmlPath.create("/feed/entry/author/name"),
        () ->
            new FieldSetup(
                new ModelTarget("atom", ModelPath.create("/feed/item/author/name")),
                new ModelTarget("atom", ModelPath.create("/feed/item/author")),
                (b, v) -> ((Atom.Author.Builder) b).setName(new StringParser().parse((String) v).value())));

    FIELD_SETUP_MAPPING.put(
        XmlPath.create("/feed/entry/summary"),
        () ->
            new FieldSetup(
                new ModelTarget("atom", ModelPath.create("/feed/item/summary")),
                new ModelTarget("atom", ModelPath.create("/feed/item")),
                (b, v) -> ((Atom.Item.Builder) b).setSummary(new StringParser().parse((String) v).value())));

    FIELD_SETUP_MAPPING.put(
        XmlPath.create("/feed/title"),
        () ->
            new FieldSetup(
                new ModelTarget("atom", ModelPath.create("/feed/title")),
                new ModelTarget("atom", ModelPath.create("/feed")),
                (b, v) -> ((Atom.Feed.Builder) b).setTitle(new StringParser().parse((String) v).value())));

  }
}
