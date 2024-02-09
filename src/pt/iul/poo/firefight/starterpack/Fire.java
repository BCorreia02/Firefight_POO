package pt.iul.poo.firefight.starterpack;

import pt.iul.ista.poo.utils.Point2D;

//Esta classe de exemplo esta' definida de forma muito basica, sem relacoes de heranca
//Tem atributos e metodos repetidos em relacao ao que está definido noutras classes 
//Isso sera' de evitar na versao a serio do projeto

public class Fire extends GameElement {

	private static final String name ="fire";
	private static int layer=3;
	private static Point2D position;

	public Fire(Point2D position) {
		super(position,name,layer);
	}

	@Override
	public boolean isBurnable() {
		return false;
	}
}
