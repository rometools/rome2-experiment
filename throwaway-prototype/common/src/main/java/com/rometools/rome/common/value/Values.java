package com.rometools.rome.common.value;

import com.google.common.collect.ImmutableMap;
import java.time.ZonedDateTime;

public class Values {

  private static final ImmutableMap<Class<?>, Class<?>> valueClasses =
      ImmutableMap.of(
          String.class, StringValue.class,
          Integer.class, IntValue.class,
          ZonedDateTime.class, DateTimeValue.class);

  public static Class<?> getValueClass(Class<?> rawClass) {
    return valueClasses.get(rawClass);
  }
}
