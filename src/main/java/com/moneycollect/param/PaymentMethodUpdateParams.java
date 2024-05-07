package com.moneycollect.param;

import com.moneycollect.model.common.Address;
import com.moneycollect.net.RequestParams;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Builder(setterPrefix = "set")
@EqualsAndHashCode(callSuper = false)
public class PaymentMethodUpdateParams extends RequestParams {
    private static final long serialVersionUID = 1L;

    private PaymentMethodBilling billingDetails;

    private PaymentMethodCardUpdate card;

    @Getter
    @Builder(setterPrefix = "set")
    public static class PaymentMethodBilling implements Serializable {
        private static final long serialVersionUID = 1L;

        /** First name */
        private String firstName;

        /** Last name */
        private String lastName;

        /** Billing phone number (including extension). */
        private String phone;

        /** "Email address. */
        private String email;

        /** Billing address. */
        private Address address;

    }

    @Getter
    @Builder(setterPrefix = "set")
    public static class PaymentMethodCardUpdate implements Serializable {
        private static final long serialVersionUID = 1L;

        /** Two-digit number representing the card’s expiration month. */
        private String expMonth;

        /** Four-digit number representing the card’s expiration year. */
        private String expYear;
    }
}
