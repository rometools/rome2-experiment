package com.rometools.rome.common.parser;

import com.rometools.rome.common.value.DateTimeValue;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeParser implements Parser<DateTimeValue> {

  public static final DateTimeParser INSTANCE = new DateTimeParser();

  private static DateTimeFormatter formatter =
      DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss z");

  @Override
  public Class<DateTimeValue> getResultClass() {
    return DateTimeValue.class;
  }

  @Override
  public DateTimeValue parse(String input) {
    if (input.isEmpty()) {
      return DateTimeValue.none();
    }

    return DateTimeValue.of(ZonedDateTime.parse(input, formatter));
  }

  @Override
  public DateTimeValue none() {
    return DateTimeValue.none();
  }
}
