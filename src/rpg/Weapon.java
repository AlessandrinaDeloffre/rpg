package rpg;

abstract class Weapon {
	protected int damage;
	protected String name;
	protected String art;
	protected int price;
	public Weapon ( int damage, String name ) {
		this.damage = damage;
		this.name=name;
		//this.price = this.damage * 10;
		this.art = "";
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getPrice() {
		return this.price;
	}
	
	public abstract String ascii_art();
	//public abstract void attackMonster(Monster m);
	//public abstract void attackObstacle(Obstacle o);
}
