package Services;

import Models.Club;
import Models.User;
import Models.UserClub;
import Repositories.ClubRepository;
import Repositories.UserClubRepository;
import Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserClubService {

    @Autowired
    private UserClubRepository userClubRepo;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ClubRepository clubRepository;


    public UserClub CreateUserClubInfo(UserClub userClub){
        User user = userRepository.findById((long) userClub.getUser().getId()).orElse(null);
        userClub.setUser(user);

        Club club = clubRepository.findById((long) userClub.getClub().getId()).orElse(null);
        userClub.setClub(club);

        return userClubRepo.save(userClub);


    }

    public UserClub UpdateUserClubInfo(long userClubID , UserClub userClub){
        UserClub existingUserClub = userClubRepo.findById(userClubID).get();

        User user = userRepository.findById((long) userClub.getUser().getId()).orElse(null);
        existingUserClub.setUser(user) ;

        Club club = clubRepository.findById((long) userClub.getClub().getId()).orElse(null);
        existingUserClub.setClub(club);
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







}

