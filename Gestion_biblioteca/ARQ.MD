# 🏛️ Arquitectura del proyecto

Este proyecto implementa una arquitectura **MVC** clásica, separando responsabilidades:

## 🧩 Capas

- **Model (`model/`)**: Contiene las entidades `Libro`, `Usuario`, `Prestamo`.
- **Controller (`controller/`)**: Expone los endpoints REST.
- **Service (`service/`)**: Lógica de negocio y comunicación con el "repositorio" (HashMap simulado).
- **Repository (simulado)**: No se usa base de datos, los datos se almacenan en estructuras en memoria.