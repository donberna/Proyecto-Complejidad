/*
 * Diseñadores: Miguel Angel Bernal Colonia Codigo: 201153852 - 3743
 * 				Yeison Betancourt Solis Codigo: 201153328 - 3743 				
 * 				Andrea Mora Ospina Codigo: 201153685 - 3743
 * 
 * Instituto: Universidad del Valle - 06 Tuluá
 */

public class VertexCover {
	
	private int edges;
	private int VC;
	private int [ ] [ ] matriz;
	private int sizeMatriz;
	
	public int getEdges() {
		return edges;
	}

	public void setEdges(int edges) {
		this.edges = edges;
	}
	
	public int getVC() {
		return VC;
	}

	public void setVC(int vC) {
		VC = vC;
	}
	
	public int[][] getMatriz() {
		return matriz;
	}

	public void setMatriz(int[][] matriz) {
		this.matriz = matriz;
	}
	
	public int getSizeMatriz() {
		return sizeMatriz;
	}

	public void setSizeMatriz(int sizeMatriz) {
		this.sizeMatriz = sizeMatriz;
	}
	
}
