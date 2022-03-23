package com.moneycollect.exception;

public class ApiException extends MoneyCollectException {
  private static final long serialVersionUID = 2L;

  public ApiException(
      String message, String requestId, String code, Integer statusCode, Throwable e) {
    super(message, requestId, code, statusCode, e);
  }
}
