package Repositories;

import Models.MediaPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaPostRepository extends JpaRepository<MediaPost, Long> {
    // You can add custom query methods here if needed
}
