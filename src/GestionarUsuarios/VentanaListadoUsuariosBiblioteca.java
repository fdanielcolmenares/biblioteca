package GestionarUsuarios;

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
import java.awt.Rectangle;
import javax.swing.SwingConstants;
import java.awt.Point;
import javax.swing.JTextField;

public class VentanaListadoUsuariosBiblioteca {

	private JInternalFrame ventana = null;  //  @jve:decl-index=0:visual-constraint="164,-3"
	private JPanel panelVentana = null;
	private JScrollPane scrollTabla = null;
	private JTableListado tablaListado = null;
	private JPanel panelBotones = null;  //  @jve:decl-index=0:visual-constraint="760,272"
	private JButton btnEditar = null;
	private JButton btnNuevo = null;
	private JButton btnEliminar = null;
	private GestorListadoUsuarios gestor;  //  @jve:decl-index=0:
	private JButton btnBuscar = null;
	private JDesktopPane desktopPane;
	private JPanel panelCentral = null;
	private CollapsiblePanel panelFiltros = null;
	private JLabel lblCedula = null;
	private JLabel lblNombre = null;
	private JLabel lblApellido = null;
	private JTextField txtCedula = null;
	private JTextField txtNombres = null;
	private JTextField txtApellidos = null;
	private JButton btnAceptar = null;
	
	
	public VentanaListadoUsuariosBiblioteca(GestorListadoUsuarios gestor, JDesktopPane desktopPane){
		/*this.gestor = gestor;
		this.desktopPane = desktopPane;
		getVentana();
		ventana.setLocation(Centrar.centrarEnComponente(ventana.getSize(), desktopPane.getSize()));
		panelFiltros.setCollapsed(true);*/
		init(gestor, desktopPane, false);
	}
	
	public VentanaListadoUsuariosBiblioteca(GestorListadoUsuarios gestor, JDesktopPane desktopPane, boolean modal){
		init(gestor, desktopPane, modal);
	}
	
	private void init(GestorListadoUsuarios gestor, JDesktopPane desktopPane, boolean catalogo){
		this.gestor = gestor;
		this.desktopPane = desktopPane;
		
		getVentana();
		ventana.setLocation(Centrar.centrarEnComponente(ventana.getSize(), desktopPane.getSize()));
		
		if(catalogo == false){			
			panelFiltros.setCollapsed(true);
			btnAceptar.setVisible(false);
		}
		if(catalogo == true){			
			panelFiltros.setCollapsed(false);
			btnAceptar.setVisible(true);
			btnEditar.setVisible(false);
			btnEliminar.setVisible(false);			
		}
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
			ventana.setTitle("Listado de usuarios de la biblioteca");
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
			tablaListado = new JTableListado(new String[]{"", "Cédula" ,"Nombres", "Apellidos", "Sexo"},
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
			btnEditar.setMnemonic(KeyEvent.VK_E);
			btnEditar.setText("Editar");
			btnEditar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					int index = tablaListado.getFilaSeleccionada();
					if(index >= 0){
						gestor.editarUsuario(tablaListado.getModel().getItem(index).getValorInt());
					}
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
					gestor.crearUsuario();
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
						if(MostrarMensajes.mostrarMensaje("¿Realmente desea eliminar el usuario?", MostrarMensajes.MENSAJE_PREGUNTA)){
							gestor.eliminarUsuario(tablaListado.getModel().getItem(index).getValorInt());
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
					gestor.listarUsuarios();
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
			lblApellido.setText("Apellidos:");
			lblApellido.setBounds(new Rectangle(232, 60, 75, 20));
			lblApellido.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNombre = new JLabel();
			lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNombre.setBounds(new Rectangle(5, 60, 75, 20));
			lblNombre.setText("Nombres:");
			lblCedula = new JLabel();
			lblCedula.setHorizontalAlignment(SwingConstants.RIGHT);
			lblCedula.setBounds(new Rectangle(5, 30, 75, 20));
			lblCedula.setText("Cédula:");
			panelFiltros = new CollapsiblePanel("Filtros");
			panelFiltros.setCollapsed(false);
			panelFiltros.setLayout(null);
			panelFiltros.setPreferredSize(new Dimension(1, 100));
			panelFiltros.setDimensiones(panelFiltros.getPreferredSize());
			panelFiltros.add(lblCedula, null);
			panelFiltros.add(lblNombre, null);
			panelFiltros.add(lblApellido, null);
			panelFiltros.add(getTxtCedula(), null);
			panelFiltros.add(getTxtNombres(), null);
			panelFiltros.add(getTxtApellidos(), null);
		}
		return panelFiltros;
	}

	/**
	 * This method initializes txtCedula	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getTxtCedula() {
		if (txtCedula == null) {
			txtCedula = new JTextField();
			txtCedula.setLocation(new Point(85, 28));
			txtCedula.setSize(new Dimension(135, 25));
			txtCedula.setText("*");
		}
		return txtCedula;
	}

	/**
	 * This method initializes txtNombres	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getTxtNombres() {
		if (txtNombres == null) {
			txtNombres = new JTextField();
			txtNombres.setLocation(new Point(85, 58));
			txtNombres.setSize(new Dimension(135, 25));
			txtNombres.setText("*");
		}
		return txtNombres;
	}

	/**
	 * This method initializes txtApellidos	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getTxtApellidos() {
		if (txtApellidos == null) {
			txtApellidos = new JTextField();
			txtApellidos.setLocation(new Point(311, 58));
			txtApellidos.setSize(new Dimension(135, 25));
			txtApellidos.setText("*");
		}
		return txtApellidos;
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
						int idUsuario = tablaListado.getModel().getItem(index).getValorInt();
						System.out.println("ID Usuario "+idUsuario);
					}
					ventana.setVisible(false);
					ventana.dispose();
				}
			});
		}
		return btnAceptar;
	}

}
