package com.rometools.rome.model.value;

import java.util.Optional;

public abstract class AbstractValue<T> {

  protected final T value;

  protected AbstractValue(T value) {
    this.value = value;
  }

  public boolean isPresent() {
    return value != null;
  }

  public Optional<T> asOptional() {
    return Optional.ofNullable(value);
  }

  public T orElse(T other) {
    return value != null ? value : other;
  }

  public T asNullable() {
    return value;
  }

  public T raw() {
    throw new UnsupportedOperationException("Raw value not stored, enable the feature");
  }

  @Override
  public String toString() {
    return String.format("%s(%s)", getClass().getSimpleName(), value);
  }
}
