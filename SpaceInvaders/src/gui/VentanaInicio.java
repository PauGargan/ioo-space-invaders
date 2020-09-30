package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;

import controlador.JuegoControlador;

import java.util.Enumeration;
import javax.swing.AbstractButton;


public class VentanaInicio extends JFrame {
	
	private JLabel rotulo;
	private JButton botonComenzar;
	private String dificultad;
	private JRadioButton cadete,guerrero,master;/*declaramos los objetos radioButtons*/
	private ButtonGroup grupoDificultad;
	private static JuegoControlador juego;
	//private ImageIcon ifondo;


	//JComboBox<String> listaDificultad;/*declaramos el objeto Combo*/
	
	public static void main(String[] args) {
			new VentanaInicio();

	}
	
	public VentanaInicio() {
		configurar();
		eventos();

	    
	}
	
	private void configurar() {
	
		Container c = this.getContentPane();
		c.setLayout(null);
		setSize(400,300);
		setVisible(true);
		setLayout(new BorderLayout());
		setLocationRelativeTo(null); // lo posiciona en el medio de la pantalla
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);

	//	c.setBackground(Color.DARK_GRAY);
		c.setBackground(new java.awt.Color(21,170,215));
		//this.setContentPane(new JLabel(new javax.swing.ImageIcon(getClass().getResource("fondo" + ".jpg"))));
		this.setTitle("Dificultad");
		//c.setBackground(Color.getHSBColor(100,47,30));
		
		rotulo = new JLabel("Selecciona una dificultad:")	;
		rotulo.setBounds(60, 60, 290, 23);
		rotulo.setFont(new java.awt.Font("Rockwell", 3, 19));
		rotulo.setForeground(Color.white);
		
		
		grupoDificultad = new ButtonGroup();
		cadete = new JRadioButton();
		cadete.setText("Cadete");
		cadete.setBounds(100, 90, 100, 15);
		cadete.setFont(new java.awt.Font("Rockwell", 1, 14));
		cadete.setBackground(new java.awt.Color(21,170,215));
		cadete.setForeground(Color.white);
		guerrero = new JRadioButton();
		guerrero.setText("Guerrero");
		guerrero.setBounds(100, 110, 100, 15);
		guerrero.setFont(new java.awt.Font("Rockwell", 1, 14));
		guerrero.setBackground(new java.awt.Color(21,170,215));
		guerrero.setForeground(Color.white);
		master = new JRadioButton();
		master.setText("Master");
		master.setBounds(100, 130, 100, 15);
		master.setFont(new java.awt.Font("Rockwell", 1, 14));
		master.setBackground(new java.awt.Color(21,170,215));
		master.setForeground(Color.white);
		grupoDificultad.add(cadete);
		grupoDificultad.add(guerrero);
		grupoDificultad.add(master);
		
		botonComenzar = new JButton("Jugar");
		botonComenzar.setBounds(130,180,80, 23);
			
		

		c.add(rotulo);
		c.add(botonComenzar);
		c.add(cadete);
		c.add(guerrero);
		c.add(master);
		}

		private void eventos() {
			botonComenzar.addActionListener(new ActionListener() {
				
				public String getSelectedButtonText(ButtonGroup grupoDificultad) {
			           for (Enumeration<AbstractButton> buttons = grupoDificultad.getElements(); buttons.hasMoreElements();) {
			               AbstractButton button = buttons.nextElement();

			               if (button.isSelected()) {
			                   return button.getText();
			               }
			           }

			           return null;
			       }
				
				public void actionPerformed(ActionEvent e) {
					dificultad= getSelectedButtonText(grupoDificultad);
					if(dificultad == null)
						JOptionPane.showMessageDialog(null,"Seleccione dificultad","Error",JOptionPane.ERROR_MESSAGE);
					else
					{	//juego.setDificutad(dificultad);
						//juego.comenzarJuego(1);
					JOptionPane.showInternalMessageDialog(null, dificultad);
					} // lo muestra en una ventana SACAR!!
					
				}}); 
		}
}
		

	
		
