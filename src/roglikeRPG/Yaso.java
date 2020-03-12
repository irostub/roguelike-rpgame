package roglikeRPG;

public class Yaso extends Monster{

	private String name;
	
	public Yaso() {
		super(250, 1, 30);
		this.name = "야스오";
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDesc() {
		return "야스오는 과학이라고, 들어나봤나?";
	}
}
