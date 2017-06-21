package GestionarAplicacionRegistro;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Rectangle;
import java.awt.Font;
import java.awt.Point;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDesktopPane;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import Utilitario.Centrar;
import Utilitario.Item;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class VentanaCrearRegistro {

	private JInternalFrame ventana = null;  //  @jve:decl-index=0:visual-constraint="183,21"
	private JPanel panelVentana = null;
	private JPanel panelCentral = null;
	private JLabel lblSexo = null;
	private JLabel lblEdad = null;
	private JLabel lblEstudia = null;
	private JLabel lblTrabaja = null;
	private JLabel lblMision = null;
	private JComboBox cbxSexo = null;
	private JRadioButton chkSiEstudia = null;
	private JRadioButton chkNoEstudia = null;
	private JRadioButton chkSiTrabaja = null;
	private JRadioButton chkNoTrabaja = null;
	private JComboBox cbxMision = null;
	private JDesktopPane desktopPane;
	private JTextField txtEdad = null;
	private JPanel panelInferior = null;
	private JButton btnGuardar = null;
	private JButton btnCerrar = null;
	
	private GestorCrearRegistro gestor;
	
	public VentanaCrearRegistro(GestorCrearRegistro gestor, JDesktopPane desktopPane){
		this.gestor = gestor;
		this.desktopPane = desktopPane;
		getVentana();
		ventana.setLocation(Centrar.centrarEnComponente(ventana.getSize(), this.desktopPane.getSize()));		
	}	
	
	/**
	 * This method initializes ventana	
	 * 	
	 * @return javax.swing.JInternalFrame	
	 */
	public JInternalFrame getVentana() {
		if (ventana == null) {
			ventana = new JInternalFrame();
			ventana.setSize(new Dimension(394, 312));
			ventana.setIconifiable(true);
			ventana.setMaximizable(true);
			ventana.setResizable(true);
			ventana.setClosable(true);
			ventana.setTitle("Crear registro de entrada");
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
			lblMision = new JLabel();
			lblMision.setText("Misión:");
			lblMision.setFont(new Font("SansSerif", Font.PLAIN, 14));
			lblMision.setBounds(new Rectangle(0, 145, 75, 20));
			lblMision.setHorizontalAlignment(SwingConstants.RIGHT);
			lblTrabaja = new JLabel();
			lblTrabaja.setText("¿Trabaja?");
			lblTrabaja.setFont(new Font("SansSerif", Font.PLAIN, 14));
			lblTrabaja.setBounds(new Rectangle(0, 115, 75, 20));
			lblTrabaja.setHorizontalAlignment(SwingConstants.RIGHT);
			lblEstudia = new JLabel();
			lblEstudia.setFont(new Font("SansSerif", Font.PLAIN, 14));
			lblEstudia.setHorizontalAlignment(SwingConstants.RIGHT);
			lblEstudia.setBounds(new Rectangle(0, 85, 75, 20));
			lblEstudia.setText("¿Estudia?");
			lblEdad = new JLabel();
			lblEdad.setText("Edad:");
			lblEdad.setHorizontalAlignment(SwingConstants.RIGHT);
			lblEdad.setBounds(new Rectangle(0, 55, 75, 20));
			lblEdad.setFont(new Font("SansSerif", Font.PLAIN, 14));
			lblSexo = new JLabel();
			lblSexo.setFont(new Font("SansSerif", Font.PLAIN, 14));
			lblSexo.setLocation(new Point(0, 25));
			lblSexo.setSize(new Dimension(75, 20));
			lblSexo.setHorizontalAlignment(SwingConstants.RIGHT);
			lblSexo.setText("Sexo:");
			panelCentral = new JPanel();
			panelCentral.setLayout(null);
			panelCentral.setFont(new Font("SansSerif", Font.PLAIN, 14));
			panelCentral.add(lblSexo, null);
			panelCentral.add(lblEdad, null);
			panelCentral.add(lblEstudia, null);
			panelCentral.add(lblTrabaja, null);
			panelCentral.add(lblMision, null);
			panelCentral.add(getCbxSexo(), null);
			panelCentral.add(getChkSiEstudia(), null);
			panelCentral.add(getChkNoEstudia(), null);
			panelCentral.add(getChkSiTrabaja(), null);
			panelCentral.add(getChkNoTrabaja(), null);
			panelCentral.add(getCbxMision(), null);
			panelCentral.add(getTxtEdad(), null);
			ButtonGroup grupoEstudia = new ButtonGroup();
			grupoEstudia.add(chkSiEstudia);
			grupoEstudia.add(chkNoEstudia);
			ButtonGroup grupoTrabaja = new ButtonGroup();
			grupoTrabaja.add(chkSiTrabaja);
			grupoTrabaja.add(chkNoTrabaja);
		}
		return panelCentral;
	}

	/**
	 * This method initializes cbxSexo	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox getCbxSexo() {
		if (cbxSexo == null) {
			cbxSexo = new JComboBox();
			cbxSexo.setBounds(new Rectangle(85, 23, 173, 25));
			Item[] items = new Item[2];
			items[0] = new Item("Masculino", "M");
			items[1] = new Item("Femenino", "F");
			cbxSexo.setModel(new DefaultComboBoxModel(items));
		}
		return cbxSexo;
	}

	/**
	 * This method initializes chkSiEstudia	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	public JRadioButton getChkSiEstudia() {
		if (chkSiEstudia == null) {
			chkSiEstudia = new JRadioButton();
			chkSiEstudia.setFont(new Font("SansSerif", Font.PLAIN, 14));
			chkSiEstudia.setBounds(new Rectangle(85, 85, 50, 20));
			chkSiEstudia.setSelected(true);
			chkSiEstudia.setText("Si");
		}
		return chkSiEstudia;
	}

	/**
	 * This method initializes chkNoEstudia	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	public JRadioButton getChkNoEstudia() {
		if (chkNoEstudia == null) {
			chkNoEstudia = new JRadioButton();
			chkNoEstudia.setText("No");
			chkNoEstudia.setBounds(new Rectangle(135, 85, 50, 20));
			chkNoEstudia.setFont(new Font("SansSerif", Font.PLAIN, 14));
		}
		return chkNoEstudia;
	}

	/**
	 * This method initializes chkSiTrabaja	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	public JRadioButton getChkSiTrabaja() {
		if (chkSiTrabaja == null) {
			chkSiTrabaja = new JRadioButton();
			chkSiTrabaja.setFont(new Font("SansSerif", Font.PLAIN, 14));
			chkSiTrabaja.setBounds(new Rectangle(85, 115, 50, 20));
			chkSiTrabaja.setText("Si");
		}
		return chkSiTrabaja;
	}

	/**
	 * This method initializes chkNoTrabaja	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	public JRadioButton getChkNoTrabaja() {
		if (chkNoTrabaja == null) {
			chkNoTrabaja = new JRadioButton();
			chkNoTrabaja.setText("No");
			chkNoTrabaja.setBounds(new Rectangle(135, 115, 50, 20));
			chkNoTrabaja.setSelected(true);
			chkNoTrabaja.setFont(new Font("SansSerif", Font.PLAIN, 14));
		}
		return chkNoTrabaja;
	}

	/**
	 * This method initializes cbxMision	
	 * 	
	 * @return javax.swing.JComboBox	
	 */
	public JComboBox getCbxMision() {
		if (cbxMision == null) {
			cbxMision = new JComboBox();
			cbxMision.setBounds(new Rectangle(85, 143, 279, 25));
			cbxMision.setModel(new DefaultComboBoxModel(gestor.getMisiones().toArray()));
		}
		return cbxMision;
	}

	/**
	 * This method initializes txtEdad	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getTxtEdad() {
		if (txtEdad == null) {
			txtEdad = new JTextField();
			txtEdad.setBounds(new Rectangle(88, 53, 97, 25));
			txtEdad.addKeyListener(new java.awt.event.KeyAdapter() {   
				public void keyTyped(java.awt.event.KeyEvent e) {
					try{
						String txt = String.valueOf(e.getKeyChar());
						Integer.parseInt(txt);
						txt = txt + txtEdad.getText();
						if(txt.length()>2){
							e.consume();
						}
					}
					catch(Exception ex){
						e.consume();
					}					
				}				
			});
		}
		return txtEdad;
	}

	/**
	 * This method initializes panelInferior	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelInferior() {
		if (panelInferior == null) {
			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setHgap(15);
			panelInferior = new JPanel();
			panelInferior.setLayout(flowLayout);
			panelInferior.setPreferredSize(new Dimension(1, 45));
			panelInferior.add(getBtnGuardar(), null);
			panelInferior.add(getBtnCerrar(), null);
		}
		return panelInferior;
	}

	/**
	 * This method initializes btnGuardar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton getBtnGuardar() {
		if (btnGuardar == null) {
			btnGuardar = new JButton();
			btnGuardar.setPreferredSize(new Dimension(110, 35));
			btnGuardar.setMnemonic(KeyEvent.VK_G);
			btnGuardar.setIcon(new ImageIcon(getClass().getResource("/Imagenes/Imagen011.png")));
			btnGuardar.setText("Guardar");
			btnGuardar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					gestor.prepararGuardar();
				}
			});
		}
		return btnGuardar;
	}

	/**
	 * This method initializes btnCerrar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getBtnCerrar() {
		if (btnCerrar == null) {
			btnCerrar = new JButton();
			btnCerrar.setText("Cerrar");
			btnCerrar.setIcon(new ImageIcon(getClass().getResource("/Imagenes/Imagen007.png")));
			btnCerrar.setMnemonic(KeyEvent.VK_C);
			btnCerrar.setPreferredSize(new Dimension(110, 35));
			btnCerrar.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					ventana.dispose();
				}
			});
		}
		return btnCerrar;
	}

}
