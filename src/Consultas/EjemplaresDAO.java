package Consultas;

import java.sql.ResultSet;

import ConexionBD.Conexion;
import Entidades.ejemplares;
import Entidades.libros;
import Utilitario.Autenticacion;
import Utilitario.VariablesGlobales;

public class EjemplaresDAO {
	private Autenticacion autenticacion;
	private Conexion conexion;
	
	public EjemplaresDAO(Autenticacion autenticacion){
		this.autenticacion = autenticacion;
		conexion = new Conexion(autenticacion);
	}
	
	public ResultSet getListadoEjemplares(String cota, String titulo, String ejemplar){
		ResultSet res = null;
		if(isConectado()){			
			StringBuilder query = new StringBuilder();
			query.append("SELECT l.id_libro id_libro, l.cota cota, e.numero numero, l.titulo titulo, ")
				 .append("l.estante estante, s.descripcion sala, e.id_ejemplar id_ejemplar, ")
				 .append("CASE(LOWER(e.estado)) WHEN 'existe' THEN 'Disponible' WHEN 'prestado' THEN 'Prestado' ELSE '---' END disponibilidad ")
				 .append("FROM libros l ")
			   	 .append("LEFT JOIN salas s ON s.id_sala = l.id_sala, ")
			   	 .append("ejemplares e ")
			   	 .append("WHERE l.id_libro=e.id_libro");
			
			if(!cota.equals("%")){
				query.append(" AND l.cota LIKE '").append(cota).append("'");
			}
			if(!titulo.equals("%")){
				query.append(" AND l.titulo LIKE '").append(titulo).append("'");
			}
			if(!ejemplar.equals("%")){
				query.append(" AND e.numero = ").append(ejemplar);
			}
			
			query.append(" ORDER BY l.cota, e.numero ASC");
			
			res = conexion.consultar(query.toString()); 
		}
		
		return res;
	}
	
	public ResultSet getListadoEjemplares2(String cota, String titulo){
		ResultSet res = null;
		if(isConectado()){			
			StringBuilder query = new StringBuilder();
			query.append("SELECT l.id_libro id_libro, l.cota cota, l.titulo titulo, ")
				 .append("l.estante estante, s.descripcion sala ")
				 .append("FROM libros l ")
			   	 .append("LEFT JOIN salas s ON s.id_sala = l.id_sala ");
			
			if(!cota.equals("%")){
				query.append(" AND l.cota LIKE '").append(cota).append("'");
			}
			if(!titulo.equals("%")){
				query.append(" AND l.titulo LIKE '").append(titulo).append("'");
			}
			/*if(!ejemplar.equals("%")){
				query.append(" AND e.numero = ").append(ejemplar);
			}*/
			
			query.append("ORDER BY l.cota ASC");
			
			res = conexion.consultar(query.toString()); 
		}
		
		return res;
	}
	
	public libros getLibro(int id_libro){
		libros libro = null;		
		
		if(isConectado()){			 
			StringBuilder query = new StringBuilder();
			query.append("SELECT id_libro, titulo, cota, estante ")				 
				 .append("FROM libros ")
				 .append("WHERE id_libro=")
				 .append(id_libro);
			
			ResultSet res = conexion.consultar(query.toString());
			
			try{
				if(res.next()){
					libro = new libros(autenticacion);
					libro.setCota(res.getString("cota"));
					libro.setTitulo(res.getString("titulo"));					
				}
				res.close();
			}
			catch(Exception e){
				if(VariablesGlobales.DEBUG){
					System.out.println("Error: EjemplaresDAO.getLibro() "+e.getMessage());
					e.printStackTrace();
				}
			}
		}
		return libro;
	}
	
	public ejemplares getEjemplar(int id_ejemplar){
		ejemplares ejemplar = null;		
		
		if(isConectado()){			 
			StringBuilder query = new StringBuilder();
			query.append("SELECT id_libro, id_ejemplar, numero ")				 
				 .append("FROM ejemplares ")
				 .append("WHERE id_ejemplar=")
				 .append(id_ejemplar);
			
			ResultSet res = conexion.consultar(query.toString());
			
			try{
				if(res.next()){
					ejemplar = new ejemplares(autenticacion);
					ejemplar.setId_libro(res.getInt("id_libro"));
					ejemplar.setNumero(res.getInt("numero"));
					
				}
				res.close();
			}
			catch(Exception e){
				if(VariablesGlobales.DEBUG){
					System.out.println("Error: EjemplaresDAO.getEjemplar() "+e.getMessage());
					e.printStackTrace();
				}
			}
		}
		return ejemplar;
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
