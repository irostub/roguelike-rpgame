package roglikeRPG;

public class BigPortion extends Food {
	
	private String name;
	
	public BigPortion() {
		super(500);
		this.name = "²Ï³ª Èñ±ÍÇÑ ´ëÇü Æ÷¼Ç";
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public String getDesc() {
		return "²Ï³ª Èñ±ÍÇÑ ¹°¾àÀÔ´Ï´Ù! hp¸¦ 500 ¸¸Å­ È¸º¹ÇÕ´Ï´Ù.";
	}
}
