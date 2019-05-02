package hxhthefirsttreasure;

import java.util.Scanner;
import java.util.Timer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

public class Ejercicio1 {

	static Scanner input = new Scanner(System.in);
	public static int files; // Global ja que la utilitzarem a diferents procediments
	public static String nomjugador; // Global ja que aquesta variable no es cambiara.
	public static int[][] taulell; // Global per que la utilitzarem a diferents procedimets.
	public static Timer timer = new Timer();
	public static Board t = new Board();
	public static Window f = new Window(t);
	public static void main(String[] args) {

		int opcio = 0;

		do {

			opcio = PresentaMenu(); // Segons l'opcio llegida farem l'acció corresponent mitjançant un switch

			switch (opcio) {

			case 1: // Acceid al menu configruació.
				configuracio();
				break;
			case 2: // Accedeix al menu jugar.
				jugar();
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

	public static int PresentaMenu() { // M�tode que retorna un valor enter
		int op = 0;
		boolean valid = false;
		// Mostrem el menú
		System.out.println("1.- Configuració\r\n" + "2.- Jugar\r\n" + "0.- Sortir\r\n");

		do {
			try {
				op = input.nextInt();
				valid = true;
			} catch (Exception e) {
				System.out.println("Atenció! Únicament es permet insertar números. ");
				input.next();
			}

		} while (!valid);
		input.nextLine();
		return op;
		// Retorna el valor al m�tode que li ha cridat
	}

	public static void configuracio() {
		System.out.println("Posa el teu nom Jugador");
		nomjugador = input.nextLine();
		System.out.println("Files i columnes del taulell?");
		files = input.nextInt();

	}

	public static void jugar() {

		init();
		boolean fi = false;
		int newtaulell[][] = borde(taulell, 9);
		while (!fi) {
			int[] posicio = seleccionarPosicio();
			newtaulell = activar(posicio[0], posicio[1], newtaulell);
			fi = comprobar(newtaulell);
			visualitzarmatriu(newtaulell);
		}
		/**
		 * 
		 * 
		 * 
		 * matriu = activar(fila,columna, matriu);
		 * 
		 * 
		 * 
		 * fi();
		 **/
	}

	public static void init() {
		taulell = new int[files][files];
		for (int i = 0; i < files; i++) {

			for (int x = 0; x < files; x++) {

				int random = (int) (Math.random() * 2);
				taulell[i][x] = random;

			}
		}

	}

	public static int[][] borde(int[][] tablero, int valor) {
		int[][] mborde = new int[tablero.length + 2][tablero[0].length + 2];
		for (int f = 0; f < mborde.length; f++) {
			for (int c = 0; c < mborde[0].length; c++) {
				if (f == 0 || f == mborde.length - 1 || c == 0 || c == mborde[0].length - 1) {
					mborde[f][c] = valor;
				} else {
					mborde[f][c] = tablero[f - 1][c - 1];
				}
			}
		}
		return mborde;
	}

	public static void visualitzarmatriu(int newtaulell2[][]) {

		for (int i = 0; i < files + 2; i++) {
			for (int x = 0; x < files + 2; x++) {

				System.out.print(newtaulell2[i][x]);

			}
			System.out.println();
		}
t.draw(newtaulell2);
	}

	public static int[] seleccionarPosicio() {
		int[] arraysito = new int[2];
		int num = 0;
		System.out.println("Introdueix la fila");
		arraysito[0] = num = input.nextInt();
		System.out.println("Introdueix la columna");
		arraysito[1] = num = input.nextInt();
		return arraysito;
	}

	public static int[][] activar(int filita, int columnita, int[][] taulellactivar) {

		if (taulellactivar[filita][columnita] == 0) {
			taulellactivar[filita][columnita] = 1;

		} else {
			taulellactivar[filita][columnita] = 0;

		}
		if (taulellactivar[filita - 1][columnita] != 9) {
			if (taulellactivar[filita - 1][columnita] == 0) {
				taulellactivar[filita - 1][columnita] = 1;
			} else {
				taulellactivar[filita - 1][columnita] = 0;
			}
		}

		if (taulellactivar[filita + 1][columnita] != 9) {
			if (taulellactivar[filita + 1][columnita] == 0) {
				taulellactivar[filita + 1][columnita] = 1;
			} else {
				taulellactivar[filita + 1][columnita] = 0;
			}
		}

		if (taulellactivar[filita][columnita - 1] != 9) {
			if (taulellactivar[filita][columnita - 1] == 0) {
				taulellactivar[filita][columnita - 1] = 1;
			} else {
				taulellactivar[filita][columnita - 1] = 0;
			}
		}
		if (taulellactivar[filita][columnita + 1] != 9) {
			if (taulellactivar[filita][columnita + 1] == 0) {
				taulellactivar[filita][columnita + 1] = 1;
			} else {
				taulellactivar[filita][columnita + 1] = 0;
			}
		}

		return taulellactivar;
	}

	public static boolean comprobar(int[][] comprobaciotaulell) {
		int cont = 0;
		boolean comprobaciio = false;
		for (int i = 0; i < files; i++) {

			for (int x = 0; x < files; x++) {
				if (comprobaciotaulell[i][x] == 0) {
					cont++;
				}

			}
		}
		if (cont > 0) {
			return comprobaciio = true;
		} else {
			return comprobaciio = false;
		}
	}

}
