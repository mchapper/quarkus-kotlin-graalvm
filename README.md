# API REST de Películas con Quarkus, Kotlin y GraalVM

Este proyecto es una API REST de demostración que utiliza Quarkus, Kotlin y GraalVM para crear un microservicio de gestión de películas.

## 🚀 Tecnologías Utilizadas

- **Quarkus**: Framework Java nativo para la nube
- **Kotlin**: Lenguaje de programación moderno y conciso
- **GraalVM**: Máquina virtual de alto rendimiento
- **PostgreSQL**: Base de datos relacional
- **Docker**: Contenedorización de la aplicación
- **Hibernate ORM**: Mapeo objeto-relacional
- **Panache**: Simplificación del acceso a datos
- **Jackson**: Serialización/deserialización JSON

## 📋 Prerrequisitos

- Java 21 con GraalVM (recomendado GraalVM 21.0.7 o superior)
- Docker y Docker Compose
- Maven

## 🛠️ Configuración del Proyecto

1. **Clonar el repositorio:**
   ```bash
   git clone git@github.com:mchapper/quarkus-kotlin-graalvm.git
   cd quarkus-kotlin-graalvm
   ```

2. **Compilar el proyecto:**
   ```bash
   mvn clean package -Pnative -Dquarkus.native.container-build=true
   ```

3. **Iniciar los servicios con Docker Compose:**
   ```bash
   docker-compose up --build
   ```

## 🌐 Estructura de la API

### Endpoints Disponibles

| Método | Ruta | Descripción |
|--------|------|-------------|
| GET | /api/movies | Obtener todas las películas |
| GET | /api/movies/{id} | Obtener una película por ID |
| POST | /api/movies | Crear una nueva película |
| PUT | /api/movies/{id} | Actualizar una película existente |
| DELETE | /api/movies/{id} | Eliminar una película |

### Ejemplos de Uso

1. **Crear una película:**
   ```bash
   curl -X POST http://localhost:8080/api/movies \
     -H "Content-Type: application/json" \
     -d '{
       "title": "Inception",
       "director": "Christopher Nolan",
       "year": 2010,
       "genre": "Sci-Fi"
     }'
   ```

2. **Listar todas las películas:**
   ```bash
   curl http://localhost:8080/api/movies
   ```

3. **Obtener una película por ID:**
   ```bash
   curl http://localhost:8080/api/movies/1
   ```

4. **Actualizar una película:**
   ```bash
   curl -X PUT http://localhost:8080/api/movies/1 \
     -H "Content-Type: application/json" \
     -d '{
       "title": "Inception",
       "director": "Christopher Nolan",
       "year": 2010,
       "genre": "Ciencia Ficción"
     }'
   ```

5. **Eliminar una película:**
   ```bash
   curl -X DELETE http://localhost:8080/api/movies/1
   ```

## 📦 Estructura del Proyecto

```
src/
├── main/
│   ├── kotlin/
│   │   └── com/
│   │       └── movies/
│   │           └── api/
│   │               ├── MovieEntity.kt    # Entidad JPA
│   │               ├── MovieDTO.kt       # Objeto de transferencia de datos
│   │               ├── MovieRepository.kt # Repositorio Panache
│   │               ├── MovieService.kt   # Lógica de negocio
│   │               └── MovieResource.kt  # Endpoints REST
│   └── resources/
│       └── application.properties        # Configuración
```

## 🔧 Configuración

### Base de Datos
La aplicación utiliza PostgreSQL con las siguientes configuraciones por defecto:
- Host: localhost
- Puerto: 5432
- Base de datos: movies
- Usuario: postgres
- Contraseña: postgres

### Docker
El proyecto incluye un `docker-compose.yml` que configura:
- Servicio PostgreSQL
- Servicio de la aplicación Quarkus
- Volumen persistente para la base de datos

## 🚀 Despliegue

1. **Compilar la aplicación nativa:**
   ```bash
   mvn clean package -Pnative -Dquarkus.native.container-build=true
   ```

2. **Iniciar los servicios:**
   ```bash
   docker-compose up --build
   ```

La aplicación estará disponible en `http://localhost:8080`

## 🛑 Detener y Limpiar

Para detener y eliminar todos los contenedores, volúmenes y redes creados por docker-compose:

```bash
docker-compose down -v
```

Este comando:
- Detiene todos los contenedores
- Elimina los contenedores
- Elimina los volúmenes (incluyendo la base de datos)
- Elimina las redes creadas

Si solo quieres detener los contenedores sin eliminar los volúmenes (para mantener los datos de la base de datos):
```bash
docker-compose down
```

## 📝 Notas Adicionales

- La aplicación utiliza carga perezosa (lazy loading) para las entidades
- Los DTOs están configurados para serialización/deserialización JSON
- La base de datos se inicializa automáticamente al primer inicio
- Los logs están configurados para mostrar información detallada

## 🤝 Contribución

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

## 📄 Licencia

Este proyecto está bajo la Licencia MIT - ver el archivo [LICENSE.md](LICENSE.md) para más detalles. 
