package com.rometools.rome.model.value;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class DateTimeValue extends AbstractValue<ZonedDateTime> {

  protected DateTimeValue(ZonedDateTime value) {
    super(value);
  }

  public static DateTimeValue ofNullable(ZonedDateTime value) {
    return new DateTimeValue(value);
  }

  public static DateTimeValue empty() {
    return ofNullable(null);
  }

  public static ArrayList<DateTimeValue> mapListOfNullables(ArrayList<ZonedDateTime> values) {
    return new ArrayList<>(
        values.stream()
            .map(DateTimeValue::ofNullable)
            .collect(Collectors.toList()));
  }

  public ZonedDateTime defaultToEpoch() {
    return orElse(ZonedDateTime.ofInstant(Instant.EPOCH, ZoneOffset.UTC));
  }
}
