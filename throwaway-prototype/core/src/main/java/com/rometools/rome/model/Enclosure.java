package com.rometools.rome.model;

import com.rometools.rome.common.value.IntValue;
import com.rometools.rome.common.value.StringValue;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.util.Objects;
import javax.annotation.Generated;

@Generated(
    value = "com.rometools.rome.factory.generator.EntityGenerator",
    date = "2019-03-22T07:00:47.743Z"
)
public class Enclosure {
  private final String url;

  private final String type;

  private final Integer length;

  Enclosure(final String url, final String type, final Integer length) {
    this.url = url;
    this.type = type;
    this.length = length;
  }

  public StringValue getUrl() {
    return StringValue.ofNullable(url);
  }

  public boolean hasUrl() {
    return url != null;
  }

  public StringValue getType() {
    return StringValue.ofNullable(type);
  }

  public boolean hasType() {
    return type != null;
  }

  public IntValue getLength() {
    return IntValue.ofNullable(length);
  }

  public boolean hasLength() {
    return length != null;
  }

  public static Builder builder() {
    return new Builder();
  }

  @Override
  public boolean equals(final Object other) {
    if (this == other) {
      return true;
    }

    if (other == null || getClass() != other.getClass()) {
      return false;
    }

    Enclosure that = (Enclosure) other;
    return Objects.equals(url, that.url)
        && Objects.equals(type, that.type)
        && Objects.equals(length, that.length);
  }

  @Override
  public String toString() {
    StringBuilder result = new StringBuilder();
    result.append("{");
    result.append("\"url\":");
    result.append("\"" + url + "\"");
    result.append(",\"type\":");
    result.append("\"" + type + "\"");
    result.append(",\"length\":");
    result.append(String.valueOf(length));
    result.append("}");
    return result.toString();
  }

  public static class Builder {
    private String url;

    private String type;

    private Integer length;

    public Builder setUrl(final String url) {
      this.url = url;
      return this;
    }

    public Builder clearUrl() {
      this.url = null;
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

    public Builder setLength(final Integer length) {
      this.length = length;
      return this;
    }

    public Builder clearLength() {
      this.length = null;
      return this;
    }

    public Enclosure build() {
      return new Enclosure(
        url,
        type,
        length
      );
    }
  }
}
