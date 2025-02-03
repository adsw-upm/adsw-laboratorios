package es.upm.dit.adsw.ajedrez25.modelo;

/**
 * Esta clase representa un tablero de ajedrez. Contiene información sobre las
 * piezas y el estado del tablero.
 */
public class Tablero {

    private Pieza[][] matrizPiezas; // Matriz que representa las piezas en el tablero.
	private boolean mate = false; // Indica si el tablero está en situación de jaque mate.

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
        matrizPiezas = new Pieza[8][8];
    }

    /**
     * Crea un tablero de ajedrez con las piezas en la posición especificada.
     * 
     * @param tableroSerializado una cadena de 64 caracteres que representa la
     *                           posición de las piezas en el tablero
     */
    public Tablero(String tableroSerializado) {
        // Completar en laboratorio 1
    }

    /**
     * Devuelve el array bidimensional que representa el tablero.
     * 
     * @return el array bidimensional que representa el tablero
     */
    public Pieza[][] getMatrizPiezas() {
        return this.matrizPiezas;
    }

    /**
     * Devuelve una cadena de texto que representa el tablero.
     * 
     * @return una cadena que representa el tablero
     */
    public String toString() {
        StringBuilder representacion = new StringBuilder();
        for (int fila = 0; fila < 8; fila++) {
            for (int columna = 0; columna < 8; columna++) {
                Pieza pieza = matrizPiezas[fila][columna];
                if (pieza != null) {
                    representacion.append(pieza.toChar());
                } else {
                    representacion.append('.');
                }
            }
        }
        return representacion.toString();
    }


    /**
     * Verifica si el tablero contiene una pieza específica.
     * 
     * @param pieza la pieza que se busca
     * @return true si la pieza está presente en el tablero, false en caso contrario
     */
    public boolean contienePieza(Pieza pieza) {
        for (int fila = 0; fila < matrizPiezas.length; fila++) {
            for (int columna = 0; columna < matrizPiezas[fila].length; columna++) {
                if (pieza.equals(matrizPiezas[fila][columna])) {
                    return true;
                }
            }
        }
        return false;
    }

	public boolean getMate() {
		return mate;
	}

	public void setMate(boolean mate) {
		this.mate = mate;
	}
}
