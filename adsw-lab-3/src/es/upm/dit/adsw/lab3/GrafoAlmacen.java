package es.upm.dit.adsw.lab3;

import java.util.List;
import es.upm.dit.adsw.geosocial.Usuario;

public interface GrafoAlmacen {
	/**
	 * Devuelve una lista con todos los Usuarios amigos alcanzables en n saltos. Si
	 * n<0 devuelve una lista con todos los amigos alcanzables.
	 * (Opcional) Primero contendrá los vecinos en rango 0, luego los vecinos en rango 1, etc.
	 * @param n
	 * @return lista de usuarios amigos sin repetidos.
	 */
	public List<Usuario> getAmigosOrdenN(int user_id, int n);

	/**
	 * Devuelve el número de componentes conexas del grafo.
	 * @return número de componentes conexas
	 */
	public int getComunidades();

}
