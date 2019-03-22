package com.rometools.rome.common.parser;

import com.rometools.rome.common.value.StringValue;

public class StringParser implements Parser<StringValue> {

  @Override
  public Class<StringValue> getResultClass() {
    return StringValue.class;
  }

  @Override
  public StringValue parse(String input) {
    return StringValue.ofNullable(input);
  }

  @Override
  public StringValue none() {
    return StringValue.none();
  }
}
