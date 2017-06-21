package GestionarUsuarios;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Point;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDesktopPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import Utilitario.Centrar;
import Utilitario.Item;
import Utilitario.MostrarMensajes;
import java.awt.event.KeyEvent;
import java.awt.Rectangle;
import java.util.Date;

import javax.swing.JTabbedPane;
import com.toedter.calendar.JDateChooser;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JEditorPane;

public class VentanaEditarUsuarioBiblioteca {

	private JInternalFrame ventana = null;  //  @jve:decl-index=0:visual-constraint="183,34"
	private JPanel panelVentana = null;
	private JPanel panelBotones = null;
	private JButton btnGuardar = null;
	private JButton btnEliminar = null;
	private JButton btnCerrar = null;
	private GestorUsuarios gestor;  //  @jve:decl-index=0:
	private JTabbedPane pestanas = null;	
	private JPanel panelReferencia1 = null;
	private JPanel panelReferencia2 = null;
	private JPanel panelDatos = null;
	private JLabel lblNombre = null;
	private JLabel lblApellidos = null;
	private JLabel lblCedula = null;
	private JTextField txtNombre = null;
	private JTextField txtApellidos = null;
	private JTextField txtCedula = null;
	private JLabel lblSexo = null;
	private JComboBox cbxSexo = null;
	private JLabel lblFechaNacimiento = null;
	private JLabel lblProfesion = null;
	private JLabel lblInstitucion = null;
	private JLabel lblTelefono1 = null;
	private JLabel lblTelefono2 = null;
	private JLabel lblCarnet = null;
	private JLabel lblDirección = null;
	private JDateChooser txtFechaNacimiento = null;
	private JTextField txtProfesion = null;
	private JTextField txtInstitucion = null;
	private JTextField txtTelefono1 = null;
	private JTextField txtTelefono2 = null;
	private JTextField txtCarnet = null;
	private JLabel lblNombresReferencia1 = null;
	private JLabel lblApellidosReferencia1 = null;
	private JLabel lblTelefonoReferencia1 = null;
	private JLabel lblDireccionReferencia1 = null;
	private JTextField txtNombresReferencia1 = null;
	private JTextField txtApellidosReferencia1 = null;
	private JTextField txtTelefonoReferencia1 = null;
	private JLabel lblNombresReferencia2 = null;
	private JLabel lblApellidosReferencia2 = null;
	private JLabel lblTelefonoReferencia2 = null;
	private JLabel lblDireccionReferencia2 = null;
	private JTextField txtNombresReferencia2 = null;
	private JTextField txtApellidosReferencia2 = null;
	private JTextField txtTelefonoReferencia2 = null;
	private JScrollPane jScrollPane = null;
	private JTextArea txtDireccion = null;
	private JScrollPane jScrollPane1 = null;
	private JTextArea txtDireccionReferencia1 = null;
	private JScrollPane jScrollPane2 = null;
	private JTextArea txtDireccionReferencia2 = null;
	private JLabel lblCedulaReferencia1 = null;
	private JTextField txtCedulaReferencia1 = null;
	private JLabel lblCedulaReferencia2 = null;
	private JTextField txtCedulaReferencia2 = null;
	
	public VentanaEditarUsuarioBiblioteca(GestorUsuarios gestor, JDesktopPane desktopPane){
		this.gestor = gestor;
		getVentana();
		
		ventana.setLocation(Centrar.centrarEnComponente(ventana.getSize(), desktopPane.getSize()));
		
		desktopPane.add(ventana);
		ventana.setVisible(true);
	}
	
	public void setGestor(GestorUsuarios gestor){
		this.gestor = gestor;
	}
	
	/**
	 * This method initializes ventana	
	 * 	
	 * @return javax.swing.JInternalFrame	
	 */
	public JInternalFrame getVentana() {
		if (ventana == null) {
			ventana = new JInternalFrame();
			ventana.setSize(new Dimension(439, 443));
			ventana.setClosable(true);
			ventana.setMaximizable(true);
			ventana.setResizable(true);
			ventana.setIconifiable(true);
			ventana.setTitle("Usuario de la biblioteca");
			ventana.setContentPane(getPanelVentana());
		}
		return ventana;
	}

	/**
	 * This method initializes panelVentana	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelVentana() {
		if (panelVentana == null) {
			panelVentana = new JPanel();
			panelVentana.setLayout(new BorderLayout());
			panelVentana.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
			panelVentana.add(getPanelBotones(), BorderLayout.SOUTH);
			panelVentana.add(getPestanas(), BorderLayout.CENTER);
		}
		return panelVentana;
	}

	/**
	 * This method initializes panelBotones	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelBotones() {
		if (panelBotones == null) {
			panelBotones = new JPanel();
			panelBotones.setLayout(new FlowLayout());
			panelBotones.add(getBtnGuardar(), null);
			panelBotones.add(getBtnEliminar(), null);
			panelBotones.add(getBtnCerrar(), null);
		}
		return panelBotones;
	}

	/**
	 * This method initializes btnGuardar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnGuardar() {
		if (btnGuardar == null) {
			btnGuardar = new JButton();
			btnGuardar.setText("Guardar");
			btnGuardar.setPreferredSize(new Dimension(110, 35));
			btnGuardar.setMnemonic(KeyEvent.VK_G);
			btnGuardar.setIcon(new ImageIcon(getClass().getResource("/Imagenes/Imagen011.png")));
			btnGuardar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.prepararGuardar();
				}
			});
		}
		return btnGuardar;
	}

	/**
	 * This method initializes btnEliminar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton getBtnEliminar() {
		if (btnEliminar == null) {
			btnEliminar = new JButton();
			btnEliminar.setPreferredSize(new Dimension(110, 35));
			btnEliminar.setIcon(new ImageIcon(getClass().getResource("/Imagenes/Imagen023.png")));
			btnEliminar.setMnemonic(KeyEvent.VK_E);
			btnEliminar.setText("Eliminar");
			btnEliminar.setVisible(false);
			btnEliminar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					if(MostrarMensajes.mostrarMensaje("¿Realmente desea eliminar el usuario?", MostrarMensajes.MENSAJE_PREGUNTA)){
						
					}
				}
			});
		}
		return btnEliminar;
	}

	/**
	 * This method initializes btnCerrar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnCerrar() {
		if (btnCerrar == null) {
			btnCerrar = new JButton();
			btnCerrar.setIcon(new ImageIcon(getClass().getResource("/Imagenes/Imagen007.png")));
			btnCerrar.setPreferredSize(new Dimension(110, 35));
			btnCerrar.setMnemonic(KeyEvent.VK_C);
			btnCerrar.setText("Cerrar");
			btnCerrar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					ventana.dispose();
				}
			});
		}
		return btnCerrar;
	}

	/**
	 * This method initializes pestanas	
	 * 	
	 * @return javax.swing.JTabbedPane	
	 */
	public JTabbedPane getPestanas() {
		if (pestanas == null) {
			pestanas = new JTabbedPane();
			pestanas.addTab("Datos", null, getPanelDatos(), null);
			pestanas.addTab("Referencia 1", null, getPanelReferencia1(), null);
			pestanas.addTab("Referencia 2", null, getPanelReferencia2(), null);			
		}
		return pestanas;
	}	

	/**
	 * This method initializes panelReferencia1	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelReferencia1() {
		if (panelReferencia1 == null) {
			lblCedulaReferencia1 = new JLabel();
			lblCedulaReferencia1.setText("Cédula:");
			lblCedulaReferencia1.setBounds(new Rectangle(0, 10, 80, 20));
			lblCedulaReferencia1.setHorizontalAlignment(SwingConstants.RIGHT);
			lblDireccionReferencia1 = new JLabel();
			lblDireccionReferencia1.setHorizontalAlignment(SwingConstants.RIGHT);
			lblDireccionReferencia1.setBounds(new Rectangle(1, 110, 80, 20));
			lblDireccionReferencia1.setText("Dirección:");
			lblTelefonoReferencia1 = new JLabel();
			lblTelefonoReferencia1.setHorizontalAlignment(SwingConstants.RIGHT);
			lblTelefonoReferencia1.setBounds(new Rectangle(1, 85, 80, 20));
			lblTelefonoReferencia1.setText("Teléfono:");
			lblApellidosReferencia1 = new JLabel();
			lblApellidosReferencia1.setHorizontalAlignment(SwingConstants.RIGHT);
			lblApellidosReferencia1.setBounds(new Rectangle(1, 60, 80, 20));
			lblApellidosReferencia1.setText("Apellidos:");
			lblNombresReferencia1 = new JLabel();
			lblNombresReferencia1.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNombresReferencia1.setBounds(new Rectangle(0, 35, 80, 20));
			lblNombresReferencia1.setText("Nombres:");
			panelReferencia1 = new JPanel();
			panelReferencia1.setLayout(null);
			panelReferencia1.add(lblNombresReferencia1, null);
			panelReferencia1.add(lblApellidosReferencia1, null);
			panelReferencia1.add(lblTelefonoReferencia1, null);
			panelReferencia1.add(lblDireccionReferencia1, null);
			panelReferencia1.add(getTxtNombresReferencia1(), null);
			panelReferencia1.add(getTxtApellidosReferencia1(), null);
			panelReferencia1.add(getTxtTelefonoReferencia1(), null);
			panelReferencia1.add(getJScrollPane1(), null);
			panelReferencia1.add(lblCedulaReferencia1, null);
			panelReferencia1.add(getTxtCedulaReferencia1(), null);
		}
		return panelReferencia1;
	}

	/**
	 * This method initializes panelReferencia2	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelReferencia2() {
		if (panelReferencia2 == null) {
			lblCedulaReferencia2 = new JLabel();
			lblCedulaReferencia2.setText("Cédula:");
			lblCedulaReferencia2.setBounds(new Rectangle(0, 10, 80, 20));
			lblCedulaReferencia2.setHorizontalAlignment(SwingConstants.RIGHT);
			lblDireccionReferencia2 = new JLabel();
			lblDireccionReferencia2.setText("Dirección:");
			lblDireccionReferencia2.setHorizontalAlignment(SwingConstants.RIGHT);
			lblDireccionReferencia2.setBounds(new Rectangle(0, 110, 80, 20));
			lblTelefonoReferencia2 = new JLabel();
			lblTelefonoReferencia2.setText("Teléfono:");
			lblTelefonoReferencia2.setHorizontalAlignment(SwingConstants.RIGHT);
			lblTelefonoReferencia2.setBounds(new Rectangle(0, 85, 80, 20));
			lblApellidosReferencia2 = new JLabel();
			lblApellidosReferencia2.setText("Apellidos:");
			lblApellidosReferencia2.setHorizontalAlignment(SwingConstants.RIGHT);
			lblApellidosReferencia2.setBounds(new Rectangle(0, 60, 80, 20));
			lblNombresReferencia2 = new JLabel();
			lblNombresReferencia2.setText("Nombres:");
			lblNombresReferencia2.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNombresReferencia2.setBounds(new Rectangle(0, 35, 80, 20));
			panelReferencia2 = new JPanel();
			panelReferencia2.setLayout(null);
			panelReferencia2.add(lblNombresReferencia2, null);
			panelReferencia2.add(lblApellidosReferencia2, null);
			panelReferencia2.add(lblTelefonoReferencia2, null);
			panelReferencia2.add(lblDireccionReferencia2, null);
			panelReferencia2.add(getTxtNombresReferencia2(), null);
			panelReferencia2.add(getTxtApellidosReferencia2(), null);
			panelReferencia2.add(getTxtTelefonoReferencia2(), null);
			panelReferencia2.add(getJScrollPane2(), null);
			panelReferencia2.add(lblCedulaReferencia2, null);
			panelReferencia2.add(getTxtCedulaReferencia2(), null);
		}
		return panelReferencia2;
	}

	/**
	 * This method initializes panelDatos	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelDatos() {
		if (panelDatos == null) {
			lblDirección = new JLabel();
			lblDirección.setText("Dirección:");
			lblDirección.setHorizontalAlignment(SwingConstants.RIGHT);
			lblDirección.setBounds(new Rectangle(0, 260, 130, 20));
			lblCarnet = new JLabel();
			lblCarnet.setText("N° de carnet:");
			lblCarnet.setHorizontalAlignment(SwingConstants.RIGHT);
			lblCarnet.setBounds(new Rectangle(0, 235, 130, 20));
			lblTelefono2 = new JLabel();
			lblTelefono2.setText("Teléfono 2:");
			lblTelefono2.setBounds(new Rectangle(0, 210, 130, 20));
			lblTelefono2.setHorizontalAlignment(SwingConstants.RIGHT);
			lblTelefono1 = new JLabel();
			lblTelefono1.setHorizontalAlignment(SwingConstants.RIGHT);
			lblTelefono1.setBounds(new Rectangle(0, 185, 130, 20));
			lblTelefono1.setText("Teléfono 1:");
			lblInstitucion = new JLabel();
			lblInstitucion.setText("Institución:");
			lblInstitucion.setBounds(new Rectangle(0, 160, 130, 20));
			lblInstitucion.setHorizontalAlignment(SwingConstants.RIGHT);
			lblProfesion = new JLabel();
			lblProfesion.setText("Profesión:");
			lblProfesion.setBounds(new Rectangle(0, 135, 130, 20));
			lblProfesion.setHorizontalAlignment(SwingConstants.RIGHT);
			lblFechaNacimiento = new JLabel();
			lblFechaNacimiento.setText("*Fecha de nacimiento:");
			lblFechaNacimiento.setBounds(new Rectangle(0, 110, 130, 20));
			lblFechaNacimiento.setHorizontalAlignment(SwingConstants.RIGHT);
			lblSexo = new JLabel();
			lblSexo.setHorizontalAlignment(SwingConstants.RIGHT);
			lblSexo.setBounds(new Rectangle(0, 85, 130, 20));
			lblSexo.setText("Sexo:");
			lblCedula = new JLabel();
			lblCedula.setLocation(new Point(0, 60));
			lblCedula.setHorizontalAlignment(SwingConstants.RIGHT);
			lblCedula.setText("Cédula:");
			lblCedula.setSize(new Dimension(130, 20));
			lblApellidos = new JLabel();
			lblApellidos.setLocation(new Point(0, 35));
			lblApellidos.setHorizontalAlignment(SwingConstants.RIGHT);
			lblApellidos.setText("*Apellidos:");
			lblApellidos.setSize(new Dimension(130, 20));
			lblNombre = new JLabel();
			lblNombre.setLocation(new Point(0, 10));
			lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNombre.setText("*Nombres:");
			lblNombre.setSize(new Dimension(130, 20));
			panelDatos = new JPanel();
			panelDatos.setLayout(null);
			panelDatos.add(lblNombre, null);
			panelDatos.add(lblApellidos, null);
			panelDatos.add(lblCedula, null);
			panelDatos.add(getTxtNombre(), null);
			panelDatos.add(getTxtApellidos(), null);
			panelDatos.add(getTxtCedula(), null);
			panelDatos.add(lblSexo, null);
			panelDatos.add(getCbxSexo(), null);
			panelDatos.add(lblFechaNacimiento, null);
			panelDatos.add(lblProfesion, null);
			panelDatos.add(lblInstitucion, null);
			panelDatos.add(lblTelefono1, null);
			panelDatos.add(lblTelefono2, null);
			panelDatos.add(lblCarnet, null);
			panelDatos.add(lblDirección, null);
			panelDatos.add(getTxtFechaNacimiento(), null);
			panelDatos.add(getTxtProfesion(), null);
			panelDatos.add(getTxtInstitucion(), null);
			panelDatos.add(getTxtTelefono1(), null);
			panelDatos.add(getTxtTelefono2(), null);
			panelDatos.add(getTxtCarnet(), null);
			panelDatos.add(getJScrollPane(), null);
		}
		return panelDatos;
	}

	/**
	 * This method initializes txtNombre	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getTxtNombre() {
		if (txtNombre == null) {
			txtNombre = new JTextField();
			txtNombre.setLocation(new Point(135, 8));
			txtNombre.setSize(new Dimension(250, 25));
		}
		return txtNombre;
	}

	/**
	 * This method initializes txtApellidos	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getTxtApellidos() {
		if (txtApellidos == null) {
			txtApellidos = new JTextField();
			txtApellidos.setLocation(new Point(135, 33));
			txtApellidos.setSize(new Dimension(250, 25));
		}
		return txtApellidos;
	}

	/**
	 * This method initializes txtCedula	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getTxtCedula() {
		if (txtCedula == null) {
			txtCedula = new JTextField();
			txtCedula.setLocation(new Point(135, 58));
			txtCedula.setSize(new Dimension(250, 25));
		}
		return txtCedula;
	}

	/**
	 * This method initializes cbxSexo	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox getCbxSexo() {
		if (cbxSexo == null) {
			cbxSexo = new JComboBox();
			Item[] items = new Item[2];
			items[0] = new Item("Masculino", "M");
			items[1] = new Item("Femenino", "F");
			cbxSexo.setModel(new DefaultComboBoxModel(items));
			cbxSexo.setSize(new Dimension(160, 26));
			cbxSexo.setLocation(new Point(135, 83));
		}
		return cbxSexo;
	}

	/**
	 * This method initializes txtFechaNacimiento	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JDateChooser getTxtFechaNacimiento() {
		if (txtFechaNacimiento == null) {
			txtFechaNacimiento = new JDateChooser();
			txtFechaNacimiento.setBounds(new Rectangle(135, 108, 160, 25));
			txtFechaNacimiento.setMaxSelectableDate(new Date());
		}
		return txtFechaNacimiento;
	}

	/**
	 * This method initializes txtProfesion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getTxtProfesion() {
		if (txtProfesion == null) {
			txtProfesion = new JTextField();
			txtProfesion.setBounds(new Rectangle(135, 133, 250, 25));
		}
		return txtProfesion;
	}

	/**
	 * This method initializes txtInstitucion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getTxtInstitucion() {
		if (txtInstitucion == null) {
			txtInstitucion = new JTextField();
			txtInstitucion.setBounds(new Rectangle(135, 158, 250, 25));
		}
		return txtInstitucion;
	}

	/**
	 * This method initializes txtTelefono1	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getTxtTelefono1() {
		if (txtTelefono1 == null) {
			txtTelefono1 = new JTextField();
			txtTelefono1.setBounds(new Rectangle(135, 183, 250, 25));
		}
		return txtTelefono1;
	}

	/**
	 * This method initializes txtTelefono2	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getTxtTelefono2() {
		if (txtTelefono2 == null) {
			txtTelefono2 = new JTextField();
			txtTelefono2.setBounds(new Rectangle(135, 208, 250, 25));
		}
		return txtTelefono2;
	}

	/**
	 * This method initializes txtCarnet	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getTxtCarnet() {
		if (txtCarnet == null) {
			txtCarnet = new JTextField();
			txtCarnet.setBounds(new Rectangle(135, 233, 160, 25));
		}
		return txtCarnet;
	}

	/**
	 * This method initializes txtNombresReferencia1	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getTxtNombresReferencia1() {
		if (txtNombresReferencia1 == null) {
			txtNombresReferencia1 = new JTextField();
			txtNombresReferencia1.setBounds(new Rectangle(85, 33, 270, 25));
		}
		return txtNombresReferencia1;
	}

	/**
	 * This method initializes txtApellidosReferencia1	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getTxtApellidosReferencia1() {
		if (txtApellidosReferencia1 == null) {
			txtApellidosReferencia1 = new JTextField();
			txtApellidosReferencia1.setBounds(new Rectangle(86, 58, 270, 25));
		}
		return txtApellidosReferencia1;
	}

	/**
	 * This method initializes txtTelefonoReferencia1	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getTxtTelefonoReferencia1() {
		if (txtTelefonoReferencia1 == null) {
			txtTelefonoReferencia1 = new JTextField();
			txtTelefonoReferencia1.setBounds(new Rectangle(86, 83, 270, 25));
		}
		return txtTelefonoReferencia1;
	}

	/**
	 * This method initializes txtNombresReferencia2	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getTxtNombresReferencia2() {
		if (txtNombresReferencia2 == null) {
			txtNombresReferencia2 = new JTextField();
			txtNombresReferencia2.setBounds(new Rectangle(85, 33, 270, 25));
		}
		return txtNombresReferencia2;
	}

	/**
	 * This method initializes txtApellidosReferencia2	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getTxtApellidosReferencia2() {
		if (txtApellidosReferencia2 == null) {
			txtApellidosReferencia2 = new JTextField();
			txtApellidosReferencia2.setBounds(new Rectangle(85, 58, 270, 25));
		}
		return txtApellidosReferencia2;
	}

	/**
	 * This method initializes txtTelefonoReferencia2	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getTxtTelefonoReferencia2() {
		if (txtTelefonoReferencia2 == null) {
			txtTelefonoReferencia2 = new JTextField();
			txtTelefonoReferencia2.setBounds(new Rectangle(85, 83, 270, 25));
		}
		return txtTelefonoReferencia2;
	}

	/**
	 * This method initializes jScrollPane	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane() {
		if (jScrollPane == null) {
			jScrollPane = new JScrollPane();
			jScrollPane.setBounds(new Rectangle(135, 258, 250, 55));
			jScrollPane.setViewportView(getTxtDireccion());
		}
		return jScrollPane;
	}

	/**
	 * This method initializes txtDireccion	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	public JTextArea getTxtDireccion() {
		if (txtDireccion == null) {
			txtDireccion = new JTextArea();
		}
		return txtDireccion;
	}

	/**
	 * This method initializes jScrollPane1	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setBounds(new Rectangle(86, 108, 270, 60));
			jScrollPane1.setViewportView(getTxtDireccionReferencia1());
		}
		return jScrollPane1;
	}

	/**
	 * This method initializes txtDireccionReferencia1	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	public JTextArea getTxtDireccionReferencia1() {
		if (txtDireccionReferencia1 == null) {
			txtDireccionReferencia1 = new JTextArea();
		}
		return txtDireccionReferencia1;
	}

	/**
	 * This method initializes jScrollPane2	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	public JScrollPane getJScrollPane2() {
		if (jScrollPane2 == null) {
			jScrollPane2 = new JScrollPane();
			jScrollPane2.setBounds(new Rectangle(85, 108, 270, 60));
			jScrollPane2.setViewportView(getTxtDireccionReferencia2());
		}
		return jScrollPane2;
	}

	/**
	 * This method initializes txtDireccionReferencia2	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	public JTextArea getTxtDireccionReferencia2() {
		if (txtDireccionReferencia2 == null) {
			txtDireccionReferencia2 = new JTextArea();
		}
		return txtDireccionReferencia2;
	}

	/**
	 * This method initializes txtCedulaReferencia1	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getTxtCedulaReferencia1() {
		if (txtCedulaReferencia1 == null) {
			txtCedulaReferencia1 = new JTextField();
			txtCedulaReferencia1.setBounds(new Rectangle(85, 8, 270, 25));
		}
		return txtCedulaReferencia1;
	}

	/**
	 * This method initializes txtCedulaReferencia2	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getTxtCedulaReferencia2() {
		if (txtCedulaReferencia2 == null) {
			txtCedulaReferencia2 = new JTextField();
			txtCedulaReferencia2.setLocation(new Point(85, 8));
			txtCedulaReferencia2.setSize(new Dimension(270, 25));
		}
		return txtCedulaReferencia2;
	}

}
