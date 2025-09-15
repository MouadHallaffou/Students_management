# 📚 Student Management System

A Spring Boot application for managing student information with MySQL database integration.

## ✨ Features

- 👥 Student CRUD operations (Create, Read, Update, Delete)
- 🔗 RESTful API endpoints
- 🗄️ MySQL database integration
- 📊 Spring Data JPA for data persistence

## 🛠️ Technologies Used

- **☕ Java** - Programming language
- **🚀 Spring Boot** - Application framework
- **📋 Spring Data JPA** - Data access layer
- **🗃️ MySQL** - Database
- **📦 Maven** - Dependency management

## 📋 Prerequisites

- ☕ Java 8 or higher
- 🗃️ MySQL 8.0+
- 📦 Maven 3.6+

## ⚙️ Setup Instructions

1. 📂 Clone the repository
```bash
git clone https://github.com/MouadHallaffou/Students_management
cd Student_management
```

2. 🗄️ Configure MySQL database
```sql
CREATE DATABASE student_management;
```

3. ⚙️ Update `application.properties`
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/student_management
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

4. ▶️ Run the application
```bash
mvn spring-boot:run
```

## 🔌 API Endpoints

- `GET /api/students/getAll` - 📋 Get all students
- `POST /api/student/create` - ➕ Create new student
- `GET /api/student/{id}/get` - 🔍 Get student by ID
- `PUT /api/student/{id}/update` - ✏️ Update student
- `DELETE /api/student/{id}/delete` - 🗑️ Delete student

## 📄 License

This project is licensed under the MIT License.
