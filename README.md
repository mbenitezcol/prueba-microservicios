#  Microservicios Ejercicio - Java + Spring Boot

Este proyecto implementa una arquitectura de microservicios en Java con Spring Boot que simula el funcionamiento basico de un sistema bancario. Esta dividido en:

- `cliente-persona-service`: Gestion de clientes y personas
- `cuenta-movimiento-service`: Gestion de cuentas y movimientos
- Comunicacion asincrona via Spring Events + HTTP

---

##  Tecnologias Utilizadas

- Java 17
- Spring Boot
- Spring Data JPA
- Oracle DB
- Docker y Docker Compose
- Postman (v9.13.2)
- Karate DSL para pruebas de integracion
- Lombok

---

##  Estructura del Proyecto

```
microservices-ejercicio-app/
 cliente-persona-service/
 cuenta-movimiento-service/
 docker-compose.yml
```

---

##  Requisitos Previos

- Java 17
- Maven
- Docker + Docker Compose
- Oracle configurado (por defecto usando imagen gvenzl/oracle-xe)

---

##  Despliegue con Docker

### 1. Construir servicios

```bash
cd cliente-persona-service
mvn clean package -DskipTests
cd ../cuenta-movimiento-service
mvn clean package -DskipTests
```

### 2. Levantar contenedores

```bash
docker-compose up --build
```

Esto inicia:

- Oracle en `localhost:1521`
- cliente-service en `localhost:8081`
- cuenta-service en `localhost:8082`

---

##  Endpoints Principales

### Cliente

- `POST /api/clientes`
- `GET /api/clientes`
- `GET /api/clientes/{id}`
- `PUT /api/clientes/{id}`
- `DELETE /api/clientes/{id}`

### Cuenta

- `POST /api/cuentas`
- `GET /api/cuentas`
- `GET /api/cuentas/{numeroCuenta}`

### Movimiento

- `POST /api/movimientos/{numeroCuenta}`
- `GET /api/movimientos/cuenta/{numeroCuenta}`
- `GET /api/movimientos/cuenta/{numeroCuenta}/rango?desde=yyyy-MM-dd&hasta=yyyy-MM-dd`

---

##  Comunicacion Asincrona

Cuando se crea un cliente, se dispara un `Spring Event` que viaja via HTTP al microservicio de cuentas para crearle automaticamente una cuenta bancaria inicial.

---

##  Pruebas con Postman

Carga la coleccion desde:

 [`microservices-ejercicio.postman_collection.json`](./microservices-ejercicio.postman_collection.json)

---

##  Pruebas Automatizadas con Karate

```bash
# Ejecutar pruebas
mvn test -Dkarate.options=classpath:ejercicio-karate-test.feature
```
