package tool;

import Main.Player;
import Main.RichMap;

public class BlockTool extends Tool {
	private static final double BLOCK_PRICE = 50.0;
	private static final String BLOCK_MARK="#";

	public BlockTool(int number) {
		super(number);
	}

	public BlockTool() {
		super();
	}

	@Override
	public void useTool(RichMap map, int position) {
		System.out.println("地图"+position+"处出现路障！兄弟们注意带粉啊！");
		map.setBlock(position);
	}

	@Override
	public void trigger(Player player, int position) {
		System.out.println(player.getName()+"遇到"+position+"处的路障，停留在该处！");
		player.setPosition(position);
	}


	@Override
	public double getPrice() {
		return BLOCK_PRICE;
	}

	@Override
	public double getSellTicket() {
		return BLOCK_PRICE / 2;
	}

	@Override
	public String getMark(){
		return BLOCK_MARK;
	}

}
