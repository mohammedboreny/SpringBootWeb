package demo.controllers;
import at.favre.lib.crypto.bcrypt.BCrypt;
import com.fasterxml.jackson.databind.ObjectMapper;
import demo.conf.JwtUtil;
import demo.db.interfaces.CredentialsDAO;
import demo.models2.Credentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class AuthController {

    @Autowired
    private CredentialsDAO credentialsDAO;

    @PostMapping("/signup")
    public ResponseEntity<Map<String, Object>> signUp(@Validated @RequestBody Credentials credentials, BindingResult bindingResult) {
        Map<String, Object> response = new HashMap<>();
        // Check for validation errors
        if (bindingResult.hasErrors()) {
            List<String> errors = new ArrayList<>();
            bindingResult.getAllErrors().forEach(error -> {
                errors.add(error.getDefaultMessage());
            });

            response.put("success", false);
            response.put("message", "Validation failed.");
            response.put("errors", errors);
            System.out.println(response.get("errors"));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        // Check if the username already exists
        if (credentialsDAO.getUserByUsername(credentials.getUsername()) != null) {
            response.put("success", false);
            response.put("message", "Username already exists.");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }

        // Hash the password before storing it in the database
        String hashedPassword = BCrypt.withDefaults().hashToString(12,credentials.getPassword().toCharArray());
        credentials.setPassword(hashedPassword);

        credentialsDAO.addCredentials(credentials);

        response.put("success", true);
        response.put("message", "User registered successfully.");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/signin")
    public ResponseEntity<Map<String, String>> signIn(@RequestBody Credentials credentials) {
        Credentials user = credentialsDAO.getUserByUsername(credentials.getUsername());
        Map<String, String> response = new HashMap<>();
        System.out.println(credentials);

        if (user == null) {
            response.put("message", "Invalid credentials.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }

        // Check if the provided password matches the hashed password
        if (BCrypt.verifyer().verify(credentials.getPassword().toCharArray(), user.getPassword()).verified) {
            String token = JwtUtil.generateToken(user.getUsername(), user.getId(), user.getUsername(), JwtUtil.generateSecretKey(), 3600000);
            response.put("success", "true");
            response.put("message", "Login successful.");
            try {
                String userJson = new ObjectMapper().writeValueAsString(user);
                response.put("data", userJson);
            } catch (Exception e) {
                // Handle the exception (e.g., log an error)
                response.put("data", "");
            }
            response.put("token", token); // Include the token in the response
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Invalid credentials.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }



}
