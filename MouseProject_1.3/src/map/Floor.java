package map;

import test.Parameters;

public class Floor extends Square{

	public Floor(int x, int y) {
		super(x, y);
		super.walkable = true;
		super.type = Parameters.FLOOR_ATTRIBUTE;
		super.icon = Parameters.FLOOR_ICON;
	}
	
}
