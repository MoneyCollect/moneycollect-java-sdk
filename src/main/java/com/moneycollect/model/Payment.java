package com.moneycollect.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.moneycollect.MoneyCollect;
import com.moneycollect.exception.MoneyCollectException;
import com.moneycollect.model.common.BillingDetails;
import com.moneycollect.model.common.Card;
import com.moneycollect.model.common.LineItem;
import com.moneycollect.model.common.Shipping;
import com.moneycollect.net.ApiResource;
import com.moneycollect.net.RequestOptions;
import com.moneycollect.param.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Payment  extends ApiResource {
    /** Unique identifier for the payment.**/
    private String id;

    /** Payment amount**/
    private Long amount;

    /** Three-letter ISO currency code**/
    private String currency;

    /** automatic、manual:payment attempt with an explicit confirmation.**/
    private String confirmationMethod;


    /**
     * ID of the Customer this PaymentIntent belongs to, if one exists.
     * Payment methods attached to other Customers cannot be used with this PaymentIntent.
     *
     * If present in combination with setup_future_usage,
     * this PaymentIntent’s payment method
     *  will be attached to the Customer after the PaymentIntent has been confirmed and any required actions from the user are complete.
     */
    /** ID of the Customer this Payment belongs to, if one exists**/
    private String customerId;

    /** An arbitrary string. Useful for displaying to users.**/
    private String description;


    /** ID of the payment method used with this Payment.**/
    private String paymentMethod;


    /** this is the email address that the receipt for this charge was sent to.**/
    private String receiptEmail;

    /** 'on' or 'off' , default is 'off' . If 'on' ,customer will be attached the payment **/
    private String setupFutureUsage;


    /** Statement descriptor suffix , Must contain at least two letter,maximum 10 characters.**/
    private String statementDescriptorSuffix;


    /** order no",name="orderNo**/
    private String orderNo;

    /** Notify this payment result**/
    private String notifyUrl ;

    /**
     * ONLY WHEN CONFIRM=TRUE
     * The URL to redirect your customer back to after they authenticate or cancel their payment on the payment method’s app or site.
     * If you’d prefer to redirect to a mobile application, you can alternatively supply an application URI scheme. This parameter can only be used with
     * confirm=true.
     */
    /** The URL to redirect your customer back to after authenticate or pay their payment**/
    private String returnUrl;

    /** This Payment from**/
    private String website;

    /** The ip of consumer**/
    private String ip;


    /** Controls when the funds will be captured from the customer’s account.**/
    private String preAuth;

    /**
     * For non-card charges, you can use this value as the complete description that appears on your customers’ statements.
     * Must contain at least one letter, maximum 22 characters.
     */
    /** Statement descriptor,Must contain at least two letter,maximum 22 characters.**/
    private String statementDescriptor;


    /** Shipping information for this Payment**/
    private Shipping shipping;


    /** A list of items the customer is purchasing**/
    private List<LineItem> lineItems;


    /**The client secret of this Payment**/
    private String clientSecret;

    /**The time at which the Payment was canceled**/
    private Date canceledAt;

    /** Reason for cancellation of this Payment **/
    private String cancellationReason;

    /**Status, succeeded,incomplete,uncaptured,disputed,pending,refunded,refund_pending,partially_refunded,failed,canceled**/
    private String displayStatus;

    /**
     *
     * Status of this Payment, one of requires_payment_method,
     *  requires_confirmation, requires_action, requires_capture,processing,canceled,failed,succeeded.
     */
    private String status;

    /** Amount that was collected by this Payment **/
    private Long amountReceived;

    /**Amount can be capture**/
    private Long amountCapturable;

    /**The list of payment method types (e.g. card) that this Payment is allowed to use. **/
    private List<String> paymentMethodTypes;

    /**The payment method type (e.g. card) that this Payment is use. **/
    private String paymentMethodType;

    /**Payment created . The value can be a string with an integer Unix timestamp**/
    private Date created;

    /**Risk info**/
    private RiskInfo riskInfo;

    /**If present, this property tells you what actions you need to do**/
    private NextAction nextAction;

    /**Payment Billing**/
    private BillingDetails billingDetails;

    /**Payment method details**/
    private PaymentMethodDetails paymentMethodDetails;


    /**error code**/
    private String errorCode;

    /**error message**/
    private String errorMessage;

    @Getter
    @Setter
    @EqualsAndHashCode(callSuper = false)
    public static class NextAction  {

        /** redirect **/
        private String type;

        /** The URL you must redirect **/
        private String redirectToUrl;
    }

    @Getter
    @Setter
    @EqualsAndHashCode(callSuper = false)
    public static class RiskInfo {
        /** The score of risk  **/
        private Integer riskScore;

        /** The message of risk **/
        private String riskMessage;
    }

    @Getter
    @Setter
    @EqualsAndHashCode(callSuper = false)
    public class PaymentMethodDetails {

        /**
         *card
         */
        private String type;

        /**
         * card details
         */
        private Card card;
    }

    public static Payment create(PaymentCreateParams params) throws MoneyCollectException {
        return create(params, (RequestOptions) null);
    }

    public static Payment create(PaymentCreateParams params, RequestOptions options)  throws MoneyCollectException {
        String url = String.format("%s%s", MoneyCollect.getApiBase(), "/v1/payment/create");
        return ApiResource.request(RequestMethod.POST, url, params, Payment.class, options);
    }

    /** Returns a list of PaymentIntents. */
    public static PaymentCollection list(PaymentListParams params)
            throws MoneyCollectException {
        return list(params, (RequestOptions) null);
    }

    /** Returns a list of PaymentIntents. */
    public static PaymentCollection list(PaymentListParams params, RequestOptions options)
            throws MoneyCollectException {
        String url = String.format("%s%s", MoneyCollect.getApiBase(), "/v1/payment");
        return ApiResource.requestCollection(url, params, PaymentCollection.class, options);
    }


    public static Payment retrieve(String paymentId) throws MoneyCollectException {
        return retrieve(paymentId,  (RequestOptions) null);
    }


    public static Payment retrieve(String paymentId, RequestOptions options)   throws MoneyCollectException {
        String url = String.format(
                        "%s%s",
                        MoneyCollect.getApiBase(),
                        String.format("/v1/payment/%s", ApiResource.urlEncodeId(paymentId)));
        return ApiResource.request(ApiResource.RequestMethod.GET, url, (Map<String, Object>) null, Payment.class, options);
    }

    public Payment update(PaymentUpdateParams params) throws MoneyCollectException {
        return update(params, (RequestOptions) null);
    }

    public Payment update(PaymentUpdateParams params, RequestOptions options) throws MoneyCollectException {
        String url =
                String.format(
                        "%s%s",
                        MoneyCollect.getApiBase(),
                        String.format("/v1/payment/%s", ApiResource.urlEncodeId(this.getId())));
        return ApiResource.request(ApiResource.RequestMethod.POST, url, params, Payment.class, options);
    }

    public Payment confirm() throws MoneyCollectException {
        return confirm(PaymentConfirmParams.builder().build(),  null);
    }

    public Payment confirm(RequestOptions options) throws MoneyCollectException {
        return confirm(PaymentConfirmParams.builder().build(), options);
    }

    public Payment confirm(PaymentConfirmParams params) throws MoneyCollectException {
        return confirm(params, (RequestOptions) null);
    }

    public Payment confirm(PaymentConfirmParams params, RequestOptions options)  throws MoneyCollectException {
        String url = String.format(
                        "%s%s",
                        MoneyCollect.getApiBase(),
                        String.format("/v1/payment/%s/confirm", ApiResource.urlEncodeId(this.getId())));
        return ApiResource.request(ApiResource.RequestMethod.POST, url, params, Payment.class, options);
    }

    public static Payment cancel(PaymentCancelParams params) throws MoneyCollectException {
        return cancel(params, (RequestOptions) null);
    }

    public static Payment cancel(PaymentCancelParams params, RequestOptions options)
            throws MoneyCollectException {
        String url = String.format("%s%s",
                        MoneyCollect.getApiBase(),
                        String.format("/v1/payment/%s/cancel", ApiResource.urlEncodeId(params.getPaymentId())));
        return ApiResource.request(ApiResource.RequestMethod.POST, url, params.toMap(), Payment.class, options);
    }

    /**
     * Capture the funds of an existing uncaptured Payment when its status is <code>
     * requires_capture</code>.
     *
     * <p>Uncaptured Payment will be canceled exactly seven days after they are created.
     *
     * authorization and capture</a>.
     */
    public static Payment capture() throws MoneyCollectException {
        return capture(null, (RequestOptions) null);
    }

    /**
     * Capture the funds of an existing uncaptured Payment when its status is <code>
     * requires_capture</code>.
     *
     * <p>Uncaptured Payment will be canceled exactly seven days after they are created.
     *
     * authorization and capture</a>.
     */
    public static Payment capture(RequestOptions options) throws MoneyCollectException {
        return capture(null, options);
    }

    public static Payment capture(PaymentCaptureParams params) throws MoneyCollectException {
        return capture(params, (RequestOptions) null);
    }

    public static Payment capture(PaymentCaptureParams params, RequestOptions options)   throws MoneyCollectException {
        String url = String.format("%s%s", MoneyCollect.getApiBase(),
                        String.format("/v1/payment/%s/capture", ApiResource.urlEncodeId(params.getPaymentId())));
        return ApiResource.request(ApiResource.RequestMethod.POST, url, params.toMap(), Payment.class, options);
    }
}
