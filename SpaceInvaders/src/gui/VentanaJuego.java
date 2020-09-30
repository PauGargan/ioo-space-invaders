package gui;


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
import modelo.Bloque;


public class VentanaJuego extends JFrame {


	private static final long serialVersionUID = 6705201031723404827L;
	private static final int VENTANA_ALTO = 700;
	private static final int VENTANA_ANCHO = 900;
	
	private Timer timer;
	private ArrayList<JLabel> naves = new ArrayList<>();
	private JLabel JLjugador;
	private JLabel JLpuntaje, JLvidas, JLbloque;
	private ArrayList<JLabel> JLproyectil = new ArrayList<>();
	private ArrayList<JLabel> JLbloques = new ArrayList<>();
	private static JuegoControlador juego;
	private static final int velocidad = 90;
	private int contador = 0;
	
	// CONSTRUCTOR
	public VentanaJuego (JuegoControlador j) {
		configurar();
		juego = j;
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
		JLjugador.setBounds(400,VENTANA_ALTO-100,80,80); // cambiar por juego.getPosXJugador()
		JLjugador.setVisible(true);
		
		
		
		//PUNTAJE
		JLpuntaje = new JLabel("Puntaje: 00" ); //String.valueOf(juego.getPuntaje()  CAMBIAR
	//	JLpuntaje.setText("Puntaje: " + String.valueOf(juego.getPuntaje()));
		JLpuntaje.setBounds(VENTANA_ANCHO-150, 0, 170, 25); 
		JLpuntaje.setForeground(new java.awt.Color(21,170,215));
		JLpuntaje.setFont(new java.awt.Font("Tahoma",3,20));
		JLpuntaje.setVisible(true);
		
		
		
		//VIDAS
		JLvidas = new JLabel("Vidas: 00"); //String.valueOf(juego.getPuntaje()
		//	JLpuntaje.setText("Puntaje: " + String.valueOf(juego.getPuntaje()));  CAMBIAR
		JLvidas.setBounds(10, 0, 170, 25); 
		JLvidas.setForeground(new java.awt.Color(21,170,215));
		JLvidas.setFont(new java.awt.Font("Tahoma",3,20));
		JLvidas.setVisible(true);
		
		
		
		//BLOQUES
		for (int i = 0; i <= 5; i++) {
			JLabel JLbloque = new JLabel("Bloque");
			JLbloque.setIcon(new javax.swing.ImageIcon(	getClass().getResource("bloque" + ".png")));
			JLbloques.add(JLbloque);
			JLbloque.setVisible(true);
			int bloqueX = i * (80 + 50);
			System.out.println(bloqueX);
			JLbloque.setBounds(bloqueX,VENTANA_ALTO-200,80,80);
			c.add(JLbloque);
			
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
				
				//System.out.println(e.getKeyCode()); // para saber que codigo es
				if( e.getKeyCode() == 39) // me muevo a la derecha
				{   int x = JLjugador.getX() +10;
					if( x<1105 )
						JLjugador.setBounds(x, JLjugador.getY(), JLjugador.getWidth(), JLjugador.getHeight());
					}
					else
					if(e.getKeyCode() == 37) // izq
					{	int x = JLjugador.getX() -10;
						if(x>15)
							JLjugador.setBounds(x, JLjugador.getY(), JLjugador.getWidth(), JLjugador.getHeight());
					}
			}
		});
		
		}
	class ManejoTimer implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(juego.siGameOver()){
				timer.stop();
				JOptionPane.showMessageDialog(null,juego.getPuntaje()+" puntos.", "GAME OVER",JOptionPane.INFORMATION_MESSAGE);
				VentanaNombre nombre = new VentanaNombre();
				nombre.setVisible(true);
				nombre.setLocationRelativeTo(null);
				dispose();
			}
			juego.moverEnemigo();
			// juego.actualizarDisparos();
			//actualizarDisparos();
			
			juego.incrementarNivel();
			
			//actualizarEnem();
			//actualizarPosDefensor();
			Bloque(); 
			
			JLpuntaje.setText("Puntaje: " + String.valueOf(juego.getPuntaje()));
			JLvidas.setText("Vidas: " + String.valueOf(juego.getVida()));
			
			if (contador == velocidad) {
				juego.dispararEnemigo();
				contador=0;
			}
			contador++;
		}
	}
	
	private void Bloque(){
		for (Bloque bloques : juego.getBloque()) {
			if(bloques.siVivo()){
				
				JLbloques.get(juego.getBloque().indexOf(bloques)).setBounds(bloques.getPosX(), bloques.getPosY(), 50,50);
				JLbloques.get(juego.getBloque().indexOf(bloques)).setText("Muro " + juego.getBloque().indexOf(bloques));
			}
			else
				JLbloques.get(juego.getBloque().indexOf(bloques)).setVisible(false);
		}
	}
}
