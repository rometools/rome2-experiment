package com.rometools.rome.model.value;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class StringValue extends AbstractValue<String> {

  protected StringValue(String value) {
    super(value);
  }

  public static StringValue ofNullable(String value) {
    return new StringValue(value);
  }

  public static StringValue empty() {
    return ofNullable(null);
  }

  public static ArrayList<StringValue> mapListOfNullables(ArrayList<String> values) {
    return new ArrayList<>(
        values.stream()
            .map(StringValue::ofNullable)
            .collect(Collectors.toList()));
  }

  public String defaultToEmpty() {
    return orElse("");
  }
}
