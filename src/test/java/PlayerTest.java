import Main.Player;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PlayerTest {
	@Test
	public void position_should_be_5_after_moved_5_while_initial_position_is_0() {
		int moveDistance = 5;
		int initialPosition = 0;
		Player player = new Player(initialPosition);
		player.move(moveDistance);
		assertThat(player.getPosition(), is(5));
	}

	@Test
	public void position_should_be_3_after_moved_5_while_initial_position_is_68() {
		int moveDistance = 5;
		int initialPosition = 68;
		Player player = new Player(initialPosition);
		player.move(moveDistance);
		assertThat(player.getPosition(), is(3));
	}

	@Test
	public void money_should_be_40_after_payed_60_while_initial_money_is_100() {
		double payMoney = 60.0;
		double initialMoney = 100.0;
		double initialTicket = 0.0;
		Player player = new Player(initialMoney, initialTicket);
		player.payMoney(payMoney);
		assertThat(player.getMoney(), is(40.0));
	}

	@Test
	public void money_should_be_130_after_accept_30_while_initial_money_is_100() {
		double acceptMoney = 30.0;
		double initialMoney = 100.00;
		double initialTicket = 0.0;
		Player player = new Player(initialMoney, initialTicket);
		player.acceptMoney(acceptMoney);
		assertThat(player.getMoney(), is(130.0));
	}

	@Test
	public void ticket_should_be_10_after_payed_20_while_initial_ticket_is_30() {
		double payTicket = 20.0;
		double initialTicket = 30.0;
		double initialMoney = 0.0;
		Player player = new Player(initialMoney, initialTicket);
		player.payTicket(payTicket);
		assertThat(player.getTicket(), is(10.0));
	}

	@Test
	public void ticket_should_be_50_after_accept_20_while_initial_ticket_is_30() {
		double acceptTicket = 20.0;
		double initialTicket = 30.0;
		double initialMoney = 0.0;
		Player player = new Player(initialMoney, initialTicket);
		player.acceptTicket(acceptTicket);
		assertThat(player.getTicket(), is(50.0));
	}
}
