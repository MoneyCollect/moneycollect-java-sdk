package com.moneycollect.model.common;

import com.moneycollect.model.common.Address;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class Shipping  implements Serializable {

    /**name="firstName",value = "Customer first name."**/
    private String firstName;

    /**name="lastName",value = "Customer last name."**/
    private String lastName;

    /**name="phone",value = "Customer phone."**/
    private String phone;

    /**name="address",value = "Customer shipping address."**/
    private Address address;

}
