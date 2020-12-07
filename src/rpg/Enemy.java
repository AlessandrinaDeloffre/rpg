package rpg;

public class Enemy extends Entity{
	protected int damage;
	protected int goldGiven;
	
	public Enemy() {
		super.pv=0;
		super.name="Enemy";
	}
	
	public void attack(Entity e) {
		e.getAttacked(this.damage);
	}
	
	public int getDamage() {return this.damage;}
	

	

	
	public int getGoldGiven() {return this.goldGiven;}

}
