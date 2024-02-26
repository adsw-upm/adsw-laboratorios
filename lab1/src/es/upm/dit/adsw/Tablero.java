package es.upm.dit.adsw;

/**
 * Esta clase representa un tablero de ajedrez. Contiene información sobre las
 * piezas y el estado del tablero.
 */
public class Tablero {

	private String representacion;
	private boolean mate;

	// TODO: en el laboratorio 1 se implementará la representación como array
	// bidimensional
	private Pieza[][] tablero;

	/**
	 * Crea un tablero de ajedrez con las piezas en su posición inicial.
	 * 
	 * @return un objeto Tablero con las piezas en su posición inicial
	 */
	public static Tablero tableroBasico() {
		return new Tablero("rnbqkbnrpppppppp................................PPPPPPPPRNBQKBNR");
	}

	/**
	 * Crea un tablero de ajedrez sin piezas.
	 */
	public Tablero() {
		tablero = new Pieza[8][8];
		representacion = "................................................................";
	}

	/**
	 * Crea un tablero de ajedrez con las piezas en la posición especificada.
	 * 
	 * @param tableroSerializado una cadena de 64 caracteres que representa la
	 *                           posición de las piezas en el tablero
	 */
	public Tablero(String tableroSerializado) {
		this.representacion = tableroSerializado;
		// TODO: se implementará en el laboratorio 1
	}

	/**
	 * Devuelve true si el tablero está en mate.
	 * 
	 * @return true si el tablero está en mate
	 */
	public boolean getMate() {
		return mate;
	}

	/**
	 * Marca el tablero como mate o no mate.
	 * 
	 * @param mate true si el tablero está en mate
	 */
	public void setMate(boolean mate) {
		this.mate = mate;
	}

	/**
	 * Devuelve el array bidimensional que representa el tablero.
	 * @return el array bidimensional que representa el tablero
	 */
	public Pieza[][] getTablero() {
		return this.tablero;
	}

	/**
	 * Devuelve una cadena de texto que representa el tablero.
	 * 
	 * @return una cadena que representa el tablero
	 */
	public String toString() {
		return this.representacion;
	}

	/**
     * Calcula la puntuación del tableo para el bando especificado.
     * @param bando el bando para el que se calcula la puntuación
     * @return la puntuación del tablero para el bando especificado
     */
    public int getPuntuacionBando(Bando bando) {
		// TODO: se implementará en el laboratorio 1
		return 0;
	}


	/**
	 * Indica si el tablero es igual a otro objeto.
	 * 
	 * @param obj el objeto con el que se compara
	 * @return true si el tablero es igual al objeto especificado; false en caso contrario
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj instanceof Tablero) {
			Tablero other = (Tablero) obj;
			return this.representacion.equals(other.representacion);
		}
		return false;
	}
}
