package com.moneycollect.model.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class BillingDetails {

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
