package es.upm.dit.adsw;

import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase representa un buscador de partidas de ajedrez.
 * Contiene métodos para buscar partidas en una lista de objetos Partida según
 * distintos criterios.
 */
public class GestorTableros {

    private List<Tablero> tableros;

    public static void main(String[] args) throws Exception {
        GestorTableros gestor = new GestorTableros(LectorPartidas.leerPartidasZip("data/partidas_extendido.zip"));
    }

    /**
     * Crea un objeto GestorTableros con una lista de partidas.
     * @param partidas una lista de objetos Partida
     */
    public GestorTableros( List<Partida> partidas) {
        this.tableros = new ArrayList<>();
        for (Partida partida : partidas) {
            tableros.addAll(partida.turnos);
        }
    }

    /**
     * Devuelve el tablero con mayor puntuación general.
     * @return el tablero con mayor puntuación general
     */
    public Tablero mayorTablero() {
        Tablero mayor = tableros.get(0);
        // TODO: implementar en el laboratorio 1
        return mayor;
    }

    /**
     * Devuelve una lista con los tableros que tienen una puntuación igual o
     * superior a la puntuación mínima especificada.
     * @param puntuacion la puntuación mínima
     * @return una lista con los tableros que tienen una puntuación igual o
     * superior a la puntuación mínima especificada
     */
    public List<Tablero> getTablerosConPuntuacionMinima(int puntuacion) {
        List<Tablero> tablerosConPuntuacionMinima = new ArrayList<>();
        // TODO: implementar en el laboratorio 1
        return tablerosConPuntuacionMinima;
    }


}
