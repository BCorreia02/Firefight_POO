package pt.iul.poo.firefight.starterpack;

import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Point2D;

public class Eucaliptus extends GameElement implements isBurnable{


	private static final String name ="eucaliptus";
	private static final int layer = 0;
	private static Point2D position;
	private static int durability=0;
	private static  int limitdurability=5;

	public Eucaliptus(Point2D position){
		super(position,name,layer);
	}

	public boolean isConsumable() {
		return limitdurability<=durability ;
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
		setName("burnteucaliptus");
	}
}
