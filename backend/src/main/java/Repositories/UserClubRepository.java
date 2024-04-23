package Repositories;

import Models.UserClub;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserClubRepository extends JpaRepository<UserClub, Long> {
    // You can add custom query methods here if needed

    List<UserClub> findUserClubsByUser_Id(long id);

    //findUserClubsByUser_IdAndClub_Id
    List<UserClub> findUserClubsByUser_IdAndClub_Id(long userId, long clubId);
}
