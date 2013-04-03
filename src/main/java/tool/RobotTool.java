package tool;

import Main.Player;
import Main.RichMap;

public class RobotTool extends Tool {
	private static final double ROBOT_PRICE = 30.0;

	public RobotTool(int number) {
		super(number);
	}

	public RobotTool() {
		super();
	}

	@Override
	public void useTool(RichMap map, int position) {
		map.clearTool(position);
	}

	@Override
	public void trigger(Player player,int position) {
	}

	@Override
	public double getPrice() {
		return ROBOT_PRICE;
	}

	@Override
	public String getMark() {
		return null;
	}

	@Override
	public double getSellTicket() {
		return ROBOT_PRICE / 2;
	}
}
