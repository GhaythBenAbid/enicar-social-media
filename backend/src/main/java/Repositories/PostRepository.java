package Repositories;

import Models.Club;
import Models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByDateDesc();
    List<Post> getPostsByUser_Id(long id);


}