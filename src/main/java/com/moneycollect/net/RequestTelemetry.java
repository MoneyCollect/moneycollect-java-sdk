package com.moneycollect.net;


import com.moneycollect.MoneyCollect;

import java.time.Duration;
import java.util.Optional;
import java.util.concurrent.ConcurrentLinkedQueue;

/** Helper class used by {@link LiveMoneyCollectResponseGetter} to manage request telemetry. */
class RequestTelemetry {
  /** The name of the header used to send request telemetry in requests. */
  public static final String HEADER_NAME = "X-MoneyCollect-Client-Telemetry";

  private static final int MAX_REQUEST_METRICS_QUEUE_SIZE = 1;

  private static ConcurrentLinkedQueue<String> prevRequestMetrics = new ConcurrentLinkedQueue<String>();

  /**
   * Returns an {@link Optional} containing the value of the {@code X-MoneyCollect-Telemetry} header to
   * add to the request. If the header is already present in the request, or if there is available
   * metrics, or if telemetry is disabled, then the returned {@code Optional} is empty.
   *
   * @param headers the request headers
   */
  public Optional<String> getHeaderValue(HttpHeaders headers) {
    if (headers.firstValue(HEADER_NAME).isPresent()) {
      return Optional.empty();
    }

    String requestMetrics = prevRequestMetrics.poll();
    if (requestMetrics == null) {
      return Optional.empty();
    }

    if (!MoneyCollect.enableTelemetry) {
      return Optional.empty();
    }

    return Optional.of(requestMetrics);
  }

  /**
   * If telemetry is enabled and the queue is not full, then enqueue a new metrics item; otherwise,
   * do nothing.
   *
   * @param response the MoneyCollect response
   * @param duration the request duration
   */
  public void maybeEnqueueMetrics(AbstractResponse<?> response, Duration duration) {
    if (!MoneyCollect.enableTelemetry) {
      return;
    }

    if (response.requestId() == null) {
      return;
    }

    if (prevRequestMetrics.size() >= MAX_REQUEST_METRICS_QUEUE_SIZE) {
      return;
    }

    prevRequestMetrics.add(response.requestId());
  }
}
