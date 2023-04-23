package es.upm.dit.adsw.lab3.ejemplo;

import java.util.ArrayList;
import java.util.List;

public class Nodo {
	private String id;
	private List<Nodo> vecinos;

	public Nodo(String id) {
		this.id = id;
		this.vecinos = new ArrayList<Nodo>();
	}

	/**
	 * Añade un vecino a la lista de vecinos
	 */
	public void addVecino(Nodo vecino) {
		this.vecinos.add(vecino);
	}

	public String getId() {
		return this.id;
	}

	public List<Nodo> getVecinos() {
		return this.vecinos;
	}

	public String toString() {
		return this.id;
	}

}
