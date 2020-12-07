package rpg;
import java.util.Scanner;

public class Wizard extends Player{
	protected int spell;
	protected int energy;
	
	
	public Wizard(String name) {
		super(name);
		super.cast="Wizard";
		this.spell=60;
		this.energy=1+super.level;
		// TODO Auto-generated constructor stub
	}
	
	public boolean useSpell(Entity e, Scanner scanner) {
		if(this.energy>0) {
			e.getAttacked(this.spell);
			this.energy--;
			return true;
		}  else {
			System.out.println("Vous n'avez pas assez d'énergie !");
			if(super.gold>=100) {
				System.out.println("Restaurez votre énergie ? (y/n)");
				String choiceStr = scanner.nextLine();
				if (choiceStr=="y") {
					restoreEnergy();
				}
			}
			System.out.println("Désolé vous n'avez pas assez d'or pour restaure votre énergie, utilisez votre arme !");
			return false;
		}
	}
	
	public void restoreEnergy() {
		this.energy=1+super.level;
		super.gold-=100;
	}
	
	public void levelUp() {
		if(this.exp>=100) {
			super.level++;
			super.exp=0;
			super.setPv();
			this.energy=1+super.level;
			System.out.println("Bravo ! Vous passez au niveau "+this.level);
		} 
	}

}
