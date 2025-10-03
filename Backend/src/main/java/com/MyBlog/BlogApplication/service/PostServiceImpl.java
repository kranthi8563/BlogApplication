package com.MyBlog.BlogApplication.service;

import com.MyBlog.BlogApplication.model.Post;
import com.MyBlog.BlogApplication.model.User;
import com.MyBlog.BlogApplication.repository.PostRepository;
import com.MyBlog.BlogApplication.repository.UserRepository;

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
        return postRepository.findAllOrderByLikesDesc();
    }


    public  Post getPostById(Long id){
        return postRepository.findById(id).orElse(null);
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
       if (!post.getLikedUsers().contains(user)) {
           post.addLike(user);
           postRepository.save(post);
       }

       return post;
   }

}
