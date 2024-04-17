package Repositories;

import Models.Club;
import Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // You can add custom query methods here if needed
    boolean existsByEmail(String email);
    User findByEmail(String email);









}
