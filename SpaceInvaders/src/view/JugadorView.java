package view;

import modelo.Proyectil;

public class JugadorView {

	private int posicionX;
	private int posicionY;
	private int velocidad;
	private int vidas;
	private ProyectilView proyectil;
	
	public JugadorView() {}
	
	public JugadorView(int x, int y, int velocidad, int vidas, ProyectilView proyectil) {
		this.posicionX = x;
		this.posicionY = y;
		this.velocidad = velocidad;
		this.vidas = vidas;
		this.proyectil = proyectil;
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
	
	public int getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}
	
	public int getVidas() {
		return vidas;
	}

	public void setVidas(int vidas) {
		this.vidas = vidas;
	}

	public ProyectilView getProyectil() {
		return proyectil;
	}

	public void setProyectil(ProyectilView proyectil) {
		this.proyectil = proyectil;
	}

	
	
}
