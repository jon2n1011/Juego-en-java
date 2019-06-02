package hxhthefirsttreasure;

public class principal extends muñeco implements java.io.Serializable {

	private boolean pausa = false;
	
	
	public principal(int x, int y, String nombre, int daño, int vida) {
		super(x, y, nombre, daño, vida);
		// TODO Auto-generated constructor stub
	}

	public int[][] moverpersonaje(char pos, int[][] array) {
	

		if (pos == 'd') {

			if (array[x][y + 1] != 2 && array[x][y + 1] != 3) {
				array[x][y] = 0;
				array[x][y + 1] = 4;
				y = y + 1;
			} else if (x == 6 && y + 1 == 4) {
				array[x][y] = 0;
				array[14][20] = 4;
				x = 14;
				y = 20;
			}

			else if (x == 14 && y + 1 == 20) {
				array[x][y] = 0;
				array[6][4] = 4;
				x = 6;
				y = 4;

			}
		}

		else if (pos == 'w') {
			if (array[x - 1][y] != 2 && array[x - 1][y] != 3) {
				array[x][y] = 0;
				array[x - 1][y] = 4;
				x--;
			} else if (x - 1 == 6 && y == 4) {
				array[x][y] = 0;
				array[14][20] = 4;
				x = 14;
				y = 20;
			}

			else if (x - 1 == 14 && y == 20) {

				array[x][y] = 0;
				array[6][4] = 4;
				x = 6;
				y = 4;

			}

		}

		else if (pos == 'a') {
			if (array[x][y - 1] != 2 && array[x][y - 1] != 3) {
				array[x][y] = 0;
				array[x][y - 1] = 7;
				y = y - 1;
			}

			else if (x == 6 && y - 1 == 4) {
				array[x][y] = 0;
				array[14][20] = 4;
				x = 14;
				y = 20;
			} else if (x == 14 && y - 1 == 20) {

				array[x][y] = 0;
				array[6][4] = 4;
				x = 6;
				y = 4;

			}
		}

		else if (pos == 's') {

			if (array[x + 1][y] != 2 && array[x + 1][y] != 3) {
				array[x][y] = 0;
				array[x + 1][y] = 4;
				x++;
			} else if (x + 1 == 6 && y == 4) {
				array[x][y] = 0;
				array[14][20] = 4;
				x = 14;
				y = 20;
			} else if (x + 1 == 14 && y == 20) {

				array[x][y] = 0;
				array[6][4] = 4;
				x = 6;
				y = 4;

			}

			

		}
		else if (pos=='x') {
			
			array[x][y] = 5;

			int cont = 1;
			boolean salir = false;
			while (!salir && cont < 4) {

				if (array[x][y + cont] == 6) {
					array[x][y + cont] = 11;// chrollo incinerado;
					

					
				} else if (array[x][y + cont] == 0 || array[x][y + cont] == 10) {
					array[x][y + cont] = 9;
				}

				else {
					salir = true;
					cont--;
				}
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				t.draw(array);
				cont++;
			}
			int retorno = y + cont;
			for (int cont2 = 1; cont2 < cont; cont2++) {

				array[x][retorno - cont2] = 0;

				t.draw(array);
			}

		}
			
		

		if ((x == 13 && y == 20) || (x == 15 && y == 20) || (x == 14 && y == 19) || (x == 14 && y == 21)
				|| ((x == 5 && y == 4) || (x == 6 && y == 3) || (x == 6 && y == 5) || (x == 7 && y == 4))) {
			array[6][4] = 3;
			array[14][20] = 3;
		}

		return array;

	}

}
