package com.rometools.rome.model.rss;

import com.rometools.rome.model.value.IntValue;
import com.rometools.rome.model.value.StringValue;
import java.lang.Integer;
import java.lang.String;
import javax.annotation.Generated;

@Generated(
    value = "com.rometools.rome.model.generator.Generator",
    date = "2018-05-13T17:42:09.372Z"
)
public class Enclosure {
  private final StringValue url;

  private final IntValue length;

  private final StringValue type;

  Enclosure(final StringValue url, final IntValue length, final StringValue type) {
    this.url = url;
    this.length = length;
    this.type = type;
  }

  public StringValue getUrl() {
    return url;
  }

  public IntValue getLength() {
    return length;
  }

  public StringValue getType() {
    return type;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {
    private String url;

    private Integer length;

    private String type;

    public Builder setUrl(final String url) {
      this.url = url;
      return this;
    }

    public Builder clearUrl() {
      this.url = null;
      return this;
    }

    public Builder setLength(final Integer length) {
      this.length = length;
      return this;
    }

    public Builder clearLength() {
      this.length = null;
      return this;
    }

    public Builder setType(final String type) {
      this.type = type;
      return this;
    }

    public Builder clearType() {
      this.type = null;
      return this;
    }

    public Enclosure build() {
      return new Enclosure(
        StringValue.ofNullable(url),
        IntValue.ofNullable(length),
        StringValue.ofNullable(type)
      );
    }
  }
}
