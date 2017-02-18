package map;

import test.Parameters;

public class Flower extends Square {

	public Flower(int x, int y) {
		super(x, y);
		super.walkable = true;
		super.type = Parameters.FLOWER_ATTRIBUTE;
		super.icon = Parameters.FLOWER_ICON;
	}
	
}
