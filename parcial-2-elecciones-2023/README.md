# Parcial 2 - Elecciones 2023

El siguiente parcial esta orientado a que los alumnos puedan demostrar los conocimientos adquiridos en la materia. 
Para esto se eligio una tematica vigente en este año electoral, las elecciones 2023.

---

## Contexto

### ¿Que votamos?
En el año 2023 es un año de elecciones en Argentina.

En las elecciones nacionales, desarrolladas los días 13 de agosto (P.A.S.O) y 22 de octubre (Generales) 
y eventual segunda vuelta 19 de noviembre, las argentinas y los argentinos elegiremos:

- Presidente/a y vicepresidente/a
- 19 parlamentarios y parlamentarias del Mercosur por distrito nacional
- 24 parlamentarios y parlamentarias del Mercosur por distrito regional
- 130 diputados y diputadas nacionales en todo el país
- 24 senadores y senadoras nacionales en 8 provincias.

### Sistema de publicación de resultados electorales

La Dirección Nacional Electoral y el Observatorio Político Electoral del Ministerio del Interior ponen a disposición 
el Sistema de Publicación de Resultados Electorales como un estándar de preservación y difusión de datos electorales.

El Sistema de Publicación de Resultados Electorales es una estructura que permite el archivo, tratamiento, intercambio 
y publicación de datos históricos de manera ágil y la aplicación de la misma sobre los resultados provisionales de los 
últimos 10 años.

Los resultados que se presentan son los correspondientes a los escrutinios provisorios de elecciones nacionales. 
También se presentan los resultados provisionales de las elecciones provinciales y/o locales realizadas bajo la 
adhesión a ley de simultaneidad.

Al contar con un buscador avanzado permite consultar, a través de una interfaz amigable, los resultados provisorios 
obtenidos por cada una de las agrupaciones políticas, organizados por categorías electorales y ámbitos geográficos.

También dispone de una herramienta de generación de informes personalizados, la opción de descarga de resultados en 
formato CSV (archivo de texto separado por comas) y una sección destinada a desarrolladores que brinda acceso a la API 
de publicación de resultados electorales históricos. Para desarrolladores se proveen tres documentos. 
La documentación técnica de la API para que pueda utilizarse en un editor de API (Postman, Insomnia, Swagger, etc), 
una colección de Insomnia con la especificación de la API que incluye algunos ejemplos de consultas y el Estándar de 
Preservación de Datos Electorales, que provee información sobre los distintos campos y las convenciones de uso que 
aplican a los datos exportados en formato CSV.

### ¿Como se organiza el padrón electoral?

El padrón electoral es el listado de personas habilitadas para votar en una elección.

En Argentina, el padrón electoral se organiza por distrito, es decir, por provincia y por la Ciudad Autónoma de Buenos Aires.
Cada distrito está dividido en secciones electorales, que a su vez se dividen en circuitos electorales.

### Información dicional

- [Clases de votos](https://www.argentina.gob.ar/dine/clases-de-votos)
- [Electores](https://www.argentina.gob.ar/dine/electores)

---

## Consigna

### Precondiciones y Aclaraciones

- Para este parcial usaremos los datos oficiales provistos por el Ministerio del Interior de la Nación Argentina 
en formato CSV que han sido expuestos en una api restful en la siguiente imagen de docker: **tupfrcutn/elecciones-2023:1.0.0**
- La especificación de la api restful esta disponible en la misma imagen, en el link **http://localhost:<PUERTO>/swagger-ui.html**
- En la api /resultados, solo se expondrán datos para el distrito 4 (Córdoba), sección 26 (Unión) y para el cargo 1 (PRESIDENTE Y VICE)

### Requerimientos Funcionales

1. Crear una api que exponga todos los distritos y que permita buscar por nombre. <span style="color:red">**(10 puntos)**</span>

**Request**
```http
curl --location 'http://localhost:8080/distritos'
```
**Response**
```json
[
  {
    "id": 1,
    "nombre": "Ciudad Autónoma de Buenos Aires"
  },
  {
    "id": 2,
    "nombre": "Buenos Aires"
  },
  {
    "id": 3,
    "nombre": "Catamarca"
  },
  {
    "id": 4,
    "nombre": "Córdoba"
  }
]
```

**Request**
```http
curl --location 'http://localhost:8080/distritos?distrito_nombre=bue'
```
**Response**
```json
[
  {
    "id": 1,
    "nombre": "Ciudad Autónoma de Buenos Aires"
  },
  {
    "id": 2,
    "nombre": "Buenos Aires"
  }
]
```


2. Crear una api que exponga los datos de los cargos disponibles para votar por distrito. <span style="color:red">**(5 puntos)**</span>

Por ejemplo, el consultar los datos por el distrito 4 (Córdoba) debería devolver los siguientes datos:

**Request**
```http
curl --location 'http://localhost:8080/cargos?distrito_id=4'
```
**Response**
```json
{
  "distrito": {
    "id": 4,
    "nombre": "Córdoba"
  },
  "cargos": [
    {
      "id": 1,
      "nombre": "PRESIDENTE Y VICE"
    },
    {
      "id": 3,
      "nombre": "DIPUTADO NACIONAL"
    },
    {
      "id": 8,
      "nombre": "PARLAMENTO MERCOSUR NACIONAL"
    },
    {
      "id": 9,
      "nombre": "PARLAMENTO MERCOSUR REGIONAL"
    }
  ]
}
```

3. Crear una api que exponga todas las secciones de un distrito (parametro obligatorio) o buscar una sección de 
un distrito (Opcional). <span style="color:red">**(10 puntos)**</span>

Por ejemplo, el consultar los datos por el distrito 4 (Córdoba) debería devolver los siguientes datos:

**Request**
```http
curl --location 'http://localhost:8080/secciones?distrito_id=4'
```
**Response**
```json
[
  {
    "id": 1,
    "nombre": "Capital"
  },
  {
    "id": 2,
    "nombre": "Calamuchita"
  },
  {
    "id": 3,
    "nombre": "Colón"
  }
]
```

**Request**
```http
curl --location 'http://localhost:8080/secciones?distrito_id=4&seccion_id=26'
```
**Response**
```json
[
  {
    "id": 26,
    "nombre": "Unión"
  }
]
```

4. Crear una api que exponga un resumen de los resultados (sumatoria total de votos) de una sección y distrito
ordenados de mayor a menor segun la cantidad de votos obtenidos por agrupación politica, votos en blanco, nulos, 
impugnados y recurridos (NO votos comando). Adicionalmente, se debe mostrar el porcentaje
de votos respecto al total del distrito y sección. <span style="color:red">**(25 puntos)**</span>

Por ejemplo, el consultar los datos por el distrito 4 (Córdoba) sección 26 (Unión) debería devolver los siguientes datos:

**Request**
```http
curl --location 'http://localhost:8080/resultados?distrito_id=4&seccion_id=26'
```
**Response**
```json
{
  "distrito": "Córdoba",
  "seccion": "Unión",
  "resultados": [
    {
      "orden": 1,
      "nombre": "LA LIBERTAD AVANZA",
      "votos": 24965,
      "porcentaje": 0.3497
    },
    {
      "orden": 2,
      "nombre": "JUNTOS POR EL CAMBIO",
      "votos": 17239,
      "porcentaje": 0.2415
    },
    {
      "orden": 3,
      "nombre": "HACEMOS POR NUESTRO PAIS",
      "votos": 16164,
      "porcentaje": 0.2264
    },
    {
      "orden": 4,
      "nombre": "UNION POR LA PATRIA",
      "votos": 11192,
      "porcentaje": 0.1567
    },
    {
      "orden": 5,
      "nombre": "FRENTE DE IZQUIERDA Y DE TRABAJADORES - UNIDAD",
      "votos": 750,
      "porcentaje": 0.0105
    },
    {
      "orden": 6,
      "nombre": "EN BLANCO",
      "votos": 645,
      "porcentaje": 0.0090
    },
    {
      "orden": 7,
      "nombre": "NULO",
      "votos": 423,
      "porcentaje": 0.0059
    },
    {
      "orden": 8,
      "nombre": "IMPUGNADO",
      "votos": 1,
      "porcentaje": 0.0001
    },
    {
      "orden": 9,
      "nombre": "RECURRIDO",
      "votos": 1,
      "porcentaje": 0.0001
    }
  ]
}
```

5. Crear los Test para que la aplicación tenga un 80 % de cobertura. <span style="color:red">**(20 puntos)**</span>

6. Entregar el proyecto con el archivo Dockerfile que permita ejecutar las aplicación en un contenedor. <span style="color:red">**(10 puntos)**</span>

7. Entregar el proyecto con un archivo docker-compose para poder ehjecutar en simultaneo el contenedor 
del server (la imagen **tupfrcutn/elecciones-2023:1.0.0**) y el cliente (nuestra app) <span style="color:red">**(20 puntos)**</span>