package com.moneycollect.model.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LineItem {

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
