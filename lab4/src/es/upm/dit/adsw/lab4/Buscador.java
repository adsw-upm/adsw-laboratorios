package es.upm.dit.adsw.lab4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import es.upm.dit.adsw.lab4.grafos.ArcoDirigido;
import es.upm.dit.adsw.lab4.grafos.GrafoDirigido;

/**
 * Buscador básico que implementa la búsqueda de manera secuencial.
 */
public class Buscador {
	
	protected Map<Integer, Map<Integer, Integer>> distancias;
	protected GrafoDirigido g;
	
	public Buscador() {
		this.distancias = new HashMap<Integer, Map<Integer, Integer>>();
	}
	
	/**
	 * Inicializa el buscador calculando las distancias entre todos los pares de nodos en el grafo dado.
	 * @param g
	 */
	public Buscador(GrafoDirigido g) {
		this();
		this.g = g;
		for(Integer n1: g.getNodos()) {
			Map<Integer, Integer> m = new HashMap<>();
			for(Integer n2: g.getNodos()) {
				if(n1 == n2) {
					m.put(n2, 0);
				}
				m.put(n2, calcularDistancia(g, n1, n2));				
			}
			distancias.put(n1, m);
		}
	}
	
	/**
	 * Devuelve la distancia pre-calculada entre el nodo origen y el nodo destino.
	 */
	public Integer getDistancia(int origen, int destino) {
		Map<Integer, Integer> desdeOrigen = this.distancias.get(origen);
		if(desdeOrigen == null) {
			return null;
		}
		return desdeOrigen.getOrDefault(destino, null);
	}
	
	/**
	 * Devuelve la distancia calculada entre origen y destino en el grafo usado en el constructor
	 * @param origen
	 * @param destino
	 * @return Número de saltos del camino mínimo entre origen y destino.
	 */
	public Integer calcularDistancia(Integer origen, Integer destino) {
		return calcularDistancia(this.g, origen, destino);
	}
	
	/**
	 * Calcula la distancia mínima (en número de arcos) entre dos nodos en un grafo dado.
	 * @param g Grafo dirigido
	 * @param origen Origen del camino mínimo
	 * @param destino Destino del camino mínimo
	 * @return Número de arcos del camino mínimo.
	 */
	public static Integer calcularDistancia(GrafoDirigido g, Integer origen, Integer destino) {
		Map<Integer, Integer> distancias = new HashMap<>();
		ArrayList<ArcoDirigido> pendientes = new ArrayList<>();
		
		distancias.put(origen, 0);
		for(ArcoDirigido arco: g.adyacentes(origen)) {
			pendientes.add(arco);
		}

		while(!pendientes.isEmpty()) {
			ArcoDirigido arco = pendientes.remove(0);
			if(distancias.containsKey(arco.getDestino())) {
				continue;
			}
			distancias.put(arco.getDestino(), distancias.get(arco.getOrigen()) + 1);
			if(destino == arco.getDestino()) {
				break;
			}
			for(ArcoDirigido saliente: g.adyacentes(arco.getDestino())) {
				pendientes.add(saliente);
			}
		}
		return distancias.get(destino);
	}


}
