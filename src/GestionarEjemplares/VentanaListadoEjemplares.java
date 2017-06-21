package GestionarEjemplares;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import Componentes.CollapsiblePanel;
import Utilitario.Centrar;
import Utilitario.Tablas.JTableListado;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Point;
import javax.swing.JTextField;

public class VentanaListadoEjemplares {

	private JInternalFrame ventana = null;  //  @jve:decl-index=0:visual-constraint="164,-3"
	private JPanel panelVentana = null;
	private JScrollPane scrollTabla = null;
	private JTableListado tablaListado = null;
	private JPanel panelBotones = null;  //  @jve:decl-index=0:visual-constraint="760,272"
	private JButton btnCerrar = null;
	private GestorListadoEjemplares gestor;  //  @jve:decl-index=0:
	private JButton btnBuscar = null;
	private JDesktopPane desktopPane;
	private JPanel panelCentral = null;
	private CollapsiblePanel panelFiltros = null;
	private JLabel lblCota = null;
	private JLabel lblTitulo = null;
	private JLabel lblApellido = null;
	private JTextField txtCota = null;
	private JTextField txtTitulo = null;
	private JTextField txtEjemplar = null;
	private JButton btnAceptar = null;
	private int tipo;
	
	
	public VentanaListadoEjemplares(GestorListadoEjemplares gestor, JDesktopPane desktopPane){		
		init(gestor, desktopPane);
		this.desktopPane.add(ventana);
		ventana.setVisible(true);
	}
	
	public VentanaListadoEjemplares(GestorListadoEjemplares gestor, JDesktopPane desktopPane,int t){
		tipo = t;
		init(gestor, desktopPane);		
		getTxtEjemplar().setVisible(false);
		lblApellido.setVisible(false);
		getPanelFiltros().setPreferredSize(new Dimension(1, 100));
		if(tipo == 1){
			ventana.setTitle("Listado de libros");
		}
		this.desktopPane.add(ventana);
		ventana.setVisible(true);
	}
	
	private void init(GestorListadoEjemplares gestor, JDesktopPane desktopPane){
		this.gestor = gestor;
		this.desktopPane = desktopPane;
		
		getVentana();
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
			ventana.setSize(new Dimension(522, 460));
			ventana.setClosable(true);
			ventana.setIconifiable(true);
			ventana.setMaximizable(true);
			ventana.setTitle("Listado de ejemplares");
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
			if(tipo==0){
				tablaListado = new JTableListado(new String[]{"", "Cota" ,"Ejemplar", "Titulo", "Estante", "Sala", "Estado"},
												new int[]{20, 10, 40, 10, 10, 10},
												false,
												true,
												true,
												scrollTabla);
			}
			if(tipo==1){
				tablaListado = new JTableListado(new String[]{"", "Cota" , "Titulo", "Estante", "Sala"},
						new int[]{20, 10, 40, 15},
						false,
						true,
						true,
						scrollTabla);
			}
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
			flowLayout.setHgap(10);
			panelBotones = new JPanel();
			panelBotones.setLayout(flowLayout);
			panelBotones.setBorder(null);
			panelBotones.add(getBtnAceptar(), null);
			panelBotones.add(getBtnBuscar(), null);			
			panelBotones.add(getBtnCerrar(), null);
		}
		return panelBotones;
	}

	/**
	 * This method initializes btnCerrar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnCerrar() {
		if (btnCerrar == null) {
			btnCerrar = new JButton();
			btnCerrar.setPreferredSize(new Dimension(110, 35));
			btnCerrar.setIcon(new ImageIcon(getClass().getResource("/Imagenes/Imagen007.png")));
			btnCerrar.setMnemonic(KeyEvent.VK_C);
			btnCerrar.setText("Cerrar");
			btnCerrar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					ventana.setVisible(false);
					ventana.dispose();
				}
			});
		}
		return btnCerrar;
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
			btnBuscar.setIcon(new ImageIcon(getClass().getResource("/Imagenes/Imagen015.png")));
			btnBuscar.setMnemonic(KeyEvent.VK_B);
			btnBuscar.setText("Buscar");
			btnBuscar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.listarEjemplares();
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
			lblApellido = new JLabel();
			lblApellido.setText("Ejemplar");
			lblApellido.setSize(new Dimension(75, 20));
			lblApellido.setLocation(new Point(5, 80));
			lblApellido.setHorizontalAlignment(SwingConstants.RIGHT);
			lblTitulo = new JLabel();
			lblTitulo.setHorizontalAlignment(SwingConstants.RIGHT);
			lblTitulo.setSize(new Dimension(75, 20));
			lblTitulo.setLocation(new Point(5, 50));
			lblTitulo.setText("Titulo:");
			lblCota = new JLabel();
			lblCota.setHorizontalAlignment(SwingConstants.RIGHT);
			lblCota.setSize(new Dimension(75, 20));
			lblCota.setLocation(new Point(5, 20));
			lblCota.setText("Cota:");
			panelFiltros = new CollapsiblePanel("Filtros");
			panelFiltros.setCollapsed(false);
			panelFiltros.setLayout(null);
			panelFiltros.setPreferredSize(new Dimension(1, 120));
			panelFiltros.setDimensiones(panelFiltros.getPreferredSize());
			panelFiltros.add(lblCota, null);
			panelFiltros.add(lblTitulo, null);
			panelFiltros.add(lblApellido, null);
			panelFiltros.add(getTxtCota(), null);
			panelFiltros.add(getTxtTitulo(), null);
			panelFiltros.add(getTxtEjemplar(), null);
		}
		return panelFiltros;
	}

	/**
	 * This method initializes txtCota	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getTxtCota() {
		if (txtCota == null) {
			txtCota = new JTextField();
			txtCota.setLocation(new Point(85, 18));
			txtCota.setSize(new Dimension(135, 25));
			txtCota.setText("*");
		}
		return txtCota;
	}

	/**
	 * This method initializes txtTitulo	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getTxtTitulo() {
		if (txtTitulo == null) {
			txtTitulo = new JTextField();
			txtTitulo.setLocation(new Point(85, 48));
			txtTitulo.setSize(new Dimension(386, 25));
			txtTitulo.setText("*");
		}
		return txtTitulo;
	}

	/**
	 * This method initializes txtEjemplar	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getTxtEjemplar() {
		if (txtEjemplar == null) {
			txtEjemplar = new JTextField();
			txtEjemplar.setLocation(new Point(85, 78));
			txtEjemplar.setSize(new Dimension(135, 25));
			txtEjemplar.setText("*");
		}
		return txtEjemplar;
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
						int idEjemplar = tablaListado.getModel().getItem(index).getValorInt();
						gestor.seleccionar(idEjemplar);
					}
					ventana.setVisible(false);
					ventana.dispose();
				}
			});
		}
		return btnAceptar;
	}

}
