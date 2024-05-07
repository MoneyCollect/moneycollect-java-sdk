package com.moneycollect.param;

import com.moneycollect.model.Invoice;
import com.moneycollect.net.RequestParams;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.Date;
import java.util.List;
@Getter
@Builder(setterPrefix = "set")
public class InvoiceUpdateParams extends RequestParams {
    /** Either charge_automatically, or send_invoice. When charging automatically, MoneyCollect will attempt to pay this invoice using the default source attached to the customer. When sending an invoice, MoneyCollect will email this invoice to the customer with payment instructions. */
    private String collectionMethod;
    //    @ApiModelProperty(value = "The ID of the customer who will be billed.")
//    private String customerId;
    /** An arbitrary string attached to the object. Often useful for displaying to users. Referenced as ‘memo’ in the Dashboard. */
    private String description;
    /** A list of up to 4 custom fields to be displayed on the invoice. If a value for custom_fields is specified, the list specified will replace the existing custom field list on this invoice. Pass an empty string to remove previously-defined fields. */
    private List<Invoice.InvoiceCustomFields> customFields;
    /** ID of the default payment method for the invoice. It must belong to the customer associated with the invoice. If not set, defaults to the subscription’s default payment method, if any, or to the default payment method in the customer’s invoice settings. */
    private String defaultPaymentMethodId;
    /** The date on which payment for this invoice is due. This value will be null for invoices where collectionMethod=charge_automatically. */
    private Date dueDate;
    /** Footer displayed on the invoice. */
    private String footer;
    /** Configuration settings for the Payment that is generated when the invoice is finalized. */
    private Invoice.InvoicePaymentSettings paymentSettings;
    /** Extra information about an invoice for the customer’s credit card statement. */
    private String statementDescriptor;
}
