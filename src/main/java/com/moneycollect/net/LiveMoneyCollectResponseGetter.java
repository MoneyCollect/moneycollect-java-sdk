package com.moneycollect.net;

import com.moneycollect.exception.*;
import com.moneycollect.model.MoneyCollectError;
import com.moneycollect.util.JsonUtil;

import java.util.Map;

public class LiveMoneyCollectResponseGetter implements MoneyCollectResponseGetter {
  private final HttpClient httpClient;

  /**
   * Initializes a new instance of the {@link LiveMoneyCollectResponseGetter} class with default
   * parameters.
   */
  public LiveMoneyCollectResponseGetter() {
    this(null);
  }

  /**
   * Initializes a new instance of the {@link LiveMoneyCollectResponseGetter} class.
   *
   * @param httpClient the HTTP client to use
   */
  public LiveMoneyCollectResponseGetter(HttpClient httpClient) {
    this.httpClient = (httpClient != null) ? httpClient : buildDefaultHttpClient();
  }


  @Override
  public <T> T request(
      ApiResource.RequestMethod method,
      String url,
      Map<String, Object> params,
      Class<T> clazz,
      RequestOptions options)
      throws MoneyCollectException {
    MoneyCollectRequest request = new MoneyCollectRequest(method, url, params, options);
    MoneyCollectResponse response = httpClient.requestWithRetries(request);
    return handleResult(response,clazz);
  }


  @Override
  public <T> T request(
      ApiResource.RequestMethod method,
      String url,
      RequestParams params,
      Class<T> clazz,
      RequestOptions options)
      throws MoneyCollectException {
    MoneyCollectRequest request = new MoneyCollectRequest(method, url, params, options);
    MoneyCollectResponse response = this.httpClient.requestWithRetries(request);
    return handleResult(response,clazz);
  }

  private  static <T> T handleResult(MoneyCollectResponse response, Class<T> clazz) throws MoneyCollectException {
    int responseCode    = response.code();
    String responseBody = response.body();
    String requestId    = response.requestId();
    if(responseCode == 500){
       throw  new ApiException( responseBody, response.requestId(), null, response.code(), null);
    }
    Result result = null;
    try {
      result = JsonUtil.parseObject(responseBody, Result.class);
    } catch (Exception e) {
      raiseMalformedJsonError(responseBody, responseCode, requestId, e);
    }
    if(result == null){
      raiseMalformedJsonError(responseBody, responseCode, requestId, null);
    }

    if(result.success()){
      T resource = JsonUtil.toJavaObject((Object)result.getData(),clazz);
      return resource;
    }else{
      handleApiError(response,result);
    }

    return null;
  }

  private static HttpClient buildDefaultHttpClient() {
    return new HttpURLConnectionClient();
  }

  private static void raiseMalformedJsonError(
      String responseBody, int responseCode, String requestId, Throwable e) throws ApiException {
    String details = e == null ? "none" : e.getMessage();
    throw new ApiException(
        String.format(
            "Invalid response object from API: %s. (HTTP response code was %d). Additional details: %s.",
            responseBody, responseCode, details),
        requestId,
        null,
        responseCode,
        e);
  }

  private static void handleApiError(MoneyCollectResponse response, Result result) throws MoneyCollectException {
    MoneyCollectException exception = null;
    MoneyCollectError error =  new MoneyCollectError(result.getCode(),result.getMsg());

    switch (response.code()) {
      case 400:
      case 404:
        if ("idempotency_error".equals(error.getCode())) {
          exception =
              new IdempotencyException(
                  error.getMessage(), response.requestId(), error.getCode(), response.code());
        } else {
          exception =
              new InvalidRequestException(
                  error.getMessage(),
                  null,
                  response.requestId(),
                  error.getCode(),
                  response.code(),
                  null);
        }
        break;
      case 401:
        exception =
            new AuthenticationException(
                error.getMessage(), response.requestId(), error.getCode(), response.code());
        break;
      case 403:
        exception =
            new PermissionException(
                error.getMessage(), response.requestId(), error.getCode(), response.code());
        break;
      default:
        exception =
            new ApiException(
                error.getMessage(), response.requestId(), error.getCode(), response.code(), null);
        break;
    }

    exception.setMoneyCollectError(error);

    throw exception;
  }


}
