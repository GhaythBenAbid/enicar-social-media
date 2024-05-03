package enicar.social.backend;

import Models.Club;
import Models.Content;
import Models.User;
import Repositories.ClubRepository;
import Repositories.ContentRepository;
import Repositories.UserRepository;
import Services.ClubService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
class BackendApplicationTests {

	@Test
	void contextLoads() {
	}

	@Mock
	private ClubRepository clubRepository;

	@Mock
	private ContentRepository contentRepository;

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private ClubService clubService;

	@Test
	public void testCreateClub() {
		// Mock data
		Club club = new Club();
		club.setName("Test Club");
		club.setDescription("Test Description");

		Content content = new Content();
		content.setContent_data("Test Content Data");

		User user = new User();
		user.setId(1);
		user.setFirstName("Test User");

		club.setContent(content);
		club.setResponsible(user);

		// Mocking behavior
		when(contentRepository.save(any())).thenReturn(content);
		when(userRepository.findById(1L)).thenReturn(Optional.of(user));
		when(clubRepository.save(any())).thenReturn(club);

		// Call the method
		Club createdClub = clubService.createClub(club);

		// Verify the result
		assertNotNull(createdClub);
		assertEquals("Test Club", createdClub.getName());
		assertEquals("Test Description", createdClub.getDescription());
		assertEquals("Test Content Data", createdClub.getContent().getContent_data());
		assertEquals("Test User", createdClub.getResponsible().getFirstName());
	}


	@Test
	public void testGetAllClubs() {
		// Mock data
		Club club1 = new Club();



		club1.setName("Test Club 1");
		club1.setDescription("Test Description 1");

		Club club2 = new Club();
		club2.setName("Test Club 2");
		club2.setDescription("Test Description 2");

		// Mocking behavior
		when(clubRepository.findAll()).thenReturn(List.of(club1, club2));

		// Call the method
		var clubs = clubService.getAllClubs();

		// Verify the result
		assertNotNull(clubs);
		assertEquals(2, clubs.size());
		assertEquals("Test Club 1", clubs.get(0).getName());
		assertEquals("Test Description 2", clubs.get(1).getDescription());
	}

}
