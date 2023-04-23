package es.upm.dit.adsw.geosocial;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Un registro representa la posición de un usuario en un momento determinado. 
 *
 */
public class Registro {
	private int id_usuario;
	private LocalDateTime fecha;
	private Localizacion loc;

	public Registro(int id_usuario, Localizacion loc) {
		this(id_usuario, LocalDateTime.now(), loc);
	}
	
	public Registro(int id_usuario, LocalDateTime fecha2, Localizacion loc) {
		super();
		this.id_usuario = id_usuario;
		this.fecha = fecha2;
		this.loc = loc;
	}
	
	public int getIdUsuario() {
		return id_usuario;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public Localizacion getLoc() {
		return loc;
	}
	
	/**
	 * Calcula si un registro coincide con otro registro que se pasa como argumento.
	 * Dos registros coinciden cuando tienen la misma localización y se han realizado en un tiempo parecido (ver método "esSimultaneo")
	 * 
	 * @param otro registro a comparar
	 * @return true si se cumplen las condiciones para tiempo y localización
	 */
	public boolean coincide(Registro otro) {
		return this.esSimultaneo(otro) && this.loc.equals(otro.loc);
	}
	

	/**
	 * Calcula si dos registros coinciden en el tiempo, con un margen en el tiempo determinado.
	 * @param otro registro a comparar
	 * @param margen Número de minutos para considerar dos tiempos como simultáneos. Intervalo abierto.
	 * @return
	 */
	public boolean esSimultaneo(Registro otro, int margen) {
		return (Duration.between(this.fecha, otro.fecha).abs().compareTo(Duration.ofMinutes(margen)) <= 0);
	}
	
	/**
	 * Calcula si dos registros coinciden, con un margen de 1 minuto
	 * @param otro registro a comparar
	 * @return 
	 */
	public boolean esSimultaneo(Registro otro) {
		return this.esSimultaneo(otro,  1);
	}
}