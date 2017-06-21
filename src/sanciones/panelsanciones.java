package sanciones;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.Point;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JList;
import java.awt.Font;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.sql.SQLException;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JApplet;
import javax.swing.JDialog;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JRadioButtonMenuItem;
import java.awt.Choice;
import java.awt.Button;
import javax.swing.JTable;
import javax.swing.BorderFactory;

import Utilitario.Centrar;

public class panelsanciones {

	private gestorSanciones gestor;  //  @jve:decl-index=0:
	private JInternalFrame jInternalFrame = null;  //  @jve:decl-index=0:visual-constraint="246,160"
	private JPanel jContentPane = null;
	public JButton san_guar = null;
	public JLabel jLabel = null;
	public JTabbedPane jTabbedPane = null;
	public JPanel jPanel = null;
	private JPanel jPanel1 = null;
	public JTextField nombre = null;
	private JLabel jLabel1 = null;
	private JLabel jLabel11 = null;
	private JTextField consu_san = null;
	public JButton jButton1 = null;
	public JPanel jPanel2 = null;
	private JLabel jLabel111 = null;
	private JTextField consu_san1 = null;
	private JButton jButton11 = null;
	public JButton san_elimi = null;
	public JButton san_ok = null;
	private JInternalFrame alert = null;  //  @jve:decl-index=0:visual-constraint="693,217"
	public JInternalFrame alerta = null;
	private JPanel jContentPane1 = null;
	public JButton aceptar = null;
	public JLabel jLabel2 = null;
	public JButton cancel = null;
	public JScrollPane jScrollPane3 = null;
	public JList lista = null;
	private JLabel estados = null;
	private JLabel jLabel32 = null;
	private JLabel jLabel3 = null;
	private JLabel fec_pre = null;
	private JLabel fec_ven = null;
	private JLabel Esta = null;
	private JScrollPane jScrollPane31 = null;
	public JList lista1 = null;
	public JInternalFrame alerta1 = null;
	private JPanel jContentPane11 = null;
	private JButton aceptar1 = null;
	private JLabel jLabel21 = null;
	private JScrollPane jScrollPane32 = null;
	public JList lista2 = null;
	private JLabel jLabel31 = null;
	private JLabel jLabel321 = null;
	private JLabel estados1 = null;
	public JInternalFrame alerta2 = null;
	private JPanel jContentPane12 = null;
	private JButton aceptar2 = null;
	public JLabel jLabel22 = null;
	private JLabel jLabel4 = null;
	public JScrollPane jScrollPane = null;
	public JList jList = null;
	private JLabel libro = null;
	private JLabel Libro = null;
	public panelsanciones (Dimension d){
		getJInternalFrame().setLocation(Centrar.centrarEnComponente(getJInternalFrame().getSize(), d));
		
	}
	public void setGestor(gestorSanciones gestor) {
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
			jInternalFrame.setSize(new Dimension(488, 294));
			jInternalFrame.setClosable(true);
			jInternalFrame.setTitle("Sanciones");
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
	 * This method initializes san_guar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton getSan_guar() {
		if (san_guar == null) {
			san_guar = new JButton();
			san_guar.setName("click");
			san_guar.setBounds(new Rectangle(302, 115, 142, 19));
			san_guar.setEnabled(false);
			san_guar.setText("Sancionar");
			san_guar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
				
					gestor.san_guardar(nombre.getText(),lista.getSelectedIndex());
					gestor.san_cance(1);
					
					
				}
			});
		}
		return san_guar;
	}
	/**
	 * This method initializes jLabel	
	 * 	
	 * @return javax.swing.JLabel	
	 */
	public JLabel getJLabel() {
		if (jLabel == null) {
			jLabel = new JLabel();
			jLabel.setText("");
			jLabel.setForeground(Color.blue);
			jLabel.setBounds(new Rectangle(9, 88, 149, 13));
		}
		return jLabel;
	}
	/**
	 * This method initializes jTabbedPane	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	public JTabbedPane getJTabbedPane() {
		if (jTabbedPane == null) {
			jTabbedPane = new JTabbedPane();
			jTabbedPane.setTabPlacement(JTabbedPane.TOP);
			jTabbedPane.setName("");
			jTabbedPane.setToolTipText("");
			jTabbedPane.setSize(new Dimension(471, 258));
			jTabbedPane.setLocation(new Point(0, 0));
			jTabbedPane.addTab("Agregar", null, getJPanel(), null);
			jTabbedPane.addTab("Consultar", null, getJPanel1(), "");
			jTabbedPane.addTab("Eliminar", null, getJPanel2(), null);
			jTabbedPane.addChangeListener(new javax.swing.event.ChangeListener() {
				public void stateChanged(javax.swing.event.ChangeEvent e) {
					gestor.limpiar();
				}
			});
			jTabbedPane.addChangeListener(new javax.swing.event.ChangeListener() {
				public void stateChanged(javax.swing.event.ChangeEvent e) {
					gestor.limpiar();
				}
			});
		}
		return jTabbedPane;
	}
	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			Libro = new JLabel();
			Libro.setBounds(new Rectangle(414, 9, 37, 13));
			Libro.setForeground(Color.red);
			Libro.setText("Libro");
			Libro = new JLabel();
			Libro.setBounds(new Rectangle(411, 9, 38, 13));
			Libro.setForeground(Color.red);
			Libro.setText("Libro");
			libro = new JLabel();
			libro.setBounds(new Rectangle(424, 14, 0, 0));
			libro.setText("JLabel");
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(164, 9, 106, 13));
			jLabel3.setText("Fecha Prestamo");
			jLabel3.setForeground(Color.red);
			jLabel32 = new JLabel();
			jLabel32.setBounds(new Rectangle(271, 9, 125, 13));
			jLabel32.setForeground(Color.red);
			jLabel32.setText("Fecha vencimiento");
			jLabel1 = new JLabel();
			jLabel1.setText("Ingrese Cedula");
			jLabel1.setLocation(new Point(14, 5));
			jLabel1.setBounds(new Rectangle(23, 3, 94, 16));
			jLabel1.setSize(new Dimension(106, 16));
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.setPreferredSize(new Dimension(12, 13));
			jPanel.add(getAlerta(), null);
			jPanel.add(getNombre(), null);
			jPanel.add(jLabel1, null);
			jPanel.add(getSan_guar(), null);
			jPanel.add(getJLabel(), null);
			jPanel.add(getSan_ok(), null);
			jPanel.add(getCancel(), null);
			jPanel.add(getJScrollPane3(), null);
			jPanel.add(jLabel32, null);
			jPanel.add(jLabel3, null);
			jPanel.add(Libro, null);
			jPanel.add(Libro, null);
			
		}
		return jPanel;
	}
	/**
	 * This method initializes jPanel1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(34, 90, 108, 16));
			jLabel4.setForeground(Color.red);
			jLabel4.setText("Nombre");
			Esta = new JLabel();
			Esta.setBounds(new Rectangle(388, 90, 44, 14));
			Esta.setText("Estado");
			Esta.setForeground(Color.red);
			fec_ven = new JLabel();
			fec_ven.setBounds(new Rectangle(265, 90, 122, 14));
			fec_ven.setText("Fecha vencimiento");
			fec_ven.setForeground(Color.red);
			fec_pre = new JLabel();
			fec_pre.setBounds(new Rectangle(158, 90, 103, 15));
			fec_pre.setText("Fecha Prestamo");
			fec_pre.setForeground(Color.red);
			jLabel11 = new JLabel();
			jLabel11.setText("Ingrese Cedula");
			jLabel11.setBounds(new Rectangle(30, 8, 137, 21));
			jPanel1 = new JPanel();
			jPanel1.setLayout(null);
			jPanel1.add(getAlerta1(), null);
			jPanel1.add(jLabel11, null);
			jPanel1.add(getConsu_san(), null);
			jPanel1.add(getJButton1(), null);
			jPanel1.add(fec_pre, null);
			jPanel1.add(fec_ven, null);
			jPanel1.add(Esta, null);
			jPanel1.add(getJScrollPane31(), null);
			jPanel1.add(jLabel4, null);
			jPanel1.add(getJScrollPane(), null);
		}
		return jPanel1;
	}
	/**
	 * This method initializes nombre	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getNombre() {
		if (nombre == null) {
			nombre = new JTextField();
			nombre.setLocation(new Point(15, 26));
			nombre.setSize(new Dimension(106, 25));
		}
		return nombre;
	}
	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */

	/**
	 * This method initializes consu_san	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getConsu_san() {
		if (consu_san == null) {
			consu_san = new JTextField();
			consu_san.setLocation(new Point(15, 32));
			consu_san.setBounds(new Rectangle(28, 34, 138, 23));
			consu_san.setSize(new Dimension(106, 20));
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
			jButton1.setSize(new Dimension(80, 25));
			jButton1.setLocation(new Point(173, 32));
			jButton1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(!(consu_san.getText().equals(""))){
					  try {
						gestor.boton_ok(consu_san.getText(),1);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}} if((consu_san.getText().equals("")))
						try {
							gestor.san_buscar("hola_sancion");
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
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
			estados1 = new JLabel();
			estados1.setBounds(new Rectangle(396, 7, 52, 14));
			estados1.setText("Estado");
			estados1.setForeground(Color.red);
			jLabel321 = new JLabel();
			jLabel321.setBounds(new Rectangle(271, 7, 114, 14));
			jLabel321.setText("Fecha vencimiento");
			jLabel321.setForeground(Color.red);
			jLabel31 = new JLabel();
			jLabel31.setBounds(new Rectangle(168, 7, 99, 14));
			jLabel31.setText("Fecha Prestamo");
			jLabel31.setForeground(Color.red);
			jLabel111 = new JLabel();
			jLabel111.setText("Ingrese Cedula");
			jLabel111.setLocation(new Point(14, 11));
			jLabel111.setBounds(new Rectangle(22, 7, 116, 18));
			jLabel111.setSize(new Dimension(85, 16));
			jPanel2 = new JPanel();
			jPanel2.setLayout(null);
			jPanel2.add(getAlerta2(), null);
			jPanel2.add(jLabel111, null);
			jPanel2.add(getConsu_san1(), null);
			jPanel2.add(getJButton11(), null);
			jPanel2.add(getSan_elimi(), null);
			jPanel2.add(getJScrollPane32(), null);
			jPanel2.add(jLabel31, null);
			jPanel2.add(jLabel321, null);
			jPanel2.add(estados1, null);
		}
		return jPanel2;
	}
	/**
	 * This method initializes consu_san1	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getConsu_san1() {
		if (consu_san1 == null) {
			consu_san1 = new JTextField();
			consu_san1.setSize(new Dimension(106, 20));
			consu_san1.setBounds(new Rectangle(19, 30, 120, 23));
			consu_san1.setLocation(new Point(15, 32));
		}
		return consu_san1;
	}
	/**
	 * This method initializes jButton11	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton getJButton11() {
		if (jButton11 == null) {
			jButton11 = new JButton();
			jButton11.setText("Buscar");
			jButton11.setLocation(new Point(15, 54));
			jButton11.setBounds(new Rectangle(22, 57, 101, 21));
			jButton11.setSize(new Dimension(92, 17));
			jButton11.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(!(consu_san1.getText().equals("")))
						{
					try {
						gestor.boton_ok(consu_san1.getText(), 2);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}}
			});
		}
		return jButton11;
	}
	/**
	 * This method initializes san_elimi	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton getSan_elimi() {
		if (san_elimi == null) {
			san_elimi = new JButton();
			san_elimi.setLocation(new Point(256, 153));
			san_elimi.setText("Elimnar Sancion");
			san_elimi.setBounds(new Rectangle(293, 107, 146, 19));
			san_elimi.setEnabled(false);
			san_elimi.setSize(new Dimension(160, 26));
			san_elimi.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.boton_buscar_modi(consu_san1.getText(),lista2.getSelectedIndex());
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}
			});
			san_elimi.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					
				}
			});
		}
		return san_elimi;
	}
	/**
	 * This method initializes san_ok	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton getSan_ok() {
		if (san_ok == null) {
			san_ok = new JButton();
			san_ok.setBounds(new Rectangle(17, 52, 58, 19));
			san_ok.setText("Ok");
			san_ok.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(!(nombre.getText().equals("")))
						{System.out.println("vacio");
						gestor.san_bus_agre(nombre.getText());
					
					//System.out.println("actionPerformed()"); // TODO Auto-generated Event stub actionPerformed()
				}}
			});
		}
		return san_ok;
	}
	/**
	 * This method initializes alert	
	 * 	
	 * @return javax.swing.JInternalFrame	
	 */
	
	
	/**
	 * This method initializes alerta	
	 * 	
	 * @return javax.swing.JInternalFrame	
	 */
	public JInternalFrame getAlerta() {
		if (alerta == null) {
			alerta = new JInternalFrame();
			alerta.setBounds(new Rectangle(61, 94, 234, 102));
			alerta.setVisible(false);
			alerta.setContentPane(getJContentPane1());
		}
		return alerta;
	}
	/**
	 * This method initializes jContentPane1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	public JPanel getJContentPane1() {
		if (jContentPane1 == null) {
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(11, 8, 205, 16));
			jLabel2.setForeground(Color.red);
			jLabel2.setText("Cedula no encontrada");
			jContentPane1 = new JPanel();
			jContentPane1.setLayout(null);
			jContentPane1.add(getAceptar(), null);
			jContentPane1.add(jLabel2, null);
		}
		return jContentPane1;
	}
	/**
	 * This method initializes aceptar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getAceptar() {
		if (aceptar == null) {
			aceptar = new JButton();
			aceptar.setBounds(new Rectangle(54, 33, 105, 24));
			aceptar.setText("Aceptar");
			aceptar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.san_aler();
				}
			});
		}
		return aceptar;
	}
	/**
	 * This method initializes cancel	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton getCancel() {
		if (cancel == null) {
			cancel = new JButton();
			cancel.setBounds(new Rectangle(78, 52, 72, 19));
			cancel.setText("Cancel");
			cancel.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.san_cance(0);
				}
			});
		}
		return cancel;
	}
	/**
	 * This method initializes jScrollPane3	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane3() {
		if (jScrollPane3 == null) {
			jScrollPane3 = new JScrollPane();
			jScrollPane3.setBounds(new Rectangle(163, 24, 304, 88));
			jScrollPane3.setViewportView(getLista());
		}
		return jScrollPane3;
	}
	/**
	 * This method initializes lista	
	 * 	
	 * @return javax.swing.JList	
	 */
	public JList getLista() {
		if (lista == null) {
			lista = new JList();
		}
		return lista;
	}
	/**
	 * This method initializes jScrollPane31	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane31() {
		if (jScrollPane31 == null) {
			jScrollPane31 = new JScrollPane();
			jScrollPane31.setBounds(new Rectangle(156, 106, 282, 97));
			jScrollPane31.setBorder(BorderFactory.createLineBorder(Color.gray, 3));
			jScrollPane31.setViewportView(getLista1());
		}
		return jScrollPane31;
	}
	/**
	 * This method initializes lista1	
	 * 	
	 * @return javax.swing.JList	
	 */
	public JList getLista1() {
		if (lista1 == null) {
			lista1 = new JList();
			lista1.setEnabled(true);
		}
		return lista1;
	}
	/**
	 * This method initializes alerta1	
	 * 	
	 * @return javax.swing.JInternalFrame	
	 */
	public JInternalFrame getAlerta1() {
		if (alerta1 == null) {
			alerta1 = new JInternalFrame();
			alerta1.setBounds(new Rectangle(137, 107, 226, 96));
			alerta1.setContentPane(getJContentPane11());
			alerta1.setVisible(false);
		}
		return alerta1;
	}
	/**
	 * This method initializes jContentPane11	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJContentPane11() {
		if (jContentPane11 == null) {
			jLabel21 = new JLabel();
			jLabel21.setBounds(new Rectangle(35, 8, 142, 16));
			jLabel21.setText("Cedula no encontrada");
			jLabel21.setForeground(Color.red);
			jContentPane11 = new JPanel();
			jContentPane11.setLayout(null);
			jContentPane11.add(getAceptar1(), null);
			jContentPane11.add(jLabel21, null);
		}
		return jContentPane11;
	}
	/**
	 * This method initializes aceptar1	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getAceptar1() {
		if (aceptar1 == null) {
			aceptar1 = new JButton();
			aceptar1.setBounds(new Rectangle(53, 31, 105, 24));
			aceptar1.setText("Aceptar");
			aceptar1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
				gestor.san_ale2();
				}
			});
		}
		return aceptar1;
	}
	/**
	 * This method initializes jScrollPane32	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	public JScrollPane getJScrollPane32() {
		if (jScrollPane32 == null) {
			jScrollPane32 = new JScrollPane();
			jScrollPane32.setBounds(new Rectangle(166, 23, 290, 82));
			jScrollPane32.setViewportView(getLista2());
		}
		return jScrollPane32;
	}
	/**
	 * This method initializes lista2	
	 * 	
	 * @return javax.swing.JList	
	 */
	public JList getLista2() {
		if (lista2 == null) {
			lista2 = new JList();
		}
		return lista2;
	}
	/**
	 * This method initializes alerta2	
	 * 	
	 * @return javax.swing.JInternalFrame	
	 */
	public JInternalFrame getAlerta2() {
		if (alerta2 == null) {
			alerta2 = new JInternalFrame();
			alerta2.setBounds(new Rectangle(97, 99, 201, 102));
			alerta2.setContentPane(getJContentPane12());
			alerta2.setVisible(false);
		}
		return alerta2;
	}
	/**
	 * This method initializes jContentPane12	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJContentPane12() {
		if (jContentPane12 == null) {
			jLabel22 = new JLabel();
			jLabel22.setBounds(new Rectangle(40, 8, 142, 16));
			jLabel22.setText("Se elimino correctamente");
			jLabel22.setForeground(Color.red);
			jContentPane12 = new JPanel();
			jContentPane12.setLayout(null);
			jContentPane12.add(getAceptar2(), null);
			jContentPane12.add(jLabel22, null);
		}
		return jContentPane12;
	}
	/**
	 * This method initializes aceptar2	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getAceptar2() {
		if (aceptar2 == null) {
			aceptar2 = new JButton();
			aceptar2.setBounds(new Rectangle(54, 33, 105, 24));
			aceptar2.setText("Aceptar");
			aceptar2.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					alerta2.setVisible(false);
				}
			});
		}
		return aceptar2;
	}
	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	public JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(30, 106, 128, 97));
			jScrollPane.setForeground(Color.blue);
			jScrollPane.setBorder(BorderFactory.createLineBorder(Color.gray, 3));
			jScrollPane.setFont(new Font("SansSerif", Font.PLAIN, 12));
			jScrollPane.setEnabled(false);
			jScrollPane.setViewportView(getJList());
		}
		return jScrollPane;
	}
	/**
	 * This method initializes jList	
	 * 	
	 * @return javax.swing.JList	
	 */
	private JList getJList() {
		if (jList == null) {
			jList = new JList();
			jList.setEnabled(true);
			jList.setFont(new Font("SansSerif", Font.PLAIN, 12));
			jList.setBackground(Color.white);
			jList.setForeground(Color.blue);
		}
		return jList;
	}

}
