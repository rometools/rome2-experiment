package com.rometools.rome.common.value;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

public class DateTimeValue extends AbstractValue<ZonedDateTime> {

  protected DateTimeValue(ZonedDateTime value) {
    super(value);
  }

  public static DateTimeValue of(ZonedDateTime value) {
    if (value == null) {
      throw new IllegalArgumentException();
    }
    return new DateTimeValue(value);
  }

  public static DateTimeValue ofNullable(ZonedDateTime value) {
    return new DateTimeValue(value);
  }

  public static DateTimeValue none() {
    return ofNullable(null);
  }

  public ZonedDateTime defaultToEpoch() {
    return orElse(ZonedDateTime.ofInstant(Instant.EPOCH, ZoneOffset.UTC));
  }
}
