package com.rometools.rome;

import java.util.Optional;
import org.w3c.dom.Node;

public class StringValue {

  private final String value;
  private final String raw;
  private final Node container;

  public StringValue(String value, String raw, Node container) {
    this.value = value;
    this.raw = raw;
    this.container = container;
  }

  public String asString() {
    return value != null ? value : "";
  }

  public boolean isPresent() {
    return value != null;
  }

  public Optional<String> asOptional() {
    return Optional.ofNullable(value);
  }

  public String raw() {
    if (raw == null) {
      throw new RuntimeException("Raw value not stored, enable the feature");
    }

    return raw;
  }

  public Node container() {
    if (raw == null) {
      throw new RuntimeException("Raw value not stored, enable the feature");
    }

    return container;
  }

  @Override
  public String toString() {
    return "StringValue{value=" + value + ", raw=" + raw + ", container=" + container + '}';
  }
}
