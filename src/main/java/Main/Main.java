package Main;

import java.util.Scanner;

public class Main {
	private Player richPlayer[] = new Player[4];
	private int playerNumber;
	private RichMap richMap = new RichMap();

	private void chosePlayer() {
		String answer = acceptChosenString();
		playerNumber = answer.length();
		if (playerNumber <= 4) {
			for (int i = 0; i < playerNumber; i++) {
				switch (answer.charAt(i)) {
					case '1':
						richPlayer[i] = new Player("钱夫人", 10000, "Q");
						break;
					case '2':
						richPlayer[i] = new Player("阿土伯", 8000, "A");
						break;
					case '3':
						richPlayer[i] = new Player("孙小美", 10000, "S");
						break;
					case '4':
						richPlayer[i] = new Player("金贝贝", 20000, "B");
						break;
					default:
						errorChosenInformation();
				}
			}
		}
	}

	private void errorChosenInformation() {
		System.out.println("选择不合规范！");
	}

	public String acceptChosenString() {
		System.out.print("请选择2~4位不重复玩家，输入编号即可。(1.钱夫人; 2.阿土伯; 3.孙小美; 4.金贝贝):");
		Scanner scanner = new Scanner(System.in);
		String answer = scanner.nextLine();
		return answer;
	}

	public Player getOwnerPlayer(String name) {
		for (int i = 0; i < playerNumber; i++) {
			if (richPlayer[i].getName().equals(name)) {
				return richPlayer[i];
			}
		}
		return null;
	}

	public void runGame() {
		chosePlayer();
		playing();
		win();
	}

	private void win() {
		System.out.println(getWinner().getName() + "获得胜利！游戏结束！");
	}

	private void playing() {
		while (getPlayerNumber() > 1) {
			for (int i = 0; i < playerNumber; i++) {
				Round round = new Round(richPlayer[i], richMap);
				round.roundRun(this);
			}
		}
	}

	private Player getWinner() {
		for (int i = 0; i < playerNumber; i++) {
			if (richPlayer[i].isNotBroken()) {
				return richPlayer[i];
			}
		}
		return null;
	}

	private int getPlayerNumber() {
		int answer = 0;
		for (int i = 0; i < playerNumber; i++) {
			if (richPlayer[i].isNotBroken()) {
				answer++;
			}
		}
		return answer;
	}

	public static void main(String[] args) {
		Main richMain = new Main();
		richMain.runGame();
	}

	public Player getStandPlayer(int position) {
		for (int i = 0; i < playerNumber; i++) {
			if (richPlayer[i].getPosition() == position) {
				return richPlayer[i];
			}
		}
		return null;
	}
}
