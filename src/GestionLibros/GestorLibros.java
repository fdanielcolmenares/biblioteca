package GestionLibros;

import java.awt.Dimension;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import Utilitario.Autenticacion;

import Entidades.autores;
import Entidades.ediciones;
import Entidades.ejemplares;
import Entidades.libros;
import Entidades.salas;

public class GestorLibros {
	
	VentanaLibros ventana;
	int y=0,y1=0,y2=0,conte=0,conted=0,conta=0;
	private List<pnlEjemplar> liseje;
	private List<pnlEdiciones> lisedi;
	private List<pnlAutores> lisaut;
	private ArrayList ids;
	private Autenticacion autenticacion;
	
	public GestorLibros(VentanaLibros ventana, Autenticacion autenticacion){
		this.ventana = ventana;
		liseje= new ArrayList<pnlEjemplar>();
		lisedi= new ArrayList<pnlEdiciones>();
		lisaut= new ArrayList<pnlAutores>();
		ids = new ArrayList();
		this.autenticacion = autenticacion;
	}
	
	/*public List getIds(){
		return ids;
	}*/
	
	public void AddPnlEjemplar(){
		if(conte>0)
			liseje.get(conte-1).ocultarX();
		pnlEjemplar pan = new pnlEjemplar(0,y,this);
		pan.inicializarLibro(ventana.getTfTitulo().getText());
		pan.setPos(conte);
		pan.setEstado(1);
		ventana.getJPanel().add(pan.getPnlEjemplar());
		ventana.getJPanel().setSize(new Dimension(pan.getPnlEjemplar().getWidth(), pan.getPnlEjemplar().getHeight()+y));
		ventana.getJPanel().setPreferredSize(new Dimension(pan.getPnlEjemplar().getWidth(), pan.getPnlEjemplar().getHeight()+y));
		y+=pan.getPnlEjemplar().getHeight();
		liseje.add(pan);
		conte++;
		SwingUtilities.updateComponentTreeUI(ventana.getJPanel());
	}
	
	public void disminuirEjemplar(){
		//System.out.println("elimina: "+y); 
		pnlEjemplar pan = new pnlEjemplar(0,y,this);
		y-=pan.getPnlEjemplar().getHeight();
		//System.out.println("elimina2: "+y); 
	}
	
	public void AddPnlEdicion(){
		if(conted>0)
			lisedi.get(conted-1).ocultarX();
		pnlEdiciones pan = new pnlEdiciones(0,y1,this); 
		pan.setEstado(1);
		ventana.getJPanel1().add(pan.getJPanel());
		ventana.getJPanel1().setSize(new Dimension(pan.getJPanel().getWidth(), pan.getJPanel().getHeight()+y1));
		ventana.getJPanel1().setPreferredSize(new Dimension(pan.getJPanel().getWidth(), pan.getJPanel().getHeight()+y1));
		y1+=pan.getJPanel().getHeight();
		lisedi.add(pan);
		conted++;
		SwingUtilities.updateComponentTreeUI(ventana.getJPanel1());
	}
	
	public void disminuirEdicion(){
		//System.out.println("elimina: "+y); 
		pnlEdiciones pan = new pnlEdiciones(0,y1,this);
		y1-=pan.getJPanel().getHeight();
		//System.out.println("elimina2: "+y); 
	}
	
	public void AddPnlAutor(){
		if(conta>0)
			lisaut.get(conta-1).ocultarX();
		pnlAutores pan = new pnlAutores(0,y2,this); 
		pan.setEstado(1);
		ventana.getJPanel2().add(pan.getJPanel());
		ventana.getJPanel2().setSize(new Dimension(pan.getJPanel().getWidth(), pan.getJPanel().getHeight()+y2));
		ventana.getJPanel2().setPreferredSize(new Dimension(pan.getJPanel().getWidth(), pan.getJPanel().getHeight()+y2));
		y2+=pan.getJPanel().getHeight();
		lisaut.add(pan);
		conta++;
		SwingUtilities.updateComponentTreeUI(ventana.getJPanel2());
	}
	
	public void disminuirAutor(){
		//System.out.println("elimina: "+y); 
		pnlAutores pan = new pnlAutores(0,y2,this); 
		y2-=pan.getJPanel().getHeight();
		//System.out.println("elimina2: "+y); 
	}
	
	public void validarLibro(){
		boolean error=false;
		//System.out.println("entre validar libro");
		if(ventana.getTfCota().getText().compareTo("")==0) //System.out.println("entre validar libro 1condicon");
			error=true;
		if(ventana.getTfTitulo().getText().compareTo("")==0)
			error=true;
		if(error){
			System.out.println("entre validar libro error");
			ventana.getContenedor().setSelectedIndex(0);
			JOptionPane.showMessageDialog(ventana.getVentanaLibro(), "Debe ingresar datos del libro", "Mensaje",
			JOptionPane.ERROR_MESSAGE, new ImageIcon(getClass().getResource("/ima/Image007.png")));
		}
	}
	
	public boolean guardar(){
		int id;
		boolean ban=false;
		libros libro;
		libro=new libros(autenticacion);
		libro.setCota(ventana.getTfCota().getText());
		if(libro.validar(ventana.getTfCota().getText())){
			JOptionPane.showMessageDialog(ventana.getVentanaLibro(), "La cota ya existe", "Mensaje",
                    JOptionPane.ERROR_MESSAGE, new ImageIcon(getClass().getResource("/ima/Image007.png")));
		}
		else{
			libro.setTitulo(ventana.getTfTitulo().getText());
			libro.setEstante(ventana.getCbEstante().getText());
			libro.setId_sala(Integer.parseInt(ids.get(ventana.getCbSala().getSelectedIndex()).toString()));
			libro.buscarNuevoID();
			id=libro.getId_libro();
			if(libro.guardar()){
				System.out.println("Guardo libro");
				ban=true;
			}
			for(int i=0;i<conte;i++){
				if(liseje.get(i).getEstado()==1){
				ejemplares ejemplar=new ejemplares(autenticacion);
				ejemplar.buscarNuevoID();
				ejemplar.setId_libro(id);
				ejemplar.setNumero(Integer.parseInt(liseje.get(i).getTfNumeroL().getText()));
				if(ejemplar.guardar())
					System.out.println("Guardo ejemplar");
				}
			}
			for(int i=0;i<conted;i++){
				if(lisedi.get(i).getEstado()==1){
				ediciones edicion=new ediciones(autenticacion);
				edicion.buscarNuevoID();
				edicion.setAno(lisedi.get(i).getTfAno().getText());
				edicion.setEditorial(lisedi.get(i).getTfEditorial().getText());
				edicion.setId_libro(id);
				edicion.setLugar(lisedi.get(i).getTfLugar().getText());
				edicion.setNumero_edicion(lisedi.get(i).getTfNumero().getText());
				if(edicion.guardar())
					System.out.println("Guardo ediciones");
				}
			}
			for(int i=0;i<conta;i++){
				if(lisaut.get(i).getEstado()==1){
				autores autor=new autores(autenticacion);
				autor.buscarNuevoID();
				autor.setApellidos(lisaut.get(i).getTfApellido().getText());
				autor.setId_libro(id);
				autor.setNacionalidad(lisaut.get(i).getTfNacionalidad().getText());
				autor.setNombres(lisaut.get(i).getTfNombre().getText());
				if(autor.guardar())
					System.out.println("Guardo autores");
				}
			}
			if(ban){
				JOptionPane.showMessageDialog(ventana.getVentanaLibro(), "Guardado Exitosamente", "Mensaje",
                JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource("/ima/Image006.png")));
			}
		}
		return true;
	}
	
	public void llenarSalas(){
		salas sala = new salas(autenticacion);
		ResultSet salas = null;
		
		ventana.getCbSala().removeAllItems();
		ids.clear();
		salas = sala.getSalas();
		if(salas==null){
			JOptionPane.showMessageDialog(ventana.getVentanaLibro(), "No hay salas cargadas", "Mensaje",
                    JOptionPane.ERROR_MESSAGE, new ImageIcon(getClass().getResource("/ima/Image007.png")));
			//venSala.getPestanas().setSelectedIndex(0);
		}
		else{
			try {
				while(salas.next()){
					ventana.getCbSala().addItem(salas.getString("descripcion"));
					ids.add(salas.getInt("id_sala"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
