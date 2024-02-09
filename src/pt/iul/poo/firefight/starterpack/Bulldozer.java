package pt.iul.poo.firefight.starterpack;

import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;
import java.awt.event.KeyEvent;

public class Bulldozer extends GameElement {

	private static String name ="bulldozer";
	private static final int layer = 2;
	private static Point2D position;

	public Bulldozer(Point2D position) {

		super(position,name,layer);

	}

	public boolean canMoveTo(Point2D p) {

		if (p.getX() < 0) return false;
		if (p.getY() < 0) return false;
		if (p.getX() >= GameEngine.GRID_WIDTH) return false;
		if (p.getY() >= GameEngine.GRID_HEIGHT) return false;
		return true;
	}

	

	public void removerVegetacao(Point2D position){
		GameElement aux=null;
		for(GameElement a: GameEngine.getInstance().getTileList()){
			if(a.getPosition().equals(position)){
				aux=a;
			}
		}
		GameEngine.getInstance().removeGuiElement(aux);
		GameEngine.getInstance().removeListElement(aux);
		GameEngine.getInstance().addListElement(new OpenLand(position));
		GameEngine.getInstance().addGuiElement(new OpenLand(position));
	}

	public void move(Direction direction){
		
		switch(direction){

		case UP:
			setName("bulldozer_up");
			break;

		case DOWN:
			setName("bulldozer_down");
			break;

		case RIGHT:
			setName("bulldozer_right");
			break;

		case LEFT:
			setName("bulldozer_left");
			break;
		}
		
		Point2D newPosition = super.getPosition().plus(direction.asVector());

		if(GameEngine.getInstance().estaAArder(newPosition)){
			
		}else if(canMoveTo(newPosition)){
			
			GameEngine.getInstance().getLevel().getScore().setPontos(-5);
			removerVegetacao(newPosition);
			setPosition(newPosition);
			
			if(GameEngine.getInstance().searchPlane())
				GameEngine.getInstance().getPlane().move();
			
		}
	}

	@Override
	public boolean isBurnable() {
		return false;
	}


}
