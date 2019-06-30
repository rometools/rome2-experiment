package com.rometools.rome.model;

import com.rometools.rome.common.value.IntValue;
import com.rometools.rome.common.value.StringValue;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.util.Objects;
import javax.annotation.processing.Generated;

@Generated(
    value = "com.rometools.rome.factory.generator.ModelGenerator",
    date = "2019-06-30T10:02:08.182418Z"
)
public class Enclosure {
  private final Integer length;

  private final String type;

  private final String url;

  Enclosure(final Integer length, final String type, final String url) {
    this.length = length;
    this.type = type;
    this.url = url;
  }

  public IntValue getLength() {
    return IntValue.ofNullable(length);
  }

  public boolean hasLength() {
    return length != null;
  }

  public StringValue getType() {
    return StringValue.ofNullable(type);
  }

  public boolean hasType() {
    return type != null;
  }

  public StringValue getUrl() {
    return StringValue.ofNullable(url);
  }

  public boolean hasUrl() {
    return url != null;
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
    return Objects.equals(length, that.length)
        && Objects.equals(type, that.type)
        && Objects.equals(url, that.url);
  }

  @Override
  public String toString() {
    StringBuilder result = new StringBuilder();
    result.append("{");
    result.append("\"length\":");
    result.append(String.valueOf(length));
    result.append(",\"type\":");
    result.append("\"" + type + "\"");
    result.append(",\"url\":");
    result.append("\"" + url + "\"");
    result.append("}");
    return result.toString();
  }

  public static class Builder {
    private Integer length;

    private String type;

    private String url;

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

    public Builder setUrl(final String url) {
      this.url = url;
      return this;
    }

    public Builder clearUrl() {
      this.url = null;
      return this;
    }

    public Enclosure build() {
      return new Enclosure(
        length,
        type,
        url
      );
    }
  }
}
