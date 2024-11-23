package com.example.aop;

import com.example.aop.LogMethod;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class ProductController {

  // Simulamos una base de datos en memoria para los productos
  private final Map<String, Map<String, String>> products = new HashMap<>() {{
    put("1", new HashMap<>() {{
      put("name", "Laptop");
      put("description", "Equipo de gran rendimiento y hecho para el gaming");
    }});
    put("2", new HashMap<>() {{
      put("name", "Smartphone");
      put("description", "Un ligero equipo con la mejor cámara del mercado.");
    }});
    put("3", new HashMap<>() {{
      put("name", "Headphones");
      put("description", "Headphones con cancelación de ruido.");
    }});
  }};

  // Obtener información básica de un producto
  @LogMethod
  @GetMapping(path = "/api/product/{productId}")
  public Map<String, String> getProduct(@PathVariable(value = "productId") String productId) {
    return products.getOrDefault(productId, Map.of(
            "message", "Product not found"
    ));
  }

  // Obtener detalles completos de un producto
  @LogMethod
  @GetMapping(path = "/api/product/{productId}/details")
  public String getProductDetails(@PathVariable(value = "productId") String productId) {
    Map<String, String> product = products.get(productId);
    if (product == null) {
      return "Product not found.";
    }
    return "ID: " + productId + " | Name: " + product.get("name") +
            " | Description: " + product.get("description");
  }

  // Listar todos los productos con detalles
  @LogMethod
  @GetMapping(path = "/api/products")
  public List<Map<String, String>> listProducts() {
    List<Map<String, String>> productList = new ArrayList<>();
    for (Map.Entry<String, Map<String, String>> entry : products.entrySet()) {
      Map<String, String> product = new HashMap<>(entry.getValue());
      product.put("id", entry.getKey());
      productList.add(product);
    }
    return productList;
  }

  // Agregar un nuevo producto
  @LogMethod
  @PostMapping(path = "/api/product")
  public String addProduct(
          @RequestParam String productId,
          @RequestParam String productName,
          @RequestParam String productDescription) {
    if (products.containsKey(productId)) {
      return "Product with ID " + productId + " already exists.";
    }
    products.put(productId, new HashMap<>() {{
      put("name", productName);
      put("description", productDescription);
    }});
    return "Product added: " + productName + " with ID " + productId;
  }

  // Actualizar un producto existente
  @LogMethod
  @PutMapping(path = "/api/product/{productId}")
  public String updateProduct(
          @PathVariable String productId,
          @RequestParam String productName,
          @RequestParam String productDescription) {
    if (!products.containsKey(productId)) {
      return "Product with ID " + productId + " not found.";
    }
    products.put(productId, new HashMap<>() {{
      put("name", productName);
      put("description", productDescription);
    }});
    return "Product updated: " + productName + " with ID " + productId;
  }

  // Eliminar un producto
  @LogMethod
  @DeleteMapping(path = "/api/product/{productId}")
  public String deleteProduct(@PathVariable String productId) {
    if (!products.containsKey(productId)) {
      return "Product with ID " + productId + " not found.";
    }
    products.remove(productId);
    return "Product removed with ID: " + productId;
  }

  // Buscar productos por nombre o descripción
  @LogMethod
  @GetMapping(path = "/api/products/search")
  public List<Map<String, String>> searchProducts(@RequestParam String query) {
    String lowerCaseQuery = query.toLowerCase();
    List<Map<String, String>> matchingProducts = new ArrayList<>();
    for (Map.Entry<String, Map<String, String>> entry : products.entrySet()) {
      String name = entry.getValue().get("name").toLowerCase();
      String description = entry.getValue().get("description").toLowerCase();
      if (name.contains(lowerCaseQuery) || description.contains(lowerCaseQuery)) {
        Map<String, String> product = new HashMap<>(entry.getValue());
        product.put("id", entry.getKey());
        matchingProducts.add(product);
      }
    }
    return matchingProducts;
  }
}
