package map;

import test.Parameters;

public class Rock extends Square{

	public Rock(int x, int y) {
		super(x, y);
		super.walkable = false;
		super.type = Parameters.ROCK_ATTRIBUTE;
		super.icon = Parameters.ROCK_ICON;
	}

}
