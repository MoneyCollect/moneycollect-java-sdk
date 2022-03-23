package com.moneycollect.param;

import com.moneycollect.net.RequestParams;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Getter
@Builder(setterPrefix = "set")
public class PaymentCaptureParams extends RequestParams {
    private String paymentId;
    private String comment;
}
