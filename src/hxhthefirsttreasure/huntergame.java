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
	public static Timer timer = new Timer();
	public static Board t = new Board();
	public static Window f = new Window(t);
	public static Scanner sc = new Scanner(System.in);
	public static principal kurapika = new principal(1, 1, "Kurapika", 1, 3);
	public static enemigo chrollo = new enemigo (18, 28, "Chrollo", 1, 5);
	public static mapa mapa1= new mapa();
	
	
	
	/**
	 * 
	 * A continuació tenim el taulell de Joc representat en una matriu 30x20 on cada
	 * numero representa una imatge.
	 * 
	 */
	

	public static void main(String[] args) {

		empezarmapa();
		bucle();

	}

	public static void bucle() {

		movimientos();
		mapa1.setMapa(chrollo.movimientonaranja(kurapika,mapa1.getMapa()));
		dibujarmapa();
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
		t.draw(mapa1.getMapa());
		
		
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
		if (kurapika.getVida() > 0) {
			if (f.getPressedKeys().contains('d')) {
				mapa1.setMapa(kurapika.moverpersonaje('d', mapa1.getMapa()));
				
				}

			else if (f.getPressedKeys().contains('w')) {
				
				mapa1.setMapa(kurapika.moverpersonaje('w', mapa1.getMapa()));

			}

			else if (f.getPressedKeys().contains('a')) {
				mapa1.setMapa(kurapika.moverpersonaje('a', mapa1.getMapa()));
			}

			else if (f.getPressedKeys().contains('s')) {
				mapa1.setMapa(kurapika.moverpersonaje('s', mapa1.getMapa()));	
			
			} else if (f.getPressedKeys().contains('x')) {
				mapa1.setMapa(kurapika.moverpersonaje('x', mapa1.getMapa()));
			
			} else if (f.getPressedKeys().contains('y')) {
				int[][][] union = { mapa1.getMapa(), { { 8 } } };
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
			
			
		}

	}

	
	public static void pausa() {
		/**
		 * Paràmetres
		 * 
		 * @param salirpausa   Booleà que ens permetra sortir del menú pausa quan polsem
		 *                     la tecla 'T'
		 * @param lletres      Lletres que dibuixarem a la matriu.
		 * @param colorlletres Color de les lletres.
		 * @param pausa        Matriu en la cual dibuixarem les lletres.
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
		 * @param union2 Unió de dues matrius, la matriu mapa i una matriu creada per
		 *               l'sprite de les vides del personatge.
		 * 
		 * 
		 */
		int[][][] union2 = { mapa1.getMapa(),
				{ { 0, 0, 0, 0, 0, 0, 0, 0, 13 + kurapika.getVida() }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0, 0, 0, 0, 0 },
						{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, } };
		t.draw(union2);
	}


	/**
	 * Dibuixarem un mapa de victoria una vegada haguem ganado (chrolloalife seria
	 * false)
	 */
	public static void victoria() {
		/**
		 * Paràmetres
		 * 
		 * @param victoria Matriu de victoria.
		 */
		if (chrollo.isVivoe()==false) {
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
		 * 
		 * @param derrota Matriu de derrota.
		 */
		if (kurapika.getVida()<=0) {
			int[][] derrota = { { 0 }, };
			t.setImgbackground("youlose.png");
			t.draw(derrota);
		}
	}

}
