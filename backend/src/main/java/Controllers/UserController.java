package Controllers;


import Dto.RegisterUser;
import Models.User;
import Services.AuthService;
import Services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:4200/")
public class UserController {

    @Autowired
    private  UserService userService;

    @Autowired
    private AuthService authService;

    @PostMapping
    public ResponseEntity<Object> createUserDetails(@RequestBody @Valid RegisterUser user){
        // Register the user
        User registeredUser = authService.register(user);
        return ResponseEntity.ok(registeredUser);
    }

    @PutMapping("/{userID}")
    public ResponseEntity<Object> updateUserDetails(@PathVariable("userID") int userID,@RequestBody User user){
        this.authService.UpdateUserInfo(userID,user);

        HashMap<String, Object> response = new HashMap<>();
        response.put("message", "User Updated Successfully");

        return new ResponseEntity<>(response, HttpStatus.OK);
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
