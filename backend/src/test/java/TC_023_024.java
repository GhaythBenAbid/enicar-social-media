
import Models.Club;
import Models.Event;
import Repositories.ClubRepository;
import Repositories.EventRepository;
import Services.EventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
        import static org.mockito.Mockito.*;

public class TC_023_024 {


    @InjectMocks
    private EventService eventService;

    @Mock
    private EventRepository eventRepo;

    @Mock
    private ClubRepository clubRepo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateEventInfo_success() {
        // Arrange
        Club club = new Club();
        club.setId(1L);
        club.setName("Test Club");
        club.setLogo("club_logo.png");
        club.setBanner("club_banner.png");
        club.setDescription("A test club");
        club.setCreationYear(2020);

        Event event = new Event();
        event.setName("Test Event");
        event.setStart_date(LocalDate.now());
        event.setEnd_date(LocalDate.now().plusDays(1));
        event.setStart_time(LocalTime.of(10, 0));
        event.setEnd_time(LocalTime.of(12, 0));
        event.setClub(club); // Set the initialized Club object

        when(clubRepo.existsById(club.getId())).thenReturn(true);
        when(eventRepo.save(event)).thenReturn(event);

        // Act
        Event createdEvent = eventService.CreateEventInfo(event);

        // Assert
        assertEquals(event.getName(), createdEvent.getName());
        verify(eventRepo, times(1)).save(event);
        verify(clubRepo, times(1)).existsById(club.getId());
    }


    @Test
    void testCreateEventInfo_invalidDate() {
        // Arrange
        Event event = new Event();
        event.setStart_date(LocalDate.now().plusDays(1));
        event.setEnd_date(LocalDate.now()); // Invalid date range

        // Act & Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> {
            eventService.CreateEventInfo(event);
        });
        assertEquals("Start date must be before end date.", thrown.getMessage());
    }

    @Test
    void testUpdateEventInfo_success() {
        // Arrange
        long eventID = 1L;
        Event existingEvent = new Event();
        existingEvent.setId(eventID);
        existingEvent.setName("Existing Event");

        Event updatedEvent = new Event();
        updatedEvent.setName("Updated Event");

        when(eventRepo.existsById(eventID)).thenReturn(true);
        when(eventRepo.findById(eventID)).thenReturn(Optional.of(existingEvent));
        when(eventRepo.save(existingEvent)).thenReturn(existingEvent);

        // Act
        Event result = eventService.UpdateEventInfo(eventID, updatedEvent);

        // Assert
        assertEquals("Updated Event", result.getName());
        verify(eventRepo, times(1)).save(existingEvent);
    }

    @Test
    void testDeleteEventInfo_success() {
        // Arrange
        long eventID = 1L;

        // Act
        eventService.DeleteEventInfo(eventID);

        // Assert
        verify(eventRepo, times(1)).deleteById(eventID);
    }

    @Test
    void testGetALLEventInfo() {
        // Act
        eventService.getALLEventInfo();

        // Assert
        verify(eventRepo, times(1)).findAll();
    }

    @Test
    void testGetEventInfo_success() {
        // Arrange
        long eventID = 1L;
        Event event = new Event();
        event.setId(eventID);
        when(eventRepo.findById(eventID)).thenReturn(Optional.of(event));

        // Act
        Optional<Event> result = eventService.getEventInfo(eventID);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(eventID, result.get().getId());
    }
}
