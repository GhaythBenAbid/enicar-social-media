package Repositories;

import Models.Club;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClubRepository extends JpaRepository<Club, Long> {
    // You can add custom query methods here if needed
}
