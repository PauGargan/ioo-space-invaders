package gui;


import java.awt.Color;
import java.awt.Container;
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
import view.JugadorView;
import view.NaveInvasoraView;
import view.ProyectilView;


public class VentanaJuego extends JFrame {


	private static final long serialVersionUID = 6705201031723404827L;
	private static final int VENTANA_ALTO = 700;
	private static final int VENTANA_ANCHO = 900;
	private static final int VELOCIDAD_JUGADOR = 20;
	
	private Timer timer;
	private ArrayList<ArrayList<JLabel>> JLnaves = new ArrayList<ArrayList<JLabel>>();
	private JLabel JLjugador;
	private JLabel JLpuntaje, JLvidas, JLbloque;
	private ArrayList<JLabel> JLproyectil = new ArrayList<>();
	private ArrayList<JLabel> JLbloques = new ArrayList<>();
	private static JuegoControlador juego;
	private static final int velocidad = 90;
	private int contador = 0;
	private JLabel JLdisparo = new JLabel();
	
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
		//	JLbloque.setIcon(new javax.swing.ImageIcon(	getClass().getResource("bloque" + ".png")));
			JLbloque.setBounds(b.getPosicionX(),b.getPosicionY(),b.getTamanio(),b.getTamanio());
			JLbloque.setOpaque(true);
			JLbloque.setBackground(new Color(255, 76, 76, b.getProteccion()));
			JLbloque.setVisible(true);
			JLbloques.add(JLbloque);
			c.add(JLbloque);
		}
		
		
		// NAVES INVASORAS
		//ArrayList<ArrayList<NaveInvasoraView>> flotaNaves = JuegoControlador.getInstancia().getNavesInvasoras();
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
		
		timer = new Timer(15, new ManejoTimer());
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
					System.out.println("DISPARE");
					JuegoControlador.getInstancia().disparar();
				}
			}
		});
		
	}
	
	private void mostrarDisparosJugador() {
		JuegoControlador.getInstancia().disparar();
		ProyectilView disparo = JuegoControlador.getInstancia().getJugador().getProyectil();
		
		//	JLbloque.setIcon(new javax.swing.ImageIcon(	getClass().getResource("bloque" + ".png")));
		System.out.println(disparo.getPosicionY());
		JLdisparo.setBounds(disparo.getPosicionX(),disparo.getPosicionY(),5,20);
		JLdisparo.setOpaque(true);
		JLdisparo.setBackground(Color.yellow);
		JLdisparo.setVisible(true);
		//JLbloques.add(JLbloque);
		Container c = this.getContentPane();
		c.add(JLdisparo);

	}
	
	private void mostrarDisparosEnemigos() {
		JuegoControlador.getInstancia().dispararEnemigo();
		ProyectilView disparo = JuegoControlador.getInstancia().getDisparoEnemigo();
		
		System.out.println(disparo.getPosicionY());
		JLdisparo.setBounds(disparo.getPosicionX(),disparo.getPosicionY(),5,20);
		JLdisparo.setOpaque(true);
		JLdisparo.setBackground(Color.pink);
		JLdisparo.setVisible(true);

		Container c = this.getContentPane();
		c.add(JLdisparo);
	}
	
	class ManejoTimer implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(JuegoControlador.getInstancia().siGameOver()){
				timer.stop();
				JOptionPane.showMessageDialog(null," puntos.", "GAME OVER",JOptionPane.INFORMATION_MESSAGE);
				//VentanaNombre nombre = new VentanaNombre();
				//nombre.setVisible(true);
				//nombre.setLocationRelativeTo(null);
				dispose();
			}
			
			// juego.actualizarDisparos();

			
		//	juego.incrementarNivel();
			
			//actualizarEnem();
			//actualizarPosDefensor();
			mostrarDisparosEnemigos();
			moverNavesEnemigas();
			actualizarMuro(); // ex Bloque()
			
//			JLpuntaje.setText("Puntaje: " + String.valueOf(juego.getPuntaje()));
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
				gBloque.setBackground(new Color(255, 76, 76, bloque.getProteccion()));
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
