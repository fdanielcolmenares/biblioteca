package GestionarEjemplares;

import java.sql.ResultSet;
import Utilitario.Autenticacion;
import Utilitario.Item;
import Utilitario.Tablas.ModeloTablaListado;
import Consultas.EjemplaresDAO;
import GestionLibros.VentanaLibros;
import GestionLibros.editarLibros;
import GestionarMantenimientos.GestorMantenimientos;
import GestionarPrestamos2.GestorPrestamos2;

public class GestorListadoEjemplares {
	private VentanaListadoEjemplares ventana;	
	private Autenticacion autenticacion;
	private int busEdita;
	private ModeloTablaListado modelo;
	
	private GestorMantenimientos gestorMantenimientos = null;
	private GestorPrestamos2 gestorPrestamos2 = null;
	
	public GestorListadoEjemplares(Autenticacion autenticacion){
		this.autenticacion = autenticacion;
	}
	
	public GestorListadoEjemplares(Autenticacion autenticacion,int busEdita){
		this.autenticacion = autenticacion;
		this.busEdita = busEdita;
	}
	
	public void listarEjemplares(){
		if(ventana.getTxtCota().getText().length()==0){
			ventana.getTxtCota().setText("*");
		}
		if(ventana.getTxtTitulo().getText().length()==0){
			ventana.getTxtTitulo().setText("*");
		}
		if(ventana.getTxtEjemplar().getText().length()==0){
			ventana.getTxtEjemplar().setText("*");
		}
		else{
			try{
				Integer.parseInt(ventana.getTxtEjemplar().getText());
			}
			catch(Exception e){
				ventana.getTxtEjemplar().setText("*");
			}
		}
		
		EjemplaresDAO dao = new EjemplaresDAO(autenticacion);
		ResultSet res = null;
		
		if(busEdita==1){
			res = dao.getListadoEjemplares2(ventana.getTxtCota().getText().replace('*', '%'), 
			ventana.getTxtTitulo().getText().replace('*', '%'));
		}
		else{
			res = dao.getListadoEjemplares(ventana.getTxtCota().getText().replace('*', '%'), 
					ventana.getTxtTitulo().getText().replace('*', '%'),
					ventana.getTxtEjemplar().getText().replace('*', '%'));
		}
		
		try{
			int i = 0;
			modelo = ventana.getTablaListado().getModel();
			modelo.setRowCount(0);
			
			while(res!=null && res.next()){
				modelo.setRowCount(i+1);
				Item itm = new Item();
				itm.setValorBoolean(false);
				if(busEdita!=1)
					itm.setValorInt(res.getInt("id_ejemplar"));
				else
					itm.setValorInt(res.getInt("id_libro"));
				
				modelo.setValueAt(itm, i, 0);
				modelo.setValueAt(res.getString("cota"), i, 1);
				if(busEdita!=1){
					modelo.setValueAt(res.getString("numero"), i, 2);
					modelo.setValueAt(res.getString("titulo"), i, 3);
					modelo.setValueAt(res.getString("estante"), i, 4);
					modelo.setValueAt(res.getString("sala"), i, 5);	
					modelo.setValueAt(res.getString("disponibilidad"), i, 6);
				}
				if(busEdita==1){
					modelo.setValueAt(res.getString("titulo"), i, 2);
					modelo.setValueAt(res.getString("estante"), i, 3);
					modelo.setValueAt(res.getString("sala"), i, 4);	
				}
				
				i++;
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		dao.desconectar();		
	}
	
	public void seleccionar(int id){
		if(gestorMantenimientos!=null){
			gestorMantenimientos.seleccionaLibro(id);
		}
		if(gestorPrestamos2!=null){
			gestorPrestamos2.seleccionaEjemplar(id);
		}
		if(busEdita==1){
			//libros bus = new libros(autenticacion);			
			//int id = bus.buscarCota(modelo.getValueAt(id_ejemplar-1,1).toString());
			/*int idLibro = id;			
			VentanaLibros libro = new VentanaLibros(ventana.getDesktopPane().getSize(),1);
			editarLibros editarL = new editarLibros(libro, autenticacion,idLibro);
			libro.setEditar(editarL);
			ventana.getDesktopPane().add(libro.getVentanaLibro());
			ventana.getVentana().setVisible(true);*/		
			
			VentanaLibros libro = new VentanaLibros(ventana.getDesktopPane().getSize(),1);
			editarLibros editarL = new editarLibros(libro, autenticacion,id);
			libro.setEditar(editarL);
			ventana.getDesktopPane().add(libro.getVentanaLibro());
			libro.getVentanaLibro().setVisible(true);
		}
	}
	
	public void setVentana(VentanaListadoEjemplares ventana){
		this.ventana = ventana;
	}
	
	public void setGestorMantenimientos(GestorMantenimientos g){
		gestorMantenimientos = g;
	}
	
	public void setGestorPrestamos2(GestorPrestamos2 g){
		gestorPrestamos2 = g;
	}
}
