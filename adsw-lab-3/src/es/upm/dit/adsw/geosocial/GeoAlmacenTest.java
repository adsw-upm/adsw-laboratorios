package es.upm.dit.adsw.geosocial;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.text.ParseException;

import org.junit.jupiter.api.Test;

class GeoAlmacenTest {

	@Test
	void test1usuario() throws FileNotFoundException, ParseException {
		GeoAlmacen gestor = new GeoAlmacen("data/locations1.tsv");
		assertEquals(1, gestor.getNUsuarios());
		assertEquals(20, gestor.getUsuario(0).getNRegistros());
	}
	
	@Test
	void test100() throws FileNotFoundException, ParseException {
		GeoAlmacen gestor = new GeoAlmacen("data/locations100.tsv");
		assertEquals(100, gestor.getNUsuarios());
	}
}
