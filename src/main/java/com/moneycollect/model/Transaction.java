package com.moneycollect.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.moneycollect.MoneyCollect;
import com.moneycollect.exception.MoneyCollectException;
import com.moneycollect.net.ApiResource;
import com.moneycollect.net.RequestOptions;
import com.moneycollect.param.TransactionListParams;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Transaction  extends ApiResource {

    /**Unique identifier for the object.**/
    private String id;
    /**The date the transaction’s net funds will become available**/
    private Date availableOn;
    /**Gross amount of the transaction, in cents.**/
    private Long amount;
    /**Three-letter ISO currency code**/
    private String currency;
    /**An arbitrary string attached to the object**/
    private String description;
    /**The exchange rate used, if applicable, for this transaction. **/
    private BigDecimal exchangeRate;
    /**Fees (in cents) paid for this transaction**/
    private Long fee;
    /**Detailed breakdown of fees (in cents) paid for this transaction.**/
    private List<TransactionFee> feeDetails;
    /**Net amount of the transaction, in cents.**/
    private Long net;
    /**Transaction type: charge、refund、refund_rollback、reserved、adjustment、payout、payout_cancel、payout_failure、mc_fee**/
    private String type;
    /**available or pending**/
    private String status;
    /**Time at which the object was created**/
    private Date created;

    /**
     * Retrieves the balance transaction with the given ID.
     *
     */
    public static Transaction retrieve(String id) throws MoneyCollectException {
        return retrieve(id, (Map<String, Object>) null, (RequestOptions) null);
    }

    /**
     * Retrieves the balance transaction with the given ID.
     */
    public static Transaction retrieve(String id, RequestOptions options)
            throws MoneyCollectException {
        return retrieve(id, (Map<String, Object>) null, options);
    }

    /**
     * Retrieves the balance transaction with the given ID.
     */
    public static Transaction retrieve(
            String id, Map<String, Object> params, RequestOptions options) throws MoneyCollectException {
        String url =
                String.format(
                        "%s%s",
                        MoneyCollect.getApiBase(),
                        String.format("/v1/transactions/%s", ApiResource.urlEncodeId(id)));
        return ApiResource.request(
                ApiResource.RequestMethod.GET, url, params, Transaction.class, options);
    }



    /**
     * Returns a list of transactions that have contributed to the Stripe account balance (e.g.,
     * charges). The transactions are returned in sorted order, with the most
     * recent transactions appearing first.
     *
     */
    public static TransactionCollection list(TransactionListParams params)
            throws MoneyCollectException {
        return list(params, (RequestOptions) null);
    }

    /**
     * Returns a list of transactions that have contributed to the Stripe account balance (e.g.,
     * charges). The transactions are returned in sorted order, with the most
     * recent transactions appearing first.
     *
     */
    public static TransactionCollection list(
            TransactionListParams params, RequestOptions options) throws MoneyCollectException {
        String url = String.format("%s%s", MoneyCollect.getApiBase(), "/v1/transactions");
        return ApiResource.requestCollection(url, params, TransactionCollection.class, options);
    }
}
