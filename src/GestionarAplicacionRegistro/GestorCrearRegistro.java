package GestionarAplicacionRegistro;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import Consultas.MisionesDAO;
import Consultas.RegistrosDAO;
import Entidades.Registros;
import Utilitario.Autenticacion;
import Utilitario.Item;
import Utilitario.MostrarMensajes;

public class GestorCrearRegistro {
	private VentanaCrearRegistro ventana;
	private Autenticacion autenticacion;	
	private Registros seleccionado;
	
	public GestorCrearRegistro(Autenticacion autenticacion){		
		this.autenticacion = autenticacion;		
	}
	
	public void setVentana(VentanaCrearRegistro ventana){
		this.ventana = ventana;
	}
	
	public void prepararGuardar(){		
		if(ventana.getTxtEdad().getText().length() == 0){
			MostrarMensajes.mostrarMensaje("La edad es un campo obligatorio", MostrarMensajes.MENSAJE_ERROR);
			return ;
		}
		
		guardar();
	}
	
	private void guardar(){
		getDatos();
		
		RegistrosDAO dao = new RegistrosDAO(autenticacion);
		
		seleccionado.setId_registro(dao.buscarNuevoId());
		if(dao.guardarRegistro(seleccionado)){
			MostrarMensajes.mostrarMensaje("Operación exitosa", MostrarMensajes.MENSAJE_EXITO);
		}else{
			MostrarMensajes.mostrarMensaje("Ocurrió un error al guardar", MostrarMensajes.MENSAJE_ERROR);
		}		
		dao.desconectar();
		
		limpiarCampos();
	}
	
	public void limpiarCampos(){
		seleccionado = new Registros();
		
		ventana.getCbxSexo().setSelectedIndex(0);
		ventana.getTxtEdad().setText("");
		ventana.getChkSiEstudia().setSelected(true);
		ventana.getChkNoTrabaja().setSelected(true);		
		
		ventana.getCbxMision().setSelectedIndex(0);		
	}
	
	public List<Item> getMisiones(){		
		List<Item> items = new ArrayList<Item>();
		
		MisionesDAO dao = new MisionesDAO(autenticacion);
		ResultSet res = dao.getListadoMisiones("%");
		
		try{			
			while(res.next()){				
				Item itm = new Item(res.getString("descripcion"), res.getInt("id_mision"));	
				items.add(itm);
			}
		}
		catch(Exception e){			
		}
		
		dao.desconectar();
		
		return items;
	}
	
	public void getDatos(){		
		seleccionado.setSexo(((Item)ventana.getCbxSexo().getSelectedItem()).getValorString());
		
		seleccionado.setEdad(Integer.parseInt(ventana.getTxtEdad().getText()));
		
		if(ventana.getChkSiEstudia().isSelected()){
			seleccionado.setEstudia("S");
		}else{
			seleccionado.setEstudia("N");
		}
		
		if(ventana.getChkSiTrabaja().isSelected()){
			seleccionado.setTrabaja("S");
		}else{
			seleccionado.setTrabaja("N");
		}
		
		seleccionado.setFecha(new Date());
		
		seleccionado.getMision().setId_mision(((Item)ventana.getCbxMision().getSelectedItem()).getValorInt());
	}
}
