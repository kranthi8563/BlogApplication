package com.MyBlog.BlogApplication.service;

import com.MyBlog.BlogApplication.exception.ResourceNotFoundException;
import com.MyBlog.BlogApplication.model.Post;
import com.MyBlog.BlogApplication.repository.PostRepository;
import com.MyBlog.BlogApplication.repository.UserRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostServiceImpl implements PostService{

    private PostRepository postRepository;
    private UserRepository userRepository;

    public PostServiceImpl(PostRepository postRepository,UserRepository userRepository){
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }


    @Override
    public Post createPost(Post post){
        return postRepository.save(post);
    }

    @Override
    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }
    @Override
    public  Post getPostById(Long id){
        return postRepository.findById(id).orElse(null);
    }

    @Override
    public  Post updatePost(Long id,Post updatedPost){
        return postRepository.findById(id).map(post-> {
            post.setTitle(updatedPost.getTitle());
            post.setContent(updatedPost.getContent());
            post.setAuthor(updatedPost.getAuthor());
            return postRepository.save(post);
        }).orElse(null);
    }
    @Override
    public void deletePost(Long id){
        postRepository.deleteById(id);
    }

    @Override
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

   @Override
   public Post  likedPost(Long id){
        Post post = postRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Post Not Found with id "+id));
        post.setLikeCount(post.getLikeCount()+1);
        return postRepository.save(post);

   }
}
