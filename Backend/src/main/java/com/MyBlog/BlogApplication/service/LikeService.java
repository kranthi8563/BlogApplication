package com.MyBlog.BlogApplication.service;


import com.MyBlog.BlogApplication.model.Like;
import com.MyBlog.BlogApplication.model.Post;
import com.MyBlog.BlogApplication.model.User;
import com.MyBlog.BlogApplication.repository.LikeRepository;
import com.MyBlog.BlogApplication.repository.PostRepository;
import com.MyBlog.BlogApplication.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class LikeService {
    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public LikeService(LikeRepository likeRepository, PostRepository postRepository, UserRepository userRepository) {
        this.likeRepository = likeRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public String likePost(Long postId, String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));

        // Check if already liked
        if (likeRepository.findByUserAndPost(user, post).isPresent()) {
            return "Already liked this post!";
        }

        Like like = new Like(user, post);
        likeRepository.save(like);
        return "Post liked successfully!";
    }

    public String unlikePost(Long postId, String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));

        Like like = (Like) likeRepository.findByUserAndPost(user, post)
                .orElseThrow(() -> new RuntimeException("Like not found"));

        likeRepository.delete(like);
        return "Post unliked!";
    }

    public long getLikesCount(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        return likeRepository.countByPost(post);
    }
}
