package utility;

import java.util.ArrayList;
import java.util.Collections;
import java.lang.Math;
import core.Node;
import core.Pathfinding;
import map.Map;
import map.Mouse;
import map.Square;


public class ArraySort {
	
	private static ArrayList<Square> sortArray;

	/**
	 * Trie l'ArrayList en plaçant les Square les plus proches de la souris au début et les plus loin à la fin
	 * @param mouse
	 * @param array
	 * @return
	 */
	public static ArrayList<Square> forwardSort(Mouse mouse, ArrayList<Square> array, Map map){
		
		sortArray = new ArrayList<Square>();
		
		Square sqr;
		while(!array.isEmpty()){
			sqr = min(mouse, array, map);
			sortArray.add(sqr);
			array.remove(sqr);
		}

		return sortArray;
	}
	
	/**
	 * Retourne le Square de l'arrayList qui a la distance en nombre de case de déplacement la plus faible avec la souris
	 * @param mouse
	 * @param array
	 * @return
	 */
	public static Square min(Mouse mouse, ArrayList<Square> array, Map map){
		
		Square min = array.get(0);
		int minDist = (int)dist(mouse, min, map);	
		
		for (int index = 0 ; index < array.size() ; index++){
			if(dist(mouse, array.get(index), map) < minDist){
				min = array.get(index);
				minDist = (int)dist(mouse, array.get(index), map);
			}
		}
		array.remove(min);
		return min;
	}	
	
	/**
	 * Retourne la distance d'un Square avec la souris (en nombre de case de déplacement)
	 * @param mouse
	 * @param sqr
	 * @return
	 */
	public static int dist(Mouse mouse, Square sqr, Map map){
		return (int)Math.sqrt(Math.pow(sqr.getX() - mouse.getX(), 2) + Math.pow(sqr.getY() - mouse.getY(), 2));
	}
	
}
