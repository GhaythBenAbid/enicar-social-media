package Services;


import Models.Reaction;
import Repositories.PostRepository;
import Repositories.ReactionRepository;
import Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class ReactionService {
    // Your service logic goes here
    @Autowired
    ReactionRepository Myrepo ;

    @Autowired
    PostRepository Prepo ;

    @Autowired
    UserRepository Urepo ;




    public Reaction CreateReactionInfo(Reaction reaction)
    {
        if (reaction.getUser() == null  || !Urepo.existsById((long)reaction.getUser().getId())) {
            throw new IllegalArgumentException("User is required and must already exist in the database.");}
        if  (reaction.getPost() == null  || !Prepo.existsById(reaction.getPost().getId())){
            throw new IllegalArgumentException("Post is required and must already exist in the database.");}
        if (reaction.getType() == null  ) throw new IllegalArgumentException("Type is required");
        Myrepo.save(reaction);
        return reaction;
    }

    public Reaction UpdateReactionInfo(long ReactionID , Reaction reaction){

        if (Myrepo.existsById(ReactionID)  ){
            Reaction existingReaction = Myrepo.findById(ReactionID).get();

            if (reaction.getUser() == null  || !Urepo.existsById((long)reaction.getUser().getId())) {
                throw new IllegalArgumentException("User is required and must already exist in the database.");}
            if  (reaction.getPost() == null  || !Prepo.existsById(reaction.getPost().getId())){
                throw new IllegalArgumentException("Post is required and must already exist in the database.");}
            if (reaction.getType() == null  ) throw new IllegalArgumentException("Type is required");

            if (reaction.getUser() != null) {
                existingReaction.setUser(reaction.getUser());
            }
            if (reaction.getComment() != null) {
                existingReaction.setComment(reaction.getComment());
            }

            if (reaction.getPost() != null ) {
                existingReaction.setPost(reaction.getPost());
            }
            if (reaction.getType() != null) {
                existingReaction.setType(reaction.getType());
            }
            if (reaction.getDate() != null ){ existingReaction.setDate(reaction.getDate());}

            return Myrepo.save(existingReaction);
        }
        else { throw new IllegalArgumentException("Reaction not found with id: " + ReactionID);}
    }





    public void DeleteReactionInfo(Long ReactionID) {Myrepo.deleteById(ReactionID);}

    public List<Reaction> GetALLReactionInfo() {return Myrepo.findAll();}

    public Optional<Reaction> GetReactionInfo(Long ReactionID ){ return  Myrepo.findById(ReactionID);}



}
