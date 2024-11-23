package com.example.aop;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @LogMethod
    @GetMapping("/{customerId}")
    public String getCustomer(@PathVariable(value = "customerId") String customerId) {
        // Simular la obtención de datos de clientes
        return "Customer ID: " + customerId + ", nombre: John Doe, Email: john.doe@example.com";
    }

    @LogMethod
    @GetMapping("/{customerId}/orders")
    public String getCustomerOrders(@PathVariable(value = "customerId") String customerId) {
        // simula la obtención de las ordenes del cliente
        return "Orders of customer ID: " + customerId + ": [Order1, Order2, Order3]";
    }

    @LogMethod
    @GetMapping("/{customerId}/profile")
    public String getCustomerProfile(@PathVariable(value = "customerId") String customerId) {
        // simula la obtención del perfil del cliente
        return "Profile of customer ID: " + customerId + ": {Name: John Doe, Age: 30, Address: 123 Main St}";
    }
}
