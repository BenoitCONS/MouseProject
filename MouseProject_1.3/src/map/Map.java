package map;

import java.util.ArrayList;

import core.MapBuilder;
import utility.Random;

public class Map{

	private Square[][] tab; 
	private int width;
	private int height;
	
	private ArrayList<Mouse> mouseAliveArray =  new ArrayList<Mouse>();
	private ArrayList<Mouse> mouseDeathArray =  new ArrayList<Mouse>();
	private ArrayList<SourceFood> sourceFoodArray =  new ArrayList<SourceFood>();
	
	
	public Map(int width, int height){
		this.width = width;
		this.height = height;
	}
	
	
	/**
	 * Retire et sauvegarde le square de sa case et met le Square avec la méthode input ci-dessous
	 * 
	 * @param sqr le square qu'on veut retirer
	 * @see Move.moveMouse
	 */
	public void output(Square sqr) {
        tab[sqr.getX()][sqr.getY()] = sqr.getCurrentSquare();            
    }

	/**
	 * Remplace un square sur la grille et sauvegarde le square de destination
	 *  
	 * @param sqr le square qu'on veut insérer
	 */
    public void input(Square sqr) {
        sqr.setCurrentSquare(tab[sqr.getX()][sqr.getY()]);                // sauvegarde le square de destination
        tab[sqr.getX()][sqr.getY()] = sqr;                          // remplace le square
    }  
	
	/**
	 * Renvoie les données statistiques de la carte
	 * 
	 * @return une chaine de caractère avec les informations de la 
	 * carte (nombre de souris en vie, nombre de souris morte, nombre
	 * de source de nourriture restantes
	 */
	public String statisticData(){
		return "Alive mouses : " + mouseAliveArray.size()
				+ "\nDeath mouses : " + mouseDeathArray.size()
				+ "\nSources of food : " + sourceFoodArray.size();
	}
	
	
	/**
	 * Affiche la carte
	 * 
	 * @param map la carte
	 */
	public String toString() {
		
		String s = "";
	    for (int j = 0; j < height; j++) {
	        for (int i = 0; i < width; i++) {
	            s += tab[i][j].getIcon() + " ";
	        }
	        s += "\n";
	    }
	    return s;
	}
	
	
	public Square[][] getTab() {
	    return tab;
	}
	    
	public void setTab(Square[][] tab){
	    this.tab = tab;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getNumberMouseAlive() {
		return mouseAliveArray.size();
	}

	public int getNumberMouseDeath() {
		return mouseDeathArray.size();
	}

	public int getNumberSourceFood() {
		return sourceFoodArray.size();
	}

	public ArrayList<Mouse> getMouseAliveArray() {
		return mouseAliveArray;
	}

	public ArrayList<Mouse> getMouseDeathArray() {
		return mouseDeathArray;
	}

	public ArrayList<SourceFood> getSourceFoodArray() {
		return sourceFoodArray;
	}

}
