package tool;

import Main.Player;
import Main.RichMap;

public abstract class Tool {
	private String name;

	abstract public void useTool(RichMap map, int position);

	abstract public void trigger(Player player, int position);

	abstract public double getPrice();

	abstract public String getMark();


	public Tool(String name) {
		this.name = name;
	}

	public abstract double getSellTicket();

	public String getName() {
		return name;
	}
}
