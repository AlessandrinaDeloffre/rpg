package rpg;

public class Knife extends Weapon {
	private static final int DAMAGE = 20;
	private static final String NAME = "Knife";
	public Knife() {
		super(DAMAGE, NAME);
		
				
	}
	public String ascii_art() {
		return "                                        \\`.   \r\n"
				+ "              .--------------.___________) \\      \r\n"
				+ "              |//////////////|___________[ ]      \r\n"
				+ "              `--------------'           ) (       \r\n"
				+ "                                         '-'      \r\n"
				;
	}
	

}
