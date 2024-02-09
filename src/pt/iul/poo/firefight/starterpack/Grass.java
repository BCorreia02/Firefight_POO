package pt.iul.poo.firefight.starterpack;

import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Point2D;

public class Grass extends GameElement implements isBurnable{

	private Point2D position;
	private  static final String name = "grass";
	private  static final int layer = 0;
	private static  int durability=0;
	private static  int limitdurability=3;


	public Grass(Point2D position) {
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
		return "grass";
	}	

	@Override
	public int getLayer() {
		return 0;
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
		setName("burntgrass");
	}
}
