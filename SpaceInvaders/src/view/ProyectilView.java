package view;

public class ProyectilView {
	
	private int posicionX;
	private int posicionY;
	private boolean estado;
	private int sentido;
	
	public ProyectilView(int posicionX, int posicionY, boolean estado, int sentido) {
		this.posicionX = posicionX;
		this.posicionY = posicionY;
		this.estado = estado;
		this.sentido = sentido;
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
	
	public boolean estaActivo() {
		return estado;
	}
	
	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public int getSentido() {
		return sentido;
	}

	public void setSentido(int sentido) {
		this.sentido = sentido;
	}
	
	

}
