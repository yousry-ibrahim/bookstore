# Book Store Api (Yousry)

its an API for book store contains some endpoints:
- login
- CRUD for book 
- search 
- checkout

# used Technologies and tools:

* Java 1.8
* Spring WEB, Spring Boot, Spring Data
* Spring Security with JWT Token
* Database H2 (In-Memory DB).
* Maven as a build Tool
* OpenAPI V3 with swaggerUI 
* Docker

# Description and how to run

- while starting the APIs, H2 DB will execute *data.sql* scripts for initial data insertion.
  
- a pre Defined User is inserted as smartD / smartD which will be used in Login Service.
  
- if you are running on docker , you have to map the port of 9090 as this is the api port.
  
- after running the service, to access the swagger UI you should go to :
  http://localhost:9090/bookstore/swagger-ui.html
  
- first you need to call /v1/login service to get the JWT token, then use this token as an 
  Authorization header in any other request to any end point in the API, other wise you will get 
  unauthorized error.
  
