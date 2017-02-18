package map;

import test.Parameters;

public class SourceFood extends Square{
	
	private int foodLevel;
	
	public SourceFood(int x, int y) {
		super(x, y);
		super.walkable = false;
		super.type = Parameters.FOOD_ATTRIBUTE;
		super.icon = Parameters.FOOD_ICON;
		foodLevel = Parameters.STARTING_FOOD_LEVEL;
	}

	public int getFoodLevel() {
		return foodLevel;
	}

	public void decFoodLevel(Map map) {
		if (foodLevel > 0){
			foodLevel --;
		}
		else{
			for(int index = 0 ; index < map.getSourceFoodArray().size() ; index++){
				SourceFood sourceFood = map.getSourceFoodArray().get(index);
				if(sourceFood.getX() == getX() && sourceFood.getY() == getY()){
					map.getSourceFoodArray().remove(index); 
				}
			}
			map.output(this); // On retire la source de nourriture 
			map.input(new Floor(getX(), getY())); // On met à la place du sol
		}
	}

}
