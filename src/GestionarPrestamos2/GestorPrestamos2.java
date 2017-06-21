package GestionarPrestamos2;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Consultas.EjemplaresDAO;
import Consultas.MisionesDAO;
import Consultas.PrestamosDAO;
import Entidades.Misiones;
import Entidades.Prestamos2;
import Entidades.ejemplares;
import Entidades.libros;
import GestionarEjemplares.GestorListadoEjemplares;
import GestionarEjemplares.VentanaListadoEjemplares;
import Utilitario.Autenticacion;
import Utilitario.Item;
import Utilitario.MostrarMensajes;
import Utilitario.VariablesGlobales;

public class GestorPrestamos2 {
	private Autenticacion autenticacion;
	private Prestamos2 seleccionado;
	private VentanaEditarPrestamos2 ventana;
	private String accion;
	
	public GestorPrestamos2(Autenticacion autenticacion){
		this.autenticacion = autenticacion;
	}
	
	public void prepararGuardar(){
		StringBuilder sb = new StringBuilder();
		getDatos();
		
		if(seleccionado.getId_usuario() == -1){
			sb.append("- Debe seleccionar el usuario\n");
		}
		if(seleccionado.getId_ejemplar() == -1){
			sb.append("- Debe seleccionar el libro\n");
		}
		if(seleccionado.getFecha_vencimiento() == null){
			sb.append("- Debe seleccionar la fecha de vencimiento\n");
		}
		
		if(seleccionado.getFecha_prestamo()==null){
			seleccionado.setFecha_entrega(new Date());
		}
		
		if(accion.equals("R") && seleccionado.getFecha_entrega()==null){
			seleccionado.setFecha_entrega(new Date());
		}
		
		if(accion.equals("C")){
			seleccionado.setFecha_entrega(null);
		}
		
		if(sb.toString().length() > 0){
			MostrarMensajes.mostrarMensaje(sb.toString(), MostrarMensajes.MENSAJE_ERROR);
			return ;
		}
		
		guardar();
	}
	
	private void guardar(){		
		PrestamosDAO dao = new PrestamosDAO(autenticacion);		
		boolean resultado = false;
		
		if(accion.equals("C")){
			seleccionado.setId_prestamo(dao.buscarNuevoId());
			
			resultado = dao.guardarPrestamo(seleccionado);
		}
		if(accion.equals("R")){
			resultado = dao.actualizarPrestamo(seleccionado);			
		}
		
		dao.desconectar();
		
		if(resultado){
			MostrarMensajes.mostrarMensaje("Operación exitosa", MostrarMensajes.MENSAJE_EXITO);
			//ventana.getBtnEliminar().setVisible(true);
			if(accion.equals("C")){
				ventana.getTxtFechaPrestamo().setEnabled(false);
				ventana.getTxtFechaVencimiento().setEnabled(false);
				ventana.getBtnBuscarUsuario().setVisible(false);
				ventana.getBtnBuscar().setVisible(false);
				ventana.getTxtFechaEntrega().setVisible(true);
				ventana.lblEntrega.setVisible(true);
				accion = "R";
			}
			else{
				ventana.getTxtFechaEntrega().setEnabled(false);
				accion = "V";				
			}
			
		}else{
			MostrarMensajes.mostrarMensaje("Ocurrió un error al guardar", MostrarMensajes.MENSAJE_ERROR);
		}
	}
	
	public void prepararRecibir(int id_prestamo){
		PrestamosDAO dao = new PrestamosDAO(autenticacion);
		
		seleccionado = dao.getPrestamo(id_prestamo);

		if(seleccionado.getFecha_entrega()==null){
			accion = "R";
			ventana.getTxtFechaPrestamo().setEnabled(true);
		}
		else{
			accion = "V";
			ventana.getTxtFechaPrestamo().setEnabled(false);
			MostrarMensajes.mostrarMensaje("El préstamo ya esta finalizado", MostrarMensajes.MENSAJE_ERROR);			
		}
		ventana.getBtnEliminar().setVisible(false);		
		ventana.getTxtFechaVencimiento().setEnabled(false);
		ventana.getBtnBuscarUsuario().setVisible(false);
		ventana.getBtnBuscar().setVisible(false);
		ventana.getTxtFechaEntrega().setVisible(true);
		ventana.lblEntrega.setVisible(true);
		
		dao.desconectar();
		setDatos();
	}
	
	public void prepararCrear(){
		accion = "C";
		seleccionado = new Prestamos2();
		
		setDatos();
		
		ventana.getTxtFechaEntrega().setVisible(false);
		ventana.lblEntrega.setVisible(false);
		ventana.getBtnEliminar().setVisible(false);
	}
	
	public void getDatos(){
		seleccionado.setFecha_entrega(ventana.getTxtFechaEntrega().getDate());
		seleccionado.setFecha_prestamo(ventana.getTxtFechaPrestamo().getDate());
		seleccionado.setFecha_vencimiento(ventana.getTxtFechaVencimiento().getDate());		
	}
	
	public void setDatos(){
		//usuario
		//libro
		ventana.getTxtFechaEntrega().setDate(seleccionado.getFecha_entrega());
		ventana.getTxtFechaPrestamo().setDate(seleccionado.getFecha_prestamo());
		ventana.getTxtFechaVencimiento().setDate(seleccionado.getFecha_vencimiento());
	}
	
	public void buscarUsuario(){
		
	}
	
	public void buscarEjemplar(){
		GestorListadoEjemplares g = new GestorListadoEjemplares(autenticacion);
		VentanaListadoEjemplares v = new VentanaListadoEjemplares(g, ventana.getDesktopPane());		
		g.setVentana(v);
		g.setGestorPrestamos2(this);
		g.listarEjemplares();
	}
	
	public void seleccionaEjemplar(int id_ejemplar){
		seleccionado.setId_ejemplar(id_ejemplar);
		ventana.getVentana().setVisible(true);
		
		EjemplaresDAO ejemplaresDAO = new EjemplaresDAO(autenticacion);
		ejemplares ejemplar = ejemplaresDAO.getEjemplar(id_ejemplar);
		
		libros libro = ejemplaresDAO.getLibro(ejemplar.getId_libro());
		ejemplaresDAO.desconectar();
		
		ventana.getTxtTitulo().setText(libro.getTitulo());
		ventana.getTxtLibro().setText(libro.getCota()+" e."+ejemplar.getNumero());
		
		try{
			ventana.getVentana().setSelected(true);
		}
		catch(Exception e){
		}
	}
	
	public void setVentanaEditar(VentanaEditarPrestamos2 ventana){
		this.ventana = ventana;
	}
}
