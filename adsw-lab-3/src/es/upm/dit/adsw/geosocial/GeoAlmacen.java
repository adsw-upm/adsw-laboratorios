package es.upm.dit.adsw.geosocial;


import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Clase que almacena usuarios y registros de una red de social de geolocalización.
 * 
 */
public class GeoAlmacen {
	
	protected List<Usuario> usuarios;
	protected List<Registro> registros;
	
    /**
     * Construye un gestor de usuarios vacío
     */
	public GeoAlmacen() {
		this.usuarios = new ArrayList<Usuario>();
		this.registros = new ArrayList<Registro>();
	}
	
    /**
     * Construye un gestor de usuarios a partir de un listado de usuarios.
     *
     * @param usuarios que tendrá el almacén
     */
	public GeoAlmacen(List<Usuario> usuarios) {
		this();
		this.usuarios = usuarios;
		for(Usuario u: usuarios) {
			for(Registro r: u.getRegistros()) {
				this.registros.add(r);
			}
		}
	}
	
    /**
     * Construye un gestor de usuarios a partir del fichero de localizaciones y el fichero con las amistades entre usuarios.
     * @param registros Fichero TSV con los registros
     * @param amistades Fichero TSV con las amistades entre usuarios. Cada línea es una relación entre dos usuarios. 
     * @throws FileNotFoundException Si el fichero no existe
     * @throws ParseException Si el formato del fichero no es el adecuado.
     */
    public GeoAlmacen(String registros, String amistades) throws FileNotFoundException, ParseException {
    	this(registros);
    	this.leerAmistades(amistades);
    }
    
    /**
     * Construye un registro de usuarios partir del fichero de localizaciones.
     * 
     * @param registros Fichero TSV con registros del usuario. Las columnas del fichero deben user_id, date, lat, long, place_id

     * @throws FileNotFoundException Si no existe el fichero especificado
     * @throws ParseException Si el formato del fichero no es el adecuado
     */
    public GeoAlmacen(String registros) throws FileNotFoundException, ParseException {
    	this();
        this.leerLocalizaciones(registros);
    }
    
    /**
     * @return todos los registros del almacén
     */
    public List<Registro> getRegistros() {
    	return this.registros;
    }
    
    /**
     * @return todos los usuarios del almacén 
     */
    public List<Usuario> getUsuarios() {
    	return this.usuarios;
    }
	
    /**
     * Calcula el número de usuarios en el almacén
     * @return número de usuarios
     */
	public int getNUsuarios() {
		return this.usuarios.size();
	}
	
	/**
	 * Calcula la suma de los registros de cada usuario del almacén
	 * @return número de registros
	 */
	
	public int getNRegistros() {
		int total = 0;
		for(Usuario user: this.usuarios) {
			total += user.getNRegistros();
		}
		return total;
	}
		
    /**
     * Devuelve un usuario dado un identificador.
     * El objeto devuelto es una copia del original, los cambios hechos
     * sobre este objeto no se reflejan en el gestor.
     *   
     * @param user_id Identificador del usuario
     * @return Copia del usuario encontrado o null
     */

	public Usuario getUsuario(int user_id) {
		for(Usuario user: this.usuarios) {
			if(user.id == user_id) {
				return user;
			}
		}
		return null;
	}
	
	/**
	 * Devuelve el índice del primer registro posterior a una fecha dada.
	 * Este método asume que la lista de registros **ya está ordenada**.
	 * 
	 * @param fecha a partir de la cual se encontrará el primer registro
	 * @return índice del primer registro posterior a la fecha dada.
	 */
	public int buscarIndiceRegistro(LocalDateTime fecha) {
		int low = 0;
		int high = registros.size()-1;
		int indice = -1;
		
		while(low <= high) {
			indice = (low+high)/2;
			int cmp = registros.get(indice).getFecha().compareTo(fecha);
			if(cmp > 0) {
				high = indice - 1;
			}else if (cmp < 0) {
				low = indice + 1;
			} else {
				break;
			}
		}
		
		while(indice < registros.size() && !registros.get(indice).getFecha().isAfter(fecha)) {
			indice++;
		}
		return indice;
	}
	
	/**
	 * Carga todas las amistades de un fichero
	 * @param amistades
	 * @throws FileNotFoundException
	 * @throws ParseException
	 */
	public void leerAmistades(String amistades) throws FileNotFoundException, ParseException {
        File cf = new File(amistades);
        try (Scanner reader = new Scanner(cf)) {
			while (reader.hasNextLine()) {
			    List<String> ids = Arrays.asList(reader.nextLine().split("\t"));
			    this.insertarAmistad(Integer.parseInt(ids.get(0)), Integer.parseInt(ids.get(1)));
			}
		}
    }   
    
    public void leerLocalizaciones(String localizaciones) throws FileNotFoundException, ParseException  {
    	File meta = new File(localizaciones);
        Scanner reader = new Scanner(meta);
        DateTimeFormatter parser = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        
        while (reader.hasNextLine()) {
            String[] data = reader.nextLine().split("\t");
            if(data.length < 1) {
            	continue;
            }
        	int id_usuario = Integer.parseInt(data[0]);
        	LocalDateTime fecha = LocalDateTime.parse(data[1], parser);
        	float lat = Float.parseFloat(data[2]);
        	float lon = Float.parseFloat(data[3]);
        	
        	Registro registro = new Registro(id_usuario, fecha, new Localizacion(lat, lon));
        	Usuario user = this.getOrCreateUsuario(id_usuario);
        	this.registros.add(registro);
        	user.registrar(registro);
        }
        reader.close();
        this.ordenar();
    }
	
    public Usuario getOrCreateUsuario(int user_id) {
		for(Usuario user: this.usuarios) {
			if(user.id == user_id) {
				return user;
			}
		}
		return this.createUsuario(user_id);
	}
	
	/**
	 * Ordena la lista de registros en orden ascendente de fecha
	 */
	public void ordenar() {
    	this.registros.sort((r1, r2) -> r1.getFecha().compareTo(r2.getFecha()));
	}
	
	public Usuario createUsuario(int user_id) {
		Usuario nuevo = new Usuario(user_id);
		this.usuarios.add(nuevo);
		return nuevo;
	}
	
	/**
	 * Inserta una amistad bidireccional entre dos usuarios
	 * @param user1
	 * @param user2
	 */
	public void insertarAmistad(int user1, int user2) {
		Usuario u1 = this.getOrCreateUsuario(user1);
		Usuario u2 = this.getOrCreateUsuario(user2);
		u1.insertaAmigo(u2);
		u2.insertaAmigo(u1);
	}
	
	/**
	 * Elimina una amistad entre dos usuarios
	 * @param user1
	 * @param user2
	 */
	public void borrarAmistad(int user1, int user2) {
		Usuario u1 = this.getUsuario(user1);
		Usuario u2 = this.getUsuario(user2);
		u1.borraAmigo(u2);
		u2.borraAmigo(u1);

	}
}
