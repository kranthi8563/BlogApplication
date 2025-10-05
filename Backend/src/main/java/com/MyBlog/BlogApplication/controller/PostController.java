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
    public Post createPost(@Valid @RequestBody Post post,Principal principal){

        post.setAuthor(principal.getName());
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
    public ResponseEntity<?> updatePost(@PathVariable Long id,
                                        @RequestBody Post updatedPost,
                                        Principal principal) {
        Post existing = postService.getPostById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        if (!existing.getAuthor().equals(principal.getName())) {
            return ResponseEntity.status(403).body("You cannot edit this post");
        }
        return ResponseEntity.ok(postService.updatePost(id, updatedPost));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id, Principal principal) {
        Post existing = postService.getPostById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        if (!existing.getAuthor().equals(principal.getName())) {
            return ResponseEntity.status(403).body("You cannot delete this post");
        }
        postService.deletePost(id);
        return ResponseEntity.ok("Post deleted");
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
