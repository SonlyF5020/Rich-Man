package Command;


import Main.Main;
import Main.Round;

import java.util.Scanner;

public class QuitCommand extends Command {
	@Override
	public void handleCommand(Round round) {
		areYouSureInformation();
		if (choseYes()) System.exit(-2);
	}

	private boolean choseYes() {
		Scanner scanner = new Scanner(System.in);
		String answer = scanner.nextLine();
		return answer.equals("Y") || answer.equals("y");
	}

	private void areYouSureInformation() {
		System.out.print("你确定要离开这么好玩的游戏吗？");
	}
}
