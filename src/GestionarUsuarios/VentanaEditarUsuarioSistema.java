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
import javax.swing.JPasswordField;
import javax.swing.JComboBox;

import Utilitario.Centrar;
import Utilitario.Item;
import Utilitario.MostrarMensajes;

import java.awt.event.KeyEvent;
import java.awt.Rectangle;

public class VentanaEditarUsuarioSistema {

	private JInternalFrame ventana = null;  //  @jve:decl-index=0:visual-constraint="183,34"
	private JPanel panelVentana = null;
	private JPanel panelDatos = null;
	private JPanel panelBotones = null;
	private JButton btnGuardar = null;
	private JButton btnEliminar = null;
	private JButton btnCerrar = null;
	private JLabel lblNombre = null;
	private JLabel lblApellidos = null;
	private JLabel lblCedula = null;
	private JLabel lblUsuario = null;
	private JLabel lblTipoUsuario = null;
	private JLabel lblClave1 = null;
	private JLabel lblClave2 = null;
	private JTextField txtNombre = null;
	private JTextField txtApellidos = null;
	private JTextField txtCedula = null;
	private JTextField txtUsuario = null;
	private JPasswordField txtClave1 = null;
	private JPasswordField txtClave2 = null;
	private JComboBox cbxTipoUsuario = null;
	
	private GestorUsuarios gestor;
	private JLabel lblSexo = null;
	private JComboBox cbxSexo = null;

	public VentanaEditarUsuarioSistema(GestorUsuarios gestor, JDesktopPane desktopPane){
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
			ventana.setSize(new Dimension(437, 335));
			ventana.setClosable(true);
			ventana.setMaximizable(true);
			ventana.setResizable(true);
			ventana.setIconifiable(true);
			ventana.setTitle("Usuario del sistema");
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
			panelVentana.add(getPanelDatos(), BorderLayout.CENTER);
			panelVentana.add(getPanelBotones(), BorderLayout.SOUTH);
		}
		return panelVentana;
	}

	/**
	 * This method initializes panelDatos	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelDatos() {
		if (panelDatos == null) {
			lblSexo = new JLabel();
			lblSexo.setText("Sexo:");
			lblSexo.setSize(new Dimension(130, 20));
			lblSexo.setHorizontalAlignment(SwingConstants.RIGHT);
			lblSexo.setLocation(new Point(0, 85));
			lblClave2 = new JLabel();
			lblClave2.setText("*Confirmar contraseña:");
			lblClave2.setSize(new Dimension(130, 20));
			lblClave2.setHorizontalAlignment(SwingConstants.RIGHT);
			lblClave2.setLocation(new Point(0, 160));
			lblClave1 = new JLabel();
			lblClave1.setText("*Contraseña:");
			lblClave1.setSize(new Dimension(130, 20));
			lblClave1.setHorizontalAlignment(SwingConstants.RIGHT);
			lblClave1.setLocation(new Point(0, 135));
			lblTipoUsuario = new JLabel();
			lblTipoUsuario.setText("Tipo de usuario:");
			lblTipoUsuario.setSize(new Dimension(130, 20));
			lblTipoUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
			lblTipoUsuario.setLocation(new Point(0, 185));
			lblUsuario = new JLabel();
			lblUsuario.setText("*Usuario:");
			lblUsuario.setSize(new Dimension(130, 20));
			lblUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
			lblUsuario.setLocation(new Point(0, 110));
			lblCedula = new JLabel();
			lblCedula.setText("*Cédula:");
			lblCedula.setSize(new Dimension(130, 20));
			lblCedula.setHorizontalAlignment(SwingConstants.RIGHT);
			lblCedula.setLocation(new Point(0, 60));
			lblApellidos = new JLabel();
			lblApellidos.setText("*Apellidos:");
			lblApellidos.setSize(new Dimension(130, 20));
			lblApellidos.setHorizontalAlignment(SwingConstants.RIGHT);
			lblApellidos.setLocation(new Point(0, 35));
			lblNombre = new JLabel();
			lblNombre.setText("*Nombres:");
			lblNombre.setSize(new Dimension(130, 20));
			lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNombre.setLocation(new Point(0, 10));
			panelDatos = new JPanel();
			panelDatos.setLayout(null);
			panelDatos.add(lblNombre, null);
			panelDatos.add(lblApellidos, null);
			panelDatos.add(lblCedula, null);
			panelDatos.add(lblUsuario, null);
			panelDatos.add(lblTipoUsuario, null);
			panelDatos.add(lblClave1, null);
			panelDatos.add(lblClave2, null);
			panelDatos.add(getTxtNombre(), null);
			panelDatos.add(getTxtApellidos(), null);
			panelDatos.add(getTxtCedula(), null);
			panelDatos.add(getTxtUsuario(), null);
			panelDatos.add(getTxtClave1(), null);
			panelDatos.add(getTxtClave2(), null);
			panelDatos.add(getCbxTipoUsuario(), null);
			panelDatos.add(lblSexo, null);
			panelDatos.add(getCbxSexo(), null);
		}
		return panelDatos;
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
	 * This method initializes txtUsuario	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getTxtUsuario() {
		if (txtUsuario == null) {
			txtUsuario = new JTextField();
			txtUsuario.setLocation(new Point(135, 108));
			txtUsuario.setSize(new Dimension(250, 25));
		}
		return txtUsuario;
	}

	/**
	 * This method initializes txtClave1	
	 * 	
	 * @return javax.swing.JPasswordField	
	 */
	public JPasswordField getTxtClave1() {
		if (txtClave1 == null) {
			txtClave1 = new JPasswordField();
			txtClave1.setLocation(new Point(135, 133));
			txtClave1.setSize(new Dimension(250, 25));
		}
		return txtClave1;
	}

	/**
	 * This method initializes txtClave2	
	 * 	
	 * @return javax.swing.JPasswordField	
	 */
	public JPasswordField getTxtClave2() {
		if (txtClave2 == null) {
			txtClave2 = new JPasswordField();
			txtClave2.setLocation(new Point(135, 158));
			txtClave2.setSize(new Dimension(250, 25));
		}
		return txtClave2;
	}

	/**
	 * This method initializes cbxTipoUsuario	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox getCbxTipoUsuario() {
		if (cbxTipoUsuario == null) {
			cbxTipoUsuario = new JComboBox();
			cbxTipoUsuario.setSize(new Dimension(250, 25));
			cbxTipoUsuario.setLocation(new Point(135, 183));
			cbxTipoUsuario.setModel(new DefaultComboBoxModel(gestor.getListadoTiposUsuario().toArray()));
		}
		return cbxTipoUsuario;
	}

	/**
	 * This method initializes cbxSexo	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox getCbxSexo() {
		if (cbxSexo == null) {
			cbxSexo = new JComboBox();
			cbxSexo.setBounds(new Rectangle(135, 82, 161, 26));
			Item[] items = new Item[2];
			items[0] = new Item("Masculino", "M");
			items[1] = new Item("Femenino", "F");
			cbxSexo.setModel(new DefaultComboBoxModel(items));
		}
		return cbxSexo;
	}

}
