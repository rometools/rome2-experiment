package com.rometools.rome.xml;

import com.rometools.rome.common.parser.DateTimeParser;
import com.rometools.rome.common.parser.IntParser;
import com.rometools.rome.common.parser.StringParser;
import com.rometools.rome.common.xml.XmlPath;
import com.rometools.rome.model.Enclosure;
import com.rometools.rome.model.Feed;
import com.rometools.rome.model.Image;
import com.rometools.rome.model.Item;
import java.lang.Object;
import java.lang.String;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.annotation.Generated;

@Generated(
    value = "com.rometools.rome.factory.generator.ParserGenerator",
    date = "2019-03-23T07:02:53.961Z"
)
public class GeneratedXmlBindings {
  public static final Map<XmlPath, Supplier<Object>> BUILDER_MAPPING = new HashMap<>();

  public static final Map<XmlPath, Function<Object, Object>> BUILD_MAPPING = new HashMap<>();

  public static final Map<XmlPath, XmlPath> PARENT_MAPPING = new HashMap<>();

  public static final Map<XmlPath, BiFunction<Object, Object, Object>> SETTER_MAPPING = new HashMap<>();

  static {
    BUILDER_MAPPING.put(XmlPath.create("/rss"), Feed::builder);
    BUILD_MAPPING.put(XmlPath.create("/rss"), b -> ((Feed.Builder) b).build());
    BUILDER_MAPPING.put(XmlPath.create("/rss/channel/image"), Image::builder);
    BUILD_MAPPING.put(XmlPath.create("/rss/channel/image"), b -> ((Image.Builder) b).build());
    PARENT_MAPPING.put(XmlPath.create("/rss/channel/image"), XmlPath.create("/rss"));
    SETTER_MAPPING.put(XmlPath.create("/rss/channel/image"), (b, v) -> ((Feed.Builder) b).setImage((Image) v));
    BUILDER_MAPPING.put(XmlPath.create("/rss/channel/item"), Item::builder);
    BUILD_MAPPING.put(XmlPath.create("/rss/channel/item"), b -> ((Item.Builder) b).build());
    PARENT_MAPPING.put(XmlPath.create("/rss/channel/item"), XmlPath.create("/rss"));
    SETTER_MAPPING.put(XmlPath.create("/rss/channel/item"), (b, v) -> ((Feed.Builder) b).addItem((Item) v));
    BUILDER_MAPPING.put(XmlPath.create("/rss/channel/item/enclosure"), Enclosure::builder);
    BUILD_MAPPING.put(XmlPath.create("/rss/channel/item/enclosure"), b -> ((Enclosure.Builder) b).build());
    PARENT_MAPPING.put(XmlPath.create("/rss/channel/item/enclosure"), XmlPath.create("/rss/channel/item"));
    SETTER_MAPPING.put(XmlPath.create("/rss/channel/item/enclosure"), (b, v) -> ((Item.Builder) b).addEnclosure((Enclosure) v));
    PARENT_MAPPING.put(XmlPath.create("/rss/channel/description"), XmlPath.create("/rss"));
    SETTER_MAPPING.put(XmlPath.create("/rss/channel/description"), (b, v) -> ((Feed.Builder) b).setDescription(new StringParser().parse((String) v).asNullable()));
    PARENT_MAPPING.put(XmlPath.create("/rss/channel/image/url"), XmlPath.create("/rss/channel/image"));
    SETTER_MAPPING.put(XmlPath.create("/rss/channel/image/url"), (b, v) -> ((Image.Builder) b).setUrl(new StringParser().parse((String) v).asNullable()));
    PARENT_MAPPING.put(XmlPath.create("/rss/channel/item/description"), XmlPath.create("/rss/channel/item"));
    SETTER_MAPPING.put(XmlPath.create("/rss/channel/item/description"), (b, v) -> ((Item.Builder) b).setDescription(new StringParser().parse((String) v).asNullable()));
    PARENT_MAPPING.put(XmlPath.create("/rss/channel/item/enclosure/@length"), XmlPath.create("/rss/channel/item/enclosure"));
    SETTER_MAPPING.put(XmlPath.create("/rss/channel/item/enclosure/@length"), (b, v) -> ((Enclosure.Builder) b).setLength(new IntParser().parse((String) v).asNullable()));
    PARENT_MAPPING.put(XmlPath.create("/rss/channel/item/enclosure/@type"), XmlPath.create("/rss/channel/item/enclosure"));
    SETTER_MAPPING.put(XmlPath.create("/rss/channel/item/enclosure/@type"), (b, v) -> ((Enclosure.Builder) b).setType(new StringParser().parse((String) v).asNullable()));
    PARENT_MAPPING.put(XmlPath.create("/rss/channel/item/enclosure/@url"), XmlPath.create("/rss/channel/item/enclosure"));
    SETTER_MAPPING.put(XmlPath.create("/rss/channel/item/enclosure/@url"), (b, v) -> ((Enclosure.Builder) b).setUrl(new StringParser().parse((String) v).asNullable()));
    PARENT_MAPPING.put(XmlPath.create("/rss/channel/item/link"), XmlPath.create("/rss/channel/item"));
    SETTER_MAPPING.put(XmlPath.create("/rss/channel/item/link"), (b, v) -> ((Item.Builder) b).setLink(new StringParser().parse((String) v).asNullable()));
    PARENT_MAPPING.put(XmlPath.create("/rss/channel/item/pubDate"), XmlPath.create("/rss/channel/item"));
    SETTER_MAPPING.put(XmlPath.create("/rss/channel/item/pubDate"), (b, v) -> ((Item.Builder) b).setPublished(new DateTimeParser().parse((String) v).asNullable()));
    PARENT_MAPPING.put(XmlPath.create("/rss/channel/item/title"), XmlPath.create("/rss/channel/item"));
    SETTER_MAPPING.put(XmlPath.create("/rss/channel/item/title"), (b, v) -> ((Item.Builder) b).setTitle(new StringParser().parse((String) v).asNullable()));
    PARENT_MAPPING.put(XmlPath.create("/rss/channel/pubDate"), XmlPath.create("/rss"));
    SETTER_MAPPING.put(XmlPath.create("/rss/channel/pubDate"), (b, v) -> ((Feed.Builder) b).setPublished(new DateTimeParser().parse((String) v).asNullable()));
    PARENT_MAPPING.put(XmlPath.create("/rss/channel/title"), XmlPath.create("/rss"));
    SETTER_MAPPING.put(XmlPath.create("/rss/channel/title"), (b, v) -> ((Feed.Builder) b).setTitle(new StringParser().parse((String) v).asNullable()));
  }
}
