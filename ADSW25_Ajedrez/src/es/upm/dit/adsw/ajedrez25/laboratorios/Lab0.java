package es.upm.dit.adsw.ajedrez25.laboratorios;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Lab0 {

    private static final Logger LOGGER = Logger.getLogger(Lab0.class.getName());
    private List<PartidaLab0> partidas;

    public static void main(String[] args) throws Exception {
        String rutaArchivo = "data/partidas.txt";
        LOGGER.info("Leyendo partidas desde el archivo: " + rutaArchivo);
        Lab0 lab0 = new Lab0(rutaArchivo);
        LOGGER.info("Número de partidas leídas: " + lab0.getPartidas().size());
    }


    public Lab0(String ruta) {
        try {
            this.partidas = leerPartidas(ruta);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<PartidaLab0> leerPartidas(String archivoPartidas) throws IOException, Exception {
        LOGGER.info("Leyendo partidas desde el archivo: " + archivoPartidas);
        List<PartidaLab0> partidas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivoPartidas))) {
            String linea = br.readLine();
            while (linea != null) {
                try {
                    String url = linea;
                    String jugadorBlancas = br.readLine();
                    String jugadorNegras = br.readLine();
                    int eloBlancas = getElo(br);
                    int eloNegras = getElo(br);
                    List<String> turnos = leerTurnos(br);
                    PartidaLab0 partida = new PartidaLab0(jugadorBlancas, jugadorNegras, eloBlancas, eloNegras, url, turnos);
                    partidas.add(partida);
                    
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
     * Lee el ELO de un jugador desde un {@link BufferedReader}.
     * Si no se puede leer el ELO (indicado por "?"), se devuelve 0.
     *
     * @param br el lector de buffer usado para leer el ELO del jugador
     * @return el ELO del jugador o 0 si no está disponible
     * @throws IOException si ocurre un error al leer el ELO
     */
    private int getElo(BufferedReader br) throws IOException {
        String line = br.readLine();
        if (line.equals("?")) {
            LOGGER.info("ELO no disponible, asignando 0");
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
    private List<String> leerTurnos(BufferedReader br) throws Exception {
        List<String> turnos = new ArrayList<>();
        String linea;
        while (true) {
            linea = br.readLine();
            if (linea == null || linea.isEmpty()) {
                break;
            }
            turnos.add(linea);
        }
        LOGGER.fine("Turnos leídos: " + turnos.size());
        return turnos;
    }

    /**
     * Devuelve la lista de partidas leídas del archivo.
     *
     * @return una lista de objetos {@link PartidaLab0}
     */
    public List<PartidaLab0> getPartidas() {
        return partidas;
    }

    /**
     * Versión simplificada de la clase Partida que solo usaremos en este laboratorio
     */
    class PartidaLab0 {
        private String jugadorBlancas, jugadorNegras;
        private int eloBlancas, eloNegras;
        private String url;
        private List<String> turnos;
        public PartidaLab0(String jugadorBlancas, String jugadorNegras, int eloBlancas, int eloNegras, String url,
                       List<String> turnos) {
            this.jugadorBlancas = jugadorBlancas;
            this.jugadorNegras = jugadorNegras;
            this.eloBlancas = eloBlancas;
            this.eloNegras = eloNegras;
            this.url = url;
            this.turnos = turnos;
        }
        public List<String> getTurnos() {
            return turnos;
        }
    }
}
