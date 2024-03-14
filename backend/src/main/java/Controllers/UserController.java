package Controllers;


import Models.User;
import Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:4200/")
public class UserController {

    @Autowired
    private  UserService userService;

    @PostMapping
    public ResponseEntity<String> createUserDetails(@RequestBody  User user){
        userService.CreateUserInfo(user);
        return new ResponseEntity<>("User Created Successfuly",HttpStatus.CREATED) ;

    }

    @PutMapping("/{userID}")
    public ResponseEntity<String> updateUserDetails(@PathVariable("userID") int userID,@RequestBody User user){
        this.userService.UpdateUserInfo(userID,user);
        return new ResponseEntity<>("User Updated Successfully",HttpStatus.OK) ;
    }


    @GetMapping("/{UserID}")
    public ResponseEntity<Models.User> getUserDetails(@PathVariable("UserID") int userID) {
        Models.User user = userService.getUserInfo(userID);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<User>> getALLUserDetails(){
        return  new ResponseEntity<>(this.userService.getALLUserInfo(),HttpStatus.OK) ;}

    @DeleteMapping ("/{userID}")
    public ResponseEntity<String> deleteUserDetails(@PathVariable("userID") int userID){
        this.userService.DeleteUserInfo(userID);
        return new ResponseEntity<>("User Deleted Successfully", HttpStatus.NO_CONTENT);
    }












}
