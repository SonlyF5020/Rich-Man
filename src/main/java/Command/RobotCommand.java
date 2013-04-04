package Command;

import Main.Main;
import Main.Round;

public class RobotCommand extends Command {
	@Override
	public void handleCommand(Round round) {
		round.useRobot();
	}
}
