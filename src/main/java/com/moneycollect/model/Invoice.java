package com.moneycollect.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.moneycollect.MoneyCollect;
import com.moneycollect.exception.InvalidRequestException;
import com.moneycollect.exception.MoneyCollectException;
import com.moneycollect.net.ApiResource;
import com.moneycollect.net.RequestOptions;
import com.moneycollect.param.InvoiceCreateParams;
import com.moneycollect.param.InvoiceListParams;
import com.moneycollect.param.InvoicePayParams;
import com.moneycollect.param.InvoiceUpdateParams;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Map;
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public class Invoice extends ApiResource {
    /** Unique identifier for the object. */
    private String id;
    /** The amount, in cents, that was paid. */
    private Long amountPaid;
    /** Number of payment attempts made for this invoice, from the perspective of the payment retry schedule. Any payment attempt counts as the first attempt, and subsequently only automatic retries increment the attempt count. In other words, manual payment attempts after the first attempt do not affect the retry schedule. */
    private Long attemptCount;
    /** Indicates the reason why the invoice was created. subscription_cycle indicates an invoice created by a subscription advancing into a new period. subscription_create indicates an invoice created due to creating a subscription. subscription_update indicates an invoice created due to updating a subscription. subscription is set for all old invoices to indicate either a change to a subscription or a period advancement. manual is set for all invoices unrelated to a subscription (for example: created via the invoice editor). The upcoming value is reserved for simulated invoices per the upcoming invoice endpoint. subscription_threshold indicates an invoice created due to a billing threshold being reached. */
    private String billingReason;
    /** Either charge_automatically, or send_invoice. When charging automatically, MoneyCollect will attempt to pay this invoice using the default source attached to the customer. When sending an invoice, MoneyCollect will email this invoice to the customer with payment instructions.*/
    private String collectionMethod;
    /** Time at which the object was created.*/
    private Date created;
    /** The amount, in cents */
    private Long amountDue;
    /** Three-letter ISO currency code, in lowercase. Must be a supported currency. */
    private String currency;
    /** A list of up to 4 custom fields to be displayed on the invoice. If a value for custom_fields is specified, the list specified will replace the existing custom field list on this invoice. Pass an empty string to remove previously-defined fields.*/
    private List<InvoiceCustomFields> customFields;
    /** customer*/
    private InvoiceCustomer customer;
    /** customer email */
    private String customerEmail;
    /** The ID of the customer who will be billed. */
    private String customerId;
    /** ID of the default payment method for the invoice. It must belong to the customer associated with the invoice. If not set, defaults to the subscription’s default payment method, if any, or to the default payment method in the customer’s invoice settings. */
    private String defaultPaymentMethodId;
    /** An arbitrary string attached to the object. Often useful for displaying to users. Referenced as ‘memo’ in the Dashboard. */
    private String description;
    /** The date on which payment for this invoice is due. This value will be null for invoices where collectionMethod=charge_automatically. */
    private Date dueDate;
    /** Footer displayed on the invoice. */
    private String footer;
    /** The URL for the hosted invoice page, which allows customers to view and pay an invoice. If the invoice has not been finalized yet, this will be null. */
    private String hostedInvoiceUrl;
    /** The link to download the PDF for the invoice. If the invoice has not been finalized yet, this will be null. */
    private String invoicePdf;

    /** The link to download the PDF for the Receipt. If the invoice has not been paid yet, this will be null. */
    private String invoiceReceiptPdf;

    /** For some errors that could be handled programmatically, a short string indicating the error code reported. */
    private LastError lastError;

    /** A unique, identifying string that appears on emails sent to the customer for this invoice. */
    private String number;
    /** The Payment associated with this invoice. The Payment is generated when the invoice is finalized, and can then be used to pay the invoice. Note that voiding an invoice will cancel the Payment. */
    private String paymentId;
    /** Configuration settings for the Payment that is generated when the invoice is finalized. */
    private InvoicePaymentSettings paymentSettings;
    /** Extra information about an invoice for the customer’s credit card statement. */
    private String statementDescriptor;
    /** The status of the invoice, one of draft, open, post_due, paid, uncollectible, or void. */
    private String status;
    /** The time that the invoice draft was finalized. */
    private Date finalizedAt;
    /** The time that the invoice was marked uncollectible. */
    private Date markedUncollectibleAt;
    /** The time that the invoice was paid. */
    private Date paidAt;
    /** The time that the invoice was voided. */
    private Date voidedAt;
    /** Start of the usage period. */
    private Date periodStart;
    /** End of the usage period. */
    private Date periodEnd;
    /** Total of all invoice items. */
    private Long subtotal;
    /** Set of key-value pairs that you can attach to an object. This can be useful for storing additional information about the object in a structured format. */
    private Map<String,String> metadata;
    /** The individual line items that make up the invoice. lines is sorted as follows:invoice items in reverse chronological order, followed by the subscription, if any. */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<InvoiceItem> items;

    /** The Payment associated with this invoice. The Payment is generated when the invoice is finalized, and can then be used to pay the invoice. Note that voiding an invoice will cancel the Payment. */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Payment payment;
    /** Mark if the invoice had past due, if the status is open and the dueDate had past then true, else false. */
    private Boolean pastDue;

    @Data
    public static class LastError{
        /** For some errors that could be handled programmatically, a short string indicating the error code reported. */
        private String code;
        /** A human-readable message providing more details about the error. For card errors, these messages can be shown to your users. */
        private String message;
        /** If the error is payment id. */
        private String paymentId;

    }
    @Data
    public static class InvoiceCustomFields {
        /** The name of the custom field. This may be up to 30 characters.*/
        private String name;
        /** The value of the custom field. This may be up to 30 characters.*/
        private String value;
    }
    @Data
    public static class InvoiceCustomer {

        /** An arbitrary string attached to the object. Often useful for displaying to users. */
        private String description;

        /** The customer’s first name or business name. */
        private String firstName;

        /** The customer’s last name or business name. */
        private String lastName;

        /** account email */
        private String email;

        /** Customer shipping details */
        private InvoiceCustomerShipping shippingDetails;

        /** Customer billing details */
        private InvoiceCustomerBilling billingDetails;

        @Data
        public static class InvoiceCustomerShipping{
            /** Customer first name. */
            private String firstName;

            /** Customer last name. */
            private String lastName;

            /** Customer phone. */
            private String phone;

            /** The customer’s shipping address. */
            private InvoiceCustomerAddress address;
        }

        @Data
        public static class InvoiceCustomerBilling {
            /** Email */
            private String email;

            /** Phone */
            private String phone;

            /** The customer’s billing address. */
            private InvoiceCustomerAddress address;
        }

        @Data
        public static class InvoiceCustomerAddress{
            /** City, district, suburb, town, or village. */
            private String city;

            /** Two-letter country code. */
            private String country;

            /** Address line 1. */
            private String line1;

            /** Address line 2. */
            private String line2;

            /** ZIP or postal code. */
            private String postalCode;

            /** State, county, province, or region. */
            private String state;
        }

    }

    @Data
    public static class InvoicePaymentSettings {
        /** The list of payment method types (e.g. card) to provide to the invoice’s Payment. */
        private List<String> paymentMethodTypes;
    }

    @Data
    public static class InvoiceItem {
        /** Unique identifier for the object. */
        private String id;
        /** The invoiceId */
        private String invoiceId;
        /** Amount (in the currency specified) of the invoice item. This should always be equal to unit_amount * quantity. */
        private Long amount;
        /** Three-letter ISO currency code, in lowercase. Must be a supported currency. */
        private String currency;
        /** Time at which the object was created. */
        private Date created;
        /** An arbitrary string attached to the object. Often useful for displaying to users. */
        private String description;
        /** The priceId of the invoice item. */
        private String priceId;
        /** Quantity of units for the invoice item. If the invoice item is a proration, the quantity of the subscription that the proration was computed for. */
        private Integer quantity;
        /** Describe the composition of the amount, when the price model is ’graduated’ or ’volume’ */
        private List<InvoiceItemAmountDetails> amountDetails;

        @Data
        public static class InvoiceItemAmountDetails {

            /** Description */
            private String description;

            /** Quantity in tiers  */
            private Integer quantity;

            /** Unit amount in tiers  */
            private Long unitAmount;

            /** Total amount in tiers  */
            private Long amount;
        }
    }

    @Data
    public static class InvoiceDeletedResult{
        private String id;
        private Boolean deleted;
    }

    public static InvoiceCollection list(InvoiceListParams params, RequestOptions options) throws MoneyCollectException {
        String url = String.format("%s%s", MoneyCollect.getApiBase(), "/v1/invoices");
        return ApiResource.requestCollection(url, params, InvoiceCollection.class, options);
    }

    public static InvoiceCollection list(InvoiceListParams params) throws MoneyCollectException {
        return list(params,null);
    }

    public static Invoice create(InvoiceCreateParams params, RequestOptions options) throws MoneyCollectException {
        String url=String.format("%s%s", MoneyCollect.getApiBase(), "/v1/invoices/create");
        return ApiResource.request(ApiResource.RequestMethod.POST, url, params, Invoice.class, options);
    }

    public static Invoice create(InvoiceCreateParams params) throws MoneyCollectException {
        return create(params, null);
    }

    public static Invoice retrieve(String invoiceId,RequestOptions options) throws MoneyCollectException {
        String url = String.format(
                "%s%s",
                MoneyCollect.getApiBase(),
                String.format("/v1/invoices/%s", ApiResource.urlEncodeId(invoiceId)));
        return ApiResource.request(ApiResource.RequestMethod.GET, url, (Map<String, Object>) null, Invoice.class, options);
    }

    public static Invoice retrieve(String invoiceId) throws MoneyCollectException {
        return retrieve(invoiceId,null);
    }

    public static InvoiceDeletedResult deleteDraftInvoice(String invoiceId,RequestOptions options) throws MoneyCollectException {
        String url = String.format(
                "%s%s",
                MoneyCollect.getApiBase(),
                String.format("/v1/invoices/%s", ApiResource.urlEncodeId(invoiceId)));
        return ApiResource.request(ApiResource.RequestMethod.DELETE, url, (Map<String, Object>) null, InvoiceDeletedResult.class, options);
    }

    public static InvoiceDeletedResult deleteDraftInvoice(String invoiceId) throws MoneyCollectException {
        return deleteDraftInvoice(invoiceId,null);
    }

    public static Invoice finalizeInvoice(String invoiceId,RequestOptions options) throws MoneyCollectException {
        String url = String.format(
                "%s%s",
                MoneyCollect.getApiBase(),
                String.format("/v1/invoices/%s/finalize", ApiResource.urlEncodeId(invoiceId)));
        return ApiResource.request(ApiResource.RequestMethod.POST, url, (Map<String, Object>) null, Invoice.class, options);
    }

    public static Invoice finalizeInvoice(String invoiceId) throws MoneyCollectException {
        return finalizeInvoice(invoiceId, null);
    }

    public static Invoice payInvoice(String invoiceId, InvoicePayParams params,RequestOptions options) throws MoneyCollectException {
        String url = String.format(
                "%s%s",
                MoneyCollect.getApiBase(),
                String.format("/v1/invoices/%s/pay", ApiResource.urlEncodeId(invoiceId)));
        return ApiResource.request(ApiResource.RequestMethod.POST, url, params, Invoice.class, options);
    }

    public static Invoice payInvoice(String invoiceId, InvoicePayParams params) throws MoneyCollectException {
        return payInvoice(invoiceId, params,null);
    }

    public static Invoice send(String invoiceId,RequestOptions options) throws MoneyCollectException {
        String url = String.format(
                "%s%s",
                MoneyCollect.getApiBase(),
                String.format("/v1/invoices/%s/send", ApiResource.urlEncodeId(invoiceId)));
        return ApiResource.request(ApiResource.RequestMethod.POST, url, (Map<String, Object>) null, Invoice.class, options);
    }

    public static Invoice send(String invoiceId) throws MoneyCollectException {
        return send(invoiceId, null);
    }


    public static Invoice uncollectible(String invoiceId,RequestOptions options) throws MoneyCollectException {
        String url = String.format(
                "%s%s",
                MoneyCollect.getApiBase(),
                String.format("/v1/invoices/%s/uncollectible", ApiResource.urlEncodeId(invoiceId)));
        return ApiResource.request(ApiResource.RequestMethod.POST, url, (Map<String, Object>) null, Invoice.class, options);
    }

    public static Invoice uncollectible(String invoiceId) throws MoneyCollectException {
        return uncollectible(invoiceId,null);
    }

    public static Invoice update(String invoiceId, InvoiceUpdateParams params,RequestOptions options) throws MoneyCollectException {
        String url = String.format(
                "%s%s",
                MoneyCollect.getApiBase(),
                String.format("/v1/invoices/%s/update", ApiResource.urlEncodeId(invoiceId)));
        return ApiResource.request(ApiResource.RequestMethod.POST, url, params, Invoice.class, options);
    }

    public static Invoice update(String invoiceId, InvoiceUpdateParams params) throws MoneyCollectException {
        return update(invoiceId, params, null);
    }

    public static Invoice voidInvoice(String invoiceId,RequestOptions options) throws MoneyCollectException {
        String url = String.format(
                "%s%s",
                MoneyCollect.getApiBase(),
                String.format("/v1/invoices/%s/void", ApiResource.urlEncodeId(invoiceId)));
        return ApiResource.request(ApiResource.RequestMethod.POST, url, (Map<String, Object>) null, Invoice.class, options);
    }

    public static Invoice voidInvoice(String invoiceId) throws MoneyCollectException {
        return voidInvoice(invoiceId, null);
    }





}
