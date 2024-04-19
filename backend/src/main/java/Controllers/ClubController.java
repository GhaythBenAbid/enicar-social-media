package Controllers;

import Models.Club;
import Repositories.ClubRepository;
import Utils.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import Services.ClubService;
import java.util.List;

@RestController
@RequestMapping("/api/club")
@CrossOrigin(origins = "http://localhost:4200/")
@Slf4j
public class ClubController {
    @Autowired
    private  ClubService clubService;

    @PostMapping
    public ResponseEntity createClubDetails(@RequestBody Club club){
        log.info("Starting Create Club");
        try {
            Club newClub = this.clubService.createClub(club);
            log.info("Club Created Successfuly id : {}",club.getId());
            return new ResponseEntity(newClub, HttpStatus.CREATED);
        } catch (Exception e) {
            log.info("Error Occured while Creating ClubID : {}",club.getId());
            return new ResponseEntity(new ApiResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping
    public ResponseEntity<Object> getAllClubs() {
        log.info("Fetching all clubs");
        List<Club> clubs = clubService.getAllClubs();
        log.info("Fetched {} clubs", clubs.size());
        return new ResponseEntity<>(clubs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getClubById(@PathVariable("id") Long id) {
        log.info("Fetching club with ID: {}", id);
        Club club = clubService.getClubById(id).orElse(null);
        if (club != null) {
            log.info("Club found with ID: {}", id);
            return new ResponseEntity<>(club, HttpStatus.OK);
        } else {
            log.error("Club not found with ID: {}", id);
            return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND.value(), "Club not found with id: " + id), HttpStatus.NOT_FOUND);
        }
    }



    @PutMapping("/{id}")
    public ResponseEntity<Object> updateClubDetails(@PathVariable("id") Long id, @RequestBody Club club) {
        log.info("Updating club details for Club ID: {}", id);
        try {
            Club updatedClub = clubService.updateClub(id, club);
            log.info("Club details updated successfully for Club ID: {}", id);
            return new ResponseEntity<>(updatedClub, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            log.error("Error updating club details for Club ID: {}", id, e);
            return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND.value(), e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteClub(@PathVariable("id") Long id) {
        log.info("Deleting club with ID: {}", id);
        clubService.deleteClub(id);
        log.info("Club deleted successfully with ID: {}", id);
        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK.value(), "Club deleted successfully"), HttpStatus.OK);
    }


}










