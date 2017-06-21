package GestionLibros;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import Entidades.autores;
import Entidades.ediciones;
import Entidades.ejemplares;
import Entidades.libros;
import Entidades.salas;
import Utilitario.Autenticacion;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class editarLibros {	
	VentanaLibros ventana;
	private Autenticacion autenticacion;
	private int idBus;
	int y=0,y1=0,y2=0,conte=0,conted=0,conta=0;
	List<pnlEjemplar> liseje;
	List<pnlEdiciones> lisedi;
	List<pnlAutores> lisaut;
	List<pnlEjemplar> liseje2;
	List<pnlEdiciones> lisedi2;
	List<pnlAutores> lisaut2;
	private ArrayList ids;
	private int conte2,conte3,conte4;
	
	public editarLibros(VentanaLibros ventana, Autenticacion autenticacion, int idBus){
		this.ventana = ventana;
		this.autenticacion = autenticacion;
		this.idBus = idBus;
		liseje= new ArrayList<pnlEjemplar>();
		lisedi= new ArrayList<pnlEdiciones>();
		lisaut= new ArrayList<pnlAutores>();
		liseje2= new ArrayList<pnlEjemplar>();
		lisedi2= new ArrayList<pnlEdiciones>();
		lisaut2= new ArrayList<pnlAutores>();
		ids = new ArrayList();
		cargarLibro();
	}
	
	public void setIdBus(int idBus) {
		this.idBus = idBus;
	}

	public void cargarLibro(){
		libros libro = new libros(autenticacion);
		ResultSet libroC = null;
		String titulo;
		
		libroC = libro.getLibro(idBus);
		try {
			libroC.next();
			ventana.getTfCota().setText(libroC.getString("cota"));
			titulo = libroC.getString("titulo");
			ventana.getTfTitulo().setText(titulo);
			ventana.getCbEstante().setText(libroC.getString("estante"));
			cargarSala(libroC.getInt("id_sala"));
			cargarEjemplares(idBus,titulo);
			cargarEdiciones(idBus);
			cargarAutores(idBus);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void cargarSala(int idSala){
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
			ventana.getCbSala().setSelectedIndex(idSala-1);
		}
	}
	
	public void cargarEjemplares(int idLibro, String titulo){
		ejemplares ejem = new ejemplares(autenticacion);
		ResultSet ejemplar = null;
		//int conte2;
		
		ejemplar = ejem.getEjemplares(idLibro);
		conte2 = ejem.getCountEjemplares(idLibro);
		
		for(int i=0;i<conte2;i++){
			//System.out.println("contando ejemplares"+i+" contador: "+conte);
			pnlEjemplar pan = new pnlEjemplar(0,y,this);
			pan.setPos(i);// aqui estaba conte
			pan.setEstado(2);
			pan.inicializarLibro(titulo);
			try {
				ejemplar.next();
				pan.setIdEjemplar(ejemplar.getInt("id_ejemplar"));
				pan.inicializarEjemplar(String.valueOf(ejemplar.getInt("numero")));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ventana.getJPanel().add(pan.getPnlEjemplar());
			ventana.getJPanel().setSize(new Dimension(pan.getPnlEjemplar().getWidth(), pan.getPnlEjemplar().getHeight()+y));
			ventana.getJPanel().setPreferredSize(new Dimension(pan.getPnlEjemplar().getWidth(), pan.getPnlEjemplar().getHeight()+y));
			y+=pan.getPnlEjemplar().getHeight();
			liseje2.add(pan);
			//conte++;
			SwingUtilities.updateComponentTreeUI(ventana.getJPanel());
		}		
	}
	
	public void eliminarEjemplar(int id){
		ejemplares ejem = new ejemplares(autenticacion);
		if(ejem.eliminar(id)){
			JOptionPane.showMessageDialog(ventana.getVentanaLibro(), "Eliminado Exitosamente", "Mensaje",
	                JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource("/ima/Image006.png")));
		}
		else{
			JOptionPane.showMessageDialog(ventana.getVentanaLibro(), "No se pudo eliminar", "Mensaje",
	        JOptionPane.ERROR_MESSAGE, new ImageIcon(getClass().getResource("/ima/Image007.png")));
		}
	}
	
	public void cargarEdiciones(int idLibro){
		ediciones edicion = new ediciones(autenticacion);
		ResultSet ediciones = null;
		//int conte2;
		
		ediciones = edicion.getEdiciones(idLibro);
		conte3 = edicion.getCountEjemplares(idLibro);
		
		for(int i=0;i<conte3;i++){
			pnlEdiciones pan = new pnlEdiciones(0,y1,this); 
			pan.setEstado(2);
			try {
				ediciones.next();
				pan.setIdEdicion(ediciones.getInt("id_edicion"));
				pan.inicializarAno(ediciones.getString("ano"));
				pan.inicializarEditorial(ediciones.getString("editorial"));
				pan.inicializarLugar(ediciones.getString("lugar"));
				pan.inicializarNumero(ediciones.getString("numero_edicion"));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ventana.getJPanel1().add(pan.getJPanel());
			ventana.getJPanel1().setSize(new Dimension(pan.getJPanel().getWidth(), pan.getJPanel().getHeight()+y1));
			ventana.getJPanel1().setPreferredSize(new Dimension(pan.getJPanel().getWidth(), pan.getJPanel().getHeight()+y1));
			y1+=pan.getJPanel().getHeight();
			lisedi2.add(pan);
			//conted++;
			SwingUtilities.updateComponentTreeUI(ventana.getJPanel1());
		}
	}
	
	public void eliminarEdiciones(int id){
		ediciones edicion = new ediciones(autenticacion);
		if(edicion.eliminar(id)){
			JOptionPane.showMessageDialog(ventana.getVentanaLibro(), "Eliminado Exitosamente", "Mensaje",
	                JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource("/ima/Image006.png")));
		}
		else{
			JOptionPane.showMessageDialog(ventana.getVentanaLibro(), "No se pudo eliminar", "Mensaje",
	        JOptionPane.ERROR_MESSAGE, new ImageIcon(getClass().getResource("/ima/Image007.png")));
		}
	}
	
	public void cargarAutores(int idLibro){
		autores autor = new autores(autenticacion);
		ResultSet autores = null;
		//int conte2;
		
		autores = autor.getAutores(idLibro);
		conte4 = autor.getCountEjemplares(idLibro);
		
		for(int i=0;i<conte4;i++){
			pnlAutores pan = new pnlAutores(0,y2,this);
			pan.setEstado(2);
			try {
				autores.next();
				pan.setIdAutor(autores.getInt("id_autor"));
				pan.inicializarApellido(autores.getString("apellidos"));
				pan.inicializarNacionalidad(autores.getString("nacionalidad"));
				pan.inicializarNombre(autores.getString("nombres"));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ventana.getJPanel2().add(pan.getJPanel());
			ventana.getJPanel2().setSize(new Dimension(pan.getJPanel().getWidth(), pan.getJPanel().getHeight()+y2));
			ventana.getJPanel2().setPreferredSize(new Dimension(pan.getJPanel().getWidth(), pan.getJPanel().getHeight()+y2));
			y2+=pan.getJPanel().getHeight();
			lisaut2.add(pan);
			//conta++;
			SwingUtilities.updateComponentTreeUI(ventana.getJPanel2());
		}
	}
	
	public void eliminarAutores(int id){
		autores autor = new autores(autenticacion);
		if(autor.eliminar(id)){
			JOptionPane.showMessageDialog(ventana.getVentanaLibro(), "Eliminado Exitosamente", "Mensaje",
	                JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource("/ima/Image006.png")));
		}
		else{
			JOptionPane.showMessageDialog(ventana.getVentanaLibro(), "No se pudo eliminar", "Mensaje",
	        JOptionPane.ERROR_MESSAGE, new ImageIcon(getClass().getResource("/ima/Image007.png")));
		}
	}
	
	public void AddPnlEjemplar(){
		if(conte>0)
			liseje.get(conte-1).ocultarX();
		pnlEjemplar pan = new pnlEjemplar(0,y,this);
		pan.setPos(conte);
		pan.setEstado(3);
		pan.inicializarLibro(ventana.getTfTitulo().getText());
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
		pan.setEstado(3);
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
		pan.setEstado(3);
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
	
	public boolean guardar(){
		int id;
		//ids = 
		boolean ban=false;
		libros libro;
		libro=new libros(autenticacion);
		libro.setCota(ventana.getTfCota().getText());
		/*if(libro.validar(ventana.getTfCota().getText())){
			JOptionPane.showMessageDialog(ventana.getVentanaLibro(), "La cota ya existe", "Mensaje",
                    JOptionPane.ERROR_MESSAGE, new ImageIcon(getClass().getResource("/ima/Image007.png")));
		}*/
		//else{
			libro.setTitulo(ventana.getTfTitulo().getText());
			libro.setEstante(ventana.getCbEstante().getText());
			libro.setId_sala(Integer.parseInt(ids.get(ventana.getCbSala().getSelectedIndex()).toString()));
			//libro.buscarNuevoID();
			//id=libro.getId_libro();
			if(libro.actualizar(idBus)){
				System.out.println("Guardo libro");
				ban=true;
			}
			for(int i=0;i<conte;i++){
				if(liseje.get(i).getEstado()!=0){
				ejemplares ejemplar=new ejemplares(autenticacion);
				ejemplar.buscarNuevoID();
				ejemplar.setId_libro(idBus);
				ejemplar.setNumero(Integer.parseInt(liseje.get(i).getTfNumeroL().getText()));
				if(ejemplar.guardar())
					System.out.println("Guardo ejemplar");
				}
			}
			for(int i=0;i<conted;i++){
				if(lisedi.get(i).getEstado()!=0){
				ediciones edicion=new ediciones(autenticacion);
				edicion.buscarNuevoID();
				edicion.setAno(lisedi.get(i).getTfAno().getText());
				edicion.setEditorial(lisedi.get(i).getTfEditorial().getText());
				edicion.setId_libro(idBus);
				edicion.setLugar(lisedi.get(i).getTfLugar().getText());
				edicion.setNumero_edicion(lisedi.get(i).getTfNumero().getText());
				if(edicion.guardar())
					System.out.println("Guardo ediciones");
				}
			}
			for(int i=0;i<conta;i++){
				if(lisaut.get(i).getEstado()!=0){
				autores autor=new autores(autenticacion);
				autor.buscarNuevoID();
				autor.setApellidos(lisaut.get(i).getTfApellido().getText());
				autor.setId_libro(idBus);
				autor.setNacionalidad(lisaut.get(i).getTfNacionalidad().getText());
				autor.setNombres(lisaut.get(i).getTfNombre().getText());
				if(autor.guardar())
					System.out.println("Guardo autores");
				}
			}
			// los editados
			for(int i=0;i<conte2;i++){
				if(liseje2.get(i).getEstado()!=0){
				ejemplares ejemplar=new ejemplares(autenticacion);
				//ejemplar.buscarNuevoID();
				ejemplar.setId_libro(idBus);
				ejemplar.setNumero(Integer.parseInt(liseje2.get(i).getTfNumeroL().getText()));
				if(ejemplar.actualizar(idBus))
					System.out.println("Guardo ejemplar");
				}
			}
			for(int i=0;i<conte3;i++){
				if(lisedi2.get(i).getEstado()!=0){
				ediciones edicion=new ediciones(autenticacion);
				//edicion.buscarNuevoID();
				edicion.setAno(lisedi2.get(i).getTfAno().getText());
				edicion.setEditorial(lisedi2.get(i).getTfEditorial().getText());
				edicion.setId_libro(idBus);
				edicion.setLugar(lisedi2.get(i).getTfLugar().getText());
				edicion.setNumero_edicion(lisedi2.get(i).getTfNumero().getText());
				if(edicion.actualizar(idBus))
					System.out.println("Guardo ediciones");
				}
			}
			for(int i=0;i<conte4;i++){
				if(lisaut2.get(i).getEstado()!=0){
				autores autor=new autores(autenticacion);
				//autor.buscarNuevoID();
				autor.setApellidos(lisaut2.get(i).getTfApellido().getText());
				autor.setId_libro(idBus);
				autor.setNacionalidad(lisaut2.get(i).getTfNacionalidad().getText());
				autor.setNombres(lisaut2.get(i).getTfNombre().getText());
				if(autor.actualizar(idBus))
					System.out.println("Guardo autores");
				}
			}
			//
			if(ban){
				JOptionPane.showMessageDialog(ventana.getVentanaLibro(), "Guardado Exitosamente", "Mensaje",
                JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource("/ima/Image006.png")));
			}
	//	}
		return true;
	}
}
