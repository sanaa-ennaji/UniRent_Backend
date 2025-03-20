package org.sanaa.youcode.redline.unirent.controller;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.sanaa.youcode.redline.unirent.model.dto.Request.PaymentRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Value("${stripe.secret-key}")
    private String stripeSecretKey;

    @PostMapping("/create-payment-intent")
    public String createPaymentIntent(@RequestBody PaymentRequest paymentRequest) throws StripeException {
        Stripe.apiKey = stripeSecretKey;

        // Create a PaymentIntent with the order amount and currency
        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
            .setAmount(paymentRequest.getAmount() * 100L) // Convert to cents
            .setCurrency("usd") // Change to your currency
            .setDescription("Payment for property booking")
            .build();

        PaymentIntent paymentIntent = PaymentIntent.create(params);

        // Return the client secret to the frontend
        return paymentIntent.getClientSecret();
    }
}
