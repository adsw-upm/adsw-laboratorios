# Laboratorio 2

En este laboratorio continuaremos con el desarrollo de la clase `GestorTableros`. 
En la clase `GestorTableros` realizaremos busquedas de tableros de una cierta puntuación
y plantearemos soluciones para recuperar datos de las partidas a partir de los tableros:

1. Buscar tableros según su puntuación mediante búsquedas binarias
2. Buscar entre las partidas un cierto tablero para recuperar su partida
3. Recuperar la partida del tablero con mayor puntuación y las partidas de los tableros 
con puntuación menor que una puntuación mínima.

El código que se desarrollará en este laboratorio será utilizado en la práctica 2.


## Paso 1: Búsqueda binaria

La primera tarea de este laboratorio es implementar un método de búsqueda binaria en la clase `GestorTableros`. 
Este método se llamará `busquedaBinaria`. Este método recibirá como parámetros una lista de tableros y una 
puntuación y devolverá el índice del **primer tablero** que tenga una puntuación mayor o igual a la puntuación 
que se pasa como parámetro. Si no hay ningún tablero con esa puntuación, el método devolverá el índice del 
primer tablero con una puntuación mayor. Si no hay ningún tablero con una puntuación mayor, el método devolverá 
el tamaño de la lista. Hay que recordar que la búsqueda binaria puede no devolver el índice del primer tablero 
con la puntuación que se busca (y nos están pidiendo el primero). Para realizar esta tarea debemos tener 
implementado el método `ordenarTableros`, y para ello debemos utilizar una clase `Tablero` que implementa 
`Comparable` mediante las puntuaciones de tablero que incluía el laboratorio 1. Por eso, lo primero que 
debemos hacer es actualizar las clases `Tablero` y `GestorTableros` con las actualizaciones que 
desarrollamos en laboratorio y en la práctica 1.

### Pruebas recomendadas

- Podemos comprobar que si pasamos como parámetro a `busquedaBinaria` la puntuación del tablero que devuelve 
`mayorTablero` debe tener como índice el último de la lista. Esta prueba se puede hacer con los siguientes pasos:

	1. Debemos tener la lista de tableros ordenada por puntuación como hicimos en la práctica 1
	2. Con la implementación de `mayorTablero` que debe recuperar el tablero de mayor puntuación general, de la lista
	de tableros ordenada, recuperamos el primer elemento de la lista
	3. Calculamos la puntuación del tablero que nos ha devuelto `mayorTablero`
	4. Pasamos como parámetros `tableros` y la puntuación calculada al método `busquedaBinaria` y recuperamos 
	el índice que nos devuelve.
	5. Con `assertEquals` podemos comprobar que el valor recuperado es el tamaño de la lista menos 3.

- Podemos llamar a `busquedaBinaria` con las puntuaciones de los tableros que nos devuelve 
`getTableroConPuntuacionMinima` y comprobar que se encuentran en las posiciones finales de la lista
(`busquedaBinaria` devuelve el primer tablero con una puntuación igual o mayor que la del parámetro y 
`getTableroConPuntuacionMinima` nos devuelve una lista de tableros que pueden tener puntuaciones repetidas).

## Paso 2: Recuperar datos de partida para un Tablero

En el laboratorio 1 trabajamos en la clase `GestorTableros`, pero solo trabajamos con los tableros que incluían los ficheros.
Otra información (por ejemplo la URL de las partidas, los usuarios de las partidas y el resto de los turnos de la partida)
está representada en la clase `Partida`. La implementación de la clase `Tablero` que hemos estado utilizando no incluye referencias
de la clase `Tablero` a la clase `Partida`, y por tanto no podemos saber, con la información de un tablero, a la partida a la que pertenece.

Para resolver este problema cambiaremos el constructor de `GestorTableros` para que guarde en un atributo la lista de las
partidas que incluye el fichero con el que trabaja el gestor (de una forma similar a como guardamos todos los tableros).

Para recuperar la partida de un tablero implementaremos en la clase `GestorTableros` los métodos `partidaMayorTablero` y 
`getPartidasTablerosPuntuacionMinima` que son análogos a los métodos `mayorTablero` y `getTablerosPuntuacionMinima`, 
implementados con los mismos parámetros, pero que nos devolverán la partida y la lista de partidas para el tablero con 
mayor puntuación y las patidas de los tableros con puntuación mínima. Para recuperar la partida de un tablero, 
implementaremos un método publico `partidaDeTablero` (con un tablero como parámetro y devuelve una partida) 
que recorriendo todas las partidas, y para cada partida mira en sus turnos si el tablero se encuentra entre ellos.

Los métodos que desarrollaremos en este laboratorio son:

- `public Partida partidaDeTablero(Tablero t)` : Devuelve la partida en la que está incluido un cierto tablero. Si ese 
tablero no está en ninguna partida, devuelve null.
- `public Partida partidaMayorTablero()` : Devuelve la partida del tablero con mayor puntuación general. Este 
método puede reutilizar los métodos `mayorTablero` y `partidaDeTablero` en su implementación.
- `public List<Partida> getPartidasTablerosConPuntuacionMinima(int puntuacion)` : Devuelve una lista con las 
partidas que tienen tableros con una puntuación igual o superior a la puntuación mínima especificada. Este 
método puede reutilizar los métodos `getTablerosConPuntuaconMinima` y `partidaDeTablero` en su implementación.

### Pruebas recomendadas

- El constructor de la clase `GestorTableros` deja en un atributo de la clase la lista de las partidas.
Podemos recuperar la primera de las partidas y de la primera de las partidas recuperamos el primero de los turnos y podemos 
comprobar que la partida que nos devuelve `partidaDeTablero` es la primera de las partidas. Los pasos de la implementación de esta
prueba serían:

	1. partidas.get(0) nos devuelve la primera de las partidas.
	2. Para la primera de las su atributo turnos incluye la lista de los tableros de la partida
	3. Recuperamos el primero de los turnos y lo utilizamos como parámetro de `partidaDeTablero`
	4. La partida que nos devuelve `partidaDeTablero` debe ser la primera de las partidas. Podemos utilizar `assertEquals`
	para comprobar esta condición en la prueba.

- Podemos construir un nuevo tablero que no está en ninguna partida, y comprobamos que `partidaDeTablero` devuelve null 
para ese tablero.

### Comprobación avanzada

La búsqueda lineal que hacemos para encontrar la partida de un tablero, buscando entre todas las partidas y dentro de las 
partidas en sus turnos, requiere una cantidad de tiempo de ejecución no despreciable. Podemos hacer estimaciones con la siguiente
ejecución, que va probando el método `partidaDeTablero` para todos los tableros de todas las partidas.

Si nuestra implementación es una búsqueda lineal sencilla, que empieza buscando en las primeras partidas, y dentro de las
partidas en los primeros turnos, las llamadas que hacemos a `partidasDeTablero` con los primeros tableros, 
se encontrarán en poco tiempo, pero a medida que hacemos más llamadas el tiempo que empleamos para encontrar el tablero 
será mayor. Si utilizamos una búsqueda lineal e intentamos encontrar todos los tableros, el tiempo será eterno. Por eso 
esta prueba incluye la posibilidad de limitar el número máximo de tableros que buscamos. 

En unos ordenadores el tiempo que tardamos para encontrar un cierto máximo será mayor que en otros. Por eso, podremos 
ajustar el máximo según sea de eficiente nuestro ordenador, y según sea nuestra paciencia para esperar que la prueba termine. 
En un ordenador bastante eficiente, si fijamos el máximo a 100.000, y utilizamos el fichero de datos partidas.txt que tiene 
poco más de 700.000 tableros, el tiempo de ejecución será del orden de 7 segundos. Si fijamos el máximo a 700.000 y utilizamos 
una busqueda lineal normal, el tiempo será mucho más de 49 segundos.

```
        int maximo=100000;
        long t=System.nanoTime();
        terminar:
        for (Partida pp : gestor.partidas)
        	for (Tablero tt : pp.turnos) {
	        	if (!pp.url.equals(gestor.partidaDeTablero(tt).url))
	        		System.out.println(pp.url+" "+gestor.partidaDeTablero(tt).url);
	        	if (maximo-- < 0)
	        		break terminar;
        	}
        System.out.println(System.nanoTime()-t);
 ```
