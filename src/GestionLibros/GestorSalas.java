package GestionLibros;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import Utilitario.Autenticacion;

import Entidades.salas;

public class GestorSalas {
	private administrarSalas venSala;
	private ArrayList ids;
	private Autenticacion autenticacion;
	
	public GestorSalas(administrarSalas venSala, Autenticacion autenticacion){
		this.venSala = venSala;
		ids = new ArrayList();
		this.autenticacion = autenticacion;
	}
	
	public boolean guardar(){
		salas sala = new salas(autenticacion);
		if(venSala.getNomSala().getText().compareTo("")==0){
			JOptionPane.showMessageDialog(venSala.getVentanaAdministrar(), "Debe llenar el campo nombre", "Mensaje",
                    JOptionPane.ERROR_MESSAGE, new ImageIcon(getClass().getResource("/ima/Image007.png")));
		}
		else{
			sala.setDescripcion(venSala.getNomSala().getText());
			sala.setActivo(1);
			sala.buscarNuevoID();
			if(sala.validar(venSala.getNomSala().getText())){
				JOptionPane.showMessageDialog(venSala.getVentanaAdministrar(), "La sala ya existe", "Mensaje",
	                    JOptionPane.ERROR_MESSAGE, new ImageIcon(getClass().getResource("/ima/Image007.png")));
			}
			else{
				if(sala.guardar()){
					JOptionPane.showMessageDialog(venSala.getVentanaAdministrar(), "Guardado Exitosamente", "Mensaje",
                    JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource("/ima/Image006.png")));
				}
			}
		}
		return true;
	}
	
	public void llenarSalas(){
		salas sala = new salas(autenticacion);
		ResultSet salas = null;
		
		//if(venSala.getListSalas().getItemCount()>0)
		venSala.getListSalas().removeAllItems();//error aqui 
		ids.clear();
		salas = sala.getSalas();
		if(salas==null){
			JOptionPane.showMessageDialog(venSala.getVentanaAdministrar(), "No hay salas cargadas", "Mensaje",
                    JOptionPane.ERROR_MESSAGE, new ImageIcon(getClass().getResource("/ima/Image007.png")));
			venSala.getPestanas().setSelectedIndex(0);
		}
		else{
			try {
				while(salas.next()){
					venSala.getListSalas().addItem(salas.getString("descripcion"));
					ids.add(salas.getInt("id_sala"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void llenarNombre(){
		salas sala = new salas(autenticacion);
		//venSala.getEditSalaName().setText("");
		//if(venSala.getListSalas().getSelectedItem().toString()!=null)
		if(venSala.getListSalas().getItemCount()<=0) return;
			venSala.getEditSalaName().setText(venSala.getListSalas().getSelectedItem().toString());
		if(sala.buscarActivo(venSala.getListSalas().getSelectedItem().toString())==0){
			venSala.getActSala().setSelected(false);
			venSala.getDesSala().setSelected(true);
		}
		else{
			venSala.getActSala().setSelected(true);
			venSala.getDesSala().setSelected(false);
		}
	}
	
	public void guardarActualizar(){
		salas sala = new salas(autenticacion);
		if(venSala.getEditSalaName().getText().compareTo("")==0){
			JOptionPane.showMessageDialog(venSala.getVentanaAdministrar(), "Debe llenar el campo nombre", "Mensaje",
                    JOptionPane.ERROR_MESSAGE, new ImageIcon(getClass().getResource("/ima/Image007.png")));
		}
		else{
			sala.setDescripcion(venSala.getEditSalaName().getText());
			if(venSala.getActSala().isSelected())
				sala.setActivo(1);
			else
				sala.setActivo(0);
			if(sala.actualizar(Integer.parseInt(ids.get(venSala.getListSalas().getSelectedIndex()).toString()))){
					JOptionPane.showMessageDialog(venSala.getVentanaAdministrar(), "Guardado Exitosamente", "Mensaje",
                    JOptionPane.INFORMATION_MESSAGE, new ImageIcon(getClass().getResource("/ima/Image006.png")));
					llenarSalas();
			}
		}
	}

}
