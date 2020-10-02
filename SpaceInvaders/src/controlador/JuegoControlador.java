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
    private ArrayList <Proyectil> proyectiles;
    private Jugador jugador;
    private Tablero tablero = new Tablero();
    private AreaDeJuego area= new AreaDeJuego();
    private PuntajePartida puntajePartida;
    private Proyectil disparoEnemigo;
    
    
    
    private JuegoControlador() { 
    	comenzarJuego(1);
    } 

    public void inicializarJuego(String nombre) {
    	puntajePartida = new PuntajePartida();
    }
    
    public void comenzarJuego(int nivel) {
    	
        //Nivel
    	this.nivel = nivel;
    	
        //NavesInvasoras
    	flotaInvasora = new FlotaInvasora();
    	//iniciarNavesInvasoras();
        
        //Jugador
        jugador = new Jugador();
        
        // Muro
        bloques = new ArrayList<Bloque>(); 
        iniciarMuro();
        
        //Disparos
    	proyectiles = new ArrayList<Proyectil>();
    	
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
    		System.out.println("EN CONTROLADOR");
    		this.jugador.disparar();
    	}
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
		    	System.out.println(col + " " + fil);
	    	} while (!naves.get(col).get(fil).siVive());
	    	
	    	if(naves.get(col).get(fil).siVive()) {
	    		nave = naves.get(col).get(fil);
	    		nave.disparar();
	    		this.disparoEnemigo = nave.getProyectil();
	    		
	    	}
    	}
    	
    	this.disparoEnemigo.avanzar();
    }
    
    public ProyectilView getDisparoEnemigo() {
    	return this.disparoEnemigo.toView();
    }
/*
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
    		this.puntajePartida.incrementarPuntaje(200);
    		incrementarVida(); // chequear
    		comenzarJuego(nivel);
    	}

    }
*/
    public void reiniciarPartida() {
    	
        //NavesInvasoras
    	flotaInvasora = new FlotaInvasora();
        
        // Muro
        bloques = new ArrayList<Bloque>(); 
        iniciarMuro();
        
        //Disparos
    	proyectiles = new ArrayList<Proyectil>();
    	
    	System.out.println(this.jugador.getVidas());
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
         	Bloque bloque = new Bloque(i * (Bloque.TAMANIO_LADO + Bloque.ESPACIO_ENTRE_BLOQUES)); //chequear posicion
         	bloques.add(bloque);        	
         }
    	 
    }
    
    public ArrayList<BloqueView> getMuro() {
    	ArrayList<BloqueView> result = new ArrayList();
    	for(Bloque b : bloques) {
    		result.add(b.toView());
    	}
    	
    	return result;
    }
    
    public FlotaInvasoraView getFlotaInvasora() {
    	return this.flotaInvasora.toView();
    }
    /* SE PUEDE BORRAR
    public ArrayList<ArrayList<NaveInvasoraView>> getNavesInvasoras() {
  
    	ArrayList<ArrayList<NaveInvasoraView>> result = new ArrayList<ArrayList<NaveInvasoraView>>();
    	
    	for(int i=0; i<TAMANIO_COLUMNA_NAVES_INVASORAS; i++) {
    		result.add(new ArrayList<NaveInvasoraView>());
    		
    		for(int j=0; j<TAMANIO_FILA_NAVES_INVASORAS; j++) {
    			result.get(i).add(this.naves.get(i).get(j).toView());
    		}
    	}
    	
    	return result;
    }*/
    

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

