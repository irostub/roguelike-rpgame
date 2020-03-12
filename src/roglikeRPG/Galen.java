package roglikeRPG;

public class Galen extends Monster{

	private String name;

	public Galen() {
		super(150, 15, 10);
		this.name = "가렌";
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDesc() {
		return "지치지 않는 팽이가 뭔지 보여주지!";
	}
}
