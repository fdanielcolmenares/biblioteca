package Consultas;

import java.sql.ResultSet;
import java.util.Date;

import ConexionBD.Conexion;
import Entidades.Registros;
import Utilitario.Autenticacion;
import Utilitario.UtilFechas;
import Utilitario.VariablesGlobales;

public class RegistrosDAO {
	private Autenticacion autenticacion;
	private Conexion conexion;
	
	public RegistrosDAO(Autenticacion autenticacion){
		this.autenticacion = autenticacion;
		conexion = new Conexion(autenticacion);
	}
	
	public int buscarNuevoId(){
		int id = 1;
		if(isConectado()){			
			StringBuilder query = new StringBuilder();
			query.append("SELECT IFNULL(")
				 .append("(SELECT MAX(id_registro)+1 FROM registros)")
				 .append(", 1)");
			
			ResultSet res = conexion.consultar(query.toString());
			
			try{
				if(res.next()){
					id = res.getInt(1);
				}
			}catch(Exception e){
				if(VariablesGlobales.DEBUG){
					System.out.println("Error: RegistrosDAO.buscarNuevoId() "+e.getMessage());
					e.printStackTrace();
				}
			}
		}
		
		return id;
	}

	public boolean guardarRegistro(Registros registro){
		boolean resultado = false;
		if(isConectado()){			 
			StringBuilder query = new StringBuilder();
			query.append("INSERT INTO registros(id_registro, sexo, edad, ")
				 .append("estudia, trabaja, id_mision, fecha) ")
				 .append("VALUES(")
				 .append(registro.getId_registro())
				 .append(", '").append(registro.getSexo())
				 .append("', ").append(registro.getEdad())
				 .append(", '").append(registro.getEstudia())
				 .append("', '").append(registro.getTrabaja())
				 .append("', ").append(registro.getMision().getId_mision())
				 .append(", '").append(UtilFechas.convertirFecha(registro.getFecha(), UtilFechas.YYYY_MM_DD))
				 .append("')");
			
			resultado = conexion.actualizar(query.toString());			
		}
		
		return resultado;
	}
	
	public ResultSet getListadoRegistros(Date fechaInicio, Date fechaFin){
		ResultSet res = null;
		if(isConectado()){			
			StringBuilder query = new StringBuilder();
			query.append("SELECT r.id_registro, ")
				 .append("CASE(UPPER(r.sexo)) WHEN 'M' THEN 'Masculino' WHEN 'F' THEN 'Femenino' END AS sexo, ")
				 .append("edad, ")
				 .append("CASE(UPPER(r.estudia)) WHEN 'S' THEN 'Si' WHEN 'N' THEN 'No' END AS estudia, ")
				 .append("CASE(UPPER(r.trabaja)) WHEN 'S' THEN 'Si' WHEN 'N' THEN 'No' END AS trabaja, ")
				 .append("r.fecha as fecha, ")
				 .append("m.descripcion AS mision ")
				 .append("FROM registros r, misiones m ")
				 .append("WHERE r.id_mision=m.id_mision");
			
			if(fechaInicio != null){
				query.append(" AND fecha >= '")
					 .append(UtilFechas.convertirFecha(fechaInicio, UtilFechas.YYYY_MM_DD))
					 .append("'");
			}
			if(fechaFin != null){
				query.append(" AND fecha <= '")
					 .append(UtilFechas.convertirFecha(fechaFin, UtilFechas.YYYY_MM_DD))
					 .append("'");
			}
			
			query.append(" ORDER BY id_registro ASC");
			
			res = conexion.consultar(query.toString()); 
		}
		
		return res;
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
