package es.upm.dit.adsw;

/**
 * Esta clase representa una pieza de ajedrez. Contiene información sobre el
 * tipo de pieza y el bando al que pertenece.
 */
public class Pieza {

	private TipoPieza tipo;
	private Bando bando;

	/**
	 * Crea una pieza de ajedrez con el tipo y el bando especificados.
	 * 
	 * @param tipo  el tipo de pieza
	 * @param bando el bando al que pertenece la pieza
	 */
	public Pieza(TipoPieza tipo, Bando bando) {
		this.tipo = tipo;
		this.bando = bando;
	}

	/**
	 * Crea una pieza de ajedrez a partir de un carácter que representa la pieza. El
	 * carácter debe ser una de las siguientes letras, obtenidas del nombre en
	 * inglés de la pieza: P - peón R - torre N - caballo B - alfil Q - reina K -
	 * rey Si el carácter es una letra minúscula, la pieza pertenece al bando de las
	 * negras.
	 * 
	 * @param c el carácter que representa la pieza
	 */
	public Pieza(char c) {
		this.bando = Character.isLowerCase(c) ? Bando.NEGRAS : Bando.BLANCAS;
		switch (Character.toUpperCase(c)) {
		case 'P':
			this.tipo = TipoPieza.PEON;
			break;
		case 'R':
			this.tipo = TipoPieza.TORRE;
			break;
		case 'N':
			this.tipo = TipoPieza.CABALLO;
			break;
		case 'B':
			this.tipo = TipoPieza.ALFIL;
			break;
		case 'Q':
			this.tipo = TipoPieza.REINA;
			break;
		case 'K':
			this.tipo = TipoPieza.REY;
			break;
		default:
			throw new IllegalArgumentException("Tipo de pieza desconocido: " + c);
		}
	}

	/**
	 * Devuelve el tipo de la pieza.
	 * 
	 * @return el tipo de la pieza
	 */
	public TipoPieza getTipo() {
		return tipo;
	}

	/**
	 * Devuelve el bando al que pertenece la pieza.
	 * 
	 * @return el bando al que pertenece la pieza
	 */
	public Bando getBando() {
		return bando;
	}

	/**
	 * Devuelve una cadena de texto que representa la pieza.
	 * 
	 * @return una cadena que representa la pieza
	 */
	public String toString() {
		return bando + " " + tipo;
	}

	/**
	 * Devuelve el carácter que representa la pieza en el tablero.
	 * 
	 * @return el carácter que representa la pieza
	 */
	public char toChar() {
		char c = ' ';
		switch (tipo) {
		case PEON:
			c = 'P';
			break;
		case TORRE:
			c = 'R';
			break;
		case CABALLO:
			c = 'N';
			break;
		case ALFIL:
			c = 'B';
			break;
		case REINA:
			c = 'Q';
			break;
		case REY:
			c = 'K';
			break;
		}
		return bando == Bando.NEGRAS ? Character.toLowerCase(c) : c;
	}
}
