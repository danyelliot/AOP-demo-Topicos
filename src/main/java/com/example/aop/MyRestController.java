package com.example.aop;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyRestController {
  @LogMethod
  @GetMapping(path = "/api/greeting/{name}")
  public String hello(@PathVariable(value = "name") String name) {
    return "Hello " + name + "!";
  }
}
