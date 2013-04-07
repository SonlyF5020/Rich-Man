package MapElement;

import Main.Player;

public class MagicHouse extends MapElement {
	private static final String MAGIC_HOUSE_MARK = "M";

	public MagicHouse(int position) {
		super("magicHouse", position);
	}

	@Override
	public void elementEvent(Player player, Player owner) {
		arriveMagicHouseInformation(player);
	}

	@Override
	public String getInitialMark() {
		return MAGIC_HOUSE_MARK;
	}


	private void arriveMagicHouseInformation(Player player) {
		System.out.println(player.getName() + "，欢迎来到魔法屋！");
	}
}
