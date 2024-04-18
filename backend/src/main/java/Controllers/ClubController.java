package Controllers;

import Models.Club;
import Models.Event;
import Repositories.ClubRepository;
import Utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import Services.ClubService;
import java.util.List;

@RestController
@RequestMapping("/api/club")
@CrossOrigin(origins = "http://localhost:4200/")
public class ClubController {
    @Autowired
    private  ClubService clubService;

    @PostMapping
    public ResponseEntity createClubDetails(@RequestBody Club club){

        try {
            Club newClub = this.clubService.createClub(club);
            return new ResponseEntity(newClub, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(new ApiResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping
    public ResponseEntity<Object> getAllClubs() {
        List<Club> clubs = clubService.getAllClubs();
        return new ResponseEntity<>(clubs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getClubById(@PathVariable("id") Long id) {
        Club club = clubService.getClubById(id).orElse(null);
        if (club != null) {
            return new ResponseEntity<>(club, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND.value(), "Club not found with id: " + id), HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping({"/{id}/events"})
    public ResponseEntity<Object> getEventsByClubId(@PathVariable("id") Long id) {
        List<Event> events = clubService.getEventsByClubId(id);
        return new ResponseEntity<>(events, HttpStatus.OK);
    }



    @PutMapping("/{id}")
    public ResponseEntity<Object> updateClubDetails(@PathVariable("id") Long id, @RequestBody Club club) {
        try {
            Club updatedClub = clubService.updateClub(id, club);
            return new ResponseEntity<>(updatedClub, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteClub(@PathVariable("id") Long id) {
        clubService.deleteClub(id);
        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK.value(), "Club deleted successfully"), HttpStatus.OK);
    }


}










