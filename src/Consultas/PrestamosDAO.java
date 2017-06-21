package Consultas;

import java.sql.ResultSet;
import ConexionBD.Conexion;
import Entidades.Prestamos2;
import Utilitario.Autenticacion;
import Utilitario.UtilFechas;
import Utilitario.VariablesGlobales;

public class PrestamosDAO {
	private Autenticacion autenticacion;
	private Conexion conexion;
	
	public PrestamosDAO(Autenticacion autenticacion){
		this.autenticacion = autenticacion;
		conexion = new Conexion(autenticacion);
	}
	
	public int buscarNuevoId(){
		int id = 1;
		if(isConectado()){			
			StringBuilder query = new StringBuilder();
			query.append("SELECT IFNULL(")
				 .append("(SELECT MAX(id_prestamo)+1 FROM prestamos2)")
				 .append(", 1)");
			
			ResultSet res = conexion.consultar(query.toString());
			
			try{
				if(res.next()){
					id = res.getInt(1);
				}
			}catch(Exception e){
				if(VariablesGlobales.DEBUG){
					System.out.println("Error: PrestamosDAO.buscarNuevoId() "+e.getMessage());
					e.printStackTrace();
				}
			}
		}
		
		return id;
	}
	
	public Prestamos2 getPrestamo(int idPrestamo){
		Prestamos2 prestamo = null;		
		
		if(isConectado()){			 
			StringBuilder query = new StringBuilder();
			query.append("SELECT id_mision, descripcion ")				 
				 .append("FROM misiones ")
				 .append("WHERE id_mision=")
				 .append(idPrestamo);
			
			ResultSet res = conexion.consultar(query.toString());
			
			try{
				if(res.next()){
					prestamo = new Prestamos2();
					//prestamo.setId_mision(res.getInt("id_mision"));
					//prestamo.setDescripcion(res.getString("descripcion"));					
				}
				res.close();
			}
			catch(Exception e){
				if(VariablesGlobales.DEBUG){
					System.out.println("Error: PrestamosDAO.getPrestamo() "+e.getMessage());
					e.printStackTrace();
				}
			}
		}
		
		return prestamo;
	}
	
	public ResultSet getListadoPrestamos(String titulo, String usuario){
		ResultSet res = null;
		if(isConectado()){			
			StringBuilder query = new StringBuilder();
			query.append("SELECT p.id_prestamo id_prestamo, LPAD(p.id_prestamo, 6, '0') codigo, ")
				 .append("CONCAT(u.nombres, ' ', u.apellidos) usuario, u.cedula cedula, ")
				 .append("CONCAT(l.cota, ' e.', e.numero) cota, l.titulo titulo, ")
				 .append("p.fecha_prestamo fecha_prestamo, p.fecha_vencimiento fecha_vencimiento, p.fecha_entrega fecha_entrega ")
				 .append("FROM prestamos p, libros l, ejemplares e, usuarios u ")
				 .append("WHERE l.id_libro=e.id_libro AND p.id_ejemplar=e.id_ejemplar AND p.id_usuario=u.id_usuario");
				 
			if(!titulo.equals("%")){
				query.append(" AND l.titulo LIKE '")
					 .append(titulo)
					 .append("'");
			}
			if(!usuario.equals("%")){
				query.append(" AND (u.nombres LIKE '")
					 .append(usuario)
					 .append("' OR u.apellidos LIKE'")
					 .append(usuario)
					 .append("'");
			}
			
			query.append(" ORDER BY p.id_prestamo ASC");
			
			res = conexion.consultar(query.toString()); 
		}
		
		return res;
	}
	
	public ResultSet getListadoPrestamos2(){
		ResultSet res = null;
		if(isConectado()){			
			StringBuilder query = new StringBuilder();
			query.append("SELECT p.id_prestamo id_prestamo, LPAD(p.id_prestamo, 6, '0') codigo, ")
				 .append("CONCAT(u.nombres, ' ', u.apellidos) usuario, u.cedula cedula, ")
				 .append("CONCAT(l.cota, ' e.', e.numero), ")
				 .append("p.fecha_prestamo fecha_prestamo, p.fecha_vencimiento fecha_vencimiento, p.fecha_entrega fecha_entrega ")
				 .append("FROM prestamos2 p, libros l, ejemplares e, usuarios u ")
				 .append("WHERE l.id_libro=e.id_libro AND p.id_ejemplar=e.id_ejemplar AND p.id_usuario=u.id_usuario");
				 
			query.append(" ORDER BY p.id_prestamo ASC");
			
			res = conexion.consultar(query.toString()); 
		}
		
		return res;
	}
	
	public boolean guardarPrestamo(Prestamos2 prestamo){
		boolean resultado = false;
		if(isConectado()){			 
			StringBuilder query = new StringBuilder();
			query.append("INSERT INTO prestamos2(id_prestamo, id_usuario, id_ejemplar, ")
				 .append("fecha_prestamo, fecha_vencimiento, fecha_entrega) ")
				 .append("VALUES(")
				 .append(prestamo.getId_prestamo())
				 .append(", ").append(prestamo.getId_usuario())
				 .append(", ").append(prestamo.getId_ejemplar());
			
			if(prestamo.getFecha_prestamo()==null){
				query.append(", '")
					 .append(UtilFechas.convertirFecha(prestamo.getFecha_prestamo(), UtilFechas.YYYY_MM_DD))
					 .append("'");
			}
			else{
				query.append(", null");
			}
			if(prestamo.getFecha_vencimiento()==null){
				query.append(", '")
				 .append(UtilFechas.convertirFecha(prestamo.getFecha_vencimiento(), UtilFechas.YYYY_MM_DD))
				 .append("'");
			}
			else{
				query.append(", null");
			}
			if(prestamo.getFecha_entrega()==null){
				query.append(", '")
				 .append(UtilFechas.convertirFecha(prestamo.getFecha_entrega(), UtilFechas.YYYY_MM_DD))
				 .append("'");
			}
			else{
				query.append(", null");
			}
			query.append("')");
			
			resultado = conexion.actualizar(query.toString());			
		}
		
		return resultado;
	}
	
	public boolean actualizarPrestamo(Prestamos2 prestamo){
		boolean resultado = false;
		if(isConectado()){			 
			StringBuilder query = new StringBuilder();
			query.append("UPDATE prestamos2 SET ")
				 .append("fecha_entrega='")
				 .append(UtilFechas.convertirFecha(prestamo.getFecha_entrega(), UtilFechas.YYYY_MM_DD))
				 .append("' WHERE id_prestamo=").append(prestamo.getId_prestamo());
			
			resultado = conexion.actualizar(query.toString());
		}
		
		return resultado;
	}
	
	public void desconectar(){
		if(conexion != null){
			conexion.desconectar();
		}
	}
	
	public boolean isConectado(){
		if(conexion == null){
			conexion = new Conexion(autenticacion);
		}
		if(!conexion.isConectado()){
			conexion.conectar();
		}
		
		return conexion.isConectado();
	}
}
