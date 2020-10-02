package modelo;
//import java.util.*;


public class PuntajePartida {

	public static final int PUNTOS_PASAR_NIVEL = 200;
	public static final int PUNTOS_MATAR_NAVE = 10;
	public static final int PUNTOS_PARA_NUEVA_VIDA = 500;
	
    private int puntaje;
    private int puntajeParaVida;
    private Tablero tablero;
    
    public PuntajePartida() {
    	puntaje = 0;
    	puntajeParaVida = 0;
    }

	public int getPuntaje() {
		return this.puntaje;
	}
	
	public void incrementarPuntaje(int puntos) {
		this.puntaje += puntos;
		this.puntajeParaVida += puntos;
	}
	
	public void restarPuntajeParaVida() {
		this.puntajeParaVida =- PUNTOS_PARA_NUEVA_VIDA;
	}
	
	public boolean mereceNuevaVida() {
		return puntajeParaVida >= PUNTOS_PARA_NUEVA_VIDA;
	}
	
	public void registrarPuntaje(String nombre)
	{
		tablero.agregarOrdenado(puntaje, nombre);
	}
}

