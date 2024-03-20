package Controllers;


import Models.Event;

import Services.EventService;

import Utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/event")
@CrossOrigin(origins = "http://localhost:4200/")
public class EventController {


    @Autowired
    private EventService eventService;



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

    @PostMapping
    public ResponseEntity createEventDetails(@RequestBody Event event){

        try {
            Event newEvent = this.eventService.CreateEventInfo(event);
            return new ResponseEntity(newEvent, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(new ApiResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }




}
