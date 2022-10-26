package com.moneycollect.param;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.moneycollect.model.common.LineItem;
import com.moneycollect.net.RequestParams;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder(setterPrefix = "set")
public class PaymentCreateParams  extends RequestParams {

    /** "Payment amount**/
    private Long amount;

    /** "Three-letter ISO currency code**/
    private String currency;

    /**  automatic
     *  <br/>(Default) Payment can be confirmed using a publishable key. After next_actions are handled, no additional confirmation is required to complete the payment.
     *   manual
     *  <br/>All payment attempts must be made using a secret key. The Payment returns to the requires_confirmation state after handling next_action, and requires your server to initiate each payment attempt with an explicit confirmation.
     *  **/
    @JsonProperty("confirmationMethod")
    private ConfirmationMethod confirmationMethod;

    /**When enabled, this Payment will accept payment methods that you have enabled in the Dashboard and are compatible with this Payment’s other parameters.**/
    private AutomaticPaymentMethods automaticPaymentMethods;

    /**Set to true to attempt to confirm this PaymentIntent immediately. This parameter defaults to false. When creating and confirming a PaymentIntent at the same time, parameters available in the confirm API may also be provided.**/
    private Boolean confirm ;

    /**The list of payment method types (e.g. card) that this Payment is allowed to use. **/
    private List<String> paymentMethodTypes;

    /**
     * ID of the Customer this PaymentIntent belongs to, if one exists.
     * Payment methods attached to other Customers cannot be used with this PaymentIntent.
     *
     * If present in combination with setup_future_usage,
     * this PaymentIntent’s payment method
     *  will be attached to the Customer after the PaymentIntent has been confirmed and any required actions from the user are complete.
     */
    /** "ID of the Customer this Payment belongs to, if one exists**/
    private String customerId;

    /** "An arbitrary string. Useful for displaying to users.**/
    private String description;


    /** "ID of the payment method used with this Payment.**/
    private String paymentMethod;


    /** "this is the email address that the receipt for this charge was sent to.**/
    private String receiptEmail;

    /**'on' or 'off' , default is 'off' . If 'on' ,customer will be attached the payment **/
    @JsonProperty("setupFutureUsage")
    private SetupFutureUsage setupFutureUsage;


    /** "Statement descriptor suffix , Must contain at least two letter,maximum 10 characters.**/
    private String statementDescriptorSuffix;

    /** "order no",name="orderNo**/
    private String orderNo;

    /** "Notify this payment result**/
    private String notifyUrl ;

    /**
     * ONLY WHEN CONFIRM=TRUE
     * The URL to redirect your customer back to after they authenticate or cancel their payment on the payment method’s app or site.
     * If you’d prefer to redirect to a mobile application, you can alternatively supply an application URI scheme. This parameter can only be used with
     * confirm=true.
     */
    /** "The URL to redirect your customer back to after authenticate or pay their payment**/
    private String returnUrl;

    /** "This Payment from**/
    private String website;

    /** "The ip of consumer**/
    private String ip;

    /** "The ip of consumer**/
    private String userAgent;

    /** "Controls when the funds will be captured from the customer’s account.**/
    @JsonProperty("preAuth")
    private PreAuth preAuth;

    /**
     *For non-card charges, you can use this value as the complete description that appears on your customers’ statements.
     * Must contain at least one letter, maximum 22 characters.
     */
    /** "Statement descriptor,Must contain at least two letter,maximum 22 characters.**/
    private String statementDescriptor;

    /**Client source channel(WEB, H5, APP, MINI).**/
    private String  fromChannel;

    /** "Shipping information for this Payment**/
    private Shipping shipping;

    /** "A list of items the customer is purchasing**/
    private List<LineItem> lineItems;

    /** "Recurring payment**/
    private Recurring recurring;

    /**When fromChannel=APP,appScheme is required.**/
    private String  appScheme;

    /** Third party open platform's user id, such as wechat user's openId. when use wechat officeAccount or MiniProgram pay, it is must.**/
    private String  openUserId;

    @Getter
    @Builder(setterPrefix = "set")
    public static class AutomaticPaymentMethods{
        private Boolean enabled;
    }

    public enum ConfirmationMethod implements RequestParams.EnumParam {
        @JsonProperty(value="automatic")
        AUTOMATIC("automatic"),

        @JsonProperty(value="manual")
        MANUAL("manual");

        @Getter(onMethod = @__({@Override}))
        private final String value;

        ConfirmationMethod(String value) {
            this.value = value;
        }
    }

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

    public enum PreAuth implements RequestParams.EnumParam {

        @JsonProperty("y")
        YES("y"),

        @JsonProperty("n")
        NO("n");

        @Getter(onMethod = @__({@Override}))
        private final String value;

        PreAuth(String value) {
            this.value = value;
        }
    }

    @Getter
    @Builder(setterPrefix = "set")
    public static class Shipping   {

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

    /**
     * 	Set for payment recurring
     */
    @Getter
    @Builder(setterPrefix = "set")
    public static class Recurring{
        /**name="initial",value="	If the first payment of recurring payment,set initial is true"**/
        public Boolean initial;
        /**name="relationPaymentId",value="If recurring payment,relationPaymentId must be the initial payment ID"**/
        public String relationPaymentId;
    }
}
