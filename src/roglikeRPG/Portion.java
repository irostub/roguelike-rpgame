package roglikeRPG;

public class Portion extends Food{
	private String name;

	public Portion() {
		super(150);
		this.name = "흔한 소형 포션";
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	public String getDesc() {
		return "보통 물약입니다. hp를 150 만큼 회복합니다.";
	}
}
