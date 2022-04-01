package com.moneycollect.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.moneycollect.MoneyCollect;
import com.moneycollect.exception.MoneyCollectException;
import com.moneycollect.model.common.Address;
import com.moneycollect.model.common.Shipping;
import com.moneycollect.net.ApiResource;
import com.moneycollect.net.RequestOptions;
import com.moneycollect.param.CustomerCreateParams;
import com.moneycollect.param.CustomerListParams;
import com.moneycollect.param.CustomerUpdateParams;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Map;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer  extends ApiResource {

    private String id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String description;
    private Address address;
    private Shipping shipping;
    private Date created;

    /** Creates a new customer object. */
    public static Customer create(CustomerCreateParams params) throws MoneyCollectException {
        return create(params, (RequestOptions) null);
    }

    /** Creates a new customer object. */
    public static Customer create(CustomerCreateParams params, RequestOptions options)  throws MoneyCollectException {
        String url = String.format("%s%s", MoneyCollect.getApiBase(), "/v1/customers/create");
        return ApiResource.request(ApiResource.RequestMethod.POST,url,params,Customer.class,options);
    }
    /**
     * Returns a list of your customers. The customers are returned sorted by creation date, with the
     * most recent customers appearing first.
     */
    public static CustomerCollection list(CustomerListParams params) throws MoneyCollectException {
        return list(params, (RequestOptions) null);
    }

    /**
     * Returns a list of your customers. The customers are returned sorted by creation date, with the
     * most recent customers appearing first.
     */
    public static CustomerCollection list(CustomerListParams params, RequestOptions options)
            throws MoneyCollectException {
        String url = String.format("%s%s", MoneyCollect.getApiBase(), "/v1/customers");
        return ApiResource.requestCollection(url, params, CustomerCollection.class, options);
    }


    /**
     * Retrieves the details of an existing customer. You need only supply the unique customer
     * identifier that was returned upon customer creation.
     */
    public static Customer retrieve(String customer) throws MoneyCollectException {
        return retrieve(customer, (Map<String, Object>) null, (RequestOptions) null);
    }

    /**
     * Retrieves the details of an existing customer. You need only supply the unique customer
     * identifier that was returned upon customer creation.
     */
    public static Customer retrieve(String customer, RequestOptions options) throws MoneyCollectException {
        return retrieve(customer, (Map<String, Object>) null, options);
    }

    /**
     * Retrieves the details of an existing customer. You need only supply the unique customer
     * identifier that was returned upon customer creation.
     */
    public static Customer retrieve(
            String customer, Map<String, Object> params, RequestOptions options) throws MoneyCollectException {
        String url =
                String.format(
                        "%s%s",
                        MoneyCollect.getApiBase(),
                        String.format("/v1/customers/%s", ApiResource.urlEncodeId(customer)));
        return ApiResource.request(ApiResource.RequestMethod.GET, url, params, Customer.class, options);
    }

    /**
     * Permanently deletes a customer. It cannot be undone. Also immediately cancels any active
     * subscriptions on the customer.
     */
    public static void delete(String customerId) throws MoneyCollectException {
          delete(customerId, (RequestOptions) null);
    }


    /**
     * Permanently deletes a customer. It cannot be undone. Also immediately cancels any active
     * subscriptions on the customer.
     */
    public static void delete(String customerId, RequestOptions options)
            throws MoneyCollectException {
        String url =
                String.format(
                        "%s%s",
                        MoneyCollect.getApiBase(),
                        String.format("/v1/customers/%s", ApiResource.urlEncodeId(customerId)));
         ApiResource.request(
                ApiResource.RequestMethod.DELETE, url, (Map<String, Object>) null, Customer.class, options);
    }

    public Customer update(CustomerUpdateParams params) throws MoneyCollectException {
        return update(params, (RequestOptions) null);
    }

    /**
     * Updates the specified customer by setting the values of the parameters passed. Any parameters
     * not provided will be left unchanged. For example, if you pass the <strong>source</strong>
     * parameter, that becomes the customer’s active source (e.g., a card) to be used for all charges
     * in the future. When you update a customer to a new valid card source by passing the
     * <strong>source</strong> parameter: for each of the customer’s current subscriptions, if the
     * subscription bills automatically and is in the <code>past_due</code> state, then the latest
     * open invoice for the subscription with automatic collection enabled will be retried. This retry
     * will not count as an automatic retry, and will not affect the next regularly scheduled payment
     * for the invoice. Changing the <strong>default_source</strong> for a customer will not trigger
     * this behavior.
     *
     * <p>This request accepts mostly the same arguments as the customer creation call.
     */
    public Customer update(CustomerUpdateParams params, RequestOptions options)
            throws MoneyCollectException {
        String url =
                String.format(
                        "%s%s",
                        MoneyCollect.getApiBase(),
                        String.format("/v1/customers/%s/update", ApiResource.urlEncodeId(this.getId())));
        return ApiResource.request(
                ApiResource.RequestMethod.POST, url, params, Customer.class, options);
    }

}
