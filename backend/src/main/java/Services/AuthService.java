package Services;


import Models.User;
import Repositories.UserRepository;
import Utils.ApiResponse;
import Utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class AuthService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    public User register(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Object login(User user) {
        // Verify the user's credentials
        User dbUser = userRepository.findByEmail(user.getEmail());
        if (dbUser != null && bCryptPasswordEncoder.matches(user.getPassword(), dbUser.getPassword())) {
            HashMap<String, Object> response = new HashMap<>();
            response.put("user", dbUser);
            response.put("token", JWTUtils.generateToken(dbUser.getEmail()));
            return response;
        } else {
            return null;
        }

    }

    //getUserDetails
    public User getUserDetails(String email){
        return userRepository.findByEmail(email);
    }



}