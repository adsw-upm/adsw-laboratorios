package es.upm.dit.adsw.ajedrez25.io;

import es.upm.dit.adsw.ajedrez25.modelo.Partida;
import es.upm.dit.adsw.ajedrez25.modelo.Pieza;
import es.upm.dit.adsw.ajedrez25.modelo.Tablero;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Lector de partidas de ajedrez.
 * Esta clase se encarga de leer registros de partidas desde un archivo y
 * almacenarlos en una lista de objetos {@link Partida}. También procesa información
 * como tableros únicos y jugadores involucrados.
 */
public class LectorPartidas {

    private static final Logger LOGGER = Logger.getLogger(LectorPartidas.class.getName());

    /** Lista de partidas leídas del archivo. */
    private final List<Partida> partidas;

    /** Diccionario que mapea la representación de un tablero con el número de veces que aparece. */
    private final HashMap<String, Integer> tableros;

    /** Conjunto de nombres de jugadores que participaron en las partidas. */
    private final Set<String> jugadores;

    /**
     * Constructor que inicializa el lector de partidas y procesa la información de un archivo dado.
     *
     * @param fichero el nombre del archivo que contiene las partidas
     * @throws Exception si ocurre un error durante la lectura o el procesamiento
     */
    public LectorPartidas(String fichero) throws Exception {
        LOGGER.info("Iniciando la lectura de partidas desde el archivo: " + fichero);
        this.partidas = leerPartidas(fichero);

        tableros = new HashMap<>();
        jugadores = new HashSet<>();
        for (Partida partida : getPartidas()) {
            for (Tablero t : partida.getTurnos()) {
                tableros.put(t.toString(), tableros.getOrDefault(t.toString(), 0) + 1);
            }
            jugadores.add(partida.getJugadorBlancas());
            jugadores.add(partida.getJugadorNegras());
        }
        LOGGER.info("Lectura completada. Número de partidas leídas: " + partidas.size());
    }

    /**
     * Lee partidas desde un archivo y las convierte en objetos {@link Partida}.
     *
     * @param archivoPartidas el nombre del archivo que contiene los registros de partidas
     * @return una lista de objetos {@link Partida}
     * @throws IOException si ocurre un error de entrada/salida al leer el archivo
     * @throws Exception si ocurre un error al procesar los datos del archivo
     */
    public static List<Partida> leerPartidas(String archivoPartidas) throws IOException, Exception {
        LOGGER.info("Leyendo partidas desde el archivo: " + archivoPartidas);
        List<Partida> partidas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivoPartidas))) {
            String linea = br.readLine();
            while (linea != null) {
                try {
                    String url = linea;
                    String jugadorBlancas = br.readLine();
                    String jugadorNegras = br.readLine();
                    int eloBlancas = getElo(br);
                    int eloNegras = getElo(br);
                    List<Tablero> turnos = leerTurnos(br);
                    Partida partida = new Partida(jugadorBlancas, jugadorNegras, eloBlancas, eloNegras, url, turnos);
                    if (validarPartida(partida)) {
                        partidas.add(partida);
                    } else {
                        LOGGER.warning("Partida inválida descartada: " + url);
                    }
                } catch (Exception e) {
                    LOGGER.log(Level.SEVERE, "Error procesando una partida", e);
                }
                linea = br.readLine();
            }
        }
        LOGGER.info("Número total de partidas válidas: " + partidas.size());
        return partidas;
    }

    /**
     * Valida que una partida sea correcta. Una partida es válida si todos los tableros contienen
     * los reyes de ambos bandos.
     *
     * @param partida la partida que se desea validar
     * @return {@code true} si la partida es válida, {@code false} en caso contrario
     */
    public static boolean validarPartida(Partida partida) {
        Pieza reyBlanco = new Pieza('K');
        Pieza reyNegro = new Pieza('k');
        for (Tablero t : partida.getTurnos()) {
            if (!t.contienePieza(reyBlanco) || !t.contienePieza(reyNegro)) {
                LOGGER.warning("Tablero inválido en partida: falta uno o ambos reyes.");
                return false;
            }
        }
        return true;
    }

    /**
     * Lee el ELO de un jugador desde un {@link BufferedReader}.
     * Si no se puede leer el ELO (indicado por "?"), se devuelve 0.
     *
     * @param br el lector de buffer usado para leer el ELO del jugador
     * @return el ELO del jugador o 0 si no está disponible
     * @throws IOException si ocurre un error al leer el ELO
     */
    private static int getElo(BufferedReader br) throws IOException {
        String line = br.readLine();
        if (line.equals("?")) {
            LOGGER.fine("ELO no disponible, asignando 0");
            return 0;
        }
        return Integer.parseInt(line);
    }

    /**
     * Lee los movimientos de una partida (tableros) desde un archivo y los almacena en una lista.
     *
     * @param br el lector de buffer usado para leer los movimientos
     * @return una lista de tableros que representan los movimientos de la partida
     * @throws Exception si ocurre un error al procesar los movimientos
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
        LOGGER.fine("Turnos leídos: " + turnos.size());
        return turnos;
    }

    /**
     * Devuelve la lista de partidas leídas del archivo.
     *
     * @return una lista de objetos {@link Partida}
     */
    public List<Partida> getPartidas() {
        return partidas;
    }

    /**
     * Devuelve un diccionario de tableros y el número de veces que aparece cada uno en las partidas leídas.
     *
     * @return un mapa con tableros como claves y su frecuencia como valores
     */
    public HashMap<String, Integer> getTableros() {
        return tableros;
    }

    /**
     * Devuelve un conjunto de nombres de jugadores que participaron en las partidas leídas.
     *
     * @return un conjunto de nombres de jugadores
     */
    public Set<String> getJugadores() {
        return jugadores;
    }
}
