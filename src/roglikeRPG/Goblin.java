package roglikeRPG;

public class Goblin extends Monster {

	private String name;
	
	public Goblin() {
		super(100, 20, 15);
		this.name = "고블린";
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDesc() {
		return "시간은 금이라구 친구";
	}
}
