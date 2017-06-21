package GestionarVentanaPrincipal;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.event.KeyEvent;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import Utilitario.Autenticacion;
import Utilitario.Centrar;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import java.util.Properties;
import java.awt.FlowLayout;
import java.awt.BorderLayout;

public class VentanaPrincipal {

	private JFrame ventana = null;  //  @jve:decl-index=0:visual-constraint="43,30"
	private JMenuBar menuBar = null;
	private JMenu m_conexion = null;
	private GestorVentanaPrincipal gestor;
	private JPanel panelVentana = null;
	private JDesktopPane desktopPanel = null;
	private JPanel panelAccesos = null;
	private JButton btn_acceso1 = null;
	private JMenuItem mi_salir = null;
	private JMenuItem mi_cerrarSesion = null;	
	private JMenuItem mi_iniciarSesion = null;
	private SystemTray tray = null;  //  @jve:decl-index=0:
	private TrayIcon trayIcon = null;  //  @jve:decl-index=0:
	private javax.swing.JMenuItem menuItemRestore;
    private javax.swing.JMenuItem menuItemSalir;
    private javax.swing.JPopupMenu popupContextual;
    private javax.swing.JSeparator separator;
	private JMenuItem mi_minimizarBarra = null;
	private JMenu m_temas = null;
	private JRadioButtonMenuItem mi_tema1 = null;
	private JRadioButtonMenuItem mi_tema2 = null;
	private ButtonGroup grupoTemas = null;  //  @jve:decl-index=0:
	private JButton jButton = null;
	private JMenu m_mantenimiento = null;
	private JMenuItem mi_usuarios = null;
	private JMenu m_usuarios = null;
	private JMenuItem mi_listadoUsuarios = null;
	private JMenuItem mi_crearUsuarios = null;
	private JMenu m_registros = null;
	private JMenuItem mi_listadoRegistros = null;
	private JMenuItem mi_crearRegistro = null;
	private JMenuItem mi_gestionarMisiones = null;
	private JMenuItem mi_gestionarSalas = null;
	private JMenu m_libros = null;
	private JMenuItem mi_agregarLibro = null;
	private JMenuItem mi_editarLibro = null;
	private JMenuItem mi_listadoEjemplares = null;
	private JMenu m_mantenimientos = null;
	private JMenuItem mi_listadoMantenimientos = null;
	private JMenuItem mi_agregarMantenimiento = null;
	private JMenu m_reportes = null;
	private JMenuItem mi_reporteUsuarios = null;
	private JMenuItem mi_reportesAcceso = null;
	private JMenu m_prestamos = null;
	private JMenuItem m_sancion = null;
	private JMenuItem mi_prestamo = null;
	public VentanaPrincipal(Autenticacion a, Properties preferencias){
		getVentana();
		ventana.setLocation(Centrar.centrarEnPantalla(ventana.getSize()));
		gestor = new GestorVentanaPrincipal(this, a, preferencias);
		redimensionarComponentes();
	}
	
	public void redimensionarComponentes(){
		//panelAccesos.setSize(ventana.getWidth(), panelAccesos.getHeight()+10);
        //desktopPanel.setSize(ventana.getWidth(), ventana.getHeight()-panelAccesos.getHeight()-menuBar.getHeight()-35);        
	}
	
	public GestorVentanaPrincipal getGestor(){
		return gestor;
	}
	
	/**
	 * This method initializes ventana	
	 * 	
	 * @return javax.swing.JFrame	
	 */
	public JFrame getVentana() {
		if (ventana == null) {
			ventana = new JFrame();
			ventana.setSize(new Dimension(614, 232));
			ventana.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
			ventana.setLayout(null);
			ventana.setJMenuBar(getMenuBar());
			ventana.setTitle("Sistema de Gestión de Biblioteca");
			ventana.setMaximumSize(new Dimension(21474, 2147483));
			ventana.setContentPane(getPanelVentana());
			ventana.setJMenuBar(getMenuBar());			
			ventana.setMinimumSize(new Dimension(850, 600));
			ventana.addWindowListener(new java.awt.event.WindowAdapter() {
				public void windowClosing(java.awt.event.WindowEvent e) {
					gestor.salir();
				}
			});					ventana.addComponentListener(new java.awt.event.ComponentAdapter() {
				public void componentResized(java.awt.event.ComponentEvent e) {
					redimensionarComponentes();
				}
			});
	getTray();
		}
		return ventana;
	}
	
	public void getTray(){
		java.awt.Image image = new ImageIcon(getClass().getResource("/Imagenes/Imagen018.png")).getImage();
        trayIcon = new TrayIcon(image, "Sistema de Gestión de Biblioteca", null);
        trayIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent e) {
                if (e.isPopupTrigger()) {
                    popupContextual.setLocation(e.getX(), e.getY());
                    popupContextual.setInvoker(popupContextual);
                    popupContextual.setVisible(true);
                }     
            }
        });
        trayIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if(SwingUtilities.isLeftMouseButton(e)){
                    ventana.setVisible(true);                                               
                    ventana.toFront();
                    tray.remove(trayIcon);
                }
            }
        });
        
        popupContextual = new javax.swing.JPopupMenu();
        menuItemRestore = new javax.swing.JMenuItem();
        separator = new javax.swing.JSeparator();
        menuItemSalir = new javax.swing.JMenuItem();

        menuItemRestore.setText("Restaurar");
        menuItemRestore.setIcon(new ImageIcon(getClass().getResource("/Imagenes/Imagen016.png")));
        menuItemRestore.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ventana.setVisible(true);                                               
                ventana.toFront();
                tray.remove(trayIcon);
            }
        });
        popupContextual.add(menuItemRestore);
        popupContextual.add(separator);

        menuItemSalir.setText("Salir");
        menuItemSalir.setIcon(new ImageIcon(getClass().getResource("/Imagenes/Imagen008.png")));
        menuItemSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //System.exit(0);
            	gestor.salir();
            }
        });
        popupContextual.add(menuItemSalir);
	}
	
	public void cambiarEstadoVentana() { 
	   ventana.setState(JFrame.NORMAL);  
	   if (SystemTray.isSupported()) {  
	        ventana.setVisible(false);
	        tray = SystemTray.getSystemTray();
	        trayIcon.setImageAutoSize(true);
	        
	         try {
	            tray.add(trayIcon);
	         }
	         catch (Exception e) {                
	            ventana.setVisible(true);
	         }  
	   }
	}
	
	/**
	 * This method initializes menuBar	
	 * 	
	 * @return javax.swing.JMenuBar	
	 */
	private JMenuBar getMenuBar() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.setPreferredSize(new Dimension(0, 30));
			menuBar.add(getM_libros());
			menuBar.add(getM_prestamos());
			menuBar.add(getM_usuarios());
			menuBar.add(getM_registros());
			menuBar.add(getM_mantenimientos());
			menuBar.add(getM_reportes());
			menuBar.add(getM_mantenimiento());
			menuBar.add(getM_conexion());
		}
		return menuBar;
	}
	/**
	 * This method initializes m_conexion	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getM_conexion() {
		if (m_conexion == null) {
			m_conexion = new JMenu();
			m_conexion.setText("Aplicación");
			m_conexion.setIcon(new ImageIcon(getClass().getResource("/Imagenes/Imagen002.png")));
			m_conexion.setFont(new Font("Dialog", Font.BOLD, 12));
			m_conexion.setMnemonic(KeyEvent.VK_A);
			m_conexion.add(getMi_iniciarSesion());
			m_conexion.add(getMi_cerrarSesion());			
			m_conexion.add(getM_temas());			
			m_conexion.add(getMi_minimizarBarra());			
			m_conexion.add(getMi_salir());
		}
		return m_conexion;
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
			panelVentana.add(getDesktopPanel(), BorderLayout.CENTER);
			panelVentana.add(getPanelAccesos(), BorderLayout.NORTH);
		}
		return panelVentana;
	}

	/**
	 * This method initializes desktopPanel	
	 * 	
	 * @return javax.swing.JDesktopPane	
	 */
	public JDesktopPane getDesktopPanel() {
		if (desktopPanel == null) {
			desktopPanel = new JDesktopPane();
			desktopPanel.setBackground(Color.white);
		}
		return desktopPanel;
	}

	/**
	 * This method initializes panelAccesos	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelAccesos() {
		if (panelAccesos == null) {
			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			flowLayout.setVgap(0);
			panelAccesos = new JPanel();
			panelAccesos.setLayout(flowLayout);
			panelAccesos.add(getBtn_acceso1(), null);
			panelAccesos.add(getJButton(), null);
		}
		return panelAccesos;
	}

	/**
	 * This method initializes btn_acceso1	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton getBtn_acceso1() {
		if (btn_acceso1 == null) {
			btn_acceso1 = new JButton();
			btn_acceso1.setEnabled(false);
			btn_acceso1.setPreferredSize(new Dimension(70, 70));
			btn_acceso1.setIcon(new ImageIcon(getClass().getResource("/Imagenes/Imagen003.png")));
			btn_acceso1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {					
					gestor.cerrarSesion();
				}
			});
		}
		return btn_acceso1;
	}

	/**
	 * This method initializes mi_salir	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMi_salir() {
		if (mi_salir == null) {
			mi_salir = new JMenuItem();
			mi_salir.setText("Salir");
			mi_salir.setMnemonic(KeyEvent.VK_S);
			mi_salir.setIcon(new ImageIcon(getClass().getResource("/Imagenes/Imagen008.png")));
			mi_salir.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.salir();
				}
			});
		}
		return mi_salir;
	}

	/**
	 * This method initializes mi_cerrarSesion	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	public JMenuItem getMi_cerrarSesion() {
		if (mi_cerrarSesion == null) {
			mi_cerrarSesion = new JMenuItem();
			mi_cerrarSesion.setMnemonic(KeyEvent.VK_C);
			mi_cerrarSesion.setIcon(new ImageIcon(getClass().getResource("/Imagenes/Imagen004.png")));
			mi_cerrarSesion.setText("Cerrar sesión");
			mi_cerrarSesion.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.cerrarSesion();
				}
			});
		}
		return mi_cerrarSesion;
	}

	/**
	 * This method initializes mi_iniciarSesion1	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	public JMenuItem getMi_iniciarSesion() {
		if (mi_iniciarSesion == null) {
			mi_iniciarSesion = new JMenuItem();
			mi_iniciarSesion.setIcon(new ImageIcon(getClass().getResource("/Imagenes/Imagen012.png")));
			mi_iniciarSesion.setText("Iniciar sesión");
			mi_iniciarSesion.setMnemonic(KeyEvent.VK_I);
			mi_iniciarSesion.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.mostrarVentanaUsuario();
				}
			});
		}
		return mi_iniciarSesion;
	}

	/**
	 * This method initializes mi_minimizarBarra	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMi_minimizarBarra() {
		if (mi_minimizarBarra == null) {
			mi_minimizarBarra = new JMenuItem();
			mi_minimizarBarra.setIcon(new ImageIcon(getClass().getResource("/Imagenes/Imagen016.png")));
			mi_minimizarBarra.setText("Ocultar ventana");
			mi_minimizarBarra.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					cambiarEstadoVentana();
					trayIcon.displayMessage("Biblioteca", "El programa se esta ejecutando...", TrayIcon.MessageType.INFO);
				}
			});
		}
		return mi_minimizarBarra;
	}

	/**
	 * This method initializes m_temas	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getM_temas() {
		if (m_temas == null) {
			m_temas = new JMenu();
			m_temas.setName("");
			m_temas.setMnemonic(KeyEvent.VK_T);
			m_temas.setText("Cambiar tema");
			m_temas.setIcon(new ImageIcon(getClass().getResource("/Imagenes/Imagen019.png")));
			m_temas.add(getMi_tema1());
			m_temas.add(getMi_tema2());
			getGrupoTemas();
		}
		return m_temas;
	}
	
	private ButtonGroup getGrupoTemas(){
		if(grupoTemas == null){
			grupoTemas = new ButtonGroup();
			grupoTemas.add(getMi_tema1());
			grupoTemas.add(getMi_tema2());
		}
		
		return grupoTemas;
	}

	/**
	 * This method initializes mi_tema1	
	 * 	
	 * @return javax.swing.JRadioButtonMenuItem	
	 */
	public JRadioButtonMenuItem getMi_tema1() {
		if (mi_tema1 == null) {
			mi_tema1 = new JRadioButtonMenuItem();
			mi_tema1.setText("Nimbus");
			mi_tema1.setSelected(true);
			mi_tema1.setMnemonic(KeyEvent.VK_UNDEFINED);			
			mi_tema1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.cambiarTema("Nimbus");
				}
			});
		}
		return mi_tema1;
	}

	/**
	 * This method initializes mi_tema2	
	 * 	
	 * @return javax.swing.JRadioButtonMenuItem	
	 */
	public JRadioButtonMenuItem getMi_tema2() {
		if (mi_tema2 == null) {
			mi_tema2 = new JRadioButtonMenuItem();
			mi_tema2.setText("Windows");
			mi_tema2.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.cambiarTema("Windows");
				}
			});
			
		}
		return mi_tema2;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setText("");
			jButton.setIcon(new ImageIcon(getClass().getResource("/Imagenes/Imagen032.png")));
			jButton.setPreferredSize(new Dimension(70, 70));
			jButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.probarBoton();
				}
			});
		}
		return jButton;
	}

	/**
	 * This method initializes m_mantenimiento	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	public JMenu getM_mantenimiento() {
		if (m_mantenimiento == null) {
			m_mantenimiento = new JMenu();
			m_mantenimiento.setText("Sistema");
			m_mantenimiento.setFont(new Font("SansSerif", Font.BOLD, 12));
			m_mantenimiento.setIcon(new ImageIcon(getClass().getResource("/Imagenes/Imagen025.png")));
			m_mantenimiento.setMnemonic(KeyEvent.VK_S);
			m_mantenimiento.setEnabled(false);
			m_mantenimiento.add(getMi_usuarios());
			m_mantenimiento.add(getMi_gestionarMisiones());
			m_mantenimiento.add(getMi_gestionarSalas());
		}
		return m_mantenimiento;
	}

	/**
	 * This method initializes mi_usuarios	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMi_usuarios() {
		if (mi_usuarios == null) {
			mi_usuarios = new JMenuItem();
			mi_usuarios.setText("Gestionar usuarios del sistema");			
			mi_usuarios.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.gestionarUsuariosSistema();
				}
			});
		}
		return mi_usuarios;
	}

	/**
	 * This method initializes m_usuarios	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	public JMenu getM_usuarios() {
		if (m_usuarios == null) {
			m_usuarios = new JMenu();
			m_usuarios.setText("Usuarios");
			m_usuarios.setIcon(new ImageIcon(getClass().getResource("/Imagenes/Imagen021.png")));
			m_usuarios.setMnemonic(KeyEvent.VK_U);
			m_usuarios.setFont(new Font("SansSerif", Font.BOLD, 12));
			m_usuarios.setEnabled(false);
			m_usuarios.add(getMi_listadoUsuarios());
			m_usuarios.add(getMi_crearUsuarios());
		}
		return m_usuarios;
	}

	/**
	 * This method initializes mi_listadoUsuarios	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMi_listadoUsuarios() {
		if (mi_listadoUsuarios == null) {
			mi_listadoUsuarios = new JMenuItem();
			mi_listadoUsuarios.setText("Gestionar usuarios");
			mi_listadoUsuarios.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.gestionarUsuariosBiblioteca();
				}
			});
		}
		return mi_listadoUsuarios;
	}

	/**
	 * This method initializes mi_crearUsuarios	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMi_crearUsuarios() {
		if (mi_crearUsuarios == null) {
			mi_crearUsuarios = new JMenuItem();
			mi_crearUsuarios.setText("Crear usuario");
			mi_crearUsuarios.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.crearUsuarioBiblioteca();
				}
			});
		}
		return mi_crearUsuarios;
	}

	/**
	 * This method initializes m_registros	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	public JMenu getM_registros() {
		if (m_registros == null) {
			m_registros = new JMenu();
			m_registros.setText("Reg. de entrada");
			m_registros.setMnemonic(KeyEvent.VK_R);
			m_registros.setFont(new Font("SansSerif", Font.BOLD, 12));
			m_registros.setEnabled(false);
			m_registros.setIcon(new ImageIcon(getClass().getResource("/Imagenes/Imagen030.png")));
			m_registros.setToolTipText("Registros de entrada");
			m_registros.add(getMi_listadoRegistros());
			m_registros.add(getMi_crearRegistro());
		}
		return m_registros;
	}

	/**
	 * This method initializes mi_listadoRegistros	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMi_listadoRegistros() {
		if (mi_listadoRegistros == null) {
			mi_listadoRegistros = new JMenuItem();
			mi_listadoRegistros.setText("Listar registros de entrada");
			mi_listadoRegistros.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.gestionarRegistrosEntrada();
				}
			});
		}
		return mi_listadoRegistros;
	}

	/**
	 * This method initializes mi_crearRegistro	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMi_crearRegistro() {
		if (mi_crearRegistro == null) {
			mi_crearRegistro = new JMenuItem();
			mi_crearRegistro.setText("Crear registro de entrada");
			mi_crearRegistro.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.crearRegistrosEntrada();
				}
			});
		}
		return mi_crearRegistro;
	}

	/**
	 * This method initializes mi_gestionarMisiones	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMi_gestionarMisiones() {
		if (mi_gestionarMisiones == null) {
			mi_gestionarMisiones = new JMenuItem();
			mi_gestionarMisiones.setText("Gestionar misiones");
			mi_gestionarMisiones.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.gestionarMisiones();
				}
			});
		}
		return mi_gestionarMisiones;
	}

	/**
	 * This method initializes mi_gestionarSalas	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMi_gestionarSalas() {
		if (mi_gestionarSalas == null) {
			mi_gestionarSalas = new JMenuItem();
			mi_gestionarSalas.setText("Gestionar salas");
			mi_gestionarSalas.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.gestionarSalas();
				}
			});
		}
		return mi_gestionarSalas;
	}

	/**
	 * This method initializes m_libros	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	public JMenu getM_libros() {
		if (m_libros == null) {
			m_libros = new JMenu();
			m_libros.setText("Libros");
			m_libros.setIcon(new ImageIcon(getClass().getResource("/ima/libros.gif")));
			m_libros.setMnemonic(KeyEvent.VK_L);
			m_libros.setEnabled(false);
			m_libros.setFont(new Font("SansSerif", Font.BOLD, 12));
			m_libros.add(getMi_agregarLibro());
			m_libros.add(getMi_editarLibro());
			m_libros.add(getMi_listadoEjemplares());
		}
		return m_libros;
	}

	/**
	 * This method initializes mi_agregarLibro	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMi_agregarLibro() {
		if (mi_agregarLibro == null) {
			mi_agregarLibro = new JMenuItem();
			mi_agregarLibro.setText("Agregar libro");
			mi_agregarLibro.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.agregarLibro();
				}
			});
		}
		return mi_agregarLibro;
	}

	/**
	 * This method initializes mi_editarLibro	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMi_editarLibro() {
		if (mi_editarLibro == null) {
			mi_editarLibro = new JMenuItem();
			mi_editarLibro.setText("Listado de libros");
			mi_editarLibro.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.editarLibro();
				}
			});
		}
		return mi_editarLibro;
	}

	/**
	 * This method initializes mi_listadoEjemplares	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMi_listadoEjemplares() {
		if (mi_listadoEjemplares == null) {
			mi_listadoEjemplares = new JMenuItem();
			mi_listadoEjemplares.setText("Listado de ejemplares");
			mi_listadoEjemplares.setVisible(true);
			mi_listadoEjemplares.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.listarEjemplares();
				}
			});
		}
		return mi_listadoEjemplares;
	}

	/**
	 * This method initializes m_mantenimientos	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	public JMenu getM_mantenimientos() {
		if (m_mantenimientos == null) {
			m_mantenimientos = new JMenu();
			m_mantenimientos.setMnemonic(KeyEvent.VK_M);
			m_mantenimientos.setFont(new Font("SansSerif", Font.BOLD, 12));
			m_mantenimientos.setText("Mantenimientos");
			m_mantenimientos.setEnabled(false);
			m_mantenimientos.setIcon(new ImageIcon(getClass().getResource("/Imagenes/imagen029.png")));
			m_mantenimientos.add(getMi_listadoMantenimientos());
			m_mantenimientos.add(getMi_agregarMantenimiento());
		}
		return m_mantenimientos;
	}

	/**
	 * This method initializes mi_listadoMantenimientos	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMi_listadoMantenimientos() {
		if (mi_listadoMantenimientos == null) {
			mi_listadoMantenimientos = new JMenuItem();
			mi_listadoMantenimientos.setText("Listado de mantenimientos");
			mi_listadoMantenimientos.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.listarMantenimientos();
				}
			});
		}
		return mi_listadoMantenimientos;
	}

	/**
	 * This method initializes mi_agregarMantenimiento	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMi_agregarMantenimiento() {
		if (mi_agregarMantenimiento == null) {
			mi_agregarMantenimiento = new JMenuItem();
			mi_agregarMantenimiento.setText("Agregar mantenimiento");
			mi_agregarMantenimiento.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.crearMantenimiento();
				}
			});
		}
		return mi_agregarMantenimiento;
	}

	/**
	 * This method initializes m_reportes	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	public JMenu getM_reportes() {
		if (m_reportes == null) {
			m_reportes = new JMenu();
			m_reportes.setText("Reportes");
			m_reportes.setFont(new Font("SansSerif", Font.BOLD, 12));
			m_reportes.setIcon(new ImageIcon(getClass().getResource("/Imagenes/Imagen026.png")));
			m_reportes.setMnemonic(KeyEvent.VK_T);
			m_reportes.setEnabled(false);
			m_reportes.add(getMi_reporteUsuarios());
			m_reportes.add(getMi_reportesAcceso());
		}
		return m_reportes;
	}

	/**
	 * This method initializes mi_reporteUsuarios	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMi_reporteUsuarios() {
		if (mi_reporteUsuarios == null) {
			mi_reporteUsuarios = new JMenuItem();
			mi_reporteUsuarios.setText("Reportes de usuarios");
			mi_reporteUsuarios.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.reportesUsuarios();
				}
			});
		}
		return mi_reporteUsuarios;
	}

	/**
	 * This method initializes mi_reportesAcceso	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMi_reportesAcceso() {
		if (mi_reportesAcceso == null) {
			mi_reportesAcceso = new JMenuItem();
			mi_reportesAcceso.setText("Reportes de registros de entrada");
			mi_reportesAcceso.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.reportesRegistros();
				}
			});
		}
		return mi_reportesAcceso;
	}

	/**
	 * This method initializes m_prestamos	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	public JMenu getM_prestamos() {
		if (m_prestamos == null) {
			m_prestamos = new JMenu();
			m_prestamos.setText("Préstamos");
			m_prestamos.setMnemonic(KeyEvent.VK_P);
			m_prestamos.setEnabled(false);
			m_prestamos.setIcon(new ImageIcon(getClass().getResource("/Imagenes/Imagen031.png")));
			m_prestamos.setFont(new Font("SansSerif", Font.BOLD, 12));
			m_prestamos.add(getMi_prestamo());
			m_prestamos.add(getM_sancion());
		}
		return m_prestamos;
	}

	/**
	 * This method initializes m_sancion	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getM_sancion() {
		if (m_sancion == null) {
			m_sancion = new JMenuItem();
			m_sancion.setText("Gestionar sanciones");
			m_sancion.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.sanciones();
				}
			});
		}
		return m_sancion;
	}

	/**
	 * This method initializes mi_prestamo	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getMi_prestamo() {
		if (mi_prestamo == null) {
			mi_prestamo = new JMenuItem();
			mi_prestamo.setText("Gestionar préstamos");
			mi_prestamo.setActionCommand("Prestamos");
			mi_prestamo.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.prestamos();
				}
			});
		}
		return mi_prestamo;
	}
}
