package Entidades;

import java.sql.ResultSet;

import Utilitario.Autenticacion;
import ConexionBD.Conexion;

public class libros {
	
	private int id_libro;
	private String cota;
	private String titulo;
	private String estante;
	private int id_sala;
	private Autenticacion autenticar;
	
	public libros(Autenticacion autenticacion){
		id_libro=0;
		cota="";
		titulo="";
		estante="";
		id_sala=0;
		autenticar = autenticacion;
	}

	public String getCota() {
		return cota;
	}

	public void setCota(String cota) {
		this.cota = cota;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getEstante() {
		return estante;
	}

	public void setEstante(String estante) {
		this.estante = estante;
	}

	public int getId_sala() {
		return id_sala;
	}

	public void setId_sala(int id_sala) {
		this.id_sala = id_sala;
	}

	public int getId_libro() {
		return id_libro;
	}
	
	public void buscarNuevoID(){
		Conexion con = new Conexion(autenticar);
		if(con.conectar()){
			String sql;
			sql = "select ifnull((select max(id_libro)+1 from libros), 1)";
			ResultSet resp = con.consultar(sql);
			try{
				if(resp.next()){
					id_libro = Integer.parseInt(resp.getString(1));
				}
			}
			catch(Exception ex){
				System.out.println("libros: Error al buscar nuevo ID");
				ex.printStackTrace();
			}
		}
		else{
			System.out.println("libros: No se pudo conectar a la BD");
		}
	}
	
	public boolean guardar(){
		String sql;
		sql="insert into libros (id_libro,cota,titulo,estante,id_sala) values ("+id_libro+"," +
				"'"+cota+"','"+titulo+"','"+estante+"',"+id_sala+")";
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
		sql = "select id_libro, cota from libros where cota = '"+des+"'";
		Conexion con = new Conexion(autenticar);
		if(con.conectar()){
			ResultSet resp = con.consultar(sql);
			try{
				if(resp.next()){
					return true;
				}
			}
			catch(Exception ex){
				System.out.println("libros: Error al validar");
				ex.printStackTrace();
			}
		}
		else{
			System.out.println("libros: No se pudo conectar a la BD");
		}
		return false;
	}
	
	public ResultSet getLibro(int id){
		ResultSet libro = null;
		Conexion con = new Conexion(autenticar);
		String sql;
		sql = "select * from libros where id_libro="+id+"";
		if(con.conectar()){
			libro = con.consultar(sql);
		}
		else{
			System.out.println("libros: No se pudo conectar a la BD");
		}
		return libro;
	}
	
	public boolean actualizar(int id){
		String sql;
		sql = "update libros set cota='"+cota+"',titulo='"+titulo+"',estante='"+estante+"',id_sala="+id_sala+" where id_libro="+id+"";
		Conexion con = new Conexion(autenticar);
		if(con.conectar()){
			if(con.actualizar(sql)){
				con.desconectar();
				return true;
			}
			else{
				System.out.println("error al actualizar libros");
			}
		}
		else{
			System.out.println("libros: No se pudo conectar a la BD");
		}
		con.desconectar();
		return false;
	}
	
	public int buscarCota(String cota){
		String sql;
		sql = "select id_libro from libros where cota='"+cota+"'";
		Conexion con = new Conexion(autenticar);
		if(con.conectar()){
			ResultSet resp = con.consultar(sql);
			try{
				if(resp.next()){
					return resp.getInt("id_libro");
				}
			}
			catch(Exception ex){
				System.out.println("libros: Error al buscar id_libros");
				ex.printStackTrace();
			}
		}
		else{
			System.out.println("libros: No se pudo conectar a la BD");
		}
		return 0;
	}
}
