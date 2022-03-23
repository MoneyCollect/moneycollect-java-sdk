package com.moneycollect.param;

import com.moneycollect.net.RequestParams;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Builder(setterPrefix = "set")
public class CustomerListParams extends RequestParams {

    /** A case-sensitive filter on the list based on the customer’s email field. The value must be a string. **/
   private String email;
   /**A limit on the number of objects to be returned. Limit can range between 1 and 100, and the default is 10.**/
   private Integer limit;
   /** created.gt,created.gte,created.lt or created.lte",example = "2021-02-01T05:12:12.000Z" **/
   private Created created;

    /**A cursor for use in pagination. startingAfter is an object ID that defines your place in the list. **/
   private String startingAfter;
    /**A cursor for use in pagination. endingBefore is an object ID that defines your place in the list. **/
   private String endingBefore;


    private CustomerListParams(String email, Integer limit, Created created, String startingAfter, String endingBefore) {
        this.email = email;
        this.limit = limit;
        this.created = created;
        this.startingAfter = startingAfter;
        this.endingBefore = endingBefore;
    }

   @Getter
   @Builder(setterPrefix = "set")
   public static class Created {
       private Date gt;
       private Date gte;
       private Date lt;
       private Date lte;

       private Created(Date gt, Date gte, Date lt, Date lte) {
           this.gt = gt;
           this.gte = gte;
           this.lt = lt;
           this.lte = lte;
       }
   }


}
