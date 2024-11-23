package com.example.aop;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/manager")
public class ManagerController {

  @LogMethod
  @GetMapping(path = "/{username}")
  public String getManager(@PathVariable(value = "username") String username) {
    return "Manager : " + username;
  }

  @LogMethod
  @GetMapping("/{username}/id")
  public String getManagerId(@PathVariable(value = "username") String username) {
    return "ID of Manager : " + username;
  }

  @LogMethod
  @GetMapping(path = "/{username}/details")
  public String getManagerDetails(@PathVariable(value = "username") String username) {
    return "Details of Manager : " + username;
  }

  @LogMethod
  @GetMapping("/{username}/direction")
  public String getManagerDirection(@PathVariable(value = "username") String username) {
    return "Direction of Manager : " + username;
  }

  @LogMethod
  @GetMapping("/{username}/projects")
  public String getManagerProjects(@PathVariable(value = "username") String username) {
    return "Projects assigned to Gerente : " + username;
  }

  @LogMethod
  @GetMapping("/{username}/employees")
  public List<String> getManagerEmployees(@PathVariable(value = "username") String username) {
    return Arrays.asList("Yarixa", "Cristina", "Yazmin");
  }
}