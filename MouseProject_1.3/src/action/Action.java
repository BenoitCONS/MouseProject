package action;

import square.Mouse;
import map.Map;

public interface Action {
	
	public abstract void doAction(Mouse mouse, Map map);
	public abstract boolean isFinished();
}
