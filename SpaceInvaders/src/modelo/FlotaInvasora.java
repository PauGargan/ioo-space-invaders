package modelo;

import java.util.ArrayList;

import view.FlotaInvasoraView;
import view.NaveInvasoraView;

public class FlotaInvasora {
	
	public static final int NAVES_POR_FLOTA = 15;
	public static final int TAMANIO_COLUMNA_NAVES_INVASORAS = 5;
	public static final int TAMANIO_FILA_NAVES_INVASORAS = 3;
	public static final int ESPACIO_ENTRE_NAVES = 10;

	private int sentido;
	private int limiteDerecho;
	private int limiteIzquierdo;
	private int limiteInferior;
	private int velocidadHorizontal = 5; // Cambiar según nivel
	private int velocidadVertical = 20;
	
	private ArrayList <ArrayList<NaveInvasora>> naves;
	
	public FlotaInvasora() {
		generarFlotaInvasora();
		this.sentido = ObjetoMovil.DIR_DERECHA;
	}
	
	private void generarFlotaInvasora() {
    	this.naves = new ArrayList<ArrayList<NaveInvasora>>();
    	
    	for(int i=0; i<TAMANIO_COLUMNA_NAVES_INVASORAS; i++) {
    		
    		this.naves.add(new ArrayList<NaveInvasora>());
    		int posX = i * (NaveInvasora.TAMANIO + ESPACIO_ENTRE_NAVES);
    		
    		for(int j=0; j<TAMANIO_FILA_NAVES_INVASORAS; j++) {
    			NaveInvasora n = new NaveInvasora(posX,j* (NaveInvasora.TAMANIO + ESPACIO_ENTRE_NAVES));
    			this.naves.get(i).add(n);
    		}
    		
    	}
    }
	
	public void avanzar() {
		// Mover a la derecha
		if(this.sentido == ObjetoMovil.DIR_DERECHA && this.getLimiteDerecho() <= AreaDeJuego.ancho) {
			moverFlota(this.sentido, this.velocidadHorizontal);
		
		// Mover a la izquierda
		} else if(this.sentido == ObjetoMovil.DIR_IZQUIERDA && this.getLimiteIzquierdo() > 0) {
			moverFlota(this.sentido, this.velocidadHorizontal);
		
		// Mover abajo y cambiar sentido a la izquierda
		} else if(this.sentido == ObjetoMovil.DIR_DERECHA) {
			this.sentido = ObjetoMovil.DIR_IZQUIERDA;
			moverFlota(ObjetoMovil.DIR_ABAJO, this.velocidadVertical);
		
		// Mover abajo y cambiar sentido a la derecha
		} else if(this.sentido == ObjetoMovil.DIR_IZQUIERDA) {
			this.sentido = ObjetoMovil.DIR_DERECHA;
			moverFlota(ObjetoMovil.DIR_ABAJO, this.velocidadVertical);
		}
	}
	
	private void moverFlota(int direccion, int movimientos) {
		for(int i=0; i < this.naves.size(); i++) {
			for(int j=0; j < this.naves.get(i).size(); j++) {
    			
    			this.naves.get(i).get(j).mover(direccion, movimientos);
    			
    		}
		}
	}

	public int getSentido() {
		return sentido;
	}

	public void setSentido(int sentido) {
		this.sentido = sentido;
	}

	public int getLimiteDerecho() {
		return this.naves.get(TAMANIO_COLUMNA_NAVES_INVASORAS-1).get(0).getPosicionX() + 60;
	}

	public void setLimiteDerecho(int limiteDerecho) {
		this.limiteDerecho = limiteDerecho;
	}

	public int getLimiteIzquierdo() {
		return this.naves.get(0).get(0).getPosicionX();
	}

	public void setLimiteIzquierdo(int limiteIzquierdo) {
		this.limiteIzquierdo = limiteIzquierdo;
	}

	public int getLimiteInferior() {
		return this.naves.get(0).get(TAMANIO_FILA_NAVES_INVASORAS-1).getPosicionY() + NaveInvasora.TAMANIO;
	}

	public void setLimiteInferior(int limiteInferior) {
		this.limiteInferior = limiteInferior;
	}
	
	public ArrayList<ArrayList<NaveInvasora>> getNaves() {
		return this.naves;
	}
	
	public FlotaInvasoraView toView() {
		
		ArrayList<ArrayList<NaveInvasoraView>> navesView = new ArrayList<ArrayList<NaveInvasoraView>>();
    	
    	for(int i=0; i < this.naves.size(); i++) {
    		navesView.add(new ArrayList<NaveInvasoraView>());
    		
    		for(int j=0; j < this.naves.get(i).size(); j++) {
    			navesView.get(i).add(this.naves.get(i).get(j).toView());
    		}
    	}
    	
		return new FlotaInvasoraView(this.sentido, this.limiteDerecho, this.limiteIzquierdo, 
				this.limiteInferior, navesView);
	}

}
