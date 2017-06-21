package Entidades;

import java.util.Date;

public class Prestamos2 {
	private int id_prestamo;
	private int id_usuario;
	private int id_ejemplar;
	private Date fecha_prestamo;
	private Date fecha_vencimiento;
	private Date fecha_entrega;
	
	public Prestamos2(){
		fecha_entrega = null;
	}
	
	public int getId_prestamo() {
		return id_prestamo;
	}
	
	public void setId_prestamo(int id_prestamo) {
		this.id_prestamo = id_prestamo;
	}
	
	public int getId_usuario() {
		return id_usuario;
	}
	
	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}
	
	public int getId_ejemplar() {
		return id_ejemplar;
	}
	
	public void setId_ejemplar(int id_ejemplar) {
		this.id_ejemplar = id_ejemplar;
	}
	
	public Date getFecha_prestamo() {
		return fecha_prestamo;
	}
	
	public void setFecha_prestamo(Date fecha_prestamo) {
		this.fecha_prestamo = fecha_prestamo;
	}
	public Date getFecha_vencimiento() {
		return fecha_vencimiento;
	}
	
	public void setFecha_vencimiento(Date fecha_vencimiento) {
		this.fecha_vencimiento = fecha_vencimiento;
	}
	
	public Date getFecha_entrega() {
		return fecha_entrega;
	}
	
	public void setFecha_entrega(Date fecha_entrega) {
		this.fecha_entrega = fecha_entrega;
	}	
}
