package Command;

import Main.Main;
import Main.Round;

public class JumpCommand extends Command {
	int jumpDistance;

	public JumpCommand(int position) {
		super();
		jumpDistance=position;
	}

	@Override
	public void handleCommand(Round round, Main main) {
		round.jump(jumpDistance,main);
	}
}
