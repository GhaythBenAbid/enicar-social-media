package Models;

import jakarta.persistence.*;
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

    @Enumerated(EnumType.STRING)
    private ReactionType type;

    private String comment;

    private Date date;
}
enum ReactionType {
    LIKE,
    LOVE,
    WOW,
    SAD}
