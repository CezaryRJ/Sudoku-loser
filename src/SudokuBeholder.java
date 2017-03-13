import java.util.ArrayList;

public class SudokuBeholder {

	long antallsvar = 0;
	ArrayList<int[][]> svar = new ArrayList<>();
	long antallsvar(){
		return antallsvar;
	}

	void settInn(int[][] tmp) {
		if (svar.size() < 2500) {
			svar.add(tmp);

		}
		antallsvar++;
	
	}

	int[][] taUt(int index) {
		return svar.get(index);
	}

	
	void print(int lengde, int hoyde) {
		if (svar.size() == 0) {
			System.out.println("No solutions found");
		} else {
			int xakse = 1;
			int yakse = 0;
			for (int i = 0; i < svar.size(); i++) {
				System.out.println("Solution " + (i+1));
				for (int y = 0; y < svar.get(i).length; y++) {
					for (int x = 0; x < svar.get(i).length; x++) {
						System.out.print(svar.get(i)[y][x]);
						if (xakse % lengde == 0 && xakse != (hoyde * lengde)) {
							System.out.print(" | ");
						}

						xakse++;

					}

					yakse++;
					if (yakse % hoyde == 0) {
						System.out.println("");
						for(int a = 0;a<(hoyde*lengde);a++){
						System.out.print("- ");}
						System.out.println("");
					
					} else {
						System.out.println();
					}xakse = 1;
				}	
			

			}
			System.out.println("Amount of solutions found = " + antallsvar);
		}
	}
}