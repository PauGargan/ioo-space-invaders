package modelo;

import view.JugadorView;

public class Jugador extends ObjetoMovil {
	
	public static final int POSICION_INICIAL_X = 400;
	public static final int POSICION_INICIAL_Y = 600;
	public static final int VELOCIDAD = 5;
	
	private final int VIDA_INICIAL = 3;
	
	private int vidas;
	
	public Jugador() {
		super(POSICION_INICIAL_X,POSICION_INICIAL_Y);
		this.vidas = VIDA_INICIAL;
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
	
	public JugadorView toView() {
		return new JugadorView(posicionX, posicionY, VELOCIDAD);
	}
}
