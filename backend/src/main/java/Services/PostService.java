package Services;


import Dto.PostDto;
import Dto.ReactionDto;
import Models.Event;
import Models.Post;
import Models.Reaction;
import Models.User;
import Repositories.PostRepository;
import Repositories.ReactionRepository;
import Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;


import Repositories.UserRepository;
import Utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Optional;

import java.util.List;
@Service
public class PostService {

    private final PostRepository postRepo;
    private final UserRepository userRepo;

    private final ReactionRepository reactionRepository;

    @Autowired
    public PostService(PostRepository postRepo, UserRepository userRepo , ReactionRepository reactionRepository){
        this.postRepo = postRepo;
        this.userRepo = userRepo;
        this.reactionRepository = reactionRepository;
    }

    @Autowired
    private StorageService storageService;

    public Post CreatePost(Post post){
        postRepo.save(post);
        return post;
    }


    public Post UpdatePost(long postID , Post post){
        Post existingPost = postRepo.findById(postID).get() ;
        existingPost.setContent(post.getContent());
        existingPost.setAuthor(post.getAuthor());
        existingPost.setImage(post.getImage());
        existingPost.setDate(post.getDate());
        existingPost.setTags(post.getTags());
        return postRepo.save(existingPost) ;
    }
    public Post getPost(long postID){
        return postRepo.findById(postID).orElse(null);
    }
    public void DeletePost(long postID){
        postRepo.deleteById(postID);

    }

    public List<Post> getALLPostInfo() {

        return postRepo.findAll();

    }
    public Optional<Post> getPostInfo(long postID) {return postRepo.findById(postID);}


    public List<PostDto> getAllPosts(){
        List<Post> posts = postRepo.findAll();
        List<PostDto> postDtos = new ArrayList<>();
        for(Post post : posts){
            PostDto postDto = new PostDto();
            postDto.setId(post.getId());
            postDto.setContent(post.getContent());
            postDto.setAuthor(post.getAuthor());
            postDto.setImage(post.getImage());
            postDto.setDate(post.getDate());
            postDto.setTags(post.getTags());
            postDto.setVisibility(post.isVisibility());


            List<Reaction> reactions = reactionRepository.getReactionsByPost_Id(post.getId());
            List<ReactionDto> reactionDtos = new ArrayList<>();
            for(Reaction reaction : reactions){
                ReactionDto reactionDto = new ReactionDto();
                reactionDto.setId(reaction.getId());
                reactionDto.setUser(reaction.getUser());
                reactionDto.setComment(reaction.getComment());
                reactionDto.setType(reaction.getType());
                reactionDto.setDate(reaction.getDate());
                reactionDtos.add(reactionDto);
            }

            postDto.setReactions(reactionDtos);


            postDtos.add(postDto);

        }
        return postDtos;
    }













}