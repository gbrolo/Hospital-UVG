
public class Paciente implements Comparable<Paciente>{

	private String nombre;
	private String enfermedad;
	private String prioridad;
	
	public Paciente(String nombre, String enfermedad, String prioridad){
		this.nombre = nombre;
		this.enfermedad = enfermedad;
		this.prioridad = prioridad;
	}
	
	public String getNombre() {return this.nombre;}
	public String getEnfermedad() {return this.enfermedad;}
	public String getPrioridad() {return this.prioridad;}
	
	public boolean equals(Paciente otro){
		return this.getPrioridad().equals(otro.getPrioridad());
	}
	
	public int compareTo(Paciente otro) {
		return this.getPrioridad().compareTo(otro.getPrioridad());
	}
	
	

}
