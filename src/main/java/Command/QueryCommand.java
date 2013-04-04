package Command;


import Main.Main;
import Main.Round;

public class QueryCommand extends Command {
	@Override
	public void handleCommand(Round round) {
		round.showDetailInformation();
	}
}
