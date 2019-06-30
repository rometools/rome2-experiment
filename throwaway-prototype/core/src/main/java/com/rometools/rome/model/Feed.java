package com.rometools.rome.model;

import com.rometools.rome.common.model.ModelId;
import com.rometools.rome.common.value.DateTimeValue;
import com.rometools.rome.common.value.StringValue;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.annotation.processing.Generated;

@Generated(
    value = "com.rometools.rome.factory.generator.ModelGenerator",
    date = "2019-06-30T10:02:08.210572Z"
)
public class Feed {
  private final String description;

  private final Image image;

  private final List<Item> itemList;

  private final ZonedDateTime published;

  private final String title;

  private final ZonedDateTime updated;

  private final Map<ModelId, Object> _models;

  Feed(final String description, final Image image, final List<Item> itemList,
      final ZonedDateTime published, final String title, final ZonedDateTime updated,
      final Map<ModelId, Object> _models) {
    this.description = description;
    this.image = image;
    this.itemList = itemList;
    this.published = published;
    this.title = title;
    this.updated = updated;
    this._models = _models;
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

  public DateTimeValue getUpdated() {
    return DateTimeValue.ofNullable(updated);
  }

  public boolean hasUpdated() {
    return updated != null;
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
        && Objects.equals(title, that.title)
        && Objects.equals(updated, that.updated);
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
    result.append(",\"updated\":");
    result.append(String.valueOf(updated));
    result.append("}");
    return result.toString();
  }

  public <T> T as(ModelId<T> modelId) {
    return modelId.cast(_models.get(modelId));
  }

  public static class Builder {
    private String description;

    private Image image;

    private List<Item> itemList;

    private ZonedDateTime published;

    private String title;

    private ZonedDateTime updated;

    Map<ModelId, Object> _models;

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

    public Builder setUpdated(final ZonedDateTime updated) {
      this.updated = updated;
      return this;
    }

    public Builder clearUpdated() {
      this.updated = null;
      return this;
    }

    public void _setModels(Map<ModelId, Object> _models) {
      this._models = _models;
    }

    public Feed build() {
      return new Feed(
        description,
        image,
        itemList,
        published,
        title,
        updated,
        _models
      );
    }
  }
}
