package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

import modelo.PuntajePartida;
import controlador.JuegoControlador;

public class VentanaTablero extends JFrame {
	
	private JLabel rotulo;
	private JButton volver;
	private DefaultTableModel tablero;
	private static JuegoControlador juego;
	

	
	public static void main(String[] args) {

				VentanaTablero inst = new VentanaTablero();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
	}
	
	public VentanaTablero() {
		
		configurar();
		eventos();
		
	    
	}
	
	private void configurar() {
		Container c = this.getContentPane();
		c.setLayout(null);
		setSize(600,400);
		//setLayout(new BorderLayout());
		setLocationRelativeTo(null); // lo posiciona en el medio de la pantalla
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		c.setBackground(new java.awt.Color(21,170,215));
		this.setTitle("TOP 10");
		
		
		tablero = new DefaultTableModel();
		tablero.addColumn("Puesto");
		tablero.addColumn("Nombre");
		tablero.addColumn("Puntaje");
		juego = new JuegoControlador();
		int i = 1;
		//for (PuntajePartida puntaje: juego.GetTablero().getTablero() ) {
			//Object[] row = { i++, puntaje.getNombreJugador(),puntaje.getpuntaje()};
			//tablero.addRow(row);		}
		
		// prueba
		
		for ( i = 1; i < 11; i++) {
		Object[] row = { i, "Prueba",i+100};
		tablero.addRow(row);	}
		
		JTable tabla = new JTable() {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int rowIndex, int colIndex) {
				return false;
			}
		};
		tabla.setAutoResizeMode(250);
		
		
		tabla.setModel(tablero);
		tabla.getTableHeader().setVisible(true);
		 // orden
		//JScrollPane scrollPane = new JScrollPane();
		//scrollPane.add(tabla);
		
		//scrollPane.setVisible(true);
		//c.add(scrollPane);
		tabla.setVisible(true);
		tabla.setBackground(new java.awt.Color(21,170,215));
		tabla.setBounds(90,20, 400, 200);
		tabla.setFont(new java.awt.Font("Rockwell", 1, 14));
		
		
		 Border b;
	     b = BorderFactory.createCompoundBorder();
	     b = BorderFactory.createCompoundBorder(b, BorderFactory.createMatteBorder(1,1,1,1,Color.white));
	           
		tabla.setBorder(b);
		
				// chequear
		//scrollPane.setBounds(90,40,400,200);
		
		volver = new JButton("Volver");
		volver.setBounds(250, 300, 75, 28);
		volver.setVisible(true);
				
		
		
		c.add(tabla);
		c.add(volver);
		
	}
	private void eventos() {
		
		volver.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaInicio inicio = new VentanaInicio();
				inicio.setVisible(true);
				inicio.setLocationRelativeTo(null);
				getContentPane().setVisible(false);
				dispose();
				
			}
		});
	}

}
