package rpg;

public class Positions {
	
	protected int x;
	protected int y;
	protected boolean discovered;
	protected boolean isCurrent;
	protected Entity enemy;
	
	public Positions() {
		this.x=0;
		this.y=0;
		this.discovered=false;
		this.isCurrent=false;
		this.enemy=null;
	}
	
	public Positions(int x, int y) {
		this.x=x;
		this.y=y;
		this.discovered=false;
		this.isCurrent=false;
	}
	

	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	
	public boolean getDiscovered() {
		return this.discovered;
	}
	
	public boolean getCurrent() {
		return this.isCurrent;
	}
	
	public boolean hasEnemy() {
		if(this.enemy==null) {
			return false;
		} else {
			return true;
		}
	}
	
	public void setX(int x) {
		this.x=x;
	}
	
	public void setY(int y) {
		this.y=y;
	}
	
	public void addEnemy(Entity enemy) {
		this.enemy=enemy;
	}
	
	public void supprEnemy() {
		this.enemy=null;
	}
	
	public void isDiscovered() {
		this.discovered=true;
	}
	
	public void setIsCurrent(boolean isCurrent) {
		this.isCurrent=isCurrent;
		if(isCurrent==true) {
			this.isDiscovered();
		}
	}
	

	

}
