package com.moneycollect.net;

import java.io.IOException;

public class BodyEncoder {

    public static HttpContent createHttpContent(RequestParams params) throws IOException {
        if (params == null) {
            return HttpContent.buildBodyContent("");
        }
        return HttpContent.buildBodyContent(params.toJson());
    }

}
