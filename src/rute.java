public class rute {
	int rady;
	int kolonnex;
	int boksnr;

	private boolean laast;
	private int verdi = 0;
	private Boks boks;
	private Kolonne kolonne;
	private Rad rad;
	private int antallmulige;

	rute(int verdi, int mulige, int y, int x, int boksnr, boolean laast) {
		this.laast = laast;
		this.verdi = verdi;
		antallmulige = mulige;
		rady = y;
		kolonnex = x;
		this.boksnr = boksnr;
	}

	rute(int mulige, int y, int x, int boksnr, boolean laast) {
		antallmulige = mulige;
		kolonnex = x;
		rady = y;
		this.boksnr = boksnr;
		this.laast = laast;
	}

	int[] finnAlleMulige() {
	
			int[] allemulige = new int[antallmulige + 1];
			for (int i = 0; i <= antallmulige; i++) {
				allemulige[i] = i;
			}

			for (int i = 0; i < antallmulige; i++) {

				int a = boks.getruter()[i].getverdi();
				int b = kolonne.getruter()[i].getverdi();
				int c = rad.getruter()[i].getverdi();
				for (int x = 1; x <= antallmulige; x++) {
					if (a == x || c == x || b == x) {
						allemulige[x] = 0;
					}

				}
			}
			int counter = 0;
			for (int i = 0; i < allemulige.length; i++) {
				if (allemulige[i] != 0) {
					counter++;
				}
			}
			int[] ferdige = new int[counter];
			int index = 0;
			for (int i = 0; i < allemulige.length; i++) {
				if (allemulige[i] != 0) {
					ferdige[index] = allemulige[i];
					index++;
				}
			}

			return ferdige;
	

	}

	// ///this shit
	void settinnsvar(rute[][] brett, SudokuBeholder beholder) {
		rute[][] brett1 = brett;
		SudokuBeholder beholder1 = beholder;
		int[][] tmp = new int[antallmulige][antallmulige];
		for (int y = 0; y < brett1.length; y++) {
			for (int x = 0; x < brett1.length; x++) {
				tmp[y][x] = brett1[y][x].getverdi();
				;

			}
		}
		beholder1.settInn(tmp);
	}

	void fyllUtDenneOgResten(rute[][] brett, SudokuBeholder beholder) {
		rute[][] brett1 = brett;
		SudokuBeholder beholder1 = beholder;
		// siste rute
		if (laast == true) {

			if (rady == (antallmulige - 1) && kolonnex == (antallmulige - 1)) {
				
				settinnsvar(brett1, beholder1);
			
			}
			else if (kolonnex < antallmulige - 1) {
				rad.getruter()[kolonnex + 1].fyllUtDenneOgResten(brett1, beholder1);


		} 
			else {
				kolonne.getruter()[rady + 1].getrad().getruter()[0]
						.fyllUtDenneOgResten(brett1, beholder1);

		} 
		}

		if (laast == false) {
			int[] tmp = finnAlleMulige();

			if (rady == (antallmulige - 1) && kolonnex == (antallmulige - 1)) {
				for (int i = 0; i < tmp.length; i++) {
					verdi = tmp[i];
					settinnsvar(brett1, beholder1);
					verdi = 0;
					

				}
			} else if (kolonnex < antallmulige - 1) {
				for (int i = 0; i < tmp.length; i++) {
					verdi = tmp[i];
					rad.getruter()[kolonnex + 1].fyllUtDenneOgResten(brett1,
							beholder1);
					verdi = 0;

				}
			} else {
				for (int i = 0; i < tmp.length; i++) {
					verdi = tmp[i];
					kolonne.getruter()[rady + 1].getrad().getruter()[0]
							.fyllUtDenneOgResten(brett1, beholder1);
					verdi = 0;
				}
			}
		}

	}

	
	int getverdi() {
		return verdi;
	}

	Rad getrad() {
		return rad;
	}

	Boks getboks() {
		return boks;
	}

	Kolonne getkolonne() {
		return kolonne;
	}

	void settboks(Boks a) {
		boks = a;

	}

	void settrad(Rad a) {
		rad = a;

	}

	
	void settkolonne(Kolonne a) {
		kolonne = a;
	}

	int gety() {
		return rady;
	}

	int getx() {
		return kolonnex;
	}

	int getboksnr() {
		return boksnr;
	}

	
	boolean laas(){
		return laast;
	}

}
