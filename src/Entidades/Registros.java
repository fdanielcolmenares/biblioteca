package Entidades;

import java.util.Date;

public class Registros {
	private int id_registro;
	private String sexo;
	private int edad;
	private String estudia;
	private String trabaja;
	private Date fecha;
	private Misiones mision;
	
	public Registros(){
		mision = new Misiones();
	}
	
	public int getId_registro() {
		return id_registro;
	}
	
	public void setId_registro(int id_registro) {
		this.id_registro = id_registro;
	}
	
	public String getSexo() {
		return sexo;
	}
	
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	public int getEdad() {
		return edad;
	}
	
	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	public String getEstudia() {
		return estudia;
	}
	
	public void setEstudia(String estudia) {
		this.estudia = estudia;
	}
	
	public String getTrabaja() {
		return trabaja;
	}
	
	public void setTrabaja(String trabaja) {
		this.trabaja = trabaja;
	}
	
	public Date getFecha() {
		return fecha;
	}
	
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Misiones getMision() {
		return mision;
	}

	public void setMision(Misiones mision) {
		this.mision = mision;
	}
}
