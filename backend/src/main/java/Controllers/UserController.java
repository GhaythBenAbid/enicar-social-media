package Controllers;


import Dto.RegisterUser;
import Models.Club;
import Models.Post;
import Models.User;
import Services.AuthService;
import Services.ClubService;
import Services.PostService;
import Services.UserService;
import Utils.CloudinarySDK;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:4200/")
@Slf4j
public class UserController {

    @Autowired
    private  UserService userService;

    @Autowired
    private AuthService authService;

    @Autowired
    private ClubService clubService;

    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity<Object> createUserDetails(@RequestBody @Valid RegisterUser user){
        // Register the user
        log.info("Creating User : {} " , user.getEmail());
        try {
            User registeredUser = authService.register(user);
            log.info("user created successfully : {}", registeredUser.getEmail());
            return ResponseEntity.ok(registeredUser);}
        catch (Exception e

        ){
            log.error("Error creating user: {}", user.getEmail());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create user");
        }
    }





    @PutMapping("/{userID}")
    public ResponseEntity<Object> updateUserDetails(
            @PathVariable("userID") int userID,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String birthDate,
            @RequestParam(required = false) Boolean verified,
            @RequestParam(required = false) MultipartFile profilePicture,
            @RequestParam(required = false) MultipartFile coverPicture,
            @RequestParam(required = false) String aboutMe) throws IOException, ParseException {

        log.info("Attempting to update user details for User ID : {}",userID);
        //convert string to date

        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setVerified(true);
        Date date = null;
        if (birthDate != null && !birthDate.equals("null")) {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(birthDate);
        }
        user.setBirthDate(date);
        if (profilePicture != null) {
            user.setProfilePicture(CloudinarySDK.imageUpload(profilePicture));
        }
        if (coverPicture != null) {
            user.setCoverPicture(CloudinarySDK.imageUpload(coverPicture));
        }
        user.setAboutMe(aboutMe);
        log.debug("User details to be updated: {}", user);
        System.out.println(user);

        User newuser =this.authService.UpdateUserInfo(userID,user);
        if (newuser != null) {
            log.info("User details updated successfully for User ID: {}", userID);
        } else {
            log.error("User details update failed for User ID: {}", userID);
        }



        HashMap<String, Object> response = new HashMap<>();
        response.put("user", newuser);
        response.put("message", "User Updated Successfully");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }




    @GetMapping("/{UserID}")
    public ResponseEntity<Models.User> getUserDetails(@PathVariable("UserID") int userID) {

        log.info("Getting user info : {}", userID);

        try {
            Models.User user = userService.getUserInfo(userID);
            log.info("Successfully retrieved user : {}",userID);
            System.out.println(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error occurred while getting user info", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{UserID}/clubs")
    public ResponseEntity<List<Club>> getUserClubs(@PathVariable("UserID") int userID) {
        List<Club> clubs = userService.getClubs(userID);
        return new ResponseEntity<>(clubs, HttpStatus.OK);
    }
    @GetMapping("/{UserID}/posts")
    public ResponseEntity<List<Post>> getUserPosts(@PathVariable("UserID") int userID) {
        List<Post> posts = userService.getPosts(userID);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }




    @GetMapping
    public ResponseEntity<List<User>> getAllUserDetails() {
        log.info("Getting all users info");

        try {
            List<User> users = userService.getALLUserInfo();
            log.info("Successfully retrieved all users");
            return ResponseEntity.ok(users);
        } catch (Exception e) {
            log.error("Error occurred while getting all users info", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping ("/{userID}")
    public ResponseEntity<String> deleteUserDetails(@PathVariable("userID") int userID){
        log.info(" Deleting user  :{}",userID);
        try {
            this.userService.DeleteUserInfo(userID);
            log.info(" User Successfully Deleted ");
            return new ResponseEntity<>("User Deleted Successfully", HttpStatus.NO_CONTENT);
        }
        catch (Exception e){
            log.error("Error occurred while deleting user {}",userID);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

        }

    }












}
