package com.moneycollect.param;

import com.moneycollect.net.RequestParams;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Builder(setterPrefix = "set")
public class PaymentMethodDetachParams  extends RequestParams {
    /**Unique identifier for the object.**/
    private String id;

}
