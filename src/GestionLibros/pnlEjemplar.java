package GestionLibros;

import javax.swing.JPanel;
import java.awt.Rectangle;
import javax.swing.BorderFactory;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class pnlEjemplar {

	private JPanel pnlEjemplar = null;  //  @jve:decl-index=0:visual-constraint="232,48"
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JTextField tfLibro = null;
	private JTextField tfNumeroL = null;
	private int pos;
	private JLabel eliminar = null;
	private GestorLibros gestor;
	private int estado,idEjemplar;
	private editarLibros editar;

	public pnlEjemplar(int x, int y, GestorLibros gestor){
		getPnlEjemplar().setLocation(x, y);
		pos=0;
		this.gestor = gestor;
	}
	
	public pnlEjemplar(int x, int y, editarLibros editar){
		getPnlEjemplar().setLocation(x, y);
		pos=0;
		this.editar = editar;
	}
	
	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}
	
	public void ocultarX(){
		eliminar.setVisible(false);
	}

	public int getIdEjemplar() {
		return idEjemplar;
	}

	public void setIdEjemplar(int idEjemplar) {
		this.idEjemplar = idEjemplar;
	}

	/**
	 * This method initializes pnlEjemplar	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	public JPanel getPnlEjemplar() {
		if (pnlEjemplar == null) {
			eliminar = new JLabel();
			eliminar.setBounds(new Rectangle(392, 4, 16, 16));
			eliminar.setText("X");
			eliminar.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseReleased(java.awt.event.MouseEvent e) {
					//System.out.println("elimina"); 
					if(getEstado()==2)
						editar.eliminarEjemplar(getIdEjemplar());
					if(getEstado()==3)//nuevos de editar
						editar.disminuirEjemplar();
					if(getEstado()==1)
						gestor.disminuirEjemplar();
					getPnlEjemplar().setVisible(false);
					setEstado(0);
				}
			});
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(12, 35, 105, 22));
			jLabel1.setText("Numero ejemplar:");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(82, 7, 36, 22));
			jLabel.setText("Libro:");
			pnlEjemplar = new JPanel();
			pnlEjemplar.setLayout(null);
			pnlEjemplar.setBounds(new Rectangle(0, 0, 410, 61));
			pnlEjemplar.setBackground(Color.white);
			pnlEjemplar.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			pnlEjemplar.add(jLabel, null);
			pnlEjemplar.add(jLabel1, null);
			pnlEjemplar.add(getTfLibro(), null);
			pnlEjemplar.add(getTfNumeroL(), null);
			pnlEjemplar.add(eliminar, null);
		}
		return pnlEjemplar;
	}

	/**
	 * This method initializes tfLibro	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getTfLibro() {
		if (tfLibro == null) {
			tfLibro = new JTextField();
			tfLibro.setBounds(new Rectangle(127, 8, 262, 23));
		}
		return tfLibro;
	}

	/**
	 * This method initializes tfNumeroL	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getTfNumeroL() {
		if (tfNumeroL == null) {
			tfNumeroL = new JTextField();
			tfNumeroL.setBounds(new Rectangle(126, 35, 98, 23));
		}
		return tfNumeroL;
	}
public void inicializarLibro(String l){
		getTfLibro().setText(l);
	}
	
	public void inicializarEjemplar(String l){
		getTfNumeroL().setText(l);
	}

}
