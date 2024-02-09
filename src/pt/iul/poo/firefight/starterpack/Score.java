package pt.iul.poo.firefight.starterpack;
import javax.swing.JOptionPane;

public class Score {

	int pontos;
	String jogador;
	String name;

	public Score() {
		this.name="levels/scores0.txt";
		this.pontos=0;
		this.jogador = JOptionPane.showInputDialog("Insira o seu nome: ");
	}
	
	public Score(int pontos, String jogador){
		this.pontos=pontos;
		this.jogador=jogador;
	}
	

	public String getJogador() {
		return jogador;
	}

	public int getPontos() {
		return pontos;
	}

	public void resetPoints() {
		this.pontos=0;
	}

	@Override
	public String toString() {
		return pontos + ":" + jogador;
	}

	public void setName(String name) {
		this.name=name;
	}

	public String getName() {
		
		return name;
	}
	
	public void setPontos(int pontos) {
		this.pontos+=pontos;
	}
}
