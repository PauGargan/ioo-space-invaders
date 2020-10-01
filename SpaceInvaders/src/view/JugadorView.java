package view;

public class JugadorView {

	private int posicionX;
	private int posicionY;
	private int velocidad;
	
	public JugadorView() {}
	
	public JugadorView(int x, int y, int velocidad) {
		this.posicionX = x;
		this.posicionY = y;
		this.velocidad = velocidad;
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

	@Override
	public String toString() {
		return "JugadorView [posicionX=" + posicionX + ", posicionY=" + posicionY + "]";
	}
	
	
}
