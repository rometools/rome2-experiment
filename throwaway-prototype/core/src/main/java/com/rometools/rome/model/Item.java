package com.rometools.rome.model;

import com.rometools.rome.common.value.StringValue;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.annotation.Generated;

@Generated(
    value = "com.rometools.rome.factory.generator.EntityGenerator",
    date = "2019-03-22T07:00:47.734Z"
)
public class Item {
  private final String description;

  private final String link;

  private final List<Enclosure> enclosureList;

  private final String title;

  Item(final String description, final String link, final List<Enclosure> enclosureList,
      final String title) {
    this.description = description;
    this.link = link;
    this.enclosureList = enclosureList;
    this.title = title;
  }

  public StringValue getDescription() {
    return StringValue.ofNullable(description);
  }

  public boolean hasDescription() {
    return description != null;
  }

  public StringValue getLink() {
    return StringValue.ofNullable(link);
  }

  public boolean hasLink() {
    return link != null;
  }

  public List<Enclosure> getEnclosureList() {
    if (enclosureList == null) {
      return new ArrayList<>();
    }
    return enclosureList;
  }

  public boolean hasEnclosureList() {
    return enclosureList != null;
  }

  public Enclosure getFirstEnclosure() {
    if (enclosureList == null) {
      return Enclosure.builder().build();
    }
    return enclosureList.get(0);
  }

  public StringValue getTitle() {
    return StringValue.ofNullable(title);
  }

  public boolean hasTitle() {
    return title != null;
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

    Item that = (Item) other;
    return Objects.equals(description, that.description)
        && Objects.equals(link, that.link)
        && Objects.equals(enclosureList, that.enclosureList)
        && Objects.equals(title, that.title);
  }

  @Override
  public String toString() {
    StringBuilder result = new StringBuilder();
    result.append("{");
    result.append("\"description\":");
    result.append("\"" + description + "\"");
    result.append(",\"link\":");
    result.append("\"" + link + "\"");
    result.append(",\"enclosureList\":");
    result.append(String.valueOf(enclosureList));
    result.append(",\"title\":");
    result.append("\"" + title + "\"");
    result.append("}");
    return result.toString();
  }

  public static class Builder {
    private String description;

    private String link;

    private List<Enclosure> enclosureList;

    private String title;

    public Builder setDescription(final String description) {
      this.description = description;
      return this;
    }

    public Builder clearDescription() {
      this.description = null;
      return this;
    }

    public Builder setLink(final String link) {
      this.link = link;
      return this;
    }

    public Builder clearLink() {
      this.link = null;
      return this;
    }

    public Builder setEnclosureList(final List<Enclosure> enclosureList) {
      this.enclosureList = enclosureList;
      return this;
    }

    public Builder addEnclosure(final Enclosure enclosure) {
      if (enclosure == null) {
        return this;
      }

      if (this.enclosureList == null) {
        this.enclosureList = new ArrayList<>();
      }

      this.enclosureList.add(enclosure);
      return this;
    }

    public Builder clearEnclosureList() {
      this.enclosureList = null;
      return this;
    }

    public Builder setTitle(final String title) {
      this.title = title;
      return this;
    }

    public Builder clearTitle() {
      this.title = null;
      return this;
    }

    public Item build() {
      return new Item(
        description,
        link,
        enclosureList,
        title
      );
    }
  }
}
