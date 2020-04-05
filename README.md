# calorieCap
Application to maintain calories per day

Frameworks/Technology Used:
Backend- Spring Boot, Java version 11.0.6
Frontend- Angular 9.1.0
Database- PostgresSql

Backend-

1)Spring Security has been used to provide JWT based authentication.

2)For security reasons BCRYPT has been used for generating saltedHashedPasswords.
3)Spring-data-jpa and hibernate are used to provide object relational mapping.
4)Folder Structure:
- DAO (Data Access Objects) or models define table and their dependency for database.
- DTO (Data Transfer Objects) are used to send or receive data from rest APIs.
- Repositories are used to perform queries on tables.
- Services contain all the business logic.
- Security Config package contains all configuration/utility classes needed for JWT authentication.
- Controllers are REST controllers.
- CalorieCapException class is custom exception class.

Angular-

1)Components
- register component to register user.
- login component to login user.
- add-meal component to provide user interface to add meals,date and calories.
- report component displays user his/her meal records where red/green color means calorie count is greater/less than expected per day.
  User can also delete or edit any meal entry from report component by expanding panel.
- report component also contains settings button to edit expected count.
- admin component shows buttons to getUserList,getUserMeals,deleteUserMeals.
- admin-report component shows users meal report of user between selected dates.

2)Services are used to send ajax requests to backend APIs.
3)auth-guard is used to redirect user to login page if not authenticated.
4)auth-interceptor is used to add JWT token in authorization header of each API before passing it to backend.
5)Client Side validations are provided with each form.
