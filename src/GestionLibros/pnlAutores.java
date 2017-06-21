package GestionLibros;

import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.border.BevelBorder;
import java.awt.Rectangle;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class pnlAutores {

	private JPanel jPanel = null;  //  @jve:decl-index=0:visual-constraint="249,7"
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel2 = null;
	private JTextField tfNombre = null;
	private JTextField tfApellido = null;
	private JTextField tfNacionalidad = null;
	private JLabel eliminar = null;
	private GestorLibros gestor;
	private int estado;
	private editarLibros editar;
	private int idAutor;

	public pnlAutores(int x, int y,GestorLibros gestor){
		getJPanel().setLocation(x, y);
		this.gestor = gestor;
	}
	
	public pnlAutores(int x, int y, editarLibros editar){
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

	public int getIdAutor() {
		return idAutor;
	}

	public void setIdAutor(int idAutor) {
		this.idAutor = idAutor;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	public JPanel getJPanel() {
		if (jPanel == null) {
			eliminar = new JLabel();
			eliminar.setBounds(new Rectangle(390, 5, 14, 16));
			eliminar.setText("X");
			eliminar.addMouseListener(new java.awt.event.MouseAdapter() {
				public void mouseReleased(java.awt.event.MouseEvent e) {
					if(getEstado()==2)
						editar.eliminarAutores(getIdAutor());
					if(getEstado()==3)//nuevos de editar
						editar.disminuirAutor();
					if(getEstado()==1)
						gestor.disminuirAutor();
					getJPanel().setVisible(false);
					setEstado(0);					
				}
			});
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(7, 57, 78, 21));
			jLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel2.setText("Nacionalidad:");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(7, 31, 78, 21));
			jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel1.setText("Apellido:");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(6, 7, 78, 21));
			jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel.setText("Nombre:");
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			jPanel.setBounds(new Rectangle(0, 0, 410, 81));
			jPanel.setBackground(Color.white);
			jPanel.add(jLabel, null);
			jPanel.add(jLabel1, null);
			jPanel.add(jLabel2, null);
			jPanel.add(getTfNombre(), null);
			jPanel.add(getTfApellido(), null);
			jPanel.add(getTfNacionalidad(), null);
			jPanel.add(eliminar, null);
		}
		return jPanel;
	}

	/**
	 * This method initializes tfNombre	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getTfNombre() {
		if (tfNombre == null) {
			tfNombre = new JTextField();
			tfNombre.setBounds(new Rectangle(88, 7, 294, 23));
		}
		return tfNombre;
	}

	/**
	 * This method initializes tfApellido	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getTfApellido() {
		if (tfApellido == null) {
			tfApellido = new JTextField();
			tfApellido.setBounds(new Rectangle(89, 32, 291, 23));
		}
		return tfApellido;
	}

	/**
	 * This method initializes tfNacionalidad	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getTfNacionalidad() {
		if (tfNacionalidad == null) {
			tfNacionalidad = new JTextField();
			tfNacionalidad.setBounds(new Rectangle(89, 56, 163, 23));
		}
		return tfNacionalidad;
	}
	
	public void inicializarNombre(String i){
		getTfNombre().setText(i);
	}

	public void inicializarApellido(String i){
		getTfApellido().setText(i);
	}
	
	public void inicializarNacionalidad(String i){
		getTfNacionalidad().setText(i);
	}
}
