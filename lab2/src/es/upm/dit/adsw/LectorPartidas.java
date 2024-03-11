package es.upm.dit.adsw;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.zip.ZipFile;

/**
 * Esta clase representa un lector de partidas de ajedrez. Lee registros de
 * partidas de un archivo y los almacena en una lista de objetos Partida.
 */
public class LectorPartidas {
	private List<Partida> partidas;
	private HashMap<String, Integer> tableros;
	private Set<String> jugadores;

	public LectorPartidas(String fichero) throws Exception {
		this.partidas = leerPartidas(fichero);

		tableros = new HashMap<>();
		jugadores = new HashSet<>();
		for (Partida partida : getPartidas()) {
			for (Tablero t : partida.turnos) {
				tableros.put(t.toString(), tableros.getOrDefault(t.toString(), 0) + 1);
			}
			jugadores.add(partida.jugadorBlancas);
			jugadores.add(partida.jugadorNegras);
		}
	}

	/**
	 * Lee registros de partidas de un archivo y muestra los detalles de cada
	 * partida.
	 * 
	 * @param args se ignora
	 * @throws RuntimeException si ocurre un error al leer el archivo de partidas
	 */
	public static void main(String[] args) throws Exception {
		String archivoPartidas = "data/muestra.txt";
		LectorPartidas lector = new LectorPartidas(archivoPartidas);
		System.out.println("Partidas leídas: " + lector.getPartidas().size());
		System.out.println("Tableros leídos: " + lector.tableros.size());
		System.out.println("Jugadores leídos: " + lector.jugadores.size());
	}

	/**
	 * Lee registros de partidas de un archivo y los almacena en una lista de
	 * objetos Partida.
	 * 
	 * @param archivoPartidas el nombre del archivo que contiene los registros de
	 *                        partidas
	 * @return una lista de objetos Partida
	 * @throws Exception
	 * @throws IOException
	 */
	public static List<Partida> leerPartidas(String archivoPartidas) throws IOException, Exception {
		List<Partida> partidas = new ArrayList<>();
		BufferedReader br = new BufferedReader(new FileReader(archivoPartidas));
		String linea = br.readLine();
		int numero = 0;
		while (linea != null) {
			numero++;
			String url = linea;
			String jugadorBlancas = br.readLine();
			String jugadorNegras = br.readLine();
			int eloBlancas = getElo(br);
			int eloNegras = getElo(br);
			List<Tablero> turnos = leerTurnos(br);
			partidas.add(new Partida(jugadorBlancas, jugadorNegras, eloBlancas, eloNegras, url, turnos));
			linea = br.readLine();
		}
		System.out.println("Leídas " + numero + " líneas.");
		return partidas;
	}

	/**
     * Lee registros de partidas de un archivo comprimido y los almacena en una
     * lista de objetos Partida.
     * 
     * @param archivoPartidas el nombre del archivo comprimido que contiene los
     *                        registros de partidas
     * @return una lista de objetos Partida
     */
    public static List<Partida> leerPartidasZip(String archivoPartidas) throws Exception {
        ZipFile zipFile = new ZipFile(archivoPartidas);
        List<Partida> partidas = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(zipFile.getInputStream(zipFile.entries().nextElement())));
        String linea;
        while ((linea = br.readLine()) != null) {
            String url = linea;
            String jugadorBlancas = br.readLine();
            String jugadorNegras = br.readLine();
            int eloBlancas = getElo(br);
            int eloNegras = getElo(br);
            List<Tablero> turnos = leerTurnos(br);
            partidas.add(new Partida(jugadorBlancas, jugadorNegras, eloBlancas, eloNegras, url, turnos));
        }
        br.close();
        zipFile.close();
        return partidas;
    }


	/**
	 * Lee el ELO de un jugador de ajedrez. Si no se puede leer el ELO, se devuelve
	 * 0.
	 * 
	 * @param br el BufferedReader usado para leer el ELO del jugador
	 * @return el ELO del jugador
	 */
	private static int getElo(BufferedReader br) throws IOException {
		String line = br.readLine();
		if (line.equals("?")) {
			return 0;
		}
		return Integer.parseInt(line);
	}

	/**
	 * Lee los movimientos de una partida de ajedrez (los distintos tableros) y los
	 * almacena en una lista de objetos Tablero. El último tablero de la lista
	 * indica que la partida ha terminado y se marca como mate.
	 * 
	 * @param br el BufferedReader usado para leer los movimientos de la partida
	 * @return una lista de objetos Tablero
	 * @throws Exception si ocurre un error al leer los movimientos de la partida
	 */
	private static List<Tablero> leerTurnos(BufferedReader br) throws Exception {
		List<Tablero> turnos = new ArrayList<>();
		turnos.add(Tablero.tableroBasico());
		String linea;
		while (true) {
			linea = br.readLine();
			if (linea == null || linea.isEmpty()) {
				break;
			}
			Tablero tablero = new Tablero(linea);
			turnos.add(tablero);
		}
		turnos.get(turnos.size() - 1).setMate(true);
		return turnos;
	}

	/**
	 * Devuelve la lista de partidas leídas.
	 * 
	 * @return Lista de partidas leídas.
	 */
	public List<Partida> getPartidas() {
		return partidas;
	}

	/**
	 * Devuelve el diccionario de tableros leídos a número de veces que aparece ese
	 * tablero en las partidas leídas.
	 * 
	 * @return Diccionario de tableros leídos.
	 */
	public HashMap<String, Integer> getTableros() {
		return tableros;
	}

	/**
	 * Devuelve el conjunto de jugadores de las partidas leídas.
	 * 
	 * @return Conjunto de jugadores.
	 */
	public Set<String> getJugadores() {
		return jugadores;
	}

}
