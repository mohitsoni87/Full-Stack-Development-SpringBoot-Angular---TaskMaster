**Full Stack Development with Spring Boot and Angular - ToDo Application**

**Overview**
This GitHub repository, maintained by Mohit Soni, presents a comprehensive guide to developing a full-stack ToDo application using Spring Boot for the backend and Angular for the frontend. The project demonstrates how to create, read, update, and delete (CRUD) tasks in a to-do list, showcasing the integration between a Java-based backend and a JavaScript-based frontend.

**Key Features**

**Spring Boot Backend:**

Utilizes Spring Boot, to build the RESTful APIs for the ToDo - Task Manager application.
Implements CRUD operations for managing tasks.
Connects to a database to persist task data.


**Angular Frontend:**
Uses Angular, for building the user interface.
Provides a responsive and interactive UI for managing the to-do tasks.
Communicates with the Spring Boot backend through HTTP requests.
Project Structure:

**Backend:**
Controllers: Handle HTTP requests and responses.
Services: Contain business logic.
Repositories: Interact with the database.
Frontend:
Components: Define various UI elements.
Services: Manage HTTP communication with the backend.
Modules: Organize the application structure.
Getting Started
To run the project locally, follow these steps:

**Clone the Repository:**
git clone https://github.com/mohitsoni87/Full-Stack-Development-SpringBoot-Angular--ToDo.git

**Backend Setup:**
Navigate to the backend directory.
Ensure Java and Maven are installed.

**Run the application using Maven:**

mvn spring-boot:run

**Frontend Setup:**
Navigate to the frontend directory.
Ensure Node.js and Angular CLI are installed.
Install dependencies and start the Angular server:

npm install

ng serve

**Technologies Used**
Spring Boot: For creating the RESTful API.
Angular: For building the user interface.
H2 Database: An in-memory database for development and testing purposes.
Maven: For managing Java dependencies and building the project.
Node.js and npm: For managing JavaScript dependencies.
Angular CLI: For scaffolding and managing the Angular application.
