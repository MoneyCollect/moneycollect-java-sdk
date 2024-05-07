package com.moneycollect.model.checkout;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.moneycollect.MoneyCollect;
import com.moneycollect.exception.MoneyCollectException;
import com.moneycollect.model.common.BillingDetails;
import com.moneycollect.model.common.LineItem;
import com.moneycollect.model.common.Shipping;
import com.moneycollect.net.ApiResource;
import com.moneycollect.net.RequestOptions;
import com.moneycollect.param.checkout.SessionCreateParams;
import com.moneycollect.param.checkout.SessionListParams;
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
public class Session extends ApiResource{

    /**ID**/
    private String id;

    /**paymentId**/
    private String paymentId;

    /**url **/
    private String url;

    /**status**/
    private String status;

    /**created.**/
    private Date created;

    
    /**Total of all items after discounts and taxes are applied.**/
    private Long amountTotal;

    /**Three-letter ISO currency code**/
    private String currency;


    /**A unique string to reference the Checkout Session**/
    private String clientReferenceId;


    /**If not provided, Checkout will be asked to enter their email address.**/
    private String customerEmail;


    /**ID of an existing Customer, if one exists**/
    private String customer;

    /**The URL the customer will be directed to if they decide to cancel payment and return to your website.**/
    private String cancelUrl;


    /**The URL should send customers when payment is complete.**/
    private String returnUrl;

    /**The URL should notify when payment is complete.**/
    private String notifyUrl;

    /**orderNo.**/
    private String orderNo;

    /**website.**/
    private String website;

    /**submitType can only be specified on Checkout Sessions in payment mode**/
    private String submitType;

    /**A list of items the customer is purchasing**/
    private List<LineItem> lineItems;


    /**Is pre-authorize , 'y' OR 'n' **/
    private String preAuth;


    /**Billing details . If not provide , Checkout should collect the customer’s billing address. **/
    private BillingDetails billingDetails;

 
    /**Shipping address**/
    private Shipping shipping;

    /**
     *For non-card charges, you can use this value as the complete description that appears on your customers’ statements.
     * Must contain at least one letter, maximum 22 characters.
     */
    /**Statement descriptor,Must contain at least two letter,maximum 22 characters.**/
    private String statementDescriptor;

    /** A list of the types of payment methods (e.g. card) this Checkout Session is allowed to accept.*/
    private List<String> paymentMethodTypes;

    /** Card types that support installment */
    private List<String> supportInstallmentCardTypes;

    /** Creates a Session object. */
    public static Session create(SessionCreateParams params) throws MoneyCollectException {
        return create(params, (RequestOptions) null);
    }

    /** Creates a Session object. */
    public static Session create(SessionCreateParams params, RequestOptions options)  throws MoneyCollectException {
        String url = String.format("%s%s", MoneyCollect.getApiBase(), "/v1/checkout/session/create");
        return ApiResource.request(ApiResource.RequestMethod.POST, url, params, Session.class, options);
    }

    /** Retrieves a Session object. */
    public static Session retrieve(String session) throws MoneyCollectException {
        return retrieve(session, (Map<String, Object>) null, (RequestOptions) null);
    }

    /** Retrieves a Session object. */
    public static Session retrieve(String session, RequestOptions options) throws MoneyCollectException {
        return retrieve(session, (Map<String, Object>) null, options);
    }

    /** Retrieves a Session object. */
    public static Session retrieve(String session, Map<String, Object> params, RequestOptions options)
            throws MoneyCollectException {
        String url =
                String.format(
                        "%s%s",
                        MoneyCollect.getApiBase(),
                        String.format("/v1/checkout/session/%s", ApiResource.urlEncodeId(session)));
        return ApiResource.request(ApiResource.RequestMethod.GET, url, params, Session.class, options);
    }

    /** Returns a list of Checkout Sessions. */
    public static SessionCollection list(SessionListParams params) throws MoneyCollectException {
        return list(params, (RequestOptions) null);
    }

    /** Returns a list of Checkout Sessions. */
    public static SessionCollection list(SessionListParams params, RequestOptions options)
            throws MoneyCollectException {
        String url = String.format("%s%s", MoneyCollect.getApiBase(), "/v1/checkout/session");
        return ApiResource.requestCollection(url, params, SessionCollection.class, options);
    }
}
