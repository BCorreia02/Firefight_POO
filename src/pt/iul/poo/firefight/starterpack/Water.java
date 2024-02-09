package pt.iul.poo.firefight.starterpack;
import pt.iul.ista.poo.utils.*;

public class Water extends GameElement{

	private static String name="water";
	private static final int layer = 4; 
	private static Point2D position;

	public Water(Point2D position){
		super(position, name, layer);

	}

	public void aguaDirection(Direction dir, Point2D p) {    

		if(dir.equals(Direction.UP)) {
			Water water = new Water(p);
			water.setName("water_up");
			GameEngine.getInstance().addElement(water);

		} else if(dir.equals(Direction.DOWN)) {
			Water water = new Water(p);
			water.setName("water_down");
			GameEngine.getInstance().addElement(water);

		} else if(dir.equals(Direction.RIGHT)) {
			Water water = new Water(p);
			water.setName("water_right");
			GameEngine.getInstance().addElement(water);

		} else if(dir.equals(Direction.LEFT)) {
			Water water = new Water(p);
			water.setName("water_left");
			GameEngine.getInstance().addElement(water);

		}
	}

	public void setName(String name){
		this.name=name;
	}

	@Override
	public boolean isBurnable() {
		return false;
	}


}

