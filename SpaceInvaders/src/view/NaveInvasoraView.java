package view;

import modelo.Proyectil;

public class NaveInvasoraView {
	
	private int posicionX;
	private int posicionY;
	private boolean estado;
	private ProyectilView proyectil;
	
	public NaveInvasoraView() {}
	
	public NaveInvasoraView(int x, int y, boolean estado, ProyectilView proyectil) {
		this.posicionX = x;
		this.posicionY = y;
		this.estado = estado;
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
	
	public boolean getEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	

	public ProyectilView getProyectil() {
		return proyectil;
	}

	public void setProyectil(ProyectilView proyectil) {
		this.proyectil = proyectil;
	}

	@Override
	public String toString() {
		return "NaveInvasoraView [posicionX=" + posicionX + ", posicionY=" + posicionY + "]";
	}
	
	

}
