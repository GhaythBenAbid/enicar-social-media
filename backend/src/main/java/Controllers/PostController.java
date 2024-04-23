package Controllers;

import Dto.PostDto;
import Models.Event;
import Repositories.UserRepository;
import Utils.CloudinarySDK;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import Models.Post;

import Services.PostService;

import Utils.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/post")
@CrossOrigin(origins = "http://localhost:4200/")
@Slf4j
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<?> createPost(@RequestParam("content") String content, @RequestParam(value = "image", required = false) MultipartFile image, @RequestParam("tags") String tags, @RequestParam("author") int author, @RequestParam("visibility") boolean visibility) throws IOException {
        log.info("Starting Create Post ");
        Post post = new Post();
        post.setContent(content);
        try{
        String imageLink = CloudinarySDK.imageUpload(image);
        post.setImage(imageLink);
        }catch (Exception e){
            post.setImage(null);
        }

        String[] tagsArray = tags.split(",\\s*");
        post.setTags(List.of(tagsArray));
        post.setAuthor(userRepository.findById((long) author).get());
        post.setDate(new Date());
        post.setVisibility(visibility);

        try {
            Post newPost = this.postService.CreatePost(post);
            log.info("Post created Successfuly : {}" ,newPost.getId() );
            return new ResponseEntity(newPost, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error occured while creating postId : {}",post.getId(),e );
            return new ResponseEntity(new ApiResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/{postID}")
    public ResponseEntity<Object> updatePost(@PathVariable("postID") int postID,@RequestBody Post post) {
        log.info("Starting update for Post ID : {}", post.getId());
        try {
            this.postService.UpdatePost(postID, post);

            HashMap<String, Object> response = new HashMap<>();
            response.put("message", "Post Updated Successfully");

            return new ResponseEntity<>(response, HttpStatus.OK);

        }
        catch( DataAccessException e) {
            log.error("Database access error occurred while updating Post ID: {}", post.getId(), e);
            HashMap<String, Object> response = new HashMap<>();

            response.put("message", "An error occurred while accessing the database. Please try again later.");
            // Return a 500 Internal Server Error response
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);


        }catch(Exception e){
            log.error("Error occured while updating PostId : ",postID,e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping
    public ResponseEntity<List<PostDto>> getALLPostDetails(){
        return  new ResponseEntity<>(this.postService.getAllPosts(),HttpStatus.OK) ;}

    @GetMapping("/{postID}")
    public  ResponseEntity<Object> getPostDetails(@PathVariable("postID") int postID) {
        log.info("Fetching details for Post ID: {}", postID);
        Models.Post post = postService.getPostInfo(postID).orElse(null);
        if (post != null) {
            log.info("Post details fetched successfully for Post ID: {}", postID);
            return new ResponseEntity<>(post, HttpStatus.OK);
        } else {
            log.error("Post not found with id: {}", postID);
            return new ResponseEntity<>(new ApiResponse(HttpStatus.NOT_FOUND.value(), "Post not found with id: " + postID), HttpStatus.NOT_FOUND);
        }
    }


    @DeleteMapping ("/{postID}")
    public ResponseEntity<Object> DeletePost(@PathVariable("postID") Long postID) {
        log.info("Deleting Post : {} ",postID);
        try {
        postService.DeletePost(postID);
        log.info("Post deleted successfully : {} ",postID);
        return new ResponseEntity<>(new ApiResponse(HttpStatus.OK.value(), "Post deleted successfully"), HttpStatus.OK);
    }
        catch (Exception e){
            log.error("Error occurred while deleting post info", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }


}}
