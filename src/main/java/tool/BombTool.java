package tool;

import Main.Player;
import Main.RichMap;

public class BombTool extends Tool {
	private static final double BOMB_PRICE = 50.0;
	public static final int BOMB_EFFECT_ROUND=5;
	private static final String BOMB_MARK="@";

	public BombTool(int number) {
		super(number);
	}

	public BombTool() {
		super();

	}

	@Override
	public void useTool(RichMap map, int position) {
		System.out.println("地图"+position+"处出现炸弹！兄弟们带宝石啊！");
		map.setBomb(position);
	}

	@Override
	public void trigger(Player player, int position) {
		System.out.println(player.getName()+"被"+position+"处的炸弹炸伤，送往医院就医5个回合！");
		player.setPosition(RichMap.HOSPITAL);
		player.setInHospitalRound(BOMB_EFFECT_ROUND);
	}

	@Override
	public double getPrice() {
		return BOMB_PRICE;
	}

	@Override
	public double getSellTicket() {
		return BOMB_PRICE / 2;
	}

	public String getMark(){
		return BOMB_MARK;
	}
}
