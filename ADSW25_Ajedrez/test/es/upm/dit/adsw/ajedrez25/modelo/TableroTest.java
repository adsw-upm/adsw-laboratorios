package es.upm.dit.adsw.ajedrez25.modelo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de pruebas exhaustiva para la clase Tablero.
 */
public class TableroTest {

    /**
     * Prueba para el constructor vacío de Tablero.
     */
    @Test
    public void testConstructorVacio() {
        Tablero tablero = new Tablero();
        Pieza[][] matriz = tablero.getMatrizPiezas();

        for (int fila = 0; fila < 8; fila++) {
            for (int columna = 0; columna < 8; columna++) {
                assertNull(matriz[fila][columna], "La posición debe estar vacía.");
            }
        }
    }

    /**
     * Prueba para el constructor con un tablero serializado.
     */
    @Test
    public void testConstructorSerializado() {
        String tableroSerializado = "rnbqkbnrpppppppp................................PPPPPPPPRNBQKBNR";
        Tablero tablero = new Tablero(tableroSerializado);

        assertEquals(tableroSerializado, tablero.toString(), "El tablero serializado no coincide con la representación del tablero.");
    }

    /**
     * Prueba para el método tableroBasico().
     */
    @Test
    public void testTableroBasico() {
        Tablero tablero = Tablero.tableroBasico();
        String representacionEsperada = "rnbqkbnrpppppppp................................PPPPPPPPRNBQKBNR";

        assertEquals(representacionEsperada, tablero.toString(), "El tablero básico no coincide con la representación esperada.");
    }

    /**
     * Prueba para el método equals() con tableros iguales.
     */
    @Test
    public void testEqualsConTablerosIguales() {
        Tablero tablero1 = new Tablero("rnbqkbnrpppppppp................................PPPPPPPPRNBQKBNR");
        Tablero tablero2 = new Tablero("rnbqkbnrpppppppp................................PPPPPPPPRNBQKBNR");

        assertEquals(tablero1, tablero2, "Los tableros deben ser iguales.");
    }

    /**
     * Prueba para el método equals() con tableros diferentes.
     */
    @Test
    public void testEqualsConTablerosDiferentes() {
        Tablero tablero1 = new Tablero("rnbqkbnrpppppppp................................PPPPPPPPRNBQKBNR");
        Tablero tablero2 = new Tablero("rnbqkbnrpppppppp................................PPPPPPPPPPPPPPPP");

        assertNotEquals(tablero1, tablero2, "Los tableros no deben ser iguales.");
    }

    /**
     * Prueba para el método contienePieza().
     */
    @Test
    public void testContienePieza() {
        Tablero tablero = new Tablero("rnbqkbnrpppppppp................................PPPPPPPPRNBQKBNR");
        Pieza pieza = new Pieza('K');

        assertTrue(tablero.contienePieza(pieza), "El tablero debe contener la pieza indicada.");
    }

    /**
     * Prueba para el método contienePieza() con una pieza no presente.
     */
    @Test
    public void testContienePiezaNoPresente() {
        Tablero tablero = new Tablero("rnbq.bnrpppppppp................................PPPPPPPPRNBQKBNR");
        Pieza pieza = new Pieza('k');

        assertFalse(tablero.contienePieza(pieza), "El tablero no debe contener la pieza indicada.");
    }
}
