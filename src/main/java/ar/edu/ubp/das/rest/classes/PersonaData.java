package ar.edu.ubp.das.rest.classes;

import java.util.Date;
import java.util.List;

public class PersonaData {

	private String nombre;
	private String apellido;
	private String clave;
	private String correo;
	private String codGenero;
	private Date   fechaNacimiento;
	private String codNacionalidad;
	private List<Integer> equipos;
	private List<Integer> actividades;


	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getCodGenero() {
		return codGenero;
	}
	public void setCodGenero(String codGenero) {
		this.codGenero = codGenero;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getCodNacionalidad() {
		return codNacionalidad;
	}
	public void setCodNacionalidad(String codNacionalidad) {
		this.codNacionalidad = codNacionalidad;
	}
	
	public List<Integer> getActividades() {
		return actividades;
	}
	public void setActividades(List<Integer> actividades) {
		this.actividades = actividades;
	}
	
	public List<Integer> getEquipos() {
		return equipos;
	}
	public void setEquipos(List<Integer> equipos) {
		this.equipos = equipos;
	}
}
