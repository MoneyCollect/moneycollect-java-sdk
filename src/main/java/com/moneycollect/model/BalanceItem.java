package com.moneycollect.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode(callSuper = false)
public class BalanceItem {
   /** Balance amount **/
    private Long amount;
    /** Three-letter ISO currency code **/
    private String currency;
}
