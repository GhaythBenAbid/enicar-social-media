package Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EventPost extends Post {

    private String eventName;

    private String eventLocation;
    
    private LocalDateTime eventDateTime;
    
    private int maxAttendees;
    
    private boolean isPublic;

    private String eventDescription;

    private String event_link;

}