package com.moneycollect.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.moneycollect.MoneyCollect;
import com.moneycollect.exception.MoneyCollectException;
import com.moneycollect.model.common.BillingDetails;
import com.moneycollect.model.common.Card;
import com.moneycollect.net.ApiResource;
import com.moneycollect.net.RequestOptions;
import com.moneycollect.param.PaymentMethodAttachParams;
import com.moneycollect.param.PaymentMethodCreateParams;
import com.moneycollect.param.PaymentMethodDetachParams;
import com.moneycollect.param.PaymentMethodListParams;
import lombok.Data;

import java.util.Date;
import java.util.HashMap;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentMethod  extends ApiResource {

    /**Unique identifier for the object.**/
    private String id;

    /**Time at which the object was created.**/
    private Date created;

    /**The ID of the Customer to which this PaymentMethod is saved. This will not be set when the PaymentMethod has not been saved to a Customer.**/
    private String customerId;

    /**card**/
    private String type;

    /**If this is a card PaymentMethod, this hash contains the user’s card details.**/
    private Card card;


    /**Billing information associated with the PaymentMethod that may be used or required by particular types of payment methods.**/
    private BillingDetails billingDetails;


    public static PaymentMethod create(PaymentMethodCreateParams params) throws MoneyCollectException {
        return create(params, (RequestOptions) null);
    }

    public static PaymentMethod create(PaymentMethodCreateParams params, RequestOptions options)  throws MoneyCollectException {
        String url = String.format("%s%s", MoneyCollect.getApiBase(), "/v1/payment_methods/create");
        return ApiResource.request(RequestMethod.POST, url, params, PaymentMethod.class, options);
    }

    public static void attach(PaymentMethodAttachParams params) throws MoneyCollectException {
        attach(params, (RequestOptions) null);
    }

    public static void attach(PaymentMethodAttachParams params, RequestOptions options)  throws MoneyCollectException {
        String url = String.format("%s%s", MoneyCollect.getApiBase(), String.format("/v1/payment_methods/%s/attach", ApiResource.urlEncodeId(params.getId())));
        ApiResource.request(RequestMethod.POST, url, params, PaymentMethod.class, options);
    }

    /** Detaches a PaymentMethod object from a Customer. */
    public static void detach(PaymentMethodDetachParams params) throws MoneyCollectException {
        detach(params, (RequestOptions) null);
    }

    /** Detaches a PaymentMethod object from a Customer. */
    public static void detach(PaymentMethodDetachParams params, RequestOptions options)   throws MoneyCollectException {
        String url = String.format(
                        "%s%s",
                        MoneyCollect.getApiBase(),
                        String.format("/v1/payment_methods/%s/detach", ApiResource.urlEncodeId(params.getId())));
         ApiResource.request(ApiResource.RequestMethod.POST, url, params, PaymentMethod.class, options);
    }

    /** Retrieves a PaymentMethod object. */
    public static PaymentMethod retrieve(String paymentMethod) throws MoneyCollectException {
        return retrieve(paymentMethod,  (RequestOptions) null);
    }

    /** Retrieves a PaymentMethod object. */
    public static PaymentMethod retrieve(String paymentMethod, RequestOptions options)   throws MoneyCollectException {
        String url = String.format(
                        "%s%s",
                        MoneyCollect.getApiBase(),
                        String.format("/v1/payment_methods/%s", ApiResource.urlEncodeId(paymentMethod)));
        return ApiResource.request(ApiResource.RequestMethod.GET, url, new HashMap<>(), PaymentMethod.class, options);
    }

    /** Returns a list of PaymentMethods for a given Customer. */
    public static PaymentMethodCollection list(PaymentMethodListParams params)   throws MoneyCollectException {
        return list(params, (RequestOptions) null);
    }

    /** Returns a list of PaymentMethods for a given Customer. */
    public static PaymentMethodCollection list(PaymentMethodListParams params, RequestOptions options)   throws MoneyCollectException {
        String url = String.format("%s%s", MoneyCollect.getApiBase(),
                                            String.format("%s%s","/v1/payment_methods/list/",ApiResource.urlEncodeId(params.getCustomerId())));
        return ApiResource.requestCollection(url, params, PaymentMethodCollection.class, options);
    }
}
