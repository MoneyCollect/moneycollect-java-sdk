package com.moneycollect.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BalanceItem {
   /** Balance amount **/
    private Long amount;
    /** Three-letter ISO currency code **/
    private String currency;
}
