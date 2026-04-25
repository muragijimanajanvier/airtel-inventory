Inventory Management System for End User Equipment
Digital and Social Transformation Project
Provided by Airtel Company
Project Overview

The Airtel End User Equipment Asset Inventory Management System is developed as part of the Digital and Social Transformation Challenge. The purpose of this system is to help Airtel company manage, monitor, and control all end-user IT equipment in an organized and reliable way.

The system manages company devices such as:

Laptops
Desktop Computers
Mobile Phones

Many organizations face problems tracking devices due to lack of centralized systems and unstable internet connectivity. This application solves that problem by working offline using localhost on Windows computers.

The system improves:

Asset accountability
Equipment tracking
Device ownership monitoring
Reporting accuracy
Operational efficiency
1. Project Title

Inventory Management System for End User Equipment

2. Company

Airtel Company

3. Project Aim

The main aim of this project is to design and develop an offline Windows-based Inventory Management System that allows Airtel company to manage and track all end-user devices effectively.

The system allows the company to clearly identify:

Which employee is using a device
Current device condition
Available devices not assigned
Assigned devices
Asset usage history
Generated reports for management decisions

Access to the system is restricted only to an authorized administrator.

4. System Overview

The application is developed as a desktop-style web system using modern Java technologies.

Technologies used include:

Java Programming Language
Spring Boot Framework
Spring MVC
Thymeleaf Template Engine
MySQL Database
Spring Data JPA
HTML with Inline CSS Styling
javax.persistence annotations

The system operates completely offline using a local database server.

5. Login Requirement

Security is implemented through an authentication system.

System workflow:

User opens the application
Login page appears
User enters username and password
System verifies credentials
Dashboard opens after successful login

Default Administrator Account:

Username: Admin
Password: Admin123

Only Airtel Administrator is allowed to access and manage system operations.

6. Main System Functions

After login, the Administrator manages all activities.

6.1 Employee Management

Administrator can:

Add new employees
Update employee information
Delete employees
View employee records
Search employees by name or department

Employees are linked to assigned company assets.

6.2 Asset Management

Administrator manages all equipment by:

Adding new assets
Updating asset information
Deleting assets
Recording serial numbers
Registering device types
Monitoring device condition
Viewing available assets
Viewing assigned assets

Supported asset types include laptops, desktops, and mobile phones.

6.3 Asset Assignment

The system supports asset distribution management.

Administrator can:

Assign assets to employees
Record issue dates
Record return dates
Track ownership history
Manage asset movement records

Database relationships use foreign keys:

Employee -> Assignment -> Asset

6.4 Searching and Filtering

The system provides advanced search and filtering features:

Search by device serial number
Search employee by name
Filter by device type
View available assets
View assigned assets
Filter assets by status
6.5 Reporting System

The system generates printable reports including:

Assets by date
Assets by department
Assigned assets report
Available assets report
Device condition status report

Reports help management make accurate operational decisions.

7. CRUD Operations Supported

The system fully implements CRUD operations:

Create data
Read data
Update data
Delete data

These operations apply to employees and assets.

8. Database Structure

Database Name: airtel_inventory_db

Main tables include:

users
employees
assets
assignments

Relationships ensure data integrity:

One employee can have multiple assignments
One asset is assigned to one employee at a time
Foreign keys maintain relational consistency

Tables are automatically generated using Spring JPA configuration.

9. Package Structure

Base Package:
com.airtel.inventory

Main packages:

model
repository
service
controller
config
security

Thymeleaf templates include:

login.html
dashboard.html
employee management pages
asset management pages
report pages
10. Technologies Used

Backend Technologies:

Java
Spring Boot
Spring MVC
Spring Data JPA

Frontend Technologies:

Thymeleaf
HTML
Inline CSS

Database:

MySQL

Development Tools:

Maven or Gradle
IntelliJ IDEA or Spring Tool Suite
11. System Benefits

The system provides several organizational benefits:

Improved asset accountability
Accurate equipment tracking
Faster asset assignment process
Reduced risk of asset loss
Offline system availability
Easy reporting and auditing
12. How to Run the System

Steps to run the application:

Install MySQL Server
Create database airtel_inventory_db
Configure application.properties
Run Spring Boot application
Open browser and access:

http://localhost:8080

Login credentials:

Username: 24RP04809
Password: Admin123

13. Target Users

The system is designed for Airtel IT Administrator.

Administrator responsibilities include:

Managing employees
Managing assets
Assigning devices
Generating reports
Searching records
Filtering data
Printing reports
14. Expected Impact

This system improves operational performance at Airtel company by providing a centralized solution for managing end-user equipment even without internet connectivity.

It increases transparency, strengthens accountability, reduces equipment mismanagement, and ensures reliable reporting for all company devices.
