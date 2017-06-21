package Consultas;

import java.sql.ResultSet;
import java.util.Date;
import ConexionBD.Conexion;
import Entidades.Mantenimientos;
import Utilitario.Autenticacion;
import Utilitario.UtilFechas;
import Utilitario.VariablesGlobales;

public class MantenimientosDAO {
	private Autenticacion autenticacion;
	private Conexion conexion;
	
	public MantenimientosDAO(Autenticacion autenticacion){
		this.autenticacion = autenticacion;
		conexion = new Conexion(autenticacion);
	}
	
	public int buscarNuevoId(){
		int id = 1;
		if(isConectado()){			
			StringBuilder query = new StringBuilder();
			query.append("SELECT IFNULL(")
				 .append("(SELECT MAX(id_mantenimiento)+1 FROM mantenimientos)")
				 .append(", 1)");
			
			ResultSet res = conexion.consultar(query.toString());
			
			try{
				if(res.next()){
					id = res.getInt(1);
				}
			}catch(Exception e){
				if(VariablesGlobales.DEBUG){
					System.out.println("Error: MantenimientosDAO.buscarNuevoId() "+e.getMessage());
					e.printStackTrace();
				}
			}
		}
		
		return id;
	}
	
	public Mantenimientos getMantenimiento(int idMantenimiento){
		Mantenimientos mantenimiento = null;		
		
		if(isConectado()){			 
			StringBuilder query = new StringBuilder();
			query.append("SELECT id_mantenimiento, fecha, observaciones, id_ejemplar ")				 
				 .append("FROM mantenimientos ")
				 .append("WHERE id_mantenimiento=")
				 .append(idMantenimiento);
			
			ResultSet res = conexion.consultar(query.toString());
			
			try{
				if(res.next()){
					mantenimiento = new Mantenimientos();
					mantenimiento.setId_mantenimiento(res.getInt("id_mantenimiento"));
					mantenimiento.setFecha(res.getDate("fecha"));
					mantenimiento.setObservaciones(res.getString("observaciones"));
					mantenimiento.setId_ejemplar(res.getInt("id_ejemplar"));
				}
				res.close();
			}
			catch(Exception e){
				if(VariablesGlobales.DEBUG){
					System.out.println("Error: MantenimientosDAO.getMantenimiento() "+e.getMessage());
					e.printStackTrace();
				}
			}
		}
		
		return mantenimiento;
	}
	
	public ResultSet getListadoMantenimientos(Date fechaInicio, Date fechaFin, String cota, String ejemplar){
		ResultSet res = null;
		if(isConectado()){			
			StringBuilder query = new StringBuilder();
			query.append("SELECT m.id_mantenimiento id_mantenimiento, l.cota cota, e.numero numero, ")
				 .append("l.titulo titulo, m.fecha fecha, LPAD(id_mantenimiento, 6, '0') codigo ")				 
				 .append("FROM mantenimientos m, libros l, ejemplares e ")
				 .append("WHERE m.id_ejemplar=e.id_ejemplar AND e.id_libro=l.id_libro ")
				 .append("AND m.valido=1");
				 
			if(fechaInicio!=null){
				query.append(" AND fecha >='")
					 .append(UtilFechas.convertirFecha(fechaInicio, UtilFechas.YYYY_MM_DD))
					 .append("'");
			}			
			if(fechaFin!=null){
				query.append(" AND fecha <='")
					 .append(UtilFechas.convertirFecha(fechaFin, UtilFechas.YYYY_MM_DD))
					 .append("'");
			}
			
			if(!cota.equals("%")){
				query.append(" AND l.cota LIKE '")
					 .append(cota)
					 .append("'");
			}
			if(!ejemplar.equals("%")){
				query.append(" AND e.numero =")
					 .append(ejemplar);
			}
				 
			query.append(" ORDER BY m.id_mantenimiento ASC");
			
			res = conexion.consultar(query.toString()); 
		}
		
		return res;
	}
	
	public boolean guardarMantenimiento(Mantenimientos mantenimiento){
		boolean resultado = false;
		if(isConectado()){			 
			StringBuilder query = new StringBuilder();
			query.append("INSERT INTO mantenimientos(id_mantenimiento, fecha, observaciones, id_ejemplar) ")
				 .append("VALUES(")
				 .append(mantenimiento.getId_mantenimiento())
				 .append(", '").append(UtilFechas.convertirFecha(mantenimiento.getFecha(), UtilFechas.YYYY_MM_DD))
				 .append("', '").append(mantenimiento.getObservaciones())
				 .append("', ").append(mantenimiento.getId_ejemplar())
				 .append(")");
			
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
