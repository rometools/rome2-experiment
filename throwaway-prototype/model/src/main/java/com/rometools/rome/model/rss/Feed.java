package com.rometools.rome.model.rss;

import com.rometools.rome.model.value.StringValue;
import java.lang.String;
import java.util.ArrayList;
import javax.annotation.Generated;

@Generated(
    value = "com.rometools.rome.model.generator.Generator",
    date = "2018-05-13T17:42:09.331Z"
)
public class Feed {
  private final StringValue title;

  private final ArrayList<Item> itemList;

  Feed(final StringValue title, final ArrayList<Item> itemList) {
    this.title = title;
    this.itemList = itemList;
  }

  public StringValue getTitle() {
    return title;
  }

  public ArrayList<Item> getItemList() {
    return itemList;
  }

  public Item getFirstItem() {
    // TODO return empty value if list is empty
    return itemList.get(0);
  }

  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {
    private String title;

    private ArrayList<Item> itemList = new ArrayList();

    public Builder setTitle(final String title) {
      this.title = title;
      return this;
    }

    public Builder clearTitle() {
      this.title = null;
      return this;
    }

    public Builder addItem(final Item item) {
      this.itemList.add(item);
      return this;
    }

    public Builder setItemList(final ArrayList<Item> itemList) {
      this.itemList = itemList;
      return this;
    }

    public Builder clearItemList() {
      this.itemList.clear();
      return this;
    }

    public Feed build() {
      return new Feed(
        StringValue.ofNullable(title),
        itemList
      );
    }
  }
}
