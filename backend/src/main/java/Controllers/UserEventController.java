package Controllers;

import Models.User;
import Models.UserEvent;
import Services.UserEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/api/userEvent")
public class UserEventController {

    @Autowired
    private UserEventService userEventService;

    // Your code goes here

    @GetMapping("/{userEventID}")
    public UserEvent getUserEventInfo(@PathVariable long userEventID) {
        return userEventService.getUserEventInfo(userEventID);
    }

    @PostMapping
    public UserEvent createUserEventInfo(@RequestBody UserEvent userEvent) {
        return userEventService.CreateUserEventInfo(userEvent);
    }

    @PutMapping("/{userEventID}")
    public UserEvent updateUserEventInfo(@PathVariable long userEventID, @RequestBody UserEvent userEvent) {
        return userEventService.UpdateUserEventInfo(userEventID, userEvent);
    }

    @DeleteMapping("/{userEventID}")
    public String deleteUserEventInfo(@PathVariable long userEventID) {
        return userEventService.DeleteUserEventInfo(userEventID);
    }

    @GetMapping("/event/{eventID}/users")
    public List<UserEvent> getUsersByEvent(@PathVariable long eventID) {
        return userEventService.getUsersByEvent(eventID);
    }

    @GetMapping("/event/{eventID}")
    public List<UserEvent> getUserEventByEventId(@PathVariable long eventID) {
        return userEventService.getUsersByEvent(eventID);
    }

    @GetMapping("/user/{userID}")
    public List<UserEvent> getUserEventByUserId(@PathVariable long userID) {
        return userEventService.getEventsByUser(userID);
    }

    @GetMapping("/event/{eventID}/user/{userID}")
    public List<UserEvent> getByEventAndUser(@PathVariable long eventID, @PathVariable long userID) {
        return userEventService.getUsersByEventAndUser(eventID, userID);
    }



    @GetMapping
    public List<UserEvent> getAllUserEvents() {
        return userEventService.getAllUserEvents();
    }
}
