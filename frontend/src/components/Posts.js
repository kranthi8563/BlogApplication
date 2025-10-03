import React, { useEffect, useState } from "react";
import API from "../API";

function Posts() {
  const [posts, setPosts] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchPosts = async () => {
      try {
        const response = await API.get("/api/posts"); // token auto attached
        setPosts(response.data);
      } catch (error) {
        console.error("Error fetching posts:", error.response || error);
        if (error.response && error.response.status === 403) {
          alert("Unauthorized! Please login again.");
          localStorage.removeItem("token");
          window.location.href = "/";
        }
      } finally {
        setLoading(false);
      }
    };

    fetchPosts();
  }, []);

  if (loading) return <p>Loading posts...</p>;

  return (
    <div>
      <h2>Posts</h2>
      {posts.length === 0 ? (
        <p>No posts available.</p>
      ) : (
        <ul>
          {posts.map((post) => (
            <li key={post.id}>
              <h3>{post.title}</h3>
              <p>{post.content}</p>
              <small>Author: {post.author}</small>
            </li>
          ))}
        </ul>
      )}
    </div>
  );
}

export default Posts;
