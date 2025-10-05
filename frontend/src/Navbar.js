import React from "react";
import { Link, useNavigate } from "react-router-dom";
import "./Navbar.css";
import { FaHome, FaPlus } from "react-icons/fa";

function Navbar() {
  const navigate = useNavigate();

  const handleLogout = () => {
    localStorage.removeItem("token");
    navigate("/");
  };

  return (
    <>
      {/* App Title fixed at top-left */}
     <h2 className="app-title">
       <span className="logo-blog">Blog</span>
       <span className="logo-pozts">Pozts</span>
     </h2>


      {/* Sidebar */}
      <div className="sidebar">
        <ul className="menu">
          <li>
            <Link to="/posts">
              <FaHome className="icon" />
              <span className="link-text">Home</span>
            </Link>
          </li>
          <li>
            <Link to="/create-post">
              <FaPlus className="icon" />
              <span className="link-text">Create</span>
            </Link>
          </li>
        </ul>
      </div>

      {/* Top bar with logout */}
      <div className="topbar">
        <button className="logout-btn" onClick={handleLogout}>
          Logout
        </button>
      </div>
    </>
  );
}

export default Navbar;
