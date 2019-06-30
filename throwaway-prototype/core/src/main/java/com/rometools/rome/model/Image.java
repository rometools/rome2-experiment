package com.rometools.rome.model;

import com.rometools.rome.common.value.StringValue;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.util.Objects;
import javax.annotation.processing.Generated;

@Generated(
    value = "com.rometools.rome.factory.generator.ModelGenerator",
    date = "2019-06-30T10:02:08.161760Z"
)
public class Image {
  private final String url;

  Image(final String url) {
    this.url = url;
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

    Image that = (Image) other;
    return Objects.equals(url, that.url);
  }

  @Override
  public String toString() {
    StringBuilder result = new StringBuilder();
    result.append("{");
    result.append("\"url\":");
    result.append("\"" + url + "\"");
    result.append("}");
    return result.toString();
  }

  public static class Builder {
    private String url;

    public Builder setUrl(final String url) {
      this.url = url;
      return this;
    }

    public Builder clearUrl() {
      this.url = null;
      return this;
    }

    public Image build() {
      return new Image(
        url
      );
    }
  }
}
