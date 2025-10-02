import React, { useEffect, useState, useCallback } from "react";
import axios from "axios";

function Posts() {
  const [posts, setPosts] = useState([]);
  const token = localStorage.getItem("token");

  // Wrap fetchPosts in useCallback so its reference is stable
  const fetchPosts = useCallback(async () => {
    try {
      const response = await axios.get("http://localhost:8081/api/posts", {
        headers: { Authorization: `Bearer ${token}` },
      });
      setPosts(response.data);
    } catch (err) {
      console.error("Error fetching posts", err);
    }
  }, [token]); // token is a dependency

  // useEffect depends on fetchPosts
  useEffect(() => {
    fetchPosts();
  }, [fetchPosts]);

  const handleLike = async (id) => {
    try {
      await axios.post(
        `http://localhost:8081/api/posts/${id}/like`,
        {},
        { headers: { Authorization: `Bearer ${token}` } }
      );
      // Refresh posts to show updated like count
      fetchPosts();
    } catch (err) {
      console.error("Error liking post", err);
    }
  };

  return (
    <div className="posts-container">
      <h2>All Blog Posts</h2>
      {posts.length === 0 ? (
        <p>No posts available</p>
      ) : (
        posts.map((post) => (
          <div key={post.id} className="post-card">
            <h3>{post.title}</h3>
            <p>{post.content}</p>
            <small>Author: {post.author}</small>
            <div className="likes-section">
              <button onClick={() => handleLike(post.id)}>👍 Like</button>
              <span>{post.likeCount} Likes</span>
            </div>
          </div>
        ))
      )}
    </div>
  );
}

export default Posts;
