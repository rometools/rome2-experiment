package com.rometools.rome.common.parser;

public interface Parser<T> {

  IntParser INT = new IntParser();
  StringParser STRING = new StringParser();
  DateTimeParser DATETIME = new DateTimeParser();

  Class<T> getResultClass();

  ParseResult<T> parse(String input);

  class ParseResult<T> {

    private final T value;

    public ParseResult(T value) {
      this.value = value;
    }

    public T value() {
      return value;
    }

    public static <T> ParseResult<T> empty() {
      return new ParseResult<>(null);
    }
  }
}
