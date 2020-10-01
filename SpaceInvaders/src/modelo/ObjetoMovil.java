package modelo;

public abstract class ObjetoMovil {
	
	public static final int DIR_DERECHA = 1;
	public static final int DIR_IZQUIERDA = 2;
	public static final int DIR_ARRIBA = 3;
	public static final int DIR_ABAJO = 4;
	
	protected int posicionX;
	protected int posicionY;
	
	public ObjetoMovil(int x, int y) {
		posicionX = x;
		posicionY = y;
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
	
	public void mover(int direccion, int unidades) {
		
		switch(direccion) {
		
			case DIR_DERECHA:
				this.posicionX += unidades;
				break;
			case DIR_IZQUIERDA:
				this.posicionX -= unidades;
				break;
			case DIR_ARRIBA:
				this.posicionY -= unidades;
				break;
			case DIR_ABAJO:
				this.posicionY += unidades;
				break;
		}
		
	}
}
