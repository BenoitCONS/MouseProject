package action;

import java.util.ArrayList;
import core.Node;
import core.Pathfinding;
import map.Map;
import map.Mouse;
import map.Square;
import utility.Random;

public class Move implements Action{
	
	private ArrayList<Node> nodePath; // Chemin en cours
	private Square destination; // Square de destination
	private Square next; // Prochain square sur lequelle va aller la souris
	
	
	public Move(Mouse mouse, Map map, Square destination){
		this.destination = destination;
		Pathfinding pathfinding = new Pathfinding(mouse.getCurrentSquare(), destination, map);
		nodePath = pathfinding.searchPath();
	}

	
	@Override
	public void doAction(Mouse mouse, Map map) {
		if (!nodePath.isEmpty()){
			int nextX = nodePath.get(0).getX();
			int nextY = nodePath.get(0).getY();
			next = map.getTab()[nextX][nextY];
			if (next.isWalkable()==true){ // la souris ne peut se déplacer que si le square est walkable
				map.output(mouse); // on retire la souris
		        mouse.setX(next.getX());
		        mouse.setY(next.getY());
		        map.input(mouse); // on insère la souris 
				nodePath.remove(nodePath.get(0));
			}
			else{ // sinon la souris arrête de poursuivre son chemin
				nodePath.clear();
			}	
		}
	}

	public boolean isFinished(){
		return nodePath.isEmpty();
	}

}

