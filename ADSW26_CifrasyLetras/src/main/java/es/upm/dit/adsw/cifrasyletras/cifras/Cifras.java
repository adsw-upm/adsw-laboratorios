package main.java.es.upm.dit.adsw.cifrasyletras.cifras;

import java.util.List;

public interface Cifras {
    
    public static final char[] OPERADORES = new char[] {'+', '-', '*', '/'}; //Sugeriría cambiarlo por una lista. Así pueden usar el contains que agiliza bastante...
    /**
     * Obtener una cifra que se aproxime al objetivo usando los numeros dados
     * El resultado es una expresion en forma de cadena que al evaluarse da la cifra o la mas cercana
     * 900 = 3 + 6 * 100
     * @param objetivo
     * @param numeros
     * @return
     */
    public String obtenerCifra(int objetivo, List<Integer> numeros);
}
