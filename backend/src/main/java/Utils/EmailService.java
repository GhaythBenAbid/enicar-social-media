package Utils;

import Models.User;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;


@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private ResourceLoader resourceLoader;

    @Autowired
    private JWTUtils jwtUtils;

    public void sendHtmlMessage(String to, String subject, String htmlpath , User user) throws MessagingException, IOException {
        Resource resource = resourceLoader.getResource("classpath:/templates/" + htmlpath);
        byte[] bytes = Files.readAllBytes(Paths.get(resource.getURI()));
        String content = new String(bytes, StandardCharsets.UTF_8);


        content = content.replace("{{firstname}}", user.getFirstName());
        content = content.replace("{{lastname}}", user.getLastName());
        content = content.replace("{{linkpath}}", "http://localhost:4200/verify/" + JWTUtils.generateToken(String.valueOf(user.getId())));




        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content, true);

        emailSender.send(message);
    }

}