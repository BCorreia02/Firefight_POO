package pt.iul.poo.firefight.starterpack;

import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Point2D;

public abstract class GameElement implements ImageTile, isBurnable{

	private Point2D position;
	private String name;
	private final int layer;

	public GameElement(Point2D position, String name, int layer) {
		this.position = position;
		this.name=name;
		this.layer=layer;
	}

	@Override
	public Point2D getPosition() {
		return position;
	}

	public void setPosition(Point2D position) {
		this.position = position;
	}

	public void setName(String name) {
		this.name=name;
	}

	public String getName() {
		return name;
	}

	public int getLayer() {
		return layer;
	}
}
