# Rest ip-info

## API Endpoints

Detalle de los endpoints disponibles

### Trace

------------

Retorna informacion del pais de origen de la ip consultada

#### Url

``` html
https://ip-info-service.herokuapp.com/api/v0/ip/trace
```
#### Metodo

`POST`

#### Body

`{
"ip": "0.0.0.0"
}`

### Stats

------------


Retornas estadisticas de las consultas realizadas

#### Url
``` html
https://ip-info-service.herokuapp.com/api/v0/ip/stats
```
#### Metodo

`GET`

## Arquitectura

### Responsabilidades

- **apis**: Realiza las peticiones a las APis externas

- **controllers**: Contiene las calses que conforman la API, define el path del recurso.

- **daos**: Clases de acceso a base de datos

- **dtos**: Trasporte de datos y valaidacion

- **entitys**: Clases que representan el modelo relacional de la base de datos

- **exceptions**: Tratamiento de errores, convierte las excepciones lanzadas por la palicacion en respuestas de error HTTP

- **services**: Clases que realizan la logica de negocio que procesan las peticiones

- **utility**: Clases  de apoyo para realizar calculos
