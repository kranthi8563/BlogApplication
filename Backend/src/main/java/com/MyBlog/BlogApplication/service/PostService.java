package com.MyBlog.BlogApplication.service;

import com.MyBlog.BlogApplication.dto.PostRequest;
import com.MyBlog.BlogApplication.dto.PostResponse;
import com.MyBlog.BlogApplication.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PostService {

    Post createPost(Post post);

    List<Post> getAllPosts();

    Post getPostById(Long id);

    Post updatePost(Long id,Post updatedPost);

    void deletePost(Long id);

    Post patchPost(Long id, Post partialUpdate);

    Post likedPost(Long id);
}
