package Entidades;

import java.sql.ResultSet;

import Utilitario.Autenticacion;

import ConexionBD.Conexion;

public class ejemplares {
	private int id_ejemplar;
	private int numero;
	private int id_libro;
	private String estado;
	private Autenticacion autenticar;
	
	public ejemplares(Autenticacion autenticacion){
		id_ejemplar=0;
		numero=0;
		id_libro=0;
		autenticar = autenticacion;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getId_libro() {
		return id_libro;
	}

	public void setId_libro(int id_libro) {
		this.id_libro = id_libro;
	}

	public int getId_ejemplar() {
		return id_ejemplar;
	}
	
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public void setId_ejemplar(int id_ejemplar) {
		this.id_ejemplar = id_ejemplar;
	}

	public void buscarNuevoID(){
		Conexion con = new Conexion(autenticar);
		if(con.conectar()){
			String sql;
			sql = "select ifnull((select max(id_ejemplar)+1 from ejemplares), 1)";
			ResultSet resp = con.consultar(sql);
			try{
				if(resp.next()){
					id_ejemplar = Integer.parseInt(resp.getString(1));
				}
			}
			catch(Exception ex){
				System.out.println("ejemplares: Error al buscar nuevo ID");
				ex.printStackTrace();
			}
		}
		else{
			System.out.println("ejemplares: No se pudo conectar a la BD");
		}
	}
	
	public boolean guardar(){//preguntar id libro como hacer?
		String sql;
		sql="insert into ejemplares (id_ejemplar,numero,id_libro, estado) values ("+id_ejemplar+"," +
				""+numero+","+id_libro+", 'existe')";
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
	
	public ResultSet getEjemplares(int idLibro){
		ResultSet ejemplares = null;
		Conexion con = new Conexion(autenticar);
		String sql;
		sql = "select * from ejemplares where id_libro="+idLibro+"";
		if(con.conectar()){
			ejemplares = con.consultar(sql);
		}
		else{
			System.out.println("ejemplares: No se pudo conectar a la BD");
		}
		return ejemplares;
	}
	
	public int getCountEjemplares(int idLibro){
		String sql;
		sql = "select count(id_ejemplar) from ejemplares where id_libro="+idLibro+"";
		Conexion con = new Conexion(autenticar);
		if(con.conectar()){
			ResultSet resp = con.consultar(sql);
			try{
				if(resp.next()){
					return resp.getInt(1);
				}
			}
			catch(Exception ex){
				System.out.println("ejemplares: Error al buscar activo");
				ex.printStackTrace();
			}
		}
		else{
			System.out.println("ejemplares: No se pudo conectar a la BD");
		}
		return 0;
	}
	
	public boolean actualizar(int id){
		String sql;
		sql = "update ejemplares set numero="+numero+" where id_ejemplar="+id+"";
		Conexion con = new Conexion(autenticar);
		if(con.conectar()){
			if(con.actualizar(sql)){
				con.desconectar();
				return true;
			}
			else{
				System.out.println("error al actualizar ejemplares");
			}
		}
		else{
			System.out.println("ejemplares: No se pudo conectar a la BD");
		}
		con.desconectar();
		return false;
	}	
	
	public boolean eliminar(int id){
		String sql;
		sql = "delete from ejemplares where id_ejemplar="+id+"";
		Conexion con = new Conexion(autenticar);
		if(con.conectar()){
			if(con.actualizar(sql)){
				con.desconectar();
				return true;
			}
			else{
				System.out.println("error al eliminar ejemplares");
			}
		}
		else{
			System.out.println("ejemplares: No se pudo conectar a la BD");
		}
		con.desconectar();
		return false;
	}
}
