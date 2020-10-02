package modelo;

import view.ProyectilView;

public class Proyectil {

	public final static int SENTIDO_ASC = 1;
	public final static int SENTIDO_DESC = 2;
	
	private int posicionX;
	private int posicionY;
	private int sentido;
	private int velocidad;
	private boolean estado;
	
	public Proyectil(int posicionX, int posicionY, int sentido) {
		this.posicionX = posicionX;
		this.posicionY = posicionY;
		this.sentido = sentido;
		this.estado = false;
		this.velocidad = 10;
	}
	
	public boolean estaActivo() {
		return this.estado;
	}
	
	public void activar() {
		this.estado = true;
	}
	
	public void desactivar() {
		this.estado = false;
	}
	
	public void avanzar() {
		if(this.sentido == SENTIDO_ASC && posicionY >= 0) {
			this.posicionY -= velocidad;
		} else if (this.sentido == SENTIDO_DESC && posicionY < AreaDeJuego.alto) {
			this.posicionY += velocidad;
		} else {
			this.estado = false;
		}
	}

	public int getPosicionX() {
		return posicionX;
	}

	public void setPosicionX(int posicionX) {
		this.posicionX = posicionX;
	}

	public int getPosicionY() {
		return posicionY;
	}

	public void setPosicionY(int posicionY) {
		this.posicionY = posicionY;
	}
	
	public int getSentido() {
		return sentido;
	}

	public void setSentido(int sentido) {
		this.sentido = sentido;
	}
	
	public ProyectilView toView() {
		return new ProyectilView(this.posicionX, this.posicionY, this.estado, this.sentido);
	}
	
}
