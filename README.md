# Rest ip-info

## API Endpoints

Detalle de los endpoints disponibles

### Trace

------------

Retorna información del país de origen de la ip

#### Url

``` html
https://ip-info-service.herokuapp.com/api/v0/ip/trace
```
#### Método

`POST`

#### Body

`{
"ip": "0.0.0.0"
}`

### Stats

------------


Retornas estadísticas de las consultas realizadas

#### Url
``` html
https://ip-info-service.herokuapp.com/api/v0/ip/stats
```
#### método

`GET`

## Funcionalidad

Cuando se recibe una petición para consultar los datos de una ip, en primer lugar se obtiene el origen de la misma, desde la api *ip2country.info*.
Con el código del país obtenido previamente se busca la información del país en la base de datos, si existe y no esta desactualizada se utiliza la misma para procesar el resto de la petición.
Si la información del país no se encuentra en la base de datos, la misma es obtenida desde la api *restcountries.eu *para persistirla en la base.
Para determinar si la información esta desactualizada, se verifica el tiempo que trascurrió desde la ultima actualización del registro, si este es mayor al establecido, se consulta los datos en la api externa, se actualiza el registro en la tabla, y se retorna la información actualizada. El mimo mecanismo aplica en el caso de la cotización de la moneda desde la api *fixer.io*.
La configuración para determinar si la información de los países en la base esta desactualizada se realiza a través de properties(consultar configuración).

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

## Configuración

Key para la api fixer.io
`ipinfo.forex.key`

Cada cuentos minutos se debe actulizar la informacion de las monedas, el mismo depende del plan contratado
`ipinfo.forex.minutes.update`

Cada cuentos minutos se debe actulizar la informacion de los paises
`ipinfo.country.info.minutes.update`

Coordenadas desde las cuales se calcula la distancia del país de origen de la ip
`ipinfo.origin.latitude`
`ipinfo.origin.longitude`

## DER

![der](img "https://github.com/ezerio/ip-info/blob/master/docs/ip-info-der.png")

## Apis externas

Geolocalización de IPs: https://ip2country.info

Información de paises: http://restcountries.eu

Información sobre monedas: http://fixer.io

