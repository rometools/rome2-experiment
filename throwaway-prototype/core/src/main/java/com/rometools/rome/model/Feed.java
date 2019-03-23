package com.rometools.rome.model;

import com.rometools.rome.common.value.DateTimeValue;
import com.rometools.rome.common.value.StringValue;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.annotation.Generated;

@Generated(
    value = "com.rometools.rome.factory.generator.EntityGenerator",
    date = "2019-03-23T07:02:53.899Z"
)
public class Feed {
  private final String description;

  private final Image image;

  private final List<Item> itemList;

  private final ZonedDateTime published;

  private final String title;

  Feed(final String description, final Image image, final List<Item> itemList,
      final ZonedDateTime published, final String title) {
    this.description = description;
    this.image = image;
    this.itemList = itemList;
    this.published = published;
    this.title = title;
  }

  public StringValue getDescription() {
    return StringValue.ofNullable(description);
  }

  public boolean hasDescription() {
    return description != null;
  }

  public Image getImage() {
    if (image == null) {
      return Image.builder().build();
    }
    return image;
  }

  public boolean hasImage() {
    return image != null;
  }

  public List<Item> getItemList() {
    if (itemList == null) {
      return new ArrayList<>();
    }
    return itemList;
  }

  public boolean hasItemList() {
    return itemList != null;
  }

  public Item getFirstItem() {
    if (itemList == null) {
      return Item.builder().build();
    }
    return itemList.get(0);
  }

  public DateTimeValue getPublished() {
    return DateTimeValue.ofNullable(published);
  }

  public boolean hasPublished() {
    return published != null;
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

    Feed that = (Feed) other;
    return Objects.equals(description, that.description)
        && Objects.equals(image, that.image)
        && Objects.equals(itemList, that.itemList)
        && Objects.equals(published, that.published)
        && Objects.equals(title, that.title);
  }

  @Override
  public String toString() {
    StringBuilder result = new StringBuilder();
    result.append("{");
    result.append("\"description\":");
    result.append("\"" + description + "\"");
    result.append(",\"image\":");
    result.append(String.valueOf(image));
    result.append(",\"itemList\":");
    result.append(String.valueOf(itemList));
    result.append(",\"published\":");
    result.append(String.valueOf(published));
    result.append(",\"title\":");
    result.append("\"" + title + "\"");
    result.append("}");
    return result.toString();
  }

  public static class Builder {
    private String description;

    private Image image;

    private List<Item> itemList;

    private ZonedDateTime published;

    private String title;

    public Builder setDescription(final String description) {
      this.description = description;
      return this;
    }

    public Builder clearDescription() {
      this.description = null;
      return this;
    }

    public Builder setImage(final Image image) {
      this.image = image;
      return this;
    }

    public Builder clearImage() {
      this.image = null;
      return this;
    }

    public Builder setItemList(final List<Item> itemList) {
      this.itemList = itemList;
      return this;
    }

    public Builder addItem(final Item item) {
      if (item == null) {
        return this;
      }

      if (this.itemList == null) {
        this.itemList = new ArrayList<>();
      }

      this.itemList.add(item);
      return this;
    }

    public Builder clearItemList() {
      this.itemList = null;
      return this;
    }

    public Builder setPublished(final ZonedDateTime published) {
      this.published = published;
      return this;
    }

    public Builder clearPublished() {
      this.published = null;
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

    public Feed build() {
      return new Feed(
        description,
        image,
        itemList,
        published,
        title
      );
    }
  }
}
