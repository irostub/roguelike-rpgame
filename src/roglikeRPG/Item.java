package roglikeRPG;

interface Item{
	public String getName();
	public String getDesc();
	public void use(Player p);
}