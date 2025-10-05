import React, { useEffect, useState } from "react";
import axios from "axios";
import "./Posts.css";

function Posts() {
  const [posts, setPosts] = useState([]);
  const [loading, setLoading] = useState(true);
  const token = localStorage.getItem("token");
  const currentUser = localStorage.getItem("username");


  useEffect(() => {
    fetchPosts();
  }, []);

  const fetchPosts = async () => {
    try {
      const res = await axios.get("http://localhost:8081/api/posts", {
        headers: { Authorization: `Bearer ${token}` },
      });
      const postsWithMenu = res.data.map((p) => ({ ...p, menuOpen: false }));
      setPosts(res.data);
    } catch (err) {
      console.error("Error fetching posts:", err);
    } finally {
      setLoading(false);
    }
  };

  const toggleLike = async (postId) => {
    try {
      const res = await axios.post(
        `http://localhost:8081/api/posts/${postId}/like`,
        {},
        { headers: { Authorization: `Bearer ${token}` } }
      );
      setPosts((prev) => prev.map((p) => (p.id === postId ? res.data : p)));
    } catch (err) {
      console.error("Error toggling like:", err);
    }
  };


  const handleDelete = async (postId) => {
    if (!window.confirm("Are you sure you want to delete this post?")) return;
    try {
      await axios.delete(`http://localhost:8081/api/posts/${postId}`, {
        headers: { Authorization: `Bearer ${token}` },
      });
      setPosts((prev) => prev.filter((p) => p.id !== postId));
    } catch (err) {
      console.error("Error deleting post:", err);
      alert("Failed to delete post");
    }
  };

  const toggleMenu = (postId) => {
      setPosts((prev) =>
        prev.map((p) =>
          p.id === postId ? { ...p, menuOpen: !p.menuOpen } : p
        )
      );
    };

  return (
    <div className="posts-page">
      <h2>All Posts</h2>

      {loading ? (
        <p>Loading posts...</p>
      ) : posts.length === 0 ? (
        <p>No posts available.</p>
      ) : (
        <div className="posts-container">
          {posts.map((post) => {
               const authorName =
                          typeof post.author === "string" ? post.author : post.author.username;
                        const isOwner = authorName === currentUser;
                        console.log("Post:", post.title, "Author:", post.author, "CurrentUser:", currentUser);


                        return (
                          <div key={post.id} className="post-card">
                            <div className="post-header">
                              <h3>{post.title}</h3>

                              {/* Three-dot menu visible only to post owner */}
                              {isOwner && (
                                <div className="post-menu">
                                  <button
                                    className="menu-btn"
                                    onClick={() => toggleMenu(post.id)}
                                  >
                                    ⋮
                                  </button>
                                  {post.menuOpen && (
                                    <div className="menu-dropdown">
                                      <button onClick={() => handleDelete(post.id)}>
                                        Delete
                                      </button>
                                      <button onClick={() => alert("Edit logic here")}>
                                        Edit
                                      </button>
                                    </div>
                                  )}
                                </div>
                              )}
                            </div>

                            <p>{post.content}</p>
                            <small>Author: {authorName}</small>

                            <div className="likes-section">
                              <button
                                className={post.likedByCurrentUser ? "liked" : ""}
                                onClick={() => toggleLike(post.id)}
                              >
                                👍 {post.likedByCurrentUser ? "Liked" : "Like"} (
                                {post.likeCount})
                              </button>
                            </div>
                          </div>
                        );
                      })}
                    </div>
                  )}
                </div>
              );
            }

            export default Posts;