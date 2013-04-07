package MapElement;

import Main.Player;
import tool.BlockTool;
import tool.BombTool;
import tool.RobotTool;
import tool.Tool;

import java.util.Scanner;

public class ToolHouse extends MapElement {
	private static final String TOOL_HOUSE_MARK = "T";

	public ToolHouse() {
		this(0);
	}

	public ToolHouse(int position) {
		super("toolHouse", position);
	}

	@Override
	public void elementEvent(Player player, Player owner) {
		arriveToolHouseInformation(player);
		action(player, playerChose());
	}

	@Override
	public String getInitialMark() {
		return TOOL_HOUSE_MARK;
	}


	private int playerChose() {
		Scanner scanner = new Scanner(System.in);
		String choseResult = scanner.nextLine();
		return Integer.parseInt(choseResult);
	}

	public void action(Player player, int choseResult) {
		buyTool(player, choseTool(choseResult));
	}

	private void buyTool(Player player, Tool tool) {
		if (tool != null) {
			player.buyNewTool(tool);
		} else buyNothingInformation(player);
	}

	public Tool choseTool(int choseResult) {
		switch (choseResult) {
			case 1:
				return new BlockTool();
			case 2:
				return new RobotTool();
			case 3:
				return new BombTool();
			default:
				return null;
		}
	}

	private void arriveToolHouseInformation(Player player) {
		System.out.println(player.getName() + "，欢迎来到道具屋，有三种道具，1>路障,50点券  2>高达,30点券  3>炸弹,50点券");
	}

	private void buyNothingInformation(Player player) {
		System.out.println(player.getName() + "什么都没有买就走了");
	}

}
