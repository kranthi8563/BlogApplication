package com.MyBlog.BlogApplication.controller;


import com.MyBlog.BlogApplication.model.Post;
import com.MyBlog.BlogApplication.service.PostService;
import com.MyBlog.BlogApplication.service.PostServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {

    private PostServiceImpl postService;

    public PostController(PostServiceImpl postService){
        this.postService = postService;
    }

    @PostMapping("")
    public Post createPost(@RequestBody Post post){
        return postService.createPost(post);
    }

    @GetMapping("")
    public List<Post> getAllPosts(){
        return postService.getAllPosts();
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

    @PatchMapping("/{id}/like")
    public ResponseEntity<Post> likeCount(@PathVariable Long id){
        Post likedPost = postService.likedPost(id);
        return ResponseEntity.ok(likedPost);

    }


}
