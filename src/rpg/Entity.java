package rpg;

public class Entity {
	protected String name;
	protected int pv;
	
	public Entity() {
		this.pv=0;
		this.name=null;
	}
	
	
	public Entity(String name) {
		this.name=name;
		this.pv=0;
	}
	
	public String getName() {return this.name;}
	
	public int getPv() {return this.pv;	}
	
	public void getAttacked(int dmg) {
		this.pv -= dmg;
	}
	
	public void attack(Entity e) {
		
	}
	
	
}
