package modelo;

public class Proyectil {

	public final static int SENTIDO_ASC = 1;
	public final static int SENTIDO_DESC = 2;
	
	private int posicionX;
	private int posicionY;
	private int sentido;
	private boolean estado;
	
	public Proyectil(int posicionX, int posicionY, int sentido) {
		this.posicionX = posicionX;
		this.posicionY = posicionY;
		this.sentido = sentido;
		this.estado = true;
	}
	
	public boolean siVivo() {
		return this.estado;
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
	
	
}
