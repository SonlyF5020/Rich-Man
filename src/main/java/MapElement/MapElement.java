package MapElement;

import Main.Main;
import Main.NullPlayer;
import Main.Player;
import tool.BlockTool;
import tool.BombTool;
import tool.Tool;

import java.util.ArrayList;

public abstract class MapElement {
	private Player owner;
	private int position;
	private ArrayList<Tool> toolsAtHere = new ArrayList<Tool>();
	private ArrayList<Player> playersAtHere = new ArrayList<Player>();

	public MapElement() {
		this.owner = new NullPlayer();
	}

	public MapElement(String name, int position) {
		this.owner = new NullPlayer(name);
		this.position = position;
	}

	public MapElement(String name) {
		this.owner = new NullPlayer(name);
	}

	public abstract void elementEvent(Player player, Player owner);

	public abstract String getInitialMark();


	public Player getOwner() {
		return owner;
	}

	public void setBomb() {
		toolsAtHere.add(new BombTool());
	}

	public void setBlock() {
		toolsAtHere.add(new BlockTool());
	}

	public void clearBlockAndBomb() {
		toolsAtHere.clear();
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public String getMark() {
		if (!playersAtHere.isEmpty()) {
			return playersAtHere.get(playersAtHere.size()-1).getMark();
		}
		if (!toolsAtHere.isEmpty()) {
			return toolsAtHere.get(toolsAtHere.size() - 1).getMark();
		} else return getInitialMark();
	}

	public ArrayList<Tool> getToolsAtHere() {
		return toolsAtHere;
	}

	public Boolean isToolHere() {
		return !toolsAtHere.isEmpty();
	}

	public int getPosition() {
		return position;
	}

	public void comeOnePlayer(Player player) {
		playersAtHere.add(player);
	}


	public void leaveOnePlayer(Player player) {
		playersAtHere.remove(player);
	}
}

