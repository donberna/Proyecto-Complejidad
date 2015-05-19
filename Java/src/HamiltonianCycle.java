import java.util.ArrayList;

/*
 * Diseñadores: Miguel Angel Bernal Colonia Codigo: 201153852 - 3743
 * 				Yeison Betancourt Solis Codigo: 201153328 - 3743 				
 * 				Andrea Mora Ospina Codigo: 201153685 - 3743
 * 
 * Instituto: Universidad del Valle - 06 Tuluá
 */

public class HamiltonianCycle {
	
	private int sizeMatriz;	
	private String [ ] [ ] matriz;
	private int i,j,index;
    private int row,col,colTmp;
    private int n,tmp;
    private char pos0,pos2,tmpPos;
    
    
	
	public int getSizeMatriz() {
		return sizeMatriz;
	}

	public void setSizeMatriz(int sizeMatriz) {
		this.sizeMatriz = sizeMatriz;
	}

	public String[][] getMatriz() {
		return matriz;
	}

	public void setMatriz(String[][] matriz) {
		this.matriz = matriz;
	}
	
	// Se contabilizan el numero de aristas del grafo a partir de su matriz de adyacencia
	public void countEdges(VertexCover VC, ArrayList<Widget> w ){
        for(row=0; row<VC.getSizeMatriz(); row++){
        	for(col=row; col<VC.getSizeMatriz(); col++){
        		if(VC.getMatriz()[row][col] == 1 && VC.getMatriz()[col][row] == 1){
        			VC.setEdges(VC.getEdges()+1);
        			Widget a = new Widget(""+row+col);
        			w.add(a);
        		}
        	}
        } 
	}
	
	// Se crea la matriz del grafo de la reduccion
	public void fillMatriz(int edges, int vc){
        setSizeMatriz((edges * 12) + vc+1); 
        setMatriz(new String [getSizeMatriz()] [getSizeMatriz()]);
        for(i = 0; i<getSizeMatriz(); i++ ){
            for(j = 0; j<getSizeMatriz(); j++ ){
            	matriz[i][j]=""+0;
            }
        }
	}
	
	// Se llena la matriz de adyacencia con las etiquetas
	public void fillMatrizTags(int edges, ArrayList<Widget> w){
		index=0;
        while(index<w.size()){
        	int a = 0;
        	for(i = 0; i<(edges * 12); i++ ){
        		matriz[0][i+1] = w.get(index).tags[a];
        		matriz[i+1][0] = w.get(index).tags[a];
                a++;
                if(a>=12){
                	index++;
                	a=0;
                }
            }
        }
	}
	
	// Se llena la matriz de adyacencia con la matriz de widget
	public void fillMatrizWidget(ArrayList<Widget> w){
		index=0;
        row=1;
        col=1;
        colTmp=1;
        while(index<w.size()){
        	col=colTmp;
        	for(i = 1; i<13; i++ ){
        		for(j = 1; j<13; j++ ){
        			matriz[row][col] = ""+w.get(index).matriz[i-1][j-1];
        			col++;
        		}
        		colTmp++;
        		col= 1 + (index*12);
        		row++;
            }
        	index++;
        }   
	}
	
	// Se llena la matriz de adyacencia con las conexiones que tienen los widgets
	public void fillMatrizConnectionsWidgets(int edges){
		n=0;
        tmp=12;
        i=6;
        while(n<edges){
            pos0 = matriz[i][0].toCharArray()[0];
            pos2 = matriz[i][0].toCharArray()[2];
            for(j = (tmp+1); j<getMatriz().length-2; j++ ){
            	if(pos2=='5'){
            		tmpPos='0';
            		if((pos0 == matriz[0][j].toCharArray()[0]) && (tmpPos == matriz[0][j].toCharArray()[2])){
            			matriz[i][j] = "X";
            			matriz[j][i] = "X";
                		break;
                	}
            	}else
            		if(pos2=='0'){
            			tmpPos='5';
                		if((pos0 == matriz[0][j].toCharArray()[0]) && (tmpPos == matriz[0][j].toCharArray()[2])){
                			matriz[i][j] = "X";
                			matriz[j][i] = "X";
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
	}
	
	// Se llena la matriz de adyacencia con las conexiones que tienen los demas vertices
	public void fillMatrizConnectionsVertex(int vc){
		i=1;
        while(i<getMatriz().length-2){
            for(j = 0; j<getMatriz().length-2; j++ ){
        		if(matriz[i][j].toCharArray()[0] == 'X'){
        			matriz[i][j] = "1";
            		for(n = vc; n>0; n-- ){
            			matriz[i][getMatriz().length-n] = "0";
            			matriz[getMatriz().length-n][i] = "0";
            		}
                	break;
            	}else{
            		for(n = vc; n>0; n-- ){
            			matriz[i][getMatriz().length-n] = "1";
            			matriz[getMatriz().length-n][i] = "1";
            		}
            	}
            }
            i+=6;
        }
        
        i=6;
        while(i<getMatriz().length-2){
            for(j = 0; j<getMatriz().length-2; j++ ){
        		if(matriz[i][j].toCharArray()[0] == 'X'){
        			matriz[i][j] = "1";
            		for(n = vc; n>0; n-- ){
            			matriz[i][getMatriz().length-n] = "0";
            			matriz[getMatriz().length-n][i] = "0";
            		}
                	break;
            	}else{
            		for(n = vc; n>0; n-- ){
            			matriz[i][getMatriz().length-n] = "1";
            			matriz[getMatriz().length-n][i] = "1";
            		}
            	}
            }
            i+=6;
        }
	}

}
