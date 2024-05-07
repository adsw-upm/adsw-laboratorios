package es.upm.dit.adsw.lab4.grafos;

/**
 * Arco dirigido entre dos nodos, con peso.
 */
public class ArcoDirigido {
    private Integer origen;
    private Integer destino;
    private int peso;

    /**
     * Crea un nuevo arco a partir de los nodos origen y destino y el peso.
     * @param origen
     * @param destino
     * @param peso
     */
    public ArcoDirigido(Integer origen, Integer destino, int peso) {
        this.origen = origen;
        this.destino = destino;
        this.peso = peso;
    }
    
    public ArcoDirigido(Integer origen, Integer destino) {
    	this(origen, destino, 1);
    }

    public Integer getOrigen() {
        return origen;
    }

    public Integer getDestino() {
        return destino;
    }

    public int getPeso() {
        return peso;
    }

    @Override
    public String toString() {
        return origen + " -> " + destino + " [Peso: " + peso + "]";
    }
}