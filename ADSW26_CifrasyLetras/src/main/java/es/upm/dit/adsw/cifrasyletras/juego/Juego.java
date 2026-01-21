package main.java.es.upm.dit.adsw.cifrasyletras.juego;

import main.java.es.upm.dit.adsw.cifrasyletras.letras.Letras;
import main.java.es.upm.dit.adsw.cifrasyletras.letras.LetrasHumano;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import main.java.es.upm.dit.adsw.cifrasyletras.cifras.Cifras;
import main.java.es.upm.dit.adsw.cifrasyletras.cifras.CifrasHumano;

public class Juego {

    private final int NUM_LETRAS = 9;
    private final int NUM_CIFRAS = 6;

    private int pruebasCifras, pruebasLetras;

    private ValidadorCifras validadorCifras;
    private ValidadorLetras validadorLetras;

    private Letras jugadorLetras;
    private Cifras jugadorCifras;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Juego juego = new Juego(2, 10, new LetrasHumano(sc),
                new CifrasHumano(sc));
        juego.jugar();
        sc.close();
    }

    public Juego(int pruebasCifras, int pruebasLetras, Letras jugadorLetras, Cifras jugadorCifras) {
        this.pruebasCifras = pruebasCifras;
        this.pruebasLetras = pruebasLetras;
        this.jugadorLetras = jugadorLetras;
        this.jugadorCifras = jugadorCifras;
        validadorCifras = new ValidadorCifras();
        validadorLetras = new ValidadorLetras("ADSW26_CifrasyLetras/data/es.txt");
    }

    public void jugar() {
        System.out.println("Comenzando juego");
        System.out.println("Pruebas de letras: " + pruebasLetras);
        System.out.println("Pruebas de cifras: " + pruebasCifras);

        System.out.println("Jugando pruebas de letras...");

        for ( int i = 0; i < pruebasLetras; i++ ) {
            //String letras = generarPruebaLetras();
            String letras = generarPruebaLetrasPorFrecuencias();
            System.out.println("Prueba de letras " + (i+1) + ": letras disponibles: " + letras);
            String palabra = jugadorLetras.obtenerPalabra(letras);
            System.out.println("Palabra obtenida: " + palabra);

            if ( validadorLetras.esValida(palabra, letras) ) {
                System.out.println("Palabra válida!");
            } else {
                System.out.println("Palabra NO válida!");
            }
        }

        System.out.println("Jugando pruebas de cifras...");

        for( int i = 0; i < pruebasCifras; i++ ) {
            int objetivo = generarObjetivoCifras();
            List<Integer> numeros = generarNumerosCifras();
            System.out.print("Prueba de cifras " + (i+1) + ": números disponibles: ");
            for ( int n : numeros ) {
                System.out.print(n + " ");
            }
            System.out.println(", objetivo: " + objetivo);
            String expresion = jugadorCifras.obtenerCifra(objetivo, numeros);
            System.out.println("Expresión obtenida: " + expresion);


            if ( validadorCifras.esValida(expresion, numeros) ) {
                System.out.println("Números válidos!");
            } else {
                System.out.println("Números NO válidos!");
            }
        }


        System.out.println("Juego terminado");
    }


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

    public int generarObjetivoCifras() {
        return Math.abs((int)(Math.random() * 899)) + 101;
    }

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
