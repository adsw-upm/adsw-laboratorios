# Laboratorio 4

En este laboratorio trabajaremos con la concurrencia en Java para acelerar el cálculo de caminos en el grafo de tableros de ajedrez. Aunque el laboratorio es independiente de la práctica 3, se basa en conceptos similares: en la práctica, se propone encontrar todos los caminos desde un tablero dado hasta un jaque mate en menos de `N` movimientos. En este laboratorio realizaremos un estudio similar buscando todos los caminos posibles entre dos tableros.

El objetivo principal de este laboratorio es utilizar un **pool de hebras (thread pool)** para paralelizar el cálculo de todos los caminos posibles entre dos tableros de ajedrez dados. Este tipo de cálculo puede ser muy costoso computacionalmente, por lo que resulta un ejemplo ideal para aplicar técnicas de concurrencia.

### Objetivos

- Entender cómo aplicar concurrencia en el procesamiento de grafos
- Implementar un pool de hebras que trabaje sobre caminos en un grafo
- Medir el impacto de la concurrencia en el tiempo de cálculo
- Comparar resultados

## Paso 1: Preparar el entorno de trabajo

Creamos la clase `Lab4.java` en el paquete `laboratorios`. En esta clase implementaremos el método `main` y la lógica de concurrencia necesaria. El punto de partida será un grafo de tableros de ajedrez que generaremos de la misma manera que en la práctica 2 o el laboratorio 3. 

Para ello, asegúrate de haber completado los pasos de la práctica 3, especialmente la implementación de la clase `AnalizadorConcurrente`. Puedes reutilizar la lógica de concurrencia y el pool de hebras implementados en esa práctica.

## Paso 2: Definir el problema

Queremos encontrar todos los caminos posibles desde un tablero de origen `T1` hasta un tablero de destino `T2` en el grafo. Un camino es una secuencia de aristas conectadas que lleva desde `T1` a `T2`.

Dado que el número de caminos puede crecer exponencialmente, es necesario:

- Eliminar ciclos (no repetir nodos dentro del mismo camino).
- Ejecutar múltiples exploraciones en paralelo para mejorar el rendimiento.

La tarea que resuelven las hebras cambia ligeramente con respecto a la práctica 3. En lugar de buscar un camino hasta un jaque mate, ahora buscamos todos los caminos posibles entre dos tableros dados. Para ello, definiremos la clase `TareaCaminoLaboratorio` que representará una tarea de búsqueda de caminos entre 2 tableros. Además del siguiente nodo a explorar y el camino recorrido, también guardaremos el tablero de destino `T2`.

### `TareaCaminoLaboratorio`:

```java
class TareaCaminoLaboratorio {
    Nodo nodoActual;
    List<Nodo> caminoRecorrido;
    Tablero destino;

    TareaCaminoLaboratorio(Nodo nodoActual, List<Nodo> caminoRecorrido, Tablero destino) {
        this.nodoActual = nodoActual;
        this.caminoRecorrido = caminoRecorrido;
        this.destino = destino;
    }
}
```

## Paso 3: Crear el pool de hebras

Reutiliza la implementación de la clase `PoolHebras` de la práctica 3. Asegúrate de que el pool de hebras pueda gestionar múltiples tareas concurrentes y que las tareas sean instancias de la clase `TareaCaminoLaboratorio`. Si es necesario, adapta la lógica para que se ajuste al problema de encontrar caminos entre dos tableros específicos (`T1` y `T2`).

### Pseudocódigo para el pool de hebras:

```java
class PoolHebras {
    List<TareaCaminoLaboratorio> tareas;
    List<List<Nodo>> resultados;
    List<HebraWorker> hebras;

    PoolHebras(int numHebras) {
        // Inicializar las colecciones
    }

    synchronized void addTarea(TareaCaminoLaboratorio tarea) {
        // Añadir tarea a la lista de tareas y notificar a las hebras
    }

    synchronized TareaCaminoLaboratorio getTarea() {
        // Obtener una tarea de la lista de tareas
    }

    synchronized void addResultado(List<Nodo> camino) {
        // Añadir el camino encontrado a la lista de resultados
    }

    synchronized List<List<Nodo>> getResultados() {
        // Devolver la lista de resultados
    }
}
```

## Paso 4: Implementar la búsqueda de caminos

1. **Inicializar la búsqueda**:
   - En la clase `Lab4`, crea un método que inicie la búsqueda de caminos entre dos tableros pasados como parámetros utilizando el pool de hebras.
   - A diferencia de la práctica 3, este método se quedará esperando a que el pool de hebras le devuelva los resultados. 
   - El método debe tener la siguiente cabecera: `public List<List<Nodo>> iniciarBusqueda(Tablero T1, Tablero T2)`.

### Pseudocódigo para `iniciarBusqueda`:

```java
public List<List<Nodo>> iniciarBusqueda(Tablero T1, Tablero T2) throws InterruptedException {
    if (!nodos.containsKey(T1)) {
        return null;
    }
    Nodo nodoInicial = nodos.get(T1);
    TareaCaminoLaboratorio tareaInicial = new TareaCaminoLaboratorio(nodoInicial, new ArrayList<>(), T2);
    pool.addTarea(tareaInicial);

    return pool.getResultados();
}
```

> [!CAUTION] 
> Cuidado con los interbloqueos. Puede ser que no se encuentre ningún camino entre los tableros y que la hebra que se queda esperando los resultados no se despierte nunca. Para evitar esto, deberíamos implementar algún mecanismo que nos permita saber cuantas hebras están resolviendo tareas, y si en algún momento no hay ninguna, despertar a la hebra principal que espera los resultados.

2. **Procesar las tareas**:
   - En la clase HebraWorker, cada tarea debe explorar los nodos adyacentes al nodo actual.
   - Si se alcanza el tablero `T2`, añade el camino a la lista de resultados.
   - Si no, crea nuevas tareas para los nodos adyacentes y añádelas al pool.

3. **Evitar ciclos**:
   - Asegúrate de que los nodos ya visitados no se repitan en el mismo camino.

## Paso 5: Medir el rendimiento

1. **Tiempo de ejecución**:
   - Mide el tiempo que tarda en completarse la búsqueda con diferentes números de hebras.
   - Compara los resultados con una implementación secuencial, o con una única hebra.

   ```java
   long startTime = System.currentTimeMillis();
   List<List<Nodo>> resultados = lab4.iniciarBusqueda(T1, T2); // Llamada al método que inicia la búsqueda
   long endTime = System.currentTimeMillis();
   System.out.println("Tiempo de ejecución: " + (endTime - startTime) + " ms");
   ```

2. **Resultados**:
   - Imprime el número total de caminos encontrados.
   - Analiza cómo varía el tiempo de ejecución al aumentar el número de hebras.

   ```java
   System.out.println("Caminos encontrados: " + pool.getResultados().size());
   ```

## Paso 6: Reflexión final

Reflexiona sobre las siguientes preguntas:

- ¿Qué mejoras observas al usar múltiples hebras?
- ¿Existe un punto en el que añadir más hebras ya no mejora el rendimiento?
- ¿Qué otras técnicas podrían usarse para acelerar este tipo de cálculos?

## Referencias

- **Práctica 3**: Revisa la implementación de `AnalizadorConcurrente` y el uso de `PoolHebras` para entender cómo gestionar tareas concurrentes.
- **Documentación de Java**: Consulta la API de `Thread` y `synchronized` para profundizar en la gestión de concurrencia.
