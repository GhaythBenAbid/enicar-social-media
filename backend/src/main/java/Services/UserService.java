package Services;


import Models.User;
import Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    // Your service logic goes here


    private final UserRepository userRepo;
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
        existingUser.setType(user.getType());
        existingUser.setPassword(user.getPassword());
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
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





}
