package Repositories;

import Models.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface ClubRepository extends JpaRepository<Club, Long> {

    List<Club> getClubsByResponsible_Id(long id);

}
