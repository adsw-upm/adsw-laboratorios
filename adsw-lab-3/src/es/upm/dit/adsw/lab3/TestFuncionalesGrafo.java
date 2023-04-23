package es.upm.dit.adsw.lab3;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.jupiter.api.*;

import es.upm.dit.adsw.geosocial.*;

public class TestFuncionalesGrafo {

	private static AlmacenLab3 grafo;
	private static AlmacenLab3 grafo2;
	private static Usuario[] amigosEnGrafo;

	@BeforeAll
	public static void setUp() {
		amigosEnGrafo = new Usuario[10];

		// Grafo de 10 nodos en anillo
		grafo = new AlmacenLab3();
		for( int i = 0; i<10; i++) {
			amigosEnGrafo[i] = ((GeoAlmacen) grafo).getOrCreateUsuario(i+1);
		}
		for(int i = 0; i<9; i++) {
			((GeoAlmacen) grafo).insertarAmistad(i+1, i+2);
		}
		((GeoAlmacen) grafo).insertarAmistad(10, 1);

		try {
			grafo2 = new AlmacenLab3("data/locations100.tsv", "data/connections100.tsv");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Test
	public void testCargarGrafo() {
		try {
			grafo2 = new AlmacenLab3("data/locations100.tsv", "data/connections100.tsv");
		} catch (Exception e) {
			e.printStackTrace();
		} 
		assertNotNull(grafo2);
		assertEquals(100, grafo2.getUsuarios().size());
	}

	@Test
	public void testExistenConstructores() {

		//comprobar que hay al menos 4
		assertTrue(4 <= AlmacenLab3.class.getConstructors().length);

		// comprobar que existen constructores con la misma firma que los de GeoAlmacen
		try {
			// sin parámetros
			assertNotNull(AlmacenLab3.class.getConstructor());

			// con un String
			assertNotNull(AlmacenLab3.class.getConstructor(String.class));

			// con dos String
			assertNotNull(AlmacenLab3.class.getConstructor(String.class, String.class));

			// con un List<ClaveValor>
			assertNotNull(AlmacenLab3.class.getConstructor(List.class));
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testImplementaInterfaz() {
		assertTrue(AlmacenLab3.class.getInterfaces().length == 1);
		assertTrue(AlmacenLab3.class.getInterfaces()[0].equals(GrafoAlmacen.class));
	}

	@Test
	public void testAmigosDeGrado1() {
		List<Usuario> amigos = grafo.getAmigosOrdenN(1, 1);
		assertNotNull(amigos);
		assertEquals(3, amigos.size());

		assertTrue(amigos.contains(amigosEnGrafo[0]));
		assertTrue(amigos.contains(amigosEnGrafo[1]));
		assertTrue(amigos.contains(amigosEnGrafo[9]));
		
		amigos = grafo2.getAmigosOrdenN(83151, 1);
		assertEquals(5, amigos.size());
		assertTrue(amigos.contains(grafo2.getOrCreateUsuario(83151)));
		assertTrue(amigos.contains(grafo2.getOrCreateUsuario(116081)));
		assertTrue(amigos.contains(grafo2.getOrCreateUsuario(162318)));
		assertTrue(amigos.contains(grafo2.getOrCreateUsuario(8042)));
		assertTrue(amigos.contains(grafo2.getOrCreateUsuario(34239)));
	}

	@Test
	public void testAmigosDeGrado2() {
		List<Usuario> amigos = grafo.getAmigosOrdenN(1, 2);
		assertNotNull(amigos);
		assertEquals(5, amigos.size());

		assertTrue(amigos.contains(amigosEnGrafo[0]));
		assertTrue(amigos.contains(amigosEnGrafo[1]));
		assertTrue(amigos.contains(amigosEnGrafo[2]));
		assertTrue(amigos.contains(amigosEnGrafo[9]));
		assertTrue(amigos.contains(amigosEnGrafo[8]));
		

		amigos = grafo2.getAmigosOrdenN(83151, 2);
		assertEquals(10, amigos.size());
		assertTrue(amigos.contains(grafo2.getOrCreateUsuario(83151)));
		assertTrue(amigos.contains(grafo2.getOrCreateUsuario(116081)));
		assertTrue(amigos.contains(grafo2.getOrCreateUsuario(162318)));
		assertTrue(amigos.contains(grafo2.getOrCreateUsuario(8042)));
		assertTrue(amigos.contains(grafo2.getOrCreateUsuario(34239)));

		assertTrue(amigos.contains(grafo2.getOrCreateUsuario(118364)));
		assertTrue(amigos.contains(grafo2.getOrCreateUsuario(70334)));
		assertTrue(amigos.contains(grafo2.getOrCreateUsuario(22828)));
		assertTrue(amigos.contains(grafo2.getOrCreateUsuario(56126)));
		assertTrue(amigos.contains(grafo2.getOrCreateUsuario(1611)));
	}

	@Test
	public void testAmigosDeGrado3() {
		List<Usuario> amigos = grafo.getAmigosOrdenN(1, 3);
		assertNotNull(amigos);
		assertEquals(7, amigos.size());

		assertTrue(amigos.contains(amigosEnGrafo[0]));
		assertTrue(amigos.contains(amigosEnGrafo[1]));
		assertTrue(amigos.contains(amigosEnGrafo[2]));
		assertTrue(amigos.contains(amigosEnGrafo[3]));
		assertTrue(amigos.contains(amigosEnGrafo[9]));
		assertTrue(amigos.contains(amigosEnGrafo[8]));
		assertTrue(amigos.contains(amigosEnGrafo[7]));
		

		assertEquals(13, grafo2.getAmigosOrdenN(83151, 3).size());
	}

	@Test
	public void testAmigosDeGradoNegativo() {
		List<Usuario> amigos = grafo.getAmigosOrdenN(1, -1);
		assertNotNull(amigos);
		assertEquals(10, amigos.size());
		for(int i = 0; i<10; i++) {
			assertTrue(amigos.contains(amigosEnGrafo[i]));
		}
		

		assertEquals(84, grafo2.getAmigosOrdenN(83151, -1).size());
	}



	@Test
	public void testComunidades() {
		int componentes = grafo.getComunidades();
		assertEquals(1, componentes);

		((GeoAlmacen) grafo).getOrCreateUsuario(100);
		componentes = grafo.getComunidades();
		assertEquals(2, componentes);

		((GeoAlmacen) grafo).insertarAmistad(2, 100);
		componentes = grafo.getComunidades();
		assertEquals(1, componentes);
	}

	@Test
	public void testComunidades2() {
		int componentes = grafo2.getComunidades();
		assertEquals(7, componentes);

	}

}
