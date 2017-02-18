package action;

import map.Map;
import map.Mouse;

public class Communicate implements Action{
	
	public Communicate(Mouse mouse, Map map){
		doAction(mouse, map);
	}

	@Override
	public void doAction(Mouse mouse, Map map) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isFinished() {
		return true;
	}
}
