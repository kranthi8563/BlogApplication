package com.MyBlog.BlogApplication.controller;


import com.MyBlog.BlogApplication.model.Post;
import com.MyBlog.BlogApplication.service.PostServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostServiceImpl postService;

    public PostController(PostServiceImpl postService){
        this.postService = postService;
    }

    @PostMapping("")
    public Post createPost(@Valid @RequestBody Post post){
        return postService.createPost(post);
    }

    @GetMapping("")
    public List<Post> getAllPosts(){
        return postService.getAllPostsSortedByLikes();
    }

    @GetMapping("/{id}")
    public Post getPostById(@PathVariable Long id){
        return postService.getPostById(id);
    }

    @PutMapping("/{id}")
    public Post updatePost(@PathVariable Long id,@RequestBody Post updatedPost){
        return postService.updatePost(id,updatedPost);
    }

    @DeleteMapping("/{id}")
    public String deletePost(@PathVariable Long id){
        Post post = new Post();
        postService.deletePost(id);
        return "the post has been deleted";
    }

    @PatchMapping("/{id}")
    public Post patchPost(@PathVariable Long id, @RequestBody Post partialUpdate) {
        return postService.patchPost(id, partialUpdate);
    }

    @PostMapping("/{id}/like")
    public ResponseEntity<Post> likeCount(@PathVariable Long id, Principal principal) {
        String username = principal.getName();
        Post likedPost = postService.likedPost(id, username);
        return ResponseEntity.ok(likedPost);
    }


}
