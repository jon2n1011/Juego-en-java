package hxhthefirsttreasure;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

/* Aplicant el disseny modular implementa un programa que realitzi les funcions d’una calculadora senzilla: suma, resta, multiplicació, divisió entera i mòdul. La calculadora treballa únicament amb nombres enters. Dispondrà d’un menú d’opcions:

Obtenir dos números a operar
Sumar 
Restar
Multiplicar
Divisió: Aquí tornarà a demanar si es vol obtenir el mòdul o la divisió entera
Visualitzar el resultat de l’operació.
Sortir del programa.

Comproveu possibles errades a les dades d’entrada.

Ara milloreu el programa perquè el resultat de cada operació pugui ser acumulatiu, de forma que a l’opció a, únicament es demani dos números quan no tinguem res acumulat. En cas contrari, demanarà un únic valor. Afegiu l’opció d’inicialitzar, posar a 0, el valor acumulat.

*/
public class hunter {

	static Scanner input = new Scanner(System.in);
	public static int files = 10; // Les files i columnes globals ja que tindre que utilitzarles mes d'un cop.
	public static int columnes = 10;
	public static float[][] rasca = new float[files][columnes]; //Matriz global per que la vaig a utilizar a dos procediments simplement per ahorrar codi.

	public static void main(String[] args) {
		int opcio = 0;
		boolean apostarara = false; // Boolean que no permetra entrar a postar si no s'ha generat una matriu abans
		boolean valid=false;
		do {

			opcio = PresentaMenu();
			// Segons l'opcio llegida farem l'acció corresponent mitjançant un switch
			switch (opcio) {

			case 1: // Mostrar les instruccions
				instruccions();
				
				break;
			case 2: // Inicialitzar la matriu
				inicialitzarrasca();
				apostarara=true;
				break;
			case 3: // Apostar
				if (apostarara) {

					boolean pararjuego = false; // Centinela que parara el juego si la pauesta es 0.
					while (!pararjuego) {
						int aposta = demanaraposta();

						if (aposta!=0) {
							
						int [] demanarposicio= demanarpos();
						
						float ganancias = calcularjuego(demanarposicio,aposta);
						
						System.out.println("Has guanyat: "+ganancias);
						}
						
						else {
							pararjuego=true;
							apostarara=false;
						}
					}
				}

				else {

					System.out.println("S'ha de generar el tauler de joc abans"); // El fico am,b un syso per posar missatges
																// personalitzas en cadascuna, si poses un error comu
																// per a totes un procediment
				}
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
		System.out.println("1.- Instruccions\r\n" + "2.- Generar taulell de joc\r\n" + "3.- Jugar Partida\r\n"
				 + "0.- Sortir\r\n");

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
		// Retorna el valor al m�tode que li ha cridat
	}

	public static void instruccions() {

		System.out.println(
				"En aquest examen creareu joc d’apostes  (Rasca Rasca) fent servir programació modular i amb el paradigma Top-Down");
		System.out.println(
				"El joc consisteix en crear aleatòriament una matriu de 10x 10  posicions de nombres entre 1 i 100. L’usuari, que vol jugar, li donarà a la banca (indicarà al sistema) una aposta d’euros positiva menor de, com a màxim, 50€ i una casella a rascar.");
		System.out.println(
				"La banca (l’ordinador) retornarà al jugador el % dels diners apostats. El % correspont al número  que hi havia a la cel·la destapada.");

	}

	public static void inicialitzarrasca() {

		for (int i = 0; i < files; i++) {
			// Prueba
			for (int j = 0; j < columnes; j++) {
				int x = (int) (Math.random() * 99) + 1;
				rasca[i][j] = x;
			}

		}
		
		for (int i = 0; i < files; i++) {
			// Prueba
			for (int j = 0; j < columnes; j++) {
				int x = (int) (Math.random() * 99) + 1;
				System.out.print(rasca[i][j]+"|");
			}
			System.out.println();
		}
		
		
		System.out.println("Matriu generada");

		boolean apostarara = true;

	}

	public static int demanaraposta() {
		int apuesta;
		System.out.println("Fica la aposta");
		apuesta = input.nextInt();
		return apuesta;

	}

	public static int [] demanarpos() {
		int [] spain = new int [2];
		System.out.println("Introduex fila");
		spain[0]= input.nextInt();
		System.out.println("Introdueix columna");
		spain[1]= input.nextInt();
		return spain;
		
	}
	public static float calcularjuego(int [] demanarposicio2,float aposta2) {
	
		float resultat;
		
		resultat= aposta2* (rasca[demanarposicio2[0]][demanarposicio2[1]])/100;
		
	return resultat;
	}


}
