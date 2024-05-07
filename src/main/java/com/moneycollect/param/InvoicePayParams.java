package com.moneycollect.param;

import com.moneycollect.net.RequestParams;
import lombok.Data;

@Data
public class InvoicePayParams extends RequestParams {
    /** A PaymentMethod to be charged. The PaymentMethod must be the ID of a PaymentMethod belonging to the customer associated with the invoice being paid. */
    private String paymentMethodId;
}
