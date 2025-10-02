import React, { useState } from "react";
import axios from "axios";

function CreatePost() {
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");
  const [author, setAuthor] = useState("");  // NEW state for author

  const handleSubmit = async (e) => {
    e.preventDefault();

    const post = { title, content, author }; // include author
    const token = localStorage.getItem("token");

    try {
      await axios.post("http://localhost:8081/api/posts", post, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      alert("Post created successfully!");
      setTitle("");
      setContent("");
      setAuthor("");
    } catch (error) {
      console.error("Error creating the post:", error.response || error);
      alert("Error creating the post");
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <input
        type="text"
        placeholder="Title"
        value={title}
        onChange={(e) => setTitle(e.target.value)}
      />
      <textarea
        placeholder="Content"
        value={content}
        onChange={(e) => setContent(e.target.value)}
      />
      <input
        type="text"
        placeholder="Author"
        value={author}
        onChange={(e) => setAuthor(e.target.value)}
      />
      <button type="submit">Create Post</button>
    </form>
  );
}

export default CreatePost;
