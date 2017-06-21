package GestionarPrestamos2;

import java.sql.ResultSet;
import java.util.Date;

import prestamos.gestorPrestamos;
import prestamos.panelPrestamos;
import Utilitario.Autenticacion;
import Utilitario.Item;
import Utilitario.MostrarMensajes;
import Utilitario.UtilFechas;
import Utilitario.Tablas.ModeloTablaListado;
import Consultas.PrestamosDAO;

public class GestorListadoPrestamos2 {
	private VentanaListadoPrestamos2 ventana;	
	
	private Autenticacion autenticacion;
	
	public GestorListadoPrestamos2(Autenticacion autenticacion){
		this.autenticacion = autenticacion;
	}	
	
	public void listarPrestamos(){
		if(ventana.getTxtLibro().getText().length()==0){
			ventana.getTxtLibro().setText("*");
		}
		if(ventana.getTxtUsuario().getText().length()==0){
			ventana.getTxtUsuario().setText("*");
		}
		
		PrestamosDAO dao = new PrestamosDAO(autenticacion);
		ResultSet res = dao.getListadoPrestamos(ventana.getTxtLibro().getText().replace('*', '%'),
				ventana.getTxtUsuario().getText().replace('*', '%'));
		
		try{
			int i = 0;
			ModeloTablaListado modelo = ventana.getTablaListado().getModel();
			modelo.setRowCount(0);
			
			while(res!=null && res.next()){
				modelo.setRowCount(i+1);
				Item itm = new Item();
				itm.setValorBoolean(false);
				itm.setValorInt(res.getInt("id_prestamo"));
				
				modelo.setValueAt(itm, i, 0);
				modelo.setValueAt(res.getString("cedula"), i, 1);
				modelo.setValueAt(res.getString("usuario"), i, 2);
				modelo.setValueAt(res.getString("titulo"), i, 3);
				modelo.setValueAt(res.getString("cota"), i, 4);
				
				Date f = res.getDate("fecha_prestamo");
				if(f == null)
					modelo.setValueAt("", i, 5);
				else
					modelo.setValueAt(UtilFechas.convertirFecha(f, UtilFechas.DD_MM_YYYY), i, 5);
				
				f = res.getDate("fecha_vencimiento");
				if(f == null)
					modelo.setValueAt("", i, 6);
				else
					modelo.setValueAt(UtilFechas.convertirFecha(f, UtilFechas.DD_MM_YYYY), i, 6);
				
				f = res.getDate("fecha_entrega");
				if(f == null)
					modelo.setValueAt("", i, 7);
				else
					modelo.setValueAt(UtilFechas.convertirFecha(f, UtilFechas.DD_MM_YYYY), i, 7);
				
				i++;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		dao.desconectar();
	}
	
	public void editarMision(int idPrestamo){		
		GestorPrestamos2 g = new GestorPrestamos2(autenticacion);
		VentanaEditarPrestamos2 v = new VentanaEditarPrestamos2(g, ventana.getDesktopPane());
		g.setVentanaEditar(v);
		
		g.prepararRecibir(idPrestamo);		
	}
	
	public void crearPrestamo(){		
		/*GestorPrestamos2 g = new GestorPrestamos2(autenticacion);
		VentanaEditarPrestamos2 v = new VentanaEditarPrestamos2(g, ventana.getDesktopPane());
		g.setVentanaEditar(v);
			
		g.prepararCrear();*/
		
		panelPrestamos pan= new panelPrestamos(ventana.getDesktopPane().getSize(), false);
		gestorPrestamos ges=new gestorPrestamos(pan,autenticacion);
		pan.setGestor(ges);		
		ventana.getDesktopPane().add(pan.getJInternalFrame());
		pan.getJInternalFrame().setVisible(true);
	}
	
	public void recibirPrestamos(){
		panelPrestamos pan= new panelPrestamos(ventana.getDesktopPane().getSize(), true);
		gestorPrestamos ges=new gestorPrestamos(pan,autenticacion);
		pan.setGestor(ges);		
		ventana.getDesktopPane().add(pan.getJInternalFrame());
		pan.getJInternalFrame().setVisible(true);
	}
	
	public void eliminarMision(int idMision){
		if(idMision == 1){
			MostrarMensajes.mostrarMensaje("No puede eliminar esta misión", MostrarMensajes.MENSAJE_ERROR);
		}
	}
	
	
	public void setVentana(VentanaListadoPrestamos2 ventana){
		this.ventana = ventana;
	}
}
