package modelo;

public class NaveInvasora {

	private boolean vivo;
	private int velocidad;
	private int posicionX;
	private int posicionY;
	
	public NaveInvasora() {
		this.vivo = true;
		
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
	
	
}