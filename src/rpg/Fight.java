package rpg;

import java.util.Random;
import java.util.Scanner;

public class Fight {
	private static int choiceInt;
	private static String choiceStr;
	boolean win;
	
	public Fight(Scanner scanner, Entity e, Player p) {
		startFight(scanner, e, p);
			
		
	}
	
	public void startFight(Scanner scanner, Entity e, Player p) {
		System.out.println("Que souhaitez vous faire ? (1) Attaquer ? | (2) Fuir ?");
		while(!scanner.hasNextInt()) {
		       scanner.next();
		   }
		choiceInt = scanner.nextInt();
		if(choiceInt==1) {
			onGoingFight(scanner,p,e);
		
		} else if (choiceInt==2) {
			this.win=false;
		} else {
			startFight(scanner, e, p);
		}
	}
	
	public void wizardAttack(Scanner scanner, Player p, Entity e) {
		System.out.println("(1) Attaquer avec mon arme | (2) Lancer un sort");
		while(!scanner.hasNextInt()) {
		       scanner.next();
		   }
		choiceInt = scanner.nextInt();
		
		if(choiceInt==1) {
			p.attack(e);
		
		} else if (choiceInt==2) {
			if(!((Wizard) p).useSpell(e, scanner)) {
				wizardAttack(scanner, p, e);
			}
		} else {
			wizardAttack(scanner, p, e);
		}
	}
	
	public void onGoingFight(Scanner scanner, Player p, Entity e) {
		if(p.cast=="Wizard") {
			wizardAttack(scanner, p, e);
		} else {
			p.attack(e);
		}
		System.out.println(p.name+" attaque "+e.name);
		if(checkPv(e)==true) {
			System.out.println(e.name+" attaque "+p.name);
			e.attack(p);
			if(checkPv(p)==true) {
				System.out.println(p.name+" : "+p.getPv()+" | "+e.name+" : "+e.getPv());
				startFight(scanner, e, p);
			} else {
				this.win=false;
				System.out.println("Vous avez perdu...");
			}
		} else {
			this.win=true;
			System.out.println("Vous avez gagné !");
		}
		
	}
	
	public boolean checkPv(Entity e) {
		if(e.getPv()>0) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean whoStarts() {
		Random random = new Random();
		return random.nextBoolean();
		
	}

}
