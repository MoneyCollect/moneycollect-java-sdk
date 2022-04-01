package com.moneycollect.exception;

import com.moneycollect.model.MoneyCollectError;
import lombok.Getter;
import lombok.Setter;

@Getter
public abstract class MoneyCollectException extends Exception {
  private static final long serialVersionUID = 2L;

  /** The error resource returned by MoneyCollect's API that caused the exception. */
  // implement Serializable
  @Setter
  transient MoneyCollectError moneyCollectError;

  private String code;
  private String requestId;
  private Integer statusCode;

  protected MoneyCollectException(String message, String requestId, String code, Integer statusCode) {
    this(message, requestId, code, statusCode, null);
  }

  /** Constructs a new MoneyCollect exception with the specified details.
   * @param message
   * @param requestId
   * @param code
   * @param statusCode
   * @param e
   */
  protected MoneyCollectException(
      String message, String requestId, String code, Integer statusCode, Throwable e) {
    super(message, e);
    this.code = code;
    this.requestId = requestId;
    this.statusCode = statusCode;
  }

  /**
   * Returns a description of the exception, including the HTTP status code and request ID (if
   * applicable).
   *
   * @return a string representation of the exception.
   */
  @Override
  public String getMessage() {
    String additionalInfo = "";
    if (code != null) {
      additionalInfo += "; code: " + code;
    }
    if (requestId != null) {
      additionalInfo += "; request-id: " + requestId;
    }
    return super.getMessage() + additionalInfo;
  }

  /**
   * Returns a description of the user facing exception
   *
   * @return a string representation of the user facing exception.
   */
  public String getUserMessage() {
    return super.getMessage();
  }
}
