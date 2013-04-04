package Main;


import Command.Command;

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
		String acceptCommand = scanner.nextLine();
		return acceptCommand;
	}


	public void roundRun(Main main) {
		roundStartInformation();
		while (roundIsNotOver()) {
			roundProcess();
		}
		showMap(main);
	}

	private void showMap(Main main) {
		richMap.showMap(main);
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
		currentPlayer.setBlock(richMap, RichMap.initial(currentPlayer.getPosition()+blockDistance));
	}

	public void setBomb(int bombDistance) {
		currentPlayer.setBomb(richMap, RichMap.initial(currentPlayer.getPosition()+bombDistance));
	}

	public void useRobot() {
		currentPlayer.setRobot(richMap);
	}

	public void showDetailInformation() {
		currentPlayer.showDetailInformation();
	}

	public void move(int rollDistance) {
		if (richMap.notMeetBlockAndBomb(currentPlayer, rollDistance)) {
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
		currentPlayer.moveTo(jumpDistance);
		richMap.eventHappen(currentPlayer);
	}
}
