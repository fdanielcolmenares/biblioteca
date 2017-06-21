package GestionarPrestamos2;

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
import javax.swing.JDesktopPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import Utilitario.Centrar;
import Utilitario.MostrarMensajes;
import java.awt.event.KeyEvent;

public class VentanaEditarPrestamos2 {

	private JInternalFrame ventana = null;  //  @jve:decl-index=0:visual-constraint="183,34"
	private JPanel panelVentana = null;
	private JPanel panelDatos = null;
	private JPanel panelBotones = null;
	private JButton btnGuardar = null;
	private JButton btnEliminar = null;
	private JButton btnCerrar = null;
	private GestorPrestamos2 gestor;
	private JLabel lblCedula = null;
	private JTextField txtCedula = null;
	private JButton btnBuscarUsuario = null;
	private JLabel lblNombre = null;
	private JTextField txtNombre = null;
	private JLabel lblLibro = null;
	private JTextField txtLibro = null;
	private JButton btnBuscar = null;
	private JDateChooser txtFechaPrestamo = null;
	private JDateChooser txtFechaVencimiento = null;
	private JDateChooser txtFechaEntrega = null;
	private JLabel lblFecha1 = null;
	private JLabel lblVencimientp = null;
	public JLabel lblEntrega = null;
	private JLabel lblTitulo = null;
	private JTextField txtTitulo = null;
	private JDesktopPane desktopPane;
	
	public VentanaEditarPrestamos2(GestorPrestamos2 gestor, JDesktopPane desktopPane){
		this.gestor = gestor;
		this.desktopPane = desktopPane;
		getVentana();
		
		ventana.setLocation(Centrar.centrarEnComponente(ventana.getSize(), desktopPane.getSize()));
		
		desktopPane.add(ventana);
		ventana.setVisible(true);
	}
	
	public void setGestor(GestorPrestamos2 gestor){
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
			ventana.setSize(new Dimension(434, 336));
			ventana.setClosable(true);
			ventana.setMaximizable(true);
			ventana.setResizable(true);
			ventana.setIconifiable(true);
			ventana.setTitle("Préstamo");
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
			lblTitulo = new JLabel();
			lblTitulo.setText("Título:");
			lblTitulo.setSize(new Dimension(75, 20));
			lblTitulo.setHorizontalAlignment(SwingConstants.RIGHT);
			lblTitulo.setLocation(new Point(0, 109));
			lblEntrega = new JLabel();
			lblEntrega.setText("Entrega:");
			lblEntrega.setSize(new Dimension(75, 20));
			lblEntrega.setHorizontalAlignment(SwingConstants.RIGHT);
			lblEntrega.setLocation(new Point(0, 208));
			lblVencimientp = new JLabel();
			lblVencimientp.setText("Vencimiento:");
			lblVencimientp.setSize(new Dimension(75, 20));
			lblVencimientp.setHorizontalAlignment(SwingConstants.RIGHT);
			lblVencimientp.setLocation(new Point(0, 175));
			lblFecha1 = new JLabel();
			lblFecha1.setText("Préstamo:");
			lblFecha1.setSize(new Dimension(75, 20));
			lblFecha1.setHorizontalAlignment(SwingConstants.RIGHT);
			lblFecha1.setLocation(new Point(0, 142));
			lblLibro = new JLabel();
			lblLibro.setText("Ejemplar:");
			lblLibro.setSize(new Dimension(75, 20));
			lblLibro.setLocation(new Point(0, 76));
			lblLibro.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNombre = new JLabel();
			lblNombre.setText("Nombre:");
			lblNombre.setSize(new Dimension(75, 20));
			lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNombre.setLocation(new Point(0, 45));
			lblCedula = new JLabel();
			lblCedula.setText("Cédula:");
			lblCedula.setLocation(new Point(0, 9));
			lblCedula.setSize(new Dimension(75, 20));
			lblCedula.setHorizontalAlignment(SwingConstants.RIGHT);
			panelDatos = new JPanel();
			panelDatos.setLayout(null);
			panelDatos.add(lblCedula, null);
			panelDatos.add(getTxtCedula(), null);
			panelDatos.add(getBtnBuscarUsuario(), null);
			panelDatos.add(lblNombre, null);
			panelDatos.add(getTxtNombre(), null);
			panelDatos.add(lblLibro, null);
			panelDatos.add(getTxtLibro(), null);
			panelDatos.add(getBtnBuscar(), null);
			panelDatos.add(getTxtFechaPrestamo(), null);
			panelDatos.add(getTxtFechaVencimiento(), null);
			panelDatos.add(getTxtFechaEntrega(), null);
			panelDatos.add(lblFecha1, null);
			panelDatos.add(lblVencimientp, null);
			panelDatos.add(lblEntrega, null);
			panelDatos.add(lblTitulo, null);
			panelDatos.add(getTxtTitulo(), null);
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
	public JButton getBtnGuardar() {
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
					if(MostrarMensajes.mostrarMensaje("¿Realmente desea eliminar la misión?", MostrarMensajes.MENSAJE_PREGUNTA)){
						
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
	 * This method initializes txtCedula	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getTxtCedula() {
		if (txtCedula == null) {
			txtCedula = new JTextField();
			txtCedula.setSize(new Dimension(170, 25));
			txtCedula.setEditable(false);
			txtCedula.setLocation(new Point(80, 7));
		}
		return txtCedula;
	}

	/**
	 * This method initializes btnBuscarUsuario	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton getBtnBuscarUsuario() {
		if (btnBuscarUsuario == null) {
			btnBuscarUsuario = new JButton();
			btnBuscarUsuario.setLocation(new Point(259, 2));
			btnBuscarUsuario.setText("Buscar");
			btnBuscarUsuario.setIcon(new ImageIcon(getClass().getResource("/Imagenes/Imagen015.png")));
			btnBuscarUsuario.setSize(new Dimension(110, 35));
			btnBuscarUsuario.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.buscarUsuario();
				}
			});
		}
		return btnBuscarUsuario;
	}

	/**
	 * This method initializes txtNombre	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getTxtNombre() {
		if (txtNombre == null) {
			txtNombre = new JTextField();
			txtNombre.setEditable(false);
			txtNombre.setSize(new Dimension(287, 25));
			txtNombre.setLocation(new Point(80, 43));
		}
		return txtNombre;
	}

	/**
	 * This method initializes txtLibro	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getTxtLibro() {
		if (txtLibro == null) {
			txtLibro = new JTextField();
			txtLibro.setSize(new Dimension(170, 25));
			txtLibro.setEditable(false);
			txtLibro.setLocation(new Point(80, 74));
		}
		return txtLibro;
	}

	/**
	 * This method initializes btnBuscar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton getBtnBuscar() {
		if (btnBuscar == null) {
			btnBuscar = new JButton();
			btnBuscar.setLocation(new Point(259, 69));
			btnBuscar.setText("Buscar");
			btnBuscar.setIcon(new ImageIcon(getClass().getResource("/Imagenes/Imagen015.png")));
			btnBuscar.setSize(new Dimension(110, 35));
			btnBuscar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.buscarEjemplar();
				}
			});
		}
		return btnBuscar;
	}

	/**
	 * This method initializes txtFechaPrestamo	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JDateChooser getTxtFechaPrestamo() {
		if (txtFechaPrestamo == null) {
			txtFechaPrestamo = new JDateChooser();
			txtFechaPrestamo.setLocation(new Point(80, 140));
			txtFechaPrestamo.setSize(new Dimension(152, 25));
		}
		return txtFechaPrestamo;
	}

	/**
	 * This method initializes txtFechaVencimiento	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JDateChooser getTxtFechaVencimiento() {
		if (txtFechaVencimiento == null) {
			txtFechaVencimiento = new JDateChooser();
			txtFechaVencimiento.setLocation(new Point(80, 173));
			txtFechaVencimiento.setSize(new Dimension(152, 25));
		}
		return txtFechaVencimiento;
	}

	/**
	 * This method initializes txtFechaEntrega	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JDateChooser getTxtFechaEntrega() {
		if (txtFechaEntrega == null) {
			txtFechaEntrega = new JDateChooser();
			txtFechaEntrega.setLocation(new Point(80, 206));
			txtFechaEntrega.setSize(new Dimension(152, 25));
		}
		return txtFechaEntrega;
	}

	/**
	 * This method initializes txtTitulo	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getTxtTitulo() {
		if (txtTitulo == null) {
			txtTitulo = new JTextField();
			txtTitulo.setLocation(new Point(80, 107));
			txtTitulo.setSize(new Dimension(287, 25));
		}
		return txtTitulo;
	}
	
	public JDesktopPane getDesktopPane(){
		return desktopPane;
	}

}
