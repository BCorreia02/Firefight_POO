package pt.iul.poo.firefight.starterpack;

import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Point2D;

public class Pine extends GameElement{

	private static final String name ="pine";
	private static final int layer = 0; 
	private static Point2D position;
	private static int durability=0;
	private static  int limitdurability=10;

	public Pine(Point2D position) {
		super(position,name,layer);
	}

	public static int getLimitDurability() {
		return limitdurability;
	}

	public boolean isConsumable() {
		return limitdurability<=durability;
	}


	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getLayer() {
		return layer;
	}

	public int getDurability(){
		return durability;
	}

	public void increaseDurability(){
		durability++;
	}

	@Override
	public boolean isBurnable(){
		return true;
	}
	
	public void consumir() {
		setName("burntpine");
	}

}

