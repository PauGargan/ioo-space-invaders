package modelo;

import view.NaveInvasoraView;

public class NaveInvasora extends ObjetoMovil {

	public static final int TAMANIO = 60;
	
	private boolean vivo;
	private int sentido;
	
	public NaveInvasora(int x, int y) {
		super(x,y);
		this.vivo = true;
		this.sentido = ObjetoMovil.DIR_DERECHA;
	}
	
	public void avanzar() {
		// TODO
	}
	
	public boolean siVive() {
		return vivo;
	}
	
	public boolean siMeDispararon(int x, int y) {
		
		return (this.posicionX == x && this.posicionY == y);
	}
	
	public int getSentido() {
		return sentido;
	}

	public void setSentido(int sentido) {
		this.sentido = sentido;
	}

	public NaveInvasoraView toView() {
		return new NaveInvasoraView(this.posicionX, this.posicionY, this.vivo);
	}
	
}