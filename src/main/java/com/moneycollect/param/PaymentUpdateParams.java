package com.moneycollect.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.moneycollect.net.RequestParams;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(setterPrefix = "set")
public class PaymentUpdateParams extends RequestParams {
     
    /** ID of the payment method used with this Payment. **/
    private String paymentMethod;

    /** Payment amount **/
    private Long amount;

    /**Three-letter ISO currency code**/
    private String currency;

    /**ID of the Customer this Payment belongs to, if one exists**/
    private String customerId;


    /**An arbitrary string. Useful for displaying to users.**/
    private String description;

    /**this is the email address that the receipt for this charge was sent to.**/
    private String receiptEmail;


    /**'on' or 'off' , default is 'off' . If 'on' ,customer will be attached the payment **/
    private SetupFutureUsage setupFutureUsage;

    /**Notify this payment result**/
    private String notifyUrl ;

    /**The URL to redirect your customer back to after authenticate or pay their payment**/
    private String returnUrl;

    /**This Payment from**/
    private String website;

    /**The ip of consumer**/
    private String ip;

    /**Shipping information for this Payment**/
    private Shipping shipping;

    /** If the value is true or false, the original value will be updated; if gives the other values(include null) the original value will not be updated */
    private Boolean use3D;

    public enum SetupFutureUsage implements RequestParams.EnumParam {
        @JsonProperty("off")
        OFF("off"),

        @JsonProperty("on")
        ON("on_session");

        @Getter(onMethod = @__({@Override}))
        private final String value;

        SetupFutureUsage(String value) {
            this.value = value;
        }
    }

    @Getter
    @Builder(setterPrefix = "set")
    public static class Shipping  {

        /**name="firstName",value = "Customer first name."**/
        private String firstName;

        /**name="lastName",value = "Customer last name."**/
        private String lastName;

        /**name="phone",value = "Customer phone."**/
        private String phone;

        /**name="address",value = "Customer shipping address."**/
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
