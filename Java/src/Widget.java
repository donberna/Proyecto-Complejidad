
public class Widget {
	
	int [][] matriz;
	String [] etiquetas;
	
	public Widget(String etiqueta){
		matriz = new int[12][12];
		etiquetas = new String[12];
		
		for(int i=1; i<12; i++){
			
			matriz[i-1][i]=1;
			matriz[i][i-1]=1;
			
			if (i%6==0){
				matriz[i-1][i]=0;
			}
		}
		
		matriz[2][6]=1;
		matriz[6][2]=1;
		
		matriz[0][8]=1;
		matriz[8][0]=1;
		
		matriz[3][11]=1;
		matriz[11][3]=1;
		
		matriz[5][9]=1;
		matriz[9][5]=1;
		
		
		for(int i = 0; i<12; i++){
			etiquetas[i] = etiqueta+i;
			if(i>5){
				etiquetas[i] = "" + etiqueta.toCharArray()[1] + etiqueta.toCharArray()[0] +(i-6);
			}
		}
	} 

}
