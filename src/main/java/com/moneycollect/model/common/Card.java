package com.moneycollect.model.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Card {

    /** Card brand. Can be amex, diners, discover, jcb, mastercard, unionpay, visa, or unknown.**/
    private String brand;

    /**Two-letter ISO code representing the country of the card. You could use this attribute to get a sense of the international breakdown of cards you’ve collected.**/
    private String country;

    /**Two-digit number representing the card’s expiration month.**/
    private String expMonth;

    /**Four-digit number representing the card’s expiration year.**/
    private String expYear;

    /**Uniquely identifies this particular card number.**/
    private String fingerprint;

    /**The last four digits of the card.**/
    private String last4;

}