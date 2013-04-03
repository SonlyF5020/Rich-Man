package Command;

import Main.Main;
import Main.Round;

abstract public class Command {

	public static Command creator(String commandFullName) {
		String commandName = getFactCommand(commandFullName);
		int commandNumber = getCommandNumber(commandFullName);

		if ("roll".equalsIgnoreCase(commandName)) {
			return new RollCommand();
		}
		if ("query".equalsIgnoreCase(commandName)) {
			return new QueryCommand();
		}
		if ("quit".equalsIgnoreCase(commandName)) {
			return new QuitCommand();
		}
		if ("block".equalsIgnoreCase(commandName)) {
			return new BlockCommand(commandNumber);
		}
		if ("bomb".equalsIgnoreCase(commandName)) {
			return new BombCommand(commandNumber);
		}
		if ("robot".equalsIgnoreCase(commandName)) {
			return new RobotCommand();
		}
		if ("sell".equalsIgnoreCase(commandName)) {
			return new SellCommand(commandNumber);
		}
		if ("selltool".equalsIgnoreCase(commandName)) {
			return new SellToolCommand(commandNumber);
		}
		if ("help".equalsIgnoreCase(commandName)) {
			return new HelpCommand();
		}
		if("jump".equalsIgnoreCase(commandName)){
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
