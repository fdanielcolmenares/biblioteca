package GestionarMisiones;

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
import Utilitario.Centrar;
import Utilitario.MostrarMensajes;
import java.awt.event.KeyEvent;

public class VentanaEditarMision {

	private JInternalFrame ventana = null;  //  @jve:decl-index=0:visual-constraint="183,34"
	private JPanel panelVentana = null;
	private JPanel panelDatos = null;
	private JPanel panelBotones = null;
	private JButton btnGuardar = null;
	private JButton btnEliminar = null;
	private JButton btnCerrar = null;
	private JLabel lblNombre = null;
	private JTextField txtDescripcion = null;
	private GestorMisiones gestor;
	public VentanaEditarMision(GestorMisiones gestor, JDesktopPane desktopPane){
		this.gestor = gestor;
		getVentana();
		
		ventana.setLocation(Centrar.centrarEnComponente(ventana.getSize(), desktopPane.getSize()));
		
		desktopPane.add(ventana);
		ventana.setVisible(true);
	}
	
	public void setGestor(GestorMisiones gestor){
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
			ventana.setSize(new Dimension(388, 244));
			ventana.setClosable(true);
			ventana.setMaximizable(true);
			ventana.setResizable(true);
			ventana.setIconifiable(true);
			ventana.setTitle("Misión");
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
			lblNombre = new JLabel();
			lblNombre.setText("*Misión:");
			lblNombre.setSize(new Dimension(86, 20));
			lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNombre.setLocation(new Point(0, 10));
			panelDatos = new JPanel();
			panelDatos.setLayout(null);
			panelDatos.add(lblNombre, null);
			panelDatos.add(getTxtDescripcion(), null);
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
	 * This method initializes txtDescripcion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getTxtDescripcion() {
		if (txtDescripcion == null) {
			txtDescripcion = new JTextField();
			txtDescripcion.setLocation(new Point(90, 8));
			txtDescripcion.setSize(new Dimension(250, 25));
		}
		return txtDescripcion;
	}

}
