package org.sanaa.youcode.redline.unirent.controller;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.sanaa.youcode.redline.unirent.model.dto.Request.PaymentRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;



@RestController
@RequestMapping("/api/payments")
public class PaymentController {

   @Value("${stripe.secret-key}")
   private String stripeSecretKey;

        @PostMapping("/create-checkout-session")
        public Map<String, String> createCheckoutSession(@RequestBody PaymentRequest paymentRequest) throws StripeException {
            if (!isValidCurrency(paymentRequest.getCurrency())) {
                throw new IllegalArgumentException("Invalid currency: " + paymentRequest.getCurrency());
            }

            Stripe.apiKey = stripeSecretKey;

            SessionCreateParams params = SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .addLineItem(
                    SessionCreateParams.LineItem.builder()
                        .setPriceData(
                            SessionCreateParams.LineItem.PriceData.builder()
                                .setCurrency(paymentRequest.getCurrency().toLowerCase())
                                .setUnitAmount(paymentRequest.getAmount() * 100L)
                                .setProductData(
                                    SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                        .setName("Property Booking")
                                        .build()
                                )
                                .build()
                        )
                        .setQuantity(1L)
                        .build()
                )
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl("http://localhost:4200/payment-success?bookingId=" + paymentRequest.getBookingId())
                .setCancelUrl("http://localhost:4200/payment-cancel")
                .build();

            Session session = Session.create(params);

            Map<String, String> response = new HashMap<>();
            response.put("sessionId", session.getId());
            return response;
        }

        private boolean isValidCurrency(String currency) {
            Set<String> supportedCurrencies = Set.of("usd", "eur", "mad", "gbp");
            return supportedCurrencies.contains(currency.toLowerCase());
        }
    }

