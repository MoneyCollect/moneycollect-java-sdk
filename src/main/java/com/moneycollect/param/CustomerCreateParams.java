package com.moneycollect.param;

import com.moneycollect.net.RequestParams;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Getter
@Builder(setterPrefix = "set")
public class CustomerCreateParams extends RequestParams {

    /** The customer’s email address.**/
    private String email;

    /** name="firstName",value = "The customer’s first name name or business name.**/
    private String firstName;

    /** The customer’s last name name or business name.**/
    private String lastName;

    /** The customer’s phone number.**/
    private String phone;

    /** An arbitrary string attached to the object. Often useful for displaying to users.**/
    private String description;

    /** The customer’s address.**/
    private Address address;

    /** The customer’s shipping.**/
    private Shipping shipping;

    private CustomerCreateParams(String email, String firstName, String lastName, String phone, String description, Address address, Shipping shipping) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.description = description;
        this.address = address;
        this.shipping = shipping;
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

}
