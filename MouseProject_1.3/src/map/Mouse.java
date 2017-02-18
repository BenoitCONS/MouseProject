package map;

import character.*;
import core.Node;
import core.Pathfinding;
import test.Parameters;
import utility.Random;
import java.util.ArrayList;
import action.*;
import utility.ArraySort;
import utility.NameGenerator;

public class Mouse extends Square{
	
	
	private String name;
	private String sexe;
	private int vision;
	private boolean alive;
	
	private int age;
	private int cooperationDegree;//Le degree de cooperation
	private int trustDegree; //Le degree de confiance
	private int health; // Barre de vie
	private int food; // Niveau de nourriture
	
	private DiffusionCharacter diffusionCharacter;
	private ReceptionCharacter receptionCharacter;
	private Action action;
	
	private ArrayList<Square> proximitySquare; // Liste des Square dans le champs de vision de la souris
	private ArrayList<Square> memory; // cf proximitySquare
	
	
	public Mouse(int x, int y){
		super(x,y);
		super.walkable = false;
		super.type = Parameters.MOUSE_ATTRIBUTE;
		super.icon = Parameters.MOUSE_ICON;
		vision = Parameters.VISION_RANGE; 
		health = Parameters.MAX_HEALTH;
		food = Parameters.STARTING_FOOD;
		sexe = Random.rand(0, 1) == 0 ? "male" : "female";
		name = NameGenerator.nameGeneration(this);
		alive = true;
		age = 0;
		cooperationDegree = Random.rand(0, 10);
		trustDegree = Random.rand(0, 10);
		diffusionCharacter = (cooperationDegree > 5) ? new Cooperative() : new Selfish();
		receptionCharacter = (trustDegree > 5) ? new Receptive() : new Nihilistic();
	}
	
	public Mouse(int x, int y, int age, DiffusionCharacter diffusionCharacter, ReceptionCharacter receptionCharacter){
		super(x, y);
		this.age = age;
		this.diffusionCharacter = diffusionCharacter;
		this.receptionCharacter = receptionCharacter;
	}
	
	
	
	/**
	 * Lance une action
	 * 
	 * @param map
	 */
	public void doAction(Map map){
		
		see(map);
		
		if (action == null){
			actionChoice(map);
			action.doAction(this, map);
		}
		else{
			if (action.isFinished()==false && food >= food/2){ // Si le déplacement prédédent n'est pas terminé 
				action.doAction(this, map);
			}
			else{
				action = null;
			}
		}
		
	}
	
	/**
	 * Choix de l'action
	 */
	public void actionChoice(Map map){
		
		int x;
		int y;
		ArrayList<Square> proximitySourceFood = new ArrayList<Square>();
		ArrayList<Square> proximityMouse = new ArrayList<Square>();
		
		for(int index = 0 ; index < proximitySquare.size() ; index++){
			x = proximitySquare.get(index).getX();
			y = proximitySquare.get(index).getY();
			if(proximitySquare.get(index).getType().equals(Parameters.MOUSE_ATTRIBUTE)){
				proximityMouse.add(map.getTab()[x][y]);
			}
			if(proximitySquare.get(index).getType().equals(Parameters.FOOD_ATTRIBUTE)){
				proximitySourceFood.add(map.getTab()[x][y]);
			}
		}
		
		// trie par ordre croissant de proximité des Square (nombre de case de déplacement)
		proximitySourceFood = ArraySort.forwardSort(this, proximitySourceFood, map);
		proximityMouse = ArraySort.forwardSort(this, proximityMouse, map);

		
		// A compléter et affiner
		if(food < Parameters.STARTING_FOOD/2){
			if(proximitySourceFood.size() > 0){
				Pathfinding pathfinding = new Pathfinding(this, proximitySourceFood.get(0), map);
				if(pathfinding.searchPath().size() == 1){
					action = new Eat(this, map, (SourceFood)proximitySourceFood.get(0));
				}
				else{
					action = new Move(this, map, proximitySourceFood.get(0));
				}
			}
			else{
				Square destination = destinationChoice(map);
				action = new Move(this, map, destination);
			}
		}
		else{
			Square destination = destinationChoice(map);
			action = new Move(this, map, destination);
		}
		
		
	}
	
	/**
	 * Permet de choisir un square de destination où va se diriger la souris
	 * @return un square de destination walkable et dans la map
	 */
	public Square destinationChoice(Map map){
		
		int currentX, currentY; // coordonnées de la souris
		int destX, destY; // coordonnées de destination
		Square destination;
		
		do{
			do{
				currentX = Random.rand(-vision, vision);
				currentY = Random.rand(-vision, vision);
				destX = getX() + currentX;
				destY = getY() + currentY;
			}while(destX < 0 || destY < 0 || destX > map.getWidth() - 1 || destY > map.getHeight() - 1); // tant que la case de destination n'est pas incluse à la carte
			destination = map.getTab()[destX][destY];
		}while(destination.isWalkable()==false); // tant que la case de destination choisi aléatoirement n'est pas walkable
	
		return destination;
	}
	
	
	/**
	 * Méthode qui retourne les Square dans le champs de vision de la souris
	 * @return
	 */
	public void see(Map map){
		
		proximitySquare = new ArrayList<Square>(); // ArrayList des squares à proximité de la souris
		
		for(int i = -vision ; i <= vision ; i++){ 
			if(i < 0){
				for(int j = -(vision + i) ; j <= vision + i ; j++){ // Champs de vision à gauche de la souris
					if (getX()+i > 0 && getX()+i < map.getWidth() && getY()+j > 0 && getY()+j < map.getHeight()){
						proximitySquare.add(map.getTab()[getX() + i][getY() + j]);
					}
				}
			}
			else{
				for(int j = -(vision - i) ; j <= vision - i ; j++){ // Champs de vision à droite de la souris
					if (getX()+i > 0 && getX()+i < map.getWidth() && getY()+j > 0 && getY()+j < map.getHeight() && (i != 0 || j != 0)){
						proximitySquare.add(map.getTab()[getX() + i][getY() + j]);
					}
				}
			}	
		}	
		
	}
	
	
	/**
	 * Met à jour les attributs de la souris chaque tour
	 */
	public void update(Map map){
		
		if(alive){
			age++;
			if (food > 0) food--;
			
			if(food == 0 && food < Parameters.STARTING_FOOD){
				if(health > 0){
					health--;
				}
				else{
					alive = false;
				}
			}
			else{
				if(health < Parameters.MAX_HEALTH){
					health++;
				}
			}
			
			if(age == Parameters.MAX_AGE){
				alive = false;
			}
			
		}
		else{
			for(int index = 0 ; index < map.getMouseAliveArray().size() ; index++){
				Mouse mouse = map.getMouseAliveArray().get(index);
				if(mouse.getX() == getX() && mouse.getY() == getY()){
					map.getMouseAliveArray().remove(index); // On retire la souris de la liste des souris vivantes de la map
				}
			}
			map.getMouseDeathArray().add(this); // On l'ajoute à la liste des souris mortes de la carte
			map.output(this); // On la retire de la carte
		}
			
	}
	
	
	public void incrementFood(){ // Car une unité de nourriture permet à la souris de vivre pendant un certain nombre de tours
		food += Parameters.FOOD_NUMBER_TURN_SATISFACTION;
	}
	
	public void decrementFood(){
		food--;
	}
	

	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSexe() {
		return sexe;
	}

	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public int getVision() {
		return vision;
	}


	public void setVision(int vision) {
		this.vision = vision;
	}


	public int getCooperationDegree() {
		return cooperationDegree;
	}


	public void setCooperationDegree(int cooperationDegree) {
		this.cooperationDegree = cooperationDegree;
	}


	public int getTrustDegree() {
		return trustDegree;
	}


	public void setTrustDegree(int trustDegree) {
		this.trustDegree = trustDegree;
	}

	public boolean isLife() {
		return alive;
	}

	public void setLife(boolean life) {
		this.alive = life;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}
	
	public int getFood(){
		return food;
	}
	
	public String toString(){
		return "[ Name = " + name + ", Age = " + age + ", Sexe = " + sexe + ", Health = " + health + ", Food = " + food + " ]";
	}
	
	
}
