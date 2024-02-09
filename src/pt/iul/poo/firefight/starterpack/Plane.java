package pt.iul.poo.firefight.starterpack;

import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;
import pt.iul.ista.poo.utils.Vector2D;

public class Plane extends GameElement{

	private static String name ="plane";
	private static final int layer = 5;
	private static Point2D position;

	public Plane(Point2D position){
		super(position, name, layer);
	}
	
	public void removePlane(){
		GameElement aux=null;
		for(GameElement a: GameEngine.getInstance().getTileList()) {
			if(a instanceof Plane)
				aux=a;
		}
		if(aux!=null)
		GameEngine.getInstance().removeElement(aux);
	}
	
	public boolean PlaneInGuiBounds(){
		boolean aux = false;
		for(GameElement a: GameEngine.getInstance().getTileList()){
			if(a instanceof Plane){
				int y = a.getPosition().getY();
				if (y <= GameEngine.getGridHeight()){
					aux = true;
				}
			}
		}
		return aux;
	}
	
	public void move(){
		
		Point2D newPosition=super.getPosition().plus(new Vector2D(0,-2));
		
		if(PlaneInGuiBounds()){
			setPosition(newPosition);
			GameEngine.getInstance().removeAllWaters();
			GameEngine.getInstance().AdicionarAgua(Direction.UP,newPosition);
			GameEngine.getInstance().AdicionarAgua(Direction.UP,newPosition.plus(new Vector2D(0,1)));
			GameEngine.getInstance().getFireman().RemoverFogo(newPosition);
			GameEngine.getInstance().getFireman().RemoverFogo(newPosition.plus(new Vector2D(0,1)));
		}else
			removePlane();
	}

	public boolean canMoveTo(Point2D p) {

		if (p.getX() < 0) return false;
		if (p.getY() < 0) return false;
		if (p.getX() >= GameEngine.GRID_WIDTH) return false;
		if (p.getY() >= GameEngine.GRID_HEIGHT) return false;
		return true;
	}

	@Override
	public boolean isBurnable(){
		return false;
	}

}
