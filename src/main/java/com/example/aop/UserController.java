package com.example.aop;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  @LogMethod
  @GetMapping(path = "/api/user/{username}")
  public String getUser(@PathVariable(value = "username") String username) {
    return "User: " + username;
  }

  @LogMethod
  @GetMapping(path = "/api/user/{username}/details")
  public String getUserDetails(@PathVariable(value = "username") String username) {
    return "Details of user: " + username;
  }
}