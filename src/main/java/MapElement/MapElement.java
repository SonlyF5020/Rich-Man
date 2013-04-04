package MapElement;

import  Main.*;
import tool.*;

import java.awt.*;
import java.util.ArrayList;

public abstract class MapElement {
	private Player owner;
	private int position;
	ArrayList <Tool> tools = new ArrayList();

	public MapElement() {
		this.owner = new NullPlayer();
	}

	public MapElement(String name,int position){
		this.owner=new NullPlayer(name);
		this.position=position;
	}

	public MapElement(String name) {
		this.owner = new NullPlayer(name);
	}

	public abstract void elementEvent(Player player, Player owner);

	public abstract String getMark();


	public Player getOwner() {
		return owner;
	}

	public void setBomb() {
		tools.add(new BombTool());
	}

	public void setBlock() {
		tools.add(new BlockTool());
	}

	public void clearBlockAndBomb() {
		tools.clear();
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public String getMark(Main main, int position) {
		if (main.getStandPlayer(position) != null) {
			return main.getStandPlayer(position).getMark();
		}
		if (!tools.isEmpty()) {
			return tools.get(tools.size()).getMark();
		} else return getMark();
	}

	public ArrayList<Tool> getTools() {
		return tools;
	}

	public Boolean isToolHere() {
		return !tools.isEmpty();
	}

	public int getPosition() {
		return position;
	}
}

