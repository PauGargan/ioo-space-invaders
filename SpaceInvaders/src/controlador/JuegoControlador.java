package controlador;


import java.util.ArrayList;

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
import view.NaveInvasoraView;

public class JuegoControlador {


    private final int TAMANIO_FLOTA_NAVES_INVASORAS = 15;
    private final int TAMANIO_FILA_NAVES_INVASORAS = 3;
    private final int TAMANIO_COLUMNA_NAVES_INVASORAS = 5;
    private final int NAVE_X_INICIAL = 200;
	private final int NAVE_Y_INICIAL = 0;
    
	public static  JuegoControlador instancia; // singleton un solo objeto controlador
	private String dificultad;
    private int cantVida;
    private int nivel = 1;
    private ArrayList <ArrayList<NaveInvasora>> naves;
    private FlotaInvasora flotaInvasora;
    private ArrayList <Bloque> bloques;
    private ArrayList <Proyectil> proyectiles;
    private Jugador jugador;
    private Tablero tablero = new Tablero();
    private AreaDeJuego area= new AreaDeJuego();
    private PuntajePartida puntajePartida;
    
    
    
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
    		for(int i=14; i>=0; i--) // verifica si las naves llegaron al jugador
    		{	
    			//if(naves.get(i).getPosicionY() > 300)
	    		return true;
    		}
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
    }

    public void dispararEnemigo() {
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
    	nivel = 1; 
    }

    public void moverEnemigo() {
    	
    	this.flotaInvasora.avanzar();
    	/*
    	int bordeIzq = 0;
    	int bordeDer = 0;
    	int bordeSup = 0;
    	int bordeInf = 0;
    	
    	bordeIzq = this.naves.get(0).get(0).getPosicionX();
    	bordeDer = this.naves.get(TAMANIO_COLUMNA_NAVES_INVASORAS-1).get(0).getPosicionX() + 60;
    	bordeSup = this.naves.get(0).get(0).getPosicionY();
    	bordeInf = this.naves.get(0).get(TAMANIO_FILA_NAVES_INVASORAS-1).getPosicionY();
    	    	
    	for(int i=0; i < this.naves.size(); i++) {
    		if(this.naves.get(i).get(0).getSentido() == ObjetoMovil.DIR_DERECHA) {
    			this.naves.get(i).get(0).setSentido(ObjetoMovil.DIR_DERECHA);
	    		for(int j=0; j < this.naves.get(i).size(); j++) {
	    			
	    			this.naves.get(i).get(j).mover(ObjetoMovil.DIR_DERECHA, 3);
	    			
	    		}
	    		bordeDer = this.naves.get(TAMANIO_COLUMNA_NAVES_INVASORAS-1).get(0).getPosicionX() + 60;
	    		if(bordeDer >= 900) {
	    			this.naves.get(i).get(0).setSentido(ObjetoMovil.DIR_IZQUIERDA);
	    		}
    		} else if(this.naves.get(i).get(0).getSentido() == ObjetoMovil.DIR_IZQUIERDA) {
				for(int j=0; j < this.naves.get(i).size(); j++) {
					
	    			this.naves.get(i).get(j).mover(ObjetoMovil.DIR_IZQUIERDA, 3);
	    			
	    		}
				bordeIzq = this.naves.get(0).get(0).getPosicionX();
				if(bordeIzq < 900) {
					this.naves.get(0).get(0).setSentido(ObjetoMovil.DIR_DERECHA);
				}
    		}
    	} */
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
    
    public void iniciarNavesInvasoras() {
    	this.naves = new ArrayList<ArrayList<NaveInvasora>>();
    	
    	for(int i=0; i<TAMANIO_COLUMNA_NAVES_INVASORAS; i++) {
    		
    		this.naves.add(new ArrayList<NaveInvasora>());
    		int posX = i * (50 + 10);
    		
    		for(int j=0; j<TAMANIO_FILA_NAVES_INVASORAS; j++) {
    			NaveInvasora n = new NaveInvasora(posX,j* (60+10));
    			this.naves.get(i).add(n);
    		}
    		
    	}
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

