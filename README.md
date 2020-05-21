# E-Clinic Application

Web application for an outpatient clinic. 

## Features

- creation, prosecution and digital maintenance of the patients' appointments
- prosecution and digital maintenance of examinations

## Technologies

- Java 1.8
- PostgreSQL 10
- Spring Boot 2.2.5
- Angular 9

## Run Backend

**Requirements**

- JDK 1.8 or later
- Maven 3.2 or later
- PostgreSQL 10
- Clone repository: `git clone https://github.com/Rzohfa/przychodnia.git`

**Create database**

1. Go to `<postgresql_installation_folder>/bin/` and run the following command :

`createdb -h <host> -p <port> -U <username> clinic_app_db`

2. Type in your postgres-user password
3. The db should now be running

**Supplement your database configuration in Spring**

1. Go to repository root directory
2. Open file `src/main/resources/application.properties`
3. Adjust the entries to your database configuration as follows:

```
spring.datasource.url=jdbc:postgresql://<host>:<port>/clinic_app_db
spring.datasource.username=<your_postgres_username>
spring.datasource.password=<your_postgres_password>
```

**Run Spring**

1. Run the following command in the repository root directory:

`./mvnw spring-boot:run`

2. Spring should now be running at `localhost:8080`
3. Checkout the `documentation` directory for the REST API reference.

## Run Frontend

**Requirements**

- Node.js 12.16 or later
- Angular CLI 9 or later

**Run Angular**

1. Go to repository root directory
2. Go to `/web-client`
3. Run `npm install` and wait for completion
4. Run `ng serve` and wait for compilation
5. Angular webapp should now be available at `localhost:4200` 
