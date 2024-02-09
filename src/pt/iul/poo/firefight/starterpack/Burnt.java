package pt.iul.poo.firefight.starterpack;

import pt.iul.ista.poo.utils.Point2D;

public abstract class Burnt extends GameElement {
	
	private static final String name ="burnt";
	private static final int layer = 0;
	private static Point2D position;

	public Burnt(Point2D position){
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

}
