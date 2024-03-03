package Services;

import Models.Club;
import Models.Content;
import Repositories.ClubRepository;
import jakarta.persistence.OneToOne;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class ClubService {
    // Your service logic goes here

    private final ClubRepository clubRepo;
    @Autowired
    public ClubService(ClubRepository clubRepo){this.clubRepo = clubRepo ;}

    public String CreateClubInfo(Club club){
        clubRepo.save(club);
        return "Club Created Successfully ";

    }
    public String UpdateClubInfo(Club club){
        clubRepo.save(club);
        return "Club Created Successfully ";
    }

    public Club getClubInfo(long clubID){
        return clubRepo.findById(clubID).get();
    }

    public String DeleteClubInfo(long clubID){
        clubRepo.deleteById(clubID);
        return "Club Deleted Successfully ";
    }

    public List<Club> getALLUserInfo(){return clubRepo.findAll();}

    ////////////////////////////////////////////
    public Club UpdateClubInfo(long clubID , Club club){
        Club existingClub = clubRepo.findById(clubID).get() ;
        existingClub.setName(club.getName()) ;
        existingClub.setLogo(club.getLogo());
        existingClub.setBanner(club.getBanner());
        existingClub.setDescription(club.getDescription());
        existingClub.setCreationYear(club.getCreationYear());
        existingClub.setContent(club.getContent());


        return clubRepo.save(existingClub) ;
    }

}
