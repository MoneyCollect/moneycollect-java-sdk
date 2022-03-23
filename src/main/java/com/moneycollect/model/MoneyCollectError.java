// File generated from our OpenAPI spec
package com.moneycollect.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class MoneyCollectError {

  @JsonProperty(value="code")
  String code;

  /**
   * A human-readable message providing more details about the error. For card errors, these
   * messages can be shown to your users.
   */
  @JsonProperty(value="msg")
  String message;

  public MoneyCollectError(){

  }

  public MoneyCollectError(String code, String message){
   this.code = code;
   this.message = message;
  }

}
