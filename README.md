# Smart Printing Service System

## Project Overview

The **Smart Printing Service System** is a web-based application designed to streamline the process of printing services. It enables users to upload documents, select print configurations, and manage printing tasks efficiently. The system integrates a **Next.js** frontend, a **Spring Boot** backend, and a **MongoDB** database for a modern and scalable solution.

---

## Features

- **User Authentication**: Secure login and registration for users.
- **Document Upload**: Users can upload documents for printing.
- **Print Configuration**: Customize print settings such as page size, orientation, color, and number of copies.
- **Order Tracking**: Monitor the status of printing tasks in real time.
- **Admin Dashboard**: Manage users, print requests, and system configurations.
- **Notification System**: Receive updates on print request statuses.

---

## Technologies Used

### Frontend
- **Framework**: [Next.js](https://nextjs.org/)
- **Styling**: CSS/SCSS, Tailwind CSS
- **State Management**: Redux/Context API

### Backend
- **Framework**: [Spring Boot](https://spring.io/projects/spring-boot)
- **API**: RESTful API with Swagger documentation
- **Security**: Spring Security with JWT for authentication

### Database
- **Database**: [MongoDB](https://www.mongodb.com/)
- **ODM**: Spring Data MongoDB

---

## Installation and Setup

### Prerequisites
Ensure you have the following installed:
- Node.js (>= 20.x)
- Java (>= 23)
- MongoDB (>= 8.x)

### Frontend (Next.js)
1. Navigate to the `frontend` folder:
   ```bash
   cd frontend
   ```
2. Install dependencies:
   ```bash
   npm install
   ```
3. Set up environment variables in a `.env.local` file:
   ```env
   NEXT_PUBLIC_API_BASE_URL=http://localhost:8080/api
   ```
4. Start the development server:
   ```bash
   npm run dev
   ```

### Backend (Spring Boot)
1. Navigate to the `backend` folder:
   ```bash
   cd backend
   ```
2. Build the project:
   ```bash
   ./mvnw clean package
   ```
3. Set up environment variables in `application.properties` or `application.yml`:
   ```properties
   spring.data.mongodb.uri=mongodb://localhost:27017/smart-printing
   jwt.secret=your_secret_key
   ```
4. Run the application:
   ```bash
   ./mvnw spring-boot:run
   ```

---

## API Endpoints

### Authentication
- `POST /api/auth/register` - Register a new user
- `POST /api/auth/login` - Authenticate and retrieve a token

### Print Requests
- `GET /api/prints` - Get all print requests (Admin only)
- `POST /api/prints` - Create a new print request
- `PUT /api/prints/{id}` - Update a print request
- `DELETE /api/prints/{id}` - Delete a print request

### Users
- `GET /api/users` - Get all users (Admin only)
- `GET /api/users/{id}` - Get user details
- `PUT /api/users/{id}` - Update user details

---

## Deployment

### Frontend
1. Build the production version:
   ```bash
   npm run build
   ```
2. Deploy using services like Vercel or Netlify.

### Backend
1. Package the application:
   ```bash
   ./mvnw package
   ```
2. Deploy the JAR file on a server or containerize using Docker.

---

## Contributing

1. Fork the repository.
2. Create a feature branch:
   ```bash
   git checkout -b feature/your-feature
   ```
3. Commit changes:
   ```bash
   git commit -m "Add your message here"
   ```
4. Push to your forked repository:
   ```bash
   git push origin feature/your-feature
   ```
5. Create a pull request.

---

## License

This project is licensed under the MIT License. See the `LICENSE` file for details.

---
