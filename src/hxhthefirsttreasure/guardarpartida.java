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
public class guardarpartida  {



	public static void guardar (mapa x,principal y, enemigo z) throws IOException {
		
		FileOutputStream fos = new FileOutputStream("partida.dat");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(x);
		oos.writeObject(y);
		oos.writeObject(z);
		System.out.println("Partida guardada");
	
	}
	
	
}
