package GestionarUsuarios;

import java.sql.ResultSet;
import Utilitario.Autenticacion;
import Utilitario.Item;
import Utilitario.Tablas.ModeloTablaListado;
import Consultas.UsuariosDAO;

public class GestorListadoUsuarios {
	public static int USUARIOS_SISTEMA = 1;
	public static int USUARIOS_BIBLIOTECA = 2;
	
	private int tipoUsuario;
	private VentanaListadoUsuariosSistema ventanaUsuariosSistema;
	private VentanaListadoUsuariosBiblioteca ventanaUsuariosBiblioteca;
	
	private Autenticacion autenticacion;
	
	public GestorListadoUsuarios(int tipoUsuario, Autenticacion autenticacion){
		this.tipoUsuario = tipoUsuario;
		this.autenticacion = autenticacion;
	}
	
	public void listarUsuarios(){
		if(tipoUsuario == USUARIOS_SISTEMA){
			listarUsuariosSistema();
		}
		if(tipoUsuario == USUARIOS_BIBLIOTECA){
			listarUsuariosBiblioteca();
		}
	}
	
	private void listarUsuariosSistema(){
		if(ventanaUsuariosSistema.getTxtUsuario().getText().length()==0){
			ventanaUsuariosSistema.getTxtUsuario().setText("*");
		}
		if(ventanaUsuariosSistema.getTxtNombres().getText().length()==0){
			ventanaUsuariosSistema.getTxtNombres().setText("*");
		}
		if(ventanaUsuariosSistema.getTxtApellidos().getText().length()==0){
			ventanaUsuariosSistema.getTxtApellidos().setText("*");
		}
		
		UsuariosDAO dao = new UsuariosDAO(autenticacion);
		ResultSet res = dao.getListadoUsuariosSistema(ventanaUsuariosSistema.getTxtUsuario().getText().replace('*', '%'),
													  ventanaUsuariosSistema.getTxtNombres().getText().replace('*', '%'),
													  ventanaUsuariosSistema.getTxtApellidos().getText().replace('*', '%'));
		
		try{
			int i = 0;
			ModeloTablaListado modelo = ventanaUsuariosSistema.getTablaListado().getModel();
			modelo.setRowCount(0);
			
			while(res!=null && res.next()){
				modelo.setRowCount(i+1);
				Item itm = new Item();
				itm.setValorBoolean(false);
				itm.setValorInt(res.getInt("id_usuario"));
				
				modelo.setValueAt(itm, i, 0);
				modelo.setValueAt(res.getString("usuario"), i, 1);
				modelo.setValueAt(res.getString("nombres"), i, 2);
				modelo.setValueAt(res.getString("apellidos"), i, 3);				
				
				i++;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		dao.desconectar();
	}
	
	private void listarUsuariosBiblioteca(){
		if(ventanaUsuariosBiblioteca.getTxtCedula().getText().length()==0){
			ventanaUsuariosBiblioteca.getTxtCedula().setText("*");
		}		
		if(ventanaUsuariosBiblioteca.getTxtNombres().getText().length()==0){
			ventanaUsuariosBiblioteca.getTxtNombres().setText("*");
		}
		if(ventanaUsuariosBiblioteca.getTxtApellidos().getText().length()==0){
			ventanaUsuariosBiblioteca.getTxtApellidos().setText("*");
		}
		
		
		UsuariosDAO dao = new UsuariosDAO(autenticacion);
		ResultSet res = dao.getListadoUsuariosBiblioteca(ventanaUsuariosBiblioteca.getTxtCedula().getText().replace('*', '%'),
														 ventanaUsuariosBiblioteca.getTxtNombres().getText().replace('*', '%'),
														 ventanaUsuariosBiblioteca.getTxtApellidos().getText().replace('*', '%'));
		
		try{
			int i = 0;
			ModeloTablaListado modelo = ventanaUsuariosBiblioteca.getTablaListado().getModel();
			modelo.setRowCount(0);
			
			while(res!=null && res.next()){
				modelo.setRowCount(i+1);
				Item itm = new Item();
				itm.setValorBoolean(false);
				itm.setValorInt(res.getInt("id_usuario"));
				
				modelo.setValueAt(itm, i, 0);
				modelo.setValueAt(res.getString("cedula"), i, 1);
				modelo.setValueAt(res.getString("nombres"), i, 2);
				modelo.setValueAt(res.getString("apellidos"), i, 3);
				if(res.getString("sexo").compareToIgnoreCase("M")==0){
					modelo.setValueAt("Masculino", i, 4);
				}else{
					modelo.setValueAt("Femenino", i, 4);
				}
				
				i++;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		dao.desconectar();
	}
	
	public void editarUsuario(int idUsuario){		
		if(tipoUsuario == USUARIOS_SISTEMA){
			GestorUsuarios g = new GestorUsuarios(tipoUsuario, autenticacion);
			VentanaEditarUsuarioSistema v = new VentanaEditarUsuarioSistema(g, ventanaUsuariosSistema.getDesktopPane());
			g.setVentanaEditar(v);
			
			g.prepararEditar(idUsuario);
		}
		if(tipoUsuario == USUARIOS_BIBLIOTECA){
			GestorUsuarios g = new GestorUsuarios(tipoUsuario, autenticacion);
			VentanaEditarUsuarioBiblioteca v = new VentanaEditarUsuarioBiblioteca(g, ventanaUsuariosBiblioteca.getDesktopPane());
			g.setVentanaEditar(v);
			
			g.prepararEditar(idUsuario);
		}
	}
	
	public void crearUsuario(){
		if(tipoUsuario == USUARIOS_SISTEMA){
			GestorUsuarios g = new GestorUsuarios(tipoUsuario, autenticacion);
			VentanaEditarUsuarioSistema v = new VentanaEditarUsuarioSistema(g, ventanaUsuariosSistema.getDesktopPane());
			g.setVentanaEditar(v);
			
			g.prepararCrear();
		}
		if(tipoUsuario == USUARIOS_BIBLIOTECA){
			GestorUsuarios g = new GestorUsuarios(tipoUsuario, autenticacion);
			VentanaEditarUsuarioBiblioteca v = new VentanaEditarUsuarioBiblioteca(g, ventanaUsuariosBiblioteca.getDesktopPane());
			g.setVentanaEditar(v);
			
			g.prepararCrear();
		}
	}
	
	public void eliminarUsuario(int idUsuario){
		if(tipoUsuario == USUARIOS_SISTEMA){
			
		}
		if(tipoUsuario == USUARIOS_BIBLIOTECA){
			
		}
	}
	
	
	public void setVentana(VentanaListadoUsuariosSistema ventana){
		this.ventanaUsuariosSistema = ventana;
	}
	
	public void setVentana(VentanaListadoUsuariosBiblioteca ventana){
		this.ventanaUsuariosBiblioteca= ventana;
	}
}
