package Command;

import Main.Main;
import Main.Round;

public class BlockCommand extends Command {
	private int blockDistance;

	public BlockCommand(int commandNumber) {
		super();
		this.blockDistance = commandNumber;
	}

	@Override
	public void handleCommand(Round round, Main main) {
		round.setBlock(blockDistance);
	}
}
