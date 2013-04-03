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
	private Tool robot = new RobotTool();
	private Tool block = new BlockTool();
	private Tool bomb = new BombTool();
	private int houseNumber[] = new int[4];
	private ArrayList<House> property=new ArrayList<House>();
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
		this.block = new BlockTool(blockNumber);
		this.bomb = new BombTool(bombNumber);
		this.robot = new RobotTool(robotNumber);
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
		if (getTicket() > ticket) {
			return true;
		} else {
			System.out.println(getName() + "点券不足！");
			return false;
		}
	}

	public int getBlockNumber() {
		return block.getNumber();
	}

	public int getRobotNumber() {
		return robot.getNumber();
	}

	public int getBombNumber() {
		return bomb.getNumber();
	}

	public void addBlock() {
		block.addOne();
	}

	public void addRobot() {
		robot.addOne();
	}

	public void addBomb() {
		bomb.addOne();
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
		System.out.println("道具 机器人:" + robot.getNumber() + "个");
		System.out.println("道具 炸弹:" + bomb.getNumber() + "个");
		System.out.println("道具 路障:" + block.getNumber() + "个");
	}

	public Tool getBlockTool() {
		return block;
	}

	public Tool getBombTool() {
		return bomb;
	}

	public Tool getRobotTool() {
		return robot;
	}

	public Tool getTool(int sellToolNumber) {
		switch (sellToolNumber) {
			case 1:
				return block;
			case 2:
				return robot;
			case 3:
				return bomb;
			default:
				return null;
		}
	}

	public void sellTool(int sellToolNumber) {
		Tool targetTool = getTool(sellToolNumber);
		if (haveThisTool(targetTool)) {
			acceptTicket(targetTool.getSellTicket());
			targetTool.minusOne();
		} else youDontHaveThisToolInformation();
	}

	private void youDontHaveThisToolInformation() {
		System.out.println(name + ",你没有这个道具！");
	}

	private boolean haveThisTool(Tool tool) {
		return (tool != null) && (tool.getNumber() != 0);
	}

	public void setBlock(RichMap richMap, int blockDistance) {
		getBlockTool().useTool(richMap, blockDistance);
		getBlockTool().minusOne();
	}

	public void setBomb(RichMap richMap, int bombDistance) {
		getBombTool().useTool(richMap, bombDistance);
		getBombTool().minusOne();
	}

	public void setRobot(RichMap richMap) {
		getRobotTool().useTool(richMap, getPosition());
		getRobotTool().minusOne();
	}

	public void getUpdateHouse(int level) {
		houseNumber[level]--;
		houseNumber[level + 1]++;
	}

	public int getHouseNumber(int level) {
		int number=0;
		for(House house:property){
			if(level==house.getLevel()){
				number++;
			}
		}
		return number;
	}

	public void sellHouse(int position) {
		if(haveThisHouse(position)){
			acceptMoney(getThisHouse(position).getSellMoney());
			sellHouseInformation(position);
			getThisHouse(position).initialHouse();
			property.remove(getThisHouse(position));
		}
		else notHaveThisHouseInformation();
	}

	private House getThisHouse(int position) {
		for(House house:property){
			if(position==house.getPosition()){
				return house;
			}
		}
		return null;
	}

	private void sellHouseInformation(int position) {
		System.out.println(name + "以" + property.get(position).getSellMoney() + "的高价卖掉了一块地，大家快来抢啊！");
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
		System.out.println(name+"您并没有此处房产所有权！");
	}

	public void setBlockNumber(int number) {
		block.setNumber(number);
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

	public void setName(String name) {
		this.name = name;
	}
}
