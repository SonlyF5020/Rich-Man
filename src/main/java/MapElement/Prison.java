package MapElement;

import Main.Player;

public class Prison extends MapElement {
	private static final String PRISON_MARK="P";
	private static final int PRISON_DAY = 2;

	public Prison() {
		super("prison");
	}

	@Override
	public void elementEvent(Player player, Player owner) {
		arrivePrisonInformation(player);
		setPrison(player);
	}

	@Override
	public void sellMapElement(Player player) {
		System.out.println(player.getName() + ",这里是监狱哦！");
	}

	@Override
	public String getMark() {
		return PRISON_MARK;
	}


	private void arrivePrisonInformation(Player player) {
		System.out.println(player.getName() + "来到了监狱，不幸被城管逮住了！");
	}

	public void setPrison(Player player) {
		player.setInPrisonRound(PRISON_DAY);
	}
}
