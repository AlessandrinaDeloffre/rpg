package rpg;

import java.util.ArrayList;
//import java.util.Scanner;

public class Player extends Entity{
	protected Weapon weapon;
	protected ArrayList<Weapon> inventory;
	protected int exp;
	protected int level;
	protected int gold;
	protected String cast;

	public Player(String name) {
		super(name);
		setPv();
		this.weapon=null;
		this.exp=0;
		this.level=0;
		this.gold=100;
		this.inventory=new ArrayList<Weapon>();
		this.cast=null;
	}
	
	public void setPv() {
		this.pv = 100+10*this.level;
	}
	
	public ArrayList<Weapon> getInventory(){
		return this.inventory;
	}
	
	public void winFight(Enemy e) {
		this.gold += 10 + e.getGoldGiven();
		this.exp+= this.pv;
		levelUp();
	}
	
	public void discoversNewCase() {
		this.gold+=20;
	}
	
	public void levelUp() {
		if(this.exp>=100) {
			this.level++;
			this.exp=0;
			setPv();
			System.out.println("Bravo ! Vous passez au niveau "+this.level);
		}
	}
	
	public void hasDiscovered() {
		this.exp+=20;
	}
	
	public void upGold(int gold) {
		this.gold += gold;
	}
	public void showInventory() {
		for(int i=0; i<this.inventory.size();i++) {
			if(this.inventory.get(i)==this.weapon) {
				System.out.println((i+1)+" - "+this.inventory.get(i).getName()+" *");
			} else {
				System.out.println((i+1)+" - "+this.inventory.get(i).getName());
			}
		}
	}


	
	public void chooseWeapon(int choice) {
		this.weapon= this.inventory.get(choice-1);
	}
	
	public void buyWeapon(WeaponStore store, int choice) {
	
		if(store.getWeapon(choice).getPrice()<=this.gold) {
			this.inventory.add(store.getWeapon(choice));
			this.gold -= store.getPrice(store.getWeapon(choice));
			if(this.weapon==null) {
				System.out.println("C'est votre première arme : nous vous équipons automatiquement");
				chooseWeapon(1);
			} else {
				System.out.println("Nouvel achat : "+store.getWeapon(choice).getName()+" ! Visiter votre inventaire pour vous équiper");
				//showInventory();
			}
		} else {
			System.out.println("Désolée cette arme est trop chère pour vous");
		}
		
	}
	
	public void attack(Entity e) {
		e.getAttacked(this.weapon.damage);
	}
	
	public void useWeapon(Entity e) {
		e.getAttacked(this.weapon.damage);
	}
	
	
	
	

}
