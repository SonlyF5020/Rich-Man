package Command;

import Main.Main;
import Main.Round;

public class NullCommand extends Command {
	@Override
	public void handleCommand(Round round, Main main) {
		round.setRoundIsOver();
	}
}
