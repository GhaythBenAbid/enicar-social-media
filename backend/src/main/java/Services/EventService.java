package Services;


import Models.Event;

import Repositories.ClubRepository;
import Repositories.EventRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    // Your service logic goes here
    @Autowired
    private EventRepository eventRepo;
    @Autowired
    private ClubRepository clubRepo ;

    @Autowired
    public EventService(EventRepository Repo , ClubRepository Crepo) {
        this.eventRepo = Repo;
        this.clubRepo = Crepo ;

    }

    public Event CreateEventInfo(Event event) {

        if (event.getStart_date() != null && event.getEnd_date() != null && event.getStart_date().isAfter(event.getEnd_date())) {
            throw new IllegalArgumentException("Start date must be before end date.");
        }
        if (event.getStart_time() != null && event.getEnd_time() != null && event.getStart_time().isAfter(event.getEnd_time())) {
            throw new IllegalArgumentException("Start Time must be before end date.");
        }
        if (event.getClub() == null  || !clubRepo.existsById(event.getClub().getId())) {
            throw new IllegalArgumentException("Club is required and must already exist in the database.");
        }
        eventRepo.save(event);
        return event ;


    }

    public Event UpdateEventInfo(long eventID, Event event) {

        if (eventRepo.existsById(eventID)  ){
            Event existingEvent = eventRepo.findById(eventID).get();

            if (event.getStart_date() != null && event.getEnd_date() != null && event.getStart_date().isAfter(event.getEnd_date())) {
                throw new IllegalArgumentException("Start date must be before end date.");
            }
            if (event.getStart_time() != null && event.getEnd_time() != null && event.getStart_time().isAfter(event.getEnd_time())) {
                throw new IllegalArgumentException("Start Time must be before end date.");
            }
            if (event.getClub() != null && !clubRepo.existsById(event.getClub().getId())) {
                throw new IllegalArgumentException("Club must already exist in the database.");}

            if (event.getName() != null) {
                existingEvent.setName(event.getName());
            }
            if (event.getDescription() != null) {
                existingEvent.setDescription(event.getDescription());
            }

            if (event.getNumber_places() != 0) {
                existingEvent.setNumber_places(event.getNumber_places());
            }
            if (event.getStart_date() != null) {
                existingEvent.setStart_date(event.getStart_date());
            }
            if (event.getEnd_date() != null) {
                existingEvent.setEnd_date(event.getEnd_date());
            }
            if (event.getStart_time() != null) {
                existingEvent.setStart_time(event.getStart_time());
            }
            if (event.getEnd_time() != null) {
                existingEvent.setEnd_time(event.getEnd_time());
            }
            if (event.getStatus() != null) {
                existingEvent.setStatus(event.getStatus());
            }

            return eventRepo.save(existingEvent);
        }
    else { throw new IllegalArgumentException("Event not found with id: " + eventID);}
    }



    public void DeleteEventInfo(long eventID) { eventRepo.deleteById(eventID);}

    public List<Event> getALLEventInfo() {return eventRepo.findAll();}

    public Optional<Event> getEventInfo(long eventID) {return eventRepo.findById(eventID);}

}