package com.rometools.rome.model.rss;

import com.rometools.rome.model.value.DateTimeValue;
import com.rometools.rome.model.value.StringValue;
import java.lang.String;
import java.time.ZonedDateTime;
import javax.annotation.Generated;

@Generated(
    value = "com.rometools.rome.model.generator.Generator",
    date = "2018-05-13T17:42:09.362Z"
)
public class Item {
  private final StringValue title;

  private final DateTimeValue pubDate;

  private final Enclosure enclosure;

  Item(final StringValue title, final DateTimeValue pubDate, final Enclosure enclosure) {
    this.title = title;
    this.pubDate = pubDate;
    this.enclosure = enclosure;
  }

  public StringValue getTitle() {
    return title;
  }

  public DateTimeValue getPubDate() {
    return pubDate;
  }

  public Enclosure getEnclosure() {
    return enclosure;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {
    private String title;

    private ZonedDateTime pubDate;

    private Enclosure enclosure;

    public Builder setTitle(final String title) {
      this.title = title;
      return this;
    }

    public Builder clearTitle() {
      this.title = null;
      return this;
    }

    public Builder setPubDate(final ZonedDateTime pubDate) {
      this.pubDate = pubDate;
      return this;
    }

    public Builder clearPubDate() {
      this.pubDate = null;
      return this;
    }

    public Builder setEnclosure(final Enclosure enclosure) {
      this.enclosure = enclosure;
      return this;
    }

    public Builder clearEnclosure() {
      this.enclosure = null;
      return this;
    }

    public Item build() {
      return new Item(
        StringValue.ofNullable(title),
        DateTimeValue.ofNullable(pubDate),
        enclosure
      );
    }
  }
}
