package com.moneycollect.net;


import com.moneycollect.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;

public class MoneyCollectResponseStream extends AbstractResponse<InputStream> {
  /**
   * Initializes a new instance of the {@link MoneyCollectResponseStream} class.
   *
   * @param code the HTTP status code of the response
   * @param headers the HTTP headers of the response
   * @param body streaming body response
   * @throws NullPointerException if {@code headers} or {@code body} is {@code null}
   */
  public MoneyCollectResponseStream(int code, HttpHeaders headers, InputStream body) {
    super(code, headers, body);
  }

  /**
   * Buffers the entire response body into a string, constructing the appropriate MoneyCollectResponse
   *
   * @return the MoneyCollectResponse
   */
  MoneyCollectResponse unstream() throws IOException {
    final String bodyString = StreamUtils.readToEnd(this.body, ApiResource.CHARSET);
    this.body.close();
    return new MoneyCollectResponse(this.code, this.headers, bodyString);
  }
}
