package GestionLibros;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Rectangle;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

import Utilitario.Centrar;

public class administrarSalas {

	private JInternalFrame ventanaAdministrar = null;  //  @jve:decl-index=0:visual-constraint="163,23"
	private JPanel panelAdministrar = null;
	private JTabbedPane pestanas = null;
	private JPanel addSala = null;
	private JPanel editSala = null;
	private JTextField nomSala = null;
	private JButton saveSalaNew = null;
	private JComboBox listSalas = null;
	private JTextField editSalaName = null;
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JRadioButton actSala = null;
	private JRadioButton desSala = null;
	private JButton saveEditSala = null;
	private JLabel jLabel2 = null;
	private ButtonGroup grupo;
	private GestorSalas gestor;  //  @jve:decl-index=0:
	
	public void setGestor(GestorSalas gestor) {
		this.gestor = gestor;
	}

	public administrarSalas(Dimension d){
		grupo = new ButtonGroup();
		getVentanaAdministrar();
		getVentanaAdministrar().setLocation(Centrar.centrarEnComponente(getVentanaAdministrar().getSize(), d));
		ventanaAdministrar.setVisible(true);
	}

	/**
	 * This method initializes ventanaAdministrar	
	 * 	
	 * @return javax.swing.JInternalFrame	
	 */
	public JInternalFrame getVentanaAdministrar() {
		if (ventanaAdministrar == null) {
			ventanaAdministrar = new JInternalFrame();
			ventanaAdministrar.setSize(new Dimension(267, 222));
			ventanaAdministrar.setClosable(true);
			ventanaAdministrar.setFrameIcon(new ImageIcon(getClass().getResource("/ima/libro2.gif")));
			ventanaAdministrar.setTitle("Administrar Salas");			
			ventanaAdministrar.setContentPane(getPanelAdministrar());			
		}
		return ventanaAdministrar;
	}

	/**
	 * This method initializes panelAdministrar	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelAdministrar() {
		if (panelAdministrar == null) {
			panelAdministrar = new JPanel();
			panelAdministrar.setLayout(new BorderLayout());
			panelAdministrar.add(getPestanas(), BorderLayout.CENTER);
		}
		return panelAdministrar;
	}

	/**
	 * This method initializes pestanas	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	public JTabbedPane getPestanas() {
		if (pestanas == null) {
			pestanas = new JTabbedPane();
			pestanas.addTab("Agregar Sala", new ImageIcon(getClass().getResource("/ima/Image073.png")), getAddSala(), null);
			pestanas.addTab("Editar Sala", new ImageIcon(getClass().getResource("/ima/libro1.jpg")), getEditSala(), null);
			pestanas.addChangeListener(new javax.swing.event.ChangeListener() {
				public void stateChanged(javax.swing.event.ChangeEvent e) {
					if(pestanas.getSelectedIndex()==1){
						gestor.llenarSalas();
					} 
				}
			});
		}
		return pestanas;
	}

	/**
	 * This method initializes addSala	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getAddSala() {
		if (addSala == null) {
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(9, 9, 114, 16));
			jLabel2.setText("Nombre de la Sala");
			addSala = new JPanel();
			addSala.setLayout(null);
			addSala.add(getNomSala(), null);
			addSala.add(getSaveSalaNew(), null);
			addSala.add(jLabel2, null);
		}
		return addSala;
	}

	/**
	 * This method initializes editSala	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getEditSala() {
		if (editSala == null) {
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(8, 52, 51, 16));
			jLabel1.setText("Nombre");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(8, 5, 85, 16));
			jLabel.setText("Lista de Salas");
			editSala = new JPanel();
			editSala.setLayout(null);
			editSala.add(getListSalas(), null);
			editSala.add(getEditSalaName(), null);
			editSala.add(jLabel, null);
			editSala.add(jLabel1, null);
			grupo.add(getActSala());
			grupo.add(getDesSala());
			editSala.add(getActSala(), null);
			editSala.add(getDesSala(), null);
			editSala.add(getSaveEditSala(), null);
		}
		return editSala;
	}

	/**
	 * This method initializes nomSala	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getNomSala() {
		if (nomSala == null) {
			nomSala = new JTextField();
			nomSala.setBounds(new Rectangle(9, 32, 235, 23));
		}
		return nomSala;
	}

	/**
	 * This method initializes saveSalaNew	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getSaveSalaNew() {
		if (saveSalaNew == null) {
			saveSalaNew = new JButton();
			saveSalaNew.setBounds(new Rectangle(74, 63, 111, 23));
			saveSalaNew.setText("Guardar");
			saveSalaNew.setIcon(new ImageIcon(getClass().getResource("/ima/3floppy_unmount.png")));
			saveSalaNew.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.guardar();
				}
			});
		}
		return saveSalaNew;
	}

	/**
	 * This method initializes listSalas	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox getListSalas() {
		if (listSalas == null) {
			listSalas = new JComboBox();
			listSalas.setBounds(new Rectangle(7, 26, 233, 22));
			listSalas.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.llenarNombre();
				}
			});
		}
		return listSalas;
	}

	/**
	 * This method initializes editSalaName	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getEditSalaName() {
		if (editSalaName == null) {
			editSalaName = new JTextField();
			editSalaName.setBounds(new Rectangle(9, 74, 228, 23));
		}
		return editSalaName;
	}

	/**
	 * This method initializes actSala	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	public JRadioButton getActSala() {
		if (actSala == null) {
			actSala = new JRadioButton();
			actSala.setBounds(new Rectangle(11, 107, 70, 21));
			actSala.setSelected(true);
			actSala.setText("Activa");
		}
		return actSala;
	}

	/**
	 * This method initializes desSala	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	public JRadioButton getDesSala() {
		if (desSala == null) {
			desSala = new JRadioButton();
			desSala.setBounds(new Rectangle(76, 108, 95, 21));
			desSala.setText("Desactivar");
		}
		return desSala;
	}

	/**
	 * This method initializes saveEditSala	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getSaveEditSala() {
		if (saveEditSala == null) {
			saveEditSala = new JButton();
			saveEditSala.setBounds(new Rectangle(76, 133, 109, 24));
			saveEditSala.setText("Guardar");
			saveEditSala.setIcon(new ImageIcon(getClass().getResource("/ima/3floppy_unmount.png")));
			saveEditSala.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.guardarActualizar();
 				}
			});
		}
		return saveEditSala;
	}

}
