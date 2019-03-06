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
 * Joc d'acció Ambientat en la serie d'animació japonesa. Hunter X Hunter
 * Copyright.
 * 
 * @author Jon Campaña Bonilla
 * @version 1.0
 * @since 1.0
 *
 */
public class huntergame {
	public static Scanner input = new Scanner(System.in);
	public static int x = 1;
	/**
	 * Variable global que ens permet senyalitzar la fila del personatge principal.
	 **/
	public static int y = 1;
	/**
	 * Variable global que ens permet senyalitzar la columna del personatge
	 * principal.
	 **/
	public static int ex = 18;
	/**
	 * Variable global que ens permet senyalitzar la fila del personatge enemic.
	 **/
	public static int ey = 28;
	/**
	 * Variable global que ens permet senyalitzar la columna del personatge enemic.
	 **/
	public static int vidas = 3;
	/**
	 * Variable global que indica la vida del nostre personatge i que ens serveix
	 * per diverses coses explicades posteriorment.
	 **/
	public static int vidac = 5;
	/**
	 * Variable global que indica la vida de l'enemic i que ens serveix per diverses
	 * coses explicades posteriorment.
	 **/
	public static boolean chrolloalife = true;
	/**
	 * Booleà que permetrà seguir jugant mentre que l'enemic sigui viu ( la variable
	 * vidac sigui major a 0).
	 **/
	/**
	 * Paràmetres a cridar de la interfície gràfica dissenyada per Marc Albareda
	 * 
	 * timer t Variable que utilitzarem per a cridar a board.java f Variable que
	 * utilitzarem per a cridar a una nova finestra.
	 * 
	 */
	public static Timer timer = new Timer();
	public static Board t = new Board();
	public static Window f = new Window(t);
	public static Scanner sc = new Scanner(System.in);

	/**
	 * 
	 * A continuació tenim el taulell de Joc representat en una matriu 30x20 on cada
	 * numero representa una imatge.
	 * 
	 */
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

		empezarmapa();
		bucle();

	}

	public static void bucle() {

		movimientos();
		dibujarmapa();
		movimientonaranja();
		derrota();
		victoria();

	}

	/**
	 * Aquest mètode és el que farà la configuració inicial del mapa, carregara tots
	 * els sprites de imatges i iniciarà un bucle de tot el joc amb els procediments
	 * corresponents
	 */

	public static void empezarmapa() {
		/**
		 * Paràmetres
		 * 
		 * @param imatges Les imatges que dibuixarem a la matriu mapa.
		 * @param f       Crida a Windows.java
		 * @param t       Crida a Board.java
		 * @param timer   Crida al temporitzador.
		 * 
		 */
		f.playMusic("hxh.wav");
		String[] imatges = { "", "losa.png", "mountain.png", "remolino.png", "kura.png", "attackx.png", "chrollo.png",
				"kura2.png", "kurapika.gif", "chain.png", "fuego.png", "chrollo2.png", "youwin.png", "vidasi.png",
				"vidas1.png", "vidas2.png", "vidas3.png" };
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

	/**
	* Aquí bàsicament mourem al personatge segons la tecla polsada, obrirem el menu
	* de pausa donant pas a una altre funció i també donarem pas a atacar, que
	* estarà linkada a un altre funció
	*/
	public static void movimientos() {
		/**
		 * Paràmetres
		 * 
		 * @param f     Crida a Windows.java
		 * @param t     Crida a Board.java
		 * @param y     Variable global per recórrer el moviment entre columnes
		 * @param x     Variable global per recórrer el moviment entre files.
		 * @param mapa  Matriu en la que treballem.
		 * @param union Matriu en la que dibuixarem la matriu mapa intercalada amb un
		 *              GIF.
		 * 
		 */
		if (vidas > 0 && chrolloalife) {
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
			} else if (f.getPressedKeys().contains('y')) {
				int[][][] union = { mapa, { { 8 } } };
				t.draw(union);
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else if (f.getPressedKeys().contains('m')) {
				pausa();
			}
			// Este if nos permite resetear los torbellinos una vez salimos de ellos para
			// volver a cargar la imagen.
			if ((x == 13 && y == 20) || (x == 15 && y == 20) || (x == 14 && y == 19) || (x == 14 && y == 21)
					|| ((x == 5 && y == 4) || (x == 6 && y == 3) || (x == 6 && y == 5) || (x == 7 && y == 4))) {
				mapa[6][4] = 3;
				mapa[14][20] = 3;
			}
		}
	}

	/**
	* Procediment en el qual mourem a l'enemic de forma aleatòria separades
	* en dos procediments alterns un per a demanar un número i l'altre per a processar l'atac
	*/
	public static void movimientonaranja() {
		/**
		 * Paràmetres
		 * 
		 * @param chrolloalife Permet la entrada al procediment sempre que sigui el
		 *                     Booleà sigui true
		 * @param vidas        Permet la entrada al procediment sempre que les vides
		 *                     siguin major a 0.
		 * @param rnd          Variable que cridara a la funció random.
		 * @param ex           Variable global per recórrer el moviment entre files.
		 * @param ey           Variable global per recórrer el moviment entre columnes.
		 * 
		 */
		if (chrolloalife && vidas > 0) {

			int rnd = random();

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
				ataquemigo();

				break;
			}
		}
	}

	/**
	 * Procediment de atac que es crea a partir de l'opció X  de movimientos();
	 */
	public static void ataquecadena() {
		/**
		 * Paràmetres
		 * 
		 * @param cont    Iniciem el contador que anirà acumulant en el bucle per tal de
		 *                fer un atac de rang 3.
		 * @param vidac   Vida de l'enemic, quan l'atac o el moviment de x i y l'agafi
		 *                la reduirem.
		 * @param salir   Booleà que permet sortir del bucle si ens topem amb un bloc
		 *                que no es pot traspassar.
		 * @param retorno Contador que utilitzarem per tal de esborrar el moviment de
		 *                rang 3 que hem fet.
		 * 
		 */
		mapa[x][y] = 5;

		int cont = 1;
		boolean salir = false;
		while (!salir && cont < 4) {

			if (mapa[x][y + cont] == 6) {
				mapa[x][y + cont] = 11;// chrollo incinerado;
				vidac--;

				if (vidac == 0) {
					chrolloalife = false;
				}
			} else if (mapa[x][y + cont] == 0 || mapa[x][y + cont] == 10) {
				mapa[x][y + cont] = 9;
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
			dibujarmapa();
			cont++;
		}
		int retorno = y + cont;
		for (int cont2 = 1; cont2 < cont; cont2++) {

			mapa[x][retorno - cont2] = 0;

			dibujarmapa();
		}

	}

	/**
	 * Procediment de atac de l'enemic a movimientonaranja();
	 */
	public static void ataquemigo() {
		/**
		 * Paràmetres
		 * 
		 * @param vidas la nostra vida, si ens agafa l'atac de l'enemic disminueix.
		 * @param ex    Variable global per recórrer el moviment entre files.
		 * @param ey    Variable global per recórrer el moviment entre columnes.
		 * 
		 */
		if (mapa[ex - 1][ey - 1] == 0 || mapa[ex - 1][ey - 1] == 4) {
			if (mapa[ex - 1][ey - 1] == 4) {
				vidas--;
			}

			else if (mapa[ex - 1][ey - 1] == 0) {
				mapa[ex - 1][ey - 1] = 10;
			}

		}
		if (mapa[ex + 1][ey - 1] == 0 || mapa[ex + 1][ey - 1] == 4) {
			if (mapa[ex + 1][ey - 1] == 4) {
				vidas--;

			} else if (mapa[ex + 1][ey - 1] == 0) {

				mapa[ex + 1][ey - 1] = 10;
			}

		}

		if (mapa[ex][ey - 1] == 0 || mapa[ex][ey - 1] == 4) {
			if (mapa[ex][ey - 1] == 4) {
				vidas--;
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
	}

	/**
	 * En aquest procediment crearem el menú pausa, que es crea a partir de haber agafat l'opció o lletra M en el procediment movimientos(); 
	 */
	public static void pausa() {
		/**
		 * Paràmetres
		 * 
		 * @param salirpausa Booleà que ens permetra sortir del menú pausa quan polsem la tecla 'T'
		 * @param lletres   Lletres que dibuixarem a la matriu.
		 * @param colorlletres Color de les lletres.
		 * @param pausa    Matriu en la cual dibuixarem les lletres.
		 * 
		 */
		boolean salirpausa = false; 
									 
		String[] lletres = { "", "C", "O", "N", "T", "R", "O", "L", "E", "S", "-", "PAUSA", "W= Pujar cap a dalt",
				"S=Baixar", "A= moures cap a la dreta", "D=Moures cap a l'esquerra", "X= Atacar",
				"Y= Fer el super attack" };
		t.setText(lletres);
		int[] colorlletres = { 0xFFFFFF, 0xFFFFFF, 0xFFFFFF, 0xFFFFFF, 0xFFFFFF, 0xFFFFFF, 0xFFFFFF, 0xFFFFFF, 0xFFFFFF,
				0xFFFFFF, 0xFFFFFF, 0x0000FF, 0xFFFFFF, 0xFFFFFF, 0xFFFFFF, 0xFFFFFF, 0xFFFFFF, 0xFFFFFF };
		t.setColortext(colorlletres);
		int[][] pausa = { { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, },

				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 12, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 13, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 14, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 15, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 16, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 11, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
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
		while (salirpausa == false) {
			t.draw(pausa, 't');
			if (f.getPressedKeys().contains('t')) { /**
													 * La finalitat del If es que quan pulsem la tecla T cambiara el
													 * bolea i ho posara a true.
													 */
				salirpausa = true;
			}

		}
		t.setImgbackground("mapa.png"); // Reinciamos el fondo del mapa
	}

	/**
	 * Procediment que utilitzarem sempre que dibuixem el mapa per pantalla. 
	 */
	public static void dibujarmapa() {
		/**
		 * Paràmetres
		 * 
		 * @param union2 Unió de dues matrius, la matriu mapa i una matriu creada per l'sprite de les vides del personatge.
		
		 * 
		 */
		int[][][] union2 = { mapa,
				{ { 0, 0, 0, 0, 0, 0, 0, 0, 13 + vidas }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, } };
		t.draw(union2);
	}

	/**
	 * Procediment que utilitzarem per a demanar un número random en movimientonaranja(); 
	 */
	public static int random() { 
		
		/**
		 * Paràmetres
		 * 
		 * @param random Crea un número random de l'1 al 5.
		
		 * @param return retorna el int random.
		 */
		int random = (int) (Math.random() * 5) + 1;
		return random;
	}

	/**
	 * Dibuixarem un mapa de victoria una vegada haguem  ganado (chrolloalife seria
	 * false)
	 */
	public static void victoria() {
		/**
		 * Paràmetres
		
		 * @param victoria Matriu de victoria.
		 */
		if (!chrolloalife) {
			int[][] victoria = { { 0 }, };
			t.setImgbackground("youwin.png");
			t.draw(victoria);
		}
	}

	/**
	 * Dibuixarem un mapa de derrota una vegada haguem perdut vides menor que 0 o 0.
	 */
	public static void derrota() {
		/**
		 * Paràmetres
		
		 * @param derrota Matriu de derrota.
		 */
		if (vidas == 0) {
			int[][] derrota = { { 0 }, };
			t.setImgbackground("youlose.png");
			t.draw(derrota);
		}
	}

}
