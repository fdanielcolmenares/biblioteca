package GestionarPrestamos2;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;

import Utilitario.Centrar;
import Utilitario.MostrarMensajes;
import Utilitario.Tablas.JTableListado;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;
import Componentes.CollapsiblePanel;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.Point;

public class VentanaListadoPrestamos2 {

	private JInternalFrame ventana = null;  //  @jve:decl-index=0:visual-constraint="164,-3"
	private JPanel panelVentana = null;
	private JScrollPane scrollTabla = null;
	private JTableListado tablaListado = null;
	private JPanel panelBotones = null;  //  @jve:decl-index=0:visual-constraint="760,272"
	private JButton btnEditar = null;
	private JButton btnNuevo = null;
	private JButton btnEliminar = null;
	private GestorListadoPrestamos2 gestor;
	private JButton btnBuscar = null;
	private JDesktopPane desktopPane;
	private CollapsiblePanel panelFiltros = null;
	private JLabel lblCedula = null;
	private JTextField txtLibro = null;
	private JLabel lblUsuario = null;
	private JTextField txtUsuario = null;
	
	public VentanaListadoPrestamos2(GestorListadoPrestamos2 gestor, JDesktopPane desktopPane){
		this.gestor = gestor;
		this.desktopPane = desktopPane;
		getVentana();
		ventana.setLocation(Centrar.centrarEnComponente(ventana.getSize(), desktopPane.getSize()));
		getPanelFiltros().setCollapsed(true);
	}
	
	/**
	 * This method initializes ventana	
	 * 	
	 * @return javax.swing.JInternalFrame	
	 */
	public JInternalFrame getVentana() {
		if (ventana == null) {
			ventana = new JInternalFrame();
			ventana.setSize(new Dimension(584, 380));
			ventana.setClosable(true);
			ventana.setIconifiable(true);
			ventana.setMaximizable(true);
			ventana.setTitle("Listado de préstamos");
			ventana.setContentPane(getPanelVentana());
			ventana.setResizable(true);
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
			panelVentana.add(getScrollTabla(), BorderLayout.CENTER);
			panelVentana.add(getPanelFiltros(), BorderLayout.NORTH);
		}
		return panelVentana;
	}

	/**
	 * This method initializes scrollTabla	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getScrollTabla() {
		if (scrollTabla == null) {
			scrollTabla = new JScrollPane();
			scrollTabla.setViewportView(getTablaListado());
		}
		return scrollTabla;
	}

	/**
	 * This method initializes tablaListado	
	 * 	
	 * @return javax.swing.JTable	
	 */
	public JTableListado getTablaListado() {
		if (tablaListado == null) {
			tablaListado = new JTableListado(new String[]{"", "Cédula", "Usuario", "Título", "Cota", "Fecha Préstamo", "Fecha Vencimiento", "Fecha Entrega"},
												new int[]{10, 15, 25, 15, 12, 12, 11},
												false,
												true,
												true,
												scrollTabla);
		}
		return tablaListado;
	}

	/**
	 * This method initializes panelBotones	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelBotones() {
		if (panelBotones == null) {
			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setHgap(15);
			panelBotones = new JPanel();
			panelBotones.setLayout(flowLayout);
			panelBotones.setBorder(null);
			panelBotones.add(getBtnBuscar(), null);
			panelBotones.add(getBtnEditar(), null);
			panelBotones.add(getBtnNuevo(), null);
			panelBotones.add(getBtnEliminar(), null);			
		}
		return panelBotones;
	}

	/**
	 * This method initializes btnEditar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnEditar() {
		if (btnEditar == null) {
			btnEditar = new JButton();
			btnEditar.setPreferredSize(new Dimension(110, 35));
			btnEditar.setIcon(new ImageIcon(getClass().getResource("/Imagenes/Imagen022.png")));
			btnEditar.setMnemonic(KeyEvent.VK_R);
			btnEditar.setText("Recibir");
			btnEditar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					/*int index = tablaListado.getFilaSeleccionada();
					if(index >= 0){
						gestor.editarMision(tablaListado.getModel().getItem(index).getValorInt());
					}
					*/
					gestor.recibirPrestamos();
				}
			});
		}
		return btnEditar;
	}

	/**
	 * This method initializes btnNuevo	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnNuevo() {
		if (btnNuevo == null) {
			btnNuevo = new JButton();
			btnNuevo.setText("Nuevo");
			btnNuevo.setPreferredSize(new Dimension(110, 35));
			btnNuevo.setMnemonic(KeyEvent.VK_N);
			btnNuevo.setIcon(new ImageIcon(getClass().getResource("/Imagenes/Imagen020.png")));
			btnNuevo.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.crearPrestamo();
				}
			});
		}
		return btnNuevo;
	}

	/**
	 * This method initializes btnEliminar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnEliminar() {
		if (btnEliminar == null) {
			btnEliminar = new JButton();
			btnEliminar.setPreferredSize(new Dimension(110, 35));
			btnEliminar.setIcon(new ImageIcon(getClass().getResource("/Imagenes/Imagen023.png")));
			btnEliminar.setMnemonic(KeyEvent.VK_L);
			btnEliminar.setText("Eliminar");
			btnEliminar.setVisible(false);
			btnEliminar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int index = tablaListado.getFilaSeleccionada();
					if(index >= 0){
						if(MostrarMensajes.mostrarMensaje("¿Realmente desea eliminar la misión?", MostrarMensajes.MENSAJE_PREGUNTA)){
							gestor.eliminarMision(tablaListado.getModel().getItem(index).getValorInt());
						}
					}
				}
			});
		}
		return btnEliminar;
	}

	/**
	 * This method initializes btnBuscar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnBuscar() {
		if (btnBuscar == null) {
			btnBuscar = new JButton();
			btnBuscar.setPreferredSize(new Dimension(110, 35));
			btnBuscar.setIcon(new ImageIcon(getClass().getResource("/Imagenes/Imagen024.png")));
			btnBuscar.setMnemonic(KeyEvent.VK_B);
			btnBuscar.setText("Buscar");
			btnBuscar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.listarPrestamos();
				}
			});
		}
		return btnBuscar;
	}

	public JDesktopPane getDesktopPane() {
		return desktopPane;
	}

	public void setDesktopPane(JDesktopPane desktopPane) {
		this.desktopPane = desktopPane;
	}

	/**
	 * This method initializes panelFiltros	
	 * 	
	 * @return Componentes.CollapsiblePanel	
	 */
	private CollapsiblePanel getPanelFiltros() {
		if (panelFiltros == null) {
			lblUsuario = new JLabel();
			lblUsuario.setText("Usuario:");
			lblUsuario.setSize(new Dimension(75, 20));
			lblUsuario.setHorizontalAlignment(SwingConstants.RIGHT);
			lblUsuario.setLocation(new Point(5, 60));
			lblCedula = new JLabel();
			lblCedula.setBounds(new Rectangle(5, 30, 75, 20));
			lblCedula.setText("Título:");
			lblCedula.setHorizontalAlignment(SwingConstants.RIGHT);
			panelFiltros = new CollapsiblePanel("Filtros");
			panelFiltros.setLayout(null);
			panelFiltros.setPreferredSize(new Dimension(1, 100));
			panelFiltros.setDimensiones(getPanelFiltros().getPreferredSize());
			panelFiltros.setCollapsed(false);
			panelFiltros.add(lblCedula, null);
			panelFiltros.add(getTxtLibro(), null);
			panelFiltros.add(lblUsuario, null);
			panelFiltros.add(getTxtUsuario(), null);
		}
		return panelFiltros;
	}

	/**
	 * This method initializes txtLibro	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getTxtLibro() {
		if (txtLibro == null) {
			txtLibro = new JTextField();
			txtLibro.setLocation(new Point(85, 28));
			txtLibro.setText("*");
			txtLibro.setSize(new Dimension(197, 25));
		}
		return txtLibro;
	}

	/**
	 * This method initializes txtUsuario	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getTxtUsuario() {
		if (txtUsuario == null) {
			txtUsuario = new JTextField();
			txtUsuario.setLocation(new Point(85, 58));
			txtUsuario.setText("*");
			txtUsuario.setSize(new Dimension(197, 25));
		}
		return txtUsuario;
	}

}
