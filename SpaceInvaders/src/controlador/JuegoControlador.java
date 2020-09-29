package controlador;


import java.util.ArrayList;

import modelo.AreaDeJuego;
import modelo.Bloque;
import modelo.Jugador;
import modelo.NaveInvasora;
import modelo.Proyectil;
import modelo.PuntajePartida;
import modelo.Tablero;

public class JuegoControlador {


    public static  JuegoControlador instancia; // singleton un solo objeto controlador
	private String dificultad;
    private int puntaje;
    private int puntajeVidaNueva;
    private int cantVida;
    private int nivel = 1;
    private int navesEnemigas;
    private String nombreJugador;
    private ArrayList <NaveInvasora> naves;
    private ArrayList <Bloque> bloques;
    private ArrayList <Proyectil> proyectiles;
    private Jugador jugador;
    private Tablero tablero = new Tablero();
    private AreaDeJuego area= new AreaDeJuego();
    
    
    
    public   JuegoControlador() { } 

    
    public void comenzarJuego(int nivel ) {
    	
        //Nivel
    	this.nivel = nivel;
    	
        //NavesInvasoras
    	naves = new ArrayList<NaveInvasora>();
        
        //Jugador
        jugador = new Jugador(this.cantVida);
        
        // Muro
        iniciarMuro();
        bloques = new ArrayList<Bloque>();
        
        //Disparos
              
    	
    	proyectiles = new ArrayList<Proyectil>();
    	

    }

 
    public void mostrarPantallaInicio() {
        // TODO implement here

    }

    public boolean siGameOver() {
    	if(jugador.siVive())
    	{
    		for(int i=14; i>=0; i--) // verifica si las naves llegaron al jugador
    		{	
    			if(naves.get(i).getPosicionY() > 300)
	    		return true;
    		}
    		return false;
    	}
    	else return true;
    }


    public void moverDer() {

    }


    public void moverIzq() {

    }


    public void registrarJugador(String jugadorNombre) {
        nombreJugador = jugadorNombre;
    }

    public void disparar() {
    }

    public void dispararEnemigo() {
    }

    public void incrementarNivel() {
    	boolean paso = true;
    	for(NaveInvasora nave : naves)
    	{
    		if (nave.siVive())
    			paso = false;
    	}
    	
    	if (paso)
    	{
    		nivel++;
    		this.puntaje += 200;
    		this.puntajeVidaNueva += 200;
    		incrementarVida(); // chequear
    		comenzarJuego(nivel);
    	}

    }

    public void reiniciarPartida() {
    	nivel = 1; 
    }

    public void moverEnemigo() {
    }

    public void incrementarVida() {
		if(puntajeVidaNueva > 499)
			this.cantVida++;
		puntajeVidaNueva = 0;
    }


    public void registrarPuntaje( String jugador, int puntaje) {
    		//paraque?
    }

    public void registrarPartida( String jugador, int puntaje) {
        PuntajePartida puntajep = new PuntajePartida(puntaje,jugador);
        tablero.agregarOrdenado(puntajep);
    }
    
    public int getPuntaje() {
        return puntaje;
    }
    
    public Tablero GetTablero() {
        return tablero ;
    }


    public void iniciarMuro() {
    	
    	bloques.clear(); 
    	for(int i=0; i<4; i++)
         {
         	Bloque bloque = new Bloque(200+i*300); //chequear posicion
         	bloques.add(bloque);        	
         }
    	 
    }

    public void avanzarVertical() {

    }
    

    public void restarVida() {

    }


    public void setDificutad(String dificultad) {
    	this.dificultad = dificultad;
    }


    public void pausar() {

    }

    public void despausar() {

    }

    public void salir() {

    }
    
    public int getPosXJugador()    {
    
    	return jugador.getPosicionX();
    }
    
        
    public int getAncho() {
    	return area.getAncho();
    }
    
    public int getAlto() {
    	return area.getAlto();
    }
    
    public int getVida() {
    	return cantVida;
    }
    
    public ArrayList<Bloque> getBloque() {
		return bloques;
	}
    
    public static JuegoControlador getInstancia() { //Singleton ==> referencia static a la misma clase
		  if (instancia == null)
			  instancia = new JuegoControlador();
		  return instancia;
		 }
}

