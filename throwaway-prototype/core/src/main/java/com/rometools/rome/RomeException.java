package com.rometools.rome;

class RomeException extends RuntimeException {

  public RomeException(String message) {
    super(message);
  }

  public RomeException(String message, Throwable cause) {
    super(message, cause);
  }
}
