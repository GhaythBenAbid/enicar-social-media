package Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Reaction {
    @Id
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Post post;

    private String type;

    private String comment;

    private Date date;
}
