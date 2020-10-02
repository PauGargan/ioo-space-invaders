package modelo;


import view.BloqueView;


public class Bloque {

	public static final int BLOQUES_POR_MURO = 5;
	public static final int ESPACIO_ENTRE_BLOQUES = 50;
	public static final int TAMANIO_LADO = 80;
	public static final int POSICION_Y = 500;
	private final int PROTECCION_MAX = 100;	
	private final int DANIO_ENEMIGO = 5;
	private final int DANIO_AMIGO = 10;
	
    private int proteccion;
    private int posicionX;
    private int posicionY; 
    private int ancho = 80;
    private int alto = 80;
    
    public Bloque(int posicionX) {
    	proteccion = PROTECCION_MAX;
    	this.posicionX = posicionX;
    	this.posicionY = POSICION_Y;
    	
    }

    public void iniciarBloque() {
    	proteccion = PROTECCION_MAX;
    }

     public void recibiDisparo(int sentido) {
        
	 	if(sentido == Proyectil.SENTIDO_ASC) // arriba
			 this.proteccion -= DANIO_AMIGO;
		 else
			 this.proteccion -= DANIO_ENEMIGO;    	  	 
    }


    public boolean siVivo() {
        return this.proteccion > 0;
    }
        
    public int getPosX() {
        return posicionX;
    }
      
    public int getPosY() {
        return posicionY;
    }
    
    public int getProteccion() {
        return proteccion;
    }
    
    public void setProteccion(int proteccion) {
        this.proteccion = proteccion;
    }
    
    public void reducirProteccion (int cant) {
    	this.proteccion -= cant;
    }
    
    public BloqueView toView() {
    	return new BloqueView(this.posicionX, this.posicionY, this.proteccion, TAMANIO_LADO);
    }
}