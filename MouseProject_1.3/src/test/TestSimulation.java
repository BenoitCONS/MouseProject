package test;

import action.Move;
import action.Action;
import core.MapBuilder;
import map.Map;
import map.Mouse;
import map.Square;
import utility.*;

public class TestSimulation {

	public static void main(String[] args) {

		try {
			MapBuilder builderMap = new MapBuilder(); // Lecture et interprétation de la carte sélectionné dans les cartes prédéfinis
			Map map = builderMap.buildMap();
			

			for (int turn = 0; turn < Parameters.MAX_TURN; turn++) {
				java.lang.Thread.sleep(Parameters.VELOCITY);
				System.out.println("Turn : " + turn);
				System.out.println(map.statisticData()); // affiche les données statistiques de la carte
				System.out.println(map);

				if(map.getMouseAliveArray().size() > 0){
					for (int i = 0; i < map.getNumberMouseAlive() ; i++) { // toutes les souris en vie sur la carte vont faire une action
						map.getMouseAliveArray().get(i).doAction(map);
						map.getMouseAliveArray().get(i).update(map); // met à jour des attributs des souris
					}
				}
			}
			
			if(map.getMouseAliveArray().size() > 0){
				for (int i = 0; i < map.getNumberMouseAlive() ; i++) { 
					System.out.println(map.getMouseAliveArray().get(i).toString());
				}
			}
			

		} catch (InterruptedException ex) {
			System.out.println(ex.getMessage());
		}
	}
}
