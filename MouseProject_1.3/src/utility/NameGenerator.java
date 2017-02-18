package utility;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import utility.Random;

import map.Mouse;

public class NameGenerator {
	
	private static File directory = new File("names");
	private static File file;
	private static String name;
	
	public static String nameGeneration(Mouse mouse){
		
		file = new File(directory + "/" + mouse.getSexe() + ".txt"); // Sur Windows
		
		try {	
			BufferedReader br = new BufferedReader(new FileReader(file));			
			String line = br.readLine();
			String[] results = line.split(",");
			name = results[Random.rand(0, results.length - 1)];
			br.close();
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		return name;
	}

}
