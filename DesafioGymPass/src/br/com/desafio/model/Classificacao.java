package br.com.desafio.model;

public class Classificacao {
	
	private int posicao;
	
	private Piloto piloto;
	
	private Corrida corrida;

	public Classificacao() {
		
	}
	
	public Classificacao(int posicao, Piloto piloto) {
		this.piloto = piloto;
		this.posicao = posicao;
	}
	
	public int getPosicao() {
		return posicao;
	}

	public void setPosicao(int posicao) {
		this.posicao = posicao;
	}

	public Piloto getPiloto() {
		return piloto;
	}

	public void setPiloto(Piloto piloto) {
		this.piloto = piloto;
	}

	public Corrida getCorrida() {
		return corrida;
	}

	public void setCorrida(Corrida corrida) {
		this.corrida = corrida;
	}
	
	
}
