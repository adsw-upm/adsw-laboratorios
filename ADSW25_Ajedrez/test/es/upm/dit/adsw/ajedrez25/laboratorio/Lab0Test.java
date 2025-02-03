package es.upm.dit.adsw.ajedrez25.laboratorio;

import es.upm.dit.adsw.ajedrez25.laboratorios.Lab0;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

class Lab0Test {

    private final String ruta = "data/muestra.txt";

    @Test
    void testArchivoExiste() {
        File file = new File(ruta);
        assertTrue(file.exists());
    }

    @Test
    void testConstructor() {
        try{
            new Lab0(ruta);
        } catch (Exception e) {
            fail("Error en la creación del objeto Lab0");
        }
    }
    
    @Test
    void testLectura() {
        try{
            Lab0 lab0 = new Lab0(ruta);
            assertEquals(4, lab0.getPartidas().size());
        } catch (Exception e) {
            fail("Error en la lectura de partidas");
        }
    }
}