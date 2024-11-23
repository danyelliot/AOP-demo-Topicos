package com.example.aop;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
public class OrderController {

  @LogMethod
  @GetMapping("/{orderId}")
  public String getOrder(@PathVariable(value = "orderId") String orderId) {
    return "Order ID: " + orderId;
  }

  @LogMethod
  @GetMapping("/{orderId}/details")
  public String getOrderDetails(@PathVariable(value = "orderId") String orderId) {
    return "Details of order ID: " + orderId;
  }
}