package com.rometools.rome.common.value;

public class Value<T> extends AbstractValue<T> {

  protected Value(T value) {
    super(value);
  }

  public static <T> Value<T> ofNullable(T value) {
    return new Value<>(value);
  }

  public static <T> Value<T> none() {
    return ofNullable(null);
  }

  public T defaultToNull() {
    return orElse(null);
  }
}
