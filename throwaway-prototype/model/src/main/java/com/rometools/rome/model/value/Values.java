package com.rometools.rome.model.value;

import com.rometools.rome.model.xml.jaxb.Type;
import java.time.ZonedDateTime;

public class Values {

  public static Class<?> wrapperClass(Type type) {
    switch (type) {
      case INTEGER:
        return IntValue.class;
      case STRING:
        return StringValue.class;
      case DATETIME:
        return DateTimeValue.class;
      default:
        throw new IllegalArgumentException("Unsupported value type: " + type);
    }
  }

  public static Class<?> rawClass(Type type) {
    switch (type) {
      case INTEGER:
        return Integer.class;
      case STRING:
        return String.class;
      case DATETIME:
        return ZonedDateTime.class;
      default:
        throw new IllegalArgumentException("Unsupported value type: " + type);
    }
  }
}
