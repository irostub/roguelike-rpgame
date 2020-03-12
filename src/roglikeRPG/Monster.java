package roglikeRPG;

public abstract class Monster{
	protected int hp;
	protected int atk;
	protected int exp;
	
	public Monster(int hp, int atk, int exp) {
		this.hp = hp;
		this.atk = atk;
		this.exp = exp;
	}
	
	public int getHp() {
		return hp;
	}
	
	public void setHp(int hp) {
		this.hp = hp;
	}
	
	public int getAtk() {
		return atk;
	}
	
	public int getExp() {
		return exp;
	}
	
	public abstract String getName();
	public abstract String getDesc();
}
