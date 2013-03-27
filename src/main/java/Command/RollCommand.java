package Command;

import Main.Main;
import Main.Round;

import java.util.Random;

public class RollCommand extends Command {
	@Override
	public void handleCommand(Round round, Main main) {
		round.move(getRollDistance(), main);
		round.setRoundIsOver();
	}

	private int getRollDistance() {
		int rollDistance;
		rollDistance = new Random().nextInt(6);
		return ++rollDistance;
	}
}
