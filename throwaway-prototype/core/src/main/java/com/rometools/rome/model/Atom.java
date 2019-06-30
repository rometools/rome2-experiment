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
    date = "2019-06-30T10:02:08.223901Z"
)
public class Atom {
  public static class Feed {
    private final List<Item> itemList;

    private final String title;

    private final ZonedDateTime updated;

    private final Map<ModelId, Object> _models;

    Feed(final List<Item> itemList, final String title, final ZonedDateTime updated,
        final Map<ModelId, Object> _models) {
      this.itemList = itemList;
      this.title = title;
      this.updated = updated;
      this._models = _models;
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
      return Objects.equals(itemList, that.itemList)
          && Objects.equals(title, that.title)
          && Objects.equals(updated, that.updated);
    }

    @Override
    public String toString() {
      StringBuilder result = new StringBuilder();
      result.append("{");
      result.append("\"itemList\":");
      result.append(String.valueOf(itemList));
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
      private List<Item> itemList;

      private String title;

      private ZonedDateTime updated;

      Map<ModelId, Object> _models;

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
          itemList,
          title,
          updated,
          _models
        );
      }
    }
  }

  public static class Author {
    private final String email;

    private final String name;

    private final String uri;

    Author(final String email, final String name, final String uri) {
      this.email = email;
      this.name = name;
      this.uri = uri;
    }

    public StringValue getEmail() {
      return StringValue.ofNullable(email);
    }

    public boolean hasEmail() {
      return email != null;
    }

    public StringValue getName() {
      return StringValue.ofNullable(name);
    }

    public boolean hasName() {
      return name != null;
    }

    public StringValue getUri() {
      return StringValue.ofNullable(uri);
    }

    public boolean hasUri() {
      return uri != null;
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

      Author that = (Author) other;
      return Objects.equals(email, that.email)
          && Objects.equals(name, that.name)
          && Objects.equals(uri, that.uri);
    }

    @Override
    public String toString() {
      StringBuilder result = new StringBuilder();
      result.append("{");
      result.append("\"email\":");
      result.append("\"" + email + "\"");
      result.append(",\"name\":");
      result.append("\"" + name + "\"");
      result.append(",\"uri\":");
      result.append("\"" + uri + "\"");
      result.append("}");
      return result.toString();
    }

    public static class Builder {
      private String email;

      private String name;

      private String uri;

      public Builder setEmail(final String email) {
        this.email = email;
        return this;
      }

      public Builder clearEmail() {
        this.email = null;
        return this;
      }

      public Builder setName(final String name) {
        this.name = name;
        return this;
      }

      public Builder clearName() {
        this.name = null;
        return this;
      }

      public Builder setUri(final String uri) {
        this.uri = uri;
        return this;
      }

      public Builder clearUri() {
        this.uri = null;
        return this;
      }

      public Author build() {
        return new Author(
          email,
          name,
          uri
        );
      }
    }
  }

  public static class Item {
    private final List<Author> authorList;

    private final String summary;

    private final String title;

    Item(final List<Author> authorList, final String summary, final String title) {
      this.authorList = authorList;
      this.summary = summary;
      this.title = title;
    }

    public List<Author> getAuthorList() {
      if (authorList == null) {
        return new ArrayList<>();
      }
      return authorList;
    }

    public boolean hasAuthorList() {
      return authorList != null;
    }

    public Author getFirstAuthor() {
      if (authorList == null) {
        return Author.builder().build();
      }
      return authorList.get(0);
    }

    public StringValue getSummary() {
      return StringValue.ofNullable(summary);
    }

    public boolean hasSummary() {
      return summary != null;
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
      return Objects.equals(authorList, that.authorList)
          && Objects.equals(summary, that.summary)
          && Objects.equals(title, that.title);
    }

    @Override
    public String toString() {
      StringBuilder result = new StringBuilder();
      result.append("{");
      result.append("\"authorList\":");
      result.append(String.valueOf(authorList));
      result.append(",\"summary\":");
      result.append("\"" + summary + "\"");
      result.append(",\"title\":");
      result.append("\"" + title + "\"");
      result.append("}");
      return result.toString();
    }

    public static class Builder {
      private List<Author> authorList;

      private String summary;

      private String title;

      public Builder setAuthorList(final List<Author> authorList) {
        this.authorList = authorList;
        return this;
      }

      public Builder addAuthor(final Author author) {
        if (author == null) {
          return this;
        }

        if (this.authorList == null) {
          this.authorList = new ArrayList<>();
        }

        this.authorList.add(author);
        return this;
      }

      public Builder clearAuthorList() {
        this.authorList = null;
        return this;
      }

      public Builder setSummary(final String summary) {
        this.summary = summary;
        return this;
      }

      public Builder clearSummary() {
        this.summary = null;
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
          authorList,
          summary,
          title
        );
      }
    }
  }
}
