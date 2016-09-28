/**
 * Define las caracteristicas de un Paciente del hospital: nombre, enfermedad y la prioridad de atencion.
 * 
 * @author Gabriel Brolo 15105, Jose Luis Mendez 15024
 * @version 1.0.0
 * @author gbrolo
 *
 */
public class Paciente implements Comparable<Paciente>{
	/* ATRIBUTOS */
	private String nombre;
	private String enfermedad;
	private String prioridad;
	
	/**
	 * Crea un objeto de tipo Paciente: un paciente del hospital, que tiene un nombre, una enfermedad y una prioridad de atecion.
	 * @param nombre, el nomble del paciente.
	 * @param enfermedad, la enfermedad, problema, o descripcion del inconveniente del paciente.
	 * @param prioridad, el nivel de prioridad de atencion del paciente segun su enfermedad. Prioridad mayor: A, B, C, ...
	 */
	public Paciente(String nombre, String enfermedad, String prioridad){
		this.nombre = nombre;
		this.enfermedad = enfermedad;
		this.prioridad = prioridad;
	}
	/**
	 * Devuelve el nombre del paciente.
	 * @return nombre
	 */
	public String getNombre() {return this.nombre;}
	/**
	 * Devuelve la enfermedad, situacion o descripcion del inconveniente del paciente.
	 * @return enfermedad
	 */
	public String getEnfermedad() {return this.enfermedad;}
	/**
	 * Devuelve la prioridad de atencion del paciente segun su enfermedad.
	 * @return prioridad
	 */
	public String getPrioridad() {return this.prioridad;}
	
	/**
	 * Compara un paciente con otro paciente segun su prioridad de atencion.
	 * @param otro, el paciente con el que se desea comparar.
	 * @return true si son iguales
	 */
	public boolean equals(Paciente otro){
		return this.getPrioridad().equals(otro.getPrioridad());
	}
	
	/**
	 * Compara un paciente con otro (Comparable) segun la prioridad de atencion.
	 * @param otro, el paciente con el que se desea comparar
	 * @return 0 si son iguales, mayor que 1 si es mas grande lexicograficamente o menor que 1 si no lo es
	 */
	public int compareTo(Paciente otro) {
		return this.getPrioridad().compareTo(otro.getPrioridad());
	}
	
	/**
	 * Devuelve todos los datos del Paciente en una cadena.
	 */
	public String toString(){
		return this.getNombre() + ", " + this.getEnfermedad() + ", " + this.getPrioridad();
	}

}
