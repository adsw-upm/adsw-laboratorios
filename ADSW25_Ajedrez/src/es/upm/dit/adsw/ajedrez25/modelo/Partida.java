package es.upm.dit.adsw.ajedrez25.modelo;

import java.util.List;

/**
 * Esta clase representa una partida de ajedrez. Contiene información sobre los
 * jugadores, sus ELOs, la URL de la partida y los distintos tableros.
 */
public class Partida {

	private String jugadorBlancas, jugadorNegras;
	private int eloBlancas, eloNegras;
	private String url;
	private List<Tablero> turnos;

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

	public String getJugadorBlancas() {
		return jugadorBlancas;
	}

	public void setJugadorBlancas(String jugadorBlancas) {
		this.jugadorBlancas = jugadorBlancas;
	}

	public String getJugadorNegras() {
		return jugadorNegras;
	}

	public void setJugadorNegras(String jugadorNegras) {
		this.jugadorNegras = jugadorNegras;
	}

	public int getEloBlancas() {
		return eloBlancas;
	}

	public void setEloBlancas(int eloBlancas) {
		this.eloBlancas = eloBlancas;
	}

	public int getEloNegras() {
		return eloNegras;
	}

	public void setEloNegras(int eloNegras) {
		this.eloNegras = eloNegras;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<Tablero> getTurnos() {
		return turnos;
	}

	public void setTurnos(List<Tablero> turnos) {
		this.turnos = turnos;
	}

}
