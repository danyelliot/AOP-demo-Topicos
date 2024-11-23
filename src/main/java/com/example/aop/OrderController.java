package com.example.aop;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

  @LogMethod
  @PutMapping("/{orderId}")
  public String updateOrder(@PathVariable(value = "orderId") String orderId, @RequestBody String updatedDetails) {
    return "Order " + orderId + " updated with details: " + updatedDetails;
  }
}
