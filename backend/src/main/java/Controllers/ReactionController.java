package Controllers;


import Models.Reaction;
import Services.ReactionService;
import Utils.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/reaction")
public class ReactionController {
    // Your controller logic goes here
    ReactionService reactionService;
    @GetMapping("/{reactionID}")
    public  ResponseEntity<Object> getReactionDetails(@PathVariable("reactionID") long ReactionID) {
        Models.Reaction reaction = reactionService.GetReactionInfo(ReactionID).orElse(null);
        if (reaction != null) {
            return new ResponseEntity<>(reaction, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND.value(), "Reaction not found with id: " + ReactionID), HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping
    public ResponseEntity<List<Reaction>> getALLReactionDetails(){
        return  new ResponseEntity<>(this.reactionService.GetALLReactionInfo(),HttpStatus.OK) ;}

    @DeleteMapping ("/{reactionID}")
    public ResponseEntity<Object> DeleteReactionDetails(@PathVariable("reactionID") Long reactionID) {
        reactionService.DeleteReactionInfo(reactionID);
        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK.value(), "Reaction deleted successfully"), HttpStatus.OK);
    }

    @PutMapping("/{reactionID}")
    public ResponseEntity<Object> updateReactionDetails(@PathVariable("reactionID") int reactionID,@RequestBody Reaction reaction){
        this.reactionService.UpdateReactionInfo(reactionID,reaction);

        HashMap<String, Object> response = new HashMap<>();
        response.put("message", "Reaction Updated Successfully");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createReactionDetails(@RequestBody Reaction reaction){

        try {
            Reaction newReaction = this.reactionService.CreateReactionInfo(reaction);
            return new ResponseEntity(newReaction, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(new ApiResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

}
