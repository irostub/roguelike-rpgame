package roglikeRPG;

public class Golem extends Monster {

	private String name;
	
	public Golem() {
		super(175, 5,10);
		this.name = "∞Ò∑Ω";
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDesc() {
		return "πŸ¿ß√≥∑≥ ¥‹¥‹«œ∞‘...";
	}
}
