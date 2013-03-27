import Main.Player;
import MapElement.*;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class MapElementTest {
	@Test
	public void house_level_should_be_1_after_updated_while_house_level_is_0() {
		int houseLevel = 0;
		double housePrice = 200.0;
		double initialMoney = 1000.0;
		House house = new House(housePrice, houseLevel);
		Player player = new Player(initialMoney, 0.0);
		house.update(player);
		assertThat(house.getLevel(), is(1));
		assertThat(player.getMoney(), is(800.0));
	}

	@Test
	public void house_level_should_be_2_after_updated_while_house_level_is_1() {
		int houseLevel = 1;
		double housePrice = 200.0;
		double initialMoney = 1000.0;
		House house = new House(housePrice, houseLevel);
		Player player = new Player(initialMoney, 0.0);
		house.update(player);
		assertThat(house.getLevel(), is(2));
		assertThat(player.getMoney(), is(800.0));
	}

	@Test
	public void house_level_should_be_3_after_updated_while_house_level_is_2() {
		int houseLevel = 2;
		double housePrice = 200.0;
		double initialMoney = 1000.0;
		House house = new House(housePrice, houseLevel);
		Player player = new Player(initialMoney, 0.0);
		house.update(player);
		assertThat(house.getLevel(), is(3));
		assertThat(player.getMoney(), is(800.0));
	}

	@Test
	public void house_level_should_be_3_after_updated_while_house_level_is_3() {
		int houseLevel = 3;
		double housePrice = 200.0;
		double initialMoney = 1000.0;
		House house = new House(housePrice, houseLevel);
		Player player = new Player(initialMoney, 0.0);
		house.update(player);
		assertThat(house.getLevel(), is(3));
		assertThat(player.getMoney(), is(1000.0));
	}

	@Test
	public void house_should_not_be_updated_while_money_is_not_enough() {
		int houseLevel = 1;
		double housePrice = 200.0;
		double initialMoney = 100.0;
		House house = new House(housePrice, houseLevel);
		Player player = new Player(initialMoney, 0.0);
		house.update(player);
		assertThat(house.getLevel(), is(1));
		assertThat(player.getMoney(), is(100.0));
	}

	@Test
	public void house_pass_fee_should_be_100_when_pass_a_level_0_house_with_price_of_200() {
		double initialPrice = 200.0;
		int houseLevel = 0;
		House house = new House(initialPrice, houseLevel);
		assertThat(house.getPassFee(), is(100.0));
	}

	@Test
	public void house_pass_fee_should_be_200_when_pass_a_level_1_house_with_price_of_200() {
		double initialPrice = 200.0;
		int houseLevel = 1;
		House house = new House(initialPrice, houseLevel);
		assertThat(house.getPassFee(), is(200.0));
	}

	@Test
	public void house_pass_fee_should_be_400_when_pass_a_level_2_house_with_price_of_200() {
		double initialPrice = 200.0;
		int houseLevel = 2;
		House house = new House(initialPrice, houseLevel);
		assertThat(house.getPassFee(), is(400.0));
	}

	@Test
	public void house_pass_fee_should_be_800_when_pass_a_level_3_house_with_price_of_200() {
		double initialPrice = 200.0;
		int houseLevel = 3;
		House house = new House(initialPrice, houseLevel);
		assertThat(house.getPassFee(), is(800.0));
	}

	@Test
	public void should_get_20_tickets_while_arrive_TicketField() {
		double initialTicket = 0.0;
		double initialMoney = 0.0;
		Player player = new Player(initialMoney, initialTicket);
		TicketField ticketField = new TicketField(20.0);
		ticketField.elementEvent(player, null);
		assertThat(player.getTicket(), is(20.0));
	}

	@Test//为了测试礼物屋中核心单元action，将action方法由私有改为了公有，请问有更好的方法吗？
	public void should_get_2000_money_while_arrive_gift_house_after_chose_1() {
		double initialMoney = 0.0;
		double initialTicket = 0.0;
		int choseOne = 1;
		Player player = new Player(initialMoney, initialTicket);
		GiftHouse giftHouse = new GiftHouse();
		giftHouse.action(player, choseOne);
		assertThat(player.getMoney(), is(2000.0));
	}

	@Test
	public void should_get_200_ticket_while_arrive_gift_house_after_chose_2() {
		double initialMoney = 0.0;
		double initialTicket = 0.0;
		int choseTwo = 2;
		Player player = new Player(initialMoney, initialTicket);
		GiftHouse giftHouse = new GiftHouse();
		giftHouse.action(player, choseTwo);
		assertThat(player.getTicket(), is(200.0));
	}

	@Test
	public void should_get_god_state_while_arrive_gift_house_after_chose_3() {
		double initialMoney = 0.0;
		double initialTicket = 0.0;
		int choseThree = 3;
		Player player = new Player(initialMoney, initialTicket);
		GiftHouse giftHouse = new GiftHouse();
		giftHouse.action(player, choseThree);
		assertThat(player.isWithGod(), is(true));
	}

	@Test
	public void should_get_into_prison_for_2_rounds_while_arrive_prison() {
		Player player = new Player();
		Prison prison = new Prison();
		prison.elementEvent(player, null);
		assertThat(player.getInPrisonRound(), is(2));
	}

	@Test
	public void should_get_1_Block_while_arrive_Tool_House_and_chose_1() {
		double initialMoney = 0.0;
		double initialTicket = 100;
		int choseOne = 1;
		Player player = new Player(initialMoney, initialTicket);
		ToolHouse toolHouse = new ToolHouse();
		toolHouse.action(player, choseOne);
		assertThat(player.getTicket(), is(50.0));
		assertThat(player.getBlockNumber(), is(1));
	}

	@Test
	public void should_get_1_Robot_while_arrive_Tool_House_and_chose_2() {
		double initialMoney = 0.0;
		double initialTicket = 100;
		int choseTwo = 2;
		Player player = new Player(initialMoney, initialTicket);
		ToolHouse toolHouse = new ToolHouse();
		toolHouse.action(player, choseTwo);
		assertThat(player.getTicket(), is(70.0));
		assertThat(player.getRobotNumber(), is(1));
	}

	@Test
	public void should_get_1_Block_while_arrive_Tool_House_and_chose_3() {
		double initialMoney = 0.0;
		double initialTicket = 100;
		int choseThree = 3;
		Player player = new Player(initialMoney, initialTicket);
		ToolHouse toolHouse = new ToolHouse();
		toolHouse.action(player, choseThree);
		assertThat(player.getTicket(), is(50.0));
		assertThat(player.getBombNumber(), is(1));
	}

	@Test
	public void should_get_nothing_while_arrive_Tool_House_and_chose_1_but_no_enough_tickets() {
		double initialMoney = 0.0;
		double initialTicket = 10.0;
		int choseOne = 1;
		Player player = new Player(initialMoney, initialTicket);
		ToolHouse toolHouse = new ToolHouse();
		toolHouse.action(player, choseOne);
		assertThat(player.getTicket(), is(10.0));
		assertThat(player.getBlockNumber(), is(0));
	}

	@Test
	public void should_get_nothing_while_arrive_Tool_House_and_chose_2_but_no_enough_tickets() {
		double initialMoney = 0.0;
		double initialTicket = 10.0;
		int choseTwo = 2;
		Player player = new Player(initialMoney, initialTicket);
		ToolHouse toolHouse = new ToolHouse();
		toolHouse.action(player, choseTwo);
		assertThat(player.getTicket(), is(10.0));
		assertThat(player.getRobotNumber(), is(0));
	}

	@Test
	public void should_get_nothing_while_arrive_Tool_House_and_chose_3_but_no_enough_tickets() {
		double initialMoney = 0.0;
		double initialTicket = 10.0;
		int choseThree = 3;
		Player player = new Player(initialMoney, initialTicket);
		ToolHouse toolHouse = new ToolHouse();
		toolHouse.action(player, choseThree);
		assertThat(player.getTicket(), is(10.0));
		assertThat(player.getBombNumber(), is(0));
	}

	@Test
	public void pay_pass_fee_100_to_house_owner_while_house_price_is_200_level_is_0() {
		double initialPlayerMoney = 1000.0;
		double initialOwnerMoney = 1000.0;
		String playName = "钱夫人";
		String ownerName = "金贝贝";
		int houseLevel = 0;
		double housePrice = 200.0;
		House house = new House(ownerName, housePrice, houseLevel);
		Player player = new Player(playName, initialPlayerMoney, 0.0);
		Player owner = new Player(ownerName, initialOwnerMoney, 0.0);
		house.action(player, owner);
		assertThat(player.getMoney(), is(900.0));
		assertThat(owner.getMoney(), is(1100.0));
	}

	@Test
	public void pay_pass_fee_200_to_house_owner_while_house_price_is_200_level_is_1() {
		double initialPlayerMoney = 1000.0;
		double initialOwnerMoney = 1000.0;
		String playName = "钱夫人";
		String ownerName = "金贝贝";
		int houseLevel = 1;
		double housePrice = 200.0;
		House house = new House(ownerName, housePrice, houseLevel);
		Player player = new Player(playName, initialPlayerMoney, 0.0);
		Player owner = new Player(ownerName, initialOwnerMoney, 0.0);
		house.action(player, owner);
		assertThat(player.getMoney(), is(800.0));
		assertThat(owner.getMoney(), is(1200.0));
	}

	@Test
	public void pay_pass_fee_400_to_house_owner_while_house_price_is_200_level_is_2() {
		double initialPlayerMoney = 1000.0;
		double initialOwnerMoney = 1000.0;
		String playName = "钱夫人";
		String ownerName = "金贝贝";
		int houseLevel = 2;
		double housePrice = 200.0;
		House house = new House(ownerName, housePrice, houseLevel);
		Player player = new Player(playName, initialPlayerMoney, 0.0);
		Player owner = new Player(ownerName, initialOwnerMoney, 0.0);
		house.action(player, owner);
		assertThat(player.getMoney(), is(600.0));
		assertThat(owner.getMoney(), is(1400.0));
	}

	@Test
	public void pay_pass_fee_800_to_house_owner_while_house_price_is_200_level_is_3() {
		double initialPlayerMoney = 2000.0;
		double initialOwnerMoney = 2000.0;
		String playName = "钱夫人";
		String ownerName = "金贝贝";
		int houseLevel = 3;
		double housePrice = 200.0;
		House house = new House(ownerName, housePrice, houseLevel);
		Player player = new Player(playName, initialPlayerMoney, 0.0);
		Player owner = new Player(ownerName, initialOwnerMoney, 0.0);
		house.action(player, owner);
		assertThat(player.getMoney(), is(1200.0));
		assertThat(owner.getMoney(), is(2800.0));
	}
	@Test
	public void sell_money_should_be_400_while_the_House_is_level_0_with_initial_price_of_200(){
		double initialMoney=200.0;
		int initialLevel=0;
		House house=new House(initialMoney,initialLevel);
		assertThat(house.getSellMoney(),is(400.0));
	}
	@Test
	public void house_in_rich_map_should_have_correct_sell_money(){
		double initialMoney=200.0;
		House house[]=new House[2];
		house[0]=new House(initialMoney,0);
		house[1]=new House(2*initialMoney,0);
		assertThat(house[0].getSellMoney(),is(400.0));
		assertThat(house[1].getSellMoney(),is(800.0));
	}
}
