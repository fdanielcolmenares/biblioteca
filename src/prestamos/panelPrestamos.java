package prestamos;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import javax.swing.ImageIcon;

import com.toedter.calendar.JDateChooser;

import Utilitario.Centrar;
import Utilitario.MostrarMensajes;

public class panelPrestamos {

	private gestorPrestamos gestor;  //  @jve:decl-index=0:
	private JInternalFrame jInternalFrame = null;  //  @jve:decl-index=0:visual-constraint="91,13"
	public JDateChooser txtFechaprestamo = null;
	public JDateChooser txtFechaentrega = null;
	public JDateChooser txtFechaentrega_usu = null;
	
	private JPanel jContentPane = null;
	public JTabbedPane jTabbedPane = null;
	public JPanel jPanel1 = null;
	public JLabel jLabel11 = null;
	public JTextField consu_san = null;
	public JButton jButton1 = null;
	public JLabel jLabel5 = null;
	private JPanel jPanel2 = null;
	public JLabel jLabel6 = null;
	public JButton Agregar = null;
	public JTextField cedula = null;
	private JLabel ced = null;
	public JButton ok = null;
	public JPanel panel1 = null;
	public JPanel panelusuario = null;
	public JLabel imagen = null;
	public JLabel libros = null;
	public JPanel listaprestamos = null;
	public JButton procprestamo = null;
	private JScrollPane jScrollPane = null;
	public JTextArea listlibros = null;
	private JLabel nomusu = null;
	public JPanel nom_usuario = null;
	public JLabel nombre_usu = null;
	public JPanel usu_devuelve = null;
	private JButton ok2 = null;
	public JTextField cedula1 = null;
	public JLabel ced1 = null;
	private JLabel imagen1 = null;
	public JPanel devolver_libro = null;
	public JScrollPane jScrollPane1 = null;
	private JButton bton_devuelve = null;
	private JLabel hoy = null;
	private JLabel jLabel10 = null;
	private JLabel jLabel1 = null;
	private JButton jButton3 = null;
	private JLabel jLabel2 = null;
	private JLabel jLabel3 = null;
	private JLabel jLabel4 = null;
	private JLabel jLabel7 = null;
	public JInternalFrame alerta = null;
	private JPanel ale = null;
	private JButton sancionar = null;
	public JLabel sancionn = null;
	public JScrollPane jScrolista = null;
	public JList jList = null;
	
	public panelPrestamos (Dimension d){
		getJInternalFrame().setLocation(Centrar.centrarEnComponente(getJInternalFrame().getSize(), d));
		
	}
	public panelPrestamos(Dimension d, boolean recibir){	
		getJInternalFrame().setLocation(Centrar.centrarEnComponente(getJInternalFrame().getSize(), d));
		if(recibir){
			jTabbedPane.setSelectedIndex(1);
		}
	}
	public void setGestor(gestorPrestamos gestor) {
		this.gestor = gestor;
	}
	/**
	 * This method initializes jInternalFrame	
	 * 	
	 * @return javax.swing.JInternalFrame	
	 */
	public JInternalFrame getJInternalFrame() {
		if (jInternalFrame == null) {
			jInternalFrame = new JInternalFrame();
			jInternalFrame.setSize(new Dimension(529, 337));
			jInternalFrame.setClosable(true);
			jInternalFrame.setTitle("Préstamos");
			jInternalFrame.setContentPane(getJContentPane());
		}
		return jInternalFrame;
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
			jContentPane.add(getJTabbedPane(), null);
		}
		return jContentPane;
	}
	/**
	 * This method initializes jTabbedPane	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	private JTabbedPane getJTabbedPane() {
		if (jTabbedPane == null) {
			jTabbedPane = new JTabbedPane();
			jTabbedPane.setBounds(new Rectangle(0, 0, 512, 297));
			jTabbedPane.setTabPlacement(JTabbedPane.TOP);
			jTabbedPane.setName("");
			jTabbedPane.setToolTipText("");
			jTabbedPane.setVisible(true);
			jTabbedPane.addTab("Libros", null, getJPanel1(), "");
			jTabbedPane.addTab("Entrega", null, getJPanel2(), null);
		}
		return jTabbedPane;
	}
	/**
	 * This method initializes jPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			nomusu = new JLabel();
			nomusu.setText("Usuario:");
			nomusu.setBounds(new Rectangle(4, 10, 63, 16));
			libros = new JLabel();
			libros.setBounds(new Rectangle(26, 43, 171, 148));
			libros.setIcon(new ImageIcon(getClass().getResource("/Imagenes/libros1.png")));
			libros.setText("");
			libros.setVisible(false);
			imagen = new JLabel();
			imagen.setBounds(new Rectangle(55, 41, 116, 128));
			imagen.setIcon(new ImageIcon(getClass().getResource("/Imagenes/default.jpg")));
			imagen.setText("");
			imagen.setVisible(true);
			ced = new JLabel();
			ced.setText("Ingrese cédula usuario");
			ced.setBounds(new Rectangle(9, 10, 144, 16));
			jLabel6 = new JLabel();
			jLabel6.setText("");
			jLabel6.setBounds(new Rectangle(116, 147, 100, 16));
			jLabel6.setForeground(Color.red);
			jLabel6.setVisible(false);
			jLabel5 = new JLabel();
			jLabel5.setText("Estado:");
			jLabel5.setBounds(new Rectangle(52, 147, 59, 16));
			jLabel5.setVisible(false);
			jLabel11 = new JLabel();
			jLabel11.setText("Ingrese Codigo del libro");
			jLabel11.setBounds(new Rectangle(53, 94, 136, 16));
			jPanel1 = new JPanel();
			jPanel1.setLayout(null);
			jPanel1.add(getPanelusuario(), null);
			jPanel1.add(getPanel1(), null);
			jPanel1.add(imagen, null);
			jPanel1.add(libros, null);
			jPanel1.add(getListaprestamos(), null);
			jPanel1.add(getNom_usuario(), null);
		}
		return jPanel1;
	}
	/**
	 * This method initializes consu_san	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getConsu_san() {
		if (consu_san == null) {
			consu_san = new JTextField();
			consu_san.setBounds(new Rectangle(66, 119, 110, 30));
			consu_san.setText(" ");
		}
		return consu_san;
	}
	/**
	 * This method initializes jButton1	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setText("Buscar");
			jButton1.setBounds(new Rectangle(67, 176, 92, 24));
		    jButton1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
				if(consu_san.getText().matches("[ ]"))
				{		MostrarMensajes.mostrarMensaje("Ingrese Cota del libro", MostrarMensajes.MENSAJE_ERROR);
					consu_san.setText(" ");
				}
				if(txtFechaentrega.getCalendar()==null || txtFechaprestamo==null)
					MostrarMensajes.mostrarMensaje("Ingrese Fechas", MostrarMensajes.MENSAJE_ERROR);
				if(!consu_san.getText().matches("[ ]"))
				{
				if(txtFechaentrega.getCalendar().get(Calendar.YEAR)<txtFechaprestamo.getCalendar().get(Calendar.YEAR) ||
				   txtFechaentrega.getCalendar().get(Calendar.MONTH)<txtFechaprestamo.getCalendar().get(Calendar.MONTH)||
				   txtFechaentrega.getCalendar().get(Calendar.DAY_OF_MONTH)<txtFechaprestamo.getCalendar().get(Calendar.DAY_OF_MONTH))
					 MostrarMensajes.mostrarMensaje("La fecha de entrega debe ser mayor o igual a la de prestamo!", MostrarMensajes.MENSAJE_ERROR);
				else{
					try {
						gestor.bton_buscar_libro(consu_san.getText());
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
				}
				}	
					
			});
				
		}
		return jButton1;
	}
	/**
	 * This method initializes jPanel2	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			jPanel2 = new JPanel();
			jPanel2.setLayout(null);
			jPanel2.setVisible(false);
			jPanel2.add(getUsu_devuelve(), null);
			jPanel2.add(getDevolver_libro(), null);
		}
		return jPanel2;
	}
	/**
	 * This method initializes Agregar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getAgregar() {
		if (Agregar == null) {
			Agregar = new JButton();
			Agregar.setText("Agregar Libro");
			Agregar.setBounds(new Rectangle(59, 178, 128, 23));
			Agregar.setVisible(false);
			Agregar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.bton_agregar_libro();
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return Agregar;
	}
	/**
	 * This method initializes cedula	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getCedula() {
		if (cedula == null) {
			cedula = new JTextField();
			cedula.setBounds(new Rectangle(36, 36,110, 30));
			cedula.setText(" ");
		}
		return cedula;
	}
	/**
	 * This method initializes ok	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton getOk() {
		if (ok == null) {
			ok = new JButton();
			ok.setText("Consultar");
			ok.setBounds(new Rectangle(41, 70, 89, 26));
			ok.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(cedula.getText().matches("[ ]"))
						MostrarMensajes.mostrarMensaje("Ingrese Cedula", MostrarMensajes.MENSAJE_ERROR);
					else{
					
					try {
						gestor.boton_buscar_usu(cedula.getText(),1);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
				}
			});
			
				
		}
		return ok;
	}
	/**
	 * This method initializes panel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	public JPanel getPanel1() {
		if (panel1 == null) {
			jLabel10 = new JLabel();
			jLabel10.setBounds(new Rectangle(7, 46, 99, 26));
			jLabel10.setText("Fecha entrega");
			hoy = new JLabel();
			hoy.setBounds(new Rectangle(6, 11, 99, 22));
			hoy.setText("Fecha prestamo");
			panel1 = new JPanel();
			panel1.setLayout(null);
			panel1.setVisible(false);
			panel1.setBounds(new Rectangle(231, 10, 262, 250));
			panel1.add(getConsu_san(), null);
			panel1.add(getTxtFechaprestamo(), null);
			panel1.add(getTxtFechaentrega(), null);
			panel1.add(jLabel11, null);
			panel1.add(jLabel5, null);
			panel1.add(jLabel6, null);
			panel1.add(getAgregar(), null);
			panel1.add(getJButton1(), null);
			panel1.add(hoy, null);
			panel1.add(jLabel10, null);
		}
		return panel1;
	}
	/**
	 * This method initializes panelusuario	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelusuario() {
		if (panelusuario == null) {
			panelusuario = new JPanel();
			panelusuario.setLayout(null);
			panelusuario.setEnabled(true);
			panelusuario.setBounds(new Rectangle(264, 60, 175, 136));
			panelusuario.setVisible(true);
			panelusuario.add(getOk(), null);
			panelusuario.add(getCedula(), null);
			panelusuario.add(ced, null);
		}
		return panelusuario;
	}
	/**
	 * This method initializes listaprestamos	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	public JPanel getListaprestamos() {
		if (listaprestamos == null) {
			listaprestamos = new JPanel();
			listaprestamos.setLayout(null);
			listaprestamos.setBounds(new Rectangle(13, 36, 213, 218));
			listaprestamos.setVisible(false);
			listaprestamos.add(getProcprestamo(), null);
			listaprestamos.add(getJScrollPane(), null);
		}
		return listaprestamos;
	}
	/**
	 * This method initializes procprestamo	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getProcprestamo() {
		if (procprestamo == null) {
			procprestamo = new JButton();
			procprestamo.setBounds(new Rectangle(22, 180, 160, 23));
			procprestamo.setText("Procesar");
			procprestamo.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.inicio();
					System.out.println("procprestamooooo"); // TODO Auto-generated Event stub actionPerformed()
					listlibros.setText("");
				}
			});
		}
		return procprestamo;
	}
	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(11, 6, 195, 141));
			jScrollPane.setViewportView(getListlibros());
		}
		return jScrollPane;
	}
	/**
	 * This method initializes listlibros	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private JTextArea getListlibros() {
		if (listlibros == null) {
			listlibros = new JTextArea();
			listlibros.setEditable(false);
		}
		return listlibros;
	}
	/**
	 * This method initializes txtFechaprestamo	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JDateChooser getTxtFechaprestamo() {
		if (txtFechaprestamo == null) {
			txtFechaprestamo = new JDateChooser();
			txtFechaprestamo.setMinSelectableDate(new Date());
			txtFechaprestamo.setBounds(new Rectangle(111, 9, 149, 28));
			
		}
		return txtFechaprestamo;
	}
	/**
	 * This method initializes txtFechaprestamo	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JDateChooser getTxtFechaentrega() {
		if (txtFechaentrega == null) {
			txtFechaentrega = new JDateChooser();
			txtFechaentrega.setMinSelectableDate(new Date());
			txtFechaentrega.setBounds(new Rectangle(111, 50, 149, 28));
		
		}
		return txtFechaentrega;
	}
	/**
	 * This method initializes txtFechaprestamo	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JDateChooser getTxtFechaentrega_usu() {
		if (txtFechaentrega_usu == null) {
			txtFechaentrega_usu = new JDateChooser();
			txtFechaentrega_usu.setMinSelectableDate(new Date());
			txtFechaentrega_usu.setBounds(new Rectangle(297, 190, 149, 28));
		}
		return txtFechaentrega_usu;
	}

	/**
	 * This method initializes nom_usuario	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	public JPanel getNom_usuario() {
		if (nom_usuario == null) {
			nombre_usu = new JLabel();
			nombre_usu.setBounds(new Rectangle(73, 9, 69, 19));
			nombre_usu.setText("Lilian");
			nom_usuario = new JPanel();
			nom_usuario.setLayout(null);
			nom_usuario.setBounds(new Rectangle(20, 7, 186, 33));
			nom_usuario.setVisible(false);
			nom_usuario.add(nomusu, null);
			nom_usuario.add(nombre_usu, null);
		}
		return nom_usuario;
	}
	/**
	 * This method initializes usu_devuelve	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getUsu_devuelve() {
		if (usu_devuelve == null) {
			imagen1 = new JLabel();
			imagen1.setBounds(new Rectangle(22, 21, 102, 102));
			imagen1.setText("");
			imagen1.setIcon(new ImageIcon(getClass().getResource("/imagenes/default.jpg")));
			ced1 = new JLabel();
			ced1.setBounds(new Rectangle(194, 35, 144, 16));
			ced1.setText("Ingrese cedula usuario");
			usu_devuelve = new JPanel();
			usu_devuelve.setLayout(null);
			usu_devuelve.setBounds(new Rectangle(66, 41, 340, 146));
			usu_devuelve.setEnabled(true);
			usu_devuelve.add(getOk2(), null);
			usu_devuelve.add(getCedula1(), null);
			usu_devuelve.add(ced1, null);
			usu_devuelve.add(imagen1, null);
			usu_devuelve.setVisible(true);
		}
		return usu_devuelve;
	}
	/**
	 * This method initializes ok2	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getOk2() {
		if (ok2 == null) {
			ok2 = new JButton();
			ok2.setBounds(new Rectangle(217, 105, 89, 26));
			ok2.setText("Consultar");
			ok2.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					
						if(cedula1.getText().matches("[ ]"))
							MostrarMensajes.mostrarMensaje("Ingrese Cedula", MostrarMensajes.MENSAJE_ERROR);
						else{
							try {
								gestor.boton_buscar_usu(cedula1.getText(),2);
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return ok2;
	}
	/**
	 * This method initializes cedula1	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getCedula1() {
		if (cedula1 == null) {
			cedula1 = new JTextField();
			cedula1.setBounds(new Rectangle(215, 62, 110, 30));
			cedula1.setText(" ");
		}
		return cedula1;
	}
	/**
	 * This method initializes devolver_libro	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	public JPanel getDevolver_libro() {
		if (devolver_libro == null) {
			jLabel7 = new JLabel();
			jLabel7.setBounds(new Rectangle(204, 9, 65, 16));
			jLabel7.setText("Id_libro");
			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(310, 10, 38, 16));
			jLabel4.setText("Titulo");
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(102, 10, 63, 16));
			jLabel3.setText("Ejemplar");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(46, 10, 49, 16));
			jLabel2.setText("Cota:");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(186, 188, 102, 23));
			jLabel1.setText("Ingrese Fecha:");
			devolver_libro = new JPanel();
			devolver_libro.setLayout(null);
			devolver_libro.setBounds(new Rectangle(28, 2, 463, 260));
			devolver_libro.setVisible(false);
			devolver_libro.add(getAlerta(), null);
			devolver_libro.add(getBton_devuelve(), null);
			devolver_libro.add(getJScrolista(), null);
			devolver_libro.add(jLabel1, null);
			devolver_libro.add(getJButton3(), null);
			devolver_libro.add(jLabel2, null);
			devolver_libro.add(jLabel3, null);
			devolver_libro.add(jLabel4, null);
			devolver_libro.add(jLabel7, null);
			devolver_libro.add(getTxtFechaentrega_usu(), null);
		}
		return devolver_libro;
	}
	/**
	 * This method initializes bton_devuelve	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBton_devuelve() {
		if (bton_devuelve == null) {
			bton_devuelve = new JButton();
			bton_devuelve.setBounds(new Rectangle(58, 189, 99, 26));
			bton_devuelve.setText("Devolver");
			bton_devuelve.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					
						
							if(txtFechaentrega_usu.getDate()==null)
								MostrarMensajes.mostrarMensaje("Ingrese Fecha", MostrarMensajes.MENSAJE_ERROR);
							else{
								if(jList.getSelectedIndex()==-1)
									MostrarMensajes.mostrarMensaje("Seleccione libro", MostrarMensajes.MENSAJE_ERROR);
								else
									try {
										gestor.cambiar_estado_devuelto(jList.getSelectedIndex());
									} catch (SQLException e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
							 System.out.print(jList.getSelectedIndex());
							}
						
						// TODO Auto-generated catch block
					// TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return bton_devuelve;
	}
	/**
	 * This method initializes jButton3	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton3() {
		if (jButton3 == null) {
			jButton3 = new JButton();
			jButton3.setBounds(new Rectangle(184, 220, 89, 23));
			jButton3.setText("Finalizar");
			jButton3.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.regresar_devolver();
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return jButton3;
	}
	/**
	 * This method initializes alerta	
	 * 	
	 * @return javax.swing.JInternalFrame	
	 */
	public JInternalFrame getAlerta() {
		if (alerta == null) {
			alerta = new JInternalFrame();
			alerta.setBounds(new Rectangle(133, 34, 215, 158));
			alerta.setVisible(false);
			alerta.setContentPane(getAle());
		}
		return alerta;
	}
	/**
	 * This method initializes ale	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getAle() {
		if (ale == null) {
			sancionn = new JLabel();
			sancionn.setBounds(new Rectangle(5, 35, 185, 33));
			sancionn.setText("Usuario debe ser sancionado");
			ale = new JPanel();
			ale.setLayout(null);
			ale.add(sancionn, null);
			ale.add(getSancionar(), null);
		}
		return ale;
	}
	/**
	 * This method initializes sancionar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getSancionar() {
		if (sancionar == null) {
			sancionar = new JButton();
			sancionar.setBounds(new Rectangle(45, 80, 110, 22));
			sancionar.setText("Sancionar ");
			sancionar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					//**********BOTON DE LA ALERTA!!!!!
					alerta.setVisible(false);
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
		}
		return sancionar;
	}
	/**
	 * This method initializes jScrolista	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	public JScrollPane getJScrolista() {
		if (jScrolista == null) {
			jScrolista = new JScrollPane();
			jScrolista.setBounds(new Rectangle(15, 50, 420, 131));
			jScrolista.setViewportView(getJList());
		}
		return jScrolista;
	}
	/**
	 * This method initializes jList	
	 * 	
	 * @return javax.swing.JList	
	 */
	public JList getJList() {
		if (jList == null) {
			jList = new JList();
		}
		return jList;
	}

}
