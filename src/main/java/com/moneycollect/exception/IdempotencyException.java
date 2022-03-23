package com.moneycollect.exception;

public class IdempotencyException extends MoneyCollectException {
  private static final long serialVersionUID = 2L;

  public IdempotencyException(String message, String requestId, String code, Integer statusCode) {
    super(message, requestId, code, statusCode);
  }
}
