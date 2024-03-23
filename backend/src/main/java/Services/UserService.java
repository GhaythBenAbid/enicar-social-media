package Services;


import Models.User;
import Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import Models.User;
import Repositories.UserRepository;
import Utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

import java.util.List;

@Service

public class UserService implements UserDetailsService {
    // Your service logic goes here


    private final UserRepository userRepo;


    @Autowired
    private StorageService storageService;



    @Autowired
    public UserService(UserRepository userRepo){this.userRepo = userRepo ;}

    public String CreateUserInfo(User user){
        userRepo.save(user);
        return "User Created Successfully ";

    }
    public User UpdateUserInfo(long userID , User user){
        User existingUser = userRepo.findById(userID).get() ;
        existingUser.setBirthDate(user.getBirthDate()) ;
        existingUser.setEmail(user.getEmail());
        existingUser.setField(user.getField());
        existingUser.setRole(user.getRole());
        existingUser.setPassword(user.getPassword());
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setVerified(user.isVerified());

        return userRepo.save(existingUser) ;
    }

    public User getUserInfo(long userID){
         return userRepo.findById(userID).orElse(null);
    }

    public String DeleteUserInfo(long userID){
        userRepo.deleteById(userID);
        return "User Deleted Successfully ";
    }

    public List<User> getALLUserInfo(){return userRepo.findAll();}


    @Autowired
    private UserRepository repository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + username);
        }

        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }


    public User updateUserPicture(int userid , MultipartFile profilePicture , String type){

        User user = userRepo.findById((long) userid).get();

        if (type.equals("profile")){
            String profilePictureName = storageService.storeFile(profilePicture);
            user.setProfilePicture(profilePictureName);
        }else if (type.equals("cover")){
            String coverPictureName = storageService.storeFile(profilePicture);
            user.setCoverPicture(coverPictureName);
        }

        return userRepo.save(user);


    }



}
