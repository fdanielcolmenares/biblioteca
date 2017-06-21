package GestionarVentanaPrincipal;

import java.awt.Desktop;
import java.io.File;
import java.util.Properties;
import javax.swing.JInternalFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import sanciones.gestorSanciones;
import sanciones.panelsanciones;
import com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel;
import com.sun.java.swing.plaf.windows.WindowsLookAndFeel;
import ConexionBD.Conexion;
import GestionLibros.GestorLibros;
import GestionLibros.GestorSalas;
import GestionLibros.VentanaLibros;
import GestionLibros.administrarSalas;
import GestionLibros.editarLibros;
import GestionarAccesoAplicacion.VentanaIniciarSesion;
import GestionarAplicacionRegistro.GestorCrearRegistro;
import GestionarAplicacionRegistro.GestorListadoRegistros;
import GestionarAplicacionRegistro.VentanaCrearRegistro;
import GestionarAplicacionRegistro.VentanaListadoRegistros;
import GestionarEjemplares.GestorListadoEjemplares;
import GestionarEjemplares.VentanaListadoEjemplares;
import GestionarMantenimientos.GestorListadoMantenimientos;
import GestionarMantenimientos.GestorMantenimientos;
import GestionarMantenimientos.VentanaAgregarMantenimiento;
import GestionarMantenimientos.VentanaListadoMantenimientos;
import GestionarMisiones.GestorListadoMisiones;
import GestionarMisiones.VentanaListadoMisiones;
import GestionarPrestamos2.GestorListadoPrestamos2;
import GestionarPrestamos2.VentanaListadoPrestamos2;
import GestionarReportes.VentanaReportesRegistros;
import GestionarReportes.VentanaReportesUsuarios;
import GestionarUsuarios.GestorListadoUsuarios;
import GestionarUsuarios.GestorUsuarios;
import GestionarUsuarios.VentanaEditarUsuarioBiblioteca;
import GestionarUsuarios.VentanaListadoUsuariosBiblioteca;
import GestionarUsuarios.VentanaListadoUsuariosSistema;
import Utilitario.Autenticacion;
import Utilitario.MostrarMensajes;
import Utilitario.Path;
import Utilitario.UtilArchivos;
import Utilitario.VariablesGlobales;

public class GestorVentanaPrincipal {
	private VentanaPrincipal ventana;
	private boolean conectadoServidor;
	private boolean autenticado;
	private Autenticacion autenticacion;
	private Properties preferencias;
	
	public GestorVentanaPrincipal(VentanaPrincipal ventana, Autenticacion autenticacion, Properties preferencias){
		this.ventana = ventana;
		this.autenticacion = autenticacion;
		this.preferencias = preferencias;
		conectadoServidor = false;	
		
		cargarPreferencias(this.preferencias);
		
		setConectadoServidor(probarConexion(autenticacion));
		ventana.getVentana().setVisible(true);
		setAutenticado(false);
	}
	
	public void probarBoton(){
		/*GestorListadoUsuarios g = new GestorListadoUsuarios(GestorListadoUsuarios.USUARIOS_BIBLIOTECA, autenticacion);
		VentanaListadoUsuariosBiblioteca v = new VentanaListadoUsuariosBiblioteca(g, ventana.getDesktopPanel(), true);		
		g.setVentana(v);
		g.listarUsuarios();		
		ventana.getDesktopPanel().add(v.getVentana());
		v.getVentana().setVisible(true);*/
		
		/*GestorListadoEjemplares g = new GestorListadoEjemplares(autenticacion);
		VentanaListadoEjemplares v = new VentanaListadoEjemplares(g, ventana.getDesktopPanel());		
		g.setVentana(v);
		g.listarEjemplares();		
		ventana.getDesktopPanel().add(v.getVentana());*/
		
		try{
			File ayuda = new File(Path.getPath("Archivos/Ayuda.pdf"));
			if(ayuda.exists()){
				Desktop.getDesktop().open(ayuda);
			}else{
				MostrarMensajes.mostrarMensaje("No existe el archivo de ayuda", MostrarMensajes.MENSAJE_ERROR);
			}
		}
		catch(Exception e){			
			MostrarMensajes.mostrarMensaje("No se pudo cargar la ayuda", MostrarMensajes.MENSAJE_ERROR);
		}
		
	}
	
	public void reportesUsuarios(){
		new VentanaReportesUsuarios(autenticacion, ventana.getDesktopPanel());
	}
	
	public void reportesRegistros(){
		new VentanaReportesRegistros(autenticacion, ventana.getDesktopPanel());
	}
	
	public void listarMantenimientos(){
		GestorListadoMantenimientos g = new GestorListadoMantenimientos(autenticacion);
		VentanaListadoMantenimientos v = new VentanaListadoMantenimientos(g, ventana.getDesktopPanel());		
		g.setVentana(v);
		g.listarMantenimientos();
		ventana.getDesktopPanel().add(v.getVentana());
		v.getVentana().setVisible(true);
	}
	
	public void crearMantenimiento(){
		GestorMantenimientos g = new GestorMantenimientos(autenticacion);
		VentanaAgregarMantenimiento v = new VentanaAgregarMantenimiento(g, ventana.getDesktopPanel());		
		g.setVentana(v);
		g.prepararCrear();
	}
	
	public void agregarLibro(){
		VentanaLibros libro = new VentanaLibros(ventana.getDesktopPanel().getSize(),0);
		GestorLibros gestorLibro = new GestorLibros(libro, autenticacion);
		libro.setGestor(gestorLibro);
		ventana.getDesktopPanel().add(libro.getVentanaLibro());
		libro.getVentanaLibro().setVisible(true);
	}
	
	public void editarLibro(){		
		GestorListadoEjemplares g = new GestorListadoEjemplares(autenticacion,1);
		VentanaListadoEjemplares v = new VentanaListadoEjemplares(g, ventana.getDesktopPanel(),1);		
		g.setVentana(v);
		g.listarEjemplares();
		
		/*VentanaLibros libro = new VentanaLibros(ventana.getDesktopPanel().getSize(),1);
		editarLibros editarL = new editarLibros(libro, autenticacion,2);//el 2 es el id del libro
		libro.setEditar(editarL);
		ventana.getDesktopPanel().add(libro.getVentanaLibro());*/
	}
	
	public void listarEjemplares(){
		GestorListadoEjemplares g = new GestorListadoEjemplares(autenticacion);
		VentanaListadoEjemplares v = new VentanaListadoEjemplares(g, ventana.getDesktopPanel());		
		g.setVentana(v);
		g.listarEjemplares();
	}

	public void gestionarSalas(){		
		administrarSalas adVen = new administrarSalas(ventana.getDesktopPanel().getSize());
		GestorSalas gesSal = new GestorSalas(adVen, autenticacion);
		adVen.setGestor(gesSal);
		ventana.getDesktopPanel().add(adVen.getVentanaAdministrar());		
	}
	
	public void gestionarRegistrosEntrada(){
		VentanaListadoRegistros v = new VentanaListadoRegistros(ventana.getDesktopPanel());
		GestorListadoRegistros g = new GestorListadoRegistros(autenticacion, v);
		v.setGestor(g);
		g.prepararListar();
		
		ventana.getDesktopPanel().add(v.getVentana());
		v.getVentana().setVisible(true);
	}
	
	public void crearRegistrosEntrada(){
		GestorCrearRegistro g = new GestorCrearRegistro(autenticacion);
		VentanaCrearRegistro v = new VentanaCrearRegistro(g, ventana.getDesktopPanel());
		g.setVentana(v);
		g.limpiarCampos();
		
		ventana.getDesktopPanel().add(v.getVentana());
		v.getVentana().setVisible(true);
	}
	
	public void gestionarUsuariosBiblioteca(){
		GestorListadoUsuarios g = new GestorListadoUsuarios(GestorListadoUsuarios.USUARIOS_BIBLIOTECA, autenticacion);
		VentanaListadoUsuariosBiblioteca v = new VentanaListadoUsuariosBiblioteca(g, ventana.getDesktopPanel());		
		g.setVentana(v);
		g.listarUsuarios();
		
		ventana.getDesktopPanel().add(v.getVentana());
		v.getVentana().setVisible(true);
	}
	
	public void gestionarMisiones(){
		GestorListadoMisiones g = new GestorListadoMisiones(autenticacion);
		VentanaListadoMisiones v = new VentanaListadoMisiones(g, ventana.getDesktopPanel());		
		g.setVentana(v);
		g.listarMisiones();
		
		ventana.getDesktopPanel().add(v.getVentana());
		v.getVentana().setVisible(true);
	}
	
	public void crearUsuarioBiblioteca(){
		GestorUsuarios g = new GestorUsuarios(GestorUsuarios.USUARIOS_BIBLIOTECA, autenticacion);
		VentanaEditarUsuarioBiblioteca v = new VentanaEditarUsuarioBiblioteca(g, ventana.getDesktopPanel());
		g.setVentanaEditar(v);
		
		g.prepararCrear();
	}
	
	public void gestionarUsuariosSistema(){
		GestorListadoUsuarios g = new GestorListadoUsuarios(GestorListadoUsuarios.USUARIOS_SISTEMA, autenticacion);
		VentanaListadoUsuariosSistema v = new VentanaListadoUsuariosSistema(g, ventana.getDesktopPanel());		
		g.setVentana(v);
		g.listarUsuarios();
		
		ventana.getDesktopPanel().add(v.getVentana());
		v.getVentana().setVisible(true);
	}
	
	public void cargarPreferencias(Properties p){
		if(p!=null){
			String tema = p.getProperty("tema");
			cambiarTema(tema);
		}
	}
	
	public void cerrarSesion(){
		if(MostrarMensajes.mostrarMensaje(ventana.getVentana(), "¿Realmente desea cerrar sesión?", MostrarMensajes.MENSAJE_PREGUNTA)){
			JInternalFrame vec[] = ventana.getDesktopPanel().getAllFrames();
	    	for(int i=0; i<vec.length; i++){
	    		ventana.getDesktopPanel().getDesktopManager().closeFrame(vec[i]);    		
	    	}
			autenticacion.setUsuario(null);
			setAutenticado(false);
		}		
	}
	
	public void mostraVentanaServidor(){
		
	}
	
	public void mostrarVentanaUsuario(){
		VentanaIniciarSesion v = new VentanaIniciarSesion(autenticacion, ventana.getVentana().getBounds());
		v.getVentana().setVisible(true);
		if(v.getConectado()){
			setAutenticado(true);
		}
	}
	
	public boolean probarConexion(Autenticacion a){
		boolean conectado = false;
		Conexion conexion = new Conexion(a);
		conectado = conexion.conectar();
		conexion.desconectar();
		return conectado;
	}
	
	public void setConectadoServidor(boolean conectado){
		conectadoServidor = conectado;
		if(conectadoServidor){
			mostraVentanaServidor();
		}
	}
	
	public void setAutenticado(boolean estado){
		autenticado = estado;
		if(!autenticado){
			ventana.getBtn_acceso1().setEnabled(false);
			ventana.getMi_iniciarSesion().setVisible(true);
			ventana.getMi_cerrarSesion().setVisible(false);
			ventana.getM_mantenimiento().setEnabled(false);
			ventana.getM_usuarios().setEnabled(false);
			ventana.getM_reportes().setEnabled(false);
			ventana.getM_registros().setEnabled(false);
			ventana.getM_libros().setEnabled(false);
			ventana.getM_mantenimientos().setEnabled(false);
			ventana.getM_prestamos().setEnabled(false);
			mostrarVentanaUsuario();
		}else{
			ventana.getMi_iniciarSesion().setVisible(false);
			ventana.getMi_cerrarSesion().setVisible(true);
			ventana.getBtn_acceso1().setEnabled(true);
			ventana.getM_mantenimiento().setEnabled(true);
			ventana.getM_libros().setEnabled(true);
			ventana.getM_reportes().setEnabled(true);
			ventana.getM_mantenimientos().setEnabled(true);
			ventana.getM_prestamos().setEnabled(true);
			if(autenticacion.getUsuario()!=null &&
					autenticacion.getUsuario().getTipo_usuario()==VariablesGlobales.ADMINISTRADOR){
				ventana.getM_usuarios().setEnabled(true);
				ventana.getM_registros().setEnabled(true);
			}
			if(autenticacion.getUsuario()!=null &&
					autenticacion.getUsuario().getTipo_usuario()==VariablesGlobales.USUARIO_REGISTROS_ACCESO){
				ventana.getM_registros().setEnabled(true);
			}
		}
	}
	
	public void cambiarTema(String tema){
		boolean valido = false;
		try{
			if(tema.compareToIgnoreCase("nimbus")==0){
				valido = true;
				UIManager.setLookAndFeel(new NimbusLookAndFeel());
				ventana.getMi_tema1().setSelected(true);
			}
			if(tema.compareToIgnoreCase("windows")==0){
				valido = true;
				UIManager.setLookAndFeel(new WindowsLookAndFeel());
				ventana.getMi_tema2().setSelected(true);
			}
			if(valido == false){
				UIManager.setLookAndFeel(new NimbusLookAndFeel());
			}			
		}
		catch(Exception ex){
		}
		
		if(valido){
			preferencias.setProperty("tema", tema);
			UtilArchivos.escribirXMLPropiedades(UtilArchivos.ARCHIVO_PREFERENCIAS, preferencias);
		}
					
		SwingUtilities.updateComponentTreeUI(ventana.getVentana());
		
	}
	
	public void salir(){		
		if(MostrarMensajes.mostrarMensaje(ventana.getVentana(), "¿Realmente desea salir?", MostrarMensajes.MENSAJE_PREGUNTA)){
			System.exit(0);
		}		
	}
	
	public void sanciones(){
		panelsanciones pan= new panelsanciones(ventana.getDesktopPanel().getSize());
		gestorSanciones ges=new gestorSanciones(pan,autenticacion);
		pan.setGestor(ges);
		pan.getJInternalFrame().setVisible(true);
		ventana.getDesktopPanel().add(pan.getJInternalFrame());
	}
	
	public void prestamos(){
		/*panelPrestamos pan= new panelPrestamos(ventana.getDesktopPanel().getSize());
		gestorPrestamos ges=new gestorPrestamos(pan,autenticacion);
		pan.setGestor(ges);		
		ventana.getDesktopPanel().add(pan.getJInternalFrame());
		pan.getJInternalFrame().setVisible(true);
		//jDesktopPane = new JDesktopPane();		
		//jDesktopPane.add(pan.getJInternalFrame(),null)
		*/
		GestorListadoPrestamos2 g = new GestorListadoPrestamos2(autenticacion);
		VentanaListadoPrestamos2 v = new VentanaListadoPrestamos2(g, ventana.getDesktopPanel());		
		g.setVentana(v);
		g.listarPrestamos();		
		ventana.getDesktopPanel().add(v.getVentana());
		v.getVentana().setVisible(true);
	}
}
