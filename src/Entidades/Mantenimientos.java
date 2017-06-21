package Entidades;

import java.util.Date;

public class Mantenimientos {
	private int id_mantenimiento;
	private Date fecha;
	private String observaciones;
	private int id_ejemplar;
	private int valido;
	
	public Mantenimientos(){		
	}
	
	public int getId_mantenimiento() {
		return id_mantenimiento;
	}
	
	public void setId_mantenimiento(int id_mantenimiento) {
		this.id_mantenimiento = id_mantenimiento;
	}
	
	public Date getFecha() {
		return fecha;
	}
	
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	public String getObservaciones() {
		return observaciones;
	}
	
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
	public int getId_ejemplar() {
		return id_ejemplar;
	}
	
	public void setId_ejemplar(int id_ejemplar) {
		this.id_ejemplar = id_ejemplar;
	}

	public int getValido() {
		return valido;
	}

	public void setValido(int valido) {
		this.valido = valido;
	}
}
