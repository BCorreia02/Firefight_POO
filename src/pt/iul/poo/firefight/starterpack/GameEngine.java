package pt.iul.poo.firefight.starterpack;

import java.util.*;
import java.io.*;

import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.observer.Observed;
import pt.iul.ista.poo.observer.Observer;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;
import pt.iul.ista.poo.utils.Vector2D;

import java.awt.event.KeyEvent;

// Note que esta classe e' um exemplo - nao pretende ser o inicio do projeto, 
// embora tambem possa ser usada para isso.
//
// No seu projeto e' suposto haver metodos diferentes.
// 
// As coisas que comuns com o projeto, e que se pretendem ilustrar aqui, sao:
// - GameEngine implementa Observer - para  ter o metodo update(...)  
// - Configurar a janela do interface grafico (GUI):
//        + definir as dimensoes
//        + registar o objeto GameEngine ativo como observador da GUI
//        + lancar a GUI
// - O metodo update(...) e' invocado automaticamente sempre que se carrega numa tecla
//
// Tudo o mais podera' ser diferente!


public class GameEngine implements Observer {

	private static GameEngine INSTANCE;

	// Dimensoes da grelha de jogo
	public static final int GRID_HEIGHT = 10;
	public static final int GRID_WIDTH = 10;


	private ImageMatrixGUI gui;  		// Referencia para ImageMatrixGUI (janela de interface com o utilizador) 
	private List<GameElement> tileList;	// Lista de imagens
	private Fireman fireman;			// Referencia para o bombeiro
	private Bulldozer bulldozer;
	private Plane plane=null;
	private level level;
	int lastKeyPressed;

	public ImageMatrixGUI getGui() {
		return gui;
	}

	public List<GameElement> getTileList() {
		return tileList;
	}

	public Fireman getFireman() {
		return fireman;
	}

	public Bulldozer getBulldozer() {
		return bulldozer;
	}

	public void addBuldozer(Bulldozer a){
		this.bulldozer=a;
		addElement(a);
	}

	public level getLevel() {
		return this.level;
	}

	// Neste exemplo o setup inicial da janela que faz a interface com o utilizador e' feito no construtor 
	// Tambem poderia ser feito no main - estes passos tem sempre que ser feitos!
	private GameEngine() {

		gui = ImageMatrixGUI.getInstance();    // 1. obter instancia ativa de ImageMatrixGUI	
		gui.setSize(GRID_HEIGHT, GRID_WIDTH);  // 2. configurar as dimensoes 
		gui.registerObserver(this);            // 3. registar o objeto ativo GameEngine como observador da GUI
		gui.go();                              // 4. lancar a GUI
		tileList = new ArrayList<>();
	}

	public static GameEngine getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new GameEngine();
		}

		return INSTANCE;
	}

	public boolean allFiresGone(){
		int nFogos=0;
		for(GameElement a: tileList) {
			if(a instanceof Fire)
				nFogos++;
		}
		return nFogos==0;
	}

	public void addListElement(GameElement a) {
		tileList.add(a);
	}

	public void removeListElement(GameElement a) {
		tileList.remove(a);
	}

	public void addGuiElement(GameElement game){
		gui.addImage(game);
		gui.update();
	}

	public void removeGuiElement(GameElement game){
		gui.removeImage(game);
		gui.update();
	}

	public void removeElement(GameElement a) {
		GameEngine.getInstance().removeGuiElement(a);
		GameEngine.getInstance().removeListElement(a);
	}

	public void addElement(GameElement a) {
		GameEngine.getInstance().addGuiElement(a);
		GameEngine.getInstance().addListElement(a);
	}


	public void RemoveMultipleElements(List<GameElement> aRemover){
		for (GameElement gameElement : aRemover) {
			removeElement(gameElement);
		}
		gui.update();   
	}

	public void RemoveMultipleGuiElements(List<GameElement> aRemover){
		for (GameElement gameElement : aRemover) {
			gui.removeImage(gameElement);
		}
		gui.update();   
	}

	public void addMultipleGuiElements(List<GameElement> aAdicionar){
		for (GameElement gameElement : aAdicionar) {
			addGuiElement(gameElement);
		}
		gui.update();
	}

	public void cleanElements() {
		for(GameElement a: tileList) {
			gui.removeImage(a);
			a=null;
		}
		this.tileList= new ArrayList<GameElement>();
	}

	public void addMultipleElements(List<GameElement> aAdicionar){
		for (GameElement gameElement : aAdicionar) {
			addElement(gameElement);
		}
		gui.update();
	}

	public  void removeAllWaters(){
		List<GameElement> aux =new ArrayList<>();
		for(GameElement a : tileList){
			if(a instanceof Water){
				aux.add(a);
			}
		}
		RemoveMultipleElements(aux);
	}

	public boolean estaAArder(Point2D position){
		for(GameElement a: tileList){
			if(a.getName().equals("fire") && a.getPosition().equals(position))
				return true;	
		}
		return false;
	}

	public boolean FireProb(Point2D point){
		for(GameElement a: tileList){
			if(a.getPosition().equals(point) && a instanceof Grass || a instanceof Pine || a instanceof Eucaliptus){
				switch(a.getName()){
				case "pine":
					return new Random().nextDouble() <= 0.05;
				case "eucaliptus":
					return new Random().nextDouble() <= 0.10;
				case "grass":
					return new Random().nextDouble() <= 0.15;
				}
			}
		}
		return false;
	}

	public List<GameElement> getElementsInPos (Point2D pos){
		ArrayList<GameElement> elements = new ArrayList<>();
		for(GameElement a : tileList){			
			if(a.getPosition().equals(pos)) 
				elements.add(a);
		}
		return elements;		
	}

	public void incendiarVizinhos(){

		List<GameElement> aux= new ArrayList<>();
		List<Point2D>  vizinhos = new ArrayList<>();
		List<GameElement> elements= new ArrayList<>();

		for(GameElement a : tileList){	
			if(a instanceof Fire){
				vizinhos =	a.getPosition().getNeighbourhoodPoints();
				for(Point2D ponto : vizinhos){
					elements= getElementsInPos(ponto);
					if(elements.size() !=1) continue;
					if(FireProb(ponto) && elements.get(0).isBurnable()){								
						aux.add(new Fire(ponto));
					}
				}
			}
		}
		addMultipleElements(aux);
	}

	public void AdicionarAgua(Direction direction, Point2D position){
		Water aux= new Water(position);
		aux.aguaDirection(direction,position);
	}

	public void updateDurability(GameElement a){
		for(GameElement aux: tileList) {
			switch(aux.getName()){
			case "pine":
				Pine temp=(Pine)a;
				temp.increaseDurability();
				break;

			case "eucaliptus":
				Eucaliptus temp1=(Eucaliptus)a;
				temp1.increaseDurability();
				break;

			case "grass":
				Grass temp2=(Grass)a;
				temp2.increaseDurability();
				break;
			}
		}
	}

	public boolean isConsumable(GameElement a){

		if(a instanceof Pine || a instanceof Eucaliptus || a instanceof Grass && estaAArder(a.getPosition())) {

			switch(a.getName()){

			case "pine":

				Pine aux1 = (Pine)a;
				return aux1.isConsumable();

			case "eucaliptus":

				Eucaliptus aux2= (Eucaliptus)a;
				return aux2.isConsumable();

			case "grass":

				Grass aux3 = (Grass)a;
				return aux3.isConsumable();
			}
		}

		return false;
	}

	public void ConsumirTerreno(){

		Point2D position= null;

		for(GameElement a : tileList){

			if(a.isBurnable()) {

				if(a.getName().equals("grass")){

					Grass aux = (Grass)a;

					if(!aux.isConsumable() && estaAArder(aux.getPosition())){
						aux.increaseDurability();
					}else if(aux.isConsumable() && estaAArder(aux.getPosition())){
						aux.consumir();
						level.getScore().setPontos(-1);
						position = aux.getPosition();
					}

				}else if(a.getName().equals("pine")) {

					Pine aux1 = (Pine)a;

					if(!aux1.isConsumable() && estaAArder(aux1.getPosition())){
						aux1.increaseDurability();
					}else if( aux1.isConsumable() && estaAArder(aux1.getPosition())){
						aux1.consumir();
						level.getScore().setPontos(-5);
						position = aux1.getPosition();
					}

				}else if(a.getName().equals("eucaliptus")) {	

					Eucaliptus aux2 = (Eucaliptus)a;

					if(!aux2.isConsumable() && estaAArder(aux2.getPosition())){
						aux2.increaseDurability();
					}else if( aux2.isConsumable() && estaAArder(aux2.getPosition())){
						aux2.consumir();
						level.getScore().setPontos(-3);
						position = aux2.getPosition();
					}

				}
			}
		}

		if(!(position==null))		
			fireman.RemoverFogo(position);
	}

	public int indexofElement(Point2D position,String nome){
		int index=0;
		for(GameElement a: GameEngine.getInstance().tileList){
			if(a.getName().equals(nome) && a.getPosition().equals(position)){
				index= GameEngine.getInstance().tileList.indexOf(a);
			}
		}
		return index;
	}

	public int colunaComMaisFogos(){

		int[] index =new int[10];

		for(int x=0; x<GRID_WIDTH;x++){
			int nFogos  = 0;
			for(GameElement a: tileList) {	
				if(a.getPosition().getX() == x && a instanceof Fire) 
					nFogos++;
			}
			index[x]= nFogos;			
		}

		return getIndexOfLargest(index);
	}

	public void addPlane(){
		if(!searchPlane()) {
			plane= new Plane((new Point2D(colunaComMaisFogos(),9)));
			addElement(plane);
		}
	}

	public boolean searchPlane(){
		boolean aux=false;
		for(GameElement a: tileList) {
			if(a instanceof Plane)
				aux=true;
		}
		return aux;
	}


	public int getIndexOfLargest( int[] v )	{
		if ( v == null || v.length == 0 ) return -1; 
		int max = 0;
		for ( int i = 1; i < v.length; i++ )  {
			if ( v[i] > v[max] ) max = i; }
		return max; 
	}

	public Plane getPlane() {
		return plane;
	}

	// O metodo update() e' invocado sempre que o utilizador carrega numa tecla
	// no argumento do metodo e' passada um referencia para o objeto observado (neste caso seria a GUI)
	@Override
	public void update(Observed source) {

		int lastKeyTyped = gui.keyPressed();

		ImageMatrixGUI.getInstance().setStatusMessage("Jogador: " + level.getScore().getJogador() +
				"             Nivel: " + level.getLevelNum() +
				"             Pontos: " + level.getScore().getPontos());

		removeAllWaters();
		incendiarVizinhos();
		ConsumirTerreno();

		if(Direction.isDirection(lastKeyTyped)){

			Direction direction = Direction.directionFor(lastKeyTyped);

			if(!bulldozer.getPosition().equals(fireman.getPosition())){
				fireman.move(direction);
			}

			else if(bulldozer.getPosition().equals(fireman.getPosition()) && lastKeyPressed >= KeyEvent.VK_LEFT && lastKeyPressed <= KeyEvent.VK_RIGHT){
				gui.removeImage(fireman);
				bulldozer.move(direction);
				fireman.setPosition(bulldozer.getPosition());
			}

			else if(fireman.getPosition().equals(bulldozer.getPosition()) && lastKeyPressed == KeyEvent.VK_ENTER){
				gui.addImage(fireman);
				fireman.move(direction);

			}

			if(lastKeyPressed == KeyEvent.VK_P){
				addPlane();
			}

			if(allFiresGone())
				level.levelUp();
		}

		gui.update();
		lastKeyPressed=lastKeyTyped;
	}

	public static GameEngine getINSTANCE() {
		return INSTANCE;
	}

	public static int getGridHeight() {
		return GRID_HEIGHT;
	}

	public static int getGridWidth() {
		return GRID_WIDTH;
	}

	// Criacao dos objetos e envio das imagens para GUI
	public void start(){
		this.level= new level();
		loadElements(this.level.getLevel());
		sendImagesToGUI();    // enviar as imagens para a GUI
	}


	public void loadElements(String f){
		try {
			Scanner sc= new Scanner(new File(f));
			int linha=0;
			while(sc.hasNextLine()){
				if(linha<10) {
					String line=sc.nextLine();
					for (int x=0; x<line.length(); x++){
						if(line.charAt(x) == 'e')
							tileList.add(new Eucaliptus(new Point2D(x,linha)));
						if(line.charAt(x) == 'p')
							tileList.add(new Pine(new Point2D(x,linha)));
						if(line.charAt(x) == 'm')
							tileList.add(new Grass(new Point2D(x,linha)));
						if(line.charAt(x) == '_')
							tileList.add(new OpenLand(new Point2D(x,linha)));
					}
					linha++;
				}

				if(linha>=10) {

					String nome= sc.next();
					int posx= sc.nextInt();
					int posy= sc.nextInt();

					if(nome.equals("Fireman")) {
						fireman= new Fireman(new Point2D(posx,posy));
						tileList.add(fireman);
					}

					if(nome.equals("Fire")) {
						tileList.add(new Fire(new Point2D(posx,posy)));
					}
					if(nome.equals("Bulldozer")) {
						bulldozer= new Bulldozer(new Point2D(posx,posy));
						tileList.add(bulldozer);
					}
				}
			}
			sc.close();
		}catch(FileNotFoundException e){
			System.err.println("Impossivel abrir o ficheiro de texto");
		}
	}
	// Envio das mensagens para a GUI - note que isto so' precisa de ser feito no inicio
	// Nao e' suposto re-enviar os objetos se a unica coisa que muda sao as posicoes  
	public void sendImagesToGUI() {
		for (GameElement gameElement : tileList){
			gui.addImage(gameElement);
		}
	}

}
