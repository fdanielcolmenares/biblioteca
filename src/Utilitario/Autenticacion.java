package Utilitario;

import Entidades.Usuarios;

public class Autenticacion {
	private String usuarioBD;
	private String claveBD;
	private String ipBD;
	private Usuarios usuario;
	
	public Autenticacion(){
		
	}
	
	public String getUsuarioBD() {
		return usuarioBD;
	}
	public void setUsuarioBD(String usuarioBD) {
		this.usuarioBD = usuarioBD;
	}
	public String getClaveBD() {
		return claveBD;
	}
	public void setClaveBD(String claveBD) {
		this.claveBD = claveBD;
	}
	
	public String getIpBD() {
		return ipBD;
	}

	public void setIpBD(String ipBD) {
		this.ipBD = ipBD;
	}

	public Usuarios getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}	
}
