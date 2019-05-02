package hxhthefirsttreasure;

public class enemigo
extends muñeco
{
	private boolean vivoe = true;

	
	
	public enemigo(int x, int y, String nombre, int daño, int vida) {
		super(x, y, nombre, daño, vida);
		// TODO Auto-generated constructor stub
	}

	public boolean isVivoe() {
		return vivoe;
	}

	public void setVivoe(boolean vivoe) {
		this.vivoe = vivoe;
	}

	public int[][] movimientonaranja(muñeco obj,int[][]array) {

		int rnd = (int) (Math.random() * 5) + 1;;

		switch (rnd) {

		case 1: /* El case 1 consistira en que el enemic puji una casella */
			if (array[x - 1][y] == 0) {
				array[x][y] = 0;
				array[x - 1][y] = 6;
				x--;
			}
			
			break;
		case 2: /* El numero 2 baixara una casella l'enemic */
			if (array[x + 1][y] == 0) {
				array[x][y] = 0;
				array[x + 1][y] = 6;
				x++;
			}
		
			break;
		case 3: /* El cas 3 es moura cap a l'esquerra */
			if (array[x][y - 1] == 0) {
				array[x][y] = 0;
				array[x][y - 1] = 6;
				y--;
			}
			
			break;
		case 4: /* En el cas 4 es moura cap a la dreta */
			if (array[x][y + 1] == 0) {
				array[x][y] = 0;
				array[x][y + 1] = 6;

				y++;

			}
		
			break;
		case 5:
			array=ataquemigo(array,obj);
			
			break;
		}
		return array;
	}

	public int [][] ataquemigo(int [][] array,muñeco obj) {
		

		if (array[x - 1][y - 1] == 0 || array[x - 1][y - 1] == 4) {
			if (array[x - 1][y - 1] == 4) {
				obj.setVida(obj.vida--);
			}

			else if (array[x - 1][y - 1] == 0) {
				array[x - 1][y - 1] = 10;
			}
			t.draw(array);
		}
		if (array[x + 1][y - 1] == 0 || array[x + 1][y - 1] == 4) {
			if (array[x + 1][y - 1] == 4) {
				obj.setVida(obj.vida--);

			} else if (array[x + 1][y - 1] == 0) {

				array[x + 1][y - 1] = 10;
			}

		}

		if (array[x][y - 1] == 0 || array[x][y - 1] == 4) {
			if (array[x][y - 1] == 4) {
				obj.setVida(obj.vida--);
			} else if (array[x][y - 1] == 0) {
				array[x][y - 1] = 10;
			}

		}
		if (array[x - 1][y - 1] == 10) {
			array[x - 1][y - 1] = 0;
		}
		if (array[x + 1][y - 1] == 10) {
			array[x + 1][y - 1] = 0;
		}
		if (array[x][y - 1] == 10) {
			array[x][y - 1] = 0;
		}
		
		return array;
	}
	
	
	

}


