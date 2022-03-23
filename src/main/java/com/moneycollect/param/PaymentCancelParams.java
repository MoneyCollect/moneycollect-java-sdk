package com.moneycollect.param;

import com.moneycollect.net.RequestParams;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Builder(setterPrefix = "set" )
public class PaymentCancelParams extends RequestParams {
    private String paymentId;
    private String cancellationReason;
}
