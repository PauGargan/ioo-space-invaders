package modelo;


import java.util.*;


public class Bloque {

    private int proteccion;
    private int posicionX;
    private int posicionY; // determinar posicion!!!
    private int ancho;
    private int alto;
    private boolean vivo;
    
    public Bloque(int posicionX) {
    	proteccion = 20;
    	vivo = true;
    	this.posicionX = posicionX;
    	
    }

    public void iniciarBloque() {
    	proteccion = 20;
        vivo = true;
    }

     public void recibiDisparo(Proyectil proyectil) {
        
    	 if(proyectil.getPosicionX() == this.posicionX && proyectil.getPosicionY() == this.posicionY)
    	 {
    		 if(proyectil.getSentido() == 1) // arriba
    			 this.proteccion = this.proteccion -2;
    		 else
    			 this.proteccion -= this.proteccion;
    		 if(proteccion <1)
    			 this.vivo = false;
    	 }    	  	 
    }


    public boolean siVivo() {
        return this.vivo;
        }
        
      public int getPosX() {
            return posicionX;
    }
      
      public int getPosY() {
          return posicionY;
  }
}