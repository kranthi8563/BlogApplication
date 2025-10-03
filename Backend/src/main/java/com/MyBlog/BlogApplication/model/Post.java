package com.MyBlog.BlogApplication.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;


import java.time.Instant;


@Entity
public class Post {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message="Please Provide the title")
    private String title;
    @Column(columnDefinition = "TEXT")
    @NotBlank(message="Please Provide the Content")
    private String content;
    @NotBlank(message = "Please Provide the Author Name")
    private String Author;
    private Instant createdAt = Instant.now();
    private Long likeCount = 0L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Long getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Long likeCount) {
        this.likeCount = likeCount;
    }
}
