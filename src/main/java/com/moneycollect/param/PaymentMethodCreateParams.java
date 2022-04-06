package com.moneycollect.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.moneycollect.net.RequestParams;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Builder(setterPrefix = "set")
@EqualsAndHashCode(callSuper = false)
public class PaymentMethodCreateParams  extends RequestParams {
    /** card **/
    @JsonProperty("type")
    private PaymentMethodType type;

    /** If this is a card PaymentMethod, this hash contains the user’s card details.**/
    private Card card;

     /** Billing information associated with the PaymentMethod that may be used or required by particular types of payment methods.**/
    private BillingDetails billingDetails;

    public enum PaymentMethodType implements RequestParams.EnumParam {
        @JsonProperty(value="card")
        CARD("card"),

        @JsonProperty(value="kakao_pay")
        KAKAO_PAY("kakao_pay"),

        @JsonProperty(value="klarna")
        Klarna("klarna"),

        @JsonProperty(value="poli")
        POLi("poli"),

        @JsonProperty(value="MyBank")
        MyBank("mybank"),

        @JsonProperty(value="eps")
        EPS("eps"),

        @JsonProperty(value="przelewy24")
        Przelewy24("przelewy24"),

        @JsonProperty(value="bancontact")
        Bancontact("bancontact"),

        @JsonProperty(value="ideal")
        ldeal("ideal"),

        @JsonProperty(value="giropay")
        Giropay("giropay"),

        @JsonProperty(value="sorfort")
        Sorfort("sorfort"),

        @JsonProperty(value="alipay_hk")
        ALIPAY_HK("alipay_hk"),

        @JsonProperty(value="wechat_pay_hk")
        WECHAT_PAY_HK("wechat_pay_hk");

        @Getter(onMethod = @__({@Override}))
        private final String value;

        PaymentMethodType(String value) {
            this.value = value;
        }
    }

    @Getter
    @Builder(setterPrefix = "set")
    @EqualsAndHashCode(callSuper = false)
    public static class Card implements Serializable {
        /** cardNo **/
        private String cardNo;

        /**securityCode**/
        private String securityCode ;

        /**Two-digit number representing the card’s expiration month.**/
        private String expMonth;

        /**Four-digit number representing the card’s expiration year.**/
        private String expYear;

    }

    @Getter
    @Builder(setterPrefix = "set")
    public static class BillingDetails implements Serializable {

        /** First name **/
        private String firstName;

        /**Last name**/
        private String lastName;

        /** Billing phone number (including extension). **/
        private String phone;

        /** Email address. **/
        private String email;

        /** Billing address. **/
        private Address address;

    }

    @Getter
    @Builder(setterPrefix = "set")
    public static class Address   {

        /**name="city",value="City, district, suburb, town, or village."**/
        private String city;

        /**name="country",value="Two-letter country code."**/
        private String country;

        /**name="line1",value="Address line 1."**/
        private String line1;

        /**name="line2",value="Address line 2."**/
        private String line2;

        /**name="postalCode",value="ZIP or postal code."**/
        private String postalCode;

        /**name="state",value="State, county, province, or region."**/
        private String state;
    }
}
