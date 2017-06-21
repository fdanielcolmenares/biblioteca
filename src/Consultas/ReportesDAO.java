package Consultas;

import java.util.Date;

import Utilitario.UtilFechas;
import Utilitario.VariablesGlobales;

public class ReportesDAO {
	public ReportesDAO(){		
	}
	
	public String getQueryListadoUsuarios(){
		StringBuilder query = new StringBuilder();
		query.append("SELECT cedula, nombres, apellidos, fecha_nacimiento, ")
			 .append("CASE(LOWER(sexo)) WHEN 'm' THEN 'Masculino' ELSE 'Femenino' END sexo, ")
			 .append("telefono1 ")
			 .append("FROM usuarios u ")
			 .append("WHERE tipo_usuario=")
			 .append(VariablesGlobales.USUARIO_BIBLIOTECA);
		
		return query.toString();
	}
	
	public String getQueryRegistros(Date fechaInicio, Date fechaFin){
		StringBuilder query = new StringBuilder();
		query.append("SELECT ")
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
		
		query.append(" ORDER BY r.id_registro ASC");
		
		return query.toString();
	}
}
