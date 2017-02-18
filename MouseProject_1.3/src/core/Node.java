package core;

public class Node{

	private int x;
	private int y;
	private int weight;
	private boolean visited;
	private boolean walkable;
	
	public Node(int x, int y, int weight, boolean walkable) {
		this.x = x;
		this.y = y;
		this.weight = weight;
		visited = false;
		this.walkable = walkable;
	}

	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public boolean isVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public boolean isWalkable() {
		return walkable;
	}

	public void setWalkable(boolean walkable) {
		this.walkable = walkable;
	}
	
}
