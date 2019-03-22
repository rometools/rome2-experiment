package com.rometools.rome.common.value;

import java.util.Comparator;
import java.util.Objects;

public class IntValue extends AbstractValue<Integer> implements Comparable<IntValue> {

  private IntValue(Integer value) {
    super(value);
  }

  public static IntValue of(int value) {
    return new IntValue(value);
  }

  public static IntValue ofNullable(Integer value) {
    return new IntValue(value);
  }

  public static IntValue none() {
    return ofNullable(null);
  }

  public Integer defaultToZero() {
    return orElse(0);
  }

  @Override
  public int compareTo(IntValue other) {
    return Objects.compare(this, other, Comparator.comparing(IntValue::asNullable));
  }
}
