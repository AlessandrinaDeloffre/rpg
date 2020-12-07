package rpg;

public class Troll extends Enemy {

	public Troll() {
		super.pv=60;
		super.name = "Troll";
		super.damage=40;
		super.goldGiven=30;
	}

	public int getDamage() {return this.damage;}
	
	public int getPv() {return this.pv;}
	
	public String getName() {return this.name;}
	
	public int getGoldGiven() {return this.goldGiven;}
	
}
