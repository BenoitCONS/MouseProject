package map;

import test.Parameters;

public class Wall extends Square {

	public Wall(int x, int y) {
		super(x, y);
		super.walkable = false;
		super.type = Parameters.WALL_ATTRIBUTE;
		super.icon = Parameters.WALL_ICON;
	}

}
