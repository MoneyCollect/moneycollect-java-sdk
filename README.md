<p align="center">
  <img src="https://www.moneycollect.com/static/common/img/logo/site_logo.png">
  <br/>
  <h1 align="center">Money Collect SDK for Java</h1>
  <p align="center"><a href="https://mvnrepository.com/artifact/com.moneycollect/moneycollect-java"><img src="https://img.shields.io/maven-metadata/v.svg?label=Maven Central&metadataUrl=https%3A%2F%2Frepo1.maven.org%2Fmaven2%2Fcom%2Fmoneycollect%2Fmoneycollect-java%2Fmaven-metadata.xml"></a></p>
<p>
    This document introduces how to obtain and call Money Collect SDK for Java.
If you have any problem while using Money Collect SDK for Java, please submit an issue.

## Money Collect Features Supported by SDK

- Balance
    - [Retrieve balance](https://apireference.moneycollect.com/docs/api-v1/8da73725ee750-retrieve-balance)
- Checkout Session
    - [List all Checkout Sessions](https://apireference.moneycollect.com/docs/api-v1/b6cb20b67c8c4-list-all-checkout-sessions)
    - [Create a Session](https://apireference.moneycollect.com/docs/api-v1/ac48ebdba6798-create-a-session)
    - [Retrieve a Session](https://apireference.moneycollect.com/docs/api-v1/e7816d324c507-retrieve-a-session)
- Customer
    - [List all customers](https://apireference.moneycollect.com/docs/api-v1/a77599caa0adb-list-all-customers)
    - [Create a customer](https://apireference.moneycollect.com/docs/api-v1/760a8d812ab23-create-a-customer)
    - [Retrieves a customer](https://apireference.moneycollect.com/docs/api-v1/033ded676c2d3-retrieves-a-customer)
    - [Delete a customer](https://apireference.moneycollect.com/docs/api-v1/c35f854d4574e-delete-a-customer)
    - [Update a customer](https://apireference.moneycollect.com/docs/api-v1/3a52716fb1d20-update-a-customer)
- Payment
    - [List all payment ](https://apireference.moneycollect.com/docs/api-v1/f7081fd7fd717-list-all-payment)
    - [Create a Payment](https://apireference.moneycollect.com/docs/api-v1/459a230b2245a-create-a-payment)
    - [Retrieve a Payment](https://apireference.moneycollect.com/docs/api-v1/c730325ae8d27-retrieve-a-payment)
    - [Cancel a Paymen](https://apireference.moneycollect.com/docs/api-v1/66b9910c13cad-cancel-a-payment)
    - [Capture a Payment](https://apireference.moneycollect.com/docs/api-v1/36c96c0bafb62-capture-a-payment)
    - [Confirm a Payment](https://apireference.moneycollect.com/docs/api-v1/030b7b9d4322f-confirm-a-payment)
    - [Update a Payment](https://apireference.moneycollect.com/docs/api-v1/6dcf5effad440-update-a-payment)
- Payment Method
    - [Create a PaymentMethod ](https://apireference.moneycollect.com/docs/api-v1/4ae9a5a4bddc4-create-a-payment-method)
    - [Retrieve a PaymentMethod](https://apireference.moneycollect.com/docs/api-v1/51b15ddec9d4e-retrieve-a-payment-method)
    - [Attach a PaymentMethod to a Customer](https://apireference.moneycollect.com/docs/api-v1/90ec8ad6c794a-attach-a-payment-method-to-a-customer)
    - [Detach a PaymentMethod from a Customer](https://apireference.moneycollect.com/docs/api-v1/1e7ee716f0b17-detach-a-payment-method-from-a-customer)
    - [Update a PaymentMethod](https://apireference.moneycollect.com/docs/api-v1/c6a1562e23914-update-a-payment-method)
- Payout
    - [List all payout](https://apireference.moneycollect.com/docs/api-v1/2a17488951605-list-all-payout)
    - [Retrieve payout](https://apireference.moneycollect.com/docs/api-v1/1fd5988661144-retrieve-payout)
- Refunds
    - [Create a refund](https://apireference.moneycollect.com/docs/api-v1/8b2479a1195ad-create-a-refund)
    - [Retrieve a refund](https://apireference.moneycollect.com/docs/api-v1/70810f9736f1a-retrieve-a-refund)
    - [Update a refund](https://apireference.moneycollect.com/docs/api-v1/241a9bf09724a-update-a-refund)
- Transaction
    - [List all transactions](https://apireference.moneycollect.com/docs/api-v1/30a4cbd72b24b-list-all-transactions)
    - [Retrieves a transaction](https://apireference.moneycollect.com/docs/api-v1/d44835aa84685-retrieves-a-transaction)

### Requirements

- Money Collect SDK requires Java 1.8 or later.

## Installation

To use the  SDK as an example, you only need to declare the following dependency in the file.`pom.xml`

```java
<dependency>
  <groupId>com.moneycollect</groupId>
  <artifactId>moneycollect-java</artifactId>
  <version>1.0.4</version>
</dependency>
```

## Quick Examples

Before starting, you need to confirm that you have a Private key. see: [API-Keys](https://docs.moneycollect.com/documentation/developer-tools/api-keys)

The following code example shows the three main steps to use Money Collect SDK for Java :

1. Set Private key.
2. Build parameters.
3. Initiate the request and handle the response or exceptions.

```java
package com.moneycollect.MoneyCollect.helloworld;


import com.moneycollect.MoneyCollect;
import com.moneycollect.exception.MoneyCollectException;
import com.moneycollect.model.checkout.Session;
import com.moneycollect.param.checkout.SessionCreateParams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.moneycollect.param.checkout.SessionCreateParams.Address;
import com.moneycollect.param.checkout.SessionCreateParams.BillingDetails;
import com.moneycollect.param.checkout.SessionCreateParams.Shipping;
import com.moneycollect.param.checkout.SessionCreateParams.LineItem;

public class Main {
  public static void main(String[] args) throws MoneyCollectException {
    // 1.Set Private key.
    // MoneyCollect.apiKey = "your-api-key";

    // If there is a need to override the API's Base URL, invoke this method.
    // MoneyCollect.overrideApiBase("your-api-base-url");
    // Otherwise, the default provided by SDK will be used.

    // 2.Build parameters.
    List<LineItem> lineItems = new ArrayList<>();
    lineItems.add(LineItem.builder()
            .setAmount(80000L)
            .setCurrency("USD")
            .setName("iPhone")
            .setQuantity(2)
            .setImages(Arrays.asList("https://www.moneycollect.com/static/common/img/shopcart.png"))
            .build());

    SessionCreateParams params = SessionCreateParams.builder()
            .setCustomer("cus_1777230416823037954")
            .setCustomerEmail("funny@moneycollect.com")
            .setBillingDetails(
                    BillingDetails
                            .builder()
                            .setFirstName("Mark")
                            .setLastName("Merrill")
                            .setPhone("210-627-6464")
                            .setEmail("funny@moneycollect.com")
                            .setAddress(
                                    Address.builder()
                                            .setCity("North Carolina")
                                            .setCountry("US")
                                            .setLine1("3968 Fidler Drive")
                                            .setState("Asheville")
                                            .setPostalCode("28806")
                                            .build())
                            .build())
            .setShipping(
                    Shipping
                            .builder()
                            .setAddress(Address.builder()
                                    .setCity("North Carolina")
                                    .setCountry("US")
                                    .setLine1("3968 Fidler Drive")
                                    .setState("Asheville")
                                    .setPostalCode("28806")
                                    .build())
                            .setFirstName("Mark")
                            .setLastName("Merrill")
                            .setPhone("210-627-6464")
                            .build())
            .setLineItems(lineItems)
            .setAmountTotal(990L)
            .setCurrency("USD")
            .setCancelUrl("http://localhost:4242/cancle.html")
            .setReturnUrl("http://localhost:4242/return.html")
            .setNotifyUrl("http://localhost:4242/notify.html")
            .setOrderNo("order_id_123456")
            .setWebsite("http://yourwebsite.org")
            .setSubmitType("Pay")
            .setPreAuth(SessionCreateParams.PreAuth.NO)
            .build();

    // 3.Initiate the request and handle the response or exceptions
    Session session = null;
    try {
      session = Session.create(params);
    } catch (MoneyCollectException e) {
      e.printStackTrace();
    }

  }
}
```
>This is just a simple example. You can find what you need in the [example project](https://github.com/MoneyCollect/moneycollect-api-java-demo), or you can find unit test cases in the 'src/test' directory of this project.
## Issues

[Opening an Issue](https://github.com/MoneyCollect/moneycollect-java-sdk/issues/new), Issues not conforming to the guidelines may be closed immediately.

## References

- [[Documentation (moneycollect.com)]](https://docs.moneycollect.com/documentation/business-operation/risk-management)
- [MoneyCollect API: simple & powerful | API Reference V1.0](https://apireference.moneycollect.com/docs/api-v1/ZG9jOjQyOTAy-money-collect-api-simple-and-powerful)

## License

[Apache-2.0](http://www.apache.org/licenses/LICENSE-2.0)