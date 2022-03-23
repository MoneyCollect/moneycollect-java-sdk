package com.moneycollect.model;

import com.moneycollect.MoneyCollect;
import com.moneycollect.exception.MoneyCollectException;
import com.moneycollect.net.ApiResource;
import com.moneycollect.net.RequestOptions;
import com.moneycollect.param.PayoutListParams;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Map;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class Payout extends ApiResource {
    /**id of payout**/
    private String id;
    /**payout amount**/
    private Long amount;
    /**currency**/
    private String currency;
    /**last4 number of bankAccountNo**/
    private String bankAccountNo;
    /** pending,paid,failed**/
    private String status;
    /**failed reason**/
    private String failedReason;
    /**created time**/
    private Date created;

    /**
     * Retrieves the details of an existing payout. Supply the unique payout ID from either a payout
     * creation request or the payout list, and MoneyCollect will return the corresponding payout
     * information.
     */
    public static Payout retrieve(String payout) throws MoneyCollectException {
        return retrieve(payout, (Map<String, Object>) null, (RequestOptions) null);
    }

    /**
     * Retrieves the details of an existing payout. Supply the unique payout ID from either a payout
     * creation request or the payout list, and MoneyCollect will return the corresponding payout
     * information.
     */
    public static Payout retrieve(String payout, RequestOptions options) throws MoneyCollectException {
        return retrieve(payout, (Map<String, Object>) null, options);
    }

    /**
     * Retrieves the details of an existing payout. Supply the unique payout ID from either a payout
     * creation request or the payout list, and MoneyCollect will return the corresponding payout
     * information.
     */
    public static Payout retrieve(String payout, Map<String, Object> params, RequestOptions options)
            throws MoneyCollectException {
        String url =
                String.format(
                        "%s%s",
                        MoneyCollect.getApiBase(), String.format("/v1/payouts/%s", ApiResource.urlEncodeId(payout)));
        return ApiResource.request(ApiResource.RequestMethod.GET, url, params, Payout.class, options);
    }

    /**
     * Returns a list of existing payouts sent to third-party bank accounts or that MoneyCollect has sent
     * you. The payouts are returned in sorted order, with the most recently created payouts appearing
     * first.
     */
    public static PayoutCollection list(PayoutListParams params) throws MoneyCollectException {
        return list(params, (RequestOptions) null);
    }

    /**
     * Returns a list of existing payouts sent to third-party bank accounts or that MoneyCollect has sent
     * you. The payouts are returned in sorted order, with the most recently created payouts appearing
     * first.
     */
    public static PayoutCollection list(PayoutListParams params, RequestOptions options)
            throws MoneyCollectException {
        String url = String.format("%s%s", MoneyCollect.getApiBase(), "/v1/payouts");
        return ApiResource.requestCollection(url, params, PayoutCollection.class, options);
    }

}
