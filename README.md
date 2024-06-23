# Obligatorio_parte2_penaYperez

La carga de datos demora un minuto y medio, luego cada reporte te dice el tiempo y la memoria. 
En la línea 19 de carga de datos se debe colocar la ruta al archivo csv. 

Proceso de Carga de Datos 

Se encarga de cargar datos de canciones desde un archivo CSV y almacenarlos en tres estructuras de datos: songs, artists y countrys. Lee el archivo CSV línea por línea, omitiendo los encabezados y procesando cada línea para extraer la información de las canciones. Luego, crea objetos Song con estos datos, los añade a la estructura songs usando el spotify_id como clave, y actualiza las estructuras artists y countrys con los datos relevantes de artistas y países. También maneja posibles errores de formato y conversión de datos, asegurando que los campos de cada línea sean limpiados y formateados adecuadamente antes de ser procesados. 

Proceso de Realización de Reportes 

Top 10 Canciones en un País en un Día Dado:  


Primero, busca el país correspondiente al nombre proporcionado en el MyHashImpl<String, Country> countries. Luego, obtiene las 50 canciones principales de ese país y filtra aquellas que coinciden con la fecha especificada, almacenándolas en un nuevo MyHashImpl<Integer, Song> top50SongsListPaisPorFecha usando la posición de la canción como clave. Después, ordena las claves de estas canciones de menor a mayor posición. Finalmente, selecciona las primeras 10 canciones de la lista ordenada y las agrega a una nueva lista MyLinkedListImpl<Song> top10Songs, que se devuelve como resultado. 

 

Top 5 Canciones que Aparecen en Más Top 50 en un Día Dado: 

 

Agrega todas las canciones a un árbol binario de búsqueda (songTree). Luego, filtra las canciones del árbol para mantener solo aquellas que coinciden con la fecha dada. Posteriormente, cuenta la frecuencia de cada canción usando un hash (songFrequency). A continuación, ordena las canciones según su frecuencia de aparición en orden descendente. Finalmente, selecciona y devuelve una lista con los títulos de las cinco canciones más frecuentes en la fecha especificada. 

 

Top 7 Artistas que Más Aparecen en los Top 50 para un Rango de Fechas Dado: 

Comienza iterando sobre todos los artistas en el diccionario artists. Para cada artista, se cuentan cuántas de sus canciones estuvieron en las listas de canciones principales de varios países en el rango de fechas entre mindate y maxdate. Para ello, se recorren las canciones del artista y se verifican si caen dentro del intervalo de fechas. Si una canción del artista cumple con el criterio de fecha, se cuenta cuántas veces ese artista aparece en las listas de las canciones principales de cada país en el diccionario countries. Este conteo se almacena en un diccionario artistasConCantidad, donde la clave es el conteo y el valor es una lista de nombres de artistas que tienen ese conteo. Finalmente, después de haber recorrido todos los artistas y completado el diccionario artistasConCantidad, se devuelve una lista de los artistas organizados según el número de veces que sus canciones aparecieron en las listas de varios países dentro del rango de fechas dado. 

Cantidad de Veces que Aparece un Artista Específico en un Top 50 en una Fecha Dada: 

 

Primero, inicializa un contador en cero. Luego, recorre todos los países en el diccionario countries y, dentro de cada país, revisa cada canción en la lista de canciones principales. Para cada canción, verifica si el nombre del artista coincide con artistname y si la fecha de la canción coincide con la fecha especificada (date). Si ambas condiciones se cumplen, incrementa el contador. Al final, devuelve el valor del contador, que indica el número de veces que el artista aparece en las listas de canciones principales en la fecha dada. 

 

Cantidad de Canciones con un Tempo en un Rango Específico para un Rango de Fechas Específico: 

 

Inicializa un contador en cero y recorre todas las canciones en el diccionario. Para cada canción, verifica si su tempo está dentro del rango y si su fecha de lanzamiento está entre las fechas mínimas y máximas. Si ambas condiciones se cumplen, incrementa el contador. Finalmente, devuelve el valor del contador, que representa el número total de canciones que cumplen con los criterios especificados. 

 

