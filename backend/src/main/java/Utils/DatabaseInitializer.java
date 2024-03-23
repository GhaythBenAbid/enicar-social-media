package Utils;

import Dto.RegisterUser;
import Models.Field;
import Models.User;
import Repositories.FieldRepository;
import Repositories.UserRepository;
import Services.AuthService;
import Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer implements CommandLineRunner {


    @Autowired
    private FieldRepository fieldRepository;

    @Autowired
    private AuthService authService;

    @Autowired
    private UserRepository userRepository;



    @Override
    public void run(String... args) throws Exception {
        Field field1 = new Field();
        field1.setName("Informatique");
        Field field2 = new Field();
        field2.setName("GSIL");
        Field field3 = new Field();
        field3.setName("Mécatronique");
        Field field4 = new Field();
        field4.setName("Infotronique");

        fieldRepository.save(field1);
        fieldRepository.save(field2);
        fieldRepository.save(field3);
        fieldRepository.save(field4);


        BCryptPasswordEncoder bcyptPasswordEncoder = new BCryptPasswordEncoder();

        User user = new User();
        user.setFirstName("admin");
        user.setLastName("admin");
        user.setEmail("projet@enicar.ucar.tn");
        user.setPassword(bcyptPasswordEncoder.encode("admin"));
        user.setRole("ADMIN");
        user.setVerified(true);
        user.setField(field1);

        userRepository.save(user);
    }
}
