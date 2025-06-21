# ScreenMatch - Buscador y Analizador de Series

![Java](https://img.shields.io/badge/Java-17%2B-blue)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.5.0-brightgreen)
![OMDB API](https://img.shields.io/badge/OMDB_API-1.0-lightgrey)

ScreenMatch es una aplicación Java que consume datos de la API de OMDB para buscar series de televisión, analizar episodios y mostrar información detallada utilizando las últimas características de Java y Spring Framework.

## Características Principales

### 🚀 Funcionalidades Implementadas
1. **Búsqueda de series** por título
2. **Visualización de información detallada** de series
3. **Análisis de temporadas y episodios**
4. **Top 5 episodios mejor evaluados**
5. **Cálculo de evaluaciones promedio por temporada**
6. **Búsqueda de episodios por año**
7. **Comparación de episodios por evaluación**

### 💻 Tecnologías Utilizadas
| Tecnología | Aplicación en el Proyecto |
|------------|----------------------------|
| **Lambdas** | Expresiones funcionales para operaciones en colecciones |
| **Streams API** | Procesamiento eficiente de datos de series y episodios |
| **Spring Boot** | Configuración y ejecución de la aplicación |
| **Spring Web** | Consumo de APIs REST (OMDB) |
| **Jackson** | Deserialización de JSON a objetos Java |

## Funcionalidades Destacadas con Ejemplos

### 1. Lambdas en Acción
```java
// Filtrado y mapeo con lambdas
series.stream()
    .filter(s -> s.getEvaluacion() > 8.0)
    .map(Serie::getTitulo)
    .forEach(System.out::println);
```

### 2. Streams para Procesamiento de Datos
```java
// Top 5 episodios mejor evaluados
datosEpisodios.stream()
    .filter(e -> !e.evaluacion().equalsIgnoreCase("N/A"))
    .sorted(Comparator.comparing(DatosEpisodio::evaluacion).reversed())
    .limit(5)
    .forEach(System.out::println);
```

### 3. Operaciones Estadísticas con Streams
```java
// Evaluación promedio por temporada
Map<Integer, Double> evaluacionesPorTemporada = episodios.stream()
    .filter(e -> e.getEvaluacion() > 0.0)
    .collect(Collectors.groupingBy(
        Episodio::getTemporada,
        Collectors.averagingDouble(Episodio::getEvaluacion)
    );
```

### 4. Transformación de Datos
```java
// Conversión de datos a objetos de dominio
List<Episodio> episodios = temporadas.stream()
    .flatMap(t -> t.episodios().stream()
        .map(d -> new Episodio(t.numero(), d))
    .collect(Collectors.toList());
```

### 5. Búsqueda Avanzada
```java
// Buscar episodios por año
LocalDate fechaBusqueda = LocalDate.of(2015, 1, 1);
episodios.stream()
    .filter(e -> e.getFechaDelanzamiento() != null && 
                e.getFechaDelanzamiento().isAfter(fechaBusqueda))
    .forEach(e -> System.out.println(
        "Temporada " + e.getTemporada() + 
        ", Episodio: " + e.getTitulo() + 
        ", Fecha: " + e.getFechaDelanzamiento()
    ));
```

## Estructura del Proyecto

```
screenmatch/
├── src/main/java/com/alvax/screenmatch/
│   ├── model/              # Modelos de datos
│   │   ├── DatosSerie.java
│   │   ├── DatosEpisodio.java
│   │   ├── DatosTemporadas.java
│   │   └── Episodio.java
│   ├── principal/          # Lógica principal
│   │   ├── Principal.java
│   │   └── EjemploStreams.java
│   ├── service/            # Servicios
│   │   ├── ConsumoAPI.java
│   │   ├── ConvierteDatos.java
│   │   └── IConvierteDatos.java
│   └── ScreenmatchApplication.java # App principal
├── src/main/resources/
│   └── application.properties
└── pom.xml
```

## Cómo Ejecutar

1. Clona el repositorio:
```bash
git clone https://github.com/AxelDesarrolloWeb/screen-match.git
cd screen-match
```

2. Ejecuta la aplicación con Maven:
```bash
./mvnw spring-boot:run
```

3. Sigue las instrucciones en la consola para buscar series y analizar episodios.

## Requisitos

- Java 17+
- Maven
- API Key de OMDB (gratuita en [omdbapi.com](https://www.omdbapi.com/apikey.aspx))

## Configuración

Agrega tu API key de OMDB en `application.properties`:
```properties
omdb.api.key=tu_api_key_aqui
```

## Contribuciones

¡Las contribuciones son bienvenidas! Por favor crea un issue o pull request para sugerir mejoras.
