package Services;


import Dto.RegisterUser;
import Models.Field;
import Models.User;
import Repositories.FieldRepository;
import Repositories.UserRepository;
import Utils.EmailService;
import Utils.JWTUtils;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import static Utils.JWTUtils.generateToken;

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

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private StorageService storageService;



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

        try {
            emailService.sendHtmlMessage(user.getEmail(), "Welcome to ENICAR Social Media", "emailVerification.html", registeredUser);

        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }

        return registeredUser;
    }

    public Object login(User user) {
        // Verify the user's credentials
        User dbUser = userRepository.findByEmail(user.getEmail());

        if (dbUser == null) {
            throw new IllegalArgumentException("this email does not exist");
        } else if (!bCryptPasswordEncoder.matches(user.getPassword(), dbUser.getPassword())) {
            throw new IllegalArgumentException("Wrong password");
        } else if (!dbUser.isVerified()) {
            throw new IllegalArgumentException("User is not verified");
        } else {
            HashMap<String, Object> response = new HashMap<>();
            response.put("user", dbUser);
            response.put("token", generateToken(dbUser.getEmail()));
            return response;
        }


    }

    //getUserDetails
    public User getUserDetails(String email) {
        return userRepository.findByEmail(email);
    }





    public void forgotPassword(String email) throws IOException, MessagingException {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new IllegalArgumentException("User not found with email: " + email);
        }


        String resetLink = "http://localhost:4200/reset-password?token=" + JWTUtils.generateToken(String.valueOf(user.getId()));
        String subject = "Reset your password";


        Resource resource = resourceLoader.getResource("classpath:/templates/passwordReset.html");
        byte[] bytes = Files.readAllBytes(Paths.get(resource.getURI()));
        String content = new String(bytes, StandardCharsets.UTF_8);


        content = content.replace("{{firstname}}", user.getFirstName());
        content = content.replace("{{lastname}}", user.getLastName());
        content = content.replace("{{linkpath}}", resetLink);




        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(email);
        helper.setSubject(subject);
        helper.setText(content, true);

        emailSender.send(message);
    }


    public User UpdateUserInfo(long userID , User user){
        User existingUser = userRepository.findById(userID).get() ;
        if(user.getBirthDate() != null){
            existingUser.setBirthDate(user.getBirthDate());
        }

        if(user.getEmail() != null){
            existingUser.setEmail(user.getEmail());
        }

        if(user.getField() != null){
            existingUser.setField(user.getField());
        }

        if(user.getRole() != null){
            existingUser.setRole(user.getRole());
        }

        if(user.getPassword() != null){
            existingUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }

        if(user.getFirstName() != null){
            existingUser.setFirstName(user.getFirstName());
        }

        if(user.getLastName() != null){
            existingUser.setLastName(user.getLastName());
        }

        if(user.isVerified() != existingUser.isVerified()){
            existingUser.setVerified(user.isVerified());
        }

        if(user.getProfilePicture() != null){
            existingUser.setProfilePicture(user.getProfilePicture());
        }

        if(user.getCoverPicture() != null){
            existingUser.setCoverPicture(user.getCoverPicture());
        }

        if(user.getAboutMe() != null){
            existingUser.setAboutMe(user.getAboutMe());
        }






        return userRepository.save(existingUser) ;
    }


}