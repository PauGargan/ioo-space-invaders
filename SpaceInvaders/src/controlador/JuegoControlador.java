package controlador;


import java.util.ArrayList;
import java.util.Random;

import modelo.AreaDeJuego;
import modelo.Bloque;
import modelo.FlotaInvasora;
import modelo.Jugador;
import modelo.NaveInvasora;
import modelo.ObjetoMovil;
import modelo.Proyectil;
import modelo.PuntajePartida;
import modelo.Tablero;
import view.BloqueView;
import view.FlotaInvasoraView;
import view.JugadorView;
import view.ProyectilView;

public class JuegoControlador {
    
	public static  JuegoControlador instancia; // singleton un solo objeto controlador
	private String dificultad;
    private int cantVida;
    private int nivel = 1;
    private FlotaInvasora flotaInvasora;
    private ArrayList <Bloque> bloques;
    //private ArrayList <Proyectil> proyectiles;
    private Jugador jugador;
    private Tablero tablero = new Tablero();
    private AreaDeJuego area= new AreaDeJuego();
    private PuntajePartida puntajePartida;
    private Proyectil disparoEnemigo;
    
    
    
    private JuegoControlador() { 
    	comenzarJuego();
    } 

    public void inicializarJuego(String nombre) {
    	puntajePartida = new PuntajePartida();
    }
    
    public void comenzarJuego() {
    	
        //Nivel
    	//this.nivel = nivel;
    	
        //NavesInvasoras
    	flotaInvasora = new FlotaInvasora();
        
        //Jugador
        jugador = new Jugador();
        
        // Muro
        bloques = new ArrayList<Bloque>(); 
        iniciarMuro();
        
        //Disparos
    	//proyectiles = new ArrayList<Proyectil>();
    	
    }

 
    public void mostrarPantallaInicio() {
        // TODO implement here

    }

    public boolean siGameOver() {
    	if(jugador.siVive())
    	{
    		return false;
    	}
    	else return true;
    }


    public int moverJugadorDer() {
    	jugador.mover(ObjetoMovil.DIR_DERECHA, Jugador.VELOCIDAD);
    	return jugador.getPosicionX();
    }


    public int moverJugadorIzq() {
    	jugador.mover(ObjetoMovil.DIR_IZQUIERDA, Jugador.VELOCIDAD);
    	return jugador.getPosicionX();
    }


    public void disparar() {
    	if(!this.jugador.getProyectil().estaActivo()) {
    		this.jugador.disparar();
    	}
    	while(this.jugador.getProyectil().estaActivo())
    		this.jugador.getProyectil().avanzar();
    }

    public void dispararEnemigo() {
    	if(this.disparoEnemigo == null || !this.disparoEnemigo.estaActivo()) {
	    	ArrayList<ArrayList<NaveInvasora>> naves = this.flotaInvasora.getNaves();
	    	int col = 0;
	    	int fil = 0;
	    	NaveInvasora nave;
	    	Random rand = new Random();
	    	do {
		    	col = rand.nextInt(FlotaInvasora.TAMANIO_COLUMNA_NAVES_INVASORAS);
		    	fil = rand.nextInt(FlotaInvasora.TAMANIO_FILA_NAVES_INVASORAS);
	    	} while (!naves.get(col).get(fil).siVive());
	    	
	    	if(naves.get(col).get(fil).siVive()) {
	    		nave = naves.get(col).get(fil);
	    		nave.disparar();
	    		this.disparoEnemigo = nave.getProyectil();
	    		
	    	}
    	}
    	
    	this.disparoEnemigo.avanzar();
    }
    
    public void muroAtacado(int bloque, int sentido) {
    	if(sentido == Proyectil.SENTIDO_DESC)
    		this.disparoEnemigo.desactivar();
    	Bloque b = this.bloques.get(bloque);
    	b.recibiDisparo(sentido);
    }
    
    public ProyectilView getDisparoEnemigo() {
    	return this.disparoEnemigo.toView();
    }

    public void incrementarNivel() {
    	boolean paso = this.flotaInvasora.fueDestruida();
    	
    	if (paso)
    	{
    		nivel++;
    		this.puntajePartida.incrementarPuntaje(PuntajePartida.PUNTOS_PASAR_NIVEL);
    		incrementarVida(); // chequear
    		reiniciarPartida();
    	}

    }

    public void reiniciarPartida() {
    	
        //NavesInvasoras
    	flotaInvasora = new FlotaInvasora();
        
        // Muro
        bloques = new ArrayList<Bloque>(); 
        iniciarMuro();
        
        this.jugador.setPosicionX(Jugador.POSICION_INICIAL_X);
        
        //Disparos
    	//proyectiles = new ArrayList<Proyectil>();
    }

    public void moverEnemigo() {
    	boolean hastaMuro = false;
    	
    	// Chequear si el muro todavía existe para ver hasta dónde llegan las naves
    	for(Bloque b : this.bloques) {
    		if(b.siVivo())
    			hastaMuro = true;
    	}
 
    	if(hastaMuro && this.flotaInvasora.getLimiteInferior() < Bloque.POSICION_Y) {
    		
    		this.flotaInvasora.avanzar();
    		
    	} else if (!hastaMuro && this.flotaInvasora.getLimiteInferior() < Jugador.POSICION_Y) {
    		
    		this.flotaInvasora.avanzar();
    		
    	} else {
    		this.jugador.restarVida();
    		this.reiniciarPartida();
    	}
    }

    public void incrementarVida() {
		if(this.puntajePartida.mereceNuevaVida()) {
			this.cantVida++;
			this.puntajePartida.restarPuntajeParaVida();
		}
    }


    public void registrarPuntaje( String jugador, int puntaje) {
    		//paraque?
    }

 /*   public void registrarPartida( String jugador, int puntaje) {
        PuntajePartida puntajep = new PuntajePartida(puntaje,jugador);
        tablero.agregarOrdenado(puntajep);
    }*/
    
    public int getPuntaje() {
        return this.puntajePartida.getPuntaje();
    }
    
    public Tablero GetTablero() {
        return tablero ;
    }


    public void iniciarMuro() {
    	
    	bloques.clear(); 
    	for(int i=0; i<Bloque.BLOQUES_POR_MURO; i++)
         {
         	Bloque bloque = new Bloque(i * (Bloque.TAMANIO_LADO + Bloque.ESPACIO_ENTRE_BLOQUES)); 
         	bloques.add(bloque);        	
         }
    	 
    }
    
    public ArrayList<BloqueView> getMuro() {
    	ArrayList<BloqueView> result = new ArrayList<BloqueView>();
    	for(Bloque b : bloques) {
    		result.add(b.toView());
    	}
    	
    	return result;
    }
    
    public FlotaInvasoraView getFlotaInvasora() {
    	return this.flotaInvasora.toView();
    }
    

    public void setDificutad(String dificultad) {
    	this.dificultad = dificultad;
    }
    
    public void restarVida() {

    }

    public void pausar() {

    }

    public void despausar() {

    }

    public void salir() {

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
    
  /*  public ArrayList<Bloque> getBloque() {
		return bloques;
	}*/
    
    // VIEWS
    public JugadorView getJugador() {
    	return jugador.toView();
    }
    
    public static JuegoControlador getInstancia() { //Singleton ==> referencia static a la misma clase
	  if (instancia == null)
		  instancia = new JuegoControlador();
	  return instancia;
	 }
}

