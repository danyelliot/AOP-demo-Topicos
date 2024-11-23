package com.example.aop;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MyRestController {

  @LogMethod
  @GetMapping(path = "/greeting/{name}")
  public String hello(@PathVariable(value = "name") String name) {
    return "Hello " + name + "!";
  }

  @LogMethod
  @PostMapping(path = "/user")
  public String createUser(@RequestBody String userData) {
    return "User created with data: " + userData;
  }

  @LogMethod
  @PutMapping(path = "/user/{id}")
  public String updateUser(@PathVariable(value = "id") String userId, @RequestBody String updatedData) {
    return "User " + userId + " updated with data: " + updatedData;
  }
}
