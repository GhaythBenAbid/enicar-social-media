package Dto;

import Models.Post;
import Models.ReactionType;
import Models.User;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
public class ReactionDto {
    private Long id;
    private User user;
    private ReactionType type;
    private String comment;
    private Date date;
}



