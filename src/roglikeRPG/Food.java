package roglikeRPG;
import java.io.Serializable;

public abstract class Food implements Item, Serializable {

	protected int heal;

	public Food(int heal) {
		this.heal = heal;
	}

	public void setHeal(int heal) {
		this.heal = heal;
	}

	public int getHeal() {
		return heal;
	}

	public void use(Player p) {
		if(p.getMaxHp()>=p.getHp()+heal)
		{
			p.setHp(p.getHp()+heal);
		}
		else if(p.getMaxHp()<p.getHp()+heal) {
			p.setHp(p.getMaxHp());
		}
		else {
			System.out.println("food class use method에서 오류!");
		}
	}
	public abstract String getName();
	public abstract String getDesc();
}
