package MapElement;

import Main.NullPlayer;
import Main.Player;

import java.util.Scanner;

public class House extends MapElement {
	private double price;
	private int level;

	public House(double initialPrice, int houseLevel, int position) {
		super("free", position);
		this.price = initialPrice;
		this.level = houseLevel;
	}

	public House(String owner, double initialPrice, int initialLevel) {
		super(owner);
		this.price = initialPrice;
		this.level = initialLevel;
	}

	public House(double housePrice, int houseLevel) {
		this.price = housePrice;
		this.level = houseLevel;
	}

	@Override
	public void elementEvent(Player player, Player owner) {
		action(player, owner);
	}


	@Override
	public String getInitialMark() {
		return "" + level;
	}


	public void action(Player player, Player owner) {
		if (isMyHouse(player)) {
			updateOrNot(player);
		} else if (isFreeHouse()) {
			buyOrNot(player);
		} else if (player.withGodProtect() || owner.isNotFree()) {
		} else {
			arriveOthersHouseInformation(player, owner, getPassFee());
			player.givePassFeeTo(owner, getPassFee());
			givePassFeeInformation(player, owner);
		}
	}

	private void givePassFeeInformation(Player player, Player owner) {
		System.out.println(player.getName() + "极不情愿的给了" + owner.getName() + getPassFee() + "元过路费");
	}

	private void buyOrNot(Player player) {
		buyOrNotInformation(player);
		if (choseYes()) {
			buyFreeHouse(player);
		} else notBuyFreeHouseInformation(player);
	}

	private void buyOrNotInformation(Player player) {
		System.out.println(player.getName() + ",你来到一处空地，请问是否购买此处空地？只需" + price + "元！");
	}

	private void buyFreeHouse(Player player) {
		if (player.haveEnoughMoney(getPrice())) {
			player.payMoney(getPrice());
			player.buyNewHouse(this);
			setOwner(player);
		} else noEnoughMoneyInformation(player);
	}

	private boolean isFreeHouse() {
		return getOwner().getName().equals("free");
	}

	private boolean isMyHouse(Player player) {
		return player.equals(getOwner());
	}

	private void updateOrNot(Player player) {
		updateOrNotInformation(player);
		if (choseYes()) {
			update(player);
		} else notUpdateInformation(player);
	}

	private void updateOrNotInformation(Player player) {
		System.out.println(player.getName() + ",欢迎回家，请问您是否升级该处房产？");
	}

	private void arriveOthersHouseInformation(Player player, Player owner, double passFee) {
		System.out.println(player.getName() + "来到了" + owner.getName() + "的地盘，交纳过路费" + passFee + "元");
	}

	private void notUpdateInformation(Player player) {
		System.out.println(player.getName() + "放弃了升级自己房屋的宝贵机会！");
	}

	private void houseIsFullLevelInformation() {
		System.out.println("该处房屋已经升级为摩天楼，不可再次升级了！");
	}

	private void notBuyFreeHouseInformation(Player player) {
		System.out.println(player.getName() + "没有购买这块空地！");
	}

	private void noEnoughMoneyInformation(Player player) {
		System.out.println(player.getName() + "金钱不足！");
	}

	public void update(Player player) {
		if (levelIsNotFull()) {
			if (player.haveEnoughMoney(getPrice())) {
				levelUp();
				player.payMoney(getPrice());
			} else noEnoughMoneyInformation(player);
		} else houseIsFullLevelInformation();
	}

	private boolean choseYes() {
		Scanner scanner = new Scanner(System.in);
		String choseResult = scanner.nextLine();
		return "y".equalsIgnoreCase(choseResult);
	}

	private void levelUp() {
		level++;
	}

	private boolean levelIsNotFull() {
		return level < 3;
	}

	public int getLevel() {
		return level;
	}

	public void initialHouse() {
		setOwner(new NullPlayer("free"));
		level = 0;
	}

	public double getPassFee() {
		double passFee = price / 2;
		for (int i = 0; i < level; i++) {
			passFee *= 2;
		}
		return passFee;
	}

	public double getPrice() {
		return price;
	}

	public double getSellMoney() {
		return (double) (2 * level + 2) * price;
	}
}
