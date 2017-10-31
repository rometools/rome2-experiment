package com.rometools.rome;

// TODO Automatically generate code for entities
public class Item {

  private final StringValue title;

  Item(StringValue title) {
    this.title = title;
  }

  public StringValue title() {
    return title;
  }

  public static Item.Builder builder() {
    return new Item.Builder();
  }

  public static class Builder {

    private String title;

    public Item.Builder title(String title) {
      this.title = title;
      return this;
    }

    public Item build() {
      return new Item(
          new StringValue(title, null, null));
    }
  }
}
