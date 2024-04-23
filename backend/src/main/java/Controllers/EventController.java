package Controllers;


import Models.Club;
import Models.Event;

import Repositories.ClubRepository;
import Services.EventService;

import Utils.ApiResponse;
import Utils.CloudinarySDK;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/event")
@CrossOrigin(origins = "http://localhost:4200/")
public class EventController {


    @Autowired
    private EventService eventService;

    @Autowired
    private ClubRepository clubRepository;



    @PutMapping("/{eventID}")
    public ResponseEntity<Object> updateEventDetails(@PathVariable("eventID") int eventID,@RequestBody Event event){
        this.eventService.UpdateEventInfo(eventID,event);

        HashMap<String, Object> response = new HashMap<>();
        response.put("message", "Event Updated Successfully");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/{eventID}")
    public  ResponseEntity<Object> getEventDetails(@PathVariable("eventID") int eventID) {
        Models.Event event = eventService.getEventInfo(eventID).orElse(null);
        if (event != null) {
            return new ResponseEntity<>(event, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND.value(), "Event not found with id: " + eventID), HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping
    public ResponseEntity<List<Event>> getALLEventDetails(){
        return  new ResponseEntity<>(this.eventService.getALLEventInfo(),HttpStatus.OK) ;}

    @DeleteMapping ("/{eventID}")
    public ResponseEntity<Object> DeleteEventDetails(@PathVariable("eventID") Long eventID) {
        eventService.DeleteEventInfo(eventID);
        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK.value(), "Event deleted successfully"), HttpStatus.OK);
    }


    /*
    private Long id;
    private String name;
    private String description;
    private String Banner;
    private int number_places;
    private LocalDate start_date;
    private LocalDate end_date;
    private LocalTime start_time;
    private LocalTime end_time;
    private String status;

    @ManyToOne
    private Club club;
     */




    @PostMapping
    public ResponseEntity createEventDetails(
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("Banner") MultipartFile Banner,
            @RequestParam("number_places") int number_places,
            @RequestParam("start_date") String start_date,
            @RequestParam("end_date") String end_date,
            @RequestParam("start_time") String start_time,
            @RequestParam("end_time") String end_time,
            @RequestParam("status") String status,
            @RequestParam("club") Long club
    ) throws IOException {

        String banner = CloudinarySDK.imageUpload(Banner);

        Event event = new Event();
        event.setName(name);
        event.setDescription(description);
        event.setBanner(banner);
        event.setNumber_places(number_places);
        event.setStart_date(LocalDate.parse(start_date));
        event.setEnd_date(LocalDate.parse(end_date));
        event.setStart_time(LocalTime.parse(start_time));
        event.setEnd_time(LocalTime.parse(end_time));
        event.setStatus(status);
        Club club1 = this.clubRepository.findById(club).orElse(null);
        event.setClub(club1);

        try {
            Event newEvent = this.eventService.CreateEventInfo(event);
            return new ResponseEntity(newEvent, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(new ApiResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }




}
