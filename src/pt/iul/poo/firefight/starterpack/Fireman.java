package pt.iul.poo.firefight.starterpack;

import java.awt.event.KeyEvent;

import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;
import java.util.*;

// Esta classe de exemplo esta' definida de forma muito basica, sem relacoes de heranca
// Tem atributos e metodos repetidos em relacao ao que está definido noutras classes 
// Isso sera' de evitar na versao a serio do projeto

public class Fireman extends GameElement{

	private static final String name="fireman";
	private static final int layer=3;
	private static Point2D position;

	public Fireman(Point2D position) {
		super(position,name,layer);
	}

	public void RemoverFogo(Point2D position){

		List<GameElement> aux= new ArrayList<>();

		for(GameElement a : GameEngine.getInstance().getTileList()){
			if(a.getPosition().equals(position) && a instanceof Fire )
				aux.add(a);
		}

		GameEngine.getInstance().RemoveMultipleElements(aux);

	}

	public void add(){
		GameEngine.getInstance().getGui().addImage(new Fireman(position));
	}

	public void move(Direction direction){

		Point2D newPosition = super.getPosition().plus(direction.asVector());

		if(GameEngine.getInstance().estaAArder(newPosition)){
			RemoverFogo(newPosition);
			GameEngine.getInstance().AdicionarAgua(direction,newPosition);
			GameEngine.getInstance().getLevel().getScore().setPontos(20);
		}

		else if(canMoveTo(newPosition)){
			
			setPosition(newPosition);
			
			GameEngine.getInstance().getLevel().getScore().setPontos(-5);
			
			if(GameEngine.getInstance().searchPlane())
				GameEngine.getInstance().getPlane().move();
		}
	}


	// Verifica se a posicao p esta' dentro da grelha de jogo
	public boolean canMoveTo(Point2D p) {

		if (p.getX() < 0) return false;
		if (p.getY() < 0) return false;
		if (p.getX() >= GameEngine.GRID_WIDTH) return false;
		if (p.getY() >= GameEngine.GRID_HEIGHT) return false;
		return true;
	}



	// Metodos de ImageTile
	@Override
	public String getName() {
		return "fireman";
	}

	@Override
	public int getLayer() {
		return 3;
	}

	@Override
	public boolean isBurnable() {
		return false;
	}


}
