package com.moneycollect.net;

import com.moneycollect.util.JsonUtil;

import java.util.Map;

/**
 * Super class to all api request params objects. This common abstraction is internally used in
 * {@link com.moneycollect.net.ApiResource#request(com.moneycollect.net.ApiResource.RequestMethod, String, RequestParams, Class,
 * com.moneycollect.net.RequestOptions)}. It also exposes a convenient method converting the typed parameter into the
 * legacy support of untyped {@code Map<String, Object>} param.
 */
public abstract class RequestParams {
  public interface EnumParam {
    String getValue();
  }
  public Map<String,Object> toMap() {
    return JsonUtil.toMap(this);
  }

  public String toJson() {
    return JsonUtil.toJson(this);
  }

}
