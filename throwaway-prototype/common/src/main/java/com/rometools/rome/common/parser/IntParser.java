package com.rometools.rome.common.parser;

import com.rometools.rome.common.value.IntValue;

public class IntParser implements Parser<IntValue> {

  @Override
  public Class<IntValue> getResultClass() {
    return IntValue.class;
  }

  @Override
  public IntValue parse(String input) {
    return IntValue.ofNullable(Integer.valueOf(input));
  }

  @Override
  public IntValue none() {
    return IntValue.none();
  }
}
