package GestionarAplicacionRegistro;

import java.sql.ResultSet;

import Consultas.RegistrosDAO;
import Utilitario.Autenticacion;
import Utilitario.Item;
import Utilitario.UtilFechas;
import Utilitario.Tablas.ModeloTablaListado;

public class GestorListadoRegistros {
	private Autenticacion autenticacion;
	private VentanaListadoRegistros ventana;
	
	public GestorListadoRegistros(Autenticacion autenticacion, VentanaListadoRegistros ventana){
		this.ventana = ventana;
		this.autenticacion = autenticacion;		
	}
	
	public void prepararListar(){
		RegistrosDAO dao = new RegistrosDAO(autenticacion);
		ResultSet res = dao.getListadoRegistros(ventana.getTxtFechaInicio().getDate(), ventana.getTxtFechaFin().getDate());
		try{
			int cont = 0;
			ModeloTablaListado modelo = ventana.getTablaListado().getModel();
			modelo.setRowCount(0);
			
			while(res.next()){
				modelo.setRowCount(cont+1);
				
				Item itm = new Item();
				itm.setValorBoolean(false);
				itm.setValorInt(res.getInt("id_registro"));
				
				modelo.setValueAt(itm, cont, 0);
				modelo.setValueAt(UtilFechas.convertirFecha(res.getDate("fecha"), UtilFechas.DD_MM_YYYY), cont, 1);
				modelo.setValueAt(res.getString("sexo"), cont, 2);
				modelo.setValueAt(res.getInt("edad"), cont, 3);
				modelo.setValueAt(res.getString("estudia"), cont, 4);
				modelo.setValueAt(res.getString("trabaja"), cont, 5);
				modelo.setValueAt(res.getString("mision"), cont, 6);
				
				cont++;
			}
		}
		catch(Exception e){			
		}
		dao.desconectar();
	}
	
	public void prepararCrear(){
		GestorCrearRegistro g = new GestorCrearRegistro(autenticacion);
		VentanaCrearRegistro v = new VentanaCrearRegistro(g, ventana.getDesktopPane());
		g.setVentana(v);
		g.limpiarCampos();		
		
		ventana.getDesktopPane().add(v.getVentana());
		v.getVentana().setVisible(true);
	}
}
