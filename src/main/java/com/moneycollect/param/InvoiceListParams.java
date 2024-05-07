package com.moneycollect.param;

import com.moneycollect.net.RequestParams;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;
@Getter
@Builder(setterPrefix = "set" )
public class InvoiceListParams extends RequestParams {
    /**A limit on the number of objects to be returned. Limit can range between 1 and 100, and the default is 10.**/
    private Integer limit;

    /** created.gt,created.gte,created.lt or created.lte",example = "2021-02-01T05:12:12.000Z" **/
    private Operated created;
    /**A cursor for use in pagination. startingAfter is an object ID that defines your place in the list. **/
    private String startingAfter;
    /**A cursor for use in pagination. endingBefore is an object ID that defines your place in the list. **/
    private String endingBefore;

    /** The collection method of the invoice to retrieve. Either charge_automatically or send_invoice.*/
    private String collectionMethod;

    /** Only return invoices for the subscription specified by this subscription ID. */
    private String subscription;

    /** The status of the invoice, one of draft, open, past_due, paid, uncollectible, or void.*/
    private String status;

    /** Only return invoices for the customer specified by this customer ID. */
    private String customer;

    /** dueDate.gt,dueDate.gte,dueDate.lt or dueDate.lte */
    private Operated dueDate;

    @Getter
    @Builder(setterPrefix = "set")
    static class Operated {
        private Date gt;
        private Date gte;
        private Date lt;
        private Date lte;
    }
}
