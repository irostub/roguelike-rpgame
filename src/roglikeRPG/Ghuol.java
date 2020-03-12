package roglikeRPG;

public class Ghuol extends Monster {

	private String name;
	
	public Ghuol() {
		super(125, 20, 20);
		this.name = "구울";
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getDesc() {
		return "흐음..송장이 그렇게 맛있더군..자네도 한입하겠나?";
	}
}
