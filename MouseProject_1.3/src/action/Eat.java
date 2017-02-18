package action;

import java.util.ArrayList;

import core.Node;
import core.Pathfinding;
import map.Map;
import map.Mouse;
import map.SourceFood;
import test.Parameters;

public class Eat implements Action{
	
	public Eat (Mouse mouse, Map map){
		doAction(mouse, map);
		sourceFood.decFoodLevel(map);
	}

	public void doAction(Mouse mouse, Map map) {
		mouse.incrementFood();
	}
	
	public boolean isFinished() {
		return true;
	}

}
