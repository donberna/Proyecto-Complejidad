/*
 * Dise�adores: Miguel Angel Bernal Colonia Codigo: 201153852 - 3743
 * 				Yeison Betancourt Solis Codigo: 201153328 - 3743 				
 * 				Andrea Mora Ospina Codigo: 201153685 - 3743
 * 
 * Instituto: Universidad del Valle - 06 Tulu�
 */

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.*;

@SuppressWarnings("serial")
public class Reduction extends JFrame{

	JPanel jpLeft, jpCenter, jpRight;
	
	private JLabel labelDirectory = new JLabel("Archivo:");
	private JLabel routeDirectory = new JLabel("");
	private JLabel superviser = new JLabel();
	private JLabel labelVC = new JLabel("Vertex Cover");
	private JLabel labelHC = new JLabel("Ciclo Hamiltoniano");
	private JLabel labelTSP = new JLabel("Problema de Agente Viajero");
	private JTextArea textAreaInf = new JTextArea();
	private JTextArea textAreaVC = new JTextArea();
	private JTextArea textAreaHC = new JTextArea();
	private JTextArea textAreaTSP = new JTextArea();
	private JButton examine = new JButton("Examinar");
	private JButton bExit = new JButton();
	private JButton bBack = new JButton();
	private JButton case1 = new JButton("VC <p HC");
	private JButton case2 = new JButton("HC <p TS");
	private JFileChooser file = new JFileChooser();
	
	private String dato;
	
	// Objetos
	VertexCover VC = new VertexCover();
	HamiltonianCycle HC = new HamiltonianCycle();
	TSP tsp = new TSP(); 
			
	public Reduction(){
		
		this.setExtendedState(MAXIMIZED_BOTH);		 
	    setVisible(true);
		setTitle("Reducci�n");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.WHITE);
		
		Font font1 = new Font("Tahoma", 1, 24);
		Font font2 = new Font("Tahoma", 1, 12);
		
		jpLeft = new JPanel();
		jpLeft.setLayout(null);
		jpLeft.setBackground(Color.WHITE);
		jpLeft.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY),BorderFactory.createEmptyBorder(30, 30, 30, 30)));
		jpLeft.setSize(((this.getToolkit().getScreenSize().width/3)-20), (this.getToolkit().getScreenSize().height-200));
		jpLeft.setLocation(20, 20);
		
		JScrollPane scrollPaneVC = new JScrollPane(textAreaVC);
		textAreaVC.setEditable(false);		
		
		textAreaInf.setText("Para demostrar que el problema de Agente Viajero es NP completo, \n" +
							"realizando una reducci�n desde Vertex Cover, primero reduciremos \n" +
							"desde vertex cover a circuito hamiltoniano (VC <p HC) para luego \n" +
							"reducir de circuito hamiltoniano a agente viajero (HC <p TS)");
		
		jpLeft.add(labelVC);
		jpLeft.add(textAreaInf);
		jpLeft.add(examine);
		jpLeft.add(labelDirectory);
		jpLeft.add(routeDirectory);
		jpLeft.add(scrollPaneVC);
		jpLeft.add(superviser);		
		
		getContentPane().add(jpLeft);
	
		labelVC.setBounds(150, 20, 200, 40);
		labelVC.setFont(font1);
		textAreaInf.setBounds(30, 100, 400, 80);
		examine.setBounds(275, 200, 100, 40);
		labelDirectory.setBounds(240, 280, 80, 40);
		labelDirectory.setFont(font2);
		routeDirectory.setBounds(295, 280, 150, 40);
		routeDirectory.setFont(font2);	
		scrollPaneVC.setBounds(250, 330, 150, 160);
		
		ImageIcon img1 = new ImageIcon("img/supervisor.png");
		superviser.setIcon(img1);
		superviser.setBounds(20, 220, 200, 250);
		
		jpCenter = new JPanel();
		jpCenter.setLayout(null);
		jpCenter.setBackground(Color.WHITE);
		jpCenter.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY),BorderFactory.createEmptyBorder(30, 30, 30, 30)));
		jpCenter.setSize(((this.getToolkit().getScreenSize().width/3)-20), (this.getToolkit().getScreenSize().height-200));
		jpCenter.setLocation(460, 20);
		
		JScrollPane scrollPaneHC = new JScrollPane(textAreaHC);
		textAreaHC.setEditable(false);
		
		
		jpCenter.add(labelHC);
		jpCenter.add(scrollPaneHC);
		jpCenter.add(case1);
		
		getContentPane().add(jpCenter);
	
		labelHC.setBounds(120, 20, 300, 40);
		labelHC.setFont(font1);
		scrollPaneHC.setBounds(30, 60, 380, 430);
		case1.setBounds(180, 500, 100, 40);
		case1.setEnabled(false);
		
		jpRight = new JPanel();
		jpRight.setLayout(null);
		jpRight.setBackground(Color.WHITE);
		jpRight.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY),BorderFactory.createEmptyBorder(30, 30, 30, 30)));
		jpRight.setSize(((this.getToolkit().getScreenSize().width/3)-20), (this.getToolkit().getScreenSize().height-200));
		jpRight.setLocation(900, 20);
		
		JScrollPane scrollPaneTSP = new JScrollPane(textAreaTSP);
		textAreaTSP.setEditable(false);
		
		
		jpRight.add(labelTSP);
		jpRight.add(scrollPaneTSP);
		jpRight.add(case2);
		
		getContentPane().add(jpRight);
	
		labelTSP.setBounds(50, 20, 400, 40);
		labelTSP.setFont(font1);
		scrollPaneTSP.setBounds(30, 60, 380, 430);
		case2.setBounds(180, 500, 100, 40);
		case2.setEnabled(false);
			
		getContentPane().add(bExit);
		getContentPane().add(bBack);		
		
		ImageIcon img2 = new ImageIcon("img/salir.png");		
		ImageIcon img3 = new ImageIcon("img/atras.png");	
		
		bExit.setIcon(img2);
		bExit.setBounds((this.getToolkit().getScreenSize().width-300), (this.getToolkit().getScreenSize().height-170), 150, 100);
		bExit.setOpaque(false);
		bExit.setContentAreaFilled(false);
		bExit.setBorderPainted(false);
		
		bBack.setIcon(img3);
		bBack.setBounds(200, (this.getToolkit().getScreenSize().height-170), 70, 100);
		bBack.setOpaque(false);
		bBack.setContentAreaFilled(false);
		bBack.setBorderPainted(false);
		
		EventManager events = new EventManager();
		examine.addActionListener(events);
		bExit.addActionListener(events);
		bBack.addActionListener(events);
		case1.addActionListener(events);
		case2.addActionListener(events);
			
	}
	
	public int returnInt(String s) {
        
		int i = 0;
        try {
            i = Integer.parseInt(s.trim());
        } catch (NumberFormatException nfe) {
            System.out.println("NumberFormatException: " + nfe.getMessage());
        }
        return i;
    }
	
	
	private void Init(String fileName) {
        
		File f = new File(fileName);
        String line;
        int i,j;       
        
        try {
            BufferedReader in = new BufferedReader(new FileReader(f));
            line = in.readLine();
            
            StringTokenizer st = new StringTokenizer(line);
            VC.setSizeMatriz(returnInt(st.nextToken()));
        	
        	i = 0;
        	textAreaVC.append(VC.getSizeMatriz()+"\n");
        	
        	VC.setMatriz(new int [VC.getSizeMatriz()] [VC.getSizeMatriz()]);
            
        	// se lee el arcivo de entrada 
            while ((line = in.readLine()) != null) {
            	j = 0;
            	if(i < VC.getSizeMatriz()){
            		textAreaVC.append(line); 
            		StringTokenizer st3 = new StringTokenizer(line);
            		
            		while(j < VC.getSizeMatriz()){
            			VC.getMatriz()[i][j] =  returnInt(st3.nextToken());
            			j++;
            		}
            		
                	if(!line.startsWith(" ")){
                		textAreaVC.append("\n");            	
                	}      
                	
                	i++;
            	}else{
            		StringTokenizer st2 = new StringTokenizer(line);
                	VC.setVC(returnInt(st2.nextToken()));
                	textAreaVC.append(line);
            	}
            }  
            in.close();
        }
        catch (IOException e) { /* Handle exceptions */ }
    }
	
	public class EventManager implements ActionListener{

		public void actionPerformed(ActionEvent eve) {
			
			if(eve.getSource().equals(bExit)){
				
				System.exit(0);
				
			}
			
			if(eve.getSource().equals(bBack)){
				
		        MainFrame mf = new MainFrame();
		        
		        mf.setLocationRelativeTo(null);
		        mf.setVisible(true);
				dispose();
				
			}
		
			if(eve.getSource().equals(examine)){
				
				File directory = new File("C:/Users/Yeison/Documents/Workspace/ProyectoComplejidad/datos");
				file.setCurrentDirectory(directory);
				int valor = file.showOpenDialog(Reduction.this);
				
				if(valor == JFileChooser.APPROVE_OPTION){
					dato = file.getName(file.getSelectedFile());				
				}

				textAreaVC.setText("");
				textAreaHC.setText("");
				textAreaTSP.setText("");
				case1.setEnabled(true);
				case2.setEnabled(false);
				routeDirectory.setText(dato);	
				Init("datos/"+dato);
				
			}
			
			if(eve.getSource().equals(case1)){

				ArrayList <Widget> w;
	            w = new ArrayList<Widget>();
	            case2.setEnabled(true);
	            VC.setEdges(0);
	            int i,j;
	            
	            HC.countEdges(VC,w);

	            HC.fillMatriz(VC.getEdges(),VC.getVC());
	      
	            HC.fillMatrizTags(VC.getEdges(),w);
	            
	            HC.fillMatrizWidget(w);
	            
	            HC.fillMatrizConnectionsWidgets(VC.getEdges());
	            	        
	            HC.fillMatrizConnectionsVertex(VC.getVC());
	            
	            textAreaHC.append(""+ (HC.getSizeMatriz()-1)+"\n");
	            
	            for(i = 0; i<HC.getSizeMatriz(); i++ ){
	                for(j = 0; j<HC.getSizeMatriz(); j++ ){
	                	textAreaHC.append(HC.getMatriz()[i][j]+"\t"); 
	                }
	                textAreaHC.append("\n");
	            }
				
			}
			
			if(eve.getSource().equals(case2)){
	
				tsp.setSizeMatriz(HC.getSizeMatriz());
				
				textAreaTSP.append(""+ (tsp.getSizeMatriz()-1));
				
				tsp.setMatrizHC(HC.getMatriz());
				
				tsp.makeCompleteGraph();
				
				tsp.allocateWeightTSP();
				
				for(int i = 0; i<tsp.getSizeMatriz(); i++ ){
	                for(int j = 0; j<tsp.getSizeMatriz(); j++ ){
	                	textAreaTSP.append(tsp.getMatriz()[i][j]+"\t"); 
	                }
	                textAreaTSP.append("\n");
	            }
				
				
				
			}
			
		}
	
	} 
		
}

