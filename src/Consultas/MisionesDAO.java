package Consultas;

import java.sql.ResultSet;
import org.apache.commons.lang.StringEscapeUtils;
import ConexionBD.Conexion;
import Entidades.Misiones;
import Utilitario.Autenticacion;
import Utilitario.VariablesGlobales;

public class MisionesDAO {
	private Autenticacion autenticacion;
	private Conexion conexion;
	
	public MisionesDAO(Autenticacion autenticacion){
		this.autenticacion = autenticacion;
		conexion = new Conexion(autenticacion);
	}
	
	public int buscarNuevoId(){
		int id = 1;
		if(isConectado()){			
			StringBuilder query = new StringBuilder();
			query.append("SELECT IFNULL(")
				 .append("(SELECT MAX(id_mision)+1 FROM misiones)")
				 .append(", 1)");
			
			ResultSet res = conexion.consultar(query.toString());
			
			try{
				if(res.next()){
					id = res.getInt(1);
				}
			}catch(Exception e){
				if(VariablesGlobales.DEBUG){
					System.out.println("Error: UsuariosDAO.buscarNuevoId() "+e.getMessage());
					e.printStackTrace();
				}
			}
		}
		
		return id;
	}
	
	public boolean validarMision(int id_mision, String descripcion){
		int cont = 0;
		if(isConectado()){			
			StringBuilder query = new StringBuilder();
			query.append("SELECT COUNT(*) ")				 
				 .append("FROM misiones ")
				 .append("WHERE descripcion='")
				 .append(StringEscapeUtils.escapeSql(descripcion))
				 .append("' AND id_mision != ").append(id_mision);
			
			ResultSet res = conexion.consultar(query.toString());
			
			try{
				if(res.next()){
					cont = res.getInt(1);					
				}
				res.close();
			}
			catch(Exception e){
				if(VariablesGlobales.DEBUG){
					System.out.println("Error: MisionesDAO.validarMision() "+e.getMessage());
					e.printStackTrace();
				}
			}
		}
		
		return cont == 0;
	}
	
	public Misiones getMision(int idMision){
		Misiones mision = null;		
		
		if(isConectado()){			 
			StringBuilder query = new StringBuilder();
			query.append("SELECT id_mision, descripcion ")				 
				 .append("FROM misiones ")
				 .append("WHERE id_mision=")
				 .append(idMision);
			
			ResultSet res = conexion.consultar(query.toString());
			
			try{
				if(res.next()){
					mision = new Misiones();
					mision.setId_mision(res.getInt("id_mision"));
					mision.setDescripcion(res.getString("descripcion"));					
				}
				res.close();
			}
			catch(Exception e){
				if(VariablesGlobales.DEBUG){
					System.out.println("Error: MisionesDAO.getMision() "+e.getMessage());
					e.printStackTrace();
				}
			}
		}
		
		return mision;
	}
	
	public ResultSet getListadoMisiones(String descripcion){
		ResultSet res = null;
		if(isConectado()){			
			StringBuilder query = new StringBuilder();
			query.append("SELECT id_mision, descripcion ")				 
				 .append("FROM misiones");
			
			if(!descripcion.equals("%")){
				query.append(" WHERE descripcion LIKE '").append(descripcion).append("'");
			}
				 
			query.append(" ORDER BY id_mision ASC");
			
			res = conexion.consultar(query.toString()); 
		}
		
		return res;
	}
	
	public boolean guardarMision(Misiones mision){
		boolean resultado = false;
		if(isConectado()){			 
			StringBuilder query = new StringBuilder();
			query.append("INSERT INTO misiones(id_mision, descripcion) ")
				 .append("VALUES(")
				 .append(mision.getId_mision())
				 .append(", '").append(mision.getDescripcion())
				 .append("')");
			
			resultado = conexion.actualizar(query.toString());			
		}
		
		return resultado;
	}
	
	public boolean actualizarMision(Misiones mision){
		boolean resultado = false;
		if(isConectado()){			 
			StringBuilder query = new StringBuilder();
			query.append("UPDATE misiones SET ")
				 .append("descripcion='").append(mision.getDescripcion())
				 .append("' WHERE id_mision=").append(mision.getId_mision());
			
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
