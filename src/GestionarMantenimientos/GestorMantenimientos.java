package GestionarMantenimientos;

import java.util.Date;
import Consultas.EjemplaresDAO;
import Consultas.MantenimientosDAO;
import Entidades.Mantenimientos;
import Entidades.ejemplares;
import Entidades.libros;
import GestionarEjemplares.GestorListadoEjemplares;
import GestionarEjemplares.VentanaListadoEjemplares;
import Utilitario.Autenticacion;
import Utilitario.MostrarMensajes;
import Utilitario.VariablesGlobales;

public class GestorMantenimientos {
	private Autenticacion autenticacion;
	private Mantenimientos seleccionado;
	private VentanaAgregarMantenimiento ventana;
	private String accion;
	
	public GestorMantenimientos(Autenticacion autenticacion){
		this.autenticacion = autenticacion;
	}
	
	public void prepararGuardar(){
		StringBuilder sb = new StringBuilder();
		getDatos();
		
		if(seleccionado.getId_ejemplar() == -1){
			sb.append("- Debe seleccionar un libro\n");
		}
		
		if(seleccionado.getFecha() == null){
			sb.append("- Debe ingresar la fecha del mantenimiento\n");
		}
		if(seleccionado.getObservaciones().length() == 0){
			sb.append("- Debe ingresar los detalles del mantenimiento\n");
		}	
		
		if(sb.toString().length() > 0){
			MostrarMensajes.mostrarMensaje(sb.toString(), MostrarMensajes.MENSAJE_ERROR);
			return ;
		}
		
		guardar();
	}
	
	private void guardar(){		
		MantenimientosDAO dao = new MantenimientosDAO(autenticacion);		
		boolean resultado = false;
		
		
		if(accion.equals("C")){
			seleccionado.setId_mantenimiento(dao.buscarNuevoId());
			seleccionado.setValido(1);
			resultado = dao.guardarMantenimiento(seleccionado);
		}
		/*if(accion.equals("E")){
			resultado = dao.actualizarUsuarioBilioteca(seleccionado);
		}*/		
		
		dao.desconectar();
		
		if(resultado){
			MostrarMensajes.mostrarMensaje("Operación exitosa", MostrarMensajes.MENSAJE_EXITO);
			accion = "V";
			ventana.getBtnBuscarLibro().setVisible(false);
			ventana.getTxtFecha().setEnabled(false);
			ventana.getTxtObservaciones().setEditable(false);
			ventana.getBtnGuardar().setVisible(false);
			if(autenticacion.getUsuario()!=null && 
					autenticacion.getUsuario().getTipo_usuario()==VariablesGlobales.ADMINISTRADOR){
				ventana.getBtnEliminar().setVisible(true);
			}
			String codigo = "000000"+seleccionado.getId_mantenimiento();
			codigo = codigo.substring(codigo.length()-6, codigo.length());
			ventana.getVentana().setTitle("Mantenimiento - "+codigo);
		}else{
			MostrarMensajes.mostrarMensaje("Ocurrió un error al guardar", MostrarMensajes.MENSAJE_ERROR);
		}
	}
	
	public void prepararVer(int id_mantenimiento){
		accion = "V";
		MantenimientosDAO dao = new MantenimientosDAO(autenticacion);
		seleccionado = dao.getMantenimiento(id_mantenimiento);
		dao.desconectar();
		
		EjemplaresDAO ejemplaresDAO = new EjemplaresDAO(autenticacion);
		ejemplares ejemplar = ejemplaresDAO.getEjemplar(seleccionado.getId_ejemplar());
		
		libros libro = ejemplaresDAO.getLibro(ejemplar.getId_libro());
		ejemplaresDAO.desconectar();
		
		ventana.getTxtTitulo().setText(libro.getTitulo());
		ventana.getTxtLibro().setText(libro.getCota()+" e."+ejemplar.getNumero());
		
		String codigo = "000000"+seleccionado.getId_mantenimiento();
		codigo = codigo.substring(codigo.length()-6, codigo.length());
		ventana.getVentana().setTitle("Mantenimiento - "+codigo);
		
		setDatos();
		
		ventana.getBtnBuscarLibro().setVisible(false);
		ventana.getTxtFecha().setEnabled(false);
		ventana.getTxtObservaciones().setEditable(false);
		ventana.getBtnGuardar().setVisible(false);
		if(autenticacion.getUsuario()!=null &&
				autenticacion.getUsuario().getTipo_usuario()==VariablesGlobales.ADMINISTRADOR){
			ventana.getBtnEliminar().setVisible(true);
		}
		else{
			ventana.getBtnEliminar().setVisible(false);
		}
			
	}
	
	public void prepararCrear(){
		accion = "C";
		seleccionado = new Mantenimientos();
		seleccionado.setFecha(new Date());
		seleccionado.setObservaciones("");
		seleccionado.setId_ejemplar(-1);
		setDatos();
		
		ventana.getBtnEliminar().setVisible(false);
	}
	
	public void getDatos(){		
		seleccionado.setFecha(ventana.getTxtFecha().getDate());
		seleccionado.setObservaciones(ventana.getTxtObservaciones().getText());
	}
	
	public void setDatos(){
		ventana.getTxtFecha().setDate(seleccionado.getFecha());
		ventana.getTxtObservaciones().setText(seleccionado.getObservaciones());
	}
	
	public void buscarLibro(){
		GestorListadoEjemplares g = new GestorListadoEjemplares(autenticacion);
		VentanaListadoEjemplares v = new VentanaListadoEjemplares(g, ventana.getDesktopPane());		
		g.setVentana(v);
		g.setGestorMantenimientos(this);
		g.listarEjemplares();
	}
	
	public void setVentana(VentanaAgregarMantenimiento ventana){
		this.ventana = ventana;
	}
	
	public void seleccionaLibro(int id_ejemplar){
		seleccionado.setId_ejemplar(id_ejemplar);
		ventana.getVentana().setVisible(true);
		
		EjemplaresDAO ejemplaresDAO = new EjemplaresDAO(autenticacion);
		ejemplares ejemplar = ejemplaresDAO.getEjemplar(id_ejemplar);
		
		libros libro = ejemplaresDAO.getLibro(ejemplar.getId_libro());
		ejemplaresDAO.desconectar();
		
		ventana.getTxtTitulo().setText(libro.getTitulo());
		ventana.getTxtLibro().setText(libro.getCota()+" e."+ejemplar.getNumero());
		//System.out.println(libro.getId_libro());
		
		try{
			ventana.getVentana().setSelected(true);
		}
		catch(Exception e){
		}
	}
	
	public void seleccionaLibro2(int id_ejemplar){
		//seleccionado.setId_ejemplar(id_ejemplar);
		//ventana.getVentana().setVisible(true);
		
		EjemplaresDAO ejemplaresDAO = new EjemplaresDAO(autenticacion);
		ejemplares ejemplar = ejemplaresDAO.getEjemplar(id_ejemplar);
		
		libros libro = ejemplaresDAO.getLibro(ejemplar.getId_libro());
		ejemplaresDAO.desconectar();
		
		//ventana.getTxtTitulo().setText(libro.getTitulo());
		//ventana.getTxtLibro().setText(libro.getCota()+" e."+ejemplar.getNumero());
		System.out.println(libro.getId_libro());
		
		/*try{
			ventana.getVentana().setSelected(true);
		}
		catch(Exception e){
		}*/
	}
}
