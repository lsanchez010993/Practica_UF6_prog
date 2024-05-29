# NBA Proyecto Creado por: Luís Sanchez y Alberto Morcillo

## Estructura de Carpetas

Hemos dividido la estructura de carpetas en 4 principales: controlador, modelo.dao, modelo, vista y servicio.

- **controlador**: Donde estará la lógica de negocio usando modelo.dao.
- **modelo.dao**: Parte del modelo que contiene las query SQL que se van a ejecutar (consultas, inserciones, actualizaciones y eliminaciones). Decidimos ponerlo en un package aparte para que sea más fácil de encontrar y de entender además de que es mucho más limpio y modular.
- **modelo**: Contiene las estructuras de los datos simples. Cada clase y sus atributos se corresponde a una tabla de la base de datos con sus columnas.
- **vista**: Donde se mostrará la información al usuario.
- **servicio**: Donde manejaremos todo el tema de los datos JSON.
- **database**: Donde se encuentra la base de datos SQL necesaria para su funcionamiento.
- **modelo/ArchivosGenerados**: Donde se guardan los archivos generados por **DataGenerator**.

## Clases

Las clases se han diseñado de manera modular:

- **Player**: Para tratar todo el tema del CRUD.
- **Team**: Para tratar todo el tema del CRUD.
- **Match**: Para tratar todo el tema del CRUD.
- **MatchStats**: Para tratar todo el tema del CRUD.
- **PlayerStats**: Se ocupa de las estadísticas específicas de los jugadores, como puntos, rebotes, asistencias, etc.
- **PlayerTeam**: Maneja las relaciones entre jugadores y equipos, útil para operaciones que involucran la asignación de jugadores a equipos, transferencias, etc.
- **TeamMatch**: Maneja las relaciones entre equipos y partidos, útil para operaciones que involucran la asignación de equipos a partidos, resultados, etc.
- **TeamStats**: Se ocupa de las estadísticas específicas de los equipos.
- **MatchStats**: Se ocupa de las estadísticas específicas de los partidos.
- **DataGenerator**: Genera datos de los jugadores y equipos de forma aleatoria. Alternativa a la base de datos propuesta por nosotros.
- **DataInserter**: Inserta los datos generados por **DataGenerator** en la base de datos.

## Librerías Necesarias

- Tienes que descargarte y instalar la librería de conector **Driver de MySQL**. La puedes encontrar en el siguiente enlace: [Descargar MySQL Connector](https://dev.mysql.com/downloads/file/?id=527658).
- También se necesita la librería de **json-simple-1.1.1.jar** que se puede encontrar en el siguiente enlace: [Descargar JSON Simple](https://code.google.com/archive/p/json-simple/downloads).

## ¿Por qué Usamos el HashMap para el Segundo Ejercicio?

Hemos usado el `HashMap` para el segundo ejercicio porque es una estructura de datos que nos permite almacenar datos de una manera más eficiente y rápida. Al realizar una única consulta para obtener todos los nombres de los equipos y almacenarlos en un `Map`, reducimos drásticamente el número de accesos a la base de datos. Esto disminuye la carga en la base de datos y mejora el rendimiento general de la aplicación. Antes de usar el `HashMap`, tardaba 2 o más minutos en cargar los datos.

## ¿Cómo Obtener los Datos de los Jugadores?

Tenemos dos formas:
1. Usando la base de datos que ya te hemos propuesto dentro de la carpeta `database`.
2. Generando datos aleatorios usando **DataGenerator** y luego insertándolos en la base de datos con **DataInserter**.

El **DataGenerator** genera:
- 30 equipos (reales)
- 300 jugadores (aleatorios)
- 900 estadísticas de jugadores (aleatorios)
- 150 partidos (aleatorios)
- 
## Insertar nuevo jugador a la BD
En la clase Player hemos tenido que hacer uso de dos constructores:
1. El primero para leer los datos ya introducidos en la base de datos y mostrarlos.
2. El segundo para validar los datos antes de insertarlos en la base de datos.

## Actualizar datos desde fichero
El ejercicio 6 lee los datos guardados en el fichero actualizarDatos. Dicho fichero se encuentra en la carpera actualizarDatos. 
Los datos del archivo txt deben seguir la siguiente estructura:
estadistiquesJugador
Datos de estadistiques de jugador
partits
Datos de partits


## AVISO: IMPORTANTE
- En el ejercicio 4, para evitar realizar lecturas innecesarias a la base de datos y con ello el impacto sobre el rendimiento, hemos decidido que sea el usuario el que decida que hacer: Añadir un jugador nuevo a un equipo existente o cambiar de equipo a un jugador existente. 
- El ejercicio número 8, de retirar el jugador, cuando le des que sí tienes que tener en cuenta que tarda unos 5 minutos. Toca tener paciencia.
- En el ejercicio 1 tienes que poner el nombre del equipo, no el nombre de la franquicia. Es decir, hay que poner Celtics, no Boston Celtics.
El motivo más importante es que en la historia de NBA se ha cambiado varias veces el nombre de la franquicia.

  De hecho, en este trabajo mismo hay un ejercicio donde puedes cambiar el nombre de la franquicia. Lo que no cambia es
  el nombre del equipo (Celtics, Lakers, etc). Por lo tanto, no importa si se ha cambiado el nombre de la franquicia.
  Para asegurarnos que el jugador entienda que tiene que poner Celtics hemos puesto un mensaje indicando que tiene que poner Celtics, no Boston Celtics.
- En el ejercicio 7 lo idea sería poner, además del jugador, en todos los equipos que ha jugado ese jugador y hacerle elegir al usuario
    que equipo quiere poner. No obstante, como lo hacemos solo en la última temporada, entendemos que no hay traspasos por lo tanto ya coje el equipo automaticamente.