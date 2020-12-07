package rpg;

import java.util.ArrayList;

public class Map {
	protected ArrayList<Positions> cases;
	protected ArrayList<Entity> entities;
	protected int size;
	
	public Map(int size) {
		this.size=size;
		this.entities = new ArrayList<Entity>();
		this.cases= new ArrayList<Positions>();
		
		
		this.cases.add(0, new Positions(0,2));
		this.cases.get(0).isDiscovered();
		this.cases.get(0).setIsCurrent(true);
		for(int i=0;i<size*size;i=i+size) {
			if(i!=0) {
				this.cases.add(i, new Positions(this.cases.get(0).getX(),this.cases.get(i-size).getY()-1));
			}
			for(int y=1; y<size;y++) {
				this.cases.add(i+y, new Positions(this.cases.get(i+y-1).getX()+1, this.cases.get(i).getY()));
			}
		}
	}
	
	public void addPlayer(Player p) {
	
			//ajout du player sur la case 1 en début de jeu
		this.entities.add(0, p);
		
	}
	
	public void addEnemy(Enemy e ) {
			this.entities.add(e);
			int random = 0;
			do {
				random = (int) (Math.random() * (this.size*this.size-1)+1);
			} while(this.cases.get(random).hasEnemy()==true || random==0);
			this.cases.get(random).addEnemy(e);
	}
	
	public void addObstacle(Obstacle o ) {
		this.entities.add(o);
		int random = 0;
		do {
			random = (int) (Math.random() * (this.size*this.size-1)+1);
		} while(this.cases.get(random).hasEnemy()==true || random==0);
		this.cases.get(random).addEnemy(o);
}
	
/*	public void addEntity(Entity e, boolean isRandom) {
		//random between 1 and last case index
		//int random = (int) (Math.random() * (this.size*this.size)+1);
	
		if(isRandom==false) {
			//ajout du player sur la case 1 en début de jeu
		this.entities.add(0, e);
		} else {
			this.entities.add(e);
			/*if(this.cases.get(random).hasEnemy()==false) {
			this.cases.get(random).addEnemy(e);
			} */
			//int random = 0;
		//	do {
				//random = (int) (Math.random() * (this.size*this.size-1)+1);
			//} while(this.cases.get(random).hasEnemy()==true || random==0);
			//this.cases.get(random).addEnemy(e);
			//A FAIRE
			//this.entities.add(random, e);
		//}
	//}
	
	public int randomInt() {
		return (int) (Math.random() * (this.size*this.size)+1);
	}
	
	public void afficherPositions() {
		for(int i=0; i<size*size;i++) {
			System.out.println(this.cases.get(i).x+" , "+this.cases.get(i).y);
		}
	}
		
	public Positions getCaseAtIndex(int index) {
			return this.cases.get(index);
	}
	
	public int getCurrentCase() {
		int currentIndex = -1;
		for(int i=0; i<this.size*this.size;i++) {
			if (this.cases.get(i).getCurrent()==true) {
				currentIndex=i;
			} 
		}
		return currentIndex;
	}
	
	public void showMap() {
		int currentIndex = this.getCurrentCase();
		System.out.println("===MAP===");
		for(int i=size*size-size;i>=0;i=i-size) {
			for(int y=0;y<size;y++) {
				if(i+y==i+size-1) {
					if((i+y)==currentIndex) {
						System.out.println(" ☺ ");
					} else {
						if(this.cases.get(i+y).getDiscovered()==false) {
							System.out.println(" ◙ ");
						} else {
							System.out.println(" ○ ");
						}
					}
					//System.out.println("("+(i+y)+")");
				} else {
					if((i+y)==currentIndex) {
						System.out.print(" ☺ ");
					} else {
						if(this.cases.get(i+y).getDiscovered()==false) {
							System.out.print(" ◙ ");
						} else {
							System.out.print(" ○ ");
						}
					}
					//System.out.print("("+(i+y)+")");
				}
			}
		}
		System.out.println("=========");
	}
	
}
