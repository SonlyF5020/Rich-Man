package MapElement;

import Main.Player;

public class Start extends MapElement {
	private static final String START_MARK = "S";

	public Start(int position) {
		super("start", position);
	}

	public void elementEvent(Player player, Player owner) {
		arriveStartInformation(player);
	}

	@Override
	public String getInitialMark() {
		return START_MARK;
	}


	private void arriveStartInformation(Player player) {
		System.out.println(player.getName() + "，欢迎回到起点，梦开始的地方！");
	}
}
