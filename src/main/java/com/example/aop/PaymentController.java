package com.example.aop;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @LogMethod
    @GetMapping("/{paymentId}")
    public String getPaymentDetails(@PathVariable(value = "paymentId") String paymentId) {
        return "Payment ID: " + paymentId + ", amount: $100, status: Completed";
    }

    @LogMethod
    @GetMapping("/{paymentId}/refund")
    public String refundPayment(@PathVariable(value = "paymentId") String paymentId) {
        return "Refund initiated for Payment ID: " + paymentId + ".";
    }
}
