package hxhthefirsttreasure;

public abstract class muñeco implements java.io.Serializable{

	protected int x;
	protected int y;
	protected String nombre;
	protected int daño;
	protected int vida;
	protected static Board t = new Board();
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getDaño() {
		return daño;
	}
	public void setDaño(int daño) {
		this.daño = daño;
	}
	public int getVida() {
		return vida;
	}
	public void setVida(int vida) {
		this.vida = vida;
	}
	public muñeco(int x, int y, String nombre, int daño, int vida) {
		super();
		this.x = x;
		this.y = y;
		this.nombre = nombre;
		this.daño = daño;
		this.vida = vida;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
