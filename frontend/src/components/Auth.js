import React, { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import "./Auth.css";

function Auth() {
  const [isLogin, setIsLogin] = useState(true);
  const [formData, setFormData] = useState({ username: "", email: "", password: "" });
  const navigate = useNavigate();

  // redirect if already logged in
  useEffect(() => {
    if (localStorage.getItem("token")) {
      navigate("/posts");
    }
  }, [navigate]);

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      if (isLogin) {
        // LOGIN
        const response = await axios.post("http://localhost:8081/api/auth/login", {
          username: formData.username,
          password: formData.password,
        });
        localStorage.setItem("token", response.data.token);
        localStorage.setItem("username", response.data.username);
        alert("Login successful!");
        navigate("/posts");
      } else {
        // REGISTER
        await axios.post("http://localhost:8081/api/auth/register", formData);
        alert("Registration successful!");
        setIsLogin(true);
      }
    } catch (err) {
      console.error(err);
      alert("Something went wrong!");
    }
  };

  return (
    <div className="auth-container">
      <div className="auth-box">
        <h2 className="app-title">BlogPozts</h2>
        <h3>{isLogin ? "Login" : "Register"}</h3>

        <form onSubmit={handleSubmit}>
          <input
            type="text"
            name="username"
            placeholder="Username"
            value={formData.username}
            onChange={handleChange}
            required
          />
          {!isLogin && (
            <input
              type="email"
              name="email"
              placeholder="Email"
              value={formData.email}
              onChange={handleChange}
              required
            />
          )}
          <input
            type="password"
            name="password"
            placeholder="Password"
            value={formData.password}
            onChange={handleChange}
            required
          />
          <button type="submit">{isLogin ? "Login" : "Register"}</button>
        </form>

        <p className="toggle-text">
          {isLogin ? (
            <>
              Don’t have an account?{" "}
              <span onClick={() => setIsLogin(false)}>Register here</span>
            </>
          ) : (
            <>
              Already have an account?{" "}
              <span onClick={() => setIsLogin(true)}>Login here</span>
            </>
          )}
        </p>
      </div>
    </div>
  );
}

export default Auth;
