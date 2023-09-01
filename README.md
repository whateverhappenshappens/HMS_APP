# HMS
Doctor Appointment Booking System

[![Angular](https://img.shields.io/badge/Angular-v12.0.0-blue.svg)](https://angular.io/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-v2.5.4-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Node.js](https://img.shields.io/badge/Node.js-v16.8.0-green.svg)](https://nodejs.org/)
[![Java](https://img.shields.io/badge/Java-v11-red.svg)](https://www.oracle.com/java/)

## Description

Welcome to the Doctor Appointment Booking System project! This application allows users to easily book and manage appointments with doctors based on their specialization. The project is built using Angular for the client side and Spring Boot for the server side.
The user can register with an account and only admins have the right to register another admin and doctor.
The user is login and upon successfully signin in the user is redirected to respective dashboard and from there on they can perform action depending upon their role.
A rule engine has been added for easy updation and reducing code changes.

## Table of Contents

* Features
* Getting Started
* Prerequisites
* Installation
* Usage
* Technologies Used
* Workflow
* Spring Boot
* Preview
* Additions that may be added in the future 
* Contributing
* License
* Contact
* Acknowledgements

## Features

Actions admin can perform currently:
* Create and update booking for a user if user requests
* Check and get details about all bookings associated with a user and a doctor.
* Update status of a booking.
* Register accounts for other admin and doctor.
* Block, Reactivate, Deactivate any user.

Actions user can perform currently:
* Register and login to a account.
* Create bookings with a doctor (A user can have max 2 bookings per day per doctor).
* Check and get details about all bookings associated with the user.
* Update status of a booking.
* Deactivate, Reactivate their account.

Actions doctor can perform currently:
* Register and login to a account.
* A doctor can have max 6 bookings per day.
* Check and get details about all bookings associated with the doctor.
* Update status of a booking.
* Deactivate, Reactivate their account.

The sessions are maintained by jwt tokens and all the validation, authorization and authentications are managed by the server. 

## Getting Started

 ### Prerequisites

Before you begin, ensure you have met the following requirements:

* Node.js and npm: Make sure you have the latest version of Node.js and npm installed.
* Angular CLI: Install the Angular CLI using the command npm install -g @angular/cli.
* Java and Spring Boot: Ensure you have Java and Spring Boot set up for the back-end.


## Installation

1. Clone the repository git clone (https://github.com/whateverhappenshappens/HMS_APP.git)

2. Set up installation and usage for client and server.

### Development server for client side

1. Navigate to the front-end directory:
    * cd HMS_APP/HMS_CLIENT
2. Install front-end dependencies:
    * npm install

### Usage for client

1. Launch the Angular front-end using the command:
    * ng serve
2. This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 16.2.0.
Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The application will automatically reload if you change any of the source files

### Quickstart for the server

1. Navigate to the back-end directory:
    * cd HMS_APP/HMS_SERVER
2. Open the project in your IDE: IntelliJ IDEA (recommended) or Eclipse
    * If you are using IntelliJ IDEA, make sure the IDE recognizes the project as a Spring Boot project.
3. Make sure you are in the `Hospital-Management` directory
4. Install back-end dependencies using Maven:
    * mvn clean install
### Usuage for server

1. Configure the database connection in `application.properties` file (check the [Database](#database) section below for more info)
2.  Just create a database, and make changes in the application.properties file 
3. Run the project (by running the `main` method in `HospitalManagementApplication.java`)
4. Open http://localhost:8080/ in your browser!
5. The front-end Angular application communicates with the backend API using this URL. Make sure the backend is up and  running before testing the front-end.

Keep in mind that these instructions assume you have Java and Maven properly installed on your system. Additionally, if your backend requires a database setup or configuration, make sure to provide those details in the appropriate configuration files.
  
### Database

MySQL or PostgreSQL can be used as the database for this project. The database connection can be configured in the `application.properties` file, with the appropriate values for the following properties:

```properties
    db.url=jdbc:mysql://[ip address of db]:[port of db]/db namejava?createDatabaseIfNotExist=true
    or 
    jdbc:postgresql://[ip address of db]:[port of db]/db name
    db.username=[username]
    db.password=[password, if any]
```

Having done that,check out Google for how to do that, because it depends on what tool you are using to access said database. 

## Technologies Used

- Angular
- Spring Boot
- Node.js
- ng-bootstrap (for UI components)
- ngx-toastr (for notifications)

# Workflow
- ![image](https://github.com/whateverhappenshappens/HMS_APP/blob/main/ASSETS/BookingDetail.png)

### Models
- represent data as entity and relationship among them.

## Spring Boot

For any information about Spring Boot, here are some useful links!

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.6.4/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.6.4/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.6.4/reference/htmlsingle/#boot-features-developing-web-applications)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)

## Preview

![image](https://github.com/jaygajera17/E-commerce-project-springBoot/assets/81226571/02a04d3c-1fc9-418c-b231-639f6525d07e)


## Additions that may be added in the future 

* A notification can be sent to user's email id with info regarding booking.
* Slot management can be improved.

## Contributing

* Contributions are welcome! If you have any suggestions, improvements, or bug fixes, feel free to open an issue or create a pull request.

## License

* This project is licensed under the MIT License. See the LICENSE file for details.

## Contact

For any questions or inquiries, please contact [Gmail](poddaryashaswi98@gmail.com)

## Acknowledgements

Inspired by FreeCodeCamp's Guide on Writing a Good README.
