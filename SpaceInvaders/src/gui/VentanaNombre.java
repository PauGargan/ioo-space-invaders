package gui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.*;


import modelo.*;
import controlador.*;

public class VentanaNombre extends JFrame {

	private static final long serialVersionUID = 1L;

	private JLabel rotulo;
	private JButton JBAceptar;
	private JTextField JTexto;
	private JuegoControlador juego= new JuegoControlador();
	
	public static void main(String[] args) {
			new VentanaNombre();

	}
	
	public VentanaNombre() {
		configurar();
		eventos();
		setSize(300, 200); //  this.setSize(juego.getAnchoArea(),juego.getAltoArea());
		setVisible(true);
	}
	
	private void configurar() {
		Container c = this.getContentPane();
		c.setLayout(null);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		c.setBackground(new java.awt.Color(21,170,215));
		setLocationRelativeTo(null);
		
		rotulo = new JLabel("Ingrese su nombre:");
		rotulo.setBounds(75,20, 150, 20);
		rotulo.setVisible(true);
		rotulo.setForeground(new java.awt.Color(255,255,255));
		rotulo.setFont(new java.awt.Font("Rockwell",1,15));
		
		JTexto = new JTextField();
		JTexto.setBounds(75, 50, 150, 30);
		
		JBAceptar = new JButton("Aceptar");
		JBAceptar.setBounds(110, 100, 80, 25);
		
		
		c.add(rotulo);
		c.add(JTexto);
		c.add(JBAceptar);
		
		
	}
	
	private void eventos() {
		
		JTexto.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if(JTexto.getText().toString().isEmpty())
					JTexto.setText("Ingrese su nombre");	
				
			}
			
			@Override  // ignora default
			public void focusGained(FocusEvent e) {
				if(JTexto.getText().toString().equalsIgnoreCase("Ingrese su nombre"))
					JTexto.setText("");
				
			}
		});
		JBAceptar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(JTexto.getText().toString().compareToIgnoreCase("")==0 || JTexto.getText().toString().compareToIgnoreCase("Ingrese su nombre")==0)
				{
					JOptionPane.showMessageDialog(null,"Ingrese su nombre",
							"Informacion necesaria",JOptionPane.ERROR_MESSAGE);
					return;
				}
				juego.registrarPuntaje(JTexto.getText(), juego.getPuntaje());
				VentanaTablero tablero  = new VentanaTablero();
				tablero.setVisible(true);
				tablero.setLocationRelativeTo(null);
				getContentPane().setVisible(false);
				dispose();
				
			}
		});
	}
}

