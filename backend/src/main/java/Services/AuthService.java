package Services;


import Dto.RegisterUser;
import Models.Field;
import Models.User;
import Repositories.FieldRepository;
import Repositories.UserRepository;
import Utils.ApiResponse;
import Utils.EmailService;
import Utils.JWTUtils;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

@Service
public class AuthService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FieldRepository fieldRepository;

    @Autowired
    private EmailService emailService;



    public User register(RegisterUser registerUser) {
        if (userRepository.existsByEmail(registerUser.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }


        Field field = fieldRepository.findById((long) registerUser.getFieldId()).orElse(null);

        User user = new User();

        user.setPassword(bCryptPasswordEncoder.encode(registerUser.getPassword()));
        user.setFirstName(registerUser.getFirstName());
        user.setLastName(registerUser.getLastName());
        user.setEmail(registerUser.getEmail());
        user.setRole(registerUser.getRole());
        user.setBirthDate(registerUser.getBirthDate());
        user.setVerified(false);
        user.setField(field);


        User registeredUser = userRepository.save(user);

        try{
            emailService.sendHtmlMessage(user.getEmail(), "Welcome to ENICAR Social Media", "emailVerification.html", registeredUser);

        }catch (MessagingException | IOException e) {
            e.printStackTrace();
        }

        return registeredUser;
    }

    public Object login(User user) {
        // Verify the user's credentials
        User dbUser = userRepository.findByEmail(user.getEmail());

        if (dbUser == null) {
            throw new IllegalArgumentException("this email does not exist");
        }else if (!bCryptPasswordEncoder.matches(user.getPassword(), dbUser.getPassword())) {
            throw new IllegalArgumentException("Wrong password");
        }else if (!dbUser.isVerified()) {
            throw new IllegalArgumentException("User is not verified");
        }else{
            HashMap<String, Object> response = new HashMap<>();
            response.put("user", dbUser);
            response.put("token", JWTUtils.generateToken(dbUser.getEmail()));
            return response;
        }


    }

    //getUserDetails
    public User getUserDetails(String email){
        return userRepository.findByEmail(email);
    }



}