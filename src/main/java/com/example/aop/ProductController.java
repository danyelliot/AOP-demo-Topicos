package com.example.aop;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

  @LogMethod
  @GetMapping(path = "/api/product/{productId}")
  public String getProduct(@PathVariable(value = "productId") String productId) {
    return "ID product: " + productId;
  }

  @LogMethod
  @GetMapping(path = "/api/product/{productId}/details")
  public String getProductDetails(@PathVariable(value = "productId") String productId) {
    return "Details of product ID: " + productId;
  }
}