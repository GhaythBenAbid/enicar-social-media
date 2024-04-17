package Controllers;

import Models.Event;
import Repositories.UserRepository;
import Utils.CloudinarySDK;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/post")
@CrossOrigin(origins = "http://localhost:4200/")
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<?> createPost(@RequestParam("content") String content, @RequestParam(value = "image", required = false) MultipartFile image, @RequestParam("tags") String tags, @RequestParam("author") int author, @RequestParam("visibility") boolean visibility) throws IOException {
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
