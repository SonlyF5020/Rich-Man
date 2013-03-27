package MapElement;

import Main.Player;

public class TicketField extends MapElement {
	private static final String TICKET_FIELD_MARK="$";
	private double ticket;

	public TicketField(double ticket) {
		super();
		this.ticket = ticket;
	}

	@Override
	public void elementEvent(Player player, Player owner) {
		arriveTicketFieldInformation(player, getTicket());
		action(player);
	}

	@Override
	public void sellMapElement(Player player) {
		System.out.println(player.getName() + ",这里是矿场哦！");
	}

	@Override
	public String getMark() {
		return TICKET_FIELD_MARK;
	}


	private void arriveTicketFieldInformation(Player player, double ticket) {
		System.out.println(player.getName() + "到达矿场，获得" + ticket + "点彩券！");
	}

	public void action(Player player) {
		player.acceptTicket(getTicket());
	}

	private double getTicket() {
		return ticket;
	}
}
