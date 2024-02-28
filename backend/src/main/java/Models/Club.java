package Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Club {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    
    private String name;
    
    private String logo;
    
    private String banner;
    
    private String description;
    
    private int creationYear;
    
    @OneToOne
    private Content content;
    
    @OneToMany(mappedBy = "club")
    private List<Event> events;
}
