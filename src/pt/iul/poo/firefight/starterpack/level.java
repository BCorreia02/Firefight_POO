package pt.iul.poo.firefight.starterpack;
import java.io.*;
import java.util.*;

public class level{

	private int level;
	private String name;
	private Score score;
	private List<String> scoreList;

	public level(){
		this.level=0;
		this.name="levels/level0.txt";
		this.score=new Score();
		loadScores();
	}

	public String getLevel(){
		return this.name;
	}

	public int getLevelNum() {
		return this.level;
	}

	public Score getScore() {
		return score;
	}

	public void loadScores() {
		try {
			System.out.println("Entrei no loadScores");
			scoreList=new ArrayList();
			Scanner sc= new Scanner(new File(score.getName()));
			while(sc.hasNextLine()) {
				scoreList.add(sc.nextLine());
				System.out.println("Adicionei" + sc.nextLine());
				if(sc.hasNextLine())
					sc.nextLine();
			}
			sc.close();
		}catch(FileNotFoundException a){
			System.err.println();
		}	
	}

	public void sortScores(){
		Score scaux=null;
		List<Score> scores = new ArrayList<>();
		List<String> stringscores = new ArrayList<>();
		for(String a: scoreList) {
			String[] aux=null;
			aux=a.split(":");
			scores.add(new Score(Integer.parseInt(aux[0]),aux[1]));
		}
		scores.sort((Score a, Score b) -> b.getPontos()-a.getPontos());
		
		for(Score a: scores) {
			stringscores.add(a.toString());
		}
		
		scoreList=stringscores;
	}

	public void printScores(){
		try {
			System.out.println("Entrei no printScores");
			scoreList.add(score.toString());
			PrintWriter pw= new PrintWriter(score.getName());
			for(String a: scoreList) {
				pw.println(a);
				System.out.println("Imprimri" + a);
			}
			pw.println("");
			pw.close();
		}catch(FileNotFoundException a){
			System.err.println();
		}	
	}

	public void levelUp(){
		
		sortScores();
		printScores();

		this.level++;

		switch(this.level){
		case 1: 
			this.name="levels/level1.txt";
			this.score.setName("levels/scores1.txt");
			System.out.println("level1");
			break;
		case 2:
			this.name="levels/level2.txt";
			this.score.setName("levels/scores2.txt");
			System.out.println("level2");
			break;
		case 3:
			this.name="levels/level3.txt";
			this.score.setName("levels/scores3.txt");
			System.out.println("level3");
			break;
		case 4:
			this.name="levels/level4.txt";
			this.score.setName("levels/scores4.txt");
			System.out.println("level4");
			break;
		}

		loadScores();
		score.resetPoints();
		GameEngine.getInstance().cleanElements();
		GameEngine.getInstance().loadElements(name);
		GameEngine.getInstance().sendImagesToGUI();

		if(this.level==5)
			System.exit(0);
	}
}



