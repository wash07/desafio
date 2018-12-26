package br.com.desafio.model;

import java.time.LocalTime;

public class Corrida {

	private LocalTime hrCorrida;
	
	private int numVolta;
	
	private String tmpVolta;
	
	private double vlMediaVolta;
	
	private Piloto piloto;

	public Corrida() {
		
	}
	
	public Corrida(LocalTime hrCorrida, int volta, String tempo, double vlMedia, Piloto piloto) {
		this.hrCorrida = hrCorrida;
		this.numVolta = volta;
		this.tmpVolta = tempo;
		this.vlMediaVolta = vlMedia;
		this.piloto = piloto;
	}
	
	public LocalTime getHrCorrida() {
		return hrCorrida;
	}

	public void setHrCorrida(LocalTime hrCorrida) {
		this.hrCorrida = hrCorrida;
	}

	public String getTmpVolta() {
		return tmpVolta;
	}

	public void setTmpVolta(String tmpVolta) {
		this.tmpVolta = tmpVolta;
	}

	public double getVlMediaVolta() {
		return vlMediaVolta;
	}

	public void setVlMediaVolta(double vlMediaVolta) {
		this.vlMediaVolta = vlMediaVolta;
	}

	public int getNumVolta() {
		return numVolta;
	}

	public void setNumVolta(int numVolta) {
		this.numVolta = numVolta;
	}

	public Piloto getPiloto() {
		return piloto;
	}

	public void setPiloto(Piloto piloto) {
		this.piloto = piloto;
	}
	
	
}
