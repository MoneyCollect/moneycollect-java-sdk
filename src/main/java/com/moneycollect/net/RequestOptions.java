package com.moneycollect.net;

import com.moneycollect.MoneyCollect;
import lombok.EqualsAndHashCode;

import java.net.PasswordAuthentication;
import java.net.Proxy;

@EqualsAndHashCode(callSuper = false)
public class RequestOptions {
  private final String apiKey;
  private final String idempotencyKey;
  /** MoneyCollect version always set at {@link MoneyCollect#API_VERSION}. */
  private final String moneycollectVersion = MoneyCollect.API_VERSION;
  /**
   * MoneyCollect version override when made on behalf of others. This can be used when the returned
   * response will not be deserialized into the current classes pinned to {@link MoneyCollect#VERSION}.
   */
  private final String moneycollectVersionOverride;

  private final int connectTimeout;
  private final int readTimeout;

  private final int maxNetworkRetries;
  private final Proxy connectionProxy;
  private final PasswordAuthentication proxyCredential;

  public static RequestOptions getDefault() {
    return new RequestOptions(
        MoneyCollect.apiKey,
        null,
        null,
        MoneyCollect.getConnectTimeout(),
        MoneyCollect.getReadTimeout(),
        MoneyCollect.getMaxNetworkRetries(),
        MoneyCollect.getConnectionProxy(),
        MoneyCollect.getProxyCredential());
  }

  private RequestOptions(
      String apiKey,
      String idempotencyKey,
      String moneycollectVersionOverride,
      int connectTimeout,
      int readTimeout,
      int maxNetworkRetries,
      Proxy connectionProxy,
      PasswordAuthentication proxyCredential) {
    this.apiKey = apiKey;
    this.idempotencyKey = idempotencyKey;
    this.moneycollectVersionOverride = moneycollectVersionOverride;
    this.connectTimeout = connectTimeout;
    this.readTimeout = readTimeout;
    this.maxNetworkRetries = maxNetworkRetries;
    this.connectionProxy = connectionProxy;
    this.proxyCredential = proxyCredential;
  }

  public String getApiKey() {
    return apiKey;
  }

  public String getIdempotencyKey() {
    return idempotencyKey;
  }

  public String getMoneyCollectVersion() {
    return moneycollectVersion;
  }

  public String getMoneyCollectVersionOverride() {
    return moneycollectVersionOverride;
  }

  public int getReadTimeout() {
    return readTimeout;
  }

  public int getConnectTimeout() {
    return connectTimeout;
  }

  public int getMaxNetworkRetries() {
    return maxNetworkRetries;
  }

  public Proxy getConnectionProxy() {
    return connectionProxy;
  }

  public PasswordAuthentication getProxyCredential() {
    return proxyCredential;
  }

  public static RequestOptionsBuilder builder() {
    return new RequestOptionsBuilder();
  }

  /**
   * Convert request options to builder, retaining invariant values for the integration.
   *
   * @return option builder.
   */
  public RequestOptionsBuilder toBuilder() {
    return new RequestOptionsBuilder().setApiKey(this.apiKey);
  }

  public static final class RequestOptionsBuilder {
    private String apiKey;
    private String idempotencyKey;
    private String moneycollectVersionOverride;
    private int connectTimeout;
    private int readTimeout;
    private int maxNetworkRetries;
    private Proxy connectionProxy;
    private PasswordAuthentication proxyCredential;

    /**
     * Constructs a request options builder with the global parameters (API key and client ID) as
     * default values.
     */
    public RequestOptionsBuilder() {
      this.apiKey = MoneyCollect.apiKey;
      this.connectTimeout = MoneyCollect.getConnectTimeout();
      this.readTimeout = MoneyCollect.getReadTimeout();
      this.maxNetworkRetries = MoneyCollect.getMaxNetworkRetries();
      this.connectionProxy = MoneyCollect.getConnectionProxy();
      this.proxyCredential = MoneyCollect.getProxyCredential();
    }

    public String getApiKey() {
      return apiKey;
    }

    public RequestOptionsBuilder setApiKey(String apiKey) {
      this.apiKey = normalizeApiKey(apiKey);
      return this;
    }

    public RequestOptionsBuilder clearApiKey() {
      this.apiKey = null;
      return this;
    }

    public RequestOptionsBuilder setIdempotencyKey(String idempotencyKey) {
      this.idempotencyKey = idempotencyKey;
      return this;
    }

    public int getConnectTimeout() {
      return connectTimeout;
    }

    /**
     * Sets the timeout value that will be used for making new connections to the MoneyCollect API (in
     * milliseconds).
     *
     * @param timeout timeout value in milliseconds
     */
    public RequestOptionsBuilder setConnectTimeout(int timeout) {
      this.connectTimeout = timeout;
      return this;
    }

    public int getReadTimeout() {
      return readTimeout;
    }

    /**
     * Sets the timeout value that will be used when reading data from an established connection to
     * the MoneyCollect API (in milliseconds).
     *
     * <p>Note that this value should be set conservatively because some API requests can take time
     * and a short timeout increases the likelihood of causing a problem in the backend.
     *
     * @param timeout timeout value in milliseconds
     */
    public RequestOptionsBuilder setReadTimeout(int timeout) {
      this.readTimeout = timeout;
      return this;
    }

    public int getMaxNetworkRetries() {
      return maxNetworkRetries;
    }

    /**
     * Sets the maximum number of times the request will be retried in the event of a failure.
     *
     * @param maxNetworkRetries the number of times to retry the request
     */
    public RequestOptionsBuilder setMaxNetworkRetries(int maxNetworkRetries) {
      this.maxNetworkRetries = maxNetworkRetries;
      return this;
    }

    public Proxy getConnectionProxy() {
      return connectionProxy;
    }

    public RequestOptionsBuilder setConnectionProxy(Proxy connectionProxy) {
      this.connectionProxy = connectionProxy;
      return this;
    }

    public PasswordAuthentication getProxyCredential() {
      return proxyCredential;
    }

    public RequestOptionsBuilder setProxyCredential(PasswordAuthentication proxyCredential) {
      this.proxyCredential = proxyCredential;
      return this;
    }

    public RequestOptionsBuilder clearIdempotencyKey() {
      this.idempotencyKey = null;
      return this;
    }

    public String getIdempotencyKey() {
      return this.idempotencyKey;
    }


    public String getMoneyCollectVersionOverride() {
      return this.moneycollectVersionOverride;
    }

    /**
     * Do not use this except for in API where JSON response is not fully deserialized into explicit
     * MoneyCollect classes, but only passed to other clients as raw data -- essentially making request on
     * behalf of others with their API version. Setting this value in a typical
     * scenario will result in deserialization error as the model classes have schema according to
     * the pinned {@link MoneyCollect#API_VERSION} and not the {@code moneycollectVersionOverride}
     *
     * @param moneycollectVersionOverride moneycollect version override which belongs to the client to make
     *     request on behalf of.
     * @return option builder
     */
    public RequestOptionsBuilder setMoneyCollectVersionOverride(String moneycollectVersionOverride) {
      this.moneycollectVersionOverride = normalizeMoneyCollectVersion(moneycollectVersionOverride);
      return this;
    }

    public RequestOptionsBuilder clearMoneyCollectVersionOverride() {
      return setMoneyCollectVersionOverride(null);
    }

    /** Constructs a {@link RequestOptions} with the specified values. */
    public RequestOptions build() {
      return new RequestOptions(
          normalizeApiKey(this.apiKey),
          normalizeIdempotencyKey(this.idempotencyKey),
          normalizeMoneyCollectVersion(this.moneycollectVersionOverride),
          connectTimeout,
          readTimeout,
          maxNetworkRetries,
          connectionProxy,
          proxyCredential);
    }
  }

  private static String normalizeApiKey(String apiKey) {
    // null apiKeys are considered "valid"
    if (apiKey == null) {
      return null;
    }
    String normalized = apiKey.trim();
    if (normalized.isEmpty()) {
      throw new InvalidRequestOptionsException("Empty API key specified!");
    }
    return normalized;
  }


  private static String normalizeMoneyCollectVersion(String moneycollectVersion) {
    // null moneycollectVersions are considered "valid" and use MoneyCollect.apiVersion
    if (moneycollectVersion == null) {
      return null;
    }
    String normalized = moneycollectVersion.trim();
    if (normalized.isEmpty()) {
      throw new InvalidRequestOptionsException("Empty MoneyCollect version specified!");
    }
    return normalized;
  }

  private static String normalizeIdempotencyKey(String idempotencyKey) {
    if (idempotencyKey == null) {
      return null;
    }
    String normalized = idempotencyKey.trim();
    if (normalized.isEmpty()) {
      throw new InvalidRequestOptionsException("Empty Idempotency Key Specified!");
    }
    if (normalized.length() > 255) {
      throw new InvalidRequestOptionsException(
          String.format(
              "Idempotency Key length was %d, which is larger than the 255 character " + "maximum!",
              normalized.length()));
    }
    return normalized;
  }


  public static class InvalidRequestOptionsException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public InvalidRequestOptionsException(String message) {
      super(message);
    }
  }
}
