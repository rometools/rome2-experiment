package com.rometools.rome.model.value;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class IntValue extends AbstractValue<Integer> {

  private IntValue(Integer value) {
    super(value);
  }

  public static IntValue ofNullable(Integer value) {
    return new IntValue(value);
  }

  public static IntValue empty() {
    return ofNullable(null);
  }

  public static ArrayList<IntValue> mapListOfNullables(ArrayList<Integer> values) {
    return new ArrayList<>(
        values.stream()
            .map(IntValue::ofNullable)
            .collect(Collectors.toList()));
  }

  public Integer defaultToZero() {
    return orElse(0);
  }
}
