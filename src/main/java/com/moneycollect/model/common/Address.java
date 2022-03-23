package com.moneycollect.model.common;

import lombok.*;

@Getter
@Setter 
public class Address   {

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
