package com.moneycollect.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.moneycollect.net.RequestParams;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder(setterPrefix = "set")
public class TransactionListParams extends RequestParams {
    /**Only returns transactions that were paid out on the specified payout ID.**/
    private String payout;

    /**The type of transaction**/
    private TransactionType type;
    /**
     * Name may set available.gt,or available.gte,or available.lt,or available.lte <br/>
     * example = "2021-02-01T05:12:12.000Z"
     * **/
    private Available available;

    /** A cursor for use in pagination. endingBefore is an object ID that defines your place in the list.**/
    private String currency;

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
    public static class Created {
        private Date gt;
        private Date gte;
        private Date lt;
        private Date lte;

        private Created(Date gt, Date gte, Date lt, Date lte) {
            this.gt = gt;
            this.gte = gte;
            this.lt = lt;
            this.lte = lte;
        }
    }

    @Getter
    @Builder(setterPrefix = "set")
    public static class Available {
        private Date gt;
        private Date gte;
        private Date lt;
        private Date lte;

        private Available(Date gt, Date gte, Date lt, Date lte) {
            this.gt = gt;
            this.gte = gte;
            this.lt = lt;
            this.lte = lte;
        }
    }

    public  enum TransactionType implements RequestParams.EnumParam {

        @JsonProperty("charge")
        CHARGE("charge"),

        @JsonProperty("charge_reversal")
        CHARGE_REVERSAL("charge_reversal"),

        @JsonProperty("refund")
        REFUND("refund"),

        @JsonProperty("refund_failed")
        REFUND_FAILED("refund_failed"),

        @JsonProperty("reserved")
        RESERVED("reserved"),

        @JsonProperty("reserved_release")
        RESERVED_RELEASE("reserved_release"),

        @JsonProperty("reserved_reversal")
        RESERVED_REVERSAL("reserved_reversal"),

        @JsonProperty("reserved_release_reversal")
        RESERVED_RELEASE_REVERSAL("reserved_release_reversal"),

        @JsonProperty("payout")
        PAYOUT("payout"),

        @JsonProperty("payout_failed")
        PAYOUT_FAILED("payout_failed"),

        @JsonProperty("payout_fee")
        PAYOUT_FEE("payout_fee"),

        @JsonProperty("adjustment")
        ADJUSTMENT("adjustment"),

        @JsonProperty("recharge")
        RECHARGE("recharge"),

        @JsonProperty("dispute")
        DISPUTE("dispute"),

        @JsonProperty("dispute_reversal")
        DISPUTE_REVERSAL("dispute_reversal");

        @Getter(onMethod = @__({@Override}))
        private final String value;

        TransactionType(String value) {
            this.value = value;
        }

    }
}
