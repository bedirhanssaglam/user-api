# User API

This is a simple Java Spring Boot application designed to manage user data with operations such as creating, updating, and deleting user information. The application uses `ResponseWrapper` to standardize API responses.

## Table of Contents
1. [Features](#features)
2. [Technologies Used](#technologies-used)
3. [Setup and Installation](#setup-and-installation)
4. [Endpoints](#endpoints)
5. [Error Handling](#error-handling)
6. [Postman Collection](#postman-collection)

---

### Features

- CRUD operations for User entities:
    - **Create a user**
    - **Retrieve user(s)** by ID, email, or name
    - **Update user information**
    - **Delete a user**
- Input validation (e.g., email format validation)
- Standardized responses with `ResponseWrapper`

### Technologies Used

- Java 11+
- Spring Boot
- Spring Data JPA
- H2 Database (or configure your own)
- Maven

---

### Setup and Installation

#### Prerequisites

- Java 11 or higher
- Maven
- Git

### Endpoints

| HTTP Method | Endpoint            | Description                 |
|-------------|----------------------|-----------------------------|
| GET         | `/users`             | Retrieve all users          |
| GET         | `/users/{id}`        | Retrieve a user by ID       |
| GET         | `/users/email/{email}` | Retrieve a user by email    |
| GET         | `/users/name/{name}` | Retrieve users by name      |
| POST        | `/users`             | Create a new user           |
| PUT         | `/users/{id}`        | Update a user by ID         |
| DELETE      | `/users/{id}`        | Delete a user by ID         |

#### Sample API Requests

1. **Retrieve a user by email:**

```http
GET /users/email/{email}
```

Example Response:

```json
{
   "code": 200,
   "data": {
      "id": "673600a3deaeb9142bc0ed94",
      "name": "Bedirhan Saglam",
      "email": "bedirhansaglam270@gmail.com"
   },
   "message": null
}
```

2. **Create a new user:**

```http
POST /users
```

Example Request Body:

```json
{
  "name": "Bedirhan Saglam",
  "email": "bedirhansaglam270@gmail.com"
}
```

Example Response:

```json
{
  "code": 201,
  "data": {
    "id": "1",
    "name": "Bedirhan Saglam",
    "email": "bedirhansaglam270@gmail.com"
  },
  "message": "User created successfully."
}
```

### Error Handling
This application uses a custom `ResponseWrapper` class to standardize API responses and error messages.

#### Common Error Messages
- Invalid Email Format: Returned when the provided email is invalid.
- User Not Found: Returned when no user is found for the specified criteria.
- Email Already Used: Returned when attempting to create or update a user with an email that already exists.

### Postman Collection
You can download Postman collection from `postman` folder. Its appearance is as follows:

<img width="276" alt="postman_collection" src="https://github.com/user-attachments/assets/3131b107-311a-4636-9d0f-6c1374f55228">
