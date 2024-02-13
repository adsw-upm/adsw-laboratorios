package es.upm.dit.adsw.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;

import es.upm.dit.adsw.*;
import org.junit.jupiter.api.Test;

class Lab0Test {

	@Test
	void testMuestra() throws IOException, Exception {
		LectorPartidas lector = new LectorPartidas("data/muestra.txt");
		assertEquals(4, lector.getPartidas().size());
	}

	@Test
	void testMuestraJugadores() throws IOException, Exception {
		LectorPartidas lector = new LectorPartidas("data/muestra.txt");
		assertEquals(8, lector.getJugadores().size());
	}

	@Test
	void testMuestraTableros() throws IOException, Exception {
		LectorPartidas lector = new LectorPartidas("data/muestra.txt");
		assertEquals(235, lector.getTableros().size());
	}
}
