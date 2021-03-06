package modelo;

import view.NaveInvasoraView;

public class NaveInvasora extends ObjetoMovil {

	public static final int TAMANIO = 60;
	
	private boolean vivo;
	private int sentido;
	private Proyectil proyectil;
	
	public NaveInvasora(int x, int y) {
		super(x,y);
		this.vivo = true;
		this.sentido = ObjetoMovil.DIR_DERECHA;
		this.proyectil = new Proyectil(x + TAMANIO/2, y + TAMANIO, Proyectil.SENTIDO_DESC);
	}
	
	public boolean siVive() {
		return vivo;
	}
	
	public boolean siMeDispararon(int x, int y) {
		
		return (this.posicionX == x && this.posicionY == y);
	}
	
	public void disparar() {
		this.proyectil.setPosicionX(this.posicionX + TAMANIO/2);
		this.proyectil.setPosicionY(this.posicionY + TAMANIO);
		this.proyectil.activar();
	}
	
	public void recibirDisparo() {
		this.vivo = false;
	}
	
	public int getSentido() {
		return sentido;
	}

	public void setSentido(int sentido) {
		this.sentido = sentido;
	}
	
	public Proyectil getProyectil() {
		return this.proyectil;
	}

	public NaveInvasoraView toView() {
		return new NaveInvasoraView(this.posicionX, this.posicionY, this.vivo, this.proyectil.toView());
	}
	
}