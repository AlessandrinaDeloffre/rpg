package rpg;

public class Warrior extends Player{
	protected int strength;
	
	public Warrior(String name) {
		super(name);
		super.cast="Warrior";
		if(super.weapon!=null) {
			super.weapon.damage+=this.strength;
		}
		// TODO Auto-generated constructor stub
	}
	
	public void upgradeWeapon() {
		super.weapon.damage+=this.strength;
	}
	
	public void upgradeStrength() {
		this.strength+=10;
		super.gold-=100;
		upgradeWeapon();
	}

	public void levelUp() {
		if(this.exp>=100) {
			this.level++;
			this.exp=0;
			setPv();
			this.strength+=10;
			System.out.println("Bravo ! Vous passez au niveau "+this.level);
		}
	}
}
