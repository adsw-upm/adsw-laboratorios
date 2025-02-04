package es.upm.dit.adsw.ajedrez25.laboratorios;

import es.upm.dit.adsw.ajedrez25.io.LectorPartidas;
import es.upm.dit.adsw.ajedrez25.modelo.Partida;
import es.upm.dit.adsw.ajedrez25.modelo.Tablero;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Clase Lab1 que gestiona una colección de tableros de ajedrez extraídos de las partidas.
 * Proporciona métodos para buscar el tablero con mayor puntuación y filtrar tableros
 * con puntuaciones superiores a un valor mínimo.
 */
public class Lab1 {

    /**
     * Lista de tableros obtenidos de las partidas.
     */
    private final List<Tablero> tableros;

    private static final Logger LOGGER = Logger.getLogger(Lab1.class.getName());

    public static void main(String[] args) {
        long t = System.currentTimeMillis();
        LectorPartidas lector = getLectorPartidas();
        LOGGER.info("Inicializando Laboratorio 1");
        Lab1 lab = new Lab1(lector);
        LOGGER.info("Laboratorio 1 creado");
        //LOGGER.info("El tablero con mayor puntuación es el siguiente :");
        //LOGGER.info(lab.mayorTablero().toString());
        //LOGGER.info("Con una puntuación de " + lab.mayorTablero().getPuntuacionGeneral());
        //LOGGER.info("Hay "+ lab.getTablerosConPuntuacionMinima(10).size() + " tableros que tienen una puntuación de al menos 10");
        LOGGER.info("Tiempo de análisis: " + (System.currentTimeMillis() - t) + " ms");
    }

    private static LectorPartidas getLectorPartidas() {

        long t = System.currentTimeMillis();
        try {
            LectorPartidas lector = new LectorPartidas("data/partidas.txt");
            LOGGER.info("Tiempo de lectura: " + (System.currentTimeMillis() - t) + " ms");
            LOGGER.info("Partidas leídas: " + lector.getPartidas().size());
            LOGGER.info("Tableros leídos: " + lector.getTableros().size());
            LOGGER.info("Jugadores leídos: " + lector.getJugadores().size());
            return lector;
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
            return null;
        }
    }


    /**
     * Constructor de la clase Lab1.
     * Inicializa la lista de tableros a partir de las partidas proporcionadas por el lector.
     *
     * @param lectorPartidas Objeto LectorPartidas que contiene las partidas a procesar.
     */
    public Lab1(LectorPartidas lectorPartidas) {
        tableros = new ArrayList<>();
        for (Partida p : lectorPartidas.getPartidas()) {
            tableros.addAll(p.getTurnos());
        }
    }
}
