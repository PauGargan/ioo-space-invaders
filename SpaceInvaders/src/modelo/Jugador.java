package modelo;

import view.JugadorView;

public class Jugador extends ObjetoMovil {
	
	public static final int POSICION_INICIAL_X = 400;
	public static final int POSICION_Y = 600;
	public static final int VELOCIDAD = 5;
	
	private final int VIDA_INICIAL = 3;
	
	private int vidas;
	private Proyectil proyectil;
	
	public Jugador() {
		super(POSICION_INICIAL_X,POSICION_Y);
		this.vidas = VIDA_INICIAL;
		this.proyectil = new Proyectil(POSICION_INICIAL_X / 2, POSICION_Y, Proyectil.SENTIDO_ASC);
	}
	
	public boolean siVive() {
		if(vidas > 0)
			return true;
					
		return false;
	}
	
	public boolean siMeDispararon(int x, int y) {
		
		return (this.posicionX == x && this.posicionY == y);
	}
	
	public void disparar() {
		this.proyectil.setPosicionX(this.posicionX + 40);
		this.proyectil.activar();
	}
	
	
	public void restarVida() {
		this.vidas--;
	}
	
	public int getVidas() {
		return this.vidas;
	}
	
	public Proyectil getProyectil() {
		return this.proyectil;
	}
	
	public void reiniciarProyectil() {
		this.proyectil = new Proyectil(POSICION_INICIAL_X / 2, POSICION_Y, Proyectil.SENTIDO_ASC);
	}
	
	public JugadorView toView() {
		return new JugadorView(posicionX, posicionY, VELOCIDAD, vidas, proyectil.toView());
	}
}
