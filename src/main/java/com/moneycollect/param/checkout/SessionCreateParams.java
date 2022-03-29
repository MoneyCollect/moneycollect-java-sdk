package com.moneycollect.param.checkout;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.moneycollect.net.RequestParams;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@Getter
@Builder(setterPrefix = "set")
public class SessionCreateParams extends RequestParams {

   /**Total of all items after discounts and taxes are applied.**/
    private Long amountTotal;


   /**Three-letter ISO currency code**/
    private String currency;

   /**A unique string to reference the Checkout Session**/
    private String clientReferenceId;

   /**If not provided, Checkout will be asked to enter their email address.**/
    private String customerEmail;


   /**ID of an existing Customer, if one exists**/
    private String customer;


   /**The URL the customer will be directed to if they decide to cancel payment and return to your website.**/
    private String cancelUrl;


   /**The URL should send customers when payment is complete.**/
    private String returnUrl;

   /**The URL should notify when payment is complete.**/
    private String notifyUrl;

   /**orderNo.**/
    private String orderNo;

   /**website.**/
    private String website;
 

   /**submitType can only be specified on Checkout Sessions in payment mode**/
    private String submitType;


   /**A list of items the customer is purchasing**/
    private List<LineItem> lineItems;

   /**Is pre-authorize , 'y' OR 'n' **/
    private PreAuth preAuth;

    /**The list of payment method types (e.g. card) that this Payment is allowed to use. **/
    private List<String> paymentMethodTypes;

   /**Billing details . If not provide , Checkout should collect the customer’s billing address. **/
    private BillingDetails billingDetails;

    /**
     * 货运地址
     */
   /**Shipping address**/
    private Shipping shipping;

    /**
     *For non-card charges, you can use this value as the complete description that appears on your customers’ statements.
     * Must contain at least one letter, maximum 22 characters.
     */
   /**Statement descriptor,Must contain at least two letter,maximum 22 characters.**/
    private String statementDescriptor;

   /** Statement descriptor suffix , Must contain at least two letter,maximum 10 characters.**/
    private String statementDescriptorSuffix;

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
    public static class Shipping  implements Serializable {

        /**name="firstName",value = "Customer first name."**/
        private String firstName;

        /**name="lastName",value = "Customer last name."**/
        private String lastName;

        /**name="phone",value = "Customer phone."**/
        private String phone;

        /**name="address",value = "Customer shipping address."**/
        private Address address;

        private Shipping(String firstName, String lastName, String phone, Address address) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.phone = phone;
            this.address = address;
        }

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

        public Address(String city, String country, String line1, String line2, String postalCode, String state) {
            this.city = city;
            this.country = country;
            this.line1 = line1;
            this.line2 = line2;
            this.postalCode = postalCode;
            this.state = state;
        }
    }

    @Getter
    @Builder(setterPrefix = "set")
    public static class BillingDetails {

        /** firstName **/
        private String firstName;

        /** lastName **/
        private String lastName;

        /** phone**/
        private String phone;

        /** email**/
        private String email;

        /** address**/
        private Address address;
    }

    @Getter
    @Builder(setterPrefix = "set")
    public static class LineItem {

        /**The product’s name, meant to be displayable to the customer. **/
        private String name;

        /**A list of up to 5 URLs of images for this line item, meant to be displayable to the customer.**/
        private List<String> images;

        /**The amount to be collected per unit of the line item.**/
        private Long amount;

        /**Three-letter ISO currency code.**/
        private String currency;

        /**The quantity of the line item being purchased.**/
        private Integer quantity;

        /**The description for the line item, to be displayed on the Checkout page.**/
        private String description;
    }


}
