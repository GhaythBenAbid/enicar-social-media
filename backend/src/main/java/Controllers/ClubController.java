package Controllers;

import Models.Club;
import Repositories.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import Services.ClubService;
import java.util.List;

@RestController
@RequestMapping("/club")
@CrossOrigin(origins = "http://localhost:4200/")
public class ClubController {
    @Autowired
    private  ClubService clubService;

    @PostMapping
    public String createClubDetails(@RequestBody Club club){
        clubService.CreateClubInfo(club);
        return "Club Created Successfuly" ;

    }

    @PutMapping
    public String updateClubDetails(@RequestBody Club club){
        this.clubService.UpdateClubInfo(club);
        return "Club Updated Successfully" ;
    }

    @GetMapping("/{ClubID}")
    public Club getClubDetails(@PathVariable("ClubID") int ClubID)
    {
        return clubService.getClubInfo(ClubID);
    }

    @GetMapping
    public List<Club> getALLClubDetails(){return this.clubService.getALLUserInfo();}

    @DeleteMapping ("/{clubID}")
    public String deleteClubDetails(@PathVariable("clubID") int clubID){
        this.clubService.DeleteClubInfo(clubID);
        return "Club Deleted Successfully" ;
    }

    @PutMapping("/{clubID}")
    public String updateClubDetails(@PathVariable("clubID") int clubID,@RequestBody Club club){
        this.clubService.UpdateClubInfo(clubID,club);
        return "Club Updated Successfully" ;
    }



}










