package core;

import java.util.ArrayList;

public class Set {
	
	private ArrayList<Node> set;
	
	public Set (){
		set = new ArrayList<Node>();
	}
	
	public Node min(){
		
		Node min = set.get(0);
		double minWeight = set.get(0).getWeight();
		
		for (int index = 0 ; index < set.size() ; index++){
			if(set.get(index).getWeight() < minWeight && set.get(index).isWalkable()==true){
				min = set.get(index);
				minWeight = min.getWeight();
			}
		}
	
		set.remove(min);
		return min;
	}	
	
	
	public ArrayList<Node> getSet() {
		return set;
	}


}
