package es.upm.dit.adsw.lab3.ejemplo;

import java.util.ArrayList;
import java.util.List;

public class Grafo {
	private List<Nodo> nodos = new ArrayList<Nodo>();

	public static void main(String[] args) {
		Grafo grafo = new Grafo();

		// crea un grafo con 10 nodos y conexiones entre ellos
		grafo.crearGrafo();

		// imprime el grafo
		grafo.imprimirGrafo();

		// imprime los vecinos del nodo 0 en orden 1
		System.out.println("Vecinos del nodo 0 en orden 1: " + grafo.getVecinosOrdenN(grafo.nodos.get(0),1));

		// imprime los vecinos del nodo 0 en orden 2
		System.out.println("Vecinos del nodo 0 en orden 2: " + grafo.getVecinosOrdenN(grafo.nodos.get(0),2));

		// imprime los vecinos del nodo 0 en orden -1
		System.out.println("Vecinos del nodo 0 en orden -1: " + grafo.getVecinosOrdenN(grafo.nodos.get(0),-1));

		// imprime las componentes conexas del grafo
		System.out.println("Componentes conexas: " + grafo.componentesConexas());
	}

	public void crearGrafo() {
		// crea los nodos
		for (int i = 0; i < 10; i++) {
			nodos.add(new Nodo("Nodo " + i));
		}

		// crea las conexiones entre los nodos
		nodos.get(0).addVecino(nodos.get(1));
		nodos.get(0).addVecino(nodos.get(2));
		nodos.get(0).addVecino(nodos.get(3));
		nodos.get(1).addVecino(nodos.get(4));
		nodos.get(1).addVecino(nodos.get(5));
		nodos.get(2).addVecino(nodos.get(6));
		nodos.get(2).addVecino(nodos.get(7));
		nodos.get(3).addVecino(nodos.get(8));
		nodos.get(3).addVecino(nodos.get(9));

		// añade las conexiones en el otro sentido
		for (Nodo nodo : nodos) {
			for (Nodo vecino : nodo.getVecinos()) {
				if (!vecino.getVecinos().contains(nodo)) vecino.addVecino(nodo);
			}
		}

	}

	/**
	 * Imprime el grafo por consola
	 */
	public void imprimirGrafo() {
		for (Nodo nodo : nodos) {
			System.out.println(nodo.getId() + " -> " + nodo.getVecinos());
		}
	}

	/**
	 * Devuelve el número de componentes conexas del grafo.
	 * @return número de componentes conexas
	 */
	public int componentesConexas(){
		return 0;
	}

	/**
	 * Devuelve una lista con todos los nodos vecinos alcanzables en n saltos. Si
	 * n<0 devuelve una lista con todos los nodos alcanzables.
	 * (Opcional) Primero contendrá los vecinos en rango 0, luego los vecinos en rango 1, etc.
	 * @param n
	 * @return lista de nodos vecinos sin repetidos. 
	 */
	public List<Nodo> getVecinosOrdenN(Nodo nodo, int n) {
		return null;
	}

}