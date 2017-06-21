package Consultas;

import java.sql.ResultSet;

import org.apache.commons.lang.StringEscapeUtils;

import Utilitario.Autenticacion;
import Utilitario.UtilFechas;
import Utilitario.VariablesGlobales;
import ConexionBD.Conexion;
import Entidades.Referencias;
import Entidades.Usuarios;

public class UsuariosDAO {
	private Autenticacion autenticacion;
	private Conexion conexion;
	
	public UsuariosDAO(Autenticacion autenticacion){
		this.autenticacion = autenticacion;
		conexion = new Conexion(autenticacion);
	}
	
	public Usuarios validarUsuario(String _usuario, String _clave){ 
		Usuarios usuario = null;
		try{
			if(isConectado()){
				StringBuilder query = new StringBuilder();
				query.append("SELECT u.id_usuario AS id_usuario, u.nombres AS nombres,")
					 .append("u.apellidos AS apellidos, u.tipo_usuario AS tipo_usuario ")					 
					 .append("FROM usuarios u ")
					 .append("WHERE u.usuario = '")
					 .append(StringEscapeUtils.escapeSql(_usuario))
					 .append("' AND u.clave = '")
					 .append(StringEscapeUtils.escapeSql(_clave))
					 .append("'");
				
				ResultSet res = conexion.consultar(query.toString());
				if(res != null){
					if(res.next()){
						usuario = new Usuarios();
						usuario.setId_usuario(res.getInt("id_usuario"));
						usuario.setNombres(res.getString("nombres"));
						usuario.setApellidos(res.getString("apellidos"));
						usuario.setTipo_usuario(res.getInt("tipo_usuario"));						
					}					
				}
			}
		}
		catch(Exception e){
			if(VariablesGlobales.DEBUG){
				System.out.println("[ERROR] UsuariosDAO.validarUsuario(): "+e.getMessage());
				e.printStackTrace();
			}
		}
		finally{
			conexion.desconectar();
		}
		
		return usuario;
	}

	public ResultSet getListadoUsuariosSistema(String usuario, String nombres, String apellidos){
		ResultSet res = null;
		if(isConectado()){			
			StringBuilder query = new StringBuilder();
			query.append("SELECT id_usuario, nombres, apellidos, ")
				 .append("cedula, usuario, clave, tipo_usuario ")
				 .append("FROM usuarios ")
				 .append("WHERE tipo_usuario!=").append(VariablesGlobales.USUARIO_BIBLIOTECA);
			
			if(!usuario.equals("%")){
				query.append(" AND usuario LIKE '").append(usuario).append("'");
			}
			if(!nombres.equals("%")){
				query.append(" AND nombres LIKE '").append(nombres).append("'");
			}
			if(!apellidos.equals("%")){
				query.append(" AND apellidos LIKE '").append(apellidos).append("'");
			}
			
			query.append(" ORDER BY usuario, nombres, apellidos ASC");
			
			res = conexion.consultar(query.toString()); 
		}
		
		return res;
	}
	
	public ResultSet getListadoUsuariosBiblioteca(String cedula, String nombres, String apellidos){
		ResultSet res = null;
		if(isConectado()){			
			StringBuilder query = new StringBuilder();
			query.append("SELECT id_usuario, nombres, apellidos,")
				 .append(" cedula, sexo")
				 .append(" FROM usuarios")
				 .append(" WHERE tipo_usuario=").append(VariablesGlobales.USUARIO_BIBLIOTECA);
				 
			if(!cedula.equals("%")){
				query.append(" AND cedula LIKE '").append(cedula).append("'");
			}
			if(!nombres.equals("%")){
				query.append(" AND nombres LIKE '").append(nombres).append("'");
			}
			if(!apellidos.equals("%")){
				query.append(" AND apellidos LIKE '").append(apellidos).append("'");
			}
			
			query.append(" ORDER BY cedula, nombres, apellidos ASC");
			
			res = conexion.consultar(query.toString()); 
		}
		
		return res;
	}
	
	public Usuarios getUsuarioSistema(int idUsuario){
		Usuarios usuario = null;		
		
		if(isConectado()){			 
			StringBuilder query = new StringBuilder();
			query.append("SELECT id_usuario, nombres, apellidos, ")
				 .append("cedula, sexo, usuario, clave, tipo_usuario ")
				 .append("FROM usuarios ")
				 .append("WHERE id_usuario=")
				 .append(idUsuario);
			
			ResultSet res = conexion.consultar(query.toString());
			
			try{
				if(res.next()){
					usuario = new Usuarios();
					usuario.setId_usuario(res.getInt("id_usuario"));
					usuario.setNombres(res.getString("nombres"));
					usuario.setApellidos(res.getString("apellidos"));
					usuario.setCedula(res.getString("cedula"));
					usuario.setSexo(res.getString("sexo"));
					usuario.setUsuario(res.getString("usuario"));
					usuario.setClave(res.getString("clave"));
					usuario.setTipo_usuario(res.getInt("tipo_usuario"));					
				}
				res.close();
			}
			catch(Exception e){
				if(VariablesGlobales.DEBUG){
					System.out.println("Error: UsuariosDAO.getUsuarioSistema() "+e.getMessage());
					e.printStackTrace();
				}
			}
		}
		
		return usuario;
	}
	
	public Usuarios getUsuarioBiblioteca(int id_usuario){
		Usuarios usuario = null;		
		
		if(isConectado()){			 
			StringBuilder query = new StringBuilder();
			query.append("SELECT id_usuario, nombres, apellidos, ")
				 .append("cedula, sexo, fecha_nacimiento, direccion, ")
				 .append("profesion, institucion, telefono1, telefono2, carnet, tipo_usuario ")
				 .append("FROM usuarios ")
				 .append("WHERE id_usuario=")
				 .append(id_usuario);
			
			ResultSet res = conexion.consultar(query.toString());
			
			try{
				if(res.next()){
					usuario = new Usuarios();
					usuario.setReferencia1(new Referencias());
					usuario.setReferencia2(new Referencias());
					
					usuario.setId_usuario(res.getInt("id_usuario"));
					usuario.setNombres(res.getString("nombres"));
					usuario.setApellidos(res.getString("apellidos"));
					usuario.setCedula(res.getString("cedula"));
					usuario.setSexo(res.getString("sexo"));
					usuario.setFecha_nacimiento(res.getDate("fecha_nacimiento"));
					usuario.setDireccion(res.getString("direccion"));
					usuario.setProfesion(res.getString("profesion"));
					usuario.setProfesion(res.getString("institucion"));
					usuario.setTelefono1(res.getString("telefono1"));
					usuario.setTelefono2(res.getString("telefono2"));
					usuario.setCarnet(res.getString("carnet"));
					usuario.setTipo_usuario(res.getInt("tipo_usuario"));
					
					ReferenciasDAO dao = new ReferenciasDAO(autenticacion);
					dao.getReferenciasUsuario(usuario.getId_usuario(), usuario.getReferencia1(), usuario.getReferencia2());
					dao.desconectar();
				}
				res.close();
			}
			catch(Exception e){
				if(VariablesGlobales.DEBUG){
					System.out.println("Error: UsuariosDAO.getUsuarioSistema() "+e.getMessage());
					e.printStackTrace();
				}
			}
		}
		
		return usuario;
	}
	
	public int buscarNuevoId(){
		int id = 1;
		if(isConectado()){			
			StringBuilder query = new StringBuilder();
			query.append("SELECT IFNULL(")
				 .append("(SELECT MAX(id_usuario)+1 FROM usuarios)")
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
	
	public boolean guardarUsuarioSistema(Usuarios usuario){
		boolean resultado = false;
		if(isConectado()){			 
			StringBuilder query = new StringBuilder();
			query.append("INSERT INTO usuarios(id_usuario, nombres, apellidos, ")
				 .append("cedula, sexo, usuario, clave, tipo_usuario) ")
				 .append("VALUES(")
				 .append(usuario.getId_usuario())
				 .append(", '").append(usuario.getNombres())
				 .append("', '").append(usuario.getApellidos())
				 .append("', '").append(usuario.getCedula())
				 .append("', '").append(usuario.getSexo())
				 .append("', '").append(usuario.getUsuario())
				 .append("', '").append(usuario.getClave())
				 .append("', ").append(usuario.getTipo_usuario())
				 .append(")");
			
			resultado = conexion.actualizar(query.toString());			
		}
		
		return resultado;
	}
	
	public boolean guardarUsuarioBiblioteca(Usuarios usuario){
		boolean resultado = false;
		if(isConectado()){			 
			StringBuilder query = new StringBuilder();
			query.append("INSERT INTO usuarios(id_usuario, nombres, apellidos, ")
				 .append("cedula, sexo, fecha_nacimiento, direccion, profesion, ")
				 .append("institucion, telefono1, telefono2, carnet, tipo_usuario) ")
				 .append("VALUES(")
				 .append(usuario.getId_usuario())
				 .append(", '").append(usuario.getNombres())
				 .append("', '").append(usuario.getApellidos())
				 .append("', '").append(usuario.getCedula())
				 .append("', '").append(usuario.getSexo())
				 .append("', '").append(UtilFechas.convertirFecha(usuario.getFecha_nacimiento(), UtilFechas.YYYY_MM_DD))
				 .append("', '").append(usuario.getDireccion())
				 .append("', '").append(usuario.getProfesion())
				 .append("', '").append(usuario.getInstitucion())
				 .append("', '").append(usuario.getTelefono1())
				 .append("', '").append(usuario.getTelefono2())				 
				 .append("', '").append(usuario.getCarnet())
				 .append("', ").append(usuario.getTipo_usuario())
				 .append(")");
			
			resultado = conexion.actualizar(query.toString());
			
			if(resultado){
				ReferenciasDAO dao = new ReferenciasDAO(autenticacion);
				usuario.getReferencia1().setId_referencia(dao.buscarNuevoId());
				usuario.getReferencia1().setId_usuario(usuario.getId_usuario());
				dao.guardarReferencia(usuario.getReferencia1());
				
				usuario.getReferencia2().setId_referencia(dao.buscarNuevoId());
				usuario.getReferencia2().setId_usuario(usuario.getId_usuario());
				dao.guardarReferencia(usuario.getReferencia2());
				
				dao.desconectar();
			}
		}
		
		return resultado;
	}
	
	public boolean actualizarUsuarioSistema(Usuarios usuario){
		boolean resultado = false;
		if(isConectado()){			 
			StringBuilder query = new StringBuilder();
			query.append("UPDATE usuarios SET ")
				 .append("nombres='").append(usuario.getNombres())
				 .append("', apellidos='").append(usuario.getApellidos())
				 .append("', cedula='").append(usuario.getCedula())
				 .append("', sexo='").append(usuario.getSexo())
				 .append("', usuario='").append(usuario.getUsuario())
				 .append("', clave='").append(usuario.getClave())
				 .append("', tipo_usuario=").append(usuario.getTipo_usuario())
				 .append(" WHERE id_usuario=").append(usuario.getId_usuario());
			
			resultado = conexion.actualizar(query.toString());
		}
		
		return resultado;
	}	
	
	public boolean actualizarUsuarioBilioteca(Usuarios usuario){
		boolean resultado = false;
		if(isConectado()){			 
			StringBuilder query = new StringBuilder();
			query.append("UPDATE usuarios SET ")
				 .append("nombres='").append(usuario.getNombres())
				 .append("', apellidos='").append(usuario.getApellidos())
				 .append("', cedula='").append(usuario.getCedula())
				 .append("', sexo='").append(usuario.getSexo())
				 .append("', direccion='").append(usuario.getDireccion())
				 .append("', profesion='").append(usuario.getProfesion())
				 .append("', institucion='").append(usuario.getInstitucion())
				 .append("', telefono1='").append(usuario.getTelefono1())
				 .append("', telefono2='").append(usuario.getTelefono2())
				 .append("', carnet='").append(usuario.getCarnet())
				 .append("', fecha_nacimiento='").append(UtilFechas.convertirFecha(usuario.getFecha_nacimiento(), UtilFechas.YYYY_MM_DD))
				 .append("' WHERE id_usuario=").append(usuario.getId_usuario());
			  
			resultado = conexion.actualizar(query.toString());
			
			ReferenciasDAO dao = new ReferenciasDAO(autenticacion);
			dao.actualizarReferencia(usuario.getReferencia1());
			dao.actualizarReferencia(usuario.getReferencia2());
			dao.desconectar();
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
