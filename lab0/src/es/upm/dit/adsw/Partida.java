package es.upm.dit.adsw;

import java.util.List;

/**
 * Esta clase representa una partida de ajedrez. Contiene información sobre los
 * jugadores, sus ELOs, la URL de la partida y los distintos tableros.
 */
public class Partida {

	public String jugadorBlancas, jugadorNegras;
	public int eloBlancas, eloNegras;
	public String url;
	public List<Tablero> turnos;

	/**
	 * Crea un objeto Partida con los datos de la partida.
	 * 
	 * @param jugadorBlancas el nombre del jugador que juega con las piezas blancas
	 * @param jugadorNegras  el nombre del jugador que juega con las piezas negras
	 * @param eloBlancas     el ELO del jugador que juega con las piezas blancas
	 * @param eloNegras      el ELO del jugador que juega con las piezas negras
	 * @param url            la URL de la partida
	 * @param turnos         una lista de objetos Tablero que representan los
	 *                       distintos tableros de la partida
	 */
	public Partida(String jugadorBlancas, String jugadorNegras, int eloBlancas, int eloNegras, String url,
			List<Tablero> turnos) {
		this.jugadorBlancas = jugadorBlancas;
		this.jugadorNegras = jugadorNegras;
		this.eloBlancas = eloBlancas;
		this.eloNegras = eloNegras;
		this.url = url;
		this.turnos = turnos;
	}

}
