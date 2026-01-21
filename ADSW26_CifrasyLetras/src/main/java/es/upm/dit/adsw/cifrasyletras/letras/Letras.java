package main.java.es.upm.dit.adsw.cifrasyletras.letras;

public interface Letras {
    /**
     * Obtener la palabra más larga que pueda formarse con ese conjunto de letras.
     * ttrosanco -> contratos
     * @param letras
     * @return palabra más larga encontrada
     */
    public String obtenerPalabra(String letras);

}
