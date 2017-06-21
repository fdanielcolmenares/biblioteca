package Entidades;

import java.sql.ResultSet;

import ConexionBD.Conexion;
import Utilitario.Autenticacion;

public class salas {
	
	private int id_sala;
	private String descripcion;
	private int activo;//nuevo
	private Autenticacion autenticar;
	
	public salas(Autenticacion autenticacion){
		id_sala=0;
		descripcion="";
		activo=0;
		autenticar = autenticacion;
	}

	public int getActivo() {
		return activo;
	}

	public void setActivo(int activo) {
		this.activo = activo;
	}

	public int getId_sala() {
		return id_sala;
	}

	public void setId_sala(int id_sala) {
		this.id_sala = id_sala;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Autenticacion getAutenticar() {
		return autenticar;
	}

	public void setAutenticar(Autenticacion autenticar) {
		this.autenticar = autenticar;
	}
	
	public void buscarNuevoID(){
		Conexion con = new Conexion(autenticar);
		if(con.conectar()){
			String sql;
			sql = "select ifnull((select max(id_sala)+1 from salas), 1)";
			ResultSet resp = con.consultar(sql);
			try{
				if(resp.next()){
					id_sala = Integer.parseInt(resp.getString(1));
				}
			}
			catch(Exception ex){
				System.out.println("salas: Error al buscar nuevo ID");
				ex.printStackTrace();
			}
		}
		else{
			System.out.println("salas: No se pudo conectar a la BD");
		}
	}
	
	public boolean guardar(){
		String sql;
		sql="insert into salas (id_sala,descripcion,activo) values ("+id_sala+"," +
				"'"+descripcion+"',"+activo+")";
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
	
	public boolean validar(String des){
		String sql;
		sql = "select * from salas where descripcion = '"+des+"'";
		Conexion con = new Conexion(autenticar);
		if(con.conectar()){
			ResultSet resp = con.consultar(sql);
			try{
				if(resp.next()){
					return true;
				}
			}
			catch(Exception ex){
				System.out.println("salas: Error al validar");
				ex.printStackTrace();
			}
		}
		else{
			System.out.println("salas: No se pudo conectar a la BD");
		}
		return false;
	}
	
	public ResultSet getSalas(){
		ResultSet salas = null;
		Conexion con = new Conexion(autenticar);
		String sql;
		sql = "select * from salas";
		if(con.conectar()){
			salas = con.consultar(sql);
		}
		else{
			System.out.println("salas: No se pudo conectar a la BD");
		}
		return salas;
	}
	
	public int buscarActivo(String des){
		String sql;
		sql = "select descripcion,activo from salas where descripcion='"+des+"'";
		Conexion con = new Conexion(autenticar);
		if(con.conectar()){
			ResultSet resp = con.consultar(sql);
			try{
				if(resp.next()){
					return resp.getInt("activo");
				}
			}
			catch(Exception ex){
				System.out.println("salas: Error al buscar activo");
				ex.printStackTrace();
			}
		}
		else{
			System.out.println("salas: No se pudo conectar a la BD");
		}
		return 0;
	}
	
	public boolean actualizar(int id){
		String sql;
		sql = "update salas set descripcion='"+descripcion+"',activo="+activo+" where id_Sala="+id+"";
		Conexion con = new Conexion(autenticar);
		if(con.conectar()){
			if(con.actualizar(sql)){
				con.desconectar();
				return true;
			}
			else{
				System.out.println("error al actualizar salas");
			}
		}
		else{
			System.out.println("salas: No se pudo conectar a la BD");
		}
		con.desconectar();
		return false;
	}

}
