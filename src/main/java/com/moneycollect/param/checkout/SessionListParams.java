package com.moneycollect.param.checkout;

import com.moneycollect.net.RequestParams;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder(setterPrefix = "set")
public class SessionListParams extends RequestParams {

    /**Only return the Checkout Session for the payment specified.payment is an object ID **/
    private String payment;
    /**A limit on the number of objects to be returned. Limit can range between 1 and 100, and the default is 10.**/
    private Integer limit;
    /** created.gt,created.gte,created.lt or created.lte",example = "2021-02-01T05:12:12.000Z" **/
    private Created created;
    /**A cursor for use in pagination. startingAfter is an object ID that defines your place in the list. **/
    private String startingAfter;
    /**A cursor for use in pagination. endingBefore is an object ID that defines your place in the list. **/
    private String endingBefore;

    @Getter
    @Builder(setterPrefix = "set")
    static class Created {
        private Date gt;
        private Date gte;
        private Date lt;
        private Date lte;
    }
}
