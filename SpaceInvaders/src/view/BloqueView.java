package view;

public class BloqueView {
	
	private int posicionY;
	private int posicionX;
	private int proteccion;
	private int tamanio;
	
	public BloqueView(int x, int y, int proteccion, int tamanio) {
		this.posicionX = x;
		this.posicionY = y;
		this.proteccion = proteccion;
		this.tamanio = tamanio;
	}

	public int getPosicionY() {
		return posicionY;
	}

	public void setPosicionY(int posicionY) {
		this.posicionY = posicionY;
	}

	public int getPosicionX() {
		return posicionX;
	}

	public void setPosicionX(int posicionX) {
		this.posicionX = posicionX;
	}

	public int getProteccion() {
		return proteccion;
	}

	public void setProteccion(int proteccion) {
		this.proteccion = proteccion;
	}

	public int getTamanio() {
		return tamanio;
	}

	public void setTamanio(int tamanio) {
		this.tamanio = tamanio;
	}
	
	

}
