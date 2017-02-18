package map;

public  class Square {

    private int x;
    private int y;
    protected boolean walkable; // variable qui n'est défini que dans les classes filles
    protected String type; // idem
    protected String icon; // idem
    private Square instance = this;

    public Square(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public Square getCurrentSquare(){
    	return this.instance;
    }
    
    public void setCurrentSquare(Square currentSquare){
    	this.instance = currentSquare;
    }

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean isWalkable() {
		return walkable;
	}

	public void setWalkable(boolean walkable) {
		this.walkable = walkable;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}


	public String getType() {
		return type;
	}


	public String getIcon() {
		return icon;
	}
	
	
}
