package es.upm.dit.adsw.cifrasyletras.juego;

import es.upm.dit.adsw.cifrasyletras.letras.Letras;
import es.upm.dit.adsw.cifrasyletras.letras.LetrasHumano;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import es.upm.dit.adsw.cifrasyletras.cifras.Cifras;
import es.upm.dit.adsw.cifrasyletras.cifras.CifrasHumano;

/**
 * Clase que implementa el juego "Cifras y Letras" para un jugador humano.
 * 
 * Este juego consiste en dos tipos de pruebas:
 *  - Pruebas de letras: Se generan letras aleatorias y el jugador debe formar la palabra más larga posible.
 *  - Pruebas de cifras: Se generan números aleatorios y un objetivo, y el jugador debe construir una expresión
 *       aritmética que se aproxime al objetivo usando los números disponibles.
 *
 * @author ADSW
 * @version 1.0
 */
public class JuegoHumano {

    /** Número de letras a generar en cada prueba de letras. */
    private final int NUM_LETRAS = 9;
    
    /** Número de cifras a generar en cada prueba de cifras. */
    private final int NUM_CIFRAS = 6;

    private int pruebasCifras;
    private int pruebasLetras;

    private ValidadorCifras validadorCifras;
    private ValidadorLetras validadorLetras;

    private Letras jugadorLetras;
    private Cifras jugadorCifras;

    /**
     * Método principal que inicia el juego.
     * 
     * Crea una instancia del juego con 2 pruebas de cifras y 10 pruebas de letras,
     * utilizando jugadores humanos que interactúan a través de la consola.
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        JuegoHumano juegoHumano = new JuegoHumano(2, 10, new LetrasHumano(sc),
                new CifrasHumano(sc));
        juegoHumano.jugar();
        sc.close();
    }

    /**
     * Constructor de la clase JuegoHumano.
     *
     * @param pruebasCifras número de pruebas de cifras a realizar
     * @param pruebasLetras número de pruebas de letras a realizar
     * @param jugadorLetras implementación del jugador que resolverá las pruebas de letras
     * @param jugadorCifras implementación del jugador que resolverá las pruebas de cifras
     */
    public JuegoHumano(int pruebasCifras, int pruebasLetras, Letras jugadorLetras, Cifras jugadorCifras) {
        this.pruebasCifras = pruebasCifras;
        this.pruebasLetras = pruebasLetras;
        this.jugadorLetras = jugadorLetras;
        this.jugadorCifras = jugadorCifras;
        validadorCifras = new ValidadorCifras();
        validadorLetras = new ValidadorLetras("data/es.txt");
    }

    /**
     * Ejecuta el juego completo.
     * 
     * Realiza primero todas las pruebas de letras y luego todas las pruebas de cifras.
     * Para cada prueba, genera los datos necesarios, solicita la respuesta al jugador
     * y valida si la respuesta es correcta.
     * 
     */
    public void jugar() {
        System.out.println("Comenzando juego");
        System.out.println("Pruebas de letras: " + pruebasLetras);
        System.out.println("Pruebas de cifras: " + pruebasCifras);

        jugarPruebasLetras();
        jugarPruebasCifras();

        System.out.println("Juego terminado");
    }

    /**
     * Ejecuta todas las pruebas de letras.
     */
    private void jugarPruebasLetras() {
        System.out.println("Jugando pruebas de letras...");

        for (int i = 0; i < pruebasLetras; i++) {
            String letras = generarPruebaLetrasPorFrecuencias();
            System.out.println("Prueba de letras " + (i + 1) + ": letras disponibles: " + letras);
            
            String palabra = jugadorLetras.obtenerPalabra(letras);
            System.out.println("Palabra obtenida: " + palabra);

            boolean esValida = validadorLetras.esValida(palabra, letras);
            if (esValida) {
                System.out.println("La palabra es válida y tiene longitud " + palabra.length());
            } else {
                System.out.println("La palabra no es válida.");
            }
        }
    }

    /**
     * Ejecuta todas las pruebas de cifras.
     */
    private void jugarPruebasCifras() {
        System.out.println("Jugando pruebas de cifras...");

        for (int i = 0; i < pruebasCifras; i++) {
            int objetivo = generarObjetivoCifras();
            List<Integer> numeros = generarNumerosCifras();
            
            System.out.print("Prueba de cifras " + (i + 1) + ": números disponibles: ");
            System.out.print(numeros);
            System.out.println(", objetivo: " + objetivo);
            
            String expresion = jugadorCifras.obtenerCifra(objetivo, numeros);
            System.out.println("Expresión obtenida: " + expresion);

            boolean esValida = validadorCifras.esValida(expresion, numeros);
            if (esValida) {
                int resultado = validadorCifras.evaluar(expresion);
                System.out.println("La expresión es válida y da el resultado: " + resultado);
                int diferencia = Math.abs(objetivo - resultado);
                System.out.println("Diferencia con el objetivo: " + diferencia);
            } else {
                System.out.println("La expresión no es válida.");
            }
        }
    }


    /**
     * Genera una prueba de letras de forma aleatoria uniforme.
     * 
     * Genera primero 3 vocales aleatorias y luego completa hasta 9 letras
     * con letras aleatorias del alfabeto (incluyendo vocales).
     *
     * @return una cadena con las letras generadas
     */
    public String generarPruebaLetras() {
        String letras = "";
        // Añadir vocales
        String vocales = "aeiou";
        while ( letras.length() < 3 ) {
            char c = vocales.charAt( (int)(Math.random() * 5) );
            letras += c;
        }
        while ( letras.length() < NUM_LETRAS ) {
            char c = (char) ('a' + (int)(Math.random() * 26));
            letras += c;
        }
        return letras;
    }

    /**
     * Genera una prueba de letras basándose en la frecuencia de las letras en español.
     * 
     * Utiliza una cadena ponderada donde las letras más frecuentes en español
     * (como 'a', 'e', 'o') aparecen más veces, aumentando su probabilidad de ser seleccionadas.
     * Esto hace que el juego sea más realista y permite formar palabras más fácilmente.
     *
     * @return una cadena con las letras generadas según frecuencias del español
     */
    public String generarPruebaLetrasPorFrecuencias() {
        String letrasPonderadas = "AAAAAAAAAAAAEEEEEEEEEEEEOOOOOOOOOIIIIIIUUUUSSSSSSSNNNNNNRRRRRRLLLLLDDDDDCCCCTTTTMMMPPPBBGGVYQHFJZXÑ".toLowerCase();

        StringBuilder mano = new StringBuilder();
        Random r = new Random();

        for (int i = 0; i < NUM_LETRAS; i++) {
            int indice = r.nextInt(letrasPonderadas.length());
            mano.append(letrasPonderadas.charAt(indice));
        }

        return mano.toString();
    }

    /**
     * Genera un número objetivo aleatorio para la prueba de cifras.
     * 
     * El objetivo generado está en el rango [101, 999].
     * 
     * @return un número entero entre 101 y 999
     */
    public int generarObjetivoCifras() {
        return Math.abs((int)(Math.random() * 899)) + 101;
    }

    /**
     * Genera una lista de números aleatorios para la prueba de cifras.
     * 
     * Los números posibles son: 1-10, 25, 50, 75, 100. Los números pequeños (1-10)
     * pueden aparecer múltiples veces, pero los números grandes (25, 50, 75, 100)
     * solo pueden aparecer una vez cada uno.
     *
     * @return una lista con los números generados para la prueba
     */
    public List<Integer> generarNumerosCifras() {
        List<Integer> numeros = new ArrayList<>();
        List<Integer> posibles = new ArrayList<>(List.of(1,2,3,4,5,6,7,8,9,10,25,50,75,100));
        for ( int i = 0; i < NUM_CIFRAS; i++ ) {
            int n = posibles.get( (int)(Math.random() * posibles.size()) );
            if ( n>=25 ){
                // Los números grandes solo pueden salir una vez
                posibles.remove( (Integer) n );
            }
            numeros.add(n);
        }
        return numeros;
    }
}
