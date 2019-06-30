package com.rometools.rome.common.parser;

public class StringParser implements Parser<String> {

  @Override
  public Class<String> getResultClass() {
    return String.class;
  }

  @Override
  public ParseResult<String> parse(String input) {
    return new ParseResult<>(input);
  }
}
