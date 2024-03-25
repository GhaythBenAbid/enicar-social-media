package Controllers;

import Models.Event;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import Models.Post;

import Services.PostService;

import Utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/post")
@CrossOrigin(origins = "http://localhost:4200/")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity  createPost(@RequestBody Post post){
        try {
            Post newPost = this.postService.CreatePost(post);
            return new ResponseEntity(newPost, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(new ApiResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/{postID}")
    public ResponseEntity<Object> updatePost(@PathVariable("postID") int postID,@RequestBody Post post){
        this.postService.UpdatePost(postID,post);

        HashMap<String, Object> response = new HashMap<>();
        response.put("message", "Post Updated Successfully");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<Post>> getALLPostDetails(){
        return  new ResponseEntity<>(this.postService.getALLPostInfo(),HttpStatus.OK) ;}

    @GetMapping("/{postID}")
    public  ResponseEntity<Object> getPostDetails(@PathVariable("postID") int postID) {
        Models.Post post = postService.getPostInfo(postID).orElse(null);
        if (post != null) {
            return new ResponseEntity<>(post, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND.value(), "Post not found with id: " + postID), HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping ("/{postID}")
    public ResponseEntity<Object> DeletePost(@PathVariable("postID") Long postID) {
        postService.DeletePost(postID);
        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK.value(), "Post deleted successfully"), HttpStatus.OK);
    }


}

