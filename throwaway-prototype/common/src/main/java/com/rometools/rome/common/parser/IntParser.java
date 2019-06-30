package com.rometools.rome.common.parser;

public class IntParser implements Parser<Integer> {

  @Override
  public Class<Integer> getResultClass() {
    return Integer.class;
  }

  @Override
  public ParseResult<Integer> parse(String input) {
    return new ParseResult<>(Integer.valueOf(input));
  }
}
