# Blog Application

This is a **Blog Application** built using **Java Spring Boot** for the backend and **ReactJS** for the frontend. The application allows users to create, view, and interact with blog posts.  

The goal of this project is to create a fully functional blogging platform where users can write posts, view posts by others, and like them.  

---

## Features Completed So Far ✅

1. **User Authentication & Authorization**
   - Users can **register** and **login**.
   - Users can **access pages based on their role** (normal user or admin).

2. **Posts**
   - Users can **create a new blog post**.
   - Users can **view all posts** on the posts page.
   - Each post shows **title, content, author, and like count**.
   - Users can **like a post**, but **one user can like a post only once**.

3. **User Interface**
   - Frontend is built using **ReactJS**.
   - Pages include:
     - **Home Page**: Shows all posts with likes.
     - **Login & Registration Pages**
     - **Create Post Page**
   - Posts are displayed in **order of most liked first**.

4. **Backend**
   - Backend is built using **Java Spring Boot**.
   - REST APIs are implemented for **user authentication, creating posts, fetching posts, and liking posts**.
   - **Database** (PostgreSQL) stores all users, posts, and likes.

---

## How the Application Works

1. **Register/Login**  
   Users create an account or login using existing credentials.  

2. **Create a Post**  
   Authenticated users can write a new post with a title and content.  

3. **View Posts**  
   All users can see a list of posts, sorted by **most liked first**.  

4. **Like a Post**  
   Users can like posts, but only once per post.  

---

## Technologies Used

- **Backend**: Java, Spring Boot, Spring Security, JWT Authentication, PostgreSQL  
- **Frontend**: ReactJS, Axios for API calls, CSS for styling  
- **Tools**: IntelliJ IDEA, Visual Studio Code, Postman  

---

## Current Status

- Major functionalities like **user authentication, post creation, viewing posts, and liking posts** are complete.  
- Some features are still under development and will be added later.  

---

## Future Improvements

- Fully implement **admin functionalities** like editing or deleting any post.  
- Add **comments section** for each post.  
- Enhance **frontend styling and responsiveness**.  
- Host the application on the cloud for public access.  

---

**Note:** This application is still **not fully completed**. I will update this repository once all features are implemented and fully functional.
