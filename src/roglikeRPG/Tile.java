package roglikeRPG;

import java.util.Random;

public class Tile {
	
	protected Item i;
	protected Monster m;
	
	Tile()
	{
		Random r = new Random();
		int encounter = r.nextInt(100);
		int itemEncounter = r.nextInt(2);
		int monsterEncounter = r.nextInt(7);
		
		if(encounter>=0 && encounter<33) {
			i = null;
			m = null;
		}
		else if(encounter>=33 && encounter<66) {
			switch(itemEncounter)
			{
			case 0:i = new Portion(); break;
			case 1:i = new BigPortion(); break;
			default:System.out.println("Tile.itemEncounter Error");
			}
		}
		else {
			switch(monsterEncounter)
			{
			case 0: m = new Galen();break;
			case 1: m = new Ghuol();break;
			case 2: m = new Goblin();break;
			case 3: m = new Golem();break;
			case 4: m = new Skeleton();break;
			case 5: m = new Troll();break;
			case 6: m = new Yaso();break;
			default:System.out.println("Tile.monsterEncounter Error");
			}
		}
	}
}
