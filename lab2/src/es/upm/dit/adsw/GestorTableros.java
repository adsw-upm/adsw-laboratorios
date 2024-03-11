package es.upm.dit.adsw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Esta clase representa un buscador de partidas de ajedrez.
 * Contiene métodos para buscar partidas en una lista de objetos Partida según
 * distintos criterios.
 */
public class GestorTableros {

    private List<Partida> partidas;
	private List<Tablero> tableros;
   
	/**
	 * Metodo getter del atributo partidas pata hacer pruebas
	 * @return partidas
	 */
	public List<Partida> getPartidas() {
		return partidas;
	}

	/**
	 * Metodo getter del atributo tableros pata hacer pruebas
	 * @return tableros
	 */
	public List<Tablero> getTableros() {
		return tableros;
	}

    public static void main(String[] args) throws Exception {
        GestorTableros gestor = new GestorTableros(LectorPartidas.leerPartidas("data/partidas.txt"));
    }

    /**
     * Crea un objeto GestorTableros con una lista de partidas.
     * @param partidas una lista de objetos Partida
     */
    public GestorTableros( List<Partida> partidas) {
    	this.partidas=partidas;
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

    /**
     * Devuelve el primer tablero de una lista ordenada por puntuaciones, que tiene una puntuación mayor
     * o igual a la que se pasa como parámetro
     * @paran tablerosOrdenados lista de tableros ordenada por puntuación
     * @param puntuación del tablero que buscamos
     * @return el inidice del de la lista del tablero buscado. Si no may ninguún tablero de puntuación igual 
     * o mayor que la buscada, devuelve el tamaño de la lista 
     */
    public static int busquedaBinaria(List<Tablero> tablerosOrdenados, int puntuacion) {
    	// TODO: implementar en el laboratorio 2
    	return -1;
    }
    
    /**
     * Devuelve la partida en la que está incluido un cierto tablero
     * @param t es el tablero del que queremos conocer la partida
     * @return la partida en l que está incluido el tablero t. Si ese tablero no está en nunguna partida, devuelve null
     */
    public Partida partidaDeTablero(Tablero t) {
    	// TODO: implementar en el laboratorio 2
    	return null;
    }
    
    /**
     * Devuelve la partida del tablero con mayor puntuación general.
     * @return la partida del tablero con mayor puntuación general
     */
    public Partida partidaMayorTablero() {
        // TODO: implementar en el laboratorio 2
        return null;
    }
    
    /**
     * Devuelve una lista con las partidas que tienen tableros con una puntuación igual o
     * superior a la puntuación mínima especificada.
     * @param puntuacion la puntuación mínima
     * @return una lista con los partidas que tienen un tablero con una puntuación igual o
     * superior a la puntuación mínima especificada
     */
    public List<Partida> getPartidasTablerosConPuntuacionMinima(int puntuacion) {
    	 // TODO: implementar en el laboratorio 2
        return null;
    }
}
