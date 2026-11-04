# Event Registration System

A backend application built using Spring Boot and MySQL for managing event registrations.

## Technology Stack
- Java 21
- Spring Boot
- Spring Data JPA
- MySQL
- Maven
- REST API

## Features
- View all events.
- View individual event details.
- Create events.
- Register users for events.
- Reuse existing users based on email.
- View user registrations.
- Cancel registrations.

## Database Structure
- Users: Stores user information.
- Events: Stores event information.
- Registrations: Connects users and events.

## API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| GET | /api/events | Get all events |
| GET | /api/events/{id} | Get event details |
| POST | /api/events | Create an event |
| POST | /api/registrations | Register for an event |
| GET | /api/registrations?email={email} | View registrations |
| DELETE | /api/registrations/{id}?email={email} | Cancel registration |

## Database Setup

Create a MySQL database named:

codealpha_event_registration

## Configuration

Set the MySQL password using the DB_PASSWORD environment variable.

PowerShell:

    $env:DB_PASSWORD = Read-Host "Enter MySQL password"

## Run Application

    .\mvnw.cmd spring-boot:run

Application URL: http://localhost:8080

## Project Structure

    controller/
    dto/
    model/
    repository/
    service/

## Security Note

This learning project uses email-based registration management without authentication. Proper authentication and authorization are required before production deployment.