package com.moneycollect.model;

import com.moneycollect.MoneyCollect;
import com.moneycollect.exception.MoneyCollectException;
import com.moneycollect.net.ApiResource;
import com.moneycollect.net.RequestOptions;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class Balance extends ApiResource {

    /**Funds that are available to be transferred or paid out**/
    private List<BalanceItem> available;
    /**Funds that are not yet available in the balance**/
    private List<BalanceItem> pending;
    /**Funds of reserved that are available to be transferred or paid out**/
    private List<BalanceItem> availableReserved;
    /**Funds of reserved that are not yet available in the balance**/
    private List<BalanceItem> pendingReserved;

    public static Balance retrieve() throws MoneyCollectException {
        return retrieve((Map<String, Object>) null, (RequestOptions) null);
    }

    public static Balance retrieve(RequestOptions options) throws MoneyCollectException {
        return retrieve((Map<String, Object>) null, options);
    }

    public static Balance retrieve(Map<String, Object> params, RequestOptions options)
            throws MoneyCollectException {
        String url = String.format("%s%s", MoneyCollect.getApiBase(), "/v1/balance");
        return ApiResource.request(ApiResource.RequestMethod.GET, url, params, Balance.class, options);
    }
}
