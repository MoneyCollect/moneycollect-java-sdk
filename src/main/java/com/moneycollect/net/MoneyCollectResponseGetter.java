package com.moneycollect.net;


import com.moneycollect.exception.MoneyCollectException;

import java.util.Map;

public interface MoneyCollectResponseGetter {

  <T> T request(
     ApiResource.RequestMethod method,
      String url,
      Map<String, Object> params,
      Class<T> clazz,
     RequestOptions options)
      throws MoneyCollectException;

  <T> T request(
     ApiResource.RequestMethod method,
      String url,
      RequestParams params,
      Class<T> clazz,
     RequestOptions options)
      throws MoneyCollectException;

}
