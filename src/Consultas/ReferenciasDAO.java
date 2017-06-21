package Consultas;

import java.sql.ResultSet;

import ConexionBD.Conexion;
import Entidades.Referencias;
import Utilitario.Autenticacion;
import Utilitario.VariablesGlobales;

public class ReferenciasDAO {
	private Autenticacion autenticacion;
	private Conexion conexion;
	
	public ReferenciasDAO(Autenticacion autenticacion){
		this.autenticacion = autenticacion;
		conexion = new Conexion(autenticacion);
	}
	
	public void getReferenciasUsuario(int id_usuario, Referencias referencia1, Referencias referencia2){
		if(isConectado()){			 
			StringBuilder query = new StringBuilder();
			query.append("SELECT id_referencia, nombres, apellidos, ")
				 .append("cedula, direccion, telefono ")				 
				 .append("FROM referencias ")
				 .append("WHERE id_usuario=")
				 .append(id_usuario);
			
			ResultSet res = conexion.consultar(query.toString());
			
			try{
				if(res.next()){
					referencia1.setId_referencia(res.getInt("id_referencia"));
					referencia1.setNombres(res.getString("nombres"));
					referencia1.setApellidos(res.getString("apellidos"));
					referencia1.setCedula(res.getString("cedula"));
					referencia1.setDireccion(res.getString("direccion"));
					referencia1.setTelefono(res.getString("telefono"));
					referencia1.setId_usuario(id_usuario);
				}
				
				if(res.next()){
					referencia2.setId_referencia(res.getInt("id_referencia"));
					referencia2.setNombres(res.getString("nombres"));
					referencia2.setApellidos(res.getString("apellidos"));
					referencia2.setCedula(res.getString("cedula"));
					referencia2.setDireccion(res.getString("direccion"));
					referencia2.setTelefono(res.getString("telefono"));
					referencia2.setId_usuario(id_usuario);					
				}
				res.close();
			}
			catch(Exception e){
				if(VariablesGlobales.DEBUG){
					System.out.println("Error: ReferenciasDAO.getReferenciasUsuario() "+e.getMessage());
					e.printStackTrace();
				}
			}
		}
	}
	
	public boolean guardarReferencia(Referencias referencia){
		boolean resultado = false;
		if(isConectado()){			 
			StringBuilder query = new StringBuilder();
			query.append("INSERT INTO referencias(id_referencia, cedula, nombres, apellidos, ")
				 .append("direccion, telefono, id_usuario) ")
				 .append("VALUES(")
				 .append(referencia.getId_referencia())
				 .append(", '").append(referencia.getCedula())
				 .append("', '").append(referencia.getNombres())
				 .append("', '").append(referencia.getApellidos())
				 .append("', '").append(referencia.getDireccion())
				 .append("', '").append(referencia.getTelefono())
				 .append("', ").append(referencia.getId_usuario())
				 .append(")");
			
			resultado = conexion.actualizar(query.toString());			
		}
		
		return resultado;
	}
	
	public boolean actualizarReferencia(Referencias referencia){
		boolean resultado = false;
		if(isConectado()){			 
			StringBuilder query = new StringBuilder();
			query.append("UPDATE referencias SET ")
				 .append("cedula='").append(referencia.getCedula())
				 .append("', nombres='").append(referencia.getNombres())
				 .append("', apellidos='").append(referencia.getApellidos())				 
				 .append("', direccion='").append(referencia.getDireccion())
				 .append("', telefono='").append(referencia.getTelefono())				 
				 .append("' WHERE id_referencia=").append(referencia.getId_referencia());
			
			resultado = conexion.actualizar(query.toString());
		}
		
		return resultado;
	}
	
	public int buscarNuevoId(){
		int id = 1;
		if(isConectado()){			
			StringBuilder query = new StringBuilder();
			query.append("SELECT IFNULL(")
				 .append("(SELECT MAX(id_referencia)+1 FROM referencias)")
				 .append(", 1)");
			
			ResultSet res = conexion.consultar(query.toString());
			
			try{
				if(res.next()){
					id = res.getInt(1);
				}
			}catch(Exception e){
				if(VariablesGlobales.DEBUG){
					System.out.println("Error: ReferenciasDAO.buscarNuevoId() "+e.getMessage());
					e.printStackTrace();
				}
			}
		}
		
		return id;
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
