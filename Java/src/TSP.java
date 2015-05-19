
public class TSP {
	
	private int sizeMatriz;	
	private String [ ] [ ] matrizHC;
	private String [ ] [ ] matrizTSP;
	
	
	public int getSizeMatriz() {
		return sizeMatriz;
	}

	public void setSizeMatriz(int sizeMatriz) {
		this.sizeMatriz = sizeMatriz;
	}

	public String[][] getMatriz() {
		return matrizTSP;
	}

	public void setMatriz(String[][] matriz) {
		this.matrizTSP = matriz;
	}
	
	public String[][] getMatrizHC() {
		return matrizHC;
	}

	public void setMatrizHC(String[][] matriz) {
		this.matrizHC = matriz;
	}
	
	public void makeCompleteGraph(){
	
		//se crea una copia del grafo de entrada 
		matrizTSP = new String [sizeMatriz][sizeMatriz] ;
		for(int i = 0; i < sizeMatriz; i++){
			for(int j = 0; j < sizeMatriz; j++){
				matrizTSP[i][j] = matrizHC[i][j]; 
			}
		}
		
		
		// se completan las conexiones 
		for(int i = 1; i < sizeMatriz; i++){
			for(int j = 1; j < sizeMatriz; j++){
				matrizTSP[i][j] = "1"; 
			}
		}
		
		/*
		System.out.println("Matriz TSP");
		for(int i = 0; i < sizeMatriz; i++){
			for(int j = 0; j < sizeMatriz; j++){
				System.out.print(matrizTSP[i][j] + '\t');
			}
			System.out.println('\n');
		}
		
		System.out.println("Matriz HC");
		for(int i = 0; i < sizeMatriz; i++){
			for(int j = 0; j < sizeMatriz; j++){
				System.out.print(matrizHC[i][j] + '\t');
			}
			System.out.println('\n');
		}*/
	}
	
	
	public void allocateWeightTSP(){
		
		//se compara q la arista exista, si no exite se pone un 2
		for(int i = 1; i < sizeMatriz; i++){
			for(int j = 1; j < sizeMatriz; j++){
				//System.out.println("tsp: "+ matrizTSP[i][j] + " hc: "+matrizHC[i][j]);
				if(matrizTSP[i][j].equals(matrizHC[i][j])){
					matrizTSP[i][j] = "1";
				}else{
					matrizTSP[i][j] = "2";
				} 
			}
		}
	}
}
