import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.JTextField;


public class Skriver {
	void lagBrett(String[][] brett,int hoyde, int lengde) throws FileNotFoundException{
		PrintWriter skriver = new PrintWriter("tmp.txt");
		skriver.println(hoyde);
		skriver.println(lengde);
		for(int y = 0;y<(hoyde*lengde);y++){
			for(int x = 0; x<(hoyde*lengde);x++){
				if(brett[y][x] == null){
					skriver.print(".");
				}
				else{
					String tmp = brett[y][x];
					skriver.print(tmp);
					}
				}skriver.println();
				
			}skriver.close();
			
		}
		
	}


