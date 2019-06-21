# BookStore
This project is order books.

This is my first time to create spring-boot rest api project.
It's very challenging but I was very happy to study and create it.

## Getting started
- Clone this repo
- `./mvnw clean install && java -jar target/june-bookstore-0.0.2-SNAPSHOT.jar` to run application
- Let's go to journey my api on http://localhost:8080/swagger-ui.html
<br/>

Note: This project use h2-database embedded.

## Dependencies
- [H2 Database](https://mvnrepository.com/artifact/com.h2database/h2) - For store data in-memory sql
- [Spring Data JPA](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-jpa) - For mapping entity with database or orm 
- [Spring Security](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-security) - For manage authentication
- [Swagger](https://mvnrepository.com/artifact/io.springfox/springfox-swagger2) - For make api document

## Other
- [db.sql](https://github.com/weeravit/BookStore/blob/master/src/main/resources/db.sql) - For view database design
- [H2 Console](http://localhost:8080/h2-console) - For view database
- [Swagger UI](http://localhost:8080/swagger-ui.html) - For view api document
