package roglikeRPG;

public class Troll extends Monster{

	private String name;
	
	public Troll() {
		super(225, 25, 30);
		this.name = "트롤";
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDesc() {
		return "트롤은 멍청하지 않아!";
	}
}
