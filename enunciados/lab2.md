
# Laboratorio 2

En este laboratorio analizaremos los jugadores que aparecen en nuestra colección de partidas y crearemos un ranking con los mejores como el siguiente:

```
╔═════════════════╦══════════╦═══════════╗
║ Nombre          ║ Partidas ║ Victorias ║
╠═════════════════╬══════════╬═══════════╣
║ Mephostophilis  ║ 174      ║ 50,57   % ║
║ bmv             ║ 185      ║ 43,24   % ║
║ mrgrumpy        ║ 89       ║ 84,27   % ║
║ smit            ║ 204      ║ 35,78   % ║
║ rima            ║ 256      ║ 27,34   % ║
║ chesss4         ║ 86       ║ 79,07   % ║
║ potsy722        ║ 69       ║ 94,20   % ║
║ 1minute         ║ 86       ║ 73,26   % ║
║ ribarisah       ║ 80       ║ 78,75   % ║
║ trom            ║ 81       ║ 76,54   % ║
╚═════════════════╩══════════╩═══════════╝
```

Para conseguir este ranking tendremos que ver cuantas partidas ha jugado cada jugador y cuantas ha ganado. Utilizaremos un diccionario para que sea más fácil acceder a la información de cada jugador, también tendremos que ordenar los jugadores según el porcentaje de victorias que tenga cada uno.

## Paso 1: Crear clase Lab2.java

Empezaremos creando la clase en la que vamos a trabajar en este laboratorio. La clase se llamará "Lab2.java" y la colocaremos en el paquete laboratorios. De manera parecida a como se hace en el laboratorio 1, crearemos un método main que nos permita ejecutar la clase y comprobar que todo funciona correctamente. 

Además crearemos un constructor que debe recibir como parámetro un objeto de la clase `LectorPartidas`. En este constructor debemos inicializar las colecciones que utilizaremos para poder construir el ranking de jugadores: un diccionario que vincule cada jugador (un String con su nombre) con la lista de partidas en las que ha participado, y una lista que contenga todos los jugadores (y que luego ordenaremos).

Comencemos por rellenar el diccionario de jugadores (un `HashMap<String, List<Partida>>`). Para ello, podemos seguir el siguiente esquema:

```
Por cada partida en el lector de partidas:
	Por cada jugador en la partida:
		Si el jugador no está en el diccionario de jugadores:
			Añadir el jugador al diccionario con una lista vacía de partidas
		Añadir la partida al jugador en el diccionario
```

Una vez tengamos el diccionario, podemos crear una lista con todos los jugadores a partir de las claves del diccionario:

```java
jugadores = new ArrayList<>(diccionario.keySet());
```

## Paso 2: Crear método para calcular victorias

Para saber cual de los dos jugadores de una partida ha ganado, podemos ver el número de movimientos que se han hecho en la partida. Como una partida empieza siempre con el tablero inicial, los tableros en posiciones impares serán movimientos del jugador del bando de las blancas, y los tableros en posiciones pares (a excepción del 0) serán movimientos del jugador del bando de las negras. En estas partidas, el último movimiento es un jaque mate, por lo que el jugador que ha hecho el último movimiento es quien ha ganado la partida.

Con este razonamiento implementaremos el método `jugadorHaGanado` que nos dirá si el jugador que pasamos como parámetro ha ganado la partida que también pasamos como parámetro. Podemos utilizar la siguiente cabecera:

```java
private static boolean jugadorHaGanado(String jugador, Partida partida)
```

## Paso 3: Ordenar jugadores por número de victorias

Ahora que somos capaces de saber si un jugador ha ganado una partida, utilizaremos este método para ordenar la lista de jugadores según el número de victorias que tengan. Para ello, podemos utilizar el método `sort` de la clase `Collections`, pero no directamente, ya que en este caso no queremos utilizar el orden natural de los jugadores (orden alfabético al ser String), sino que queremos ordenarlos según el número de victorias que tengan. Para ello, podemos utilizar un comparador que compare dos jugadores según el número de victorias que tengan. Primero crearemos este comparador que nos permite comparar dos jugadores:

```java
Comparator<String> comparadorPartidasGanadas = new Comparator<String>() {
	@Override
	public int compare(String jugador1, String jugador2) {
		// Implementar comparación
	}
};
```

El método compare tiene que obtener el número de victorias de cada jugador y compararlos. Para obtener el número de victorias de un jugador, buscaremos en el diccionario de jugadores todas las partidas en las que ha participado y contaremos cuantas ha ganado. Una vez tengamos el comparador, podemos utilizarlo para ordenar la lista de jugadores:

```java
Collections.sort(jugadores, comparadorPartidasGanadas);
```

**Nota 1:** Estamos ordenando los jugadores de menor a mayor, al pintar el ranking deberemos recorrer la lista desde el final.
**Nota 2:** Esta solución es poco eficiente, ya que cada vez que queremos comparar dos jugadores, estamos buscando en el diccionario todas las partidas en las que ha participado. Probablemente esta operación se repita varias veces para el mismo jugador. Como mejora, podríamos calcular el número de victorias de cada jugador una sola vez y guardarlo en un diccionario aparte.

## Paso 4: Pintar el ranking

Una vez tengamos la lista de jugadores ordenada y el diccionario de jugadores relleno, podemos pintar el ranking. Crearemos un método con la siguiente cabecera:

```java
private void pintarRanking(int top)
```

Este método tiene que imprimir por pantalla una tabla como la que se muestra al principio de este enunciado, pero solo con los primeros `top` jugadores. Para ello, podemos recorrer la lista de jugadores desde el final hasta el principio, y para cada jugador, obtener el número de partidas en las que ha participado y el número de victorias que ha tenido. Con estos datos, podemos calcular el porcentaje de victorias y pintar la tabla.

Para que la tabla quede bien alineada, podemos utilizar la clase `String.format` para darle un formato concreto a los números que pintamos por pantalla. Por ejemplo, para pintar un número entero con dos decimales, podemos utilizar el siguiente formato:

```java
String.format("%.2f", numero)
```

Si queremos que un String ocupe al menos un número de caracteres (por ejemplo 10), podemos utilizar el siguiente formato:

```java
String.format("%-10s", string)
```

Para más información sobre el formato de los Strings, se puede consultar la documentación de la clase `Formatter`: https://docs.oracle.com/javase/8/docs/api/java/util/Formatter.html 

## Paso 5: Ejecutar el programa

Para comprobar que todo funciona correctamente, podemos modificar el método main para que cree un objeto de la clase `Lab2` y ejecute el método `pintarRanking` con un número de jugadores a mostrar. Por ejemplo, si queremos mostrar los 10 primeros jugadores, podemos utilizar el siguiente código:

```java
public static void main(String[] args) {
	LectorPartidas lector = new LectorPartidas("data/partidas.txt");
	Lab2 lab2 = new Lab2(lector);
	lab2.pintarRanking(10);
}
```
