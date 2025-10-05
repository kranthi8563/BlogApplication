import React, { useState } from "react";
import axios from "axios";
import './CreatePost.css';

function CreatePost() {
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");


  const handleSubmit = async (e) => {
    e.preventDefault();

    const post = { title, content};
    const token = localStorage.getItem("token");

    try {
      await axios.post("http://localhost:8081/api/posts", post, {
        headers: {
          Authorization: `Bearer ${token}`, // ✅ Correct Authorization header
        },
      });
      alert("Post created successfully!");
      setTitle("");
      setContent("");
    } catch (error) {
      console.error("Error creating the post:", error.response || error);
      alert("Error creating the post");
    }
  };

  return (
    <div className="create-post-container">
                <div className="post-form-card">

                    <h2 className="form-title">
                        Share Your Thoughts
                    </h2>
                    <p className="form-subtitle">
                        Craft a compelling title and write the content for your new blog post.
                    </p>

                    <form onSubmit={handleSubmit} className="post-form">

                        {/* 1. Title Input */}
                        <div>
                            <label htmlFor="title" className="form-label">Post Title</label>
                            <input
                                id="title"
                                type="text"
                                placeholder="A captivating headline..."
                                value={title}
                                onChange={(e) => setTitle(e.target.value)}
                                required
                                className="form-input"
                            />
                        </div>



                        {/* 3. Content Textarea */}
                        <div>
                            <label htmlFor="content" className="form-label">Content</label>
                            <textarea
                                id="content"
                                placeholder="Start writing your long-form content here..."
                                rows="15"
                                value={content}
                                onChange={(e) => setContent(e.target.value)}
                                required
                                className="form-textarea"
                            ></textarea>
                        </div>

                        <button
                            type="submit"
                            className="publish-button"
                        > Publish Post</button>
                    </form>
                </div>
            </div>
  );
}

export default CreatePost;
