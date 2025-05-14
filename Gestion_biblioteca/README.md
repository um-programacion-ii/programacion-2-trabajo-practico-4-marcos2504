📚 Gestión de Biblioteca - Spring Boot

Proyecto de gestión de biblioteca que permite administrar usuarios, libros y préstamos.

## 🚀 Tecnologías utilizadas

- Java 21
- Spring Boot 3.4.5
- Maven
- Lombok
- JUnit 5 + Mockito + MockMvc

## 🧑‍💻 Endpoints principales

- `GET /api/libros` → Listar libros
- `POST /api/usuarios` → Crear usuario
- `PUT /api/prestamos/{id}` → Actualizar préstamo
-

## ⚙️ Cómo correr el proyecto

```bash
# Clonar el repo
git clone https://github.com/tu_usuario/gestion-biblioteca.git
cd gestion-biblioteca

# Compilar y correr
./mvnw spring-boot:run
