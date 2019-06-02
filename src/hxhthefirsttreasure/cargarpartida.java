package hxhthefirsttreasure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class cargarpartida {
	private static ArrayList<Object> objetos = new ArrayList<>();
	
	public static void cargarpartida(mapa x,principal y, enemigo z) throws IOException, ClassNotFoundException {
		
		ObjectInputStream ois= null;
		try {
				File f3 = new File("partida.dat");
				// solo hace falta hacer esa comprobacion para leer. Para escribir te lo crea
				// auto
				if (!f3.exists()) {
					f3.createNewFile();
				}
				FileInputStream fis = new FileInputStream(f3);
				ois = new ObjectInputStream(fis);
				Object aux = ois.readObject();
				objetos.add(((mapa) aux));
				aux = ois.readObject();
				objetos.add((principal) aux);
				aux = ois.readObject();
				objetos.add((enemigo) aux);
				aux=ois.readObject();
				ois.close();
				}
				catch (IOException io) {
					System.out.println("Lectura finaliza");
					System.out.println(objetos.size());
				}
		
	
				}
	
	public static  ArrayList<Object> getobjetos() {
	    return objetos;
	}
	public static void  borrar() {
		
		objetos.clear();
	}
	
			
		
		
	
}
