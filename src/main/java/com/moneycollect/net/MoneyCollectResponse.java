package com.moneycollect.net;

/** A response from MoneyCollect's API, with body represented as a String. */
public class MoneyCollectResponse extends AbstractResponse<String> {
  /**
   * Initializes a new instance of the {@link MoneyCollectResponse} class.
   *
   * @param code the HTTP status code of the response
   * @param headers the HTTP headers of the response
   * @param body the body of the response
   * @throws NullPointerException if {@code headers} or {@code body} is {@code null}
   */
  public MoneyCollectResponse(int code, HttpHeaders headers, String body) {
    super(code, headers, body);
  }
}
