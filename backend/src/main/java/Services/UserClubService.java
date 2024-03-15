package Services;

import Models.Club;
import Models.User;
import Models.UserClub;
import Repositories.UserClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserClubService {
    private final UserClubRepository userClubRepo;
    @Autowired
    public UserClubService(UserClubRepository userClubRepo){this.userClubRepo = userClubRepo ;}

    public String CreateUserClubInfo(UserClub userClub){
        userClubRepo.save(userClub);
        return "UserCLub Created Successfully ";

    }

    //hna ya gaith


    public UserClub UpdateUserClubInfo(long userClubID , UserClub userClub){
        UserClub existingUserClub = userClubRepo.findById(userClubID).get() ;
        existingUserClub.setUser(UserClub.getUser()) ;
        existingUserClub.setClub(UserClub.getClub());
        existingUserClub.setDate(userClub.getDate());

        return userClubRepo.save(existingUserClub) ;
    }

    public UserClub getUserClubInfo(long userClubID){
        return userClubRepo.findById(userClubID).orElse(null);
    }

    public String DeleteUserClubInfo(long userClubID){
        userClubRepo.deleteById(userClubID);
        return "UserClub Deleted Successfully ";
    }

    public List<UserClub> getALLUserClubInfo(){return userClubRepo.findAll();}






    // Your service logic goes here
}

