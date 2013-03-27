package Command;

import Main.Main;
import Main.Round;

public class SellCommand extends Command {
	private int sellHousePosition;

	public SellCommand(int commandNumber) {
		super();
		this.sellHousePosition = commandNumber;
	}

	@Override
	public void handleCommand(Round round, Main main) {
		round.sellHouse(sellHousePosition);
	}
}
