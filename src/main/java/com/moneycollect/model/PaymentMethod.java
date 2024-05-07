package com.moneycollect.model;

import com.moneycollect.MoneyCollect;
import com.moneycollect.exception.MoneyCollectException;
import com.moneycollect.model.common.BillingDetails;
import com.moneycollect.model.common.Card;
import com.moneycollect.net.ApiResource;
import com.moneycollect.net.RequestOptions;
import com.moneycollect.param.*;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
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

    /** If this is a boleto PaymentMethod, this hash contains details about the Boleto payment method.*/
    private Boleto boleto;

    /** If this is a creditCardBrazil PaymentMethod, this hash contains details about the CreditCardBrazil payment method. */
    private CreditCardBrazil creditCardBrazil;

    /** If this is a pix PaymentMethod, this hash contains details about the PIX payment method. */
    private DlocalPix pix;

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
        Map<String, Object> mapParams = new HashMap<>();
        mapParams.put("id", params.getId());
        mapParams.put("customerId", params.getCustomerId());
        /** new map for supporting the www-urlencoded that the httpUtil no method to support */
        ApiResource.request(RequestMethod.POST, url, mapParams, PaymentMethod.class, options);
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

    public static PaymentMethod update(String paymentMethod, PaymentMethodUpdateParams params,RequestOptions options) throws MoneyCollectException {
        String url = String.format(
                "%s%s",
                MoneyCollect.getApiBase(),
                String.format("/v1/payment_methods/%s/update", ApiResource.urlEncodeId(paymentMethod)));
        return ApiResource.request(ApiResource.RequestMethod.POST, url, params, PaymentMethod.class, options);
    }

    public static PaymentMethod update(String paymentMethod, PaymentMethodUpdateParams params) throws MoneyCollectException {
        return update(paymentMethod, params, null);
    }


    @Getter
    @Builder(setterPrefix = "set")
    @EqualsAndHashCode(callSuper = false)
    public static class Boleto implements Serializable {
        /** The tax ID of the customer (CPF for individual consumers or CNPJ for businesses consumers) */
        private String taxId;
    }

    @Getter
    @Builder(setterPrefix = "set")
    @EqualsAndHashCode(callSuper = false)
    public static class CreditCardBrazil implements Serializable {
        /** The tax ID of the customer (CPF for individual consumers or CNPJ for businesses consumers) */
        private String taxId;
    }
    @Getter
    @Builder(setterPrefix = "set")
    @EqualsAndHashCode(callSuper = false)
    public static class DlocalPix implements Serializable {
        /** The tax ID of the customer (CPF for individual consumers or CNPJ for businesses consumers) */
        private String taxId;
    }
}
