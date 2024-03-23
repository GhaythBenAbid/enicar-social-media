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
public class Post {
    @Id
    private Long id;


    private String title;

    private String content;

    @ManyToOne
    private User author;

    private String date;

    @Column(columnDefinition = "JSON")
    @ElementCollection
    private List<String> tags;

    private String visibility;

}
