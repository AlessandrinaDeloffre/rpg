package rpg;

public class Axe extends Weapon {
	private static final int DAMAGE = 30;
	private static final String NAME = "Axe";
	public Axe() {
		super(DAMAGE, NAME);
	
		}
	
	public String ascii_art() {
		//a remplacer par bon art
		return "                                        \\`.   \r\n"
				+ "              .--------------.___________) \\      \r\n"
				+ "              |//////////////|___________[ ]      \r\n"
				+ "              `--------------'           ) (       \r\n"
				+ "                                         '-'      \r\n"
				;
	}

}
