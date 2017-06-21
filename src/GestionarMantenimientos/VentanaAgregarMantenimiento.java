package GestionarMantenimientos;

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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class VentanaAgregarMantenimiento {

	private JInternalFrame ventana = null;  //  @jve:decl-index=0:visual-constraint="183,34"
	private JPanel panelVentana = null;
	private JPanel panelDatos = null;
	private JPanel panelBotones = null;
	private JButton btnGuardar = null;
	private JButton btnEliminar = null;
	private JButton btnCerrar = null;
	private JLabel lblLibro = null;
	private JTextField txtLibro = null;
	private GestorMantenimientos gestor;
	private JButton btnBuscarLibro = null;
	private JLabel lblFecha = null;
	private JDateChooser txtFecha = null;
	private JScrollPane scrlObservaciones = null;
	private JTextArea txtObservaciones = null;
	private JLabel lblObservaciones = null;
	private JLabel lblTitulo = null;
	private JTextField txtTitulo = null;
	private JDesktopPane desktopPane;
	
	public VentanaAgregarMantenimiento(GestorMantenimientos gestor, JDesktopPane desktopPane){
		this.gestor = gestor;
		this.desktopPane = desktopPane;
		
		getVentana();		
		ventana.setLocation(Centrar.centrarEnComponente(ventana.getSize(), desktopPane.getSize()));
		
		desktopPane.add(ventana);
		ventana.setVisible(true);
	}
	
	public void setGestor(GestorMantenimientos gestor){
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
			ventana.setSize(new Dimension(447, 385));
			ventana.setClosable(true);
			ventana.setMaximizable(true);
			ventana.setResizable(true);
			ventana.setIconifiable(true);
			ventana.setTitle("Mantenimiento");
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
			lblTitulo.setSize(new Dimension(84, 20));
			lblTitulo.setHorizontalAlignment(SwingConstants.RIGHT);
			lblTitulo.setLocation(new Point(0, 43));
			lblObservaciones = new JLabel();
			lblObservaciones.setText("Detalles:");
			lblObservaciones.setSize(new Dimension(84, 20));
			lblObservaciones.setHorizontalAlignment(SwingConstants.RIGHT);
			lblObservaciones.setLocation(new Point(0, 103));
			lblFecha = new JLabel();
			lblFecha.setText("*Fecha:");
			lblFecha.setSize(new Dimension(84, 20));
			lblFecha.setHorizontalAlignment(SwingConstants.RIGHT);
			lblFecha.setLocation(new Point(0, 73));
			lblLibro = new JLabel();
			lblLibro.setText("*Cota:");
			lblLibro.setSize(new Dimension(84, 20));
			lblLibro.setHorizontalAlignment(SwingConstants.RIGHT);
			lblLibro.setLocation(new Point(0, 13));
			panelDatos = new JPanel();
			panelDatos.setLayout(null);
			panelDatos.add(lblLibro, null);
			panelDatos.add(getTxtLibro(), null);
			panelDatos.add(getBtnBuscarLibro(), null);
			panelDatos.add(lblFecha, null);
			panelDatos.add(getTxtFecha(), null);
			panelDatos.add(getScrlObservaciones(), null);
			panelDatos.add(lblObservaciones, null);
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
	 * This method initializes txtLibro	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getTxtLibro() {
		if (txtLibro == null) {
			txtLibro = new JTextField();
			txtLibro.setLocation(new Point(90, 11));
			txtLibro.setEditable(false);
			txtLibro.setSize(new Dimension(194, 25));
		}
		return txtLibro;
	}

	/**
	 * This method initializes btnBuscarLibro	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton getBtnBuscarLibro() {
		if (btnBuscarLibro == null) {
			btnBuscarLibro = new JButton();
			btnBuscarLibro.setLocation(new Point(286, 6));
			btnBuscarLibro.setIcon(new ImageIcon(getClass().getResource("/Imagenes/Imagen015.png")));
			btnBuscarLibro.setText("Buscar");
			btnBuscarLibro.setSize(new Dimension(110, 35));
			btnBuscarLibro.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.buscarLibro();
				}
			});
		}
		return btnBuscarLibro;
	}

	/**
	 * This method initializes txtFecha	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JDateChooser getTxtFecha() {
		if (txtFecha == null) {
			txtFecha = new JDateChooser();
			txtFecha.setLocation(new Point(90, 71));
			txtFecha.setSize(new Dimension(134, 25));
		}
		return txtFecha;
	}

	/**
	 * This method initializes scrlObservaciones	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getScrlObservaciones() {
		if (scrlObservaciones == null) {
			scrlObservaciones = new JScrollPane();
			scrlObservaciones.setSize(new Dimension(373, 138));
			scrlObservaciones.setLocation(new Point(35, 133));
			scrlObservaciones.setViewportView(getTxtObservaciones());
		}
		return scrlObservaciones;
	}

	/**
	 * This method initializes txtObservaciones	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	public JTextArea getTxtObservaciones() {
		if (txtObservaciones == null) {
			txtObservaciones = new JTextArea();
		}
		return txtObservaciones;
	}

	/**
	 * This method initializes txtTitulo	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getTxtTitulo() {
		if (txtTitulo == null) {
			txtTitulo = new JTextField();
			txtTitulo.setLocation(new Point(90, 40));
			txtTitulo.setEditable(false);
			txtTitulo.setSize(new Dimension(300, 25));
		}
		return txtTitulo;
	}
	
	public JDesktopPane getDesktopPane(){
		return desktopPane;
	}

}
