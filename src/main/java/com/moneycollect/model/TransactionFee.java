package com.moneycollect.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode(callSuper = false)

public class TransactionFee {

    /**Amount of the fee, in cents.**/
    private Long amount;
    /**Three-letter ISO currency code**/
    private String currency;
    /**An arbitrary string attached to the object.**/
    private String description;
    /**Types:mdr、single_fee、charge_back_fee、pay_out_fee**/
    private String type;
}
