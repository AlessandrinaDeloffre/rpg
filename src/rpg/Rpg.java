package rpg;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Rpg {

	private static int choiceInt;
	private static String choiceStr;
	private static String pseudo;
	private static int cast;
	private static int sizeMap;
	private static boolean endGame = false;
	private static Map map;
	private static Player player;

	public static void main(String[] args) {
		game();
		
	}
	
	public static void game() {
		Scanner scannerInit = new Scanner( System.in );
		WeaponStore store = new WeaponStore() ; // WeaponStore
		//	store.showStock();
		//	System.out.println(store.getWeapon(1).getName());
		
		startGame(scannerInit);
		
		System.out.println("Les règles du jeu");
		System.out.println("Vous devez explorez la carte en vous déplaçant de case en case, la sortie étant située en haut à droite");
		System.out.println("Au cours de votre exploration, vous allez rencontrer des ennemis et des obstacles. \nCombattre ou esquiver, à vous de choisir la stratégie !");
		System.out.println("En explorant et en gagnant des combats, vous pourrez gagner de l'or, de l'expérience et passer des niveaux !");

		System.out.println("Vous commencez le jeu avec 100 pièces d'or, cela vous permet d'acheter une première arme !");
		showWeaponStore(scannerInit, player, store);
		
	
		//map.afficherPositions();
	//	System.out.println(map.entities.indexOf(enemy));
		//System.out.println(map.entities.get(map.entities.indexOf(enemy)).name);
	
//		System.out.println(map.entities.get(1).name);
		//System.out.println(map)
		//System.out.println(map.getCaseAtIndex(0).getX());
		//System.out.println(map.getCaseAtIndex(0).getY());
		//map.showMap();
	
	
		//player.upGold(5000);
		do {
			System.out.println(player.name+" (PV) "+player.getPv()+" (OR) "+player.gold+" (EXP) "+player.exp+" (LEVEL) "+player.level);
			if(player.cast=="Wizard") {
				System.out.println("* Mage * (ENERGY) "+((Wizard) player).energy);
			} else if (player.cast=="Warrior") {
				System.out.println("* Guerrier * (STRENGTH) "+((Warrior) player).strength);
			}
			System.out.println( "Voulez vous : (1) Visiter le magasin ? | (2) Voir votre inventaire ? \n              (3) Avancer ?            | (4) Arrêter la partie ? " );
			if(player.cast=="Wizard") {
				System.out.println("(5) Restaurez votre énergie ? (100 pièces)");
			} else if (player.cast=="Warrior") {
				System.out.println("(5) Augmenter votre force ? (100 pièces)");
			}
			while(!scannerInit.hasNextInt()) {
			       scannerInit.next();
			   }
			choiceInt = scannerInit.nextInt();
			Scanner scanner = new Scanner( System.in );
			scanner.useDelimiter(System.lineSeparator());

			if(choiceInt==1) {
				showWeaponStore(scanner, player, store);
			} else if (choiceInt==2) {
				showInventory(scanner, player);
			} else if (choiceInt==3) {
				moveToQuestion(scanner, player, map);
				if(map.getCaseAtIndex(map.getCurrentCase()).hasEnemy()==true) {
					if(map.getCaseAtIndex(map.getCurrentCase()).enemy.getClass()==Obstacle.class) {
						fightObstacle(scanner, map.getCaseAtIndex(map.getCurrentCase()).enemy, player);
						
						
					} else {
						System.out.println("COMBAT");
						Fight fight = new Fight(scanner, map.getCaseAtIndex(map.getCurrentCase()).enemy, player );
						if(fight.win==true) {
							player.winFight((Enemy) map.getCaseAtIndex(map.getCurrentCase()).enemy);
							map.getCaseAtIndex(map.getCurrentCase()).supprEnemy();
							player.discoversNewCase();
						} else {
							reculer();
							if(player.pv==0) {
							isDead(scanner, player, map);
							}
						}
					}
				} else {
					player.discoversNewCase();
				}
				//a faire
			} else if (choiceInt==4) {
				endGame=true;
				System.out.println("Voulez vous recommencer ? y/n");
				 while(!scannerInit.hasNext()) {
				       scannerInit.next();
				   }
				choiceStr=scannerInit.next();
				System.out.println(choiceStr);
				if(choiceStr.equals("y")) {
					game();
				} else {
					System.out.println("Merci d'avoir joué !");
				}
			} else if (choiceInt==5) {
				if(player.cast=="Wizard") {
					((Wizard) player).restoreEnergy();
				} else if (player.cast=="Warrior") {
					((Warrior) player).upgradeStrength();
				}
			} else {
				System.out.println( "Saisie incorrecte" );
			}
		} while(endGame==false);
	}
	
	public static void reculer() {
		if (choiceStr=="H") {choiceStr="B";} else
		if (choiceStr=="B") {choiceStr="H";} else
		if (choiceStr=="D") {choiceStr="G";} else
		if (choiceStr=="G") {choiceStr="D";}
		moveToChoice(map, choiceStr);
	}
	
	public static void fightObstacle(Scanner scanner, Entity e, Player p) {
		if(e.pv>player.weapon.damage*2.5) {
			System.out.println("ATTENTION OBSTACLE ! Votre arme est trop faible, vous reculez ! Changez d'arme avant d'essayer à nouveau ou contournez l'obstacle");
			reculer();
		} else {
			System.out.println("ATTENTION OBSTACLE ! Que voulez vous faire ? (1) Détruire (2) Reculer");
			 while(!scanner.hasNextInt()) {
			       scanner.next();
			   }
			choiceInt = scanner.nextInt();
			if(choiceInt==1) {
				int compteur=0;
				while(e.pv>0) {
					e.getAttacked(p.weapon.damage);
					compteur++;
				}
				player.gold+=30/compteur;//pour avoir détruit l'obstacle
				player.discoversNewCase();
				player.exp+=20/compteur;
				player.levelUp();
				
			} else if (choiceInt==2) {
				reculer();
			} else {
				System.out.println("Saisie incorrecte");
				fightObstacle(scanner, e, p);
			}
	
		}
	}
	
	public static void chooseCast (Scanner scannerInit){
		System.out.println("Choisissez votre caste : (1) Mage | (2) Guerrier ");
		System.out.println("Les mages peuvent combattre leurs ennemis avec des sorts ! Ces sorts sont puissants mais recquierent de l'énergie !");
		System.out.println("Vous pouvez restaurer votre énergie contre 100 pièces d'or ou attendre de passer un niveau");
		System.out.println("Attention vos sorts ne serez d'aucune utilité contre les obstacles !");
		
		System.out.println("Les guerriers ont une force surhumaine qui augmentent la puissance de leur arme");
		System.out.println("Vous pouvez augmenter votre force contre 100 pièces d'or ou attendre de passer un niveau");
		
		
		while(!scannerInit.hasNextInt()) {
		       scannerInit.next();
		   }
		cast = scannerInit.nextInt();
		if(cast==1) {
			player = new Wizard(pseudo);
		} else if (cast==2) {
			player = new Warrior(pseudo);
			((Warrior) player).upgradeWeapon();
		} else {
			System.out.println("Saisie incorrecte");
			chooseCast(scannerInit);
		}
	}
	
	public static void startGame(Scanner scannerInit) {
		System.out.println("Bienvenue dans l'aventure ! Avant de commencer, choisissez votre pseudo : ");
		pseudo = scannerInit.nextLine();
		chooseCast(scannerInit);
		chooseMapSize(scannerInit);
		map = new Map(sizeMap);
		map.addPlayer(player);
		chooseDifficulty(scannerInit, map);
		for(int i=0; i<map.size*map.size;i++) {
			if(map.getCaseAtIndex(i).enemy!=null) {
				System.out.println("index enemi : "+i);
			}
			//System.out.println(map.getCaseAtIndex(i).enemy);
		}
		map.showMap();		
		System.out.println("Bonne aventure !");
	}
	
	public static void chooseDifficulty(Scanner scannerInit, Map map) {
		System.out.println("Choisissez votre niveau de difficulté ? Il determine le nombre d'ennemis présent sur la carte\n(1) Facile | (2) Moyen | (3) Difficile");
		
		 while(!scannerInit.hasNextInt()) {
		       scannerInit.next();
		   }
		choiceInt = scannerInit.nextInt();
		//scannerInit.next();	
		if(choiceInt>3) {
			chooseDifficulty(scannerInit, map);
		} else {
			int difficulty=(sizeMap*sizeMap-1)*25/100;
			if(choiceInt==1) {
				difficulty = (sizeMap*sizeMap-1)*15/100;
				System.out.println("Diff : "+difficulty);
				
			} else if(choiceInt==2) {
				difficulty = (sizeMap*sizeMap-1)*23/100;
				System.out.println("Diff : "+difficulty);
			} else if(choiceInt==3) {
				difficulty = (sizeMap*sizeMap-1)*30/100;
				System.out.println(sizeMap);
				System.out.println("Diff : "+difficulty);
			}
			for(int i=0; i<difficulty; i++) {
				map.addEnemy(new Troll());
				map.addObstacle(new Obstacle());
				if(sizeMap!=3 || choiceInt==2) {
				map.addEnemy(new Ghost());	
				}
			}
		}
	
	}
	
	public static void chooseMapSize(Scanner scannerInit) {
		System.out.println("Sur quelle type de carte voulez vous jouer ? (1) Petite | (2) Moyenne | (3) Grande");
		
		 while(!scannerInit.hasNextInt()) {
		       scannerInit.next();
		   }
		choiceInt = scannerInit.nextInt();
		//scannerInit.next();
		if(choiceInt==1) {sizeMap=3;} else
		if(choiceInt==2) {sizeMap=4;} else
		if(choiceInt==3) {sizeMap=5;} else {chooseMapSize(scannerInit);}
	}
	
	public static void isDead(Scanner scanner, Player p, Map m) {
		System.out.println("Vous êtes mort ! Voulez vous : (1) Ressuciter ? (100 gold) | (2) Recommencer ? | (3) Abandonner ?");
		while(!scanner.hasNextInt()) {
		     //  System.out.print("This app doesn't accept letters or symbols: ");
		       scanner.next();
		   }
		choiceInt = scanner.nextInt();
		if(choiceInt==1) {
			if(p.gold>=100) {
				p.setPv();
				p.gold-=100;
			} else {
				System.out.println("Désolé mais vous n'avez pas assez d'argent");
				isDead(scanner, p, m);
			}
		} else if (choiceInt==2) {
			//A CHANGER
			player=new Player(pseudo);
			map = new Map(3);
			
		}else if (choiceInt==3) {
			endGame=true;
		} else {
			System.out.println("Saisie incorrecte");
			isDead(scanner, p, m);
		}
	}
	
	public static void moveToQuestion(Scanner scanner, Player player, Map map) {
		int x = map.getCaseAtIndex(map.getCurrentCase()).getX();
		int y = map.getCaseAtIndex(map.getCurrentCase()).getY();
		map.showMap();
		System.out.println("Où voulez vous allez :");
		if(x==map.size-1 && y==0) {
			System.out.println("D pour droite : attention fin du jeu !");
		} else {
		if(x!=map.size-1) {System.out.println("D pour droite");}
		}
		if(x!=0) {System.out.println("G pour gauche");}
		if(y!=map.size-1) {System.out.println(y);System.out.println("B pour bas");}
		if(y!=0) {System.out.println("H pour haut");}
		choiceStr = scanner.nextLine();
		moveToChoice(map, choiceStr);
		map.showMap();
		
	}
	
	public static void moveToChoice(Map map, String choice) {
		int currentCaseIndex = map.getCurrentCase();
		if(choice.equals("D")) {
			map.getCaseAtIndex(currentCaseIndex).setIsCurrent(false);
			map.getCaseAtIndex(currentCaseIndex+1).setIsCurrent(true);
		} else if (choice.equals("G")) {
			map.getCaseAtIndex(currentCaseIndex).setIsCurrent(false);
			map.getCaseAtIndex(currentCaseIndex-1).setIsCurrent(true);
		} else if (choice.equals("B")) {
			map.getCaseAtIndex(currentCaseIndex).setIsCurrent(false);
			map.getCaseAtIndex(currentCaseIndex-3).setIsCurrent(true);
		} else if (choice.equals("H")) {
			map.getCaseAtIndex(currentCaseIndex).setIsCurrent(false);
			map.getCaseAtIndex(currentCaseIndex+3).setIsCurrent(true);
		} 
	}

	public static void showInventory(Scanner scanner, Player player) {
		player.showInventory();
		
		System.out.print( "Voulez vous changer d'équipement ? (y/n)" );
		choiceStr = scanner.nextLine();
		if(choiceStr.equals("y")) {
			if(player.getInventory().size()==0) {
				System.out.println( "Votre inventaire est vide, visiter le magasin " );
			} else {
				System.out.print( "Veuillez saisir le numéro de l'arme avec laquelle vous voulez vous équipez : " );
				 while(!scanner.hasNextInt()) {
				       scanner.next();
				   }
				choiceInt = scanner.nextInt();
				player.chooseWeapon(choiceInt);
				System.out.println("Bravo vous êtes désormais équipé d'une "+player.getInventory().get(choiceInt-1).getName());
			}
		} else if (choiceStr.equals("n")) {
			System.out.println("Vous sortez de votre inventaire");
		} else {
			System.out.println("Saisie incorrecte : veuillez recommencer");
		}
		
		
	}
	
	public static void showWeaponStore(Scanner scanner, Player player, WeaponStore store ) {
		store.showStock();
		System.out.print( "Voulez vous acheter une arme ? (y/n)" );
		 while(!scanner.hasNext()) {
		       scanner.next();
		   }

		choiceStr = scanner.next();
		System.out.println("choix : "+choiceStr);
		if(choiceStr.equals("y")) {
		System.out.print( "Veuillez saisir le numéro de l'arme que vous voulez acheter : " );
		 while(!scanner.hasNextInt()) {
		       scanner.next();
		   }
		choiceInt = scanner.nextInt();
		

		player.buyWeapon(store, choiceInt);
		} else if (choiceStr.equals("n")) {
			System.out.println("Vous sortez du magasin");
		} else {
			System.out.println("Saisie incorrecte : veuillez recommencer");
		}
		

	}
	
	
}
