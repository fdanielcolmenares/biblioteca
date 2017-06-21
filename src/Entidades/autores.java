package Entidades;

import java.sql.ResultSet;

import Utilitario.Autenticacion;

import ConexionBD.Conexion;

public class autores {
	private int id_autor;
	private String nombres;
	private String apellidos;
	private String nacionalidad;
	private int id_libro;
	private Autenticacion autenticar;
	
	public autores(Autenticacion autenticacion){
		id_autor=0;
		nombres="";
		apellidos="";
		nacionalidad="";
		id_libro=0;
		autenticar = autenticacion;
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

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public int getId_libro() {
		return id_libro;
	}

	public void setId_libro(int id_libro) {
		this.id_libro = id_libro;
	}

	public int getId_autor() {
		return id_autor;
	}
	
	public void buscarNuevoID(){
		Conexion con = new Conexion(autenticar);
		if(con.conectar()){
			String sql;
			sql = "select ifnull((select max(id_autor)+1 from autores), 1)";
			ResultSet resp = con.consultar(sql);
			try{
				if(resp.next()){
					id_autor = Integer.parseInt(resp.getString(1));
				}
			}
			catch(Exception ex){
				System.out.println("autores: Error al buscar nuevo ID");
				ex.printStackTrace();
			}
		}
		else{
			System.out.println("autores: No se pudo conectar a la BD");
		}
	}
	
	public boolean guardar(){
		String sql;
		sql="insert into autores (id_autor,nombres,apellidos,nacionalidad,id_libro) values ("+id_autor+"," +
				"'"+nombres+"','"+apellidos+"','"+nacionalidad+"',"+id_libro+")";
		Conexion con = new Conexion(autenticar);
		if(!con.conectar())
			System.out.println("no se pudo conectar");
		if(!con.actualizar(sql)){
			System.out.println("no se pudo guardar en la base de datos");
			return false;
		}
		con.desconectar();
		return true;
	}
	
	public ResultSet getAutores(int idLibro){
		ResultSet autores = null;
		Conexion con = new Conexion(autenticar);
		String sql;
		sql = "select * from autores where id_libro="+idLibro+"";
		if(con.conectar()){
			autores = con.consultar(sql);
		}
		else{
			System.out.println("autores: No se pudo conectar a la BD");
		}
		return autores;
	}
	
	public int getCountEjemplares(int idLibro){
		String sql;
		sql = "select count(id_autor) from autores where id_libro="+idLibro+"";
		Conexion con = new Conexion(autenticar);
		if(con.conectar()){
			ResultSet resp = con.consultar(sql);
			try{
				if(resp.next()){
					return resp.getInt(1);
				}
			}
			catch(Exception ex){
				System.out.println("autores: Error al buscar activo");
				ex.printStackTrace();
			}
		}
		else{
			System.out.println("autores: No se pudo conectar a la BD");
		}
		return 0;
	}
	
	public boolean actualizar(int id){
		String sql;
		sql = "update autores set nombres='"+nombres+"',apellidos='"+apellidos+"',nacionalidad='"+nacionalidad+"' where id_autor="+id+"";
		Conexion con = new Conexion(autenticar);
		if(con.conectar()){
			if(con.actualizar(sql)){
				con.desconectar();
				return true;
			}
			else{
				System.out.println("error al actualizar autores");
			}
		}
		else{
			System.out.println("autores: No se pudo conectar a la BD");
		}
		con.desconectar();
		return false;
	}
	
	public boolean eliminar(int id){
		String sql;
		sql = "delete from autores where id_autor="+id+"";
		Conexion con = new Conexion(autenticar);
		if(con.conectar()){
			if(con.actualizar(sql)){
				con.desconectar();
				return true;
			}
			else{
				System.out.println("error al eliminar autores");
			}
		}
		else{
			System.out.println("autores: No se pudo conectar a la BD");
		}
		con.desconectar();
		return false;
	}
}
