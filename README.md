
# 🏠 Real Estate Trading Platform

A fullstack web application built for managing real estate properties, user accounts, and customer transactions. Designed with a scalable backend and a responsive user interface, the platform supports role-based access and advanced property search across 16 customizable fields.

## 🚀 Features

### ✅ Authentication & Authorization
- User login & registration
- Role-based access control (Manager, Staff, User)

### 🧑‍💼 Account Management
- Create, edit, delete, and search user accounts
- Permission-based operation control

### 🏢 Property Management
- Add, update, delete properties
- Search by 16 fields (name, price, area, district,...)
- Assign properties to staff

### 👥 Customer & Transaction Handling
- Assign customers to staff
- Manage transaction flows between customers and agents

### 🌐 Responsive UI
- Clean and responsive design built with Bootstrap
- Supports desktop views

## 🛠 Tech Stack

### 🖥 Backend
- Java, Spring Boot, Spring MVC, Spring Security
- JPA / Hibernate, Spring Data JPA
- RESTful API design

### 💻 Frontend
- HTML, CSS, JavaScript
- Bootstrap
- JSP, jQuery, AJAX

### 🗄 Database
- MySQL

### 🔧 Architecture
- 3-layer architecture (Controller - Service - Repository)
- Clear separation of concerns for scalability and maintainability



## 📦 Setup Instructions

1. Clone this repository:
   git clone https://github.com/your-username/real-estate-trading.git
2. Open the project in IntelliJ

3. Configure MySQL database:
   - Copy code in database/insert_database.sql file and run it in your DBMS
   - Update `application.properties` with your DB credentials

4. Run the project:
5. Access the website at `http://localhost:8080`

## 🧠 Future Improvements
- Add unit & integration tests
- Support property image uploads
- Export transaction reports to PDF
- Add user analytics dashboard

## 🙋 Author

**Ngo Tran Quang Dat** – [LinkedIn](https://linkedin.com/in/ntqdat) | [Email](mailto:ngotranquangdat2511@gmail.com)

