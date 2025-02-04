package es.upm.dit.adsw.ajedrez25.modelo;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de pruebas para la clase Partida.
 */
public class PartidaTest {

    /**
     * Prueba para el constructor y los métodos getter.
     */
    @Test
    public void testConstructorYGetters() {
        List<Tablero> turnos = new ArrayList<>();
        turnos.add(new Tablero("rnbqkbnrpppppppp................................PPPPPPPPRNBQKBNR"));
        turnos.add(new Tablero("rnbqkbnrpppp.ppp...............................PPPPPPPPRNBQKBNR"));

        Partida partida = new Partida("JugadorBlancas", "JugadorNegras", 1500, 1450, "https://example.com", turnos);

        assertEquals("JugadorBlancas", partida.getJugadorBlancas(), "El jugador de las piezas blancas debe ser correcto.");
        assertEquals("JugadorNegras", partida.getJugadorNegras(), "El jugador de las piezas negras debe ser correcto.");
        assertEquals(1500, partida.getEloBlancas(), "El ELO de las blancas debe ser correcto.");
        assertEquals(1450, partida.getEloNegras(), "El ELO de las negras debe ser correcto.");
        assertEquals("https://example.com", partida.getUrl(), "La URL de la partida debe ser correcta.");
        assertEquals(turnos, partida.getTurnos(), "La lista de turnos debe ser correcta.");
    }

    /**
     * Prueba para los métodos setter.
     */
    @Test
    public void testSetters() {
        Partida partida = new Partida("Blancas", "Negras", 1500, 1450, "https://example.com", new ArrayList<>());

        partida.setJugadorBlancas("NuevoBlancas");
        assertEquals("NuevoBlancas", partida.getJugadorBlancas(), "El jugador de las piezas blancas debe actualizarse correctamente.");

        partida.setJugadorNegras("NuevoNegras");
        assertEquals("NuevoNegras", partida.getJugadorNegras(), "El jugador de las piezas negras debe actualizarse correctamente.");

        partida.setEloBlancas(1600);
        assertEquals(1600, partida.getEloBlancas(), "El ELO de las blancas debe actualizarse correctamente.");

        partida.setEloNegras(1550);
        assertEquals(1550, partida.getEloNegras(), "El ELO de las negras debe actualizarse correctamente.");

        partida.setUrl("https://newexample.com");
        assertEquals("https://newexample.com", partida.getUrl(), "La URL de la partida debe actualizarse correctamente.");

        List<Tablero> nuevosTurnos = new ArrayList<>();
        nuevosTurnos.add(new Tablero("................................................................"));
        partida.setTurnos(nuevosTurnos);
        assertEquals(nuevosTurnos, partida.getTurnos(), "La lista de turnos debe actualizarse correctamente.");
    }

    /**
     * Prueba para una partida con una lista vacía de turnos.
     */
    @Test
    public void testPartidaConTurnosVacios() {
        Partida partida = new Partida("Blancas", "Negras", 1500, 1450, "https://example.com", new ArrayList<>());

        assertTrue(partida.getTurnos().isEmpty(), "La lista de turnos debe estar vacía.");
    }

    /**
     * Prueba para verificar la cantidad de turnos en una partida.
     */
    @Test
    public void testCantidadDeTurnos() {
        List<Tablero> turnos = new ArrayList<>();
        turnos.add(new Tablero("rnbqkbnrpppppppp................................PPPPPPPPRNBQKBNR"));
        turnos.add(new Tablero("rnbqkbnrpppp.ppp...............................PPPPPPPPRNBQKBNR"));
        turnos.add(new Tablero("rnbqkbnrpppp..pp...............................PPPPPPPPRNBQKBNR"));

        Partida partida = new Partida("Blancas", "Negras", 1500, 1450, "https://example.com", turnos);

        assertEquals(3, partida.getTurnos().size(), "La cantidad de turnos debe ser correcta.");
    }

    /**
     * Prueba para verificar los datos de los jugadores y ELOs.
     */
    @Test
    public void testDatosDeJugadoresYELOs() {
        Partida partida = new Partida("Jugador1", "Jugador2", 2000, 1800, "https://example.com", new ArrayList<>());

        assertEquals("Jugador1", partida.getJugadorBlancas(), "El jugador de las piezas blancas debe ser correcto.");
        assertEquals("Jugador2", partida.getJugadorNegras(), "El jugador de las piezas negras debe ser correcto.");
        assertEquals(2000, partida.getEloBlancas(), "El ELO de las blancas debe ser correcto.");
        assertEquals(1800, partida.getEloNegras(), "El ELO de las negras debe ser correcto.");
    }
}