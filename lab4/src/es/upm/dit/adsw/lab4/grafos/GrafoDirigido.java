package es.upm.dit.adsw.lab4.grafos;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GrafoDirigido {
	// protected para que se pueda utilizar en las subclases.
    protected Map<Integer, Set<ArcoDirigido>> enlaces = new HashMap<>();

    /**
     * Introduce un nodo nuevo en el grafo. El nodo podría no tener arcos asociados.
     * @param nodo
     */
    public void insertarNodo(Integer nodo) {
        if(!enlaces.containsKey(nodo)) {
        	enlaces.put(nodo, new HashSet<>());
        }
    }

    /**
     * Introduce un nuevo arco dirigido en el grafo.
     * @param arco
     */
    public void insertarArco(ArcoDirigido arco) {
        this.insertarNodo(arco.getOrigen());
        this.insertarNodo(arco.getDestino());
        this.enlaces.get(arco.getOrigen()).add(arco);
    }

    /**
     * Inserta un nuevo arco dirigido en el grafo, creado a partir de un nodo origen y otro destino.
     * @param origen
     * @param destino
     */
    public void insertarArco(Integer origen, Integer destino) {
    	ArcoDirigido link = new ArcoDirigido(origen, destino);
        this.insertarArco(link);
    }
    
    /**
     * Devuelve el conjunto de nodos que componen el grafo.
     * @return Nodos en el grafo
     */
    public Set<Integer> getNodos() {
    	return this.enlaces.keySet();
    }
    
    /**
     * Devuelve los arcos dirigidos que parten del nodo especificado
     * @param node
     * @return Conjunto de arcos cuyo origen es el nodo dado.
     */
    public Set<ArcoDirigido> adyacentes(Integer origen) {
    	return this.enlaces.get(origen);
    }

}