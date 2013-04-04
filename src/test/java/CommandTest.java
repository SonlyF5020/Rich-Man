import Command.Command;
import Main.Main;
import Main.Player;
import Main.RichMap;
import Main.Round;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CommandTest {
	private Main main=new Main();

	@Test
	public void map_position_6_should_have_a_block_when_player_stand_at_0_used_BLOCK_6_command() {
		int initialPosition = 0;
		int initialBlockNumber = 3;
		RichMap richMap = new RichMap();
		Player player = new Player(initialPosition, initialBlockNumber, 0, 0);
		Round round = new Round(player, richMap);
		Command command = Command.creator("block 6");
		command.handleCommand(round);
		assertThat(player.getBlockNumber(), is(2));
		assertThat(richMap.isToolHere(6), is(true));
	}
	@Test
	public void map_position_6_should_have_a_bomb_when_player_stand_at_0_used_BOMB_6_command() {
		int initialPosition = 0;
		int initialBombNumber = 3;
		RichMap richMap = new RichMap();
		Player player = new Player(initialPosition,0, initialBombNumber, 0);
		Round round = new Round(player, richMap);
		assertThat(player.getBombNumber(), is(3));
		assertThat(richMap.isToolHere(6), is(false));
		Command command = Command.creator("bomb 6");
		command.handleCommand(round);
		assertThat(player.getBombNumber(), is(2));
		assertThat(richMap.isToolHere(6), is(true));
	}
	@Test
	public void map_position_6_should_have_no_block_when_player_stand_at_0_used_BLOCK_6_command_and_then_used_ROBOT_command() {
		int initialPosition = 0;
		int initialBlockNumber = 3;
		int initialRobotNumber=3;
		RichMap richMap = new RichMap();
		Player player = new Player(initialPosition, initialBlockNumber, 0, initialRobotNumber);
		Round round = new Round(player, richMap);

		assertThat(player.getBlockNumber(), is(3));
		assertThat(player.getRobotNumber(),is(3));
		assertThat(richMap.isToolHere(6), is(false));

		Command command = Command.creator("block 6");
		command.handleCommand(round);
		assertThat(player.getBlockNumber(), is(2));
		assertThat(player.getRobotNumber(),is(3));
		assertThat(richMap.isToolHere(6), is(true));

		Command command1 = Command.creator("robot");
		command1.handleCommand(round);
		assertThat(player.getBlockNumber(), is(2));
		assertThat(player.getRobotNumber(),is(2));
		assertThat(richMap.isToolHere(6), is(false));
	}
	@Test
	public void map_position_6_should_have_no_bomb_when_player_stand_at_0_used_BOMB_6_command_and_then_used_ROBOT_command() {
		int initialPosition = 0;
		int initialBombNumber = 3;
		int initialRobotNumber=3;
		RichMap richMap = new RichMap();
		Player player = new Player(initialPosition, 0, initialBombNumber, initialRobotNumber);
		Round round = new Round(player, richMap);

		assertThat(player.getBombNumber(), is(3));
		assertThat(player.getRobotNumber(),is(3));
		assertThat(richMap.isToolHere(6), is(false));

		Command command = Command.creator("BOMB 6");
		command.handleCommand(round);
		assertThat(player.getBombNumber(), is(2));
		assertThat(player.getRobotNumber(),is(3));
		assertThat(richMap.isToolHere(6), is(true));

		Command command1 = Command.creator("robot");
		command1.handleCommand(round);
		assertThat(player.getBombNumber(), is(2));
		assertThat(player.getRobotNumber(),is(2));
		assertThat(richMap.isToolHere(6), is(false));
	}
	@Test
	public void should_Gain_400_money_after_sell_a_free_house_with_initial_price_of_200() {
		int initialMoney=100;
		int housePosition=1;
		RichMap richMap = new RichMap();
		Player player = new Player(initialMoney,0.0);

		assertThat(player.getHouseNumber(0),is(0));

		richMap.setOwner(housePosition,player);
		assertThat(player.getHouseNumber(0),is(1));

		Round round = new Round(player, richMap);
		assertThat(richMap.getOwner(housePosition),is(player));

		Command command = Command.creator("sell "+housePosition);
		command.handleCommand(round);
		assertThat(player.getMoney(),is(500.0));
		assertThat(richMap.getOwner(housePosition).equals(player),is(false));
		assertThat(player.getHouseNumber(0),is(0));
	}
	@Test
	public void should_Gain_1000_money_after_sell_a_free_house_with_initial_price_of_500() {
		int initialMoney=100;
		int housePosition=30;
		RichMap richMap = new RichMap();
		Player player = new Player(initialMoney,0.0);

		assertThat(player.getHouseNumber(0),is(0));

		richMap.setOwner(housePosition,player);
		assertThat(player.getHouseNumber(0),is(1));

		Round round = new Round(player, richMap);
		assertThat(richMap.getOwner(housePosition),is(player));

		Command command = Command.creator("sell "+housePosition);
		command.handleCommand(round);
		assertThat(player.getMoney(),is(1100.0));
		assertThat(richMap.getOwner(housePosition).equals(player),is(false));
		assertThat(player.getHouseNumber(0),is(0));
	}
	@Test
	public void should_Gain_0_money_after_sell_a_free_house_not_belong_to_the_current_player() {
		int initialMoney=100;
		int housePosition=30;
		RichMap richMap = new RichMap();
		Player player = new Player(initialMoney,0.0);

		assertThat(player.getHouseNumber(0),is(0));

		richMap.setOwner(housePosition,player);
		assertThat(player.getHouseNumber(0),is(1));

		Round round = new Round(player, richMap);
		assertThat(richMap.getOwner(housePosition),is(player));

		Command command = Command.creator("sell "+(housePosition+1));
		command.handleCommand(round);
		assertThat(player.getMoney(), is(100.0));
		assertThat(richMap.getOwner(housePosition).equals(player),is(true));
		assertThat(richMap.getOwner(housePosition+1).equals(player),is(false));
		assertThat(player.getHouseNumber(0),is(1));
	}
	@Test
	public void should_got_25_Tickets_when_sell_a_tool_bomb(){
		Player player=new Player();
		player.setBlockNumber(2);
		RichMap richMap=new RichMap();
		Round round=new Round(player,richMap);
		Command command=Command.creator("selltool 1");
		command.handleCommand(round);
		assertThat(player.getBlockNumber(),is(1));
		assertThat(player.getTicket(),is(25.0));
	}
}
