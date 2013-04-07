package MapElement;

import Main.Player;

import java.util.Scanner;

public class GiftHouse extends MapElement {
	private static final double MONEY_GIFT = 2000.0;
	private static final double TICKET_GIFT = 200.0;
	private static final int GOD_GIFT = 5;
	private static final String GIFT_HOUSE_MARK = "G";

	@Override
	public void elementEvent(Player player, Player owner) {
		presentInformation(player);
		action(player, playerChose());
	}

	@Override
	public String getInitialMark() {
		return GIFT_HOUSE_MARK;
	}

	public void action(Player player, int choseResult) {
		switch (choseResult) {
			case 1:
				moneyGift(player);
				moneyGiftInformation(player);
				break;
			case 2:
				ticketGift(player);
				tickeyGiftInformation(player);
				break;
			case 3:
				godGift(player);
				godGiftInformation(player);
				break;
			default:
				choseNothingInformation(player);
				break;
		}
	}

	private void godGift(Player player) {
		player.setWithGodRounds(GOD_GIFT);
	}

	private void ticketGift(Player player) {
		player.acceptTicket(TICKET_GIFT);
	}

	private void moneyGift(Player player) {
		player.acceptMoney(MONEY_GIFT);
	}

	private int playerChose() {
		Scanner scanner = new Scanner(System.in);
		String choseResult = scanner.nextLine();
		return Integer.parseInt(choseResult);
	}

	private void presentInformation(Player player) {
		System.out.println(player.getName() + "，欢迎来到礼品屋！有三种礼品，1>2000金钱  2>200彩券  3>福神附体");
	}

	private void godGiftInformation(Player player) {
		System.out.println(player.getName() + "从礼物屋选择了福神！ 价值连城");
	}

	private void tickeyGiftInformation(Player player) {
		System.out.println(player.getName() + "从礼物屋选择了点券礼物！ 价值200点");
	}

	private void moneyGiftInformation(Player player) {
		System.out.println(player.getName() + "从礼物屋选择了现金礼物！ 价值2000元");
	}

	private void choseNothingInformation(Player player) {
		System.out.println(player.getName() + "有礼物恐惧症，没有选择礼物就走了");
	}

	public GiftHouse(int position) {
		super("giftHouse", position);
	}

	public GiftHouse() {
		this(0);
	}
}
