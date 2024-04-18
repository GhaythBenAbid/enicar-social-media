package Services;

import Models.Club;
import Models.Content;
import Models.Event;
import Repositories.ClubRepository;
import Repositories.ContentRepository;
import Repositories.EventRepository;
import Repositories.UserRepository;
import jakarta.persistence.OneToOne;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

@Service
public class ClubService {
    // Your service logic goes here

    private final ClubRepository clubRepository;
    private final ContentRepository contentRepository;
    private final UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    public ClubService(ClubRepository clubRepository , ContentRepository contentRepository , UserRepository userRepository){
        this.clubRepository = clubRepository;
        this.contentRepository = contentRepository;
        this.userRepository = userRepository;
    }

    public Club createClub(Club club) {
        if (club.getContent() != null && club.getContent().getContent_data() != null) {
            Content content = new Content();
            content.setContent_data(club.getContent().getContent_data());
            Content savedContent = contentRepository.save(content);
            club.setContent(savedContent);
        }

        if (club.getResponsible() != null) {
            club.setResponsible(userRepository.findById((long) club.getResponsible().getId()).orElse(null));
        }

        clubRepository.save(club);

        return club;
    }
    public List<Club> getAllClubs() {
        return this.clubRepository.findAll();
    }

    public Optional<Club> getClubById(Long id) {
        return this.clubRepository.findById(id);
    }

    public Club updateClub(Long id, Club club) {
        Optional<Club> optionalClub = this.clubRepository.findById(id);
        if (optionalClub.isPresent()) {
            Club existingClub = optionalClub.get();

            if (club.getName() != null) {
                existingClub.setName(club.getName());
            }
            if (club.getLogo() != null) {
                existingClub.setLogo(club.getLogo());
            }
            if (club.getBanner() != null) {
                existingClub.setBanner(club.getBanner());
            }
            if (club.getDescription() != null) {
                existingClub.setDescription(club.getDescription());
            }
            if (club.getCreationYear() != 0) {
                existingClub.setCreationYear(club.getCreationYear());
            }
            if (club.getContent() != null) {
                existingClub.setContent(club.getContent());
            }
            if (club.getResponsible() != null) {
                existingClub.setResponsible(club.getResponsible());
            }

            return this.clubRepository.save(existingClub);
        } else {
            throw new IllegalArgumentException("Club not found with id: " + id);
        }
    }

    public List<Event> getEventsByClubId(Long id) {
        return this.eventRepository.getEventsByClub_Id(id);
    }


    public void deleteClub(Long id) {
        this.clubRepository.deleteById(id);
    }

}
