package MapElement;

import Main.Player;
import tool.BlockTool;
import tool.BombTool;
import tool.RobotTool;
import tool.Tool;

import java.util.Scanner;

public class ToolHouse extends MapElement {
	private static final String TOOL_HOUSE_MARK = "T";
	Tool bomb = new BombTool();
	Tool block = new BlockTool();
	Tool robot = new RobotTool();

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
	public String getMark() {
		return TOOL_HOUSE_MARK;
	}


	private int playerChose() {
		Scanner scanner = new Scanner(System.in);
		String choseResult = scanner.nextLine();
		return Integer.parseInt(choseResult);
	}

	public void action(Player player, int choseResult) {
		switch (choseResult) {
			case 1:
				buyBlock(player);
				break;
			case 2:
				buyRobot(player);
				break;
			case 3:
				buyBomb(player);
				break;
			default:
				buyNothingInformation(player);
				break;
		}
	}

	private void arriveToolHouseInformation(Player player) {
		System.out.println(player.getName() + "，欢迎来到道具屋，有三种道具，1>路障,50点券  2>高达,30点券  3>炸弹,50点券");
	}

	private void buyBombInformation(Player player) {
		System.out.println(player.getName() + "购买了道具 炸弹 1个！");
	}

	private void buyRobotInformation(Player player) {
		System.out.println(player.getName() + "购买了道具 机器人 1个！");
	}

	private void buyBlockInformation(Player player) {
		System.out.println(player.getName() + "购买了道具 路障 1个！");
	}

	private void buyNothingInformation(Player player) {
		System.out.println(player.getName() + "什么都没有买就走了");
	}

	private void buyBomb(Player player) {
		if (player.haveEnoughTicket(bomb.getPrice())) {
			player.payTicket(bomb.getPrice());
			player.addBomb();
			buyBombInformation(player);
		}
	}

	private void buyBlock(Player player) {
		if (player.haveEnoughTicket(block.getPrice())) {
			player.payTicket(block.getPrice());
			player.addBlock();
			buyBlockInformation(player);
		}
	}

	private void buyRobot(Player player) {
		if (player.haveEnoughTicket(robot.getPrice())) {
			player.payTicket(robot.getPrice());
			player.addRobot();
			buyRobotInformation(player);
		}
	}


}
