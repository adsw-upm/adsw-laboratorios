package es.upm.dit.adsw.geosocial;

/**
 * Clase principal que representa una localización en el mundo real.
 * 
 * Una localización se caracteriza poner unas coordenadas en el mapa (latitud y longitud) y un identificador del lugar en que está la localización.
 *
 */
public class Localizacion {
	private float latitud;
	private float longitud;
	
	public Localizacion(float latitud, float longitud) {
		super();
		this.latitud = latitud;
		this.longitud = longitud;
	}

	public float getLatitud() {
		return latitud;
	}

	public float getLongitud() {
		return longitud;
	}
	
	public boolean equals(Localizacion loc) {
		return this.latitud == loc.latitud && this.longitud == loc.longitud;
	}
}
