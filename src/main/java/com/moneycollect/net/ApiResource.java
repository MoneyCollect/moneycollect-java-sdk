package com.moneycollect.net;


import com.moneycollect.exception.InvalidRequestException;
import com.moneycollect.exception.MoneyCollectException;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public abstract class ApiResource  {
  public static final Charset CHARSET = StandardCharsets.UTF_8;

  private static MoneyCollectResponseGetter moneycollectResponseGetter = new LiveMoneyCollectResponseGetter();

  public static void setMoneyCollectResponseGetter(MoneyCollectResponseGetter srg) {
    ApiResource.moneycollectResponseGetter = srg;
  }



  public enum RequestMethod {
    GET,
    POST,
    DELETE
  }

  /** URL-encodes a string. */
  public static String urlEncode(String str) {
    // Preserve original behavior that passing null for an object id will lead
    // to us actually making a request to /v1/foo/null
    if (str == null) {
      return null;
    }

    try {
      // Don't use strict form encoding by changing the square bracket control
      // characters back to their literals. This is fine by the server, and
      // makes these parameter strings easier to read.
      return URLEncoder.encode(str, CHARSET.name()).replaceAll("%5B", "[").replaceAll("%5D", "]");
    } catch (UnsupportedEncodingException e) {
      // This can literally never happen, and lets us avoid having to catch
      // UnsupportedEncodingException in callers.
      throw new AssertionError("UTF-8 is unknown");
    }
  }

  /** URL-encode a string ID in url path formatting. */
  public static String urlEncodeId(String id) throws InvalidRequestException {
    if (id == null) {
      throw new InvalidRequestException(
          "Invalid null ID found for url path formatting. This can be because your string ID "
              + "argument to the API method is null, or the ID field in your moneycollect object "
              + "instance is null. Please contact support@moneycollect.com on the latter case. ",
          null,
          null,
          null,
          0,
          null);
    }

    return urlEncode(id);
  }

  public static <T> T request(
      ApiResource.RequestMethod method,
      String url,
      RequestParams params,
      Class<T> clazz,
      RequestOptions options)
      throws MoneyCollectException {
    checkNullTypedParams(url, params);
    return ApiResource.moneycollectResponseGetter.request(method, url, params, clazz, options);
  }

  public static <T> T request(
      ApiResource.RequestMethod method,
      String url,
      Map<String, Object> params,
      Class<T> clazz,
      RequestOptions options)
      throws MoneyCollectException {
    return ApiResource.moneycollectResponseGetter.request(method, url, params, clazz, options);
  }



  public static  <T> T requestCollection(
          String url, RequestParams params, Class<T> clazz, RequestOptions options)
      throws MoneyCollectException {
    checkNullTypedParams(url, params);
    return requestCollection(url, params.toMap(), clazz, options);
  }

  /**
   * Similar to #request, but specific for use with collection types that come from the API (i.e.
   * lists of resources).
   *
   * <p>Collections need a little extra work because we need to plumb request options and params
   * through so that we can iterate to the next page if necessary.
   */
  public static <T> T requestCollection(
      String url, Map<String, Object> params, Class<T> clazz, RequestOptions options)
      throws MoneyCollectException {
    return request(RequestMethod.GET, url, params, clazz, options);
  }


  /**
   * Invalidate null typed parameters.
   *
   * @param url request url associated with the given parameters.
   * @param params typed parameters to check for null value.
   */
  public static void checkNullTypedParams(String url, RequestParams params) {
    if (params == null) {
      throw new IllegalArgumentException(
          String.format(
              "Found null params for %s. "
                  + "Please pass empty params using param builder via `builder().build()` instead.",
              url));
    }
  }


}
