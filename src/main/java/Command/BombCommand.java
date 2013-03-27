package Command;

import Main.Main;
import Main.Round;

public class BombCommand extends Command {
	private int bombDistance;

	public BombCommand(int commandNumber) {
		super();
		this.bombDistance = commandNumber;
	}

	@Override
	public void handleCommand(Round round, Main main) {
		round.setBomb(bombDistance);
	}
}
