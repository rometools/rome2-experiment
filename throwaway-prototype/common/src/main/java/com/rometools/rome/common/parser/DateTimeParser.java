package com.rometools.rome.common.parser;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeParser implements Parser<ZonedDateTime> {

  private static DateTimeFormatter formatter =
      DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss z");

  @Override
  public Class<ZonedDateTime> getResultClass() {
    return ZonedDateTime.class;
  }

  @Override
  public ParseResult<ZonedDateTime> parse(String input) {
    if (input.isEmpty()) {
      return ParseResult.empty();
    }

    return new ParseResult<>(ZonedDateTime.parse(input, formatter));
  }
}
