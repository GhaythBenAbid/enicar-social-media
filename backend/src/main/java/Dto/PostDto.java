package Dto;

import Models.Reaction;
import Models.User;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class PostDto {
    private Long id;
    private String content;
    private String image;
    private User author;
    private Date date;
    private List<String> tags;
    private boolean visibility;
    private List<ReactionDto> reactions;

}
