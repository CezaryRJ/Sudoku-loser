import java.util.*;
import java.io.*;

public class Brett {
	rute brett[][];
	Kolonne kolonner[];
	Rad rader[];
	Boks bokser[];
	
	private ArrayList<ArrayList<String>> y = new ArrayList<>();
	private ArrayList<String> rawdata = new ArrayList<>();

	private int hoyde;
	private int lengde;


	Brett() throws Exception {

	}
	void delInnRuter(){

		lagrad();
		lagkolonne();
		lagboks();
		
		
	}

	void lagrad() {

		for(int i = 0;i<(hoyde*lengde);i++){
			rader[i] = new Rad(brett[i]);
		}
		for(int i = 0;i<(hoyde*lengde);i++){
			for(int a = 0;a<(hoyde*lengde);a++){
				brett[i][a].settrad(rader[i]);
			}
				
			}
		
	}

	void lagkolonne() {
		rute[] tmp = new rute[hoyde*lengde];
	for(int i = 0;i<(hoyde*lengde);i++){
		for(int a = 0;a<(hoyde*lengde);a++){
			tmp[a] = brett[a][i];
			
			
		}
		kolonner[i] = new Kolonne(tmp);
		tmp = new rute[hoyde*lengde];
		
	}
	for(int i = 0;i<(hoyde*lengde);i++){
		for(int a = 0;a<(hoyde*lengde);a++){
			brett[i][a].settkolonne(kolonner[a]);
		}
		}

	}

	void lagboks() {
		for(int i = 0; i< hoyde*lengde;i++){
			for(int a = 0; a< hoyde*lengde;a++){
				rute tmp = brett[i][a];
				int boksnr = tmp.getboksnr();
				bokser[boksnr].settinn(tmp);
				tmp.settboks(bokser[boksnr]);
				
			}
			
		}
		
	}
		
		
		
		
	


	

	public void lesFil(String fil) throws FileNotFoundException {
		Scanner leser = new Scanner(new File(fil));

		while (leser.hasNextLine()) {
			String tmp = leser.nextLine();
			rawdata.add(tmp);
		}
		hoyde = Integer.parseInt(rawdata.get(0));
		lengde = Integer.parseInt(rawdata.get(1));

		brett = new rute[hoyde*lengde][lengde*hoyde];
		kolonner = new Kolonne[hoyde * lengde];
		rader = new Rad[hoyde * lengde];
		bokser = new Boks[lengde*hoyde];
		for(int i = 0; i<(hoyde*lengde);i++){
			bokser[i] = new Boks(hoyde*lengde);
		}

		for (int i = 2; i < rawdata.size(); i++) {
			String ting = rawdata.get(i);

			y.add(splitt(ting));

		}
		lagruter();

	}

	ArrayList<String> splitt(String ting) {
		ArrayList<String> tmp = new ArrayList<String>();
		for (int b = 0; b < ting.length(); b++) {
			String test = (ting.substring(b, b + 1));
			tmp.add(test);

		}

		return tmp;

	}

	void lagruter() {
	int[] boksnr = giboks(hoyde,lengde);
	int index = 0;
		for (int i = 0; i < y.size(); i++) {
			ArrayList<String> tmp = y.get(i);
			for (int a = 0; a < tmp.size(); a++) {
				String tmp1 = tmp.get(a);
				if (tmp1.equals(".")) {
					brett[i][a] = new rute((hoyde*lengde),i,a,boksnr[index],false); index++;
				} else {
					brett[i][a] = new rute(Integer.parseInt(tmp1),(hoyde*lengde),i,a,boksnr[index],true); index++;
				}
				
			}
		}

	}
	void fyllUtDenneOgResten(rute[][] a, SudokuBeholder beholder){
	
		brett[0][0].fyllUtDenneOgResten(a,beholder);
	
	}
	void printbrett(){
		int xakse = 0;
		int yakse = 0;
		System.out.println("Loaded board");
		for(int y = 0;y<(hoyde*lengde);y++){
			
			for(int x = 0;x<(hoyde*lengde);x++){
			
				if (xakse % lengde == 0 && xakse != (hoyde * lengde) && xakse != 0) {
					System.out.print(" | ");
				}

				
				if(brett[y][x].getverdi() == 0){
					System.out.print("-");
				}
				else{
				System.out.print(brett[y][x].getverdi());
			}	xakse++;
				}
			yakse++;
			xakse = 0;
			if (yakse % hoyde == 0) {
				System.out.println("");
				for(int a = 0;a<(hoyde*lengde);a++){
				System.out.print("--");}
				System.out.println("");
			
			} else {
				System.out.println();
			}
				
		}
	}
	void sjekklaas(){
		for(int y = 0;y<(hoyde*lengde);y++){
			
			for(int x = 0;x<(hoyde*lengde);x++){
				if(brett[y][x].laas() == true){
					System.out.print("L");
				}
				else{
				System.out.print(brett[y][x].getverdi());
			}}
			System.out.println();
				
		}
	}

	
	
	public static rute[] settsammen(rute[] a, rute[] b) {
		int length = a.length + b.length;
		rute[] result = new rute[length];
		System.arraycopy(a, 0, result, 0, a.length);
		System.arraycopy(b, 0, result, a.length, b.length);
		return result;
	}

	int gethoyde() {
		return hoyde;
	}

	int getlengde() {
		return lengde;
	}

	
	int[] giboks(int hoyde1,int lengde1){
		int lengde = lengde1;
		//antallrader i en boks
		int hoyde = hoyde1;
		int antallkjort = 0;
		int boks = 0;
		int index = 0;
		int[] sekvens = new int[(hoyde*lengde*hoyde*lengde)];
		for(int i = 0;i<(hoyde*lengde*hoyde*lengde);i++){

			if(i%lengde == 0 && i!=0){
				boks++;
			}
			if(i%(lengde*hoyde) == 0 && i!=0){
				boks-= hoyde;
				antallkjort++;
			}
			if(antallkjort == hoyde){
				boks+=hoyde;
				antallkjort = 0;
			}
			sekvens[index] = boks;
			index++;
			
		}return sekvens;

	}
	}

