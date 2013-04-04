package Main;

import MapElement.*;

public class RichMap {
	public static final int START = 0;
	public static final int HOSPITAL = 14;
	public static final int TOOL_HOUSE = 28;
	public static final int GIFT_HOUSE = 35;
	public static final int PRISON = 49;
	public static final int MAGIC_HOUSE = 63;
	public static final int TOTAL_POINT = 70;
	MapElement[] totalMap = new MapElement[TOTAL_POINT];

	public RichMap() {
		totalMap[START] = new Start(START);
		totalMap[HOSPITAL] = new Hospital(HOSPITAL);
		totalMap[TOOL_HOUSE] = new ToolHouse(TOOL_HOUSE);
		totalMap[GIFT_HOUSE] = new GiftHouse(GIFT_HOUSE);
		totalMap[PRISON] = new Prison(PRISON);
		totalMap[MAGIC_HOUSE] = new MagicHouse(MAGIC_HOUSE);

		houseSequence(START, HOSPITAL, 200.0);
		houseSequence(HOSPITAL, TOOL_HOUSE, 200.0);
		houseSequence(TOOL_HOUSE, GIFT_HOUSE, 500.0);
		houseSequence(GIFT_HOUSE, PRISON, 300.0);
		houseSequence(PRISON, MAGIC_HOUSE, 300.0);

		totalMap[MAGIC_HOUSE + 1] = new TicketField(20,MAGIC_HOUSE + 1);
		totalMap[MAGIC_HOUSE + 2] = new TicketField(80,MAGIC_HOUSE + 2);
		totalMap[MAGIC_HOUSE + 3] = new TicketField(100,MAGIC_HOUSE + 3);
		totalMap[MAGIC_HOUSE + 4] = new TicketField(40,MAGIC_HOUSE + 4);
		totalMap[MAGIC_HOUSE + 5] = new TicketField(80,MAGIC_HOUSE + 5);
		totalMap[MAGIC_HOUSE + 6] = new TicketField(60,MAGIC_HOUSE + 6);
	}

	private void houseSequence(int begin, int end, double price) {
		for (int i = begin + 1; i < end; i++) {
			totalMap[i] = new House(price, 0,i);
		}
	}

	public Player getOwner(int initialPosition) {
		return totalMap[initialPosition].getOwner();
	}

	public void setBomb(int position) {
		totalMap[position].setBomb();
	}

	public void setBlock(int position) {
		totalMap[position].setBlock();
	}

	public void clearTool(int centerPosition) {
		for (int i = centerPosition - 10; i < centerPosition + 10; i++) {
			totalMap[initial(i)].clearBlockAndBomb();
		}
	}

	public static int initial(int position) {
		if (position < 0) return position + TOTAL_POINT;
		if (position >= TOTAL_POINT) return position - TOTAL_POINT;
		else return position;
	}

	public void setOwner(int position, Player player) {
		totalMap[position].setOwner(player);
		player.addHouse((House)totalMap[position]);
	}

	public void eventHappen(Player player) {
		Player ownerPlayer = totalMap[player.getPosition()].getOwner();
		totalMap[player.getPosition()].elementEvent(player, ownerPlayer);
	}

	public boolean notMeetBlockAndBomb(Player player, int moveDistance) {
		boolean notMeetBlockOrBomb = true;
		for (int i = player.getPosition(); i < player.getPosition() + moveDistance; i++) {
			if(!totalMap[i].getTools().isEmpty()){
				totalMap[i].getTools().get(0).trigger(player,i);
				notMeetBlockOrBomb = false;
			}
		}
		return notMeetBlockOrBomb;
	}

	public void showMap(Main main) {
		for (int i = START; i <= TOOL_HOUSE; i++) {
			System.out.print(totalMap[i].getMark(main, i));
		}
		System.out.println();
		System.out.println(totalMap[initial(START - 1)].getMark(main, initial(START - 1)) + "                                                  " + totalMap[initial(TOOL_HOUSE + 1)].getMark(main, initial(TOOL_HOUSE - 1)));
		System.out.println(totalMap[initial(START - 2)].getMark(main, initial(START - 2)) + "                                                  " + totalMap[initial(TOOL_HOUSE + 2)].getMark(main, initial(TOOL_HOUSE - 2)));
		System.out.println(totalMap[initial(START - 3)].getMark(main, initial(START - 3)) + "                                                  " + totalMap[initial(TOOL_HOUSE + 3)].getMark(main, initial(TOOL_HOUSE - 3)));
		System.out.println(totalMap[initial(START - 4)].getMark(main, initial(START - 4)) + "                                                  " + totalMap[initial(TOOL_HOUSE + 4)].getMark(main, initial(TOOL_HOUSE - 4)));
		System.out.println(totalMap[initial(START - 5)].getMark(main, initial(START - 5)) + "                                                  " + totalMap[initial(TOOL_HOUSE + 5)].getMark(main, initial(TOOL_HOUSE - 5)));
		System.out.println(totalMap[initial(START - 6)].getMark(main, initial(START - 6)) + "                                                  " + totalMap[initial(TOOL_HOUSE + 6)].getMark(main, initial(TOOL_HOUSE - 6)));
		for (int i = MAGIC_HOUSE; i >= GIFT_HOUSE; i--) {
			System.out.print(totalMap[i].getMark(main, i));
		}
		System.out.println();
	}

	public Boolean isToolHere(int position) {
		return totalMap[position].isToolHere();
	}

}
