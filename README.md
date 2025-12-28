# 🎓 AI-Powered Quiz App (Backend)

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.7-6DB33F?logo=springboot&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring%20Security-Enabled-6DB33F?logo=springsecurity&logoColor=white)
![Spring AI](https://img.shields.io/badge/Spring%20AI-Integrated-6DB33F?logo=spring&logoColor=white)
![OpenAI](https://img.shields.io/badge/OpenAI-API-412991?logo=openai&logoColor=white)
![OpenRouter](https://img.shields.io/badge/OpenRouter-API-0f172a)
![JJWT](https://img.shields.io/badge/JJWT-JWT%20Auth-000000)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Database-336791?logo=postgresql&logoColor=white)
![Spring Data JPA](https://img.shields.io/badge/Spring%20Data%20JPA-Repository-6DB33F?logo=spring&logoColor=white)
![Apache PDFBox](https://img.shields.io/badge/Apache%20PDFBox-3.0.6-D22128?logo=apache&logoColor=white)
![Lombok](https://img.shields.io/badge/Lombok-Annotations-BC4E9C)
![Dotenv](https://img.shields.io/badge/Dotenv-Env%20Config-2b7e51)
![Jackson](https://img.shields.io/badge/Jackson-JSON-000000)
![Maven](https://img.shields.io/badge/Maven-Build-C71A36?logo=apachemaven&logoColor=white)
![Java](https://img.shields.io/badge/Java-17-007396?logo=openjdk&logoColor=white)


This is the core engine for the **AI-Powered Quiz Application**. It leverages **Spring AI** to transform text or PDF documents into interactive quizzes, managed through a secure **JWT-based** architecture.

> [!IMPORTANT]
> This service is designed to be paired with the **[AI-Powered Quiz App Frontend](https://github.com/ThilinaJayamal/AI-powerd-Quiz-App-Frontend)** for a complete user experience.

---

## ✨ Core Features

* **🧠 AI Question Generation**: Generate high-quality multiple-choice questions from any topic description or uploaded PDF file using Spring AI.
* **🔒 Secure Authentication**: Robust user management with registration, login, and stateless JWT authentication.
* **🤝 Quiz Collaboration**: Share quizzes with other users via email to allow them access.
* **📊 Analytics & Tracking**: Track quiz attempts and view summarized performance analytics.
* **📄 PDF Integration**: Automatic text extraction from PDF documents for context-aware quiz generation.

---

## 🛠️ Tech Stack

* **Framework**: Spring Boot 3.5.7
* **AI**: Spring AI with OpenAI/OpenRouter integration
* **Security**: Spring Security & JJWT
* **Database**: PostgreSQL with Spring Data JPA
* **PDF Library**: Apache PDFBox 3.0.6
* **Utilities**: Lombok, Dotenv, Jackson

---

## 📂 Project Structure

The project follows a clean, modular Spring Boot architecture:

```text
src/main/java/com/thilina271/AI/powered/Quiz/app/
├── config/      # Security & CORS configurations
├── controller/  # REST API Endpoints
├── dto/         # Data Transfer Objects for API communication
├── exception/   # Custom exception handling
├── filter/      # JWT Authentication filters
├── mapper/      # Object mapping logic
├── model/       # JPA Entities (User, Quiz, Question, etc.)
├── repository/  # Database access interfaces
├── service/     # Business logic & AI interaction
└── validation/  # Request validation groups

```

---

## 🚀 Getting Started

### 1. Prerequisites

* **JDK 17** or higher.
* **PostgreSQL** (running on port 5432).
* **OpenAI/OpenRouter API Key**.

### 2. Installation & Run

```bash
# Clone the repository
git clone https://github.com/ThilinaJayamal/AI-powerd-Quiz-App-Backend.git
cd AI-powerd-Quiz-App-Backend

# Setup environment variables
# Create a .env file in the root directory:
DB_PASSWORD=your_postgres_password
OPENAI_API_KEY=your_api_key

# Run the application
./mvnw spring-boot:run

```

The application will be accessible at `http://localhost:8080`.

---

## 🔌 API Endpoints

### Authentication (`/api/auth`)

* `POST /register`: Register a new user.
* `POST /login`: Authenticate and receive a JWT.
* `POST /logout`: Invalidate session.
* `GET /me`: Get current authenticated user details.

### Question Generation (`/api/question`)

* `GET /`: Generate questions from a `description` and `size`.
* `POST /upload`: Upload a PDF to generate questions from its content.

### Quiz Management (`/api/quiz`)

* `POST /`: Save a generated quiz.
* `GET /{id}`: Fetch a specific quiz.
* `GET /all`: Fetch all quizzes created by the user.
* `DELETE /{id}`: Remove a quiz.

### Sharing & Collaboration (`/api/quiz/share`)

* `POST /{id}?email={user_email}`: Share a quiz with another user.
* `GET /{id}`: View share details for a quiz.
* `GET /all`: View all quizzes shared with you.
* `DELETE /delete`: Revoke access for a shared user.

### Attempts & Analytics

* `POST /api/attempt/quiz/{id}`: Submit answers for a quiz.
* `GET /api/attempt/quiz/all`: View all previous quiz attempts.
* `GET /api/analytics/summary`: Get overall performance statistics.

---

## 🤝 Contributing

We welcome contributions to improve the AI logic or add new features!

1. **Fork** the project.
2. **Create** your feature branch: `git checkout -b feature/AmazingFeature`.
3. **Commit** changes: `git commit -m 'Add some AmazingFeature'`.
4. **Push** to branch: `git push origin feature/AmazingFeature`.
5. **Open** a Pull Request.

---

## 👤 Author

**Thilina Jayamal** - [@ThilinaJayamal](https://github.com/ThilinaJayamal)

---

## 📄 License

This project is licensed under the MIT License.
