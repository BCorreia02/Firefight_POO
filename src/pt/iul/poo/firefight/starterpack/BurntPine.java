package pt.iul.poo.firefight.starterpack;

import pt.iul.ista.poo.utils.Point2D;

public class BurntPine extends GameElement{

	private static final String name ="burntpine";
	private static final int layer = 0;
	private static Point2D position;

	public BurntPine(Point2D position){
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
	public boolean isBurnable() {
		// TODO Auto-generated method stub
		return false;
	}

}
