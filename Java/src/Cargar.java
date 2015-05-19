/*
 * Diseñadores: Miguel Angel Bernal Colonia Codigo: 201153852 - 3743
 * 				Yeison Betancourt Solis Codigo: 201153328 - 3743 				
 * 				Andrea Mora Ospina Codigo: 201153685 - 3743
 * 
 * Instituto: Universidad del Valle - 06 Tuluá
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
public class Cargar extends JFrame{

	JPanel jpLeft, jpCenter, jpRight;
	
	private JLabel labelDirectory = new JLabel("Archivo:");
	private JLabel routeDirectory = new JLabel("");
	private JLabel superviser = new JLabel();
	private JLabel labelVC = new JLabel("Vertex Cover");
	private JLabel labelCH = new JLabel("Ciclo Hamiltoniano");
	private JLabel labelTSP = new JLabel("Problema de Agente Viajero");
	private JTextArea textAreaInf = new JTextArea();
	private JTextArea textAreaVC = new JTextArea();
	private JTextArea textAreaCH = new JTextArea();
	private JTextArea textAreaTSP = new JTextArea();
	private JButton examine = new JButton("Examinar");
	private JButton bExit = new JButton();
	private JButton bBack = new JButton();
	private JButton case1 = new JButton("VC <p HC");
	private JButton case2 = new JButton("HC <p TS");
	private JFileChooser archivo = new JFileChooser();
	
	private String dato;
	
	// Objetos
	VertexCover vc = new VertexCover();
	CicloHamiltoniano CH = new CicloHamiltoniano();
			
	public Cargar(){
		
		this.setExtendedState(MAXIMIZED_BOTH);		 
	    setVisible(true);
		setTitle("Reducción");
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
							"realizando una reducción desde Vertex Cover, primero reduciremos \n" +
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
		
		JScrollPane scrollPaneCH = new JScrollPane(textAreaCH);
		textAreaCH.setEditable(false);
		
		
		jpCenter.add(labelCH);
		jpCenter.add(scrollPaneCH);
		jpCenter.add(case1);
		
		getContentPane().add(jpCenter);
	
		labelCH.setBounds(120, 20, 300, 40);
		labelCH.setFont(font1);
		scrollPaneCH.setBounds(30, 60, 380, 430);
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
		
		ManejoDeEventos eventos = new ManejoDeEventos();
		examine.addActionListener(eventos);
		bExit.addActionListener(eventos);
		bBack.addActionListener(eventos);
		case1.addActionListener(eventos);
		case2.addActionListener(eventos);
			
	}
	
	public int returnInt (String s) {
        
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
            vc.setTamMatriz(returnInt(st.nextToken()));
        	
        	i = 0;
        	textAreaVC.append(vc.getTamMatriz()+"\n");
        	
        	vc.setMatriz(new int [vc.getTamMatriz()] [vc.getTamMatriz()]);
            
        	// se lee el arcivo de entrada 
            while ((line = in.readLine()) != null) {
            	j = 0;
            	if(i < vc.getTamMatriz()){
            		textAreaVC.append(line); 
            		StringTokenizer st3 = new StringTokenizer(line);
            		
            		while(j < vc.getTamMatriz()){
            			vc.getMatriz()[i][j] =  returnInt(st3.nextToken());
            			j++;
            		}
            		
                	if(!line.startsWith(" ")){
                		textAreaVC.append("\n");            	
                	}      
                	
                	i++;
            	}else{
            		StringTokenizer st2 = new StringTokenizer(line);
                	vc.setVC(returnInt(st2.nextToken()));
                	textAreaVC.append(line);
            	}
            }  
            in.close();
        }
        catch (IOException e) { /* Handle exceptions */ }
    }
	
	public class ManejoDeEventos implements ActionListener{

		public void actionPerformed(ActionEvent eve) {
			
			if(eve.getSource().equals(bExit)){
				
				System.exit(0);				
			}
			
			if(eve.getSource().equals(bBack)){
				
		        VentanaPrincipal ventana = new VentanaPrincipal();
		        
		        ventana.setLocationRelativeTo(null);
		        ventana.setVisible(true);
				dispose();
				
			}
		
			if(eve.getSource().equals(examine)){
				
				File directory = new File("C:/Users/Yeison/Documents/Workspace/ProyectoComplejidad/datos");
				archivo.setCurrentDirectory(directory);
				int valor = archivo.showOpenDialog(Cargar.this);
				
				if(valor == JFileChooser.APPROVE_OPTION){
					dato = archivo.getName(archivo.getSelectedFile());				
				}

				case1.setEnabled(true);
				routeDirectory.setText(dato);	
				Init("datos/"+dato);
				
			}
			
			if(eve.getSource().equals(case1)){

				ArrayList <Widget> w;
	            w = new ArrayList<Widget>();
	            case2.setEnabled(true);
	            vc.setAristas(0);
	            int i,j,index;
	            int row,col,colTmp;
	            int n,tmp;
	            char pos0,pos2,tmpPos;
	            
	            // Se contabilizan el numero de aristas del grafo a partir de su matriz de adyacencia 
	            for(row=0; row<vc.getTamMatriz(); row++){
	            	for(col=row; col<vc.getTamMatriz(); col++){
	            		if(vc.getMatriz()[row][col] == 1 && vc.getMatriz()[col][row] == 1){
	            			vc.setAristas(vc.getAristas()+1);
	            			Widget a = new Widget(""+row+col);
	            			w.add(a);
	            			
	            		}
	            	}
	            }                     
	           
	            // se crea la matriz del grafo de la reduccion
	            CH.setTamMatriz((vc.getAristas() * 12) + vc.getVC()+1); 
	            CH.setMatriz(new String [CH.getTamMatriz()] [CH.getTamMatriz()]);
	            for(i = 0; i<CH.getTamMatriz(); i++ ){
	                for(j = 0; j<CH.getTamMatriz(); j++ ){
	                	CH.getMatriz()[i][j]=""+0;
	                }
	            }
	      
	            index=0;
	            while(index<w.size()){
	            	int a = 0;
	            	for(i = 0; i<(vc.getAristas() * 12); i++ ){
	            		CH.getMatriz()[0][i+1] = w.get(index).etiquetas[a];
	            		CH.getMatriz()[i+1][0] = w.get(index).etiquetas[a];
	                    a++;
	                    if(a>=12){
	                    	index++;
	                    	a=0;
	                    }
	                }
	            }
	            
	            index=0;
	            row=1;
	            col=1;
	            colTmp=1;
	            while(index<w.size()){
	            	col=colTmp;
	            	for(i = 1; i<13; i++ ){
	            		for(j = 1; j<13; j++ ){
	            			CH.getMatriz()[row][col] = ""+w.get(index).matriz[i-1][j-1];
	            			col++;
	            		}
	            		colTmp++;
	            		col= 1 + (index*12);
	            		row++;
	                }
	            	index++;
	            }   
	            
	            n=0;
	            tmp=12;
	            i=6;
	            while(n<vc.getAristas()){
	                pos0 = CH.getMatriz()[i][0].toCharArray()[0];
	                pos2 = CH.getMatriz()[i][0].toCharArray()[2];
	                for(j = (tmp+1); j<CH.getMatriz().length-2; j++ ){
	                	if(pos2=='5'){
	                		tmpPos='0';
	                		if((pos0 == CH.getMatriz()[0][j].toCharArray()[0]) && (tmpPos == CH.getMatriz()[0][j].toCharArray()[2])){
	                    		CH.getMatriz()[i][j] = "X";
	                    		CH.getMatriz()[j][i] = "X";
	                    		break;
	                    	}
	                	}else
	                		if(pos2=='0'){
	                			tmpPos='5';
	                    		if((pos0 == CH.getMatriz()[0][j].toCharArray()[0]) && (tmpPos == CH.getMatriz()[0][j].toCharArray()[2])){
	                        		CH.getMatriz()[i][j] = "X";
	                        		CH.getMatriz()[j][i] = "X";
	                        		break;
	                        	}
	                		}
	                }
	                if(i==tmp){
	                	i++;
	                	tmp*=2;
	                }
	                i+=6;
	                n++;
	            }
	            	        
	            i=1;
	            while(i<CH.getMatriz().length-2){
	                for(j = 0; j<CH.getMatriz().length-2; j++ ){
                		if(CH.getMatriz()[i][j].toCharArray()[0] == 'X'){
                    		CH.getMatriz()[i][j] = "1";
                    		for(n = vc.getVC(); n>0; n-- ){
                    			CH.getMatriz()[i][CH.getMatriz().length-n] = "0";
                    			CH.getMatriz()[CH.getMatriz().length-n][i] = "0";
                    		}
                        	break;
                    	}else{
                    		for(n = vc.getVC(); n>0; n-- ){
                    			CH.getMatriz()[i][CH.getMatriz().length-n] = "1";
                    			CH.getMatriz()[CH.getMatriz().length-n][i] = "1";
                    		}
                    	}
	                }
	                i+=6;
	            }
	            
	            i=6;
	            while(i<CH.getMatriz().length-2){
	                for(j = 0; j<CH.getMatriz().length-2; j++ ){
                		if(CH.getMatriz()[i][j].toCharArray()[0] == 'X'){
                    		CH.getMatriz()[i][j] = "1";
                    		for(n = vc.getVC(); n>0; n-- ){
                    			CH.getMatriz()[i][CH.getMatriz().length-n] = "0";
                    			CH.getMatriz()[CH.getMatriz().length-n][i] = "0";
                    		}
                        	break;
                    	}else{
                    		for(n = vc.getVC(); n>0; n-- ){
                    			CH.getMatriz()[i][CH.getMatriz().length-n] = "1";
                    			CH.getMatriz()[CH.getMatriz().length-n][i] = "1";
                    		}
                    	}
	                }
	                i+=6;
	            }
	            
	            for(i = 0; i<CH.getTamMatriz(); i++ ){
	                for(j = 0; j<CH.getTamMatriz(); j++ ){
	                	textAreaCH.append(CH.getMatriz()[i][j]+"\t"); 
	                }
	                textAreaCH.append("\n");
	            }
				
			}
			
			if(eve.getSource().equals(case2)){

				textAreaTSP.setText("Programando...");
				
			}
			
		}
	
	} 
		
}

