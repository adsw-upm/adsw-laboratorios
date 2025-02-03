package es.upm.dit.adsw.ajedrez25.modelo.grafo;

public class Enlace {
    private Nodo origen, destino;
    private int peso = 1;

    public Enlace(Nodo origen, Nodo destino) {
        this.origen = origen;
        this.destino = destino;
    }

    public int getPeso() {
        return peso;
    }
    public void setPeso(int peso) {
        this.peso = peso;
    }
    public Nodo getOrigen() {
        return origen;
    }
    public Nodo getDestino() {
        return destino;
    }
    public boolean contiene(Nodo nodo) {
        return origen.equals(nodo) || destino.equals(nodo);
    }
    public Nodo getOtro(Nodo nodo) {
        if (origen.equals(nodo)) {
            return destino;
        } else if (destino.equals(nodo)) {
            return origen;
        } else {
            return null;
        }
    }
    public boolean equals(Object o) {
        if (o instanceof Enlace) {
            Enlace e = (Enlace) o;
            return origen.equals(e.origen) && destino.equals(e.destino);
        } else {
            return false;
        }
    }
    public String toString() {
        return origen + " -> " + destino + " (" + peso + ")";
    }
    public void incrementarPeso() {
        peso++;
    }
}
