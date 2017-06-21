package GestionarInicioAplicacion;

import java.util.Properties;
import GestionarVentanaPrincipal.VentanaPrincipal;
import Utilitario.Autenticacion;
import Utilitario.MostrarMensajes;
import Utilitario.UtilArchivos;
import Utilitario.VariablesGlobales;

public class GestorInicioAplicacion {
	private VentanaInicioAplicacion ventana;
	
	public GestorInicioAplicacion(VentanaInicioAplicacion ventana) {
		this.ventana = ventana;
	}
		
	public void iniciarCargaAplicacion(){		
        int avance = 100 / 3;
        boolean aplicacionValida = true;
        Properties propiedades = null;
        Properties preferencias = null;
        
        try{
	        ventana.getProgressBar().setValue(0);
	        ventana.getProgressBar().setString("Validando instalación - 0%");	        
	        propiedades = buscarPropiedades();        
	        Thread.sleep(200);	        
	        
	        ventana.getProgressBar().setString("Validando servidor - " + (avance) + "%");
            //Busco la direccion del servidor
	        ventana.getProgressBar().setValue(avance);
            Thread.sleep(300);
            
            ventana.getProgressBar().setString("Buscando ajustes - 0%");
	        ventana.getProgressBar().setValue(avance*2);
	        preferencias = buscarPreferencias();	        
	        Thread.sleep(200);
	        
            for (int i = ventana.getProgressBar().getValue(); i <= 100; i++) {
            	ventana.getProgressBar().setString("Iniciando - " + i + "%");
            	ventana.getProgressBar().setValue(i);
                Thread.sleep(30);
            }
        }
        catch(Exception e){
        	if(VariablesGlobales.DEBUG){
        		System.out.println("Error: GestorInicioAplicacion.iniciarCargaAplicacion() "+e.getMessage());
        		e.printStackTrace();
        		aplicacionValida = false;
        		
        		MostrarMensajes.mostrarMensaje(e.getMessage(), MostrarMensajes.MENSAJE_ERROR);
        	}
        }
        
        if(aplicacionValida){
        	Autenticacion a = new Autenticacion();
        	a.setUsuarioBD(propiedades.getProperty("usuario"));        	
        	a.setClaveBD(propiedades.getProperty("clave"));        	
        	a.setIpBD(propiedades.getProperty("servidor"));
        	a.setUsuario(null);
        	ventana.getVentana().setVisible(false);
        	new VentanaPrincipal(a, preferencias);        	
        	
        }else{
        	System.exit(0);
        }
	}
	
	public Properties buscarPropiedades() throws Exception{
		Properties p = UtilArchivos.leerXMLPropiedades(UtilArchivos.ARCHIVO_CONFIGURACION);
		
		/*if(!p.containsKey("usuario") || !p.containsKey("clave")){
			throw new Exception("No se pudo leer el archivo de propiedades");
		}*/		
		
		return p;
	}
	
	public Properties buscarPreferencias() throws Exception{
		Properties p = UtilArchivos.leerXMLPropiedades(UtilArchivos.ARCHIVO_PREFERENCIAS);	
		
		return p;
	}
}
