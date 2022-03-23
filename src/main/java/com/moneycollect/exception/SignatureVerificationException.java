package com.moneycollect.exception;

import lombok.Getter;

@Getter
public class SignatureVerificationException extends MoneyCollectException {
  private static final long serialVersionUID = 2L;

  private final String sigHeader;

  public SignatureVerificationException(String message, String sigHeader) {
    super(message, null, null, 0);
    this.sigHeader = sigHeader;
  }
}
