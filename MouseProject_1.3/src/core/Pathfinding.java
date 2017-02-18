package core;

import java.util.ArrayList;

import map.Map;
import map.Mouse;
import map.Square;

/**
 * Algorithme de Dijkstra permettand de trouver le chemin le plus court entre deux points
 * 
 * @author Maxime
 *
 */
public class Pathfinding {
	
	private Node[][] nodes; // Tableaus des noeuds
	private Node[][] pred; // Tableaux des prédécesseurs
	private Set set; // Ensemble des noeuds traités
	private ArrayList<Node> nodePath; // Chemin trouvé
	
	private int width;
	private int height;
	
	private Square startSqr; // Square de départ
	private Square destSqr; // Square d'arrivée
	private Map map;
	
	
	public Pathfinding (Square startSqr, Square destSqr, Map map){
		this.startSqr = startSqr;
		this.destSqr = destSqr;
		this.map = map;
		width = map.getWidth();
		height = map.getHeight();
		nodes = new Node[width][height];
		pred = new Node[width][height];
		set = new Set();
		nodePath = new ArrayList<Node>();
	}
	
	/**
	 * Methode générale du pathfinding qui retourne le chemin sous forme d'une liste de noeuds
	 * 
	 * @param currentSquare
	 * @param destination
	 * @param map
	 */
	public ArrayList<Node> searchPath(){
		
		init(startSqr, map); //initialisation des tableaux et de l'ensemble
		Node node; // noeud courant traité
		
		while(nodes[destSqr.getX()][destSqr.getY()].isVisited()==false){ // tant que le noeud de destination n'est pas traité		
			node = set.min(); // on récupère le noeud ayant le poids minimum de l'ensemble
			node.setVisited(true);	
			update(node); // Traitement des noeuds voisins et ...
		}
		
		Node start = new Node(startSqr.getX(), startSqr.getY());
		Node end =  new Node(destSqr.getX(), destSqr.getY());
		nodePath = nodePath(start, end);
		return nodePath;
	}
	
	
	/**
	 * Chemin final qu'on obtient en lisant le tableau des prédecesseurs à l'envers en partant du noeud de destination jusqu'au noeud de départ
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	private ArrayList<Node> nodePath(Node start, Node end){
		
		ArrayList<Node> invertedPath = new ArrayList<Node>(); // Chemin inversé qui va du noeud de destination à celui de départ
		int x = end.getX();
		int y = end.getY();
		int i = 1;
		
		invertedPath.add(nodes[x][y]);
		
		while(pred[x][y] != nodes[x][y]){ // tant qu'on arrive pas au noeud de départ
			invertedPath.add(pred[x][y]);
			x = invertedPath.get(i).getX();
			y = invertedPath.get(i).getY();	
			i++;
		}
		
		for (int index = invertedPath.size() - 1 ; index >= 0 ; index--){ // on ajoute les éléments du chemin inversé dans le chemin des noeuds afin de les remettre dans le bon ordre
			nodePath.add((Node)invertedPath.get(index));
		}
		nodePath.remove(0); // Noeud de départ est inutile (la souris lors de son premier mouvement va rester sur place)
		
		return nodePath;
		
	}

	/**
	 * Initialisation des différents tableaux et de l'ensemble
	 * @param currentSquare
	 * @param map
	 */
	public void init(Square startSqr, Map map){
		
		for(int j = 0 ; j < height ; j++){
			for(int i = 0 ; i < width ; i++){
				if (i == startSqr.getX() && j == startSqr.getY()){
					nodes[i][j] = new Node(i, j, 0, map.getTab()[i][j].isWalkable());
					set.getSet().add(nodes[i][j]);
				}
				else{
					nodes[i][j] = new Node(i, j, 1, map.getTab()[i][j].isWalkable());
				}
				pred[i][j] = nodes[i][j];
			}
		}
	}
	
	
	/**
	 * Retourne les noeuds voisins d'un noeud
	 * @param node
	 * @return une ArrayList qui contient les noeuds voisins d'un noeud
	 */
	public ArrayList<Node> neighbours(Node node){
		
		int x = node.getX();
		int y = node.getY();
		ArrayList <Node> neighbours = new ArrayList<Node>(); 
		
		if(x > 0){
			if(nodes[x-1][y].isVisited()==false){
				neighbours.add(nodes[x-1][y]);
			}
		}
		if(x < width-1){
			if(nodes[x+1][y].isVisited()==false){
				neighbours.add(nodes[x+1][y]);
			}
		}
		if(y > 0){
			if(nodes[x][y-1].isVisited()==false){
				neighbours.add(nodes[x][y-1]);
			}
		}
		if(y < height-1){
			if(nodes[x][y+1].isVisited()==false){
				neighbours.add(nodes[x][y+1]);
			}
		}
		
		return neighbours;
	}
	
	
	/**
	 * 
	 * @param father
	 */
	public void update(Node father){
		
		Node children; // Noeud voisin du noeud père
		for(int index = 0 ; index < neighbours(father).size() ; index++){
			
			children = neighbours(father).get(index);
				
			if(children.isVisited()==false && father.getWeight() + children.getWeight() <= children.getWeight() || (children.getWeight() == 1)){	
				if (children.isWalkable()==true){
					children.setWeight(father.getWeight() + children.getWeight());
				}
				else{
					children.setWeight(father.getWeight() + children.getWeight() + 999); // Si c'est un obstacle on rajoute un poids très important pour que la case ne soit pas prise en compte
				}
				set.getSet().add(children);
				pred[children.getX()][children.getY()] = father;
			}
		}
	}
	
}
