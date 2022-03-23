package com.moneycollect.param;

import com.moneycollect.net.RequestParams;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder(setterPrefix = "set")
public class PaymentMethodAttachParams extends RequestParams {
    /** customer ID**/
    private String customerId;
    /** payment method ID**/
    private String id;
}
