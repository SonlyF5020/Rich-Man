package Main;


import Command.Command;
import tool.BlockTool;
import tool.BombTool;
import tool.RobotTool;

import java.util.Scanner;

public class Round {
	private Player currentPlayer;
	private RichMap richMap;
	private boolean roundIsNotOver;

	public Round(Player player, RichMap richMap) {
		this.currentPlayer = player;
		this.richMap = richMap;
		roundIsNotOver = true;
	}

	public void roundStartInformation() {
		System.out.println(currentPlayer.getName() + "回合开始！");
	}

	public void roundProcess() {
		Command command = Command.creator(acceptCommand());
		command.handleCommand(this);
	}

	private String acceptCommand() {
		System.out.println();
		System.out.print(currentPlayer.getName() + ">");
		Scanner scanner = new Scanner(System.in);
		String acceptCommandString = scanner.nextLine();
		return acceptCommandString;
	}


	public void roundRun() {
		roundStartInformation();
		while (roundIsNotOver()) {
			roundProcess();
		}
		showMap();
	}

	private void showMap() {
		richMap.showMap();
	}

	private boolean roundIsNotOver() {
		if (currentPlayer.getInPrisonRound() > 0) {
			System.out.println(currentPlayer.getName() + "尚在监狱中！");
			currentPlayer.passOneRoundInPrison();
			setRoundIsOver();
		} else if (currentPlayer.getInHospitalRound() > 0) {
			System.out.println(currentPlayer.getName() + "尚在医院接受肋骨焊接手术！");
			currentPlayer.passOneRoundInHospital();
			setRoundIsOver();
		}
		if(currentPlayer.withGodProtect()){
			currentPlayer.passOneRoundWithGod();
		}
		return roundIsNotOver;
	}

	public void setBlock(int blockDistance) {
		currentPlayer.useTool(richMap, RichMap.initial(currentPlayer.getPosition() + blockDistance), new BlockTool());
	}

	public void setBomb(int bombDistance) {
		currentPlayer.useTool(richMap, RichMap.initial(currentPlayer.getPosition() + bombDistance), new BombTool());
	}

	public void useRobot() {
		currentPlayer.useTool(richMap, currentPlayer.getPosition(), new RobotTool());
	}

	public void showDetailInformation() {
		currentPlayer.showDetailInformation();
	}

	public void move(int rollDistance) {
		richMap.removeStandPlayer(currentPlayer);
		if (richMap.notMeetTool(currentPlayer, rollDistance)) {
			currentPlayer.move(rollDistance);
		}
		richMap.eventHappen(currentPlayer);
	}

	public void sellHouse(int sellHousePosition) {
		currentPlayer.sellHouse(sellHousePosition);
	}

	public void sellTool(int sellToolNumber) {
		currentPlayer.sellTool(sellToolNumber);
	}

	public void setRoundIsOver() {
		System.out.println("回合结束");
		roundIsNotOver = false;
	}

	public void jump(int jumpDistance) {
		richMap.removeStandPlayer(currentPlayer);
		currentPlayer.moveTo(jumpDistance);
		richMap.eventHappen(currentPlayer);
	}
}
