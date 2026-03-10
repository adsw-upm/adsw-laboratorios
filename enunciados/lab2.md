# ADSW Laboratorio 2: Resolución por Fuerza Bruta del problema de Cifras

Como recordatorio, en la prueba de Cifras se dispone de un conjunto de números y un número objetivo.  
El objetivo es combinar algunos o todos los números usando operaciones aritméticas básicas (*, /, +, -) para obtener exactamente ese valor o aproximarse lo máximo posible.

Por ejemplo, si los números son:

    50, 75, 6, 8

y el objetivo es 952, una posible expresión sería:

    952 = 50 + 75 - 6 * 8

En este laboratorio se propone **implementar una solución automática para el problema de Cifras**. Para ello, se generarán y 
evaluarán todas las expresiones posibles a partir de un conjunto dado de números (fuerza bruta), con el objetivo de encontrar 
la más cercana al valor objetivo.

Las reglas de las operaciones son las mismas que en el Laboratorio 0. Las expresiones se operarán de izquierda a derecha, 
sin prioridad de operadores ni paréntesis. Además:

- No se permiten resultados intermedios negativos.
- Las divisiones no pueden resultar en números decimales, ni se puede dividir entre 0.

---

## Preparación inicial

Antes de comenzar con las tareas:

1. Crea una nueva clase llamada `CifrasFuerzaBruta` que implemente la interfaz `Cifras`.
2. Copia y pega el siguiente código base en dicha clase.

A lo largo de este laboratorio completarás los métodos marcados con `TODO` (del inglés To Do, o Por Hacer).

```java
package es.upm.dit.adsw.cifrasyletras.cifras;

import java.util.*;

public class CifrasFuerzaBruta implements Cifras {

    private final List<String> operadores = new ArrayList<>(List.of("+", "-", "*", "/"));

    /**
     * Método principal del problema de Cifras.
     *
     * A partir de un número objetivo y una lista de números disponibles,
     * debe generar todas las expresiones posibles (sin paréntesis),
     * evaluarlas en modo calculadora (operando de dos en dos, estrictamente de izquierda a derecha) y
     * devolver la expresión cuyo resultado sea el más cercano al objetivo.
     *
     * La expresión devuelta debe tener el formato:
     *
     *     "resultado = n0 op0 n1 op1 n2 ..."
     *
     * Ejemplo:
     *     "952 = 50 + 75 - 6 * 8"
     *
     * @param objetivo número al que se desea aproximarse
     * @param numeros lista de números disponibles para construir expresiones
     * @return String con la mejor expresión encontrada y su resultado
     */
    @Override
    public String obtenerCifra(int objetivo, List<Integer> numeros) {
        // Caso trivial
        if (numeros.size() == 1) {
            return numeros.get(0) + " = " + numeros.get(0);
        }

        // Diccionario: resultado -> "resultado = expresion"
        Map<Integer, String> diccionarioCifras = new HashMap<>();

        // TODO (Tarea 3): completar el cuerpo de este método.
        // Pista: para cada tamaño i (desde numeros.size() hasta 1),
        // 1) genera variaciones de números (sin repetición) de tamaño i
        // 2) genera variaciones de operadores (con repetición) de tamaño i-1
        // 3) construye expresiones intercalando números y operadores (usar el método construirExpresiones)
        // 4) calcula y valida cada expresión; si es válida, almacena en diccionarioCifras

        // Si no se ha encontrado ninguna expresión válida, devolvemos un caso seguro.
        if (diccionarioCifras.isEmpty()) {
            return numeros.get(0) + " = " + numeros.get(0);
        }

        // Selección del mejor resultado (ya implementado)
        List<Integer> listadoResultados = new ArrayList<>(diccionarioCifras.keySet());
        Collections.sort(listadoResultados);
        Integer mejor = obtenerMejorResultado(objetivo, 0, listadoResultados.size() - 1, listadoResultados);

        return diccionarioCifras.get(mejor);
    }

    /**
     * Selecciona, mediante búsqueda binaria, el valor de la lista ordenada
     * que esté más próximo al objetivo.
     *
     * Si el objetivo existe en la lista, se devuelve directamente.
     * En caso contrario, se compara el elemento inmediatamente inferior
     * y el inmediatamente superior para determinar cuál está más cerca.
     *
     * @param objetivo valor al que se desea aproximarse
     * @param low índice inferior del rango de búsqueda
     * @param high índice superior del rango de búsqueda
     * @param listadoResultados lista ordenada de resultados posibles
     * @return valor del listado que esté más cercano al objetivo
     */
    public Integer obtenerMejorResultado(int objetivo, int low, int high, List<Integer> listadoResultados) {
        // Paramos cuando low > high -> Ya habremos evaluado todos los casos.
        if (low > high) {
            if (high < 0) return listadoResultados.get(0);
            if (low > listadoResultados.size() - 1) return listadoResultados.get(listadoResultados.size() - 1);

            // En este caso toca desempatar entre ambas opciones
            int valorDerecha = Math.abs(listadoResultados.get(low) - objetivo);
            int valorIzquierda = Math.abs(listadoResultados.get(high) - objetivo);

            if (valorDerecha < valorIzquierda) {
                return listadoResultados.get(low);
            } else {
                return listadoResultados.get(high);
            }
        }

        // Seguimos dividiendo el rango
        int mid = low + (high - low) / 2;

        if (objetivo == listadoResultados.get(mid)) {
            return listadoResultados.get(mid);
        } else if (objetivo < listadoResultados.get(mid)) {
            return obtenerMejorResultado(objetivo, low, mid - 1, listadoResultados);
        } else {
            return obtenerMejorResultado(objetivo, mid + 1, high, listadoResultados);
        }
    }

    /**
     * Variaciones con repetición:
     * - El orden importa.
     * - Se permite repetir elementos.
     *
     * En este laboratorio se utiliza para generar las posibles secuencias de operadores,
     * ya que los operadores (+, -, *, /) pueden repetirse tantas veces como sea necesario.
     *
     * @param elementos lista de elementos disponibles (p.ej., ["+","-","*","/"])
     * @param k longitud de cada variación (p.ej., si hay i números, entonces k = i-1 operadores)
     * @return lista con todas las variaciones posibles de longitud k
     */
    public List<List<String>> variacionesConRepeticion(List<String> elementos, int k) {
        // Para simplificar el laboratorio: si k <= 0, no generamos nada.
        // (En nuestro caso, k será i-1, y solo tiene sentido cuando i >= 2)
        if (k <= 0) return new ArrayList<>();

        List<List<String>> resultado = new ArrayList<>();
        generarVariacionesConRepeticion(elementos, k, new ArrayList<>(), resultado);
        return resultado;
    }

    /**
     * Método auxiliar recursivo que construye variaciones con repetición.
     * La lista "actual" representa la variación parcial que estamos construyendo.
     *
     * @param elementos lista de elementos disponibles
     * @param k longitud objetivo de la variación
     * @param actual variación parcial construida hasta el momento
     * @param resultado lista donde se almacenan todas las variaciones completas
     */
    private void generarVariacionesConRepeticion(List<String> elementos, int k,
                                                 List<String> actual,
                                                 List<List<String>> resultado) {
        // Caso base: si ya hemos elegido k elementos, guardamos la variación.
        if (actual.size() == k) {
            resultado.add(new ArrayList<>(actual));
            return;
        }

        // Probamos todos los elementos posibles en la siguiente posición.
        for (String e : elementos) {
            actual.add(e);
            generarVariacionesConRepeticion(elementos, k, actual, resultado);
            actual.remove(actual.size() - 1); // deshacer la elección
        }
    }

    /**
     * Variaciones sin reutilizar índices:
     * - El orden importa.
     * - No se puede reutilizar la misma posición de la lista original.
     * - Si el conjunto inicial contiene valores repetidos,
     *   podrán utilizarse tantas veces como aparezcan (ya que son posiciones distintas).
     *
     * En este laboratorio se utiliza para generar las posibles variaciones
     * de los números dados en el problema.
     *
     * @param elementos lista original de números disponibles
     * @param k longitud de cada variación
     * @return conjunto con todas las variaciones posibles de tamaño k
     */
    public Set<List<Integer>> variacionesSinRepeticion(List<Integer> elementos, int k) {
        if (k <= 0 || k > elementos.size()) return new HashSet<>();

        Set<List<Integer>> resultado = new HashSet<>();
        boolean[] usados = new boolean[elementos.size()];

        generarVariacionesSinRepeticion(elementos, k, new ArrayList<>(), resultado, usados);

        return resultado;
    }

    /**
     * Método auxiliar recursivo que construye variaciones sin reutilizar índices.
     *
     * La lista "actual" representa la variación parcial que se está construyendo.
     * El array "usados" indica qué posiciones de la lista original ya han sido utilizadas.
     *
     * @param elementos lista original de números disponibles
     * @param k longitud objetivo de la variación
     * @param actual variación parcial construida hasta el momento
     * @param resultado conjunto donde se almacenan las variaciones completas
     * @param usados array que marca qué índices ya han sido utilizados
     */
    private void generarVariacionesSinRepeticion(List<Integer> elementos,
                                                 int k,
                                                 List<Integer> actual,
                                                 Set<List<Integer>> resultado,
                                                 boolean[] usados) {
        /*
         * TODO (Tarea 1): implementar el caso base y el paso recursivo.
         * Se puede inspirar en el método generarVariacionesConRepeticion, pero teniendo en cuenta que aquí no se pueden reutilizar índices.
         */
    }

    /**
     * Construye expresiones intercalando números y operadores.
     *
     * Una expresión se representa como una lista de Strings alternando:
     * numero, operador, numero, operador, ...
     *
     * Ejemplo:
     * - Números:    [50, 75, 6]
     * - Operadores: ["+", "-"]
     * - Expresión:  ["50", "+", "75", "-", "6"]
     *
     * Este método combina todas las variaciones de números con todas las
     * variaciones de operadores compatibles, generando así todas las
     * expresiones posibles para un tamaño determinado.
     *
     * @param variacionesNumeros conjunto de listas de números
     *                           (cada lista representa una variación concreta)
     * @param variacionesOperadores lista de listas de operadores
     *                              (cada lista representa una secuencia concreta)
     * @return lista con todas las expresiones construidas
     */
    public List<List<String>> construirExpresiones(Set<List<Integer>> variacionesNumeros,
                                                   List<List<String>> variacionesOperadores) {

        List<List<String>> listadoExpresiones = new ArrayList<>();

        // Caso: sin operadores (expresiones de un solo número)
        if (variacionesOperadores.isEmpty()) {
            for (List<Integer> nums : variacionesNumeros) {
                List<String> expr = new ArrayList<>();
                expr.add(String.valueOf(nums.get(0)));
                listadoExpresiones.add(expr);
            }
            return listadoExpresiones;
        }

        // Caso general
        for (List<Integer> nums : variacionesNumeros) {
            for (List<String> ops : variacionesOperadores) {
                List<String> expr = new ArrayList<>();
                for (int j = 0; j < nums.size(); j++) {
                    expr.add(String.valueOf(nums.get(j)));
                    if (j < ops.size()) expr.add(ops.get(j));
                }
                listadoExpresiones.add(expr);
            }
        }

        return listadoExpresiones;
    }

    /**
     * TODO (Tarea 2): Calcular una expresión (sin paréntesis) en modo calculadora:
     * operando de dos en dos, estrictamente de izquierda a derecha.
     *
     * Reglas:
     * - Si aparece un resultado intermedio negativo -> la expresión es inválida.
     * - La división solo es válida si el divisor no es 0 y la división es exacta.
     * - Si la expresión es inválida, el método debe devolver -1.
     *
     * @param expresion expresión a evaluar, representada como:
     *                 ["n0", "op0", "n1", "op1", "n2", ...]
     * @return resultado final si la expresión es válida; -1 en caso contrario
     */
    public int calcularExpresion(List<String> expresion) {
        // TODO (Tarea 2)
        return -1;
    }
}
```

---

## Tarea 1. Generación de variaciones sin reutilizar posiciones

En esta tarea se debe completar el método:

    generarVariacionesSinRepeticion(...)

que es el método auxiliar utilizado por:

    variacionesSinRepeticion(List<Integer> elementos, int k)

---

### Objetivo

Generar todas las variaciones posibles de tamaño `k` a partir de la lista de números dada, cumpliendo las siguientes condiciones:

- El orden importa; es decir, no es lo mismo `[2,3]` que `[3,2]`.
- No se puede reutilizar el mismo elemento de la lista original más de una vez en la misma variación.
- Si el conjunto inicial contiene valores repetidos, podrán utilizarse tantas veces como aparezcan (porque están en posiciones distintas de la lista).

Es importante entender que aquí no estamos prohibiendo repetir valores, sino reutilizar la **misma posición** de la lista original.

---

### Ejemplo

Si la lista original es:

    [2, 3, 5]

y `k = 2`, el resultado debe contener:

    [2,3], [2,5], [3,2], [3,5], [5,2], [5,3]

Si la lista original es:

    [2, 2, 3]

y `k = 2`, se podrán generar combinaciones como:

    [2,2], [2,3], [3,2]

porque existen dos posiciones distintas que contienen el valor 2.

---

### Sobre el array `usados`

El método recibe un array booleano llamado `usados`.

- `usados[i] = true` significa que el elemento en la posición `i` ya se ha utilizado en la variación parcial actual.
- `usados[i] = false` significa que todavía puede utilizarse.

Este array permite controlar qué posiciones ya han sido elegidas en cada paso.

---

### Idea de implementación

El método recibe:

- `elementos`: lista original de números.
- `k`: tamaño objetivo de cada variación.
- `actual`: lista que representa la variación parcial que se está construyendo.
- `resultado`: conjunto donde deben almacenarse las variaciones completas.
- `usados`: array booleano que indica qué posiciones ya han sido utilizadas.

La lógica debe ser:

1. Caso base:  
   Si `actual.size() == k`, se ha construido una variación completa.  
   En ese caso:
    - Añadir una copia de `actual` al conjunto `resultado`.
    - Terminar la ejecución del método.

2. Paso recursivo:  
   Recorrer todas las posiciones de la lista original (desde 0 hasta `elementos.size() - 1`).

   Para cada posición `i`:
    - Si `usados[i]` es `true`, se ignora esa posición.
    - Si no está usada:
        - Añadir `elementos.get(i)` a `actual`.
        - Marcar `usados[i] = true`.
        - Llamar recursivamente al mismo método.
        - Al volver, deshacer la elección:
            - Quitar el último elemento añadido a `actual`.
            - Marcar `usados[i] = false`.

---

### Comprobaciones

Antes de continuar con la siguiente tarea, asegúrate de que:

- El método genera todas las variaciones posibles de tamaño `k`.
- No se reutilizan elementos ya seleccionados.

Se recomienda hacer pruebas antes de integrarlo en el método principal.

---

## Tarea 2. Evaluación de una expresión

En esta tarea se debe completar el método:

    calcularExpresion(List<String> expresion)

Este método recibirá una expresión ya construida (resultado del método `construirExpresiones(...)`) y deberá calcular su resultado.

---

### Formato de entrada

La expresión se representa como una lista de `String` alternando:

    ["n0", "op0", "n1", "op1", "n2", ...]

Por ejemplo:

    ["50", "+", "75", "-", "6"]

Puedes asumir que la expresión está bien construida (no faltan números u operadores en medio), ya que proviene del método `construirExpresiones(...)`.

---

### Objetivo

Calcular el resultado final de la expresión en **modo calculadora**:

- Se opera de dos en dos.
- Estrictamente de izquierda a derecha.
- Sin aplicar precedencia de operadores.
- Sin paréntesis.

Ejemplo:

    50 + 75 - 6 * 8

se evalúa como:

    ((50 + 75) - 6) * 8

---

### Reglas de validez

Durante la evaluación, si ocurre cualquiera de estos casos, la expresión se considera inválida y el método debe devolver `-1`:

- Aparece un resultado intermedio negativo.
- Se intenta dividir entre 0.
- La división no es exacta (es decir, resultaría en un número decimal).

---

### Qué debe devolver el método

- Si la expresión es válida, devolver el resultado final (tipo `int`).
- Si la expresión es inválida, devolver `-1`.

---

### Pista de implementación

1. Convertir el primer número (`expresion.get(0)`) a entero y guardarlo como resultado parcial.
2. Recorrer la lista desde la posición 1, saltando de 2 en 2 posiciones (operador y número).  
   En cada paso:
    - Leer el operador.
    - Leer el siguiente número.
    - Antes de realizar la operación, comprobar:
        - Si es división:
            - El divisor no puede ser 0.
            - La división debe ser exacta.
        - Si es resta:
            - No debe producir un resultado negativo.
    - Si alguna comprobación falla, devolver `-1`.
    - Si todo es válido, operar el resultado parcial y el número y actualizar el resultado parcial.
3. Si se termina el recorrido sin problemas, devolver el resultado parcial.

> [!TIP]
> Este método es muy parecido al método `esValida()` que se implementó en la clase `ValidadorCifras` en el Laboratorio 0.
> Si tienes esa implementación, puedes reutilizar parte de la lógica. En este caso, el método `calcularExpresion(...)` 
> debe devolver el resultado del cálculo de la expresión, no validar si la expresión es correcta o incorrecta.

Se recomienda probar este método con expresiones pequeñas antes de integrarlo en la Tarea 3.

---

## Tarea 3. Integración final

En esta última tarea se debe completar el cuerpo del método:

    obtenerCifra(int objetivo, List<Integer> numeros)

Llegados a este punto, toda la funcionalidad necesaria ya está implementada:

- Ya podemos generar variaciones de números.
- Ya podemos generar variaciones de operadores.
- Ya podemos construir expresiones.
- Ya podemos evaluarlas.

Por tanto, esta tarea consiste únicamente en **conectar todas las piezas anteriores**.

---

### Qué debe hacer el método

La idea es:

1. Probar expresiones utilizando distintos tamaños de lista de números de mayor a menor (desde `numeros.size()` hasta 1).

2. Para cada tamaño `i`:

    - Generar variaciones de números con `variacionesSinRepeticion(...)`.
    - Generar variaciones de operadores con `variacionesConRepeticion(...)` (si `i = 1`, la lista de operadores será vacía).
    - Construir expresiones con `construirExpresiones(...)`.
    - Evaluar cada expresión con `calcularExpresion(...)`.

3. Si el resultado es válido (distinto de `-1`), almacenarlo (como `Clave`) en `diccionarioCifras`
   junto con la expresión (como `Valor`) en formato `String`:

       "resultado = expresión"

---

### Paso final

El método ya incluye el código necesario para:

- Ordenar los resultados obtenidos.
- Seleccionar el más cercano al objetivo.
- Devolver la expresión asociada.

No es necesario modificar esa parte.

---

### Idea clave

Si las Tareas 1 y 2 funcionan correctamente, esta parte consiste en organizar llamadas a métodos dentro de los bucles adecuados.