# Massive CSV Application

This project is an application developed in **Java 21** with **Spring Framework**, including components such as Spring Data JPA and Spring MVC. Its primary purpose is to manage and process large volumes of data from CSV files, focusing on batch operations to optimize performance.

---

## Project Structure

The project is based on a clean and hexagonal architecture, including these main layers:

1. **Application Layer**:
    - Contains the main services that orchestrate the business logic.
2. **Domain Layer**:
    - Contains the business models and ports (interfaces) that define the communication between the application and infrastructure layers.
3. **Infrastructure Layer (DRIVEN)**:
    - Implements and connects the interfaces (secondary ports) defined in the domain with the external technologies necessary for the application to function.
    - Includes the following key responsibilities:
        - **Data Persistence**: Uses Spring Data JPA to interact with a PostgreSQL database for entity persistence.
        - **Object Mapping**: Utilizes MapStruct to convert between DTOs (transfer objects) and domain entities.
        - **Integration with External Resources**: Manages interaction with external systems such as file storage or external APIs if necessary.
    - This layer ensures that technical details are decoupled from the main business logic, allowing changes in the underlying technology without affecting the domain.
4. **Infrastructure Layer (DRIVING)**:
    - Provides REST interfaces to consume the application's features.
    - Example: `MassiveController`.

---

## Technologies Used

- **Jakarta EE**
- **Spring Framework**, including:
    - Spring Data JPA
    - Spring MVC
- **Lombok**: For boilerplate code generation.
- **MapStruct**: For automatic object mapping.
- **PostgreSQL**: Database used for persistence.

---

## Environment Configuration

### Prerequisites

- Java SDK 21
- PostgreSQL (or any other database compatible with Spring JPA)
- Gradle (optional, depending on the build system used)

### Environment Variables

Configure the following variables in your environment to ensure the application works correctly:

- `BATCH_SIZE`: Defines the size of batch operations when saving data. This value is used in the `application.yaml` file to configure batch persistence operations via Hibernate.
- `SERVER_PORT`: Defines the port on which the application's REST services will be exposed. This value is referenced in `application.yaml` to configure the embedded server.
- `DATABASE_JDBC_URL`: Specifies the database connection URL. Used in the `application-local.yaml` file.
- `DATABASE_USERNAME`: Username to connect to the database. Defined in the `application-local.yaml` file.
- `DATABASE_PASSWORD`: Password for the database user. Also configured in `application-local.yaml`.

### Example of Environment Variables Setup

#### For Linux or MacOS:
```bash
export BATCH_SIZE=50
export SERVER_PORT=8080
export DATABASE_JDBC_URL=jdbc:postgresql://localhost:5432/massive_excel
export DATABASE_USERNAME=user
export DATABASE_PASSWORD=password
```

#### For Windows (Powershell):
```powershell
$env:BATCH_SIZE="50"
$env:SERVER_PORT="8080"
$env:DATABASE_JDBC_URL="jdbc:postgresql://localhost:5432/massive_excel"
$env:DATABASE_USERNAME="user"
$env:DATABASE_PASSWORD="password"
```

These variables are referenced in the `application.yaml` file.

> **Note**: Make sure to select the `local` profile when running the project. This ensures that the application uses the
> correct environment-specific configuration (`application-local.yaml`).
---

## Execution

1. Clone this repository:
   ```bash
   git clone <repository>
   ```
2. Configure the required environment variables.
3. Build the application:
   ```bash
   ./gradlew build
   ```
4. Run the application:
   ```bash
   java -jar build/libs/<jar-name>.jar
   ```

---

## REST Endpoints

### Example Endpoint: Bulk File Upload

- **URL**: `/api/massive`
- **HTTP Method**: `POST`
- **Description**: Upload an Excel file and process its data in batches.

`Example`:
```http
POST http://localhost:${SERVER_PORT}/api/massive/bulk-csv
Content-Type: multipart/form-data; boundary=10000rows

--10000rows
Content-Disposition: form-data; name="meta-data"
Content-Type: application/json; charset=UTF-8

{
"fileName": "random_data.csv" // name of file
}

--10000rows
Content-Disposition: form-data; name="file-data"; filename="random_data.csv" // name of file again
Content-Type: text/csv

< ./random_data.csv // file
```

---

## Swagger Integration

The project includes Swagger UI for API documentation and testing purposes. You can access the Swagger UI at the
following URL after starting the application:

- **URL**: [http://localhost:\${SERVER_PORT}/api/swagger-ui/index.html](http://localhost:${SERVER_PORT}/api/swagger-ui/index.html)

---

## Contact

Thank you for reviewing this project. If you have any questions or suggestions, feel free to contact me:

- **Email**: [jupabeto@gmail.com](mailto:jupabeto@gmail.com)

---