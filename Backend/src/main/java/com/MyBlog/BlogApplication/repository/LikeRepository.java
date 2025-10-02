package com.MyBlog.BlogApplication.repository;

import com.MyBlog.BlogApplication.model.Like;
import com.MyBlog.BlogApplication.model.Post;
import com.MyBlog.BlogApplication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like,Long> {


    Optional<Object> findByUserAndPost(User user, Post post);

    long countByPost(Post post);
}
