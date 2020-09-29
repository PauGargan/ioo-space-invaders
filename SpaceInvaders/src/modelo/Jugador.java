package modelo;

public class Jugador {
	
	private final int X_INICIAL = 50;
	private final int Y_INICIAL = 50;
	private int vidas;
	private int posicionX;
	private int posicionY;
	
	public Jugador(int vidas) {
		this.vidas = vidas;
		this.posicionX = X_INICIAL;
		this.posicionY = Y_INICIAL;
	}
	
	public boolean siVive() {
		if(vidas > 0)
			return true;
					
		return false;
	}
	
	public boolean siMeDispararon(int x, int y) {
		
		return (this.posicionX == x && this.posicionY == y);
	}
	
	public Proyectil disparar() {
		Proyectil disparo = new Proyectil(this.posicionX, this.posicionY, Proyectil.SENTIDO_ASC);
		
		return disparo;
	}
	
	public void moverIzq() {
		this.posicionX =- 5;
	}
	
	public void moverDer() {
		this.posicionX =+ 5;
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
