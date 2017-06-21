package GestionLibros;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import java.awt.Rectangle;
import javax.swing.BorderFactory;
import javax.swing.border.BevelBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class pnlEdiciones {

	private JPanel jPanel = null;  //  @jve:decl-index=0:visual-constraint="223,20"
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel2 = null;
	private JLabel jLabel3 = null;
	private JTextField tfEditorial = null;
	private JTextField tfAno = null;
	private JTextField tfLugar = null;
	private JTextField tfNumero = null;
	private JLabel eliminar = null;
	private GestorLibros gestor;
	private int estado,idEdicion;
	private editarLibros editar;

	public pnlEdiciones(int x, int y,GestorLibros gestor){
		getJPanel().setLocation(x, y);
		this.gestor = gestor;
	}
	
	public pnlEdiciones(int x, int y,editarLibros editar){
		getJPanel().setLocation(x, y);
		this.editar = editar;
	}
	
	public void ocultarX(){
		eliminar.setVisible(false);
	}
	
	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}
	
	public int getIdEdicion() {
		return idEdicion;
	}

	public void setIdEdicion(int idEdicion) {
		this.idEdicion = idEdicion;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	public JPanel getJPanel() {
		if (jPanel == null) {
			eliminar = new JLabel();
			eliminar.setBounds(new Rectangle(393, 4, 12, 16));
			eliminar.setText("X");
			eliminar.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseReleased(java.awt.event.MouseEvent e) {
					if(getEstado()==2)
						editar.eliminarEdiciones(getIdEdicion());
					if(getEstado()==3)//nuevos de editar
						editar.disminuirEdicion();
					if(getEstado()==1)
						gestor.disminuirEdicion();
					getJPanel().setVisible(false);
					setEstado(0);					
				}
			});
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(20, 78, 98, 20));
			jLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel3.setText("Numero Edicion:");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(20, 54, 98, 20));
			jLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel2.setText("Lugar:");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(21, 29, 98, 20));
			jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel1.setText("año:");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(21, 6, 98, 20));
			jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel.setText("Editorial:");
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.setBounds(new Rectangle(0, 0, 410, 104));
			jPanel.setBackground(Color.white);
			jPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			jPanel.add(jLabel, null);
			jPanel.add(jLabel1, null);
			jPanel.add(jLabel2, null);
			jPanel.add(jLabel3, null);
			jPanel.add(getTfEditorial(), null);
			jPanel.add(getTfAno(), null);
			jPanel.add(getTfLugar(), null);
			jPanel.add(getTfNumero(), null);
			jPanel.add(eliminar, null);
		}
		return jPanel;
	}

	/**
	 * This method initializes tfEditorial	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getTfEditorial() {
		if (tfEditorial == null) {
			tfEditorial = new JTextField();
			tfEditorial.setBounds(new Rectangle(125, 7, 263, 23));
		}
		return tfEditorial;
	}

	/**
	 * This method initializes tfAno	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getTfAno() {
		if (tfAno == null) {
			tfAno = new JTextField();
			tfAno.setBounds(new Rectangle(125, 31, 81, 23));
		}
		return tfAno;
	}

	/**
	 * This method initializes tfLugar	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getTfLugar() {
		if (tfLugar == null) {
			tfLugar = new JTextField();
			tfLugar.setBounds(new Rectangle(124, 55, 271, 23));
		}
		return tfLugar;
	}

	/**
	 * This method initializes tfNumero	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getTfNumero() {
		if (tfNumero == null) {
			tfNumero = new JTextField();
			tfNumero.setBounds(new Rectangle(126, 79, 80, 23));
		}
		return tfNumero;
	}
	
	public void inicializarEditorial(String i){
		getTfEditorial().setText(i);
	}
	
	public void inicializarAno(String i){
		getTfAno().setText(i);
	}
	
	public void inicializarLugar(String i){
		getTfLugar().setText(i);
	}
	
	public void inicializarNumero(String i){
		getTfNumero().setText(i);
	}

}
