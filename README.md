# Turnos API

API REST para la gestión de turnos médicos desarrollada con Spring Boot. Permite administrar pacientes, médicos y turnos.

## Tecnologías

- Java 17
- Spring Boot 3
- Spring Data JPA / Hibernate
- H2 Database (en memoria)
- Maven
- Lombok
- Bean Validation
- Swagger / OpenAPI (springdoc)

## Cómo correr el proyecto


```bash
cd turnos-api
./mvnw spring-boot:run
```

La API queda disponible en `http://localhost:8080`.

Documentación interactiva disponible en: http://localhost:8080/swagger-ui/index.html

Para explorar la base de datos en tiempo real: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:turnosdb`
- Usuario: `sa`
- Contraseña: (vacía)

## Endpoints

### Pacientes `/pacientes`

| Método | URL | Descripción |
|--------|-----|-------------|
| GET | `/pacientes` | Listar todos los pacientes |
| GET | `/pacientes/{id}` | Obtener paciente por ID |
| POST | `/pacientes` | Crear paciente |
| PUT | `/pacientes/{id}` | Actualizar paciente |
| DELETE | `/pacientes/{id}` | Eliminar paciente |

### Médicos `/medicos`

| Método | URL | Descripción |
|--------|-----|-------------|
| GET | `/medicos` | Listar todos los médicos |
| GET | `/medicos/{id}` | Obtener médico por ID |
| POST | `/medicos` | Crear médico |
| PUT | `/medicos/{id}` | Actualizar médico |
| DELETE | `/medicos/{id}` | Eliminar médico |

### Turnos `/turnos`

| Método | URL                                               | Descripción |
|--------|---------------------------------------------------|-------------|
| GET | `/turnos`                                         | Listar todos los turnos |
| GET | `/turnos/{id}`                                    | Obtener turno por ID |
| POST | `/turnos`                                         | Crear turno |
| PATCH | `/turnos/{id}/estado`                             | Cambiar estado del turno |
| DELETE | `/turnos/{id}`                                    | Eliminar turno |
| GET | `/turnos/disponibles?medicoId={id}&fecha={fecha}` | Ver horarios disponibles de un médico |

## Ejemplos de uso

### Crear un paciente
```json
POST /pacientes
{
    "nombre": "nombrePaciente",
    "apellido": "apellidoPaciente",
    "email": "paciente@gmail.com",
    "telefono": "1234567"
}
```

### Crear un médico
```json
POST /medicos
{
    "nombre": "nombreMedico",
    "apellido": "apellidoMedico",
    "email": "medico@gmail.com",
    "especialidad": "Clínico"
}
```

### Crear un turno
```json
POST /turnos
{
    "medicoId": 1,
    "pacienteId": 1,
    "fecha": "2026-06-01",
    "hora": "10:00:00"
}
```

### Cambiar el estado de un turno
```json
PATCH /turnos/1/estado

"CONFIRMADO"
```
Estados válidos: CONFIRMADO, COMPLETADO, CANCELADO

### Ver horarios disponibles
```
GET /turnos/disponibles?medicoId=1&fecha=2026-06-01
```

## Restricciones del Sistema

- Los turnos solo se pueden agendar en intervalos de 15 minutos (09:00, 09:15, 09:30...)
- Los horarios de los turnos tienen que ser desde 09:00 a 18:00
- Un médico no puede tener dos turnos en la misma fecha y hora
- Los turnos siguen el flujo: `PENDIENTE → CONFIRMADO → COMPLETADO`, con posibilidad de `CANCELADO` desde `PENDIENTE` o `CONFIRMADO`
- No se puede eliminar un paciente o médico que tenga turnos asociados

## Manejo de errores

La API devuelve respuestas de error en formato JSON:

```json
{
    "mensaje": "Descripción del error",
    "codigo": 404
}
```