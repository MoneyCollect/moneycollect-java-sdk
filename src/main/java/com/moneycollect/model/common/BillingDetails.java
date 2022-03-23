package com.moneycollect.model.common;

import lombok.*;

@Getter
@Setter
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
