package GestionarMisiones;

import java.util.ArrayList;
import java.util.List;
import Consultas.MisionesDAO;
import Entidades.Misiones;
import Utilitario.Autenticacion;
import Utilitario.Item;
import Utilitario.MostrarMensajes;
import Utilitario.VariablesGlobales;

public class GestorMisiones {
	private Autenticacion autenticacion;
	private Misiones seleccionado;
	private VentanaEditarMision ventana;
	private String accion;
	
	public GestorMisiones(Autenticacion autenticacion){
		this.autenticacion = autenticacion;
	}
	
	public void prepararGuardar(){
		StringBuilder sb = new StringBuilder();
		getDatos();
		
		if(seleccionado.getDescripcion().length() == 0){
			sb.append("- Debe ingresar el nombre de la misión\n");
		}
		
		if(sb.toString().length() > 0){
			MostrarMensajes.mostrarMensaje(sb.toString(), MostrarMensajes.MENSAJE_ERROR);
			return ;
		}
		
		guardar();
	}
	
	private void guardar(){		
		MisionesDAO dao = new MisionesDAO(autenticacion);		
		boolean resultado = false;
		
		if(accion.equals("C")){
			seleccionado.setId_mision(dao.buscarNuevoId());
			if(dao.validarMision(seleccionado.getId_mision(), seleccionado.getDescripcion())){
				resultado = dao.guardarMision(seleccionado);
			}
			else{
				MostrarMensajes.mostrarMensaje("La misión ya existe", MostrarMensajes.MENSAJE_ERROR);
				dao.desconectar();
				return;
			}
			
		}
		if(accion.equals("E")){
			if(dao.validarMision(seleccionado.getId_mision(), seleccionado.getDescripcion())){
				resultado = dao.actualizarMision(seleccionado);
			}
			else{
				MostrarMensajes.mostrarMensaje("La misión ya existe", MostrarMensajes.MENSAJE_ERROR);
				dao.desconectar();
				return;
			}			
		}
		
		
		dao.desconectar();
		
		if(resultado){
			MostrarMensajes.mostrarMensaje("Operación exitosa", MostrarMensajes.MENSAJE_EXITO);
			ventana.getBtnEliminar().setVisible(true);
			accion = "E";
		}else{
			MostrarMensajes.mostrarMensaje("Ocurrió un error al guardar", MostrarMensajes.MENSAJE_ERROR);
		}
	}
	
	public void prepararEditar(int id_mision){
		accion = "E";
		MisionesDAO dao = new MisionesDAO(autenticacion);
		
		seleccionado = dao.getMision(id_mision);
		
		dao.desconectar();
		setDatos();
	}
	
	public void prepararCrear(){
		accion = "C";
		seleccionado = new Misiones();
		
		setDatos();
		
		ventana.getBtnEliminar().setVisible(false);		
	}
	
	public void getDatos(){
		seleccionado.setDescripcion(ventana.getTxtDescripcion().getText());			
	}
	
	public void setDatos(){		
		ventana.getTxtDescripcion().setText(seleccionado.getDescripcion());	
	}
	
	public List<Item> getListadoTiposUsuario(){
		List<Item> items = new ArrayList<Item>();
		
		Item itm = new Item("Administrador", VariablesGlobales.ADMINISTRADOR);	
		items.add(itm);
		
		return items;
	}
	
	public void setVentanaEditar(VentanaEditarMision ventana){
		this.ventana = ventana;
	}
}
