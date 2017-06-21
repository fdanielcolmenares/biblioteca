package GestionarAplicacionRegistro;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import Componentes.CollapsiblePanel;
import Utilitario.Centrar;
import Utilitario.Tablas.JTableListado;
import com.toedter.calendar.JDateChooser;
import javax.swing.JScrollPane;
import java.util.Date;
import java.awt.Rectangle;

public class VentanaListadoRegistros {

	private JInternalFrame ventana = null;  //  @jve:decl-index=0:visual-constraint="62,13"
	private JPanel panelVentana = null;
	private JPanel panelCentral = null;
	private JPanel panelInferior = null;
	private JButton btnBuscar = null;
	private JButton btnAgregar = null;
	private JButton btnEliminar = null;
	private CollapsiblePanel panelFiltros = null;
	private JLabel lblFechaInicio = null;
	private JLabel lblFechaFin = null;
	private JDateChooser txtFechaInicio = null;
	private JDateChooser txtFechaFin = null;
	private JScrollPane scrollTabla = null;
	private JTableListado tablaListado = null;
	private JDesktopPane desktopPane;
	
	private GestorListadoRegistros gestor;
	
	public VentanaListadoRegistros(JDesktopPane desktopPane){
		this.desktopPane = desktopPane;
		getVentana();
		ventana.setLocation(Centrar.centrarEnComponente(ventana.getSize(), this.desktopPane.getSize()));
		
		panelFiltros.setCollapsed(true);
		getBtnEliminar().setVisible(false);
	}
	
	public void setGestor(GestorListadoRegistros gestor){
		this.gestor = gestor;
	}
	
	public JDesktopPane getDesktopPane() {
		return desktopPane;
	}

	/**
	 * This method initializes ventana	
	 * 	
	 * @return javax.swing.JInternalFrame	
	 */
	public JInternalFrame getVentana() {
		if (ventana == null) {
			ventana = new JInternalFrame();
			ventana.setSize(new Dimension(530, 396));
			ventana.setIconifiable(true);
			ventana.setMaximizable(true);
			ventana.setClosable(true);
			ventana.setTitle("Listado de registros");
			ventana.setResizable(true);
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
			panelVentana.add(getPanelCentral(), BorderLayout.CENTER);
			panelVentana.add(getPanelInferior(), BorderLayout.SOUTH);
		}
		return panelVentana;
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
	 * This method initializes panelInferior	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelInferior() {
		if (panelInferior == null) {
			panelInferior = new JPanel();
			panelInferior.setLayout(new FlowLayout());
			panelInferior.add(getBtnBuscar(), null);
			panelInferior.add(getBtnAgregar(), null);
			panelInferior.add(getBtnEliminar(), null);
		}
		return panelInferior;
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
					gestor.prepararListar();
				}
			});
		}
		return btnBuscar;
	}

	/**
	 * This method initializes btnAgregar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnAgregar() {
		if (btnAgregar == null) {
			btnAgregar = new JButton();
			btnAgregar.setPreferredSize(new Dimension(110, 35));
			btnAgregar.setMnemonic(KeyEvent.VK_N);
			btnAgregar.setIcon(new ImageIcon(getClass().getResource("/Imagenes/Imagen020.png")));
			btnAgregar.setText("Nuevo");
			btnAgregar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.prepararCrear();
				}
			});
		}
		return btnAgregar;
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
			btnEliminar.setMnemonic(KeyEvent.VK_E);
			btnEliminar.setIcon(new ImageIcon(getClass().getResource("/Imagenes/Imagen023.png")));
			btnEliminar.setText("Eliminar");
		}
		return btnEliminar;
	}

	/**
	 * This method initializes panelFiltros	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private CollapsiblePanel getPanelFiltros() {
		if (panelFiltros == null) {
			lblFechaFin = new JLabel();
			lblFechaFin.setText("Fecha final:");
			lblFechaFin.setBounds(new Rectangle(245, 30, 84, 20));
			lblFechaFin.setHorizontalAlignment(SwingConstants.RIGHT);
			lblFechaInicio = new JLabel();
			lblFechaInicio.setText("Fecha inicial:");
			lblFechaInicio.setBounds(new Rectangle(10, 30, 84, 20));
			lblFechaInicio.setHorizontalAlignment(SwingConstants.RIGHT);
			panelFiltros = new CollapsiblePanel("Filtros");
			panelFiltros.setCollapsed(false);			
			panelFiltros.setLayout(null);
			panelFiltros.setPreferredSize(new Dimension(100, 70));
			panelFiltros.setDimensiones(panelFiltros.getPreferredSize());
			panelFiltros.add(lblFechaInicio, null);
			panelFiltros.add(lblFechaFin, null);
			panelFiltros.add(getTxtFechaInicio(), null);
			panelFiltros.add(getTxtFechaFin(), null);
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
			txtFechaInicio.setDate(new Date());
			txtFechaInicio.setBounds(new Rectangle(95, 28, 150, 25));
		}
		return txtFechaInicio;
	}

	/**
	 * This method initializes txtFechaFin	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JDateChooser getTxtFechaFin() {
		if (txtFechaFin == null) {
			txtFechaFin = new JDateChooser();
			txtFechaFin.setDate(new Date());
			txtFechaFin.setBounds(new Rectangle(335, 28, 150, 25));
		}
		return txtFechaFin;
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
			tablaListado = new JTableListado(new String[]{"", "Fecha", "Sexo" ,"Edad", "Estudia", "Trabaja", "Misión"},
					new int[]{22, 20, 12, 12, 12, 22},
					false,
					true,
					true,
					scrollTabla);
		}
		return tablaListado;
	}

}
