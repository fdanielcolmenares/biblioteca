package Utilitario;

public class Item{
	private String nombre;
	private String valorString;	
	private boolean valorBoolean;
	private int valorInt;
	
	public Item(){		
	}

	public Item(String nombre, String valor){
		this.nombre = nombre;
		this.valorString = valor;
	}
	
	public Item(String nombre, int valor){
		this.nombre = nombre;
		this.valorInt = valor;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getValorString() {
		return valorString;
	}
	
	public void setValorString(String valorString) {
		this.valorString = valorString;
	}
	
	public boolean getValorBoolean() {
		return valorBoolean;
	}
	
	public void setValorBoolean(boolean valorBoolean) {
		this.valorBoolean = valorBoolean;
	}
	
	public int getValorInt() {
		return valorInt;
	}
	
	public void setValorInt(int valorInt) {
		this.valorInt = valorInt;
	}
	
	public String toString(){
		return nombre;
	}
}
