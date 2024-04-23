package Services;

import Models.*;
import Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserEventService {

    @Autowired
    private UserEventRepository userEventRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    public UserEvent CreateUserEventInfo(UserEvent userEvent){
        User user = userRepository.findById((long) userEvent.getUser().getId()).orElse(null);
        userEvent.setUser(user);

        Event event = eventRepository.findById((long) userEvent.getEvent().getId()).orElse(null);
        userEvent.setEvent(event);

        return userEventRepository.save(userEvent);
    }

    public UserEvent UpdateUserEventInfo(long userEventID , UserEvent userEvent) {
        UserEvent existingUserEvent = userEventRepository.findById(userEventID).get();
        User user = userRepository.findById((long) userEvent.getUser().getId()).orElse(null);
        existingUserEvent.setUser(user);
        Event event = eventRepository.findById((long) userEvent.getEvent().getId()).orElse(null);
        existingUserEvent.setEvent(event);
        existingUserEvent.setDate(userEvent.getDate());
        return userEventRepository.save(existingUserEvent);

    }

    public UserEvent getUserEventInfo(long userEventID) {
        return userEventRepository.findById(userEventID).orElse(null);
    }

    public String DeleteUserEventInfo(long userEventID) {
        userEventRepository.deleteById(userEventID);
        return "User Event Deleted Successfully";
    }

    public List<UserEvent> getUsersByEvent(long eventID) {
        return userEventRepository.findUserEventsByEvent_Id(eventID);
    }

    //getAllUserEvents
    public List<UserEvent> getAllUserEvents() {
        return userEventRepository.findAll();
    }

    //getUsersByEventAndUser
    public List<UserEvent> getUsersByEventAndUser(long eventID, long userID) {
        return userEventRepository.findUserEventsByEvent_IdAndUser_Id(eventID, userID);
    }

    public List<UserEvent> getEventsByUser(long userID) {
        return userEventRepository.findUserEventsByUser_Id(userID);
    }




}
