package tool;

import Main.Player;
import Main.RichMap;

public abstract class Tool {
	int number;

	abstract public void useTool(RichMap map, int position);

	abstract public void trigger(Player player,int position);

	abstract public double getPrice();

	public Tool() {
		this.number = 0;
	}

	public Tool(int number) {
		this.number = number;
	}

	public void addOne() {
		number++;
	}

	public int getNumber() {
		return number;
	}

	public abstract double getSellTicket();

	public void minusOne() {
		number--;
	}

	public void setNumber(int number) {
		this.number = number;
	}

}
