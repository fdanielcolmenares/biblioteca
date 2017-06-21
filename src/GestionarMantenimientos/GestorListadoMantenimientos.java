package GestionarMantenimientos;

import java.sql.ResultSet;
import Utilitario.Autenticacion;
import Utilitario.Item;
import Utilitario.VariablesGlobales;
import Utilitario.Tablas.ModeloTablaListado;
import Consultas.MantenimientosDAO;

public class GestorListadoMantenimientos {
	private VentanaListadoMantenimientos ventana;
	
	private Autenticacion autenticacion;
	
	public GestorListadoMantenimientos(Autenticacion autenticacion){
		this.autenticacion = autenticacion;
	}
	
	public void listarMantenimientos(){
		if(ventana.getTxtCota().getText().length()==0){
			ventana.getTxtCota().setText("*");
		}		
		if(ventana.getTxtEjemplar().getText().length()==0){
			
		}
		else{
			try{
				Integer.parseInt(ventana.getTxtEjemplar().getText());
			}
			catch(Exception e){
				ventana.getTxtEjemplar().setText("*");
			}
		}
		
		MantenimientosDAO dao = new MantenimientosDAO(autenticacion);
		ResultSet res = dao.getListadoMantenimientos(ventana.getTxtFechaInicio().getDate(),
													 ventana.getTxtFechaFin().getDate(),
													 ventana.getTxtCota().getText().replace('*', '%'),
													 ventana.getTxtEjemplar().getText().replace('*', '%'));
		
		try{
			int i = 0;
			ModeloTablaListado modelo = ventana.getTablaListado().getModel();
			modelo.setRowCount(0);
			
			while(res!=null && res.next()){
				modelo.setRowCount(i+1);
				Item itm = new Item();
				itm.setValorBoolean(false);
				itm.setValorInt(res.getInt("id_mantenimiento"));
				
				modelo.setValueAt(itm, i, 0);
				modelo.setValueAt(res.getString("codigo"), i, 1);
				modelo.setValueAt(res.getString("fecha"), i, 2);
				modelo.setValueAt(res.getString("cota")+" e."+res.getString("numero"), i, 3);		
				modelo.setValueAt(res.getString("titulo"), i, 4);
				
				i++;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		dao.desconectar();
	}
	
	public void crearMantenimiento(){
		GestorMantenimientos g = new GestorMantenimientos(autenticacion);
		VentanaAgregarMantenimiento v = new VentanaAgregarMantenimiento(g, ventana.getDesktopPanel());		
		g.setVentana(v);
		g.prepararCrear();
	}
	
	public void eliminarMantenimiento(int idMantenimiento){
		
	}
	
	public void prepararVer(int id_mantenimiento){
		GestorMantenimientos g = new GestorMantenimientos(autenticacion);
		VentanaAgregarMantenimiento v = new VentanaAgregarMantenimiento(g, ventana.getDesktopPanel());		
		g.setVentana(v);
		g.prepararVer(id_mantenimiento);
	}
	
	public void setVentana(VentanaListadoMantenimientos ventana){
		this.ventana = ventana;
		if(autenticacion.getUsuario()!=null &&
				autenticacion.getUsuario().getTipo_usuario()==VariablesGlobales.ADMINISTRADOR){
			ventana.getBtnEliminar().setVisible(true);
		}
		else{
			ventana.getBtnEliminar().setVisible(false);
		}
	}
}
