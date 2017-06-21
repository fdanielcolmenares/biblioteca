import javax.swing.JDialog;
import javax.swing.UIManager;
import GestionarInicioAplicacion.VentanaInicioAplicacion;
import Utilitario.VariablesGlobales;
import com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel;


public class Principal {
	
	public static void main(String[] args) {
		if(args.length == 1){
			try{
				if(args[0].compareTo("-debug")==0){				
					VariablesGlobales.DEBUG = true;
				}
			}
			catch(Exception e){				
			}
		}
		
		Principal principal = new Principal();
		principal.cambiarEstiloSwing();
		
		new VentanaInicioAplicacion();
	}
	
	public void cambiarEstiloSwing(){
		JDialog.setDefaultLookAndFeelDecorated(true);
		try{
			//WindowsLookAndFeel laf = new WindowsLookAndFeel();
			NimbusLookAndFeel laf = new NimbusLookAndFeel();			
			UIManager.setLookAndFeel(laf);
			/*MetalLookAndFeel]
			NimbusLookAndFeel]
			MotifLookAndFeel]
			WindowsLookAndFeel]
			WindowsClassicLookAndFeel]*/
		}
		catch(Exception e){			
		}
	}

}
