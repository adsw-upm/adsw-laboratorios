package es.upm.dit.adsw.ajedrez25.modelo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de pruebas exhaustiva para la clase Pieza.
 */
public class PiezaTest {

    /**
     * Prueba para el constructor que recibe tipo y bando.
     */
    @Test
    public void testConstructorConTipoYBando() {
        Pieza pieza = new Pieza(TipoPieza.REY, Bando.BLANCAS);

        assertEquals(TipoPieza.REY, pieza.getTipo(), "El tipo de la pieza debe ser REY.");
        assertEquals(Bando.BLANCAS, pieza.getBando(), "El bando de la pieza debe ser BLANCAS.");
    }

    /**
     * Prueba para el constructor que recibe un carácter.
     */
    @Test
    public void testConstructorConCaracter() {
        Pieza pieza = new Pieza('q');

        assertEquals(TipoPieza.REINA, pieza.getTipo(), "El tipo de la pieza debe ser REINA.");
        assertEquals(Bando.NEGRAS, pieza.getBando(), "El bando de la pieza debe ser NEGRAS.");

        pieza = new Pieza('K');
        assertEquals(TipoPieza.REY, pieza.getTipo(), "El tipo de la pieza debe ser REY.");
        assertEquals(Bando.BLANCAS, pieza.getBando(), "El bando de la pieza debe ser BLANCAS.");
    }

    /**
     * Prueba para caracteres inválidos en el constructor.
     */
    @Test
    public void testConstructorConCaracterInvalido() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Pieza('x'));

        assertEquals("Tipo de pieza desconocido: x", exception.getMessage(), "El mensaje de excepción debe ser adecuado.");
    }

    /**
     * Prueba para el método toString().
     */
    @Test
    public void testToString() {
        Pieza pieza = new Pieza(TipoPieza.CABALLO, Bando.BLANCAS);

        assertEquals("BLANCAS CABALLO", pieza.toString(), "La representación de la pieza debe ser correcta.");
    }

    /**
     * Prueba para el método toChar().
     */
    @Test
    public void testToChar() {
        Pieza pieza = new Pieza(TipoPieza.PEON, Bando.BLANCAS);
        assertEquals('P', pieza.toChar(), "El carácter de la pieza debe ser 'P'.");

        pieza = new Pieza(TipoPieza.PEON, Bando.NEGRAS);
        assertEquals('p', pieza.toChar(), "El carácter de la pieza debe ser 'p'.");

        pieza = new Pieza(TipoPieza.REY, Bando.BLANCAS);
        assertEquals('K', pieza.toChar(), "El carácter de la pieza debe ser 'K'.");

        pieza = new Pieza(TipoPieza.REY, Bando.NEGRAS);
        assertEquals('k', pieza.toChar(), "El carácter de la pieza debe ser 'k'.");
    }

    /**
     * Prueba para el método equals() con piezas iguales.
     */
    @Test
    public void testEqualsConPiezasIguales() {
        Pieza pieza1 = new Pieza(TipoPieza.ALFIL, Bando.BLANCAS);
        Pieza pieza2 = new Pieza(TipoPieza.ALFIL, Bando.BLANCAS);

        assertEquals(pieza1, pieza2, "Las piezas deben ser iguales.");
    }

    /**
     * Prueba para el método equals() con piezas diferentes.
     */
    @Test
    public void testEqualsConPiezasDiferentes() {
        Pieza pieza1 = new Pieza(TipoPieza.TORRE, Bando.BLANCAS);
        Pieza pieza2 = new Pieza(TipoPieza.TORRE, Bando.NEGRAS);

        assertNotEquals(pieza1, pieza2, "Las piezas no deben ser iguales.");
    }

    /**
     * Prueba para el método equals() con un objeto no relacionado.
     */
    @Test
    public void testEqualsConObjetoNoRelacionado() {
        Pieza pieza = new Pieza(TipoPieza.REINA, Bando.BLANCAS);

        assertNotEquals(pieza, "Una cadena", "La comparación con un objeto no relacionado debe ser falsa.");
    }

    /**
     * Prueba para los métodos setTipo() y setBando().
     */
    @Test
    public void testSetters() {
        Pieza pieza = new Pieza(TipoPieza.PEON, Bando.BLANCAS);

        pieza.setTipo(TipoPieza.CABALLO);
        assertEquals(TipoPieza.CABALLO, pieza.getTipo(), "El tipo de la pieza debe ser CABALLO.");

        pieza.setBando(Bando.NEGRAS);
        assertEquals(Bando.NEGRAS, pieza.getBando(), "El bando de la pieza debe ser NEGRAS.");
    }
}