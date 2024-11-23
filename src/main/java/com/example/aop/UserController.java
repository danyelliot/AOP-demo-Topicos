package com.example.aop;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class UserController {

  // Simulamos una base de datos en memoria para los usuarios
  private final Map<String, Map<String, String>> users = new HashMap<>() {{
    put("john_doe", new HashMap<>() {{
      put("fullName", "John Doe");
      put("email", "john.doe@gmail.com");
    }});
    put("jane_smith", new HashMap<>() {{
      put("fullName", "Jane Smith");
      put("email", "jane.smith@gmail.com");
    }});
    put("alice_wonder", new HashMap<>() {{
      put("fullName", "Alice Wonderland");
      put("email", "alice@gmail.com");
    }});
  }};

  // Obtener información básica de un usuario
  @LogMethod
  @GetMapping(path = "/api/user/{username}")
  public Map<String, String> getUser(@PathVariable(value = "username") String username) {
    return users.getOrDefault(username, Map.of(
            "message", "User not found"
    ));
  }

  // Obtener detalles completos de un usuario
  @LogMethod
  @GetMapping(path = "/api/user/{username}/details")
  public String getUserDetails(@PathVariable(value = "username") String username) {
    Map<String, String> user = users.get(username);
    if (user == null) {
      return "User not found.";
    }
    return "Username: " + username + " | Full Name: " + user.get("fullName") +
            " | Email: " + user.get("email");
  }

  // Listar todos los usuarios
  @LogMethod
  @GetMapping(path = "/api/users")
  public List<Map<String, String>> listUsers() {
    List<Map<String, String>> userList = new ArrayList<>();
    for (Map.Entry<String, Map<String, String>> entry : users.entrySet()) {
      Map<String, String> user = new HashMap<>(entry.getValue());
      user.put("username", entry.getKey());
      userList.add(user);
    }
    return userList;
  }

  // Registrar un nuevo usuario
  @LogMethod
  @PostMapping(path = "/api/user")
  public String addUser(@RequestBody Map<String, String> userData) {
    String username = userData.get("username");
    String fullName = userData.get("fullName");
    String email = userData.get("email");

    if (username == null || fullName == null || email == null) {
      return "Username, full name, and email are required.";
    }

    if (users.containsKey(username)) {
      return "User with username " + username + " already exists.";
    }

    users.put(username, new HashMap<>() {{
      put("fullName", fullName);
      put("email", email);
    }});

    return "User added: " + fullName + " with username " + username;
  }

  // Actualizar información de un usuario existente
  @LogMethod
  @PutMapping(path = "/api/user/{username}")
  public String updateUser(@PathVariable String username, @RequestBody Map<String, String> userData) {
    String fullName = userData.get("fullName");
    String email = userData.get("email");

    if (fullName == null || email == null) {
      return "Full name and email are required.";
    }

    if (!users.containsKey(username)) {
      return "User with username " + username + " not found.";
    }

    users.put(username, new HashMap<>() {{
      put("fullName", fullName);
      put("email", email);
    }});

    return "User updated: " + fullName + " with username " + username;
  }

  // Eliminar un usuario
  @LogMethod
  @DeleteMapping(path = "/api/user/{username}")
  public String deleteUser(@PathVariable String username) {
    if (!users.containsKey(username)) {
      return "User with username " + username + " not found.";
    }
    users.remove(username);
    return "User removed with username: " + username;
  }

  // Buscar usuarios por nombre completo o email
  @LogMethod
  @GetMapping(path = "/api/users/search")
  public List<Map<String, String>> searchUsers(@RequestParam String query) {
    String lowerCaseQuery = query.toLowerCase();
    List<Map<String, String>> matchingUsers = new ArrayList<>();
    for (Map.Entry<String, Map<String, String>> entry : users.entrySet()) {
      String fullName = entry.getValue().get("fullName").toLowerCase();
      String email = entry.getValue().get("email").toLowerCase();
      if (fullName.contains(lowerCaseQuery) || email.contains(lowerCaseQuery)) {
        Map<String, String> user = new HashMap<>(entry.getValue());
        user.put("username", entry.getKey());
        matchingUsers.add(user);
      }
    }
    return matchingUsers;
  }

  // Cambiar contraseña de un usuario
  @LogMethod
  @PostMapping(path = "/api/user/{username}/change-password")
  public String changePassword(@PathVariable String username, @RequestBody Map<String, String> passwordData) {
    String oldPassword = passwordData.get("oldPassword");
    String newPassword = passwordData.get("newPassword");

    if (oldPassword == null || newPassword == null) {
      return "Old password and new password are required.";
    }

    // Simulamos la verificación de la contraseña antigua (no implementada realmente)
    if (!"password123".equals(oldPassword)) {
      return "Old password is incorrect.";
    }

    // Simulamos el cambio de contraseña (no almacenamos contraseñas realmente)
    return "Password changed successfully for user: " + username;
  }

  // Iniciar sesión (simulado)
  @LogMethod
  @PostMapping(path = "/api/user/login")
  public String loginUser(@RequestBody Map<String, String> loginData) {
    String username = loginData.get("username");
    String password = loginData.get("password");

    if (username == null || password == null) {
      return "Username and password are required.";
    }

    if (!users.containsKey(username)) {
      return "User with username " + username + " not found.";
    }

    // Simulamos la verificación de la contraseña (no implementada realmente)
    if (!"password123".equals(password)) {
      return "Incorrect password.";
    }

    return "User " + username + " logged in successfully.";
  }

  // Cerrar sesión (simulado)
  @LogMethod
  @PostMapping(path = "/api/user/logout")
  public String logoutUser(@RequestBody Map<String, String> logoutData) {
    String username = logoutData.get("username");

    if (username == null) {
      return "Username is required.";
    }

    // Simulamos el cierre de sesión (no hay estado de sesión real)
    return "User " + username + " logged out successfully.";
  }
}
