package hxhthefirsttreasure;

public abstract class mu�eco implements java.io.Serializable{

	protected int x;
	protected int y;
	protected String nombre;
	protected int da�o;
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
	public int getDa�o() {
		return da�o;
	}
	public void setDa�o(int da�o) {
		this.da�o = da�o;
	}
	public int getVida() {
		return vida;
	}
	public void setVida(int vida) {
		this.vida = vida;
	}
	public mu�eco(int x, int y, String nombre, int da�o, int vida) {
		super();
		this.x = x;
		this.y = y;
		this.nombre = nombre;
		this.da�o = da�o;
		this.vida = vida;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
