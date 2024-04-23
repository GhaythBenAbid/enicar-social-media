package Repositories;

import Models.User;
import Models.UserEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserEventRepository extends JpaRepository<UserEvent, Long> {

        List<UserEvent> findUserEventsByEvent_Id(long id);

        List<UserEvent> findUserEventsByUser_Id(long id);

        List<UserEvent> findUserEventsByEvent_IdAndUser_Id(long id , long userId);
}
