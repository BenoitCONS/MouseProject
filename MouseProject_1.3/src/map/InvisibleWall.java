package map;

import test.Parameters;

public class InvisibleWall extends Square {
	
	public InvisibleWall(int x, int y) {
		super(x, y);
		super.walkable = false;
		super.type = Parameters.INVISIBLE_WALL_ATTRIBUTE;
		super.icon = Parameters.INVISIBLE_WALL_ICON_DISPLAY;
	}
}
