package modelo;
//import java.util.*;


public class PuntajePartida {

    private String jugador;
    private int puntaje;
    
    public PuntajePartida(int puntajejugador, String nombrejugador) {
    	puntaje = puntajejugador;
    	jugador = nombrejugador;
    }

	public int getpuntaje() {
		return this.puntaje;
	}

	public String getNombreJugador() {
		return this.jugador;
	}
}

