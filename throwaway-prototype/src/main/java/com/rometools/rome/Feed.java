package com.rometools.rome;

import java.util.ArrayList;
import java.util.List;

// TODO Automatically generate code for entities
public class Feed {

  private final StringValue title;
  private final List<Item> items;

  Feed(StringValue title, List<Item> items) {
    this.title = title;
    this.items = items;
  }

  public StringValue title() {
    return title;
  }

  public List<Item> items() {
    return items;
  }

  public static Feed.Builder builder() {
    return new Builder();
  }

  public static class Builder {

    private String title;
    private List<Item> items = new ArrayList<>();

    public Builder title(String title) {
      this.title = title;
      return this;
    }

    public Builder item(Item item) {
      this.items.add(item);
      return this;
    }

    public Feed build() {
      return new Feed(
          new StringValue(title, null, null),
          this.items);
    }
  }
}
