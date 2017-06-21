package Entidades;

import java.sql.ResultSet;

import ConexionBD.Conexion;
import Utilitario.Autenticacion;

public class ediciones {
	private int id_edicion;
	private String editorial;
	private String ano;
	private String lugar;
	private String numero_edicion;
	private int id_libro;
	private Autenticacion autenticar;
	
	public ediciones(Autenticacion autenticacion){
		id_edicion=0;
		editorial="";
		ano="";
		lugar="";
		numero_edicion="";
		id_libro=0;
		autenticar = autenticacion;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public String getNumero_edicion() {
		return numero_edicion;
	}

	public void setNumero_edicion(String numero_edicion) {
		this.numero_edicion = numero_edicion;
	}

	public int getId_libro() {
		return id_libro;
	}

	public void setId_libro(int id_libro) {
		this.id_libro = id_libro;
	}

	public int getId_edicion() {
		return id_edicion;
	}
	
	public void buscarNuevoID(){
		Conexion con = new Conexion(autenticar);
		if(con.conectar()){
			String sql;
			sql = "select ifnull((select max(id_edicion)+1 from ediciones), 1)";
			ResultSet resp = con.consultar(sql);
			try{
				if(resp.next()){
					id_edicion = Integer.parseInt(resp.getString(1));
				}
			}
			catch(Exception ex){
				System.out.println("edicones: Error al buscar nuevo ID");
				ex.printStackTrace();
			}
		}
		else{
			System.out.println("edicones: No se pudo conectar a la BD");
		}
	}
	
	public boolean guardar(){
		String sql;
		sql="insert into ediciones (id_edicion,editorial,ano,lugar,numero_edicion,id_libro) " +
				"values ("+id_edicion+",'"+editorial+"','"+ano+"','"+lugar+"','"+numero_edicion+"',"+id_libro+")";
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
	
	public ResultSet getEdiciones(int idLibro){
		ResultSet ediciones = null;
		Conexion con = new Conexion(autenticar);
		String sql;
		sql = "select * from ediciones where id_libro="+idLibro+"";
		if(con.conectar()){
			ediciones = con.consultar(sql);
		}
		else{
			System.out.println("ediciones: No se pudo conectar a la BD");
		}
		return ediciones;
	}
	
	public int getCountEjemplares(int idLibro){
		String sql;
		sql = "select count(id_edicion) from ediciones where id_libro="+idLibro+"";
		Conexion con = new Conexion(autenticar);
		if(con.conectar()){
			ResultSet resp = con.consultar(sql);
			try{
				if(resp.next()){
					return resp.getInt(1);
				}
			}
			catch(Exception ex){
				System.out.println("ediciones: Error al buscar activo");
				ex.printStackTrace();
			}
		}
		else{
			System.out.println("ediciones: No se pudo conectar a la BD");
		}
		return 0;
	}
	
	public boolean actualizar(int id){
		String sql;
		sql = "update ediciones set editorial='"+editorial+"',ano='"+ano+"',lugar='"+lugar+"',numero_edicion='"+numero_edicion+"' where id_edicion="+id+"";
		Conexion con = new Conexion(autenticar);
		if(con.conectar()){
			if(con.actualizar(sql)){
				con.desconectar();
				return true;
			}
			else{
				System.out.println("error al actualizar ediciones");
			}
		}
		else{
			System.out.println("ediciones: No se pudo conectar a la BD");
		}
		con.desconectar();
		return false;
	}	
	
	public boolean eliminar(int id){
		String sql;
		sql = "delete from ediciones where id_edicion="+id+"";
		Conexion con = new Conexion(autenticar);
		if(con.conectar()){
			if(con.actualizar(sql)){
				con.desconectar();
				return true;
			}
			else{
				System.out.println("error al eliminar ediciones");
			}
		}
		else{
			System.out.println("ediciones: No se pudo conectar a la BD");
		}
		con.desconectar();
		return false;
	}
}
