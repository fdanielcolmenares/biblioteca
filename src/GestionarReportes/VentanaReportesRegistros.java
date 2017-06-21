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
import java.util.Date;
import java.util.HashMap;

import com.toedter.calendar.JDateChooser;

public class VentanaReportesRegistros {

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
	private JLabel lblFechaDesde = null;
	private JLabel lblFechaFin = null;
	private JDateChooser txtFechaInicio = null;
	private JDateChooser txtFechaFin = null;
	private Autenticacion autenticacion;
	
	
	public VentanaReportesRegistros(Autenticacion autenticacion, JDesktopPane desktopPane){
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
	public JInternalFrame getVentana() {
		if (ventana == null) {
			ventana = new JInternalFrame();
			ventana.setSize(new Dimension(475, 276));
			ventana.setTitle("Reportes de registros de acceso");
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
			lblFechaFin = new JLabel();
			lblFechaFin.setText("hasta:");
			lblFechaFin.setSize(new Dimension(45, 20));
			lblFechaFin.setLocation(new Point(258, 61));
			lblFechaDesde = new JLabel();
			lblFechaDesde.setText("Desde la fecha:");
			lblFechaDesde.setSize(new Dimension(101, 20));
			lblFechaDesde.setLocation(new Point(10, 61));
			lblTipoReporte = new JLabel();
			lblTipoReporte.setText("Tipo de reporte:");
			lblTipoReporte.setSize(new Dimension(100, 20));
			lblTipoReporte.setLocation(new Point(10, 16));
			panelFiltros = new JPanel();
			panelFiltros.setLayout(null);
			panelFiltros.setBorder(null);
			panelFiltros.add(lblTipoReporte, null);
			panelFiltros.add(getCbxTipoReporte(), null);
			panelFiltros.add(lblFechaDesde, null);
			panelFiltros.add(lblFechaFin, null);
			panelFiltros.add(getTxtFechaInicio(), null);
			panelFiltros.add(getTxtFechaFin(), null);
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
			cbxTipoReporte.setBounds(new Rectangle(120, 12, 206, 26));
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
		items[1].setNombre("Listado de registros de acceso");
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
		ReportesDAO consultas = new ReportesDAO();
		
		if(tipoReporte == REPORTE_LISTADO_GENERAL){			
			parametros.put("query", consultas.getQueryRegistros(txtFechaInicio.getDate(), txtFechaFin.getDate()));
			
			reporte.setArchivo("Archivos/Reportes/ListadoRegistrosAcceso.jasper");
		}
		
		conexion.conectar();
		reporte.setParametros(parametros);		
		reporte.setConexion(conexion.getConexion());
		
		reporte.start();
	}

	/**
	 * This method initializes txtFechaInicio	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JDateChooser getTxtFechaInicio() {
		if (txtFechaInicio == null) {
			txtFechaInicio = new JDateChooser();
			txtFechaInicio.setLocation(new Point(122, 58));
			txtFechaInicio.setSize(new Dimension(127, 25));
			txtFechaInicio.setDate(new Date());
		}
		return txtFechaInicio;
	}

	/**
	 * This method initializes txtFechaFin	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JDateChooser getTxtFechaFin() {
		if (txtFechaFin == null) {
			txtFechaFin = new JDateChooser();
			txtFechaFin.setLocation(new Point(307, 58));
			txtFechaFin.setSize(new Dimension(127, 25));
			txtFechaFin.setDate(new Date());
		}
		return txtFechaFin;
	}

}
