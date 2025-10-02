package com.MyBlog.BlogApplication.controller;


import com.MyBlog.BlogApplication.service.LikeService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/post")
public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService){
        this.likeService=likeService;
    }

    @PostMapping("/{postId}/like")
    public String likePost(@PathVariable Long postId, Authentication authentication) {
        String username = authentication.getName();
        return likeService.likePost(postId, username);
    }

    @DeleteMapping("/{postId}/unlike")
    public String unlikePost(@PathVariable Long postId, Authentication authentication) {
        String username = authentication.getName();
        return likeService.unlikePost(postId, username);
    }

    @GetMapping("/{postId}/likes")
    public long getLikesCount(@PathVariable Long postId) {
        return likeService.getLikesCount(postId);
    }
}
