package Models;

import jakarta.annotation.Nullable;
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
    private User responsible;

    @OneToOne
    private Content content;
    

}
