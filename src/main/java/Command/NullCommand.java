package Command;

import Main.Main;
import Main.Round;

public class NullCommand extends Command {
	@Override
	public void handleCommand(Round round) {
		round.setRoundIsOver();
	}
}
