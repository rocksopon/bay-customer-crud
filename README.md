
# BAY Customer CRUD

Developed Microservices Rest API with JAVA Spring Boot for Create, Read, Update and Delete
Customer Model in In-Memory Database
    GET /api/customers      - get all customers
    GET /api/customers/{id}    - get specific customer by id
    POST /api/customers      - create customer
    PUT /api/customers/{id}    - update customer details by id
    DELETE /api/customers/{id}  - delete customer by id

## Table of Contents
- [Installation](#installation)
- [Usage](#usage)
- [Features](#features)
- [Testing](#testing)

## Installation
- Create directory that you wanna keep the project
- Open Terminal/cmd
> git init

> git pull git@github.com:rocksopon/bay-customer-crud.git
- Import Bay Customer CRUD project into your IDE
- Open termianal in your IDE
> mvn spring-boot:run

## Usage
- Run the application to starting the microservices for Customer CRUD
- JWT Token examples  with role **MAKER**
> eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYW1lIjoiU29wb24gSmFtcmVhbmtpdCIsImJyYW5jaCI6IjIwMDAwMCIsInJvbGUiOiJNQUtFUiJ9.tctPZ4Zs0Y4t_aa0xT1ARBT9CQgE_EZp8Dac0TdxAdc
-  JWT Token examples  with role **CHECKER**
> eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuYW1lIjoiU29wb24gSmFtcmVhbmtpdCIsImJyYW5jaCI6IjIwMDAwMCIsInJvbGUiOiJDSEVDS0VSIn0.a2mv5iAXY1O1UN89tdEL0HcMhXf9p6q69zYynwzPlfk
## Features
- BAY Customer CRUD

## Testing
- open terminal in your IDE
> mvn test -Dtest=DemoApplicationTests
