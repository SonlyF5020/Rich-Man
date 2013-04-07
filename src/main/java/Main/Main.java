package Main;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	private ArrayList<Player> richPlayer = new ArrayList<Player>();
	private int playerNumber;
	private RichMap richMap = new RichMap();

	private void chosePlayer() {
		String answer = acceptChosenString();
		playerNumber = answer.length();
		if (playerNumber <= 4) {
			for (int i = 0; i < playerNumber; i++) {
				switch (answer.charAt(i)) {
					case '1':
						richPlayer.add(new Player("钱夫人", 10000, "Q"));
						break;
					case '2':
						richPlayer.add(new Player("阿土伯", 8000, "A"));
						break;
					case '3':
						richPlayer.add(new Player("孙小美", 10000, "S"));
						break;
					case '4':
						richPlayer.add(new Player("金贝贝", 20000, "B"));
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

	public void runGame() {
		chosePlayer();
		playing();
		win();
	}

	private void win() {
		System.out.println(getWinner().getName() + "获得胜利！游戏结束！");
	}

	private void playing() {
		initial(richMap,richPlayer);
		while (getPlayerNumber() > 1) {
			for (int i = 0; i < playerNumber; i++) {
				Round round = new Round(richPlayer.get(i), richMap);
				round.roundRun();
				checkIfAnyOneIsBroken(richPlayer.get(i));
			}
		}
	}

	private void checkIfAnyOneIsBroken(Player player) {
		if (player.isNotBroken()) {
			return;
		}
		richPlayer.remove(player);
	}

	private void initial(RichMap map, ArrayList<Player> playerList) {
		if(!playerList.isEmpty()){
			for(Player player:playerList){
				map.initialPlayerPosition(player);
			}
		}
	}

	private Player getWinner() {
		if(!richPlayer.isEmpty()){
			return richPlayer.get(0);
		}
		return null;
	}

	private int getPlayerNumber() {
		return richPlayer.size();
	}

	public static void main(String[] args) {
		Main richMain = new Main();
		richMain.runGame();
	}

}
