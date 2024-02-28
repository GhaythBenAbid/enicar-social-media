package Repositories;

import Models.UserClub;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserClubRepository extends JpaRepository<UserClub, Long> {
    // You can add custom query methods here if needed
}
