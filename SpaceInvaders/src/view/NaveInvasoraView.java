package view;

public class NaveInvasoraView {
	
	private int posicionX;
	private int posicionY;
	private boolean estado;
	
	public NaveInvasoraView() {}
	
	public NaveInvasoraView(int x, int y, boolean estado) {
		this.posicionX = x;
		this.posicionY = y;
		this.estado = estado;
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

	@Override
	public String toString() {
		return "NaveInvasoraView [posicionX=" + posicionX + ", posicionY=" + posicionY + "]";
	}
	
	

}
