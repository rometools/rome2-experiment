package com.rometools.rome.common.value;

public class StringValue extends AbstractValue<String> {

  protected StringValue(String value) {
    super(value);
  }

  public static StringValue of(String value) {
    if (value == null) {
      throw new IllegalArgumentException();
    }
    return new StringValue(value);
  }

  public static StringValue ofNullable(String value) {
    return new StringValue(value);
  }

  public static StringValue none() {
    return ofNullable(null);
  }

  public String defaultToEmpty() {
    return orElse("");
  }
}
