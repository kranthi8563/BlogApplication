package com.MyBlog.BlogApplication.repository;

import com.MyBlog.BlogApplication.model.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface PostRepository extends JpaRepository<Post,Long> {

}
