package Main;

import MapElement.House;
import tool.BlockTool;
import tool.BombTool;
import tool.RobotTool;
import tool.Tool;

import java.util.ArrayList;

public class Player {
	private String name;
	private double money;
	private double ticket;
	private int position;
	private int withGodRounds;
	private int inPrisonRound;
	private ArrayList<House> property = new ArrayList<House>();
	private ArrayList<Tool> playerTool = new ArrayList<Tool>();
	private int inHospitalRound;
	private String mark;

	public Player(int position) {
		this.position = position;
	}

	public Player(double initialMoney, double initialTicket) {
		this.name = "noBody";
		this.money = initialMoney;
		this.ticket = initialTicket;
		this.inHospitalRound = 0;
	}

	public Player(String name, double initialMoney, double initialTicket) {
		this(initialMoney, initialTicket);
		this.name = name;
	}

	public Player() {
		this.name = "testBody";
		this.inPrisonRound = 0;
	}

	public Player(int position, int blockNumber, int bombNumber, int robotNumber) {
		this.position = position;
		addBlock(blockNumber);
		addBomb(bombNumber);
		addRobot(robotNumber);
	}

	private void addRobot(int robotNumber) {
		for (int i = 0; i < robotNumber; i++) {
			playerTool.add(new RobotTool());
		}
	}

	private void addBomb(int bombNumber) {
		for (int i = 0; i < bombNumber; i++) {
			playerTool.add(new BombTool());
		}
	}

	private void addBlock(int blockNumber) {
		for (int i = 0; i < blockNumber; i++) {
			playerTool.add(new BlockTool());
		}
	}

	public Player(String name, int money, String mark) {
		this.name = name;
		this.money = money;
		this.mark = mark;
	}

	public void move(int moveDistance) {
		position += moveDistance;
		position = position < 70 ? position : position - 70;
		System.out.println(getName() + "移动到" + getPosition() + "处");
	}

	public int getPosition() {
		return position;
	}

	public void payMoney(double payMoney) {
		money -= payMoney;
	}

	public double getMoney() {
		return money;
	}

	public void acceptMoney(double acceptMoney) {
		money += acceptMoney;
	}

	public double getTicket() {
		return ticket;
	}

	public void payTicket(double payTicket) {
		ticket -= payTicket;
	}

	public void acceptTicket(double acceptTicket) {
		ticket += acceptTicket;
	}

	public String getName() {
		return name;
	}

	public void setWithGodRounds(int withGodRounds) {
		this.withGodRounds = withGodRounds;
	}

	public Boolean isWithGod() {
		return withGodRounds != 0;
	}

	public int getInPrisonRound() {
		return inPrisonRound;
	}

	public void setInPrisonRound(int inPrisonRound) {
		this.inPrisonRound = inPrisonRound;
	}

	public boolean haveEnoughTicket(double ticket) {
		return getTicket() > ticket;
	}

	public int getToolNumber(Tool tool) {
		int temp = 0;
		for (Tool toolTemp : playerTool) {
			if (tool.getName().equals(toolTemp.getName())) {
				temp++;
			}
		}
		return temp;
	}

	public int getBlockNumber() {
		return getToolNumber(new BlockTool());
	}

	public int getRobotNumber() {
		return getToolNumber(new RobotTool());
	}

	public int getBombNumber() {
		return getToolNumber(new BombTool());
	}

	public void givePassFeeTo(Player owner, double passFee) {
		if (haveEnoughMoney(passFee)) {
			payMoney(passFee);
			owner.acceptMoney(passFee);
		} else bankruptInformation();
	}

	private void bankruptInformation() {
		System.out.println(getName() + "经营不善，申请破产！");
		System.exit(-1);
	}

	public boolean haveEnoughMoney(double passFee) {
		return getMoney() >= passFee;
	}

	public void buyNewHouse(House house) {
		property.add(house);
	}

	public void showDetailInformation() {
		System.out.println(name);
		System.out.println("金钱:" + money);
		System.out.println("彩券:" + ticket);
		System.out.println("位置:" + position);
		System.out.println("地皮:" + getHouseNumber(0));
		System.out.println("茅房:" + getHouseNumber(1));
		System.out.println("洋房:" + getHouseNumber(2));
		System.out.println("摩天楼:" + getHouseNumber(3));
		System.out.println("道具 路障:" + getToolNumber(new BlockTool()) + "个");
		System.out.println("道具 机器人:" + getToolNumber(new RobotTool()) + "个");
		System.out.println("道具 炸弹:" + getToolNumber(new BombTool()) + "个");
	}


	private Tool getTool(int sellToolNumber) {
		switch (sellToolNumber) {
			case 1:
				return new BlockTool();
			case 2:
				return new RobotTool();
			case 3:
				return new BombTool();
			default:
				return null;
		}
	}

	public void sellTool(int sellToolNumber) {
		Tool targetTool = getThisTool(getTool(sellToolNumber));
		if (haveThisTool(targetTool)) {
			acceptTicket(targetTool.getSellTicket());
			playerTool.remove(targetTool);
		} else youDontHaveThisToolInformation();
	}

	private void youDontHaveThisToolInformation() {
		System.out.println(name + ",你没有这个道具！");
	}

	private boolean haveThisTool(Tool tool) {
		return (tool != null) && (getToolNumber(tool) != 0);
	}

	public void useTool(RichMap richMap, int distance, Tool tool) {
		if (haveThisTool(tool)) {
			getThisTool(tool).useTool(richMap, distance);
			playerTool.remove(getThisTool(tool));
		}
	}

	private Tool getThisTool(Tool tool) {
		for (Tool toolTemp : playerTool) {
			if (tool.getName().equals(toolTemp.getName())) {
				return toolTemp;
			}
		}
		return null;
	}

	public int getHouseNumber(int level) {
		int number = 0;
		for (House house : property) {
			if (level == house.getLevel()) {
				number++;
			}
		}
		return number;
	}

	public void sellHouse(int position) {
		if (haveThisHouse(position)) {
			acceptMoney(getThisHouse(position).getSellMoney());
			sellHouseInformation(position);
			getThisHouse(position).initialHouse();
			property.remove(getThisHouse(position));
		} else notHaveThisHouseInformation();
	}

	private House getThisHouse(int position) {
		for (House house : property) {
			if (position == house.getPosition()) {
				return house;
			}
		}
		return null;
	}

	private void sellHouseInformation(int position) {
		System.out.println(name + "以" + getThisHouse(position).getSellMoney() + "的高价卖掉了一块地，大家快来抢啊！");
	}

	private boolean haveThisHouse(int position) {
		for (House house : property) {
			if (position == house.getPosition()) {
				return true;
			}
		}
		return false;
	}

	private void notHaveThisHouseInformation() {
		System.out.println(name + "您并没有此处房产所有权！");
	}

	public void moveTo(int position) {
		this.position = position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public void setInHospitalRound(int inHospitalRound) {
		this.inHospitalRound = inHospitalRound;
	}

	public void passOneRoundInPrison() {
		this.inPrisonRound--;
	}

	public int getInHospitalRound() {
		return inHospitalRound;
	}

	public void passOneRoundInHospital() {
		this.inHospitalRound--;
	}

	public String getMark() {
		return mark;
	}

	public boolean isNotBroken() {
		if (money < 0) {
			System.out.println(name + "经营不善，输了个精光，退出游戏！");
			return false;
		} else return true;
	}

	public boolean withGodProtect() {
		if (withGodRounds > 0) {
			System.out.println("福神附身，可免过路费!");
			return true;
		} else return false;
	}

	public boolean isNotFree() {
		return (inHospitalRound > 0) || (inPrisonRound > 0);
	}

	public void passOneRoundWithGod() {
		withGodRounds--;
	}

	public void addHouse(House house) {
		property.add(house);
	}

	public void addTool(Tool tool) {
		playerTool.add(tool);
	}
}
