package es.upm.dit.adsw.cifrasyletras.cifras;

import java.util.List;

public interface Cifras {
    
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
