
//  TC-017: Récupération de mot de passe


import Models.User;
import Repositories.FieldRepository;
import Repositories.UserRepository;
import Services.AuthService;
import Utils.EmailService;
import Utils.JWTUtils;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.javamail.JavaMailSender;

import java.io.IOException;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TC_017 {

    private static final Logger logger = LoggerFactory.getLogger(TC_017.class);

    @InjectMocks
    private AuthService authService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private FieldRepository fieldRepository;

    @Mock
    private EmailService emailService;

    @Mock
    private JavaMailSender emailSender;

    @Mock
    private ResourceLoader resourceLoader;

    @Mock
    private JWTUtils jwtUtils;

    @Mock
    private Resource resource;

    private User user;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setFirstName("yassine");
        user.setLastName("lamouchi");
        user.setEmail("yassine.lamouchi@enicar.ucar.tn");
        user.setPassword("lamouchi");
        user.setRole("ADMIN");
        user.setVerified(true);
        logger.info("User set up for tests: {}", user);
    }

    @Test
    public void testForgotPassword() throws IOException, MessagingException {
        // Arrange
        when(userRepository.findByEmail("yassine.lamouchi@enicar.ucar.tn")).thenReturn(user);
        when(resourceLoader.getResource(anyString())).thenReturn(resource);
        when(resource.getURI()).thenReturn(Paths.get("src/main/resources/templates/passwordReset.html").toUri());

        MimeMessage mimeMessage = mock(MimeMessage.class);
        when(emailSender.createMimeMessage()).thenReturn(mimeMessage);

        logger.info("Testing forgotPassword for user: {}", user.getEmail());

        // Act
        authService.forgotPassword(user.getEmail());

        // Assert
        verify(emailSender, times(1)).send(mimeMessage);
        logger.info("Email sent successfully for forgotPassword to: {}", user.getEmail());
    }

    @Test
    public void testForgotPassword_UserNotFound() {
        // Arrange
        String email = "nonexistent@example.com";
        when(userRepository.findByEmail(email)).thenReturn(null);

        logger.info("Testing forgotPassword for nonexistent user: {}", email);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            authService.forgotPassword(email);
        });

        assertEquals("User not found with email: nonexistent@example.com", exception.getMessage());
        logger.error("User not found exception thrown for email: {}", email);
    }
}
