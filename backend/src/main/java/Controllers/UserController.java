package Controllers;


import Dto.RegisterUser;
import Models.Club;
import Models.User;
import Services.AuthService;
import Services.ClubService;
import Services.UserService;
import Utils.CloudinarySDK;
import jakarta.validation.Valid;
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
public class UserController {

    @Autowired
    private  UserService userService;

    @Autowired
    private AuthService authService;

    @Autowired
    private ClubService clubService;

    @PostMapping
    public ResponseEntity<Object> createUserDetails(@RequestBody @Valid RegisterUser user){
        // Register the user
        User registeredUser = authService.register(user);
        return ResponseEntity.ok(registeredUser);
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
        //convert string to date

        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        Date date = null;
        if (birthDate != null && !birthDate.equals("null")) {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(birthDate);
        }
        user.setBirthDate(date);
        user.setVerified(verified);
        if (profilePicture != null) {
            user.setProfilePicture(CloudinarySDK.imageUpload(profilePicture));
        }
        if (coverPicture != null) {
            user.setCoverPicture(CloudinarySDK.imageUpload(coverPicture));
        }
        user.setAboutMe(aboutMe);

        System.out.println(user);


        User newuser =this.authService.UpdateUserInfo(userID,user);

        HashMap<String, Object> response = new HashMap<>();
        response.put("user", newuser);
        response.put("message", "User Updated Successfully");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }




    @GetMapping("/{UserID}")
    public ResponseEntity<Models.User> getUserDetails(@PathVariable("UserID") int userID) {
        Models.User user = userService.getUserInfo(userID);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/{UserID}/clubs")
    public ResponseEntity<List<Club>> getUserClubs(@PathVariable("UserID") int userID) {
        List<Club> clubs = userService.getClubs(userID);
        return new ResponseEntity<>(clubs, HttpStatus.OK);
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
