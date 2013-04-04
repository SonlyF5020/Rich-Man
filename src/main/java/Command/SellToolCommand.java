package Command;

import Main.Main;
import Main.Round;

public class SellToolCommand extends Command {
	private int sellToolNumber;

	public SellToolCommand(int commandNumber) {
		super();
		this.sellToolNumber = commandNumber;
	}

	@Override
	public void handleCommand(Round round) {
		round.sellTool(sellToolNumber);
	}
}
