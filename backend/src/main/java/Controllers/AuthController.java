package Controllers;

import Dto.RegisterUser;
import Models.User;
import Services.AuthService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200/")
public class AuthController {

    @Autowired
    private AuthService authService;


    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@Valid @RequestBody RegisterUser user){
        // Register the user
        User registeredUser = authService.register(user);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        // Authenticate the user and generate a JWT
        Object data = authService.login(user);
        //convert the response to a hashmap
        HashMap<String, Object> response = (HashMap<String, Object>) data;
        if (response != null) {

            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }


    @PostMapping("/forget-password")
    public ResponseEntity<?> forgotPassword(@RequestBody User user) {
        try {
            String email = user.getEmail();
            authService.forgotPassword(email);
            HashMap<String, String> response = new HashMap<>();
            response.put("message", "Password reset instructions sent to your email.");
            return ResponseEntity.ok().body(response);
        } catch (IllegalArgumentException e) {
            HashMap<String, String> response = new HashMap<>();
            response.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        } catch (MessagingException | IOException e) {
            throw new RuntimeException(e);
        }
    }









}