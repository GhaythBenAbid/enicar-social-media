package Controllers;

import Models.User;
import Models.UserClub;
import Services.UserClubService;
import Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("/api/user-club")
@CrossOrigin(origins = "http://localhost:4200/")
public class UserClubController {

    @Autowired
    private UserClubService userClubService;

    @PostMapping
    public ResponseEntity<Object> createUserDetails(@RequestBody UserClub userClub){
        UserClub newUserClub = userClubService.CreateUserClubInfo(userClub);
        HashMap<String, Object> response = new HashMap<>();
        response.put("userClub", newUserClub);

        return new ResponseEntity<>(response,HttpStatus.CREATED) ;

    }

    @PutMapping("/{userClubID}")
    public ResponseEntity<Object> updateUserClubDetails(@PathVariable("userClubID") int userClubID, @RequestBody UserClub userClub){
        this.userClubService.UpdateUserClubInfo(userClubID,userClub);

        HashMap<String, Object> response = new HashMap<>();
        response.put("message", "UserClub Updated Successfully");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/{UserClubID}")
    public ResponseEntity<Models.UserClub> getUserClubDetails(@PathVariable("UserClubID") int userClubID) {
        Models.UserClub userClub = userClubService.getUserClubInfo(userClubID);


        return new ResponseEntity<>(userClub, HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<UserClub>> getALLUserClubDetails(){
        return  new ResponseEntity<>(this.userClubService.getALLUserClubInfo(),HttpStatus.OK) ;}

    @GetMapping("/user/{userClubID}")
    public ResponseEntity<List<UserClub>> getUserClubByUserID(@PathVariable("userClubID") int userClubID){
        return new ResponseEntity<>(this.userClubService.getUserClubByUserID(userClubID),HttpStatus.OK);
    }

    @DeleteMapping ("/{userClubID}")
    public ResponseEntity<String> deleteUserClubDetails(@PathVariable("userClubID") int userClubID){
        this.userClubService.DeleteUserClubInfo(userClubID);
        return new ResponseEntity<>("UserClub Deleted Successfully", HttpStatus.NO_CONTENT);
    }

    //findUserClubsByUser_IdAndClub_Id
    @GetMapping("/{userID}/{clubID}")
    public ResponseEntity<List<UserClub>> getUserClubByUserIdAndClubId(@PathVariable("userID") long userID, @PathVariable("clubID") long clubID){
        return new ResponseEntity<>(this.userClubService.getUserClubByUserIdAndClubId(userID, clubID),HttpStatus.OK);
    }
}
