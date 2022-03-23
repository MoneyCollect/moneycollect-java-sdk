package com.moneycollect.model;

import com.moneycollect.MoneyCollect;
import com.moneycollect.exception.MoneyCollectException;
import com.moneycollect.net.ApiResource;
import com.moneycollect.net.RequestOptions;
import com.moneycollect.param.RefundCreateParams;
import com.moneycollect.param.RefundUpdateParams;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Map;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class Refund extends ApiResource {

    /** Unique identifier for the object.**/
    private String id;

    /**
     * Amount, in cents.<br/>
     * A positive integer representing how much to charge in the smallest currency unit.(eg.,990 cents to charge $9.90, a zero-decimal currency)
    **/
    private Long amount;

    /** ID of the payment that was refunded.**/
    private String paymentId;

    /** An arbitrary string attached to the object. Often useful for displaying to users **/
    private String description;

    /** Reason for the refund,(duplicate, fraudulent, or requested_by_customer,or other).**/
    private String reason;

    /** A note is required when a provided reason is 'other'.**/
    private String note;

    /** If the refund failed, the reason for refund failure if known. Possible values are lost_or_stolen_card, expired_or_canceled_card, or unknown.**/
    private String failureReason;

    /** Status of the refund. For credit card refunds, this can be pending, succeeded, or failed. **/
    private String status;

    /** Three-letter ISO currency code. Must be a supported currency.**/
    private String currency;

    /** Time at which the object was created. Measured in seconds since the Unix epoch.**/
    private Date created;


    /** Create a refund. */
    public static Refund create(RefundCreateParams params) throws MoneyCollectException {
        return create(params, (RequestOptions) null);
    }

    /** Create a refund. */
    public static Refund create(RefundCreateParams params, RequestOptions options)
            throws MoneyCollectException {
        String url = String.format("%s%s", MoneyCollect.getApiBase(), "/v1/refunds/create");
        return ApiResource.request(ApiResource.RequestMethod.POST, url, params, Refund.class, options);
    }

    /** Retrieves the details of an existing refund. */
    public static Refund retrieve(String refund) throws MoneyCollectException {
        return retrieve(refund, (Map<String, Object>) null, (RequestOptions) null);
    }

    /** Retrieves the details of an existing refund. */
    public static Refund retrieve(String refund, RequestOptions options) throws MoneyCollectException {
        return retrieve(refund, (Map<String, Object>) null, options);
    }

    /** Retrieves the details of an existing refund. */
    public static Refund retrieve(String refund, Map<String, Object> params, RequestOptions options)
            throws MoneyCollectException {
        String url =
                String.format(
                        "%s%s",
                        MoneyCollect.getApiBase(), String.format("/v1/refunds/%s", ApiResource.urlEncodeId(refund)));
        return ApiResource.request(ApiResource.RequestMethod.GET, url, params, Refund.class, options);
    }


    /**
     * Updates the specified refund by setting the values of the parameters passed. Any parameters not
     * provided will be left unchanged.
     *
     * <p>This request only accepts <code>metadata</code> as an argument.
     */
    public static Refund update(RefundUpdateParams params) throws MoneyCollectException {
        return update(params, (RequestOptions) null);
    }

    /**
     * Updates the specified refund by setting the values of the parameters passed. Any parameters not
     * provided will be left unchanged.
     *
     * <p>This request only accepts <code>metadata</code> as an argument.
     */
    public static Refund update(RefundUpdateParams params, RequestOptions options) throws MoneyCollectException {
        String url =
                String.format(
                        "%s%s",
                        MoneyCollect.getApiBase(),
                        String.format("/v1/refunds/%s/update", ApiResource.urlEncodeId(params.getId())));
        return ApiResource.request(ApiResource.RequestMethod.POST, url, params, Refund.class, options);
    }

}
