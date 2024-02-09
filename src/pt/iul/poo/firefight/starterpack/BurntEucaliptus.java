package pt.iul.poo.firefight.starterpack;

import pt.iul.ista.poo.utils.Point2D;

public class BurntEucaliptus extends GameElement{

	private static final String name ="burnteucaliptus";
	private static final int layer = 0;
	private static Point2D position;

	public BurntEucaliptus(Point2D position){
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
