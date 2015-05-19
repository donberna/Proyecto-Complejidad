/*
 * Dise�adores: Miguel Angel Bernal Colonia Codigo: 201153852 - 3743
 * 				Yeison Betancourt Solis Codigo: 201153328 - 3743 				
 * 				Andrea Mora Ospina Codigo: 201153685 - 3743
 * 
 * Instituto: Universidad del Valle - 06 Tulu�
 */

public class Widget {
	
	private int [][] matriz;
	private String [] tags;
	
	public Widget(String tag){
		matriz = new int[12][12];
		tags = new String[12];
		
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
			tags[i] = tag+i;
			if(i>5){
				tags[i] = "" + tag.toCharArray()[1] + tag.toCharArray()[0] +(i-6);
			}
		}
	} 
	
	
	public int [][] getMatrizWidget(){
		return matriz;
	}
	
	public String [] getTagsWidget(){
		return tags;
	}

}
