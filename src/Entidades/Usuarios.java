package Entidades;

import java.util.Date;

public class Usuarios {
	private int id_usuario;
	private String nombres;
	private String apellidos;
	private String cedula;
	private int edad;
	private String sexo;
	private Date fecha_nacimiento;
	private String direccion;
	private String profesion;
	private String institucion;
	private String telefono1;
	private String telefono2;
	private String usuario;
	private int tipo_usuario;
	private String clave;
	private String carnet;
	
	private Referencias referencia1;
	private Referencias referencia2;
	
	public Usuarios() {
		id_usuario = 0;
		nombres = "";
		apellidos = "";
		cedula = "";
		edad = 0;
		sexo = "";
		fecha_nacimiento = null;
		direccion = "";
		profesion = "";
		institucion = "";
		telefono1 = "";
		telefono2 = "";
		tipo_usuario = 0;
		clave = "";
		carnet = "";		
		usuario = "";
	}

	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
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

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Date getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(Date fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getProfesion() {
		return profesion;
	}

	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}

	public String getInstitucion() {
		return institucion;
	}

	public void setInstitucion(String institucion) {
		this.institucion = institucion;
	}

	public String getTelefono1() {
		return telefono1;
	}

	public void setTelefono1(String telefono1) {
		this.telefono1 = telefono1;
	}

	public String getTelefono2() {
		return telefono2;
	}

	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}

	public int getTipo_usuario() {
		return tipo_usuario;
	}

	public void setTipo_usuario(int tipo_usuario) {
		this.tipo_usuario = tipo_usuario;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getCarnet() {
		return carnet;
	}

	public void setCarnet(String carnet) {
		this.carnet = carnet;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Referencias getReferencia1() {
		return referencia1;
	}

	public void setReferencia1(Referencias referencia1) {
		this.referencia1 = referencia1;
	}

	public Referencias getReferencia2() {
		return referencia2;
	}

	public void setReferencia2(Referencias referencia2) {
		this.referencia2 = referencia2;
	}	
}