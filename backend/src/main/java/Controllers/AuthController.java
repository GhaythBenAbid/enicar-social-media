package Controllers;

import Dto.RegisterUser;
import Models.User;
import Services.AuthService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200/")
@Slf4j

public class AuthController {

    @Autowired
    private AuthService authService;


    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@Valid @RequestBody RegisterUser user){
        // Register the user
        log.info("Attempting to register user: {}", user.getEmail());
        User registeredUser = authService.register(user);

        if (registeredUser != null) {
            log.info("User registered successfully: {}", registeredUser.getEmail());
        } else {
            log.error("User registration failed for: {}", user.getEmail());
        }

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        log.info("Attempting to log in user: {}", user.getEmail());
        // Authenticate the user and generate a JWT
        Object data = authService.login(user);
        //convert the response to a hashmap
        HashMap<String, Object> response = (HashMap<String, Object>) data;
        if (response != null) {
            log.info("User logged in successfully: {}", user.getEmail());
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            log.error("User login failed for: {}", user.getEmail());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }




    @PostMapping("/forget-password")
    public ResponseEntity<?> forgotPassword(@RequestBody User user) {
        log.info("Received password reset request for email: {}", user.getEmail());
        try {
            String email = user.getEmail();
            authService.forgotPassword(email);
            HashMap<String, String> response = new HashMap<>();
            response.put("message", "Password reset instructions sent to your email.");
            log.info("Password reset instructions sent to email: {}", email);
            return ResponseEntity.ok().body(response);
        } catch (IllegalArgumentException e) {
            log.error("Error occurred while processing password reset for email: {}", user.getEmail(), e);
            HashMap<String, String> response = new HashMap<>();
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (MessagingException | IOException e) {
            log.error("System error occurred while processing password reset for email: {}", user.getEmail(), e);
            throw new RuntimeException(e);
        }
    }









}