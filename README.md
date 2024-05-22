# NBA proyecto creado por: Luís Sanchez y Alberto Morcillo

## Estructura de carpetas

Hemos dividido la estructura de carpetas en 4 principales: controlador, modelo.dao, modelo, vista y servicio.

La de modelo.dao es parte del modal pero lo hemos puesto fuera para que sea más fácil de encontrar. Pero al final es parte del modelo.

En el **modelo** hemos puesto las estructuras de los datos simples. Cada clase y sus atributos se corresponde a una 
tabla de la base de datos con sus columnas. 

En el **modelo.dao** hemos puesto las estructuras de los datos que se van a guardar en la base de datos.
Es decir, las query sql que se van a ejecutar (consultas, inserciones, actualizaciones y eliminaciones).
Decidimos ponerlo en un package aparte para que sea más fácil de encontrar y de entender además de que es mucho más limpio y modular.

En el **controlador** es donde estará la lógica de negocio usando modelo.dao. 

En la **vista** es donde se mostrará la información al usuario.

En el **servicio** es donde manajeremos todo el tema de los datos JSON.

## Clases

Las clases hemos intentado hacerlo de manera modular.

El **Player** es para tratar todo el tema del CRUD.

El **Team** es para tratar todo el tema del CRUD.

El **Match** es para tratar todo el tema del CRUD.

El **MatchStats** es para tratar todo el tema del CRUD.

El **PlayerStats** se ocupa de las estadísticas específicas de los jugadores,
como por ejemplo, los puntos, rebotes, asistencias, etc.

El **PlayerTeam** maneja las relaciones entre jugadores y equipos, 
útil para operaciones que involucran la asignación de jugadores a equipos, transferencias, etc.

El **TeamMatch** maneja las relaciones entre equipos y partidos,
útil para operaciones que involucran la asignación de equipos a partidos, resultados, etc.

El **TeamStats** se ocupa de las estadísticas específicas de los equipos,

El **MatchStats** se ocupa de las estadísticas específicas de los partidos,

Lo mismo pero con servicio, controlador, etc.


## Librerias necesarias
- Tienes que descargarte y instalar la libreria de connector **Driver de MySQL**. La puedes encontrar en el siguiente enlace: https://dev.mysql.com/downloads/file/?id=527658
                                                                                                          Por si peta el link:   https://1drv.ms/u/s!AiiOtLFISwSjhO5iczeYGqX5w7pIGA?e=E9Rtwj                                                 
- También se necesita la libreria de **json-simple-1.1.1.jar** que se puede encontrar en el siguiente enlace: https://code.google.com/archive/p/json-simple/downloads


## ¿Por qué usamos el hashmap para el segundo ejercicio 2?

Hemos usado el hashmap para el segundo ejercicio porque es una estructura de datos que nos permite almacenar datos de una manera más eficiente y rápida.
Al realizar una única consulta para obtener todos los nombres de los equipos y almacenarlos en un Map, reducimos drásticamente el número de accesos a la base de datos. 
Esto disminuye la carga en la base de datos y mejora el rendimiento general de la aplicación.
Antes de usar el hashmap, tardaba 2 o más minutos en cargar los datos.

## AVISO: IMPORTANTE
El ejercicio número 8, de rerirar el jugador, cuando le des que sí tienes que tener en cuenta que tarda unos 5 minutos.
Toca tener paciencia.