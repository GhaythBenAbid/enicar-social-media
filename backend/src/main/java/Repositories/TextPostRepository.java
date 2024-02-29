package Repositories;

import Models.TextPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TextPostRepository extends JpaRepository<TextPost, Long> {
    // You can add custom query methods here if needed
}
