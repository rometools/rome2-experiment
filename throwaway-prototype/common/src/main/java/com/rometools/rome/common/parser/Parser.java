package com.rometools.rome.common.parser;

public interface Parser<T> {

  Class<T> getResultClass();

  T parse(String input);

  T none();
}
