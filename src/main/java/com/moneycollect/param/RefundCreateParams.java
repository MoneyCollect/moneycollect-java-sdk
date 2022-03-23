package com.moneycollect.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.moneycollect.net.RequestParams;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder(setterPrefix = "set")
public class RefundCreateParams extends RequestParams {

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
    @JsonProperty("reason")
    private RefundReason reason;

    /** A note is required when a provided reason is 'other'.**/
    private String note;

    public  enum RefundReason implements RequestParams.EnumParam {

        @JsonProperty("duplicate")
        DUPLICATE("duplicate"),

        @JsonProperty("requested_by_customer")
        REQUESTED_BY_CUSTOMER( "requested_by_customer"),

        @JsonProperty("other")
        OTHER("other"),

        @JsonProperty("fraudulent")
        FRAUDULENT("fraudulent");

        @Getter(onMethod = @__({@Override}))
        private final String value;

        RefundReason(String value) {
            this.value = value;
        }
    }
}
