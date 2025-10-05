import React from "react";
import { BrowserRouter as Router, Routes, Route, useLocation } from "react-router-dom";
import Navbar from "./Navbar";
import Auth from "./components/Auth";
import Posts from "./components/Posts";
import CreatePost from "./components/CreatePost";
import ProtectedRoute from "./components/ProtectedRoute";

function App() {
  return (
    <Router>
      <MainLayout />
    </Router>
  );
}

function MainLayout() {
  const location = useLocation();
  const isLoggedIn = !!localStorage.getItem("token");

  return (
    <>
      {/* Show Navbar only if logged in */}
      {isLoggedIn && <Navbar />}

      <Routes>
        <Route path="/" element={<Auth />} />
        <Route
          path="/posts"
          element={
            <ProtectedRoute>
              <Posts />
            </ProtectedRoute>
          }
        />
        <Route
          path="/create-post"
          element={
            <ProtectedRoute>
              <CreatePost />
            </ProtectedRoute>
          }
        />
      </Routes>
    </>
  );
}

export default App;
