Group B3

Name: MURAGIJIMANA Janvier

Reg No: 24RP04809



Name: MUTUYIMANA Ange

Reg No: 24RP04727





**Inventory Management System for End User Equipment**

Digital and Social Transformation Project

Provided by Airtel Company

**Project Overview**



The Airtel End User Equipment Asset Inventory Management System is developed as part of the Digital and Social Transformation Challenge.

The purpose of this system is to help Airtel company manage, monitor, and control all end-user IT equipment in an organized and reliable way.



**The system manages company devices such as:**



Laptops

Desktop Computers

Mobile Phones



Many organizations face problems tracking devices due to lack of centralized systems and unstable internet connectivity.

This application solves that problem by working offline using localhost on Windows computers.



**The system improves:**



Asset accountability

Equipment tracking

Device ownership monitoring

Reporting accuracy

Operational efficiency

**1. Project Title**



Inventory Management System for End User Equipment



**2. Company**



Airtel Company



**3. Project Aim**



The main aim of this project is to design and develop an offline Windows-based

Inventory Management System that allows Airtel company to manage and

track all end-user devices effectively.



The system allows the company to clearly identify:



Which employee is using a device

Current device condition

Available devices not assigned

Assigned devices

Asset usage history

Generated reports for management decisions



Access is restricted only to an authorized administrator.



**4. System Overview**



The application is developed as a desktop-style web system using Java technologies.



**Technologies used:**



Java

Spring Boot

Spring MVC

Thymeleaf

MySQL Database

Spring Data JPA

HTML with Inline CSS

javax.persistence annotations



The system works completely offline using a local database.



**5. Login Requirement**



**System workflow:**



User opens application only ADMIN

Login page appears

User enters username and password

System verifies credentials

Dashboard opens



**Default Admin:**



**Username: Admin**

**Password: Admin123**



Only Airtel Administrator can access system operations.



**6. Main System Functions**



**6.1 Employee Management**





Add employee

Update employee details

Delete employee

View employee list

Search employee



**6.2 Asset Management**





Add assets

Update assets

Delete assets

Manage serial numbers

Track device condition

View available assets

View assigned assets



**Devices include:**



1.Laptops

2.Desktops

3.Mobile Phones



**6.3 Asset Assignment**





Assign asset to employee

Return asset

Track ownership

Record issue/return dates

Maintain history



**Relationship:**



Employee → Assignment → Asset



**6.4 Searching \& Filtering**





Search by serial number

Search by employee name

Filter by device type

Filter by status

View available assets

View assigned assets



**6.5 Reporting System**





Assets by date

Assets by department

Assigned assets report

Available assets report

Device status report

Printable reports



**7. CRUD Operations**







The system supports full CRUD:



Create

Read

Update

Delete



**Applied to:**



Employees

Assets



**8. Database Structure**



Database Name:



airtel\_inventory\_db



**Main Tables:**



1.users

2.employee

3.asset

4.assignment

5.audit\_log





**Relationships:**



**One employee → many assignments**

**One asset → one active assignment**

**Foreign keys ensure data integrity**



Tables are auto-generated using Spring JPA.



**9. Package Structure (UPDATED + COMPLETE)**



Base Package



com.airtel.inventory



**Full Layered Architecture**



com.airtel.inventory

│

├── controller

│     AuthController

│     EmployeeController

│     AssetController

│     AssignmentController

│     ReportController

│     DashboardController

│

├── model

│     User

│     Employee

│     Asset

│     Assignment

│     AuditLog

│

├── repository

│     UserRepository

│     EmployeeRepository

│     AssetRepository

│     AssignmentRepository

│

├── service

│     UserService

│     EmployeeService

│     AssetService

│     AssignmentService

│     ReportService

│

├── security

│     SecurityConfig

│

├── config

│     DataInitializer

│

└── exception

      ResourceNotFoundException

Templates (Thymeleaf Views)

templates/

│

├── login.html

├── dashboard.html

├── employees.html

├── assets.html

├── assignments.html

├── reports.html



**10. Technologies Used**



**Backend:**



Java

Spring Boot

Spring MVC

Spring Data JPA



**Frontend:**



Thymeleaf

HTML

Inline CSS



Database:



MySQL



**Tools:**



Maven

IntelliJ IDEA



**11. System Benefits**





Improved asset tracking

Better accountability

Faster assignment process

Reduced asset loss

Offline support

Easy reporting



**12. How to Run System**





**Steps:**



**Install MySQL**

I Create database airtel\_inventory\_db

I Configure on application.properties

Run Spring Boot application

**Open browser:**



**http://localhost:8080**



**Login With this :**



Username: Admin

Password: Admin123



**13. Target Users**



Airtel IT Administrator manages:



Employees

Assets

Assignments

Reports

Search \& Filtering

Printing reports



**14. Expected Impact**





The system improves Airtel company operations by providing a centralized and offline solution for managing IT equipment.



**It increases:**



Transparency

Accountability

Efficiency

Reliability of reporting

Asset control

