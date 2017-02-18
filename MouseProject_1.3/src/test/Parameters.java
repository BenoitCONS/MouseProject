package test;

public class Parameters {
	
	// Icone String des différents objets de la map
	public final static String MOUSE_ICON = "S"; 
	public final static String FOOD_ICON = "#"; 
	public final static String FLOOR_ICON = "."; 
	public final static String WALL_ICON = "@"; 
	public final static String ROCK_ICON = "O"; 
	public final static String FLOWER_ICON = "x";
	public final static String INVISIBLE_WALL_ICON = "i";
	public final static String INVISIBLE_WALL_ICON_DISPLAY = " ";
	
	// Attribut Type des différents objets de la map
	public final static String MOUSE_ATTRIBUTE = "MOUSE"; 
	public final static String FOOD_ATTRIBUTE = "FOOD"; 
	public final static String FLOOR_ATTRIBUTE = "FLOOR"; 
	public final static String WALL_ATTRIBUTE = "WALL"; 
	public final static String ROCK_ATTRIBUTE = "ROCK"; 
	public final static String FLOWER_ATTRIBUTE = "FLOWER"; 
	public final static String INVISIBLE_WALL_ATTRIBUTE = "INVISIBLE_WALL"; 

	// Choix de la carte
	public final static String SIZE_MAP = "small"; // Taille de la carte
	public final static String NAME_MAP = "ruines"; // Nom de la carte 
	
	public final static int MAX_TURN = 1000; // Nombre de tours maximum
	public final static int VELOCITY = 200; // Vitesse du programme en ms
	
	// Pour placer aléatoirement au début des éléments sur la carte
	public final static int START_NUMBER_MOUSE = 10; // Nombre de souris de départ
	public final static int START_NUMBER_SOURCEFOOD = 10; // Nombre de source de nourriture au départ
	public final static int START_NUMBER_ROCK = 20;
	public final static int START_NUMBER_FLOWER = 0;
	
	// Paramètres des souris
	public final static int VISION_RANGE = 20;
	public final static int MAX_HEALTH = 50; // Vie maximum des souris
	public final static int STARTING_FOOD = 30; // Niveau de nourriture au départ
	public final static int MAX_AGE = 1000; // En nombre de tours
	
	// Paramètres des sources de nourriture
	public static final int STARTING_FOOD_LEVEL = 10; // Nombre d'unité de nourriture d'une source de nourriture à son état initial
	public static final int FOOD_NUMBER_TURN_SATISFACTION = 20; // Nombre de tours dont une unité de nourriture rassasie
	

}
