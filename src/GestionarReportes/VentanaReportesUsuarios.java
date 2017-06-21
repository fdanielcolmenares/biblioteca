package GestionarReportes;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.JComboBox;
import ConexionBD.Conexion;
import Consultas.ReportesDAO;
import Utilitario.Autenticacion;
import Utilitario.Centrar;
import Utilitario.Item;
import Utilitario.MostrarMensajes;
import java.awt.Point;
import java.util.HashMap;

public class VentanaReportesUsuarios {

	private JInternalFrame ventana = null;  //  @jve:decl-index=0:visual-constraint="44,57"
	private JPanel panelVentana = null;
	private JPanel panelFiltros = null;
	private JPanel panelInferior = null;
	private JButton btnAceptar = null;
	private JButton btnCancelar = null;
	private JLabel jLabel = null;
	private JLabel lblTipoReporte = null;
	private JComboBox cbxTipoReporte = null;
	
	private int REPORTE_VACIO = 1;
	private int REPORTE_LISTADO_GENERAL = 2;
	
	private Autenticacion autenticacion;
	
	
	public VentanaReportesUsuarios(Autenticacion autenticacion, JDesktopPane desktopPane){
		this.autenticacion = autenticacion;
		
		getVentana();
		ventana.setLocation(Centrar.centrarEnComponente(ventana.getSize(), desktopPane.getSize()));
		
		desktopPane.add(ventana);
		ventana.setVisible(true);		
	}
	
	/**
	 * This method initializes ventana	
	 * 	
	 * @return javax.swing.JInternalFrame	
	 */
	private JInternalFrame getVentana() {
		if (ventana == null) {
			ventana = new JInternalFrame();
			ventana.setSize(new Dimension(448, 229));
			ventana.setTitle("Reportes de usuarios");
			ventana.setIconifiable(true);
			ventana.setResizable(true);
			ventana.setClosable(true);
			ventana.setMaximizable(true);
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
			jLabel = new JLabel();
			jLabel.setText("");
			panelVentana = new JPanel();
			panelVentana.setLayout(new BorderLayout());
			panelVentana.setBorder(BorderFactory.createEmptyBorder(10, 10, 5, 10));
			panelVentana.add(getPanelFiltros(), BorderLayout.CENTER);
			panelVentana.add(getPanelInferior(), BorderLayout.SOUTH);
			panelVentana.add(jLabel, BorderLayout.NORTH);
		}
		return panelVentana;
	}

	/**
	 * This method initializes panelFiltros	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelFiltros() {
		if (panelFiltros == null) {
			lblTipoReporte = new JLabel();
			lblTipoReporte.setText("Tipo de reporte:");
			lblTipoReporte.setSize(new Dimension(100, 20));
			lblTipoReporte.setLocation(new Point(2, 16));
			panelFiltros = new JPanel();
			panelFiltros.setLayout(null);
			panelFiltros.setBorder(null);
			panelFiltros.add(lblTipoReporte, null);
			panelFiltros.add(getCbxTipoReporte(), null);
		}
		return panelFiltros;
	}

	/**
	 * This method initializes panelInferior	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelInferior() {
		if (panelInferior == null) {
			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setHgap(20);
			panelInferior = new JPanel();
			panelInferior.setLayout(flowLayout);
			panelInferior.setBorder(null);
			panelInferior.add(getBtnAceptar(), null);
			panelInferior.add(getBtnCancelar(), null);
		}
		return panelInferior;
	}

	/**
	 * This method initializes btnAceptar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnAceptar() {
		if (btnAceptar == null) {
			btnAceptar = new JButton();
			btnAceptar.setPreferredSize(new Dimension(115, 35));
			btnAceptar.setIcon(new ImageIcon(getClass().getResource("/Imagenes/Imagen017.png")));
			btnAceptar.setText("Aceptar");
			btnAceptar.setMnemonic(KeyEvent.VK_A);
			btnAceptar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					generarReporte();
				}
			});
		}
		return btnAceptar;
	}

	/**
	 * This method initializes btnCancelar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnCancelar() {
		if (btnCancelar == null) {
			btnCancelar = new JButton();
			btnCancelar.setPreferredSize(new Dimension(115, 35));
			btnCancelar.setMnemonic(KeyEvent.VK_C);
			btnCancelar.setIcon(new ImageIcon(getClass().getResource("/Imagenes/Imagen007.png")));
			btnCancelar.setText("Cancelar");
			btnCancelar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					ventana.setVisible(false);
					ventana.dispose();
				}
			});
		}
		return btnCancelar;
	}

	/**
	 * This method initializes cbxTipoReporte	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	private JComboBox getCbxTipoReporte() {
		if (cbxTipoReporte == null) {
			cbxTipoReporte = new JComboBox();
			cbxTipoReporte.setBounds(new Rectangle(106, 12, 295, 26));
			cbxTipoReporte.setModel(new DefaultComboBoxModel(getTiposReporte()));
		}
		return cbxTipoReporte;
	}
	
	public Item[] getTiposReporte(){
		Item items[] = new Item[2];
		
		items[0] = new Item();
		items[0].setNombre("Seleccione un tipo de reporte");
		items[0].setValorInt(REPORTE_VACIO);
		
		items[1] = new Item();
		items[1].setNombre("Listado general de usuarios");
		items[1].setValorInt(REPORTE_LISTADO_GENERAL);
		
		
		return items;
	}
	
	public void generarReporte(){
		int tipoReporte = ((Item)getCbxTipoReporte().getSelectedItem()).getValorInt();
		if(tipoReporte == REPORTE_VACIO){
			MostrarMensajes.mostrarMensaje("Seleccione un tipo de reporte", MostrarMensajes.MENSAJE_ERROR);
			return ;
		}
		
		Reporte reporte = new Reporte();		
		HashMap<String, Object> parametros = new HashMap<String, Object>();
		Conexion conexion = new Conexion(autenticacion);
		
		if(tipoReporte == REPORTE_LISTADO_GENERAL){
			ReportesDAO consultas = new ReportesDAO();
			parametros.put("query", consultas.getQueryListadoUsuarios());
			
			reporte.setArchivo("Archivos/Reportes/ListadoUsuarios.jasper");
		}
		
		conexion.conectar();
		reporte.setParametros(parametros);		
		reporte.setConexion(conexion.getConexion());
		
		reporte.start();
	}

}
