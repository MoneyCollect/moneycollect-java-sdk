package com.moneycollect.param;

import com.moneycollect.net.RequestParams;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(setterPrefix = "set")
public class RefundUpdateParams extends RequestParams {
    /** Unique identifier for the object. **/
    private String id;
    /**
     * description
     */
    private String description;
}
