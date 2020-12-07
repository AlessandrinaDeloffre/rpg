package rpg;

public class Ghost extends Enemy {
	public Ghost() {
		super.pv=150;
		super.name = "Ghost";
		super.damage=15;
		super.goldGiven=60;
	}
	
	public int getDamage() {return this.damage;}
	
	public int getPv() {return this.pv;}
	
	public String getName() {return this.name;}
	
	public int getGoldGiven() {return this.goldGiven;}
	

}
