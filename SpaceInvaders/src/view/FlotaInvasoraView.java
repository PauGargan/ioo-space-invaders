package view;

import java.util.ArrayList;

import modelo.NaveInvasora;

public class FlotaInvasoraView {
	
	private int sentido;
	private int limiteDerecho;
	private int limiteIzquierdo;
	private int limiteInferior;
	
	private ArrayList<ArrayList<NaveInvasoraView>> naves;
	
	public FlotaInvasoraView() {}

	public FlotaInvasoraView(int sentido, int limiteDerecho, int limiteIzquierdo, int limiteInferior,
			ArrayList<ArrayList<NaveInvasoraView>> naves) {
		this.sentido = sentido;
		this.limiteDerecho = limiteDerecho;
		this.limiteIzquierdo = limiteIzquierdo;
		this.limiteInferior = limiteInferior;
		this.naves = naves;
	}

	public int getSentido() {
		return sentido;
	}

	public void setSentido(int sentido) {
		this.sentido = sentido;
	}

	public int getLimiteDerecho() {
		return limiteDerecho;
	}

	public void setLimiteDerecho(int limiteDerecho) {
		this.limiteDerecho = limiteDerecho;
	}

	public int getLimiteIzquierdo() {
		return limiteIzquierdo;
	}

	public void setLimiteIzquierdo(int limiteIzquierdo) {
		this.limiteIzquierdo = limiteIzquierdo;
	}

	public int getLimiteInferior() {
		return limiteInferior;
	}

	public void setLimiteInferior(int limiteInferior) {
		this.limiteInferior = limiteInferior;
	}

	public ArrayList<ArrayList<NaveInvasoraView>> getNaves() {
		return naves;
	}

	public void setNaves(ArrayList<ArrayList<NaveInvasoraView>> naves) {
		this.naves = naves;
	}
		
	

}
