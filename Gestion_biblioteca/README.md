📚 Gestión de Biblioteca - Spring Boot

Proyecto de gestión de biblioteca que permite administrar usuarios, libros y préstamos.

## 🚀 Tecnologías utilizadas

- Java 21
- Spring Boot 3.4.5
- Maven
- Lombok
- JUnit 5 + Mockito + MockMvc

## 🧑‍💻 Endpoints principales

## 📡 Endpoints de la API

### 📘 Libros (`/api/libros`)

| Método | Endpoint            | Descripción                         |
|--------|---------------------|-------------------------------------|
| GET    | `/api/libros`       | Obtener todos los libros            |
| GET    | `/api/libros/{id}`  | Obtener un libro por su ID          |
| POST   | `/api/libros`       | Crear un nuevo libro                |
| PUT    | `/api/libros/{id}`  | Actualizar un libro existente       |
| DELETE | `/api/libros/{id}`  | Eliminar un libro por su ID         |

---

### 👤 Usuarios (`/api/usuarios`)

| Método | Endpoint              | Descripción                          |
|--------|-----------------------|--------------------------------------|
| GET    | `/api/usuarios`       | Obtener todos los usuarios           |
| GET    | `/api/usuarios/{id}`  | Obtener un usuario por su ID         |
| POST   | `/api/usuarios`       | Crear un nuevo usuario               |
| PUT    | `/api/usuarios/{id}`  | Actualizar un usuario existente      |
| DELETE | `/api/usuarios/{id}`  | Eliminar un usuario por su ID        |

---

### 📚 Préstamos (`/api/prestamos`)

| Método | Endpoint               | Descripción                          |
|--------|------------------------|--------------------------------------|
| GET    | `/api/prestamos`       | Obtener todos los préstamos          |
| GET    | `/api/prestamos/{id}`  | Obtener un préstamo por su ID        |
| POST   | `/api/prestamos`       | Crear un nuevo préstamo              |
| PUT    | `/api/prestamos/{id}`  | Actualizar un préstamo existente     |
| DELETE | `/api/prestamos/{id}`  | Eliminar un préstamo por su ID       |


## ⚙️ Cómo correr el proyecto

```bash
# Clonar el repo
git clone https://github.com/tu_usuario/gestion-biblioteca.git
cd gestion-biblioteca

# Compilar y correr
./mvnw spring-boot:run
