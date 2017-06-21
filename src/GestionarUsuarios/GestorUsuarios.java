package GestionarUsuarios;

import java.util.ArrayList;
import java.util.List;
import Consultas.UsuariosDAO;
import Entidades.Referencias;
import Entidades.Usuarios;
import Utilitario.Autenticacion;
import Utilitario.Item;
import Utilitario.MostrarMensajes;
import Utilitario.VariablesGlobales;

public class GestorUsuarios {
	public static int USUARIOS_SISTEMA = 1;
	public static int USUARIOS_BIBLIOTECA = 2;	
	private int tipoUsuario;	
	private Autenticacion autenticacion;
	private Usuarios seleccionado;
	private VentanaEditarUsuarioSistema ventanaUsuarioSistema;
	private VentanaEditarUsuarioBiblioteca ventanaUsuarioBiblioteca;
	private String accion;
	
	public GestorUsuarios(int tipoUsuario, Autenticacion autenticacion){
		this.tipoUsuario = tipoUsuario;
		this.autenticacion = autenticacion;
	}
	
	public void prepararGuardar(){
		StringBuilder sb = new StringBuilder();
		getDatos();
		
		if(seleccionado.getNombres().length() == 0){
			sb.append("- Debe ingresar el nombre del usuario\n");
		}
		if(seleccionado.getApellidos().length() == 0){
			sb.append("- Debe ingresar el apellido del usuario\n");
		}
		
		if(tipoUsuario == USUARIOS_SISTEMA){
			if(seleccionado.getCedula().length() == 0){
				sb.append("- Debe ingresar la cédula del usuario\n");				
			}
			if(seleccionado.getUsuario().length() == 0){
				sb.append("- Debe ingresar el usuario\n");				
			}
			if(String.valueOf(ventanaUsuarioSistema.getTxtClave1().getPassword()).length() == 0){
				sb.append("- Debe ingresar la contraseña del usuario\n");				
			}
			if(String.valueOf(ventanaUsuarioSistema.getTxtClave2().getPassword()).length() == 0){
				sb.append("- Debe confirmar la contraseña del usuario\n");				
			}
			if(String.valueOf(ventanaUsuarioSistema.getTxtClave1().getPassword()).length() > 0 &&
					String.valueOf(ventanaUsuarioSistema.getTxtClave2().getPassword()).length() > 0 &&
					String.valueOf(ventanaUsuarioSistema.getTxtClave1().getPassword()).compareTo(String.valueOf(ventanaUsuarioSistema.getTxtClave1().getPassword()))!=0){
				sb.append("- Las contraseñas no coinciden\n");
			}
			
			//TODO: Validar que el usuario no exista previamente
		}
		
		if(tipoUsuario == USUARIOS_BIBLIOTECA){
			if(seleccionado.getFecha_nacimiento() == null){
				if(ventanaUsuarioBiblioteca.getTxtFechaNacimiento().getDate()== null){
					sb.append("- Debe ingresar la fecha de nacimiento\n");
				}
			}
		}
		
		if(sb.toString().length() > 0){
			MostrarMensajes.mostrarMensaje(sb.toString(), MostrarMensajes.MENSAJE_ERROR);
			return ;
		}
		
		guardar();
	}
	
	private void guardar(){		
		UsuariosDAO dao = new UsuariosDAO(autenticacion);		
		boolean resultado = false;
		
		if(tipoUsuario == USUARIOS_SISTEMA){
			if(accion.equals("C")){
				seleccionado.setId_usuario(dao.buscarNuevoId());
				resultado = dao.guardarUsuarioSistema(seleccionado);
			}
			if(accion.equals("E")){
				resultado = dao.actualizarUsuarioSistema(seleccionado);
			}
		}
		
		if(tipoUsuario == USUARIOS_BIBLIOTECA){
			if(accion.equals("C")){
				seleccionado.setId_usuario(dao.buscarNuevoId());
				resultado = dao.guardarUsuarioBiblioteca(seleccionado);
			}
			if(accion.equals("E")){
				resultado = dao.actualizarUsuarioBilioteca(seleccionado);
			}
		}
		
		dao.desconectar();
		
		if(resultado){
			MostrarMensajes.mostrarMensaje("Operación exitosa", MostrarMensajes.MENSAJE_EXITO);
			accion = "E";
		}else{
			MostrarMensajes.mostrarMensaje("Ocurrió un error al guardar", MostrarMensajes.MENSAJE_ERROR);
		}
	}
	
	public void prepararEditar(int id_usuario){
		accion = "E";
		UsuariosDAO dao = new UsuariosDAO(autenticacion);
		if(tipoUsuario == USUARIOS_SISTEMA){
			seleccionado = dao.getUsuarioSistema(id_usuario);
		}
		if(tipoUsuario == USUARIOS_BIBLIOTECA){
			seleccionado = dao.getUsuarioBiblioteca(id_usuario);
		}
		dao.desconectar();
		setDatos();
	}
	
	public void prepararCrear(){
		accion = "C";
		seleccionado = new Usuarios();
		seleccionado.setReferencia1(new Referencias());
		seleccionado.setReferencia2(new Referencias());
		seleccionado.setFecha_nacimiento(null);
		setDatos();
		
		if(tipoUsuario == USUARIOS_SISTEMA){
			ventanaUsuarioSistema.getBtnEliminar().setVisible(false);
		}
		
		if(tipoUsuario == USUARIOS_BIBLIOTECA){			
			ventanaUsuarioBiblioteca.getBtnEliminar().setVisible(false);
		}
	}
	
	public void getDatos(){		
		if(tipoUsuario == USUARIOS_SISTEMA){
			seleccionado.setNombres(ventanaUsuarioSistema.getTxtNombre().getText());
			seleccionado.setApellidos(ventanaUsuarioSistema.getTxtApellidos().getText());
			seleccionado.setCedula(ventanaUsuarioSistema.getTxtCedula().getText());
			seleccionado.setUsuario(ventanaUsuarioSistema.getTxtUsuario().getText());	
			seleccionado.setClave(String.valueOf(ventanaUsuarioSistema.getTxtClave1().getPassword()));
			seleccionado.setSexo(((Item)ventanaUsuarioSistema.getCbxSexo().getSelectedItem()).getValorString());
			seleccionado.setTipo_usuario(((Item)ventanaUsuarioSistema.getCbxTipoUsuario().getSelectedItem()).getValorInt());
		}
		if(tipoUsuario == USUARIOS_BIBLIOTECA){
			seleccionado.setNombres(ventanaUsuarioBiblioteca.getTxtNombre().getText());
			seleccionado.setApellidos(ventanaUsuarioBiblioteca.getTxtApellidos().getText());
			seleccionado.setCedula(ventanaUsuarioBiblioteca.getTxtCedula().getText());
			seleccionado.setSexo(((Item)ventanaUsuarioBiblioteca.getCbxSexo().getSelectedItem()).getValorString());
			seleccionado.setFecha_nacimiento(ventanaUsuarioBiblioteca.getTxtFechaNacimiento().getDate());
			seleccionado.setProfesion(ventanaUsuarioBiblioteca.getTxtProfesion().getText());
			seleccionado.setInstitucion(ventanaUsuarioBiblioteca.getTxtInstitucion().getText());
			seleccionado.setTelefono1(ventanaUsuarioBiblioteca.getTxtTelefono1().getText());
			seleccionado.setTelefono2(ventanaUsuarioBiblioteca.getTxtTelefono2().getText());
			seleccionado.setCarnet(ventanaUsuarioBiblioteca.getTxtCarnet().getText());
			seleccionado.setDireccion(ventanaUsuarioBiblioteca.getTxtDireccion().getText());
			seleccionado.setTipo_usuario(VariablesGlobales.USUARIO_BIBLIOTECA);			
			
			seleccionado.getReferencia1().setCedula(ventanaUsuarioBiblioteca.getTxtCedulaReferencia1().getText());
			seleccionado.getReferencia1().setNombres(ventanaUsuarioBiblioteca.getTxtNombresReferencia1().getText());
			seleccionado.getReferencia1().setApellidos(ventanaUsuarioBiblioteca.getTxtApellidosReferencia1().getText());
			seleccionado.getReferencia1().setTelefono(ventanaUsuarioBiblioteca.getTxtTelefonoReferencia1().getText());
			seleccionado.getReferencia1().setDireccion(ventanaUsuarioBiblioteca.getTxtDireccionReferencia1().getText());
			
			seleccionado.getReferencia2().setCedula(ventanaUsuarioBiblioteca.getTxtCedulaReferencia2().getText());
			seleccionado.getReferencia2().setNombres(ventanaUsuarioBiblioteca.getTxtNombresReferencia2().getText());
			seleccionado.getReferencia2().setApellidos(ventanaUsuarioBiblioteca.getTxtApellidosReferencia2().getText());
			seleccionado.getReferencia2().setTelefono(ventanaUsuarioBiblioteca.getTxtTelefonoReferencia2().getText());
			seleccionado.getReferencia2().setDireccion(ventanaUsuarioBiblioteca.getTxtDireccionReferencia2().getText());
		}
	}
	
	public void setDatos(){
		if(tipoUsuario == USUARIOS_SISTEMA){
			ventanaUsuarioSistema.getTxtNombre().setText(seleccionado.getNombres());
			ventanaUsuarioSistema.getTxtApellidos().setText(seleccionado.getApellidos());
			ventanaUsuarioSistema.getTxtCedula().setText(seleccionado.getCedula());
			ventanaUsuarioSistema.getTxtUsuario().setText(seleccionado.getUsuario());
			ventanaUsuarioSistema.getTxtClave1().setText(seleccionado.getClave());
			ventanaUsuarioSistema.getTxtClave2().setText(seleccionado.getClave());			
			
			if(seleccionado.getSexo().compareToIgnoreCase("F") == 0){
				ventanaUsuarioSistema.getCbxSexo().setSelectedIndex(1);
			}else{
				ventanaUsuarioSistema.getCbxSexo().setSelectedIndex(0);
			}
			
			for(int i=0; i<ventanaUsuarioSistema.getCbxTipoUsuario().getModel().getSize(); i++){
				if(((Item)ventanaUsuarioSistema.getCbxTipoUsuario().getModel().getElementAt(i)).getValorInt() == seleccionado.getTipo_usuario()){
					ventanaUsuarioSistema.getCbxTipoUsuario().setSelectedIndex(i);
				}
			}
		}
		
		if(tipoUsuario == USUARIOS_BIBLIOTECA){
			ventanaUsuarioBiblioteca.getTxtNombre().setText(seleccionado.getNombres());
			ventanaUsuarioBiblioteca.getTxtApellidos().setText(seleccionado.getApellidos());
			ventanaUsuarioBiblioteca.getTxtCedula().setText(seleccionado.getCedula());			
			ventanaUsuarioBiblioteca.getTxtFechaNacimiento().setDate(seleccionado.getFecha_nacimiento());			
			ventanaUsuarioBiblioteca.getTxtProfesion().setText(seleccionado.getProfesion());
			ventanaUsuarioBiblioteca.getTxtInstitucion().setText(seleccionado.getInstitucion());
			ventanaUsuarioBiblioteca.getTxtTelefono1().setText(seleccionado.getTelefono1());
			ventanaUsuarioBiblioteca.getTxtTelefono2().setText(seleccionado.getTelefono2());			
			ventanaUsuarioBiblioteca.getTxtCarnet().setText(seleccionado.getCarnet());			
			ventanaUsuarioBiblioteca.getTxtDireccion().setText(seleccionado.getDireccion());
			
			if(seleccionado.getSexo().compareToIgnoreCase("F") == 0){
				ventanaUsuarioBiblioteca.getCbxSexo().setSelectedIndex(1);
			}else{
				ventanaUsuarioBiblioteca.getCbxSexo().setSelectedIndex(0);
			}
			
			ventanaUsuarioBiblioteca.getTxtCedulaReferencia1().setText(seleccionado.getReferencia1().getCedula());
			ventanaUsuarioBiblioteca.getTxtNombresReferencia1().setText(seleccionado.getReferencia1().getNombres());
			ventanaUsuarioBiblioteca.getTxtApellidosReferencia1().setText(seleccionado.getReferencia1().getApellidos());
			ventanaUsuarioBiblioteca.getTxtTelefonoReferencia1().setText(seleccionado.getReferencia1().getTelefono());
			ventanaUsuarioBiblioteca.getTxtDireccionReferencia1().setText(seleccionado.getReferencia1().getDireccion());
			
			ventanaUsuarioBiblioteca.getTxtCedulaReferencia2().setText(seleccionado.getReferencia2().getCedula());
			ventanaUsuarioBiblioteca.getTxtNombresReferencia2().setText(seleccionado.getReferencia2().getNombres());
			ventanaUsuarioBiblioteca.getTxtApellidosReferencia2().setText(seleccionado.getReferencia2().getApellidos());
			ventanaUsuarioBiblioteca.getTxtTelefonoReferencia2().setText(seleccionado.getReferencia2().getTelefono());
			ventanaUsuarioBiblioteca.getTxtDireccionReferencia2().setText(seleccionado.getReferencia2().getDireccion());
			
		}
	}
	
	public List<Item> getListadoTiposUsuario(){
		List<Item> items = new ArrayList<Item>();
		
		Item itm = new Item("Administrador", VariablesGlobales.ADMINISTRADOR);	
		items.add(itm);
		
		itm = new Item("Registros de acceso", VariablesGlobales.USUARIO_REGISTROS_ACCESO);	
		items.add(itm);
		
		itm = new Item("Operador", VariablesGlobales.USUARIO_REGISTROS_ACCESO);	
		items.add(itm);
		
		return items;
	}
	
	public void setVentanaEditar(VentanaEditarUsuarioSistema ventana){
		this.ventanaUsuarioSistema = ventana;
	}
	
	public void setVentanaEditar(VentanaEditarUsuarioBiblioteca ventana){
		this.ventanaUsuarioBiblioteca = ventana;
	}
	
}
