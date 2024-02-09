package pt.iul.poo.firefight.starterpack;

import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Point2D;

public class OpenLand extends GameElement {
	
	private static final String name ="land";
	private static final int layer = 0;
	private static Point2D position;
		

	public OpenLand(Point2D position) {
		super(position,name,layer);
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getLayer() {
		return layer;
	}

	@Override
	public boolean isBurnable(){
		return false;
	}
}