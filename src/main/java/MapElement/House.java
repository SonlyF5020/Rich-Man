package MapElement;

import Main.Player;

import java.util.Scanner;

public class House extends MapElement {
	private double price;
	private int level;

	public House(double initialPrice, int houseLevel) {
		super("free");
		this.price = initialPrice;
		this.level = houseLevel;
	}

	public House(String owner, double initialPrice, int initialLevel) {
		super(owner);
		this.price = initialPrice;
		this.level = initialLevel;
	}

	@Override
	public void elementEvent(Player player, Player owner) {
		action(player, owner);
	}

	@Override
	public void sellMapElement(Player player) {
		if (thisIsMyHouse(player)) {
			player.acceptMoney(getSellMoney());
			player.sellHouse(level);
			sellHouseInformation(player);
			initialHouse();
		} else notYourHouseInformation();
	}

	@Override
	public String getMark() {
		return "" + level;
	}

	private void notYourHouseInformation() {
		System.out.println("这是" + getOwner() + "的房子哦亲！");
	}

	private void sellHouseInformation(Player player) {
		System.out.println(player.getName() + "以" + getSellMoney() + "的高价卖掉了一块地，大家快来抢啊！");
	}

	private boolean thisIsMyHouse(Player player) {
		return player.getName().equals(getOwner());
	}

	public void action(Player player, Player owner) {
		if (isMyHouse(player)) {
			updateOrNot(player);
		} else if (isFreeHouse()) {
			buyOrNot(player);
		} else if (player.withGodProtect()||owner.isNotFree()) {
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
			player.buyNewHouse();
			setOwner(player.getName());
		} else noEnoughMoneyInformation(player);
	}

	private boolean isFreeHouse() {
		return getOwner().equals("free");
	}

	private boolean isMyHouse(Player player) {
		return player.getName().equals(getOwner());
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
				player.getUpdateHouse(getLevel());
				levelUp();
				player.payMoney(getPrice());
			} else noEnoughMoneyInformation(player);
		} else houseIsFullLevelInformation();
	}

	private boolean choseYes() {
		Scanner scanner = new Scanner(System.in);
		String choseResult = scanner.nextLine();
		return choseResult.equals("Y") || choseResult.equals("y");
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
		setOwner("free");
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