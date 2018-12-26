package br.com.desafio.model;

import java.time.LocalTime;

public class Piloto {

	private int codPiloto;
	
	private String nmePiloto;
	
	private LocalTime melhorVolta = null;
	
	private Double velocidadeMedia = 0.0;
	
	public Piloto(int cod, String nme) {
		this.codPiloto = cod;
		this.nmePiloto = nme;
	}

	public int getCodPiloto() {
		return codPiloto;
	}

	public void setCodPiloto(int codPiloto) {
		this.codPiloto = codPiloto;
	}

	public String getNmePiloto() {
		return nmePiloto;
	}

	public void setNmePiloto(String nmePiloto) {
		this.nmePiloto = nmePiloto;
	}

	public LocalTime getMelhorVolta() {
		return melhorVolta;
	}

	public void setMelhorVolta(LocalTime melhorVolta) {
		this.melhorVolta = melhorVolta;
	}

	public Double getVelocidadeMedia() {
		return velocidadeMedia;
	}

	public void setVelocidadeMedia(Double velocidadeMedia) {
		this.velocidadeMedia = velocidadeMedia;
	}
	
	
}
