package GestionarMantenimientos;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import Componentes.CollapsiblePanel;
import Utilitario.Centrar;
import Utilitario.MostrarMensajes;
import Utilitario.Tablas.JTableListado;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Point;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

public class VentanaListadoMantenimientos {

	private JInternalFrame ventana = null;  //  @jve:decl-index=0:visual-constraint="164,-3"
	private JPanel panelVentana = null;
	private JScrollPane scrollTabla = null;
	private JTableListado tablaListado = null;
	private JPanel panelBotones = null;  //  @jve:decl-index=0:visual-constraint="760,272"
	private JButton btnNuevo = null;
	private JButton btnEliminar = null;
	private GestorListadoMantenimientos gestor;  //  @jve:decl-index=0:
	private JButton btnBuscar = null;
	private JDesktopPane desktopPane;
	private JPanel panelCentral = null;
	private CollapsiblePanel panelFiltros = null;
	private JLabel lblFecha = null;
	private JLabel lblLibro = null;
	private JDateChooser txtFechaInicio = null;
	private JTextField txtCota = null;
	private JButton btnAceptar = null;
	private JLabel lblFechaFin = null;
	private JDateChooser txtFechaFin = null;
	private JLabel lblEjemplar = null;
	private JTextField txtEjemplar = null;
	
	
	public VentanaListadoMantenimientos(GestorListadoMantenimientos gestor, JDesktopPane desktopPane){
		/*this.gestor = gestor;
		this.desktopPane = desktopPane;
		getVentana();
		ventana.setLocation(Centrar.centrarEnComponente(ventana.getSize(), desktopPane.getSize()));
		panelFiltros.setCollapsed(true);*/
		init(gestor, desktopPane);
	}
	
	private void init(GestorListadoMantenimientos gestor, JDesktopPane desktopPane){
		this.gestor = gestor;
		this.desktopPane = desktopPane;
		
		getVentana();
		panelFiltros.setCollapsed(true);
		ventana.setLocation(Centrar.centrarEnComponente(ventana.getSize(), desktopPane.getSize()));
	}
	
	/**
	 * This method initializes ventana	
	 * 	
	 * @return javax.swing.JInternalFrame	
	 */
	public JInternalFrame getVentana() {
		if (ventana == null) {
			ventana = new JInternalFrame();
			ventana.setSize(new Dimension(522, 424));
			ventana.setClosable(true);
			ventana.setIconifiable(true);
			ventana.setMaximizable(true);
			ventana.setTitle("Listado de mantenimientos");
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
			panelVentana.add(getPanelCentral(), BorderLayout.CENTER);
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
			tablaListado = new JTableListado(new String[]{"", "Código" ,"Fecha", "Cota", "Título"},
												new int[]{20, 30, 30, 20},
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
			panelBotones = new JPanel();
			panelBotones.setLayout(new FlowLayout());
			panelBotones.setBorder(null);
			panelBotones.add(getBtnAceptar(), null);
			panelBotones.add(getBtnBuscar(), null);			
			panelBotones.add(getBtnNuevo(), null);
			panelBotones.add(getBtnEliminar(), null);
		}
		return panelBotones;
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
					gestor.crearMantenimiento();
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
	public JButton getBtnEliminar() {
		if (btnEliminar == null) {
			btnEliminar = new JButton();
			btnEliminar.setPreferredSize(new Dimension(110, 35));
			btnEliminar.setIcon(new ImageIcon(getClass().getResource("/Imagenes/Imagen023.png")));
			btnEliminar.setMnemonic(KeyEvent.VK_L);
			btnEliminar.setText("Eliminar");
			btnEliminar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int index = tablaListado.getFilaSeleccionada();
					if(index >= 0){
						if(MostrarMensajes.mostrarMensaje("¿Realmente desea eliminar el usuario?", MostrarMensajes.MENSAJE_PREGUNTA)){
							gestor.eliminarMantenimiento(tablaListado.getModel().getItem(index).getValorInt());
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
					gestor.listarMantenimientos();
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
	 * This method initializes panelCentral	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelCentral() {
		if (panelCentral == null) {
			panelCentral = new JPanel();
			panelCentral.setLayout(new BorderLayout());
			panelCentral.add(getPanelFiltros(), BorderLayout.NORTH);
			panelCentral.add(getScrollTabla(), BorderLayout.CENTER);
		}
		return panelCentral;
	}

	/**
	 * This method initializes panelFiltros	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private CollapsiblePanel getPanelFiltros() {
		if (panelFiltros == null) {
			lblEjemplar = new JLabel();
			lblEjemplar.setText("Ejemplar:");
			lblEjemplar.setSize(new Dimension(63, 20));
			lblEjemplar.setHorizontalAlignment(SwingConstants.RIGHT);
			lblEjemplar.setLocation(new Point(250, 60));
			lblFechaFin = new JLabel();
			lblFechaFin.setText("hasta:");
			lblFechaFin.setSize(new Dimension(63, 20));
			lblFechaFin.setHorizontalAlignment(SwingConstants.RIGHT);
			lblFechaFin.setLocation(new Point(250, 30));
			lblLibro = new JLabel();
			lblLibro.setHorizontalAlignment(SwingConstants.RIGHT);
			lblLibro.setLocation(new Point(5, 60));
			lblLibro.setSize(new Dimension(85, 20));
			lblLibro.setText("Cota:");
			lblFecha = new JLabel();
			lblFecha.setHorizontalAlignment(SwingConstants.RIGHT);
			lblFecha.setLocation(new Point(5, 30));
			lblFecha.setSize(new Dimension(85, 20));
			lblFecha.setText("Desde fecha:");
			panelFiltros = new CollapsiblePanel("Filtros");
			panelFiltros.setCollapsed(false);
			panelFiltros.setLayout(null);
			panelFiltros.setPreferredSize(new Dimension(1, 100));
			panelFiltros.setDimensiones(panelFiltros.getPreferredSize());
			panelFiltros.add(lblFecha, null);
			panelFiltros.add(lblLibro, null);
			panelFiltros.add(getTxtFechaInicio(), null);
			panelFiltros.add(getTxtCota(), null);
			panelFiltros.add(lblFechaFin, null);
			panelFiltros.add(getTxtFechaFin(), null);
			panelFiltros.add(lblEjemplar, null);
			panelFiltros.add(getTxtEjemplar(), null);
		}
		return panelFiltros;
	}

	/**
	 * This method initializes txtFechaInicio	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JDateChooser getTxtFechaInicio() {
		if (txtFechaInicio == null) {
			txtFechaInicio = new JDateChooser();
			txtFechaInicio.setLocation(new Point(95, 28));
			txtFechaInicio.setSize(new Dimension(152, 25));
		}
		return txtFechaInicio;
	}

	/**
	 * This method initializes txtCota	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getTxtCota() {
		if (txtCota == null) {
			txtCota = new JTextField();
			txtCota.setLocation(new Point(95, 58));
			txtCota.setSize(new Dimension(150, 25));
			txtCota.setText("*");
		}
		return txtCota;
	}

	/**
	 * This method initializes btnAceptar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnAceptar() {
		if (btnAceptar == null) {
			btnAceptar = new JButton();
			btnAceptar.setPreferredSize(new Dimension(110, 35));
			btnAceptar.setMnemonic(KeyEvent.VK_A);
			btnAceptar.setText("Aceptar");
			btnAceptar.setIcon(new ImageIcon(getClass().getResource("/Imagenes/Imagen017.png")));
			btnAceptar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int index = tablaListado.getFilaSeleccionada();
					if(index >= 0){
						int idMantenimiento = tablaListado.getModel().getItem(index).getValorInt();
						gestor.prepararVer(idMantenimiento);
					}
				}
			});
		}
		return btnAceptar;
	}

	/**
	 * This method initializes txtFechaFin	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JDateChooser getTxtFechaFin() {
		if (txtFechaFin == null) {
			txtFechaFin = new JDateChooser();
			txtFechaFin.setLocation(new Point(315, 29));
			txtFechaFin.setSize(new Dimension(152, 25));
		}
		return txtFechaFin;
	}
	
	public JDesktopPane getDesktopPanel(){
		return desktopPane;
	}

	/**
	 * This method initializes txtEjemplar	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getTxtEjemplar() {
		if (txtEjemplar == null) {
			txtEjemplar = new JTextField();
			txtEjemplar.setText("*");
			txtEjemplar.setSize(new Dimension(100, 25));
			txtEjemplar.setLocation(new Point(315, 58));
		}
		return txtEjemplar;
	}

}
