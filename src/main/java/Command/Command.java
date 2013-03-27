package Command;

import Main.Main;
import Main.Round;

abstract public class Command {

	public static Command creator(String commandFullName) {
		String commandName = getFactCommand(commandFullName);
		int commandNumber = getCommandNumber(commandFullName);

		if (commandName.equals("ROLL") || commandName.equals("roll")) {
			return new RollCommand();
		}
		if (commandName.equals("QUERY") || commandName.equals("query")) {
			return new QueryCommand();
		}
		if (commandName.equals("QUIT") || commandName.equals("quit")) {
			return new QuitCommand();
		}
		if (commandName.equals("BLOCK") || commandName.equals("block")) {
			return new BlockCommand(commandNumber);
		}
		if (commandName.equals("BOMB") || commandName.equals("bomb")) {
			return new BombCommand(commandNumber);
		}
		if (commandName.equals("ROBOT") || commandName.equals("robot")) {
			return new RobotCommand();
		}
		if (commandName.equals("SELL") || commandName.equals("sell")) {
			return new SellCommand(commandNumber);
		}
		if (commandName.equals("SELLTOOL") || commandName.equals("selltool")) {
			return new SellToolCommand(commandNumber);
		}
		if (commandName.equals("HELP") || commandName.equals("help")) {
			return new HelpCommand();
		}
		if(commandName.equals("JUMP")||commandName.equals("jump")){
			return new JumpCommand(commandNumber);
		}
		else return new NullCommand();
	}

	private static String getFactCommand(String command) {
		String[] commandDetail = command.split(" ");
		return commandDetail[0];
	}

	private static int getCommandNumber(String command) {
		String[] commandDetail = command.split(" ");
		if (commandDetail.length > 1) return Integer.parseInt(commandDetail[1]);
		else return 0;
	}

	abstract public void handleCommand(Round round, Main main);
}
