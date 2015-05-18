/*
 * Dise�adores: Miguel Angel Bernal Colonia Codigo: 201153852 - 3743
 * 				Yeison Betancourt Solis Codigo: 201153328 - 3743 				
 * 				Andrea Mora Ospina Codigo: 201153685 - 3743
 * 
 * Instituto: Universidad del Valle - 06 Tulu�
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class VentanaPrincipal extends JFrame{

	private JLabel title1 = new JLabel("Reduccion desde VC a TPS");
	private JButton case1 = new JButton("Iniciar");
	private JLabel regImg = new JLabel();
	
	public VentanaPrincipal(){
		
		setTitle("Proyecto de Complejidad");
		setSize(800,400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		getContentPane().setLayout(null);	
		
		Font font1 = new Font("Tahoma", 1, 30);
		
		getContentPane().add(title1);
		getContentPane().add(case1);
		getContentPane().add(regImg);
		
		title1.setBounds(170, 5, 500, 100);
		title1.setFont(font1);

		case1.setBounds(355, 270, 100, 50);
				
		ImageIcon img = new ImageIcon("img/grafo.gif");
		
		regImg.setIcon(img);
		regImg.setBounds(0, 0, 800, 400);
		
		ManejoDeEventos eventos = new ManejoDeEventos();
		
		case1.addActionListener(eventos);
		
	}
	
	public class ManejoDeEventos implements ActionListener{

		public void actionPerformed(ActionEvent eve) {
							
			if(eve.getSource().equals(case1)){
				
				Cargar vent = new Cargar();
				
				vent.setLocationRelativeTo(null);
				vent.setVisible(true);
				dispose();
				
			}	
			
		}
	
	} 
		
}

