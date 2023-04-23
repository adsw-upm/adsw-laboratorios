package es.upm.dit.adsw.geosocial;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Usuario de la red social de geolocalización
 * Internamente, un usuario tiene una lista de los registros asociados al usuario, y una lista de otros usuarios
 * que son amigos. 
 */
public class Usuario implements Cloneable {
	public int id;
	public List<Registro> registros; 
	public List<Usuario> amigos;
	
	public Usuario(int id_usuario) {
		this.id = id_usuario;
		this.amigos = new ArrayList<Usuario>();
		this.registros = new ArrayList<Registro>();
	}
	
	public int getId() {
		return this.id;
	}
	
	public void insertaAmigo(Usuario u2) {
		this.amigos.add(u2);
	}
	
	public void borraAmigo(Usuario u2) {
		this.amigos.remove(u2);
	}
	
	public void registrar(Registro reg) {
		this.registros.add(reg);
	}
	
	public void registrar(Localizacion loc) {
		this.registros.add(new Registro(this.id, loc));
	}
	
	public void registrar(Localizacion loc, LocalDateTime tiempo) {
		this.registros.add(new Registro(this.id, tiempo, loc));
	}

	public List<Registro> getRegistros() {
		return this.registros;
	}

	public Integer getNRegistros() {
		return this.registros.size();
	}
	
	public Registro getUltimo() {
		Registro ultimo = null;
		for(Registro reg: this.registros) {
			if(ultimo == null || reg.getFecha().isAfter(ultimo.getFecha())) {
				ultimo = reg;
			}
		}
		return ultimo;
	}
	
	@Override
	public Usuario clone() {
		Usuario copia = new Usuario(this.id);
		copia.registros.addAll(this.registros);
		copia.amigos.addAll(this.amigos);
		return copia;
	}
	
	public boolean equals(Usuario other) {
		return this.id == other.id;
	}

}
