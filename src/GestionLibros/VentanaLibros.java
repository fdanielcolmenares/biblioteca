package GestionLibros;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JTabbedPane;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Rectangle;
import javax.swing.JTextField;
import java.awt.Point;
import javax.swing.SwingConstants;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.JButton;

import Utilitario.Centrar;

import java.awt.FlowLayout;

public class VentanaLibros {

	private JInternalFrame ventanaLibro = null;  //  @jve:decl-index=0:visual-constraint="175,22"
	private JPanel jContentPane = null;
	private JTabbedPane contenedor = null;
	private JPanel libro = null;
	private JPanel ejemplar = null;
	private JPanel edicion = null;
	private JPanel autor = null;
	private JLabel jLabel = null;
	private JTextField tfCota = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel2 = null;
	private JTextField tfTitulo = null;
	private JLabel jLabel3 = null;
	private JComboBox cbSala = null;
	private JScrollPane jScrollPane1 = null;
	private JScrollPane jScrollPane2 = null;
	private JButton jbAddAutor = null;
	private JButton jbAddEdicion = null;
	private JScrollPane jScrollPane = null;
	private JButton jbAddEjemplar = null;
	private GestorLibros gestor;  //  @jve:decl-index=0:
	private JButton guardar = null;
	private JPanel jPanel = null;  //  @jve:decl-index=0:visual-constraint="37,105"
	private JPanel jPanel1 = null;
	private JPanel jPanel2 = null;
	private JTextField cbEstante = null;
	private int tipo;
	private editarLibros editar;
	
	public VentanaLibros(Dimension d, int t){
		tipo = t;
		getVentanaLibro();
		getVentanaLibro().setLocation(Centrar.centrarEnComponente(getVentanaLibro().getSize(), d));
	}

	public void setGestor(GestorLibros gestor) {
		this.gestor = gestor;
		gestor.llenarSalas();
	}	

	public void setEditar(editarLibros editar) {
		this.editar = editar;
	}

	/**
	 * This method initializes ventanaLibro	
	 * 	
	 * @return javax.swing.JInternalFrame	
	 */
	public JInternalFrame getVentanaLibro() {
		if (ventanaLibro == null) {
			ventanaLibro = new JInternalFrame();
			ventanaLibro.setSize(new Dimension(458, 280));
			ventanaLibro.setClosable(true);
			ventanaLibro.setName("");
			ventanaLibro.setTitle("Gestion de Libros");
			ventanaLibro.setFrameIcon(new ImageIcon(getClass().getResource("/ima/libros.gif")));
			ventanaLibro.setVisible(false);
			ventanaLibro.setContentPane(getJContentPane());
		}
		return ventanaLibro;
	}

	/**
	 * This method initializes jContentPane	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getContenedor(), BorderLayout.CENTER);
			jContentPane.add(getGuardar(), null);
		}
		return jContentPane;
	}

	/**
	 * This method initializes contenedor	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	public JTabbedPane getContenedor() {
		if (contenedor == null) {
			contenedor = new JTabbedPane();
			contenedor.setBounds(new Rectangle(1, 1, 446, 217));
			contenedor.addTab("Libro", new ImageIcon(getClass().getResource("/ima/libro1.jpg")), getLibro(), "Ingresar o editar datos del libro");
			contenedor.addTab("Ejemplares", new ImageIcon(getClass().getResource("/ima/libro2.gif")), getEjemplar(), "Ingresar o editar ejemplares");
			contenedor.addTab("Ediciones", new ImageIcon(getClass().getResource("/ima/edicion.jpg")), getEdicion(), "Ingresar o editar ediciones del libro");
			contenedor.addTab("Autores", new ImageIcon(getClass().getResource("/ima/autor.jpg")), getAutor(), "Ingresar o editar autores del libro");
			contenedor.addChangeListener(new javax.swing.event.ChangeListener() {
				public void stateChanged(javax.swing.event.ChangeEvent e) {
					if(contenedor.getSelectedIndex()>0 && tipo==0){
						//System.out.println("entre pestaña0");
						gestor.validarLibro(); 
					}
				}
			});
		}
		return contenedor;
	}

	/**
	 * This method initializes libro	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getLibro() {
		if (libro == null) {
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(8, 109, 50, 20));
			jLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel3.setText("Sala:");
			jLabel2 = new JLabel();
			jLabel2.setText("Estante:");
			jLabel2.setSize(new Dimension(50, 20));
			jLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel2.setLocation(new Point(7, 78));
			jLabel1 = new JLabel();
			jLabel1.setText("Titulo:");
			jLabel1.setSize(new Dimension(50, 20));
			jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel1.setLocation(new Point(8, 44));
			jLabel = new JLabel();
			jLabel.setText("Cota:");
			jLabel.setSize(new Dimension(50, 20));
			jLabel.setHorizontalTextPosition(SwingConstants.TRAILING);
			jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
			jLabel.setLocation(new Point(8, 14));
			libro = new JPanel();
			libro.setLayout(null);
			libro.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			libro.add(jLabel, null);
			libro.add(getTfCota(), null);
			libro.add(jLabel1, null);
			libro.add(jLabel2, null);
			libro.add(getTfTitulo(), null);
			libro.add(jLabel3, null);
			libro.add(getCbSala(), null);
			libro.add(getCbEstante(), null);
		}
		return libro;
	}

	/**
	 * This method initializes ejemplar	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getEjemplar() {
		if (ejemplar == null) {
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.fill = GridBagConstraints.BOTH;
			gridBagConstraints2.gridy = 0;
			gridBagConstraints2.weightx = 1.0;
			gridBagConstraints2.weighty = 1.0;
			gridBagConstraints2.gridx = 0;
			ejemplar = new JPanel();
			ejemplar.setLayout(null);
			ejemplar.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			ejemplar.add(getJScrollPane(), gridBagConstraints2);
			ejemplar.add(getJbAddEjemplar(), null);
		}
		return ejemplar;
	}

	/**
	 * This method initializes edicion	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getEdicion() {
		if (edicion == null) {
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			gridBagConstraints.gridy = 0;
			gridBagConstraints.weightx = 1.0;
			gridBagConstraints.weighty = 1.0;
			gridBagConstraints.gridx = 0;
			edicion = new JPanel();
			edicion.setLayout(null);
			edicion.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			edicion.add(getJScrollPane1(), gridBagConstraints);
			edicion.add(getJbAddEdicion(), null);
		}
		return edicion;
	}

	/**
	 * This method initializes autor	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getAutor() {
		if (autor == null) {
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.fill = GridBagConstraints.BOTH;
			gridBagConstraints1.gridy = 0;
			gridBagConstraints1.weightx = 1.0;
			gridBagConstraints1.weighty = 1.0;
			gridBagConstraints1.gridx = 0;
			autor = new JPanel();
			autor.setLayout(null);
			autor.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			autor.add(getJScrollPane2(), gridBagConstraints1);
			autor.add(getJbAddAutor(), null);
		}
		return autor;
	}

	/**
	 * This method initializes tfCota	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getTfCota() {
		if (tfCota == null) {
			tfCota = new JTextField();
			tfCota.setLocation(new Point(63, 10));
			tfCota.setSize(new Dimension(161, 25));
		}
		return tfCota;
	}

	/**
	 * This method initializes tfTitulo	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getTfTitulo() {
		if (tfTitulo == null) {
			tfTitulo = new JTextField();
			tfTitulo.setSize(new Dimension(370, 25));
			tfTitulo.setLocation(new Point(63, 42));
		}
		return tfTitulo;
	}

	/**
	 * This method initializes cbSala	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox getCbSala() {
		if (cbSala == null) {
			//String[] h = {"Isaac","Santana","lilian"};
			cbSala = new JComboBox();
			cbSala.setLocation(new Point(63, 106));
			cbSala.setPreferredSize(new Dimension(36, 25));
			cbSala.setSize(new Dimension(161, 25));
		}
		return cbSala;
	}

	/**
	 * This method initializes jScrollPane1	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			jScrollPane1.setSize(new Dimension(439, 155));
			jScrollPane1.setViewportView(getJPanel1());
			jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		}
		return jScrollPane1;
	}

	/**
	 * This method initializes jScrollPane2	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane2() {
		if (jScrollPane2 == null) {
			jScrollPane2 = new JScrollPane();
			//jScrollPane2.setLayout(null);
			jScrollPane2.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
			jScrollPane2.setSize(new Dimension(439, 155));
			jScrollPane2.setViewportView(getJPanel2());
			jScrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		}
		return jScrollPane2;
	}

	/**
	 * This method initializes jbAddAutor	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJbAddAutor() {
		if (jbAddAutor == null) {
			jbAddAutor = new JButton();
			jbAddAutor.setBounds(new Rectangle(333, 159, 106, 20));
			jbAddAutor.setIcon(new ImageIcon(getClass().getResource("/ima/Image073.png")));
			jbAddAutor.setText("Agregar");
			/*if(tipo==1) //System.out.println("ttpo igual a 1");
				jbAddAutor.setVisible(false);*/
			jbAddAutor.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(tipo==0)
						gestor.AddPnlAutor();
					if(tipo==1)
						editar.AddPnlAutor();
				}
			});
		}
		return jbAddAutor;
	}

	/**
	 * This method initializes jbAddEdicion	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJbAddEdicion() {
		if (jbAddEdicion == null) {
			jbAddEdicion = new JButton();
			jbAddEdicion.setText("Agregar");
			jbAddEdicion.setIcon(new ImageIcon(getClass().getResource("/ima/Image073.png")));
			jbAddEdicion.setBounds(new Rectangle(333, 159, 106, 20));
			/*if(tipo==1)
				jbAddEdicion.setVisible(false);*/
			jbAddEdicion.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(tipo==0)
						gestor.AddPnlEdicion();
					if(tipo==1)
						editar.AddPnlEdicion();
				}
			});
		}
		return jbAddEdicion;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	public JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setSize(new Dimension(439, 155));
			jScrollPane.setPreferredSize(new Dimension(22, 22));
				jScrollPane.setViewportView(getJPanel());
			jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			jScrollPane.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		}
		return jScrollPane;
	}

	/**
	 * This method initializes jbAddEjemplar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJbAddEjemplar() {
		if (jbAddEjemplar == null) {
			jbAddEjemplar = new JButton();
			jbAddEjemplar.setBounds(new Rectangle(334, 159, 105, 20));
			jbAddEjemplar.setIcon(new ImageIcon(getClass().getResource("/ima/Image073.png")));
			jbAddEjemplar.setText("Agregar");
			/*if(tipo==1)
				jbAddEjemplar.setVisible(false);*/
			jbAddEjemplar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(tipo==0)
						gestor.AddPnlEjemplar();
					if(tipo==1)
						editar.AddPnlEjemplar();
				}
			});
		}
		return jbAddEjemplar;
	}

	/**
	 * This method initializes guardar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getGuardar() {
		if (guardar == null) {
			guardar = new JButton();
			guardar.setBounds(new Rectangle(331, 220, 108, 24));
			guardar.setIcon(new ImageIcon(getClass().getResource("/ima/3floppy_unmount.png")));
			guardar.setText("Guardar");
			guardar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(tipo==0)
						gestor.guardar();
					if(tipo==1){
						editar.guardar();
					}
				}
			});
		}
		return guardar;
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	public JPanel getJPanel() {
		if (jPanel == null) {
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.setSize(new Dimension(64, 35));
		}
		return jPanel;
	}

	/**
	 * This method initializes jPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	public JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			jPanel1.setLayout(null);
		}
		return jPanel1;
	}

	/**
	 * This method initializes jPanel2	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	public JPanel getJPanel2() {
		if (jPanel2 == null) {
			jPanel2 = new JPanel();
			jPanel2.setLayout(null);
		}
		return jPanel2;
	}

	/**
	 * This method initializes cbEstante	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getCbEstante() {
		if (cbEstante == null) {
			cbEstante = new JTextField();
			cbEstante.setLocation(new Point(63, 76));
			cbEstante.setSize(new Dimension(161, 25));
		}
		return cbEstante;
	}

}
