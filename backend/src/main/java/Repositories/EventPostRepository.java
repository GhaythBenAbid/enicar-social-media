package Repositories;

import Models.EventPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventPostRepository extends JpaRepository<EventPost, Long> {
    // You can add custom query methods here if needed
}
