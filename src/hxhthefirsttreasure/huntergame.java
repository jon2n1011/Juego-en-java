package hxhthefirsttreasure;

import java.util.*;
import java.util.Scanner;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
 /** 
  *  Controles Y super ataque en beta
  *  Mover a,s,d,w
  *  atacar boton x
  *  pausar el juego M
  * 
  * 
  * 
  * 
  * 
  * 
  * **/
public class huntergame {
	public static Scanner input = new Scanner(System.in);
	public static int x = 1;
	public static int y = 1;
	public static int ex = 18;
	public static int ey = 28;
	public static int vidas = 3;
	public static int vidac = 5;
	public static boolean chrolloalife = true;
	public static Timer timer = new Timer();
	public static Board t = new Board();
	public static Window f = new Window(t);
	public static Scanner sc = new Scanner(System.in);
	public static int[][] mapa = {
			{ 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 },

			{ 2, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2 },
			{ 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2 },
			{ 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2 },
			{ 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2 },
			{ 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2 },
			{ 2, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2 },
			{ 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2 },
			{ 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2 },
			{ 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2 },
			{ 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2 },
			{ 2, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2 },
			{ 2, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2 },
			{ 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2 },
			{ 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 0, 2 },
			{ 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2 },
			{ 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2 },
			{ 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2 },
			{ 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 2 },
			{ 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2 }, };
	
	public static void main(String[] args) {
		int opcio = 0;
		do {

			opcio = PresentaMenu();
			// Segons l'opcio llegida farem l'acció corresponent mitjançant un switch
			switch (opcio) {

			case 1:
				empezarmapa();
				bucle();

				break;
			case 0: // Sortir
				System.out.println("Arribederchi");
				break;
			default:
				System.out.println("Opció incorrecte");

			}

		} while (opcio != 0);

		input.close();
	}
	public static int PresentaMenu() {
		int op = 0;
		boolean valid = false;
		// Mostrem el menú
		System.out.println("1.- Jugar");

		do {
			try {
				op = input.nextInt();

				valid = true;
			} catch (Exception e) {
				System.out.println("Atenció! Únicament es permet insertar números. ");
				input.next();
			}

		} while (!valid);
		return op;

	}
	
	public static void bucle() {
		if (chrolloalife) {
		movimientos();
		movimientonaranja();
		}
		
		
		else {
		victoria();	
		
			
		}
	}

	

	public static void empezarmapa() {
		String[] imatges = { "", "losa.png", "mountain.png", "remolino.png", "kura.png", "attackx.png", "chrollo.png",
				"kura2.png", "kurapika.gif", "chain.png", "fuego.png","chrollo2.png","youwin.png","vidasi.png","vidas1.png","vidas2.png","vidas3.png"};
		t.setSprites(imatges);
		f.setActLabels(false);
		f.setTitle("Hunter X Hunter The Revenge of Scarlet eyes");
		t.setActimgbackground(true);
		t.setImgbackground("mapa.png");
		t.draw(mapa);
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				bucle();
			}

		}, 0, 200);
	}

	public static void movimientos() {
		/**
		 * Aqui empezariamos lo que seria el movimiento de nuestro personaje y sus
		 * ataques explicados apartado por apartado a continuación
		 **/
		if (f.getPressedKeys().contains('d')) {

			if (mapa[x][y + 1] != 2 && mapa[x][y + 1] != 3) {
				mapa[x][y] = 0;
				mapa[x][y + 1] = 4;
				y = y + 1;
			} else if (x == 6 && y + 1 == 4) {
				mapa[x][y] = 0;
				mapa[14][20] = 4;
				x = 14;
				y = 20;
			}

			else if (x == 14 && y + 1 == 20) {

				mapa[x][y] = 0;
				mapa[6][4] = 4;
				x = 6;
				y = 4;

			}
		}

		else if (f.getPressedKeys().contains('w')) {
			if (mapa[x - 1][y] != 2 && mapa[x - 1][y] != 3) {
				mapa[x][y] = 0;
				mapa[x - 1][y] = 4;
				x--;
			} else if (x - 1 == 6 && y == 4) {
				mapa[x][y] = 0;
				mapa[14][20] = 4;
				x = 14;
				y = 20;
			}

			else if (x - 1 == 14 && y == 20) {

				mapa[x][y] = 0;
				mapa[6][4] = 4;
				x = 6;
				y = 4;

			}

		}

		else if (f.getPressedKeys().contains('a')) {
			if (mapa[x][y - 1] != 2 && mapa[x][y - 1] != 3) {
				mapa[x][y] = 0;
				mapa[x][y - 1] = 7;
				y = y - 1;
			}

			else if (x == 6 && y - 1 == 4) {
				mapa[x][y] = 0;
				mapa[14][20] = 4;
				x = 14;
				y = 20;
			} else if (x == 14 && y - 1 == 20) {

				mapa[x][y] = 0;
				mapa[6][4] = 4;
				x = 6;
				y = 4;

			}
		}

		else if (f.getPressedKeys().contains('s')) {

			if (mapa[x + 1][y] != 2 && mapa[x + 1][y] != 3) {
				mapa[x][y] = 0;
				mapa[x + 1][y] = 4;
				x++;
			} else if (x + 1 == 6 && y == 4) {
				mapa[x][y] = 0;
				mapa[14][20] = 4;
				x = 14;
				y = 20;
			} else if (x + 1 == 14 && y == 20) {

				mapa[x][y] = 0;
				mapa[6][4] = 4;
				x = 6;
				y = 4;

			}
		} else if (f.getPressedKeys().contains('x')) {
			ataquecadena();
		}
		else if (f.getPressedKeys().contains('y')) {
			int[][][] union = { mapa, { { 8 } } };
			t.draw(union);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}	
		}
		else if (f.getPressedKeys().contains('m')) {
		pausa();
		}
		// Este if nos permite resetear los torbellinos una vez salimos de ellos para volver a cargar la imagen.
		if ((x == 13 && y == 20) || (x == 15 && y == 20) || (x == 14 && y == 19) || (x == 14 && y == 21)
				|| ((x == 5 && y == 4) || (x == 6 && y == 3) || (x == 6 && y == 5) || (x == 7 && y == 4))) {
			mapa[6][4] = 3;
			mapa[14][20] = 3;
		}
		dibujarmapa();
	}

	public static void movimientonaranja() {

		int rnd = (int) (Math.random() * 5) + 1;

		switch (rnd) {

		case 1: /* El case 1 consistira en que el enemic puji una casella */
			if (mapa[ex - 1][ey] == 0) {
				mapa[ex][ey] = 0;
				mapa[ex - 1][ey] = 6;
				ex--;
			}
			break;
		case 2: /* El numero 2 baixara una casella l'enemic */
			if (mapa[ex + 1][ey] == 0) {
				mapa[ex][ey] = 0;
				mapa[ex + 1][ey] = 6;
				ex++;
			}
			break;
		case 3: /* El cas 3 es moura cap a l'esquerra */
			if (mapa[ex][ey - 1] == 0) {
				mapa[ex][ey] = 0;
				mapa[ex][ey - 1] = 6;
				ey--;
			}
			break;
		case 4: /* En el cas 4 es moura cap a la dreta */
			if (mapa[ex][ey + 1] == 0) {
				mapa[ex][ey] = 0;
				mapa[ex][ey + 1] = 6;

				ey++;

			}
			break;
		case 5:

			if (mapa[ex - 1][ey - 1] == 0 || mapa[ex - 1][ey - 1] == 4) {
				if (mapa[ex - 1][ey - 1] == 4) {
					vidas--;
					System.out.println(vidas);
				}

				else if (mapa[ex - 1][ey - 1] == 0) {
					mapa[ex - 1][ey - 1] = 10;
				}

			}
			if (mapa[ex + 1][ey - 1] == 0 || mapa[ex + 1][ey - 1] == 4) {
				if (mapa[ex + 1][ey - 1] == 4) {
					vidas--;
					System.out.println(vidas);
				} else if (mapa[ex + 1][ey - 1] == 0) {

					mapa[ex + 1][ey - 1] = 10;
				}

			}

			if (mapa[ex][ey - 1] == 0 || mapa[ex][ey - 1] == 4) {
				if (mapa[ex][ey - 1] == 4) {
					vidas--;
					System.out.println(vidas);
				} else if (mapa[ex][ey - 1] == 0) {
					mapa[ex][ey - 1] = 10;
				}

			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if (mapa[ex - 1][ey - 1] == 10) {
				mapa[ex - 1][ey - 1] = 0;
			}
			if (mapa[ex + 1][ey - 1] == 10) {
				mapa[ex + 1][ey - 1] = 0;
			}
			if (mapa[ex][ey - 1] == 10) {
				mapa[ex][ey - 1] = 0;
			}

			break;
		}

	}
	public static void ataquecadena() {
		mapa[x][y] = 5;

		/* A partir de aqui crearemos un bucle porque el ataque tiene 3 de rango. Hacer un void */
		
		int cont=1;
		boolean salir=false;
		while (!salir && cont<4){
		
			if (mapa[x][y + cont]==6) {
				mapa[x][y+cont]=11;//chrollo incinerado;
			vidac--;
			
				if (vidac==0) {
				chrolloalife=false;
				}
			}
			else if (mapa[x][y+cont]==0  || mapa[x][y+cont]==10)  {
			mapa[x][y + cont] = 9;}
			
			else {
				salir=true;
				cont--;
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			dibujarmapa();
			cont++;
		}
		int retorno = y + cont ;
		for (int cont2 = 1; cont2 < cont; cont2++) {

			mapa[x][retorno - cont2] = 0;

			dibujarmapa();
		}

	} 
	public static void pausa () {
		
		boolean salirpausa=false; // Este boolean nos sirve para empezar el bucle y nos permitirar continuarlo mientras sea falso ( mientras no se pulse la tecle t como mas abajo lo explicamos)
		while (salirpausa==false) {
		String[] lletres = { "", "C", "O", "N", "T", "R", "O", "L", "E", "S","-","PAUSA","W= Pujar cap a dalt","S=Baixar","A= moures cap a la dreta","D=Moures cap a l'esquerra", "X= Atacar","Y= Fer el super attack" };
		t.setText(lletres);
		int[] colorlletres = { 0xFFFFFF, 0xFFFFFF, 0xFFFFFF, 0xFFFFFF, 0xFFFFFF, 0xFFFFFF, 0xFFFFFF, 0xFFFFFF, 0xFFFFFF,
				0xFFFFFF,0xFFFFFF,0x0000FF,0xFFFFFF,0xFFFFFF,0xFFFFFF,0xFFFFFF,0xFFFFFF,0xFFFFFF };
		t.setColortext(colorlletres);	
		int[][] pausa = {
				{ 0,1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  },

				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 12, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 13, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 14, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 15, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 11,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 17, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, };
		t.setActimgbackground(true);
		t.setImgbackground("yuzu.png");
		t.draw(pausa,'t');	
		if (f.getPressedKeys().contains('t')) {
		salirpausa=true;
		}
		
		}
		t.setImgbackground("mapa.png"); // Reinciamos el fondo del mapa
	}
	public static void dibujarmapa () {
		int [][][] union2 = {mapa,{{0,0,0,0,0,0,0,0,13+vidas},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0},
		}};
		t.draw(union2);
	}
	public static void victoria () {
		
		 int[][] victoria = {{0},};
		t.setImgbackground("youwin.png");
		t.draw(victoria);	
	}

}
