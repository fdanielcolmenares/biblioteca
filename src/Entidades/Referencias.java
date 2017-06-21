package Entidades;


public class Referencias {
	private int id_referencia;
	private String cedula;
	private String nombres;
	private String apellidos;
	private String direccion;
	private String telefono;
	private int id_usuario;
	
	public Referencias() {
		cedula = "";
		nombres = "";
		apellidos = "";
		direccion = "";
		telefono = "";
	}

	public int getId_referencia() {
		return id_referencia;
	}
	
	public void setId_referencia(int id_referencia) {
		this.id_referencia = id_referencia;
	}
	
	public String getNombres() {
		return nombres;
	}
	
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	
	public String getApellidos() {
		return apellidos;
	}
	
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	
	public String getDireccion() {
		return direccion;
	}
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public String getTelefono() {
		return telefono;
	}
	
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}	
}
