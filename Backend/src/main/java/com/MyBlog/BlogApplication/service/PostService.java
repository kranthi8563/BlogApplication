package com.MyBlog.BlogApplication.service;

import com.MyBlog.BlogApplication.model.Post;

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
