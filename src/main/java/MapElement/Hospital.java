package MapElement;

import Main.Player;

public class Hospital extends MapElement {
	private static final String HOSPITAL_MARK="H";

	public Hospital(int position) {
		super("hospital",position);
	}

	@Override
	public void elementEvent(Player player, Player owner) {
		arriveHospitalInformation(player);
	}


	@Override
	public String getMark() {
		return HOSPITAL_MARK;
	}


	private void arriveHospitalInformation(Player player) {
		System.out.println(player.getName() + "在医院");
	}

}
