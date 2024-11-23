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
  @GetMapping(path = "/farewell/{name}")
  public String goodbye(@PathVariable(value = "name") String name) {
    return "Goodbye " + name + "!";
  }
}
