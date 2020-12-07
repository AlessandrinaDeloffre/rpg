package rpg;

public class Hammer extends Weapon {
	private static final int DAMAGE = 50;
	private static final String NAME = "Hammer";
	public Hammer() {
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
