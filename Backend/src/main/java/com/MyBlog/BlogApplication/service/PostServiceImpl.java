package com.MyBlog.BlogApplication.service;

import com.MyBlog.BlogApplication.model.Post;
import com.MyBlog.BlogApplication.model.User;
import com.MyBlog.BlogApplication.repository.PostRepository;
import com.MyBlog.BlogApplication.repository.UserRepository;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl{

    private PostRepository postRepository;
    private UserRepository userRepository;

    public PostServiceImpl(PostRepository postRepository,UserRepository userRepository){
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }



    public Post createPost(Post post){
        return postRepository.save(post);
    }

    public List<Post> getAllPostsSortedByLikes() {
        List<Post> posts = postRepository.findAllOrderByLikesDesc();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = auth != null ? auth.getName() : null;

        if (currentUsername != null) {
            posts.forEach(post -> {
                boolean liked = post.getLikedUsers().stream()
                        .anyMatch(user -> user.getUsername().equals(currentUsername));
                post.setLikedByCurrentUser(liked);
            });
        }

        return posts;
    }


    public  Post getPostById(Long id){
        Post post = postRepository.findById(id).orElse(null);

        if (post != null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String currentUsername = auth != null ? auth.getName() : null;

            if (currentUsername != null) {
                boolean liked = post.getLikedUsers().stream()
                        .anyMatch(user -> user.getUsername().equals(currentUsername));
                post.setLikedByCurrentUser(liked);
            }
        }

        return post;
    }


    public  Post updatePost(Long id,Post updatedPost){
        return postRepository.findById(id).map(post-> {
            post.setTitle(updatedPost.getTitle());
            post.setContent(updatedPost.getContent());
            post.setAuthor(updatedPost.getAuthor());
            return postRepository.save(post);
        }).orElse(null);
    }

    public void deletePost(Long id){
        postRepository.deleteById(id);
    }


   public Post patchPost(Long id, Post partialUpdate){
        return postRepository.findById(id).map(post -> {
            if (partialUpdate.getTitle() != null) {
                post.setTitle(partialUpdate.getTitle());
            }
            if (partialUpdate.getContent() != null) {
                post.setContent(partialUpdate.getContent());
            }
            if (partialUpdate.getAuthor() != null) {
                post.setAuthor(partialUpdate.getAuthor());
            }
            return postRepository.save(post);
        }).orElse(null);
   }


   public Post likedPost(Long postId, String username) {
       Post post = postRepository.findById(postId)
               .orElseThrow(() -> new RuntimeException("Post not found"));

       User user = userRepository.findByUsername(username)
               .orElseThrow(() -> new RuntimeException("User not found"));

       // Toggle like (add if not liked, remove if already liked)
       if (post.getLikedUsers().contains(user)) {
           post.removeLike(user);
       } else {
           post.addLike(user);
       }

       // Save updated post
       Post savedPost = postRepository.save(post);

       // Update "likedByCurrentUser" flag for frontend
       savedPost.setLikedByCurrentUser(savedPost.getLikedUsers().contains(user));

       return savedPost;
   }

}
