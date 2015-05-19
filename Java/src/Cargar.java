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
public class Cargar extends JFrame{

	private JLabel labelDirectory = new JLabel("Archivo:");
	private JLabel routeDirectory = new JLabel("");
	private JLabel superviser = new JLabel();
	private JLabel problem = new JLabel("Problema: ");
	private JTextArea textArea = new JTextArea(); 
	private JButton examine = new JButton("Examinar");
	private JButton bExit = new JButton();
	private JButton bBack = new JButton();
	private JButton case1 = new JButton("VC <p HC");
	private JButton case2 = new JButton("HC <p TS");
	private JFileChooser archivo = new JFileChooser();
	
	private String dato;
	
	// Objetos
	VertexCover vc = new VertexCover();
	
	// Reduccion 
    String [ ] [ ]matriz_adyacencia;
   
			
	public Cargar(){
		
		 this.setExtendedState(MAXIMIZED_BOTH);
		//setEnabled(false);
	    //setResizable(false);
	    setVisible(true);
		setTitle("Configuracion");
		//setSize(600,500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.WHITE);
		
		Font font = new Font("Tahoma", 1, 12);	
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		textArea.setEditable(false);
		
		getContentPane().add(labelDirectory);
		getContentPane().add(routeDirectory);
		getContentPane().add(superviser);
		getContentPane().add(examine);
		getContentPane().add(problem);
		getContentPane().add(scrollPane);
		getContentPane().add(bExit);
		getContentPane().add(bBack);		
		getContentPane().add(case1);
		getContentPane().add(case2);
		
		
		examine.setBounds(240, 150, 100, 40);
		problem.setBounds(380, 80, 200, 40);
		scrollPane.setBounds(380, 120, 170, 170);
		labelDirectory.setBounds(210, 230, 80, 40);
		labelDirectory.setFont(font);
		routeDirectory.setBounds(265, 230, 150, 40);
		routeDirectory.setFont(font);
		case1.setBounds(120, 340, 100, 40);
		case2.setBounds(350, 340, 100, 40);
			
		ImageIcon img1 = new ImageIcon("img/supervisor.png");
		superviser.setIcon(img1);
		superviser.setBounds(20, 80, 200, 250);
		
		ImageIcon img2 = new ImageIcon("img/salir.png");		
		ImageIcon img3 = new ImageIcon("img/atras.png");	
		
		bExit.setIcon(img2);
		bExit.setBounds(1100, 600, 150, 100);
		bExit.setOpaque(false);
		bExit.setContentAreaFilled(false);
		bExit.setBorderPainted(false);
		
		bBack.setIcon(img3);
		bBack.setBounds(30, 600, 70, 100);
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
        
        problem.setText("Problema: Vertex Cover");
        
        try {
            BufferedReader in = new BufferedReader(new FileReader(f));
            line = in.readLine();
            
            StringTokenizer st = new StringTokenizer(line);
            vc.setTamMatriz(returnInt(st.nextToken()));
        	
        	i = 0;
        	textArea.append(vc.getTamMatriz()+"\n");
        	
        	vc.setMatrix(new int [vc.getTamMatriz()] [vc.getTamMatriz()]);
            
        	// se lee el arcivo de entrada 
            while ((line = in.readLine()) != null) {
            	j = 0;
            	if(i < vc.getTamMatriz()){
            		textArea.append(line); 
            		StringTokenizer st3 = new StringTokenizer(line);
            		
            		while(j < vc.getTamMatriz()){
            			vc.getMatrix()[i][j] =  returnInt(st3.nextToken());
            			j++;
            		}
            		
                	if(!line.startsWith(" ")){
                		textArea.append("\n");            	
                	}      
                	
                	i++;
            	}else{
            		StringTokenizer st2 = new StringTokenizer(line);
                	vc.setVC(returnInt(st2.nextToken()));
            		textArea.append(line);
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

				textArea.setText("");
				routeDirectory.setText(dato);	
				Init("datos/"+dato);
				
			}
			
			if(eve.getSource().equals(case1)){

				problem.setText("Problema: Circuito Hamiltoniano");
				textArea.setText("");
				
				ArrayList <Widget> w;
	            w = new <Widget>ArrayList();
	            vc.setAristas(0);
	            int i,j;
	            
	            // Se contabilizan el numero de aristas del grafo a partir de su matriz de adyacencia 
	            for(int row=0; row<vc.getTamMatriz(); row++){
	            	for(int col=row; col<vc.getTamMatriz(); col++){
	            		if(vc.getMatrix()[row][col] == 1 && vc.getMatrix()[col][row] == 1){
	            			vc.setAristas(vc.getAristas()+1);
	            			Widget a = new Widget(""+row+col);
	            			w.add(a);
	            			
	            		}
	            	}
	            }
	            System.out.println("aristas: "+vc.getAristas());                     
	           
	            // se crea la matriz del grafo de la reduccion
	            int tamano = ((vc.getAristas() * 12) + vc.getVC()+1); 
	            matriz_adyacencia =  new String [tamano] [tamano];
	            for(i = 0; i<tamano; i++ ){
	                for(j = 0; j<tamano; j++ ){
	                	matriz_adyacencia[i][j]=""+0;
	                }
	            }
	      
	            int index=0;
	            while(index<w.size()){
	            	int a = 0;
	            	for(i = 0; i<(vc.getAristas() * 12); i++ ){
	                    matriz_adyacencia[0][i+1] = w.get(index).etiquetas[a];
	                    matriz_adyacencia[i+1][0] = w.get(index).etiquetas[a];
	                    //System.out.println("w: "+w.get(i).matriz[0][0]);
	                    a++;
	                    if(a>=12){
	                    	index++;
	                    	a=0;
	                    }
	                }
	            	
	            }
	            
	            index=0;
	            int row=1;
	            int col=1;
	            int colTmp=1;
	            while(index<w.size()){
	            	col=colTmp;
	            	for(i = 1; i<13; i++ ){
	            		for(j = 1; j<13; j++ ){
	            			matriz_adyacencia[row][col] = ""+w.get(index).matriz[i-1][j-1];
	            			//System.out.print(" Row: "+row+" Col: "+col);
	            			//System.out.print(" Row: "+i+" Col: "+j);
	            			//System.out.println("ColTEMP: "+colTmp);
	            			col++;
	            		}
	            		colTmp++;
	            		col= 1 + (index*12);
	            		row++;
	            		//System.out.println('\t');
	                }
	            	//System.out.println('x');
	            	index++;
	            }   
	            
	            int n=0;
	            int tmp=12;
	            char pos0;
	            char pos2;
	            char tmpPos;
	            j=6;
	            while(n<vc.getAristas()){
	                //System.out.println(matriz_adyacencia[j][0].toCharArray());
	                pos0 = matriz_adyacencia[j][0].toCharArray()[0];
	                pos2 = matriz_adyacencia[j][0].toCharArray()[2];
	                //System.out.println(pos0);
	                //System.out.println(pos2);
	                //System.out.println(matriz_adyacencia.length);
	                for(int k = (tmp+1); k<matriz_adyacencia.length-2; k++ ){
	                	//System.out.println(matriz_adyacencia[k][0].toCharArray());
	                	//System.out.println("nro iter: "+k);
	                	if(pos2=='5'){
	                		tmpPos='0';
	                		//System.out.println("else1: "+tmpPos);
	                		if((pos0 == matriz_adyacencia[0][k].toCharArray()[0]) && (tmpPos == matriz_adyacencia[0][k].toCharArray()[2])){
	                    		//System.out.println("J1: "+j);
	                        	//System.out.println("K1: "+k);
	                    		//System.out.println(matriz_adyacencia[0][k].toCharArray());
	                    		matriz_adyacencia[j][k] = "X";
	                    		matriz_adyacencia[k][j] = "X";
	                    		break;
	                    	}
	                	}else
	                		if(pos2=='0'){
	                			tmpPos='5';
	                    		//System.out.println("else2: "+tmpPos);
	                    		if((pos0 == matriz_adyacencia[0][k].toCharArray()[0]) && (tmpPos == matriz_adyacencia[0][k].toCharArray()[2])){
	                        		//System.out.println("J2: "+j);
	                            	//System.out.println("K2: "+k);
	                        		//System.out.println(matriz_adyacencia[0][k].toCharArray());
	                        		matriz_adyacencia[j][k] = "X";
	                        		matriz_adyacencia[k][j] = "X";
	                        		break;
	                        	}
	                		}
	                }
	                //System.out.println("salio del for");
	                if(j==tmp){
	                	j++;
	                	tmp*=2;
	                }
	                j+=6;
	                n++;
	            }
	            	        
	            i=1;
	            while(i<matriz_adyacencia.length-2){
	                //System.out.println(matriz_adyacencia[j][0].toCharArray());
	                //System.out.println("i first: "+i);
	                //System.out.println(pos2);
	                //System.out.println(matriz_adyacencia.length);
	                for(j = 0; j<matriz_adyacencia.length-2; j++ ){
	                	//System.out.println(matriz_adyacencia[k][0].toCharArray());
	                	//System.out.println("nro iter: "+k);	                	
                		if(matriz_adyacencia[i][j].toCharArray()[0] == 'X'){
                    		//System.out.println(matriz_adyacencia[0][k].toCharArray());
                    		matriz_adyacencia[i][j] = "1";
                    		for(int v = vc.getVC(); v>0; v-- ){
                    			matriz_adyacencia[i][matriz_adyacencia.length-v] = "0";
                    			matriz_adyacencia[matriz_adyacencia.length-v][i] = "0";
                    		}
                    		//System.out.println("if i1: "+i);
                        	//System.out.println("if j1: "+j);
                        	//System.out.println("matriz: "+(matriz_adyacencia.length-2));
                        	//System.out.println("matriz: "+(matriz_adyacencia.length-1));
                        	//System.out.println(matriz_adyacencia[i][matriz_adyacencia.length-2]);
                        	//System.out.println(matriz_adyacencia[i][matriz_adyacencia.length-1]);
                        	break;
                    	}else{
                    		//System.out.println("else i2: "+i);
                        	//System.out.println("else j2: "+j);
                    		for(int v = vc.getVC(); v>0; v-- ){
                    			matriz_adyacencia[i][matriz_adyacencia.length-v] = "1";
                    			matriz_adyacencia[matriz_adyacencia.length-v][i] = "1";
                    		}
                    	}
	                }
	                //System.out.println("salio del for");
	                i+=6;
	            }
	            
	            i=6;
	            while(i<matriz_adyacencia.length-2){
	                //System.out.println(matriz_adyacencia[j][0].toCharArray());
	                //System.out.println("i first: "+i);
	                //System.out.println(pos2);
	                //System.out.println(matriz_adyacencia.length);
	                for(j = 0; j<matriz_adyacencia.length-2; j++ ){
	                	//System.out.println(matriz_adyacencia[k][0].toCharArray());
	                	//System.out.println("nro iter: "+k);	                	
                		if(matriz_adyacencia[i][j].toCharArray()[0] == 'X'){
                    		//System.out.println(matriz_adyacencia[0][k].toCharArray());
                    		matriz_adyacencia[i][j] = "1";
                    		for(int v = vc.getVC(); v>0; v-- ){
                    			matriz_adyacencia[i][matriz_adyacencia.length-v] = "0";
                    			matriz_adyacencia[matriz_adyacencia.length-v][i] = "0";
                    		}
                    		//System.out.println("if i1: "+i);
                        	//System.out.println("if j1: "+j);
                        	//System.out.println("matriz: "+(matriz_adyacencia.length-2));
                        	//System.out.println("matriz: "+(matriz_adyacencia.length-1));
                        	//System.out.println(matriz_adyacencia[i][matriz_adyacencia.length-2]);
                        	//System.out.println(matriz_adyacencia[i][matriz_adyacencia.length-1]);
                        	break;
                    	}else{
                    		//System.out.println("else i2: "+i);
                        	//System.out.println("else j2: "+j);
                    		for(int v = vc.getVC(); v>0; v-- ){
                    			matriz_adyacencia[i][matriz_adyacencia.length-v] = "1";
                    			matriz_adyacencia[matriz_adyacencia.length-v][i] = "1";
                    		}
                    	}
	                }
	                //System.out.println("salio del for");
	                i+=6;
	            }
	            
	            for(i = 0; i<tamano; i++ ){
	                for(j = 0; j<tamano; j++ ){
	                	textArea.append(matriz_adyacencia[i][j]+"\t"); 
	                	System.out.print(matriz_adyacencia[i][j]+"\t");
	                }
	                textArea.append("\n");
	                System.out.println("\t");
	            }
				
			}
			
			if(eve.getSource().equals(case2)){

				problem.setText("Problema: Agente Viajero");
				textArea.setText("Programando...");
				
			}
			
		}
	
	} 
		
}

