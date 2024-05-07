package es.upm.dit.adsw.lab4;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import es.upm.dit.adsw.lab4.grafos.GrafoDirigido;

public class TestBuscador {
	protected static GrafoDirigido g;
	protected static final int profundidad = 5;
	protected static final int[] primos = new int[] {2, 3, 5};


    @BeforeAll
    public static void initAll() {
		g = grafoEjemplo(profundidad);
    }
    
    /**
     * Genera un árbol sintético para las pruebas.
     * @param profundidad del árbol.
     * @return Árbol sintético con la profundidad proporcionada.
     */
    public static GrafoDirigido grafoEjemplo(int profundidad) {
	    g = new GrafoDirigido();
		List<Integer> ultima = new ArrayList<>();
		List<Integer> siguiente = new ArrayList<>();
		ultima.add(1);
		for(int veces=0; veces<profundidad; veces++) {
			siguiente.clear();
	    	for(int primo: primos) {
	    		for(int n1: ultima) {
	    			int n2 = primo * n1;
	    			g.insertarArco(n1, n2);
	    			siguiente.add(n2);
	    		}
	    	}
	    	List<Integer> t = ultima;
	    	ultima = siguiente;
	    	siguiente = t;
		}
		return g;
    }
    
	/**
	 * Lanza un smoketest sobre un grafo ya creado. Se asume que el grafo creado
	 * es el de ejemplo. Para otro grafo, las comprobaciones no funcionarían.
	 * @param buscador
	 * @param g Grafo de ejemplo, de nivel superior a 4.
	 */
	public void testGrafoEjemplo(Buscador buscador) {	
		for(int i=0; i<10; i++) {
			List<Integer> ultima = new ArrayList<>();
			List<Integer> siguiente = new ArrayList<>();
			ultima.add(1);
			for(int veces=1; veces<=profundidad; veces++) {
				siguiente.clear();
		    	for(int primo: primos) {
		    		for(int n1: ultima) {
		    			int n2 = primo * n1;
		    			assertEquals(1, buscador.getDistancia(n1, n2));
		    			assertEquals(veces, buscador.getDistancia(1, n2));
		    			siguiente.add(n2);
		    		}
		    	}
		    	List<Integer> t = ultima;
		    	ultima = siguiente;
		    	siguiente = t;
			}

			assertEquals(null, buscador.getDistancia(2, 3));
		}
	}
	
	/**
	 * Comprueba la implementación secuencial con el grafo de ejemplo.
	 */
	@Test
	public void testSecuencial() {
		testGrafoEjemplo(new Buscador(g));
	}
	
}
