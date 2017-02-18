package core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import map.*;
import test.Parameters;
import utility.Random;

/**
 * Pattern Factory
 * 
 * @author Maxime
 *
 */
public class MapBuilder {
	
	private String[][] mapInput; // on récupère toutes les informations de la carte sélectionné exactement comme écrites dans le fichier txt
	private Map mapOutput; // Map de sortie
	private Square[][] tab; // tableau de square pour la Map de sortie
	private int width;
	private int height;
	private File file;
	
	public MapBuilder() {
		
		file = new File("map/" + Parameters.SIZE_MAP + "/" + Parameters.NAME_MAP + ".txt"); // Problème relation size et nom des cartes en fonction du sous dossier
		
		try {		
			BufferedReader br = new BufferedReader(new FileReader(file));			
			String line;
			String[] results;
			width = 0;
			height = 0;
			
			while ((line = br.readLine()) != null) {
				results = line.split(" ");
				width = results.length;
				height++;
			}

		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		
		mapInput = new String[width][height];
		mapOutput = new Map(width,height);
		tab = new Square[width][height];
		
		readTxt(Parameters.SIZE_MAP);
		
	}
	
	/**
	 * lit le fichier txt de la carte choisie
	 * @param size
	 */
	public void readTxt(String size){
		
		try {
			
			BufferedReader br = new BufferedReader(new FileReader(file));			
			String line;
			String[] results;
			int j = 0;
			
			while ((line = br.readLine()) != null) {
				results = line.split(" ");
				for(int i = 0 ; i < results.length ; i++){
					mapInput[i][j] = results[i];
				}
				j++;
			}
		} catch (FileNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
	
    /**
     * Interprête le tableau à deux dimensions représentant la carte
     * 
     * @param width la largeur de la carte sélectionnée
     * @param height la hauteur de la carte sélectionnée
     * @return la carte de sortie, un tableau d'objets de type Square
     * @see Square
     */
	public Map buildMap(){
		
		for (int j = 0 ; j < height ; j++) {
            for (int i = 0 ; i < width ; i++) {
                
            	switch(mapInput[i][j]){  
            		case Parameters.FOOD_ICON: SourceFood sourceFood = new SourceFood(i,j); 
            								   mapOutput.getSourceFoodArray().add(sourceFood); 
            								   tab[i][j] = sourceFood;	break;
            		case Parameters.WALL_ICON: tab[i][j] = new Wall(i,j); break;
            		case Parameters.FLOOR_ICON: tab[i][j] = new Floor(i,j); break;
            		case Parameters.FLOWER_ICON: tab[i][j] = new Flower(i,j); break;
            		case Parameters.ROCK_ICON: tab[i][j] = new Rock(i,j); break;
            		case Parameters.INVISIBLE_WALL_ICON: tab[i][j] = new InvisibleWall(i,j); break;
            		default : tab[i][j] = null; break;
            	}
            }
        }
		
		mapOutput.setTab(tab);
		inputRand(Parameters.START_NUMBER_MOUSE, Parameters.MOUSE_ATTRIBUTE); 
		inputRand(Parameters.START_NUMBER_SOURCEFOOD, Parameters.FOOD_ATTRIBUTE);
		inputRand(Parameters.START_NUMBER_ROCK, Parameters.ROCK_ATTRIBUTE);
		inputRand(Parameters.START_NUMBER_FLOWER, Parameters.FLOWER_ATTRIBUTE);
		
        return mapOutput;
	}
	
	
	/**
	 * Place aléatoirement un certain nombre de type de Square sur la carte
	 * 
	 * @param number
	 * @param type
	 */
	public void inputRand(int number, String type){
		
		int x, y;
		
		for(int i = 0 ; i < number ; i++){
			x = Random.rand(1, width-1);
			y = Random.rand(1, height-1);
			if (tab[x][y].isWalkable()){
				mapOutput.input(createNewSquare(type, x, y));
				// ou alors map[x][y] = createNewSquare(type, x, y);
			}
			else{
				i--;
			}
		}
	}
	
	/**
	 * Retourne un nouvel objet Square du type demandé pour le placer sur la grille
	 * 
	 * @param type 
	 * @param x
	 * @param y
	 * @return un Square de type demandé
	 */
	public Square createNewSquare(String type, int x, int y){
		switch(type){
			case Parameters.MOUSE_ATTRIBUTE: 
				Mouse mouse = new Mouse(x, y);
				mapOutput.getMouseAliveArray().add(mouse);
				return mouse; 
			case Parameters.FOOD_ATTRIBUTE: 
				SourceFood sourceFood = new SourceFood(x, y);
				mapOutput.getSourceFoodArray().add(sourceFood);
				return sourceFood; 
			case Parameters.ROCK_ATTRIBUTE: return new Rock(x, y); 
			case Parameters.FLOWER_ATTRIBUTE: return new Flower(x, y); 
			default: return null; 
		}
		
	}
	

}
