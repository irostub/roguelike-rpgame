package roglikeRPG;

import java.util.ArrayList;
import java.util.Iterator;
import java.io.Serializable;

public class Inventory implements Serializable
{
	private ArrayList<Item> i;
	
	public Inventory() {
		i = new ArrayList<Item>();
	}
	
	public void setI(ArrayList<Item> i) {
		this.i = i;
	}

	public void showInven() {
		
		Iterator<Item> itr = i.iterator();
		int count = 0;
		
		if(i.isEmpty())
		{
			System.out.println("획득한 아이템이 아무것도 없네요! 텅텅 비었어요. 행운을 빌어요!");
			return;
		}
		System.out.println("--------------------");
		System.out.println("[   획득한 아이템 목록      ]");
		
		while(itr.hasNext()) {
			Item temp = itr.next();
			System.out.println("["+count+"] 번 인벤토리 아이템");
			System.out.println("아이템명 : " + temp.getName());
			System.out.println("아이템 설명 : " + temp.getDesc());
			System.out.println();
			count++;
		}
		System.out.println("--------------------");
	}
	
	public ArrayList<Item> getI() {
		if(i.size() == 0) {
			return null;
		}
		return i;
	}
	
	public void addItem(Item i)
	{
		this.i.add(i);
	}
	
	public void delItem(int index)
	{
		i.remove(index);
	}
}
