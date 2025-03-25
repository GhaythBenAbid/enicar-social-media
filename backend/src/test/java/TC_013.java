import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import Models.Club;
import Models.Content;
import Models.User;
import Repositories.ClubRepository;
import Repositories.ContentRepository;
import Repositories.UserRepository;
import Services.ClubService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TC_013 {

    private static final Logger logger = LoggerFactory.getLogger(TC_013.class);

    @Mock
    private ClubRepository clubRepository;

    @Mock
    private ContentRepository contentRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ClubService clubService;

    private User user;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        // Initialize the User object
        user = new User();
        user.setId(100);  // Set an ID for the user
        user.setFirstName("yassine");
        user.setLastName("lamouchi");
        user.setEmail("yassine.lamouchi@enicar.ucar.tn");
        user.setPassword("lamouchi");
        user.setRole("ADMIN");
        user.setVerified(true);
        logger.info("User set up for tests: {}", user);
    }

    @Test
    public void testCreateClub() {
        // Arrange
        Content content = new Content(100, "Sample Content");
        Club club = new Club(100L, "Test Club", "logo.png", "banner.png", "Test Description", 2024, user, content);  // 'user' is assigned to 'responsible'
        logger.info("Creating club: {}", club);

        // Mock repository save behavior
        when(contentRepository.save(any(Content.class))).thenReturn(content);
        when(userRepository.save(any(User.class))).thenReturn(user);  // Return the user when save is called

        // Ensure the clubRepository returns the correct 'Club' object with the 'responsible' field set
        when(clubRepository.save(any(Club.class))).thenAnswer(invocation -> {
            Club savedClub = invocation.getArgument(0);  // Get the club object being passed in
            savedClub.setResponsible(user);  // Ensure the 'responsible' user is set
            return savedClub;  // Return the updated club
        });

        // Act
        Club createdClub = clubService.createClub(club);
        logger.info("Club created: {}", createdClub);

        // Assert
        assertNotNull(createdClub);  // Ensure the club is not null
        assertNotNull(createdClub.getResponsible(), "Responsible user should not be null");  // Ensure responsible user is set
        assertEquals("Sample Content", createdClub.getContent().getContent_data());  // Check content data
        assertEquals("yassine", createdClub.getResponsible().getFirstName());  // Check the first name of the responsible user
        assertEquals("lamouchi", createdClub.getResponsible().getLastName());  // Check the last name of the responsible user
        verify(contentRepository, times(1)).save(any(Content.class));  // Verify content was saved once
        verify(userRepository, times(1)).save(any(User.class));  // Verify user was saved once
        verify(clubRepository, times(1)).save(any(Club.class));  // Verify club was saved once
    }

    @Test
    public void testGetAllClubs() {
        // Arrange
        List<Club> clubs = new ArrayList<>();
        clubs.add(new Club());
        clubs.add(new Club());

        when(clubRepository.findAll()).thenReturn(clubs);
        logger.info("Retrieving all clubs");

        // Act
        List<Club> result = clubService.getAllClubs();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(clubRepository, times(1)).findAll();
        logger.info("Number of clubs retrieved: {}", result.size());
    }

    @Test
    public void testGetClubById_Success() {
        // Arrange
        Club club = new Club();
        club.setId(100L);
        logger.info("Setting up club with ID: {}", club.getId());

        when(clubRepository.findById(100L)).thenReturn(Optional.of(club));

        // Act
        Optional<Club> result = clubService.getClubById(100L);
        logger.info("Retrieving club by ID: {}", 100L);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(100L, result.get().getId());
        verify(clubRepository, times(1)).findById(100L);
    }

    @Test
    public void testGetClubById_NotFound() {
        // Arrange
        when(clubRepository.findById(1L)).thenReturn(Optional.empty());
        logger.info("Attempting to retrieve club with ID: {}", 1L);

        // Act
        Optional<Club> result = clubService.getClubById(1L);

        // Assert
        assertFalse(result.isPresent());
        verify(clubRepository, times(1)).findById(1L);
        logger.info("No club found with ID: {}", 1L);
    }

    @Test
    public void testUpdateClub() {
        // Arrange
        Club existingClub = new Club(100L, "Old Club", "old_logo.png", "old_banner.png", "Old Description", 2020, null, null);
        Club updatedClub = new Club(null, "New Club", "new_logo.png", "new_banner.png", "New Description", 2024, null, null);

        when(clubRepository.findById(100L)).thenReturn(Optional.of(existingClub));
        when(clubRepository.save(any(Club.class))).thenReturn(updatedClub);  // Return the updated club object
        logger.info("Updating club with ID: {}", existingClub.getId());

        // Act
        Club result = clubService.updateClub(100L, updatedClub);
        logger.info("Club updated: {}", result);

        // Assert
        assertEquals("New Club", result.getName());
        assertEquals("new_logo.png", result.getLogo());
        assertEquals("New Description", result.getDescription());
        verify(clubRepository, times(1)).save(any(Club.class));  // Ensure the save is called
    }

    @Test
    public void testDeleteClub() {
        // Arrange
        Long clubId = 100L;
        doNothing().when(clubRepository).deleteById(clubId);
        logger.info("Preparing to delete club with ID: {}", clubId);

        // Act
        clubService.deleteClub(clubId);

        // Assert
        verify(clubRepository, times(1)).deleteById(clubId);
        logger.info("Club with ID {} deleted successfully.", clubId);
    }
}
