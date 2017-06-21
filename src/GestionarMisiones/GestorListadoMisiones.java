package GestionarMisiones;

import java.sql.ResultSet;
import Utilitario.Autenticacion;
import Utilitario.Item;
import Utilitario.MostrarMensajes;
import Utilitario.Tablas.ModeloTablaListado;
import Consultas.MisionesDAO;

public class GestorListadoMisiones {
	private VentanaListadoMisiones ventana;	
	
	private Autenticacion autenticacion;
	
	public GestorListadoMisiones(Autenticacion autenticacion){
		this.autenticacion = autenticacion;
	}	
	
	public void listarMisiones(){
		if(ventana.getTxtMision().getText().length()==0){
			ventana.getTxtMision().setText("*");
		}
		
		MisionesDAO dao = new MisionesDAO(autenticacion);
		ResultSet res = dao.getListadoMisiones(ventana.getTxtMision().getText().replace('*', '%'));
		
		try{
			int i = 0;
			ModeloTablaListado modelo = ventana.getTablaListado().getModel();
			modelo.setRowCount(0);
			
			while(res!=null && res.next()){
				modelo.setRowCount(i+1);
				Item itm = new Item();
				itm.setValorBoolean(false);
				itm.setValorInt(res.getInt("id_mision"));
				
				modelo.setValueAt(itm, i, 0);
				modelo.setValueAt(res.getString("descripcion"), i, 1);
				
				i++;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		dao.desconectar();
	}
	
	public void editarMision(int idMision){
		if(idMision == 1){
			MostrarMensajes.mostrarMensaje("No puede editar esta misión", MostrarMensajes.MENSAJE_ERROR);
		}
		else{
			GestorMisiones g = new GestorMisiones(autenticacion);
			VentanaEditarMision v = new VentanaEditarMision(g, ventana.getDesktopPane());
			g.setVentanaEditar(v);
			
			g.prepararEditar(idMision);
		}
	}
	
	public void crearMision(){		
		GestorMisiones g = new GestorMisiones(autenticacion);
		VentanaEditarMision v = new VentanaEditarMision(g, ventana.getDesktopPane());
		g.setVentanaEditar(v);
			
		g.prepararCrear();
	}
	
	public void eliminarMision(int idMision){
		if(idMision == 1){
			MostrarMensajes.mostrarMensaje("No puede eliminar esta misión", MostrarMensajes.MENSAJE_ERROR);
		}
	}
	
	
	public void setVentana(VentanaListadoMisiones ventana){
		this.ventana = ventana;
	}
}
