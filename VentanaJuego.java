package gui;


import java.awt.Color;
import java.awt.Container;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.WindowConstants;

import controlador.JuegoControlador;
import view.BloqueView;
import view.FlotaInvasoraView;
import view.JugadorView;
import view.NaveInvasoraView;
import view.ProyectilView;


public class VentanaJuego extends JFrame {


	private static final long serialVersionUID = 6705201031723404827L;
	private static final int VENTANA_ALTO = 700;
	private static final int VENTANA_ANCHO = 900;
	private static final int VELOCIDAD_JUGADOR = 20;
	
	private Timer timer;
	private Timer timerDos;
	private ArrayList<ArrayList<JLabel>> JLnaves = new ArrayList<ArrayList<JLabel>>();
	private JLabel JLjugador;
	private JLabel JLpuntaje, JLvidas;
	private ArrayList<JLabel> JLproyectil = new ArrayList<>();
	private ArrayList<JLabel> JLbloques = new ArrayList<>();
	private static final int velocidad = 90;
	private int contador = 0;
	private JLabel JLdisparo = new JLabel();
	private JLabel JLdisparoE = new JLabel();
	
	// CONSTRUCTOR
	public VentanaJuego () {
		configurar();
		//juego = j;
		eventos();
		setLocationRelativeTo(null);
		setSize(VENTANA_ANCHO, VENTANA_ALTO); //  this.setSize(juego.getAnchoArea(),juego.getAltoArea());
		setVisible(true);
		
	}
	
	private void configurar() {
		Container c = this.getContentPane();
		c.setLayout(null);
		this.setTitle("Spacer Invaders");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		//this.setResizable(false);
		//this.setContentPane(new JLabel(new javax.swing.ImageIcon(getClass().getResource("fondo" + ".jpg"))));
		
		
		//JUGADOR
		
		JLjugador = new JLabel("Jugador");
		JLjugador.setIcon(new javax.swing.ImageIcon(getClass().getResource("jugador" + ".png")));
		JugadorView j = JuegoControlador.getInstancia().getJugador();
		JLjugador.setBounds(j.getPosicionX(),j.getPosicionY(),80,80); 
		JLjugador.setVisible(true);
		
		//PUNTAJE
		JLpuntaje = new JLabel("Puntaje: 00" ); //String.valueOf(juego.getPuntaje()  CAMBIAR
		JLpuntaje.setBounds(VENTANA_ANCHO-150, 0, 170, 25); 
		JLpuntaje.setForeground(new java.awt.Color(21,170,215));
		JLpuntaje.setFont(new java.awt.Font("Tahoma",3,20));
		JLpuntaje.setVisible(true);
		
		
		
		//VIDAS
		int vidas = JuegoControlador.getInstancia().getJugador().getVidas();
		JLvidas = new JLabel("Vidas: " + vidas); //String.valueOf(juego.getPuntaje()
		//	JLpuntaje.setText("Puntaje: " + String.valueOf(juego.getPuntaje()));  CAMBIAR
		JLvidas.setBounds(10, 0, 170, 25); 
		JLvidas.setForeground(new java.awt.Color(21,170,215));
		JLvidas.setFont(new java.awt.Font("Tahoma",3,20));
		JLvidas.setVisible(true);
		
		
		
		//BLOQUES
		ArrayList<BloqueView> bloques = JuegoControlador.getInstancia().getMuro();
		for(BloqueView b : bloques) {
			JLabel JLbloque = new JLabel();
			JLbloque.setBounds(b.getPosicionX(),b.getPosicionY(),b.getTamanio(),b.getTamanio());
			JLbloque.setOpaque(true);
			JLbloque.setBackground(new Color(255, 255, 255, b.getProteccion()));
			JLbloque.setVisible(true);
			JLbloques.add(JLbloque);
			c.add(JLbloque);
		}
		
		
		// NAVES INVASORAS
		ArrayList<ArrayList<NaveInvasoraView>> flotaNaves = JuegoControlador.getInstancia().getFlotaInvasora().getNaves();
		
		for(int col=0; col < flotaNaves.size(); col++) {
			this.JLnaves.add(new ArrayList<JLabel>());
			for(int fil=0; fil < flotaNaves.get(fil).size(); fil++) {
				JLabel JLnave = new JLabel();
				JLnave.setIcon(new javax.swing.ImageIcon(getClass().getResource("naveInvasora.png")));
				JLnave.setBounds(flotaNaves.get(col).get(fil).getPosicionX(),flotaNaves.get(col).get(fil).getPosicionY(),60,60);
				JLnave.setVisible(true);
				c.add(JLnave);
				this.JLnaves.get(col).add(JLnave);
			}
		}
		
		
		c.add(JLjugador);
		c.add(JLpuntaje);
		c.add(JLvidas);

		
	}
	
	
	private void eventos() {
		// TIMER
		
		timer = new Timer(20, new ManejoTimer());
		timer.start();	
		
		//JUGADOR TECLADO
		
		this.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {}
			
			@Override
			public void keyReleased(KeyEvent e) {}
			
			@Override
			public void keyPressed(KeyEvent e) {
				
				if( e.getKeyCode() == 39) { // me muevo a la derecha
					int x = JuegoControlador.getInstancia().moverJugadorDer();
					
					if( x < VENTANA_ANCHO - 85 ) {
						
						JLjugador.setBounds(x, JLjugador.getY(), JLjugador.getWidth(), JLjugador.getHeight());
					} else {
						JuegoControlador.getInstancia().moverJugadorIzq();
					}
					
				} else if(e.getKeyCode() == 37) { // me muevo a la izq	
					
					int x = JuegoControlador.getInstancia().moverJugadorIzq();
					if(x > 5) {
						JLjugador.setBounds(x, JLjugador.getY(), JLjugador.getWidth(), JLjugador.getHeight());
					} else {
						JuegoControlador.getInstancia().moverJugadorIzq();
					}
				} else if(e.getKeyCode() == 32) {
					// Disparar jugador
					//mostrarDisparosJugador();

					JuegoControlador.getInstancia().disparar(true);
					timerDos = new Timer(15, new ManejoTimerDos());
					timerDos.start();	
				}
			}
		});
		
	}
	
	
	
	private void mostrarDisparosEnemigos() {
		JuegoControlador.getInstancia().dispararEnemigo();
		ProyectilView disparo = JuegoControlador.getInstancia().getDisparoEnemigo();
		
		System.out.println(disparo.getPosicionY());
		JLdisparoE.setBounds(disparo.getPosicionX(),disparo.getPosicionY(),5,20);
		JLdisparoE.setOpaque(true);
		JLdisparoE.setBackground(Color.red);
		JLdisparoE.setVisible(true);

		Container c = this.getContentPane();
		c.add(JLdisparoE);
		
		// Choco muro? 
		chocoMuro(disparo);
		chocoNaveJugador(disparo);
	}
	
	private void chocoMuro(ProyectilView disparo) {
		ArrayList<BloqueView> bloques = JuegoControlador.getInstancia().getMuro();
		Rectangle proy = new Rectangle(disparo.getPosicionX(), disparo.getPosicionY(), 5, 20);
		for(BloqueView b : bloques) {
			if(b.getProteccion() > 0) {
				Rectangle bloque = new Rectangle(b.getPosicionX(), b.getPosicionY(), b.getTamanio(), b.getTamanio());
				if(proy.intersects(bloque)) {
					JuegoControlador.getInstancia().muroAtacado(bloques.indexOf(b), disparo.getSentido());
				}
			}
		}
	}
	
	private void chocoNaveJugador(ProyectilView disparo) {
		JugadorView jugador = JuegoControlador.getInstancia().getJugador();
		//ArrayList<BloqueView> bloques = JuegoControlador.getInstancia().getMuro();
		Rectangle proy = new Rectangle(disparo.getPosicionX(), disparo.getPosicionY(), 5, 20);
		Rectangle juga = new Rectangle(jugador.getPosicionX(), jugador.getPosicionY(), 80, 80);
		if(proy.intersects(juga)) {
			System.out.println("INSERSECCION");
			JuegoControlador.getInstancia().matarJugador();
		}
	}
	
	private void chocoNaveEnemiga(ProyectilView disparo) {
		FlotaInvasoraView flota = JuegoControlador.getInstancia().getFlotaInvasora();
		Rectangle proy = new Rectangle(disparo.getPosicionX(), disparo.getPosicionY(), 5, 20);
		for(int i=0; i < flota.getNaves().size(); i++) {
			for(NaveInvasoraView n : flota.getNaves().get(i)) {
				Rectangle nave = new Rectangle(n.getPosicionX(), n.getPosicionY(), 60, 60);
				if(proy.intersects(nave)) {
					JuegoControlador.getInstancia().enemigoAtacado(i, flota.getNaves().get(i).indexOf(n));
				}
			}
		}
	}
	
	class ManejoTimerDos implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JuegoControlador.getInstancia().disparar(false);
			ProyectilView disparo = JuegoControlador.getInstancia().getJugador().getProyectil();
			JLdisparo.setBounds(disparo.getPosicionX(),disparo.getPosicionY(),5,20);
			JLdisparo.setOpaque(true);
			JLdisparo.setBackground(Color.blue);
			JLdisparo.setVisible(true);

			Container c = getContentPane();
			c.add(JLdisparo);
			
			// Choco muro? 
			chocoMuro(disparo);
			chocoNaveEnemiga(disparo);

			if(!disparo.estaActivo()) {
				timerDos.stop();
				JLdisparo.setVisible(false);
				JLdisparo = new JLabel();
			}
		}
	}
	
	class ManejoTimer implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(JuegoControlador.getInstancia().siGameOver()) {
				timer.stop();
				JOptionPane.showMessageDialog(null,JuegoControlador.getInstancia().getPuntaje()+" puntos.", "GAME OVER",JOptionPane.INFORMATION_MESSAGE);
				VentanaNombre nombre = new VentanaNombre();
				nombre.setVisible(true);
				nombre.setLocationRelativeTo(null);
				getContentPane().setVisible(false);
				dispose();
			}
			
			// juego.actualizarDisparos();

			
			if(JuegoControlador.getInstancia().incrementarNivel()) {
				configurar();
			}
			
			//actualizarEnem();
			//actualizarPosDefensor();
		//	mostrarDisparosJugador();
			mostrarDisparosEnemigos();
			moverNavesEnemigas();
			actualizarMuro(); // ex Bloque()
			
//			JLpuntaje.setText("Puntaje: " + String.valueOf(juego.getPuntaje())); // + JuegoControlador.getInstancia().getPuntaje());
			JLvidas.setText("Vidas: " + JuegoControlador.getInstancia().getJugador().getVidas());
			
/*			if (contador == velocidad) {
				juego.dispararEnemigo();
				contador=0;
			}
			contador++; */
		}
	}
	
	private void actualizarMuro(){
		ArrayList<BloqueView> bloques = JuegoControlador.getInstancia().getMuro();
		
		for (BloqueView bloque : bloques) {
			JLabel gBloque = JLbloques.get(bloques.indexOf(bloque));
			if(bloque.getProteccion() > 0) {
				
				gBloque.setBounds(bloque.getPosicionX(), bloque.getPosicionY(), 
						bloque.getTamanio(),bloque.getTamanio());
				gBloque.setBackground(new Color(0, 0, 0, bloque.getProteccion()));
			} else {
				gBloque.setVisible(false);
			}
		}
	}
	
	private void moverNavesEnemigas(){
		JuegoControlador.getInstancia().moverEnemigo();
		ArrayList<ArrayList<NaveInvasoraView>> navesView = JuegoControlador.getInstancia().getFlotaInvasora().getNaves();
		
		for(int col=0; col < navesView.size(); col++) {
			for(int fil=0; fil < navesView.get(fil).size(); fil++) {
				
				NaveInvasoraView naveView = navesView.get(col).get(fil);
				JLabel gNave = JLnaves.get(col).get(fil);
				if(naveView.getEstado()) {
					
					gNave.setBounds(naveView.getPosicionX(), naveView.getPosicionY(), 60, 60);
							//naveView.getTamanio(),naveView.getTamanio());
					//gBloque.setBackground(new Color(255, 76, 76, bloque.getProteccion()));
				} else {
					gNave.setVisible(false);
				}
			}
		}
	}
}
