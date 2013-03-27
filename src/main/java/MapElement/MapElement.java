package MapElement;

import Main.Main;
import Main.Player;
import tool.BlockTool;
import tool.BombTool;

public abstract class MapElement {
	private String owner;
	BombTool bombHere;
	BlockTool blockHere;

	public MapElement() {
		this.owner = "free";
		bombHere = null;
		blockHere = null;
	}

	public MapElement(String name) {
		this.owner = name;
	}

	public abstract void elementEvent(Player player, Player owner);

	public abstract void sellMapElement(Player currentPlayer);

	public abstract String getMark();


	public String getOwner() {
		return owner;
	}

	public void setBomb() {
		bombHere=new BombTool();
	}

	public BombTool getBombHere(){
		return bombHere;
	}

	public BlockTool getBlockHere(){
		return blockHere;
	}

	public void setBlock() {
		blockHere=new BlockTool();
	}

	public void clearBlockAndBomb() {
		bombHere=null;
		blockHere=null;
	}

	public boolean blockIsHere() {
		return blockHere!=null;
	}

	public boolean bombIsHere() {
		return bombHere!=null;
	}

	public void setPlayer(Player owner) {
		this.owner = owner.getName();
		owner.addHouse(0);
	}

	public void setOwner(String name) {
		this.owner = name;
	}

	public String getMark(Main main,int position) {
		if(main.getStandPlayer(position)!=null){
			return main.getStandPlayer(position).getMark();
		}
		if(bombIsHere()){
			return bombHere.getMark();
		}
		if(blockIsHere()){
			return blockHere.getMark();
		}
		else return getMark();
	}
}

