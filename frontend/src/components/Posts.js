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

  const handleLike = async (postId) => {
    try {
      const response = await API.post(`/api/posts/${postId}/like`);
      const updatedPost = response.data;

      setPosts((prevPosts) =>
        prevPosts.map((post) =>
          post.id === updatedPost.id ? updatedPost : post
        )
      );
    } catch (error) {
      console.error("Error liking post:", error.response || error);
    }
  };


  if (loading) return <p>Loading posts...</p>;

  return (
    <div>
      <h2>Posts</h2>
      {posts.length === 0 ? (
        <p>No posts available.</p>
      ) : (
        <ul>
          {posts.map((post) => (
            <li key={post.id} style={{ marginBottom: "20px" }}>
              <h3>{post.title}</h3>
              <p>{post.content}</p>
              <small>Author: {post.author}</small>
               <div>
                    <button onClick={() => handleLike(post.id)}>👍 Like</button>
                         <span style={{ marginLeft: "10px" }}>
                                {post.likeCount ||  0} likes
                         </span>
               </div>
            </li>
          ))}
        </ul>
      )}
    </div>
  );
}

export default Posts;
