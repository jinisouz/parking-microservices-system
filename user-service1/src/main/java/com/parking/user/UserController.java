package com.parking.user;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users") // All URLs will start with http://localhost:8081/users
public class UserController {

    private final UserRepository userRepository;

    // Constructor Injection (Best practice)
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 1. GET Test endpoint
    @GetMapping("/test")
    public String test() {
        return "User Service is LIVE with PostgreSQL!";
    }

    // 2. POST - Create a new user
    // URL: http://localhost:8081/users
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    // 3. GET - Get all users
    // URL: http://localhost:8081/users
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // 4. GET - Get a single user by ID
    // URL: http://localhost:8081/users/1
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 5. DELETE - Remove a user
    // URL: http://localhost:8081/users/1
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (!userRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
    @PostMapping("/{id}/deduct") // MUST be @PostMapping to match the Booking Service call
    public boolean deductBalance(@PathVariable Long id, @RequestParam double amount) {
        return userRepository.findById(id).map(user -> {
            if (user.getBalance() >= amount) {
                user.setBalance(user.getBalance() - amount);
                userRepository.save(user);
                return true;
            }
            return false;
        }).orElse(false);
    }
    
 // Add this to your UserController.java in user-service
    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        // This saves the name, email, and balance directly to user_db
        return userRepository.save(user);
    }
}